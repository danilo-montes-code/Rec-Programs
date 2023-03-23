"""minecraft-villager-trades

This script connects to the Minecraft wiki, parses the villager trade
information, and saves it to a file for offline viewing.

If there is a file already present, it will simply read from the file.
Otherwise, it will connect to the website.

Dependencies:
* requests
* BeautifulSoup
"""

import requests
from bs4 import BeautifulSoup

#################################################
#                     Main                      #
#################################################



def main() -> None:
    """driver function"""

    try: 
        data = open('villager-data.txt', 'r')

        print(data.read())

    except:
        # file does not exist, connect to wiki
        dom = connect()
        prof_list = get_list(dom)
        write_to_file(prof_list)

    finally:
        data.close()
    

#################################################
#              Connecting and DOM               #
#################################################

def connect() -> BeautifulSoup:
    """Connects to the Minecraft Wiki Trading page

    Returns
    -------
    BeautifulSoup
        a BeautifulSoup object representing the DOM of the website
    """

    URL = "https://minecraft.fandom.com/wiki/Trading"
    page = requests.get(URL)

    soup = BeautifulSoup(page.content, "html.parser")

    return soup


def get_list(dom) -> list:
    """Parses the DOM to get the required items to put in a list

    Parameters
    ----------
    dom : BeautifulSoup
        the DOM of the website

    Returns
    -------
    list
        a list of DOM objects for saving
    """

    return []


'''
JSON format of villager records
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
}
'''

def write_to_file(data):
    for item in data:
        print('[=========' + item['profession'] + '=========]')

        for trade in item['trades']:
            print('----' + trade['level'] + '----')

            for exchange in item['trades']['exchanges']:
                pass


def display_data(villagers):
    for item in villagers:
        print('[=========' + item['profession'] + '=========]')

        for trade in item['trades']:
            print('----' + trade['level'] + '----')

            for exchange in item['trades']['exchanges']:
                print(exchange)






if __name__ == __name__:
    main()