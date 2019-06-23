def create_cubes(x):
    result = []
    for x in range(x):
        result.append(x**3)
    return result

for x in create_cubes(10):
    print(x)
'''
0
1
8
27
64
125
216
343
512
729
'''
print()
def create_cubes2(x):
    for x in range(x):
        yield x**3
for x in create_cubes2(10):
    print(x)
'''
0
1
8
27
64
125
216
343
512
729
'''
print()
def gen_fib(n):
    a = 1
    b = 1
    for x in range(n):
        yield a
        a,b = b, a+b

for num in gen_fib(10):
    print(num)
'''
1
1
2
3
5
8
13
21
34
55
'''
print()
def simple_gen():
    for x in range(3):
        yield x

for number in simple_gen():
    print(number)
'''
0
1
2
'''
print()
g = simple_gen()
print(next(g))
print(next(g))
print(next(g))
'''
0
1
2
'''
print()
s = 'hello'
for letter in s:
    print(letter)
  # next(s) - TypeError: 'str' object is not an iterator
print()
s_iter = iter(s)
print(next(s_iter))
print(next(s_iter))
print(next(s_iter))
print(next(s_iter))
print(next(s_iter))
'''
h
e
l
l
o
'''
print() 
