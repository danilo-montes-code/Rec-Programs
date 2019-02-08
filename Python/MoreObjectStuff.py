class Animal():

    def __init__(self):
        print("ANIMAL CREATED")

    def who_am_i(self):
        print("I am an animal")

    def eat(self):
        print("I am eating")


# This class inherits the methods from the Animal class so that they don't have to be rewritten
# You can also overwrite methods that are pre defining in the inherited class
class Dog(Animal):

    def __init__(self):
        Animal.__init__(self)
        print("DOG CREATED")

    def who_am_i(self):
        print("I am a dog")

def main():
    myanimal = Animal()
    myanimal.eat()
    myanimal.who_am_i()
    doggy = Dog()
    doggy.eat()
    doggy.who_am_i()

if __name__ == "__main__":
    main()

'''
ANIMAL CREATED
I am eating
I am an animal
ANIMAL CREATED
DOG CREATED
I am eating
I am a dog
'''
