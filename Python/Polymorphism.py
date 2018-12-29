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

def main():
    niko = Dog('niko')
    felix = Cat('felix')
    print(niko.speak())
    print(felix.speak())
    print('')

    for pet_class in [niko, felix]:
        print(type(pet_class))
        print(pet_class.speak())
    print('')

    def pet_speak(pet):
        print(pet.speak())
    pet_speak(niko)
    pet_speak(felix)

if __name__ == '__main__':
    main()
