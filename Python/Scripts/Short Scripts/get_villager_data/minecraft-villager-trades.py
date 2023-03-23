import requests
from bs4 import BeautifulSoup


def main():
    dom = connect()

    prof_list = get_list(dom)

    display(prof_list)
    

def connect():
    URL = "https://minecraft.fandom.com/wiki/Trading"
    page = requests.get(URL)

    soup = BeautifulSoup(page.content, "html.parser")

    return soup


def get_list(dom):
    pass


'''
[
    {
        "profession" : <PROFESSION>,
        "trades" : [
            {
                "level" : <LEVEL>,
                "exchanges" : [
                    {
                        "wanted" : <ITEM>,
                        "given"  : <ITEM>
                    },
                    ...
                ]
            },
            ...
        ]  
    },
    ...
]
'''

def display(data):
    for item in data:
        print('[=========' + item['profession'] + '=========]')

        for trade in item['trades']:
            print('----' + trade['level'] + '----')

            for exchange in item['trades']['exchanges']:
                pass







if __name__ == __name__:
    main()