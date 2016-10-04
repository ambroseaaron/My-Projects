/*------------------------------------------------------------------------------
 * Programmer: Aaron Ambrose
 * Date: 2/11/2015
 * Name: hw1.c
 * Description: Game that converts between temperatures and gives off a score
 -------------------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAXTEMP 100
#define MINTEMP -100

char getCharacterFrmKeyBoard();
double celciustofahrenheit(double temperature);
double fahrenheittocelcius(double temperature);
int mistake(double temperature);

int main( void )
{
    int choice,a,b,c,s,t,p,number,degrees;
    double temperature = 200;
    char letter;
    int scale = 3;
    double percent = 0;
    double score = 0;
    double total = 0;
    double answer = 0;

    srand(time(NULL));

    printf("Welcome to the temperature convertion training system.  Press any key to start, press x to exit.\n");

    letter = getCharacterFrmKeyBoard();

    while(letter != 'x') //will run the whole program until the user hits x
    {
        scale = rand()%2; //randomizes between 0 and 1, which represent celcius and fahrenheit
        temperature = rand()%(MAXTEMP*2+1) - 100;  //finds a random number in a set of 200 numbers and then subtracts 100 from that to get it into the negatives

        if(scale == 1)  //if scale is 1, run the celcius to fahrinheit convertion question
        {
            answer = celciustofahrenheit(temperature); //sends value to function to get correct answer
            number = answer;
            degrees = temperature;
            choice = rand()%(3); //randomly chooses if 0,1,or 2 will be the correct answer, which represent a,b, and c
            if(choice==0) //sets answers based on random choice selection
            {
                a = number;
                b = fahrenheittocelcius(temperature); //picked the other two answers to be calculated the wrong ways
                c = mistake(temperature);

                printf("Convert %d degrees celcius to fahrenheit.\n a) %d b) %d c) %d\n", degrees,a,b,c);
                letter = getCharacterFrmKeyBoard();

                if(letter=='a')//if answered correcty, add score, otherwise just add total
                {
                    printf("Correct!\n");
                    score = score + 1;
                    total = total + 1;
                }
                else
                {
                    printf("Sorry that is incorrect. The correct answer was a) %d\n", number);
                    total = total + 1;
                }
            }


            else if(choice==1) //does the same thing as above, only with b being the correct answer
            {
                a = fahrenheittocelcius(temperature);
                b = number;
                c = mistake(temperature);

                printf("Convert %d degrees celcius to fahrenheit.\n a) %d b) %d c) %d\n", degrees,a,b,c);
                letter = getCharacterFrmKeyBoard();

                if(letter=='b')
                {
                    printf("Correct!\n");
                    score = score + 1;
                    total = total + 1;
                }
                else
                {
                    printf("Sorry that is incorrect. The correct answer is b) %d\n", number);
                    total = total + 1;
                }
            }

            else //does the same thing as the two above, only with c as the correct answer
            {
                a = fahrenheittocelcius(temperature);
                b = mistake(temperature);
                c = number;

                printf("Convert %d degrees celcius to fahrenheit.\n a) %d b) %d c) %d\n", degrees,a,b,c);
                letter = getCharacterFrmKeyBoard();

                if(letter=='c')
                {
                    printf("Correct!\n");
                    score = score + 1;
                    total = total + 1;
                }
                else
                {
                    printf("Sorry that is incorrect. The correct answer is c) %d\n", number);
                    total = total + 1;
                }
            }
            printf("Press x to exit, any other key to continue\n");
            letter = getCharacterFrmKeyBoard();
        }

        else
        {
            answer = fahrenheittocelcius(temperature); //finds answer of temp using the function to get it to celcius
            number = answer;
            degrees = temperature;
            choice = rand()%(3); //randomly chooses the answer slot

            if(choice==0) //if answer is a, set values and ask question
            {
                a = number;
                b = celciustofahrenheit(temperature);
                c = mistake(temperature);

                printf("Convert %d degrees fahrenheit to celcius.\n a) %d b) %d c) %d\n", degrees,a,b,c);
                letter = getCharacterFrmKeyBoard();

                if(letter=='a') //checks if the correct answer was typed or not
                {
                    printf("Correct!\n");
                    score = score + 1;
                    total = total + 1;
                }
                else
                {
                    printf("Sorry that is incorrect. The correct answer is a) %d\n", number);
                    total = total + 1;
                }
            }
            else if(choice==1) //does same thing as above only with b as the correct answer
            {
                a = celciustofahrenheit(temperature);
                b = number;
                c = mistake(temperature);

                printf("Convert %d degrees fahrenheit to celcius.\n a) %d b) %d c) %d\n", degrees,a,b,c);
                letter = getCharacterFrmKeyBoard();

                if(letter=='b')
                {
                    printf("Correct!\n");
                    score = score + 1;
                    total = total + 1;
                }
                else
                {
                    printf("Sorry that is incorrect. The correct answer is b) %d\n", number);
                    total = total + 1;
                }
            }
            else //does the same thing as above only with c as the answer
            {
                a = celciustofahrenheit(temperature);
                b = mistake(temperature);
                c = number;

                 printf("Convert %d degrees fahrenheit to celcius.\n a) %d b) %d c) %d\n", degrees,a,b,c);
                letter = getCharacterFrmKeyBoard();

                if(letter=='c')
                {
                    printf("Correct!\n");
                    score = score + 1;
                    total = total + 1;
                }
                else
                {
                    printf("Sorry that is incorrect. The correct answer is c) %d\n", number);
                    total = total + 1;
                }
            }

            printf("Press x to exit, any other key to continue\n");
            letter = getCharacterFrmKeyBoard();
        }
    }

    percent = ((score/total)*100)+0.5;
    p = percent;
    s = score;
    t = total;

    printf("You got %d out of %d correct, or %d percent\n",s,t,p);

    return 0;

}
/*---------------------------------------------------------------------------------
 * Function name: getCharacterFrmKeyBoard
 * Inputs a character from the keyboard
 * Removes unwanted newline characters in the input stream
 * Read the help file on ICON HW1 description for details
 *--------------------------------------------------------------------------------*/
char getCharacterFrmKeyBoard()
{
    char discard,ch;

    do{
        ch=getchar();
    } while (ch=='\n');

    do{
        discard=getchar();
    } while (discard!='\n');

    return(ch);
}

/*---------------------------------------------------------------------------------
 *Function name: Celcius to Fahrenheit
 *Converts the random temp to fahrenheit
 *Input: temperature
 *Output: celcius
 *---------------------------------------------------------------------------------*/
 double celciustofahrenheit(double temperature)
 {
    temperature = (1.8 * temperature) + 32;
    if(temperature>=0)
    {
        temperature = temperature + .5;
    }
    else
    {
        temperature = temperature - .5;
    }
    return temperature;
 }

/*---------------------------------------------------------------------------------
 * Function name: Fahrenheit to Celcius
 *Converts the random temp to celcius
 *Input: temperature
 *Output: fahrenheit
 *---------------------------------------------------------------------------------*/
 double fahrenheittocelcius(double temperature)
 {
    temperature = (temperature - 32) / 1.8;
    if(temperature>=0)
    {
        temperature = temperature + .5;
    }
    else
    {
        temperature = temperature - .5;
    }
    return temperature;
 }

/*---------------------------------------------------------------------------------
 * Function name: Mistake Temperature
 *Incorrectly computes the temp, as one of the bad answers
 *Input: temperature
 *Output: miscomputed temperature
 *---------------------------------------------------------------------------------*/
 int mistake(double temperature)
 {
    temperature = (temperature + 32) * 1.8;
    if(temperature>=0)
    {
        temperature = temperature + .5;
    }
    else
    {
        temperature = temperature - .5;
    }
    return temperature;
 }
