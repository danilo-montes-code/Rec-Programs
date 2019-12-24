def check_int(message):
    while True:
        try:
            inp = int(input(message + '\n'))
        except ValueError:
            print('Error: Please enter an integer\n')
        else:
            return inp


def convert_to_dec(number):
    length = len(str(number))
    dec = 0
    for i in range(0, length):
        power = length - 1 - i
        if str(number)[i] is '1':
            dec += pow(2, power)
    print('\n')
    print('Binary: ' + str(number))
    print('Decimal: ' + str(dec))


def convert_to_bin(number):
    og_num = number
    binary = ''

    while number > 0:
        binary = str(number % 2) + binary
        number = int(number / 2)

    print('Decimal: ' + str(og_num))
    print('Binary: ' + binary)


def main():
    print('Base Converter\n')

    change = check_int('What base would you like to convert from and to? (1 - Binary to Decimal or 2 - vise versa)')

    while change != 1 and change != 2:
        print('Error: Please enter either 1 or 2\n')
        change = check_int('What base would you like to convert from and to? (1 - Binary to Decimal or 2 - vise versa)')

    print('\n')
    num = check_int('What number are you converting?')

    if change == 1:
        convert_to_dec(num)
    else:
        convert_to_bin(num)


if __name__ == '__main__':
    main()
