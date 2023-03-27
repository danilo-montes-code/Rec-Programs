all = ''
all = all.split()
combine = []
temp = ''
for number, i in enumerate(all):
    temp += i
    if number % 2 == 0:
        temp += ' '
    elif number % 2 == 1:
        combine.append(temp)
        temp = ''

for i in combine:
    print(i)
