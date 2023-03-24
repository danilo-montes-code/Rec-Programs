"""minecraft-villager-trades

This script connects to the Minecraft wiki, parses the villager trade
information, and saves it to a file for offline viewing.

If there is a file already present, it will simply read from the file.
Otherwise, it will connect to the website.

Dependencies:
* requests
* BeautifulSoup
"""

import requests, json, os.path
from typing import TextIO
from bs4 import BeautifulSoup

#################################################
#                     Main                      #
#################################################



def main() -> None:
    """driver function"""

    # if file does not exist
    if not os.path.isfile('villager-data.txt'):
        create_file()

        with open('villager-data.txt', 'w') as f:
            dom = connect()
            prof_list = get_list(dom)
            data = make_into_json(prof_list)
            write_to_file(f, data)

    # open file
    file = open_file()

    if file is None:
        return
    
    # display data from file
    # TODO add different display options
    # TODO add command line args

    


#################################################
#                 File Handling                 #
#################################################

def create_file() -> bool:
    """Create file for local storage of villager data

    Returns
    -------
    true,  if file was created successfully
    false, otherwise
    """
    try:
        with open('villager-data.txt', 'w') as f:
            ret = True
        print('file created successfully')

    except IOError:
        print('error creating file')


def open_file() -> TextIO:

    try: 
        data = open('villager-data.txt', 'r')
        print('file opened successfully')

    except IOError:
        print('error: could not read from file')
        data = None

    finally:
        return data
    

def write_to_file(file, data) -> None:
    
    try:
        file.write(data)
        print('success writing to file')

    except:
        print('error writing to file')



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
        a list of DOM objects
    """

    return []


#################################################
#                 Data Handling                 #
#################################################

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
def make_into_json(data):
    for item in data:
        print('[=========' + item['profession'] + '=========]')

        for trade in item['trades']:
            print('----' + trade['level'] + '----')

            for exchange in item['trades']['exchanges']:
                pass




#################################################
#                    Display                    #
#################################################

def display_data(villagers):
    for item in villagers:
        print('[=========' + item['profession'] + '=========]')

        for trade in item['trades']:
            print('----' + trade['level'] + '----')

            for exchange in item['trades']['exchanges']:
                print(exchange)






if __name__ == '__main__':
    main()