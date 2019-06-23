def func():
    return 1
def hello():
    print("Hello")
  # Points to location that hello() points to
greet = hello
greet()
del hello
  # hello()  # NameError: name 'hello' is not defined
greet()
'''
Hello
Hello
'''
print()


def hi(name='Danilo'):
    print('The hi() function has been executed')

    def greeter():
        return '\t This is the greeter() function inside hi()'

    def welcome():
        return '\t This is welcome() inside hi()'

    print(greeter())
    print(welcome())
    print('This is the end of the hi() function')

    print('I am going to return a function')

    if name == 'Danilo':
        return greeter
    else:
        return welcome

hi()
new_func = hi('Danilo')
print(new_func())
'''
The hi() function has been executed
	 This is the greeter() function inside hi()
	 This is welcome() inside hi()
This is the end of the hi() function
I am going to return a function

The hi() function has been executed
	 This is the greeter() function inside hi()
	 This is welcome() inside hi()
This is the end of the hi() function
I am going to return a function
	 #  This is the greeter() function inside hi()
'''
  # print(welcome())  # NameError: name 'welcome' is not defined
print()


def cool():
    def super_cool():
        return 'I am very cool'

    return super_cool
something = cool()
print(something())  # I am very cool
print()

def ya():
    return 'Hi Danilo'

def other(some_def_func):
    print('Other code runs here!')
    print(some_def_func())
other(ya)
'''
Other code runs here!
Hi Danilo
'''
print()







def new_decorator(og_func):

    def wrap_func():

        print('Some extra code, before the original func')

        og_func()

        print('Some extra code, afer the og func')

    return wrap_func

def func_needs_dec():
    print('I want to be decorated')

decorated_func = new_decorator(func_needs_dec)
decorated_func()
'''
Some extra code, before the original func
I want to be decorated
Some extra code, afer the og func
'''
print()
@new_decorator
def needs_dec():
    print('I want to be decorated')

needs_dec()
'''
Some extra code, before the original func
I want to be decorated
Some extra code, afer the og func
'''
