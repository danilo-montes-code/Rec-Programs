all = 'Alex	Lee Belinda	Zhang Jeffrey	Han Joshua	Aalampour Joy	Chen Leah	Chin Mary	Fouad Pranav	Nair Raveesh	Mehta Roey	Beniluz Ryan	Lin Samiyah	Siddiqui Sanchita	Ray Shreya	Sinkar Aaron	Zhu Aniket	Das Anthony	Leapo Ayman	Mohammad Benjamin	Hong Bhaskar	Jain Brandon	Gao Bryan	Lee Caelie	Moi Chinmay	Sahasrabudhe Christine	Chang Clara	Lee Edwin	Chao Elizabeth	Mundkowsky Emily	Chu Erik	Wei Erin	Ma Fiona	Liu Flora	Cai Harrison	Wong Horace	Yu Jessica	Chou Jhanvi	Pai Joshua	Yi Kaitlyn	Liu Kavin	Mohan Klara	Tosic Lara	Petrunis Michael	Mogilevsky Michael	Sun Minta	Caune Neha	Ravuri Rachel	Ai Rothela	Samadi Sandra	Lee Sarah	Levin Sophia 	Jorgensen Srinu	Appana Steven	Han Tarun	Sivakumar Tracy	Wan Varun	Mehrotra Yoshna	Rajendran Yusuf	Zahran'
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
