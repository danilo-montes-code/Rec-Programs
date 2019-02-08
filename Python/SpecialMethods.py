class Book():

    def __init__(self, title, author, pages):
        self.title = title
        self.author = author
        self.pages = pages

    def __str__(self):
        return f'{self.title} by {self.author}'

    def __len__(self):
        return self.pages

    def __del__(self):
        print("A book object has been deleted")

def main():
    d = Book('Python Rocks', 'Jose', 200)
    print(d)
    # Python Rocks by Jose
    print(len(d))
    # 200

    # del b  # Deletes b from memory in computer
    # print(b)

    # Traceback (most recent call last):
    # File "C:\Users\Rubikscrafter\Documents\Coding\Python\Programs\My Programs\SpecialMethods.py", line 25, in <module>
    # main()
    # File "C:\Users\Rubikscrafter\Documents\Coding\Python\Programs\My Programs\SpecialMethods.py", line 21, in main
    # print(b)
    # UnboundLocalError: local variable 'b' referenced before assignment

if __name__ == "__main__":
    main()
