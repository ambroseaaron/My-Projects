#Aaron Ambrose

import random

#test for the 3D box class that creates multiple boxes
#and tests its member functions
def testBox():
        box1 = Box(10.0, 5.0, 0.0, 2.0, 1.0, 1.0)
        print("Creating Box 1")
        print(box1)
        
        box2 = Box(0, 0, 0, 3.5, 2.5, 1.0)
        print("Creating Box 2")
        print(box2)
        
        print("Volume of box 1")
        print(box1.volume())
        print("Volume of box 2")
        print(box2.volume())
        
        print("Surface area of box 1")
        print(box1.surfaceArea())
        print("Surface area of box 2")
        print(box2.surfaceArea())
   
        print("Does box 1 overlap box 2?")
        print(box1.overlaps(box2))

        print("Resetting the center of box 1")
        box1.setCenter(2.75, 0.0, 0.0)
        print(box1)
              
        print("Does box 1 overlap box 2?")
        print(box1.overlaps(box2))
   
        print("Resetting the center of box 1")
        box1.setCenter(2.76, 0.0, 0.0)
        print(box1)
              
        print("Does box 1 overlap box 2?")
        print(box1.overlaps(box2))

        print("Resetting width and depth of box 1")
        box1.setWidth(50.0)
        box1.setDepth(50.0)
        print(box1)
        print("Does box 2 overlap box 1?")
        print(box2.overlaps(box1))

        print("Creating Box 3")
        box3 = Box(0, 0, 0)
        print(box3)
              
        print("Does box 2 contain box 3")
        print(box2.contains(box3))

        box4 = Box(10.0, 5.0, 0.0, 2.0, 1.0, 1.0)
        print("Creating Box 4")
        print(box4)

        print("Does box 4 contain box 3")
        print(box4.contains(box3))

        print("Resetting height of box 3")
        box3.setHeight(15)
        print("Box 3 is now")
        print(box3)

        return

#3D Box Class
class Box:
    def __init__ (self, centerX = 0.0, centerY = 0.0, centerZ = 0.0, width = 1.0, height = 1.0, depth = 1.0):
        self.centerX = centerX
        self.centerY = centerY
        self.centerZ = centerZ
        self.width = width
        self.height = height
        self.depth = depth

        #Method to reset the box to a new coordinate location   
    def setCenter(self, x, y, z):
        self.centerX = x
        self.centerY = y
        self.centerZ = z

        #Method to reset the width of the box
    def setWidth(self, width):
        self.width = width

        #Method to reset the height of the box
    def setHeight(self, height):
        self.height = height

        #Method to reset the depth of the box
    def setDepth(self, depth):
        self.depth = depth

        #Method to calculate the volume of the box
    def volume(self):
        return (self.width * self.height * self.depth)

        #Method to calculate the surface area of the box
    def surfaceArea(self):
        return (2 * ((self.width * self.depth) + (self.height * self.depth) + (self.height * self.width)))

        #Method to determine if two boxes overlap
    def overlaps(self, otherBox):

        #The following save the coordinates of the sides of each box
        rightSelf = self.centerX + (self.width/2)
        leftSelf = self.centerX - (self.width/2)
        rightOther = otherBox.centerX + (otherBox.width/2)
        leftOther = otherBox.centerX - (otherBox.width/2)
        topSelf = self.centerY + (self.height/2)
        bottomSelf = self.centerY - (self.height/2)
        topOther = otherBox.centerY + (otherBox.height/2)
        bottomOther = otherBox.centerY - (otherBox.height/2)
        frontSelf = self.centerZ - (self.depth/2)
        backSelf = self.centerZ + (self.depth/2)
        frontOther = otherBox.centerZ - (otherBox.depth/2)
        backOther = otherBox.centerZ + (otherBox.depth/2)

        #returns if any of the sides of box 2 overlap the sides of box 1
        return ((rightSelf >= leftOther and rightOther >= leftSelf) and
                (topSelf >= bottomOther and topOther >= bottomSelf) and
                (backSelf >= frontOther and backOther >= frontSelf))

        #Method to determine if one box is inside of the other
    def contains(self, otherBox):

        #The following save the coordinates of the sides of each box
        rightSelf = self.centerX + (self.width/2)
        leftSelf = self.centerX - (self.width/2)
        rightOther = otherBox.centerX + (otherBox.width/2)
        leftOther = otherBox.centerX - (otherBox.width/2)
        topSelf = self.centerY + (self.height/2)
        bottomSelf = self.centerY - (self.height/2)
        topOther = otherBox.centerY + (otherBox.height/2)
        bottomOther = otherBox.centerY - (otherBox.height/2)
        frontSelf = self.centerZ - (self.depth/2)
        backSelf = self.centerZ + (self.depth/2)
        frontOther = otherBox.centerZ - (otherBox.depth/2)
        backOther = otherBox.centerZ + (otherBox.depth/2)
        
        #Checks if the width of the box is within the width of the other box
        if(rightOther > rightSelf or leftOther < leftSelf):
            return False

        #Checks if the height of the box is within the height of the other box
        if(topOther > topSelf or bottomOther < bottomSelf):
            return False

        #Checks if the depth of the box is within the depth of the other box
        if(frontOther < frontSelf or backOther > backSelf):
            return False
        #Returns true if none of the sides of the box are outside of the other box
        return True
            
    def __repr__(self):
        return '< {}-by-{}-by-{} 3D box with center at ({}, {}, {}) >'.format(self.width, self.height, self.depth, self.centerX, self.centerY, self.centerZ)

#Class to play the 'Subtraction Game'
class NimGame:

        #Initializes the game to any number of starting balls
    def __init__(self, num):
        self.numRemaining = num
        print("Game started with {} balls".format(self.numRemaining))

        #remove function to play the game
    def remove(self, number):

            #Makes sure the number is valid
        if(number < 1) or (number > 3) or (number > self.numRemaining):
            print("Invalid number. Try again.")
        else:
                #removes the specified number of balls from the total
            self.numRemaining = self.numRemaining - number
            print("You took {} balls".format(number))
            print("{} balls remain".format(self.numRemaining))

            #if you took the last ball, you win!
            if self.numRemaining == 0:
                print("You Win!")

            #if there are still balls left, its the computers turn
            else:
                    #if there is more than 3 balls remaining, take a random number of balls
                if self.numRemaining > 3:
                    computerNumber = random.randint(1,3)

                    #if there is less than 3 balls remaining, take the remaining balls
                else:
                    computerNumber = self.numRemaining

                #subtracts the number of balls taken from the total
                self.numRemaining = self.numRemaining - computerNumber
                print("Computer took {} balls".format(computerNumber))
                print("{} balls remain".format(self.numRemaining))

                #if the computer took the last ball, the computer wins!
                if self.numRemaining == 0:
                    print("Computer Wins!")
                    
        



   
