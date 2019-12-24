##############################/METHOD 1/################################
print("METHOD ONE")
import requests
from urllib.request import urlopen
from lxml import html

# gets the lab day
url = 'https://parents.ebnet.org/genesis/parents?gohome=true'

session_requests = requests.session()
result = session_requests.get(
	url,
	headers = dict(referer = url)
)

tree = html.fromstring(result.content)
names = tree.xpath('/html/body/form[2]/table[1]/tbody/tr[2]/td/table/tbody/tr/td[2]/div[2]/span')
print(names)
print()
#############################/METHOD 2/################################
print("METHOD TWO")
page = requests.get(url)
page_html = html.fromstring(page.content)
print(page_html)

lab_div = page_html.xpath('/html/body/form[2]/table[1]/tbody/tr[2]/td/table/tbody/tr/td[2]/div[2]/span/text()')
print(lab_div)
print()
#############################/METHOD 3/################################
print("METHOD THREE")
#from lxml import etree

#htmlparser = etree.HTMLParser()
#tree = etree.parse(urlopen(str(driver.page_source)), htmlparser)
test2 = tree.xpath('/html/body/form[2]/table[1]/tbody/tr[2]/td/table/tbody/tr/td[2]/div[2]/span')
print(test2)

test4 = tree.xpath('//div[15]/text()')
print(test4)
print()
######################/METHOD 4 [KINDA WORKS]/#########################
print("METHOD FOUR")
from bs4 import BeautifulSoup
import re
from selenium import webdriver

driver = webdriver.Chrome()
driver.get('https://parents.ebnet.org/genesis/parents?gohome=true')

# type text
username = driver.find_element_by_xpath('//*[@id="j_username"]')
username.send_keys('chabicasm@gmail.com')
password = driver.find_element_by_xpath('//*[@id="j_password"]')
password.send_keys('kidsinfo')

# click login button
login = driver.find_elements_by_xpath("/html/body/form/div/div[2]/input[1]")[0]
login.click()

# click summary
summary = driver.find_element_by_xpath('/html/body/div[2]/span[1]')
summary.click()

soup = BeautifulSoup(driver.page_source, 'lxml')
soup.prettify(formatter=lambda s: s.replace(u'\xa0', ' '))

# gets the "block" div and gets the number lab day that it is
block = soup.find(string=re.compile("Block"))
try:
	lab_day = block.next_element.next_element.next_element.next_element.get_text().strip()
except AttributeError:
	print('Today is a Day 0')
else:
	print('Today is a Day '+lab_day)

# closes window
driver.close()

print()
#############################/METHOD 5/################################
'''
print("METHOD FIVE")
s = requests.Session()
te = s.get('https://parents.ebnet.org/genesis/j_security_check')
print(te.text)

print()
'''
#######################################################################
# https://parents.ebnet.org/genesis/j_security_check
# https://parents.ebnet.org/genesis/parents?gohome=true
