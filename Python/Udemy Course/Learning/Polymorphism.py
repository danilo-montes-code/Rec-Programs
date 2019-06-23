class Dog():

    def __init__(self, name):
        self.name = name

    def speak(self):
        return self.name + ' says WOOF!'

class Cat():

    def __init__(self, name):
        self.name = name

    def speak(self):
        return self.name + ' says MEOW!'

# Abstract class, never expected to be instanced
class Animal():

    def __init__(self,name):
        self.name = name

    def speak(self):
        raise NotImplementedError("Subclass must implement this abstract method")

class Fish(Animal):

    def speak(self):
        return self.name + " says fish things!"


def main():
    niko = Dog('niko')
    felix = Cat('felix')
    print(niko.speak())
    print(felix.speak())
    # niko says WOOF!
    # felix says MEOW!
    print('')

    for pet_class in [niko, felix]:
        print(type(pet_class))
        print(pet_class.speak())
        # <class '__main__.Dog'>
        # niko says WOOF!
        # <class '__main__.Cat'>
        # felix says MEOW!
    print('')

    def pet_speak(pet):
        print(pet.speak())
    pet_speak(niko)
    pet_speak(felix)
    # niko says WOOF!
    # felix says MEOW!
    print('')

    # myanimal = Animal('fred')
    # myanimal.speak()

    # Traceback (most recent call last):
    # File "C:\Users\Rubikscrafter\Documents\Coding\Python\Programs\My Programs\Polymorphism.py", line 58, in <module>
    # main()
    # File "C:\Users\Rubikscrafter\Documents\Coding\Python\Programs\My Programs\Polymorphism.py", line 54, in main
    # myanimal.speak()
    # File "C:\Users\Rubikscrafter\Documents\Coding\Python\Programs\My Programs\Polymorphism.py", line 24, in speak
    # raise NotImplementedError("Subclass must implement this abstract method")
    # NotImplementedError: Subclass must implement this abstract method

    gold = Fish('Goldy')
    print(gold.speak())
    # Goldy says fish things!




if __name__ == '__main__':
    main()
