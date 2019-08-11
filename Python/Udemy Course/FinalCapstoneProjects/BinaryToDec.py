def convertToDec(number):
    pass


def convertToBin(number):
    pass


def main():
    print('Base Converter\n')

    while True:
        try:
            change = \
                int(input('What base would you like to convert from and to? (1 - Binary to Decimal or 2 - vise versa)\n'))
        except ValueError:
            print('Error: Please enter an integer')
        else:
            break

    while change is not 1 or 2:
        print('Error: Please enter either 1 or 2')
        change = input('What base would you like to convert from and to? (1 - Binary to Decimal or 2 - vise versa)\n')

    num = input('What number are you converting?\n')

    if change == 1:
        convertToDec(num)
    else:
        convertToBin(num)


if __name__ == '__main__':
    main()
