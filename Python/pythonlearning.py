a = 3
print (a)
print(type(a))
print ('hi')
test = False
print(test)
print(type(test))


print(1 < 2)
print(3 < 2)
print(1 < 2 < 3)
print(1 < 2 > 3)
print(1 < 2 and 2 > 3)
print(1 < 2 and 2 < 3)
print(1 == 1 or 2 == 2)
print(1 == 1 or 2 == 200)
print(1 == 10 or 2 == 200)
print(not 1 == 1)


hungry = False
if hungry:
    print("Its true")
else:
    print("Im not hungry")

loc = 'bank'
if loc == "auto shop":
    print("rari")
elif loc == "bank":
    print("$")
else:
    print('lel')

factorial = [1,2,3,4,5]
final = 1
for num in factorial:
    final = final * num
    print(final)
print(final)

myString = 'hello world'
for letter in myString:
    print(letter)
for letter in 'hello world':
    print(letter)

tup = (1,2,3)
for item in tup:
    print(item)

mylist = [(1,2),(3,4),(5,6),(7,8)]
for item in mylist:
    print(item)

mylist2 = [(1,2),(3,4),(5,6),(7,8)]
for (a,b) in mylist2:
    print(a)
    print(b)

mylist3 = [(1,2),(3,4),(5,6),(7,8)]
for a,b in mylist3:
    print(b)

mylist4 = [(1,2,3),(4,5,6),(7,8,9)]
for a,b,c in mylist4:
    print(b)
