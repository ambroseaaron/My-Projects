#Aaron Ambrose

#Animal test class that creates instances of each animal and
#calls their respective member functions
def testAnimals():
    #Instantiates a dog object and tests its methods
    dog = Dog("Charlie", "Terrier")
    print('Dogs name is: {} and is a {}'.format(dog.name, dog.dogType))
    print('{} says:'.format(dog.name))
    dog.speak()
    dog.fetch()

    print()

    #Instantiates a cat object and tests its methods
    cat = Cat("Wiskers", "Brown", "Siamese")
    print('Cats name is {} and is {}'.format(cat.name, cat.catType))
    print('{} says:'.format(cat.name))
    cat.speak()
    print("{} fur color is {}".format(cat.name, cat.getFurColor()))

    print()

    #Instantiates a bird object and tests its methods
    bird = Bird("Tweety Bird", "Cardinal")
    print("Birds name is {} and is a {}".format(bird.name, bird.birdType))
    print("{} says:".format(bird.name))
    bird.speak()
    bird.fly()
    bird.feed()
    
#General Superclass for all animals
class Animal ():
    
    numAnimals = 0

    def __init__ (self, name = 'NoName', numLegs = 0):
        self.name = name
        self.numLegs = numLegs
        self.id = Animal.numAnimals
        Animal.numAnimals = Animal.numAnimals + 1
        
    def getName(self):
        return self.name
    
    def getNumLegs(self):
        return self.numLegs
   
    def speak(self):
        print("...")

    def __repr__(self):
        return ('Animal(name={}, numlegs={})'.format(self.name, self.numLegs))
    
    def __str__(self):
        return ('<{} the animal(ID#{}))>'.format(self.name, self.id))

#Dog class with new method getDogType
class Dog(Animal):
    def __init__(self, name = 'rover', dogType = 'none'):
        Animal.__init__(self, name, 4)
        self.dogType = dogType
    
    def speak(self):
        print('woof')
        
    def fetch(self):
        print("I'm fetching")

    def getDogType(self):
        return self.dogType

    def __repr__(self):
        return "Dog(name='{}')".format(self.name)
    
    def __str__(self):
        return '<{} the dog(ID#{}))>'.format(self.name, self.id)

#Cat class with new method getCatType
class Cat(Animal):
    def __init__(self, name = 'noname', furColor = 'unknown', catType = 'none'):
        Animal.__init__(self, name, 4)
        self.color = furColor
        self.catType = catType
    
    def speak(self):
        print('meow')

    def getFurColor(self):
        return self.color

    def getCatType(self):
        return self.catType

    def __repr__(self):
        return ("Cat(name='{}', furColor='{}')".format(self.name, self.color))
    
    def __str__(self):
        return ('<{} the {} cat(ID#{}))>'.format(self.name, self.color, self.id))

#New created Bird class
class Bird(Animal):
    def __init__(self, name = 'none', birdType = 'none'):
        Animal.__init__(self, name, 2)
        self.birdType = birdType
    
    def speak(self):
        print('Tweet Tweet')
        
    def fly(self):
        print("I'm flying")

    def feed(self):
        print("Feed me!")

    def getBirdType(self):
        return self.birdType

    def __repr__(self):
        return "Bird(name='{}')".format(self.name)
    
    def __str__(self):
        return '<{} the Bird(ID#{}))>'.format(self.name, self.id)
