/** -----------------------------------------------------------------------------
 * @file
 * @author Mathews Jacob & Aaron Ambrose
 * @brief Partial solution to homework 5: Photoshop Competitor
 * @brief This program will either: linearly smooth an image (imput 'smooth'), adaptively smooth
 * @brief an image (imput 'asmooth'), or remove a blemish from an image (imput 'resolve') based
 * @brief on your input.  The image to be modified should be imputted in the program arguments as well.
 * @brief You then might have to enter more parameters based on your choice.
 *
 * PSEUDOCODE:
 */
#include "Image.h"
#include "PixelLocation.h"
#include <iostream>
#include <stack>
#include <cmath>
#include <iostream>
#include <stdio.h>
#include <string.h>

// possible function prototypes
/***************************************************************************************
* @brief Function to compute the  component of the image that is 4 connected to the pixel
*        indexed by seedRow and seedCol and has the same value as the seedPixel
* @param image
* @param seedRow
* @param seedCol
* @param ccLabel
***************************************************************************************/
static void markConnectedComponent(Image & image, const int seedRow, const int seedCol, const int ccLabel = 255);

/***************************************************************************************
* @brief Function to adaptive smooth the image
*
* @param image:  InputImage
* @param sigma:  standard deviation of the noise (default value is 20)
* @param factor: Strength of smoothing (default value is 0.2)
*
* @return image: smoothed image
 ***************************************************************************************/
static Image smoothAdaptive(const Image & image, const double sigma=20, const double factor = 0.2);

/***************************************************************************************
 * @brief Function to smooth the image
 *
 * @param image:  InputImage
 * @param alpha: Strength of smoothing (default value is 0.2)
 *
 * @return image: smoothed image
 ***************************************************************************************/
static Image smooth(const Image & myImage, const double alpha = 0.2);

/***************************************************************************************
* @brief The main function call
*
* @param argc  The number of arguments provided on the command line
* @param argv[] The vector of character strings from the command line
*
* @return 0 if no errors occured
 ***************************************************************************************/

int main( int argc, char* argv[] )
{
  // get input/output image file names (and threshold) from command line
  if( argc != 4 )
    {
    std::cout << "Usage instructions: " << std::endl;
    std::cout << "> hw5.exe inputImageFileName.bmp outputImage.bmp processtype ('smooth'/'asmooth'/'restore')"
              << std::endl;
    return -1;
    }
  const std::string inputImageFileName(argv[1]);

  // read input image
  std::cout << "Reading input image: " << inputImageFileName << std::endl;
  Image      myImage;
  const bool success = myImage.readFromBMPFile(inputImageFileName);
  if( !success )
    {
    std::cout << "Error reading input image." << std::endl;
    return -1;
    }
  const std::string outputImageFileName(argv[2]);

    std::cout << "Image Size " << myImage.getNumRows() << " " << myImage.getNumCols() << std::endl;

    Image processedImage(myImage);

  if( strcmp(argv[3], "smooth") == 0 ) // Smoothing
    {
    int iter;
    int alpha;
    std::cout << "Smoothing: Enter number of iterations" << std::endl;
    std::cin >> iter;

    std::cout << "Smoothing the image: " << std::endl;
    for( int i = 1; i < iter; ++i )
      {
          processedImage = smooth(processedImage);
      }
    }
  /* Adaptive smoothing */

  else if( strcmp(argv[3], "asmooth") == 0 )
    {                                       // Adaptive Smoothing
    int iter;
    int sigma;
    std::cout << "Adaptive smoothing: Enter number of iterations and noise variance" << std::endl;
    std::cin >> iter >> sigma;

    std::cout << "Adaptive Smoothing the image: " << inputImageFileName << std::endl;
    for( int i = 1; i < iter; ++i )
      {
          processedImage = smoothAdaptive(processedImage, sigma);
      }
    }

  else   // Blemish removal
    {
        const int seedRow = 168;
        const int seedCol = 343;

        // Number of iterations
        const int iter = 126;

        // Threshold
        int threshold;
        std::cout << "Enter threshold " << std::endl;
        std::cin >> threshold;

        //creates images that get updated from the original image
        Image maskedimage(processedImage.getNumRows(), processedImage.getNumCols() );
        Image smoothedimage(processedImage.getNumRows(), processedImage.getNumCols() );
        Image originalimage(processedImage.getNumRows(), processedImage.getNumCols() );
        Image firstimage(processedImage.getNumRows(), processedImage.getNumCols() );
        Image secondimage(processedImage.getNumRows(), processedImage.getNumCols() );

        originalimage = processedImage; //keeps track of the original image

        maskedimage.maskImage(processedImage, maskedimage); //creates image called maskedimage which is the same as the original image

        Image notmaskedimage(maskedimage);  //creates an image called not masked image that is the same as the masked image

        for (int r = 0; r < maskedimage.getNumRows(); ++r) //subtracts the pixel value of the seed pixel from the image
        {
            for (int c = 0; c < maskedimage.getNumCols(); ++c)
            {
                int oldPixelValue = processedImage.getPixel(r,c);
                int newPixelValue = oldPixelValue - processedImage.getPixel(seedRow, seedCol);
                maskedimage.setPixel(r,c,newPixelValue);
            }
        }

        maskedimage.threshold(threshold); //thresholds the subtracted image

        markConnectedComponent(maskedimage, seedRow, seedCol); //calls the function that identifys the pixels connected to the seed pixel

        notmaskedimage = maskedimage; //sets the not masked image to be the same as the masked image

        notmaskedimage.logicalNot(); //inverts the masked image

        notmaskedimage.setAllPixelsWithOldValToNewVal(1,255); //sets the pixels that are not black to be white

        for(int i = 0; i != iter; ++i)
        {
            processedImage = smooth(processedImage);//calls the smooth image function to smooth the processed image
            processedImage *= maskedimage; //masks that processed image
            originalimage *= notmaskedimage; //masks the original image with the inverted mask
            processedImage += originalimage; //combines the two masked images
        }
    }

    std::cout << "Writing final image: " << outputImageFileName << std::endl;

    processedImage.writeToBMPFile(outputImageFileName);

  return 0;
}

    /***************************************************************************************
     * @brief Function to adaptive smooth the image
     *
     * @param image:  InputImage
     * @param sigma:  standard deviation of the noise (default value is 20)
     * @param factor: Strength of smoothing (default value is 0.2)
     *
     * @return image: smoothed image
     ***************************************************************************************/
Image smoothAdaptive(const Image & image, const double sigma, const double alpha)
{
  Image smoothedImage(image.getNumRows(), image.getNumCols() );

  for( int r = 0; r < image.getNumRows(); ++r )
    {
    for( int c = 0; c < image.getNumCols(); ++c )
      {
      const double rightDifference = (image.getPixel(r, c + 1) - image.getPixel(r, c) );
      const double leftDifference  = (image.getPixel(r, c - 1) - image.getPixel(r, c) );
      const double topDifference   = (image.getPixel(r + 1, c) - image.getPixel(r, c) );
      const double downDifference  = (image.getPixel(r - 1, c + 1) - image.getPixel(r, c) );

      const double alphaRight = alpha * exp(-rightDifference * rightDifference / sigma / sigma);
      const double alphaLeft  = alpha * exp(-leftDifference * leftDifference / sigma / sigma);
      const double alphaTop   = alpha * exp(-topDifference * topDifference / sigma / sigma);
      const double alphaDown  = alpha * exp(-downDifference * downDifference / sigma / sigma);

      double pixel  = (1.0 - alphaRight - alphaLeft - alphaTop - alphaDown) * image.getPixel(r, c);
      pixel += alphaRight * image.getPixel(r, c + 1);
      pixel += alphaLeft * image.getPixel(r, c - 1);
      pixel += alphaTop * image.getPixel(r + 1, c);
      pixel += alphaDown * image.getPixel(r - 1, c);

      smoothedImage.setPixel(r, c, pixel);
      }
    }

  return smoothedImage;
}

     /***************************************************************************************
     * @brief Function to linearly smooth the image by setting the current pixel to be the average
              of its surrounding pixels
     *
     * @param image:  InputImage
     * @param alpha: Strength of smoothing (default value is 0.2)
     *
     * @return image: smoothed image
     ***************************************************************************************/
Image smooth(const Image & myImage, const double alpha)
{
    Image processedImage(myImage.getNumRows(), myImage.getNumCols() ); //creates an image called processedImage

    for (int r = 0; r < myImage.getNumRows(); r++)
    {
        for (int c = 0; c < myImage.getNumCols(); c++)
        {
            double pixelcenter = myImage.getPixel(r,c); //gets the value of the center pixel
            double pixelleft = myImage.getPixel(r,c-1); //and its neighboring pixels
            double pixelright = myImage.getPixel(r,c+1);
            double pixeltop = myImage.getPixel(r-1,c);
            double pixelbottom = myImage.getPixel(r+1,c);
            double x = 4 * alpha;  //creates variables to calculate the smoothed pixel center
            x = 1 - x;             //easier
            x = x * pixelcenter;
            double y = pixelright + pixelleft + pixeltop + pixelbottom;
            y = y * alpha;
            double newPixelValue = x + y;
            processedImage.setPixel(r,c,newPixelValue); //sets the calculated smoothed center pixel
        }                                              // to the pixel on the new image
    }

    return processedImage;
}

 /***************************************************************************************
     * @brief Function to identify the pixels that are connected to the seed pixel
     *
     * @param image:  InputImage
     * @param seedRow: row that the blemish is on
     * @param seedCol: column that the blemish is on
     * @param ccLabel: value that the components are set to (default value is 255)
     *
     * @return image: image with only the part to be smoothed showing
     ***************************************************************************************/
static void markConnectedComponent(Image & image, const int seedRow, const int seedCol, const int ccLabel)
{
    std::stack< PixelLocation > pixelstack; //creates a useable stack
    PixelLocation pixel, pixelleft, pixelright, pixeltop, pixelbottom; //creates multiple pixel locations

    pixel.SetRow(seedRow); //sets the current pixel to be on the seed pixel
    pixel.SetCol(seedCol);

    pixelstack.push(pixel); //pushs the seed pixel to the stack

    while(! pixelstack.empty() )
    {
        PixelLocation top = pixelstack.top(); //sets a top pixel to be the pixel on the top of the stack

        int x = top.GetCol(); //sets x and y to be the row and collumn that the top pixel is on
        int y = top.GetRow();

        double val = image.getPixel(y, x); //gets the value the the pixel is

        pixelstack.pop(); //pops the pixel from stack

        if (val == 254) //if value is connected to seed component...
        {
            image.setPixel(y, x, ccLabel); //set that pixel to a new image, with a value of 255
            pixelleft.SetCol(x-1); //gets the rows and collumns of the surrounding pixels
            pixelleft.SetRow(y);  //and pushes them to the stack
            pixelright.SetCol(x+1);
            pixelright.SetRow(y);
            pixeltop.SetCol(x);
            pixeltop.SetRow(y-1);
            pixelbottom.SetCol(x);
            pixelbottom.SetRow(y+1);
            pixelstack.push(pixelleft);
            pixelstack.push(pixelright);
            pixelstack.push(pixeltop);
            pixelstack.push(pixelbottom);
        }
    }
    //sets the pixels with values that are the same color as the seed pixel, but are not connected to it, to 0
    image.setAllPixelsWithOldValToNewVal(254,0);
}
