import math

class Dog():
    species = 'Mammal'
    def __init__(self, breed, name, spots):
        self.breed = breed
        self.name = name
        self.spots = spots
    def bark(self):
        print(f'WOOF! My name is {self.name}')
    def yeet(self, number):
        print(f'Ya yeet! I am a {self.breed} and the number that was entered is {number}')

class Circle():

    def __init__(self, radius=1):
        self.radius = radius
        self.area = radius**2 * math.pi

    def get_circum(self):
        return self.radius*math.pi*2

    def get_area(self):
        return self.area

def main():
    my_dog = Dog(breed = 'Lab', name = 'Gold', spots = True)
    print(my_dog.breed)
    print(my_dog.name)
    print(my_dog.spots)
    print(my_dog.species)
    my_dog.bark()
    my_dog.yeet(10)

    cir = Circle(radius = 30)
    print(cir.get_circum())
    print(cir.get_area())

if __name__ == "__main__":
    main()

'''
Lab
Gold
True
Mammal
WOOF! My name is Gold
Ya yeet! I am a Lab and the number that was entered is 10
188.49555921538757
2827.4333882308138
'''
