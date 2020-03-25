all_emails = ''
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
