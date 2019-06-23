def add(n1,n2):
    print(n1+n2)

add(10,20)
  # 30
# add(10,"20")
  # TypeError: unsupported operand type(s) for +: 'int' and 'str'

try:
    # Want to attempt this code
    # May have an error
    result = 10 + '10'
except:
    print("You aren't adding correctly")
else:
    print("Add went well!")
    print(result)

try:
    # Want to attempt this code
    # May have an error
    result = 10 + 10
except:
    print("You aren't adding correctly")
else:
    print("Add went well!")
    print(result)

try:
    f = open('testfile','r')
    f.write("Write a test line")
except TypeError:
    print('TypeError')
except OSError:
    print('OSError')
except:
    print('All other exceptions')
finally:
    print('I always run')
  # OSError
  # I always run
