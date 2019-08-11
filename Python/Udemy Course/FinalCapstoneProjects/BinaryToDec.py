def int_checker(inp):
    while int(inp) is NaN:
        print('Error: Please enter a numerical answer\n')
        inp = input()
    return inp


def convertToDec(number):
    pass

def convertToBin(number):
    pass

def main():
    print('Base Converter\n')

    change = input('What base would you like to convert from and to? (1 - Binary to Decimal or 2 - vise versa)\n')

    while int_checker(change) is not 1 or 2:
        print('Error: Please enter either 1 or 2')
        change = input('What base would you like to convert from and to? (1 - Binary to Decimal or 2 - vise versa)\n')

    num = int_checker(input('What number are you converting?\n'))

    if change == 1:
        convertToDec(num)
    else:
        convertToBin(num)


if __name__ == '__main__':
    main()
