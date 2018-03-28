# Aaron Ambrose
# Homework 2
def printRanges(high, low):
    counter = high
    while(counter >= low): #loops from the high to the low number
        print(counter) #prints the number we are counting down from
        num = 0
        while(num < counter): #counts down from the counter to 0
            print(str(num) + " ", end="") #prints the numbers all on one line
            num += 1
        print("")
        counter -= 1

def printVowelStats(inputString):
    inputString = inputString.lower()
    nonVowels = a = e = i = o = u = 0
    for letter in inputString: #checks each letter to see if it is a vowel
        if(letter == 'a'):
            a += 1
        elif(letter == 'e'):
            e += 1
        elif(letter == 'i'):
            i += 1
        elif(letter == 'o'):
            o += 1
        elif(letter == 'u'):
            u += 1
        else:
            nonVowels += 1
    print(inputString + " has " + str(a) + " a's, " + str(e) + " e's, " + str(i) + " i's, " + str(o) + " o's, " + str(u) + " u's, and " + str(nonVowels) + " non-vowels.")
    
          
def leastChar(inputString):
    lowerString = inputString.lower()
    minValue = lowerString[0]
    minPos = 0
    counter = 0
    for value in lowerString: #for each letter's value in the string
        if value < minValue: #if the value is lower than the original lower value
            minPos = counter #save the value and position of the new lowest letter
            minValue = value
        counter += 1
    minValue = inputString[minPos]
    print("The least char is " + minValue + " and occurs at position "+ str(minPos))

def generateSequence(startNumber, factor, offset, stopNumber):
    currNumber = startNumber
    counter = 1
    while(currNumber != stopNumber): #run until the current number equals the stopping number
        counter += 1
        print(str(currNumber)) #keeps track of the length and prints the new number
        if(currNumber % 2 == 0): #checks if the number is even
            currNumber = currNumber / 2
        else:
            currNumber = ((currNumber * factor) + offset)
    print(str(currNumber))
    print("Length of sequence: " + str(counter))
