all_emails = 'tsivakumar@ebnet.org, belindazhang2014@gmail.com, sanchitaray9@gmail.com, jychen20202@gmail.com, fionaliu016@gmail.com, bhaskar.picky@gmail.com, tleapo@gmail.com, Lhannan@ebnet.org, nravuri@ebnet.org, rsamadi@ebnet.org, lepetrunis@ebnet.org, kaitlynliu@comcast.net, pai.jhanvipai@gmail.com, mcaune@ebnet.org, jessicachou65@gmail.com, jyi@ebnet.org, bhong8989@gmail.com, agramuglia@ebnet.org, kcosta@ebnet.org, mmmplayer16@gmail.com, csahasrabudhe@ebnet.org, ayfifa123@gmail.com, bbee27smile@gmail.com, edwinchao@comcast.net, varun.mehrotra50@gmail.com, sahiagb@gmail.com, jlqi@ebnet.org, hyu1@ebnet.org, dtereshchenko@ebnet.org, Elizabeth Mundkowsky <emundkowsky@ebnet.org>, parthpatel1@gmail.com, kmohan@ebnet.org, adas@ebnet.org, sjorgensen@ebnet.org, to.kinolee@gmail.com, fcai@ebnet.org, albert1c@hotmail.com, rai@ebnet.org, nho1323@gmail.com'
emails = []
temp = ''
for i in range(0, len(all_emails)):
    if all_emails[i] != ',':
        temp += all_emails[i]
    else:
        emails.append(temp)
        temp = ''
for email in emails:
    print(email)
