"""minecraft-villager-trades

This script connects to the Minecraft wiki, parses the villager trade
information, and saves it to a file for offline viewing.

If there is a file already present, it will simply read from the file.
Otherwise, it will connect to the website.

Dependencies:
* requests
* BeautifulSoup
"""

import requests, json, os.path, sys
from typing import TextIO
from bs4 import BeautifulSoup



TESTING = True




#################################################
#                     Main                      #
#################################################

def main() -> None:
    """driver function"""

    if not TESTING: 
        # if file does not exist
        if not os.path.isfile('villager-data.txt'):
            if not create_file():
                return

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
        display_data(data)

    else:

        dom = connect()
        prof_list = get_list(dom)
        data = make_into_json(prof_list)
        display_data(data)
    


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
        ret = False

    finally:
        return ret


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


def get_list(dom: BeautifulSoup) -> list:
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

    ##### This wiki is *very* annoyingly formatted so parsing isn't as
    ##### simple as it should be


    # get headers related to villager professions
    headers = dom.select('h3')
    headers = headers[3:16]

    professions = []
    for header in headers:
        professions.append(header.get_text().lower())

    
    # get job sites for each profession
    job_sites_span = dom.select('h3 ~ p > a[href^="/wiki/"] > span > span.sprite-text')

    job_sites = []
    for job in job_sites_span:
        job_sites.append(job.get_text().lower())

    job_sites = job_sites[:13]




    # for i, prof in enumerate(professions):
    #     print(str(i) + ': ' + prof)

    # for i in range(13):
    #     print(professions[i] + ' -> ' + job_sites[i])



    # get tables related to villager trades
    tables = dom.select('h3 + p + figure + table.wikitable')

    # get mason table (due to extra note making it not appear before)
    mason_table = dom.select_one('h3 + p + figure + div + table.wikitable')
    tables = tables[:13]

    # shift array down and place mason in right position
    for i in range(len(tables) - 1, 8, -1):
        tables[i] = tables[i-1]
    tables[9] = mason_table








    with open(os.path.join(sys.path[0], 'tables.txt'), 'w') as f:
        
        stuff = []
        for table in tables:
            desc = table['data-description']
            parts = desc.split(' ')

            stuff.append(desc)

        f.write('\n'.join(stuff))

    return []


#################################################
#                 Data Handling                 #
#################################################

'''
JSON format of villager records
{
    "profession" : <PROFESSION>,
    'job-site-block' : <JOB SITE BLOCK>,
    "trades" : [
        {
            "level" : <LEVEL>,
            "exchanges" : [
                {
                    "wanted" : {
                        'item' : <ITEM>,
                        'default-quantity' : <NUMBER>,
                        'price-multiplier' : <NUMBER>
                    },
                    "given"  : {
                        'item' : <ITEM>,
                        'quantity' : <NUMBER>,
                    },
                    'trades-until-disabled' : <NUMBER>,
                    'xp-to-villager' : <NUMBER>
                },
                ...
            ]
        },
        ...
    ]\
}
'''
def make_into_json(data):
    # for item in data:
    #     print('[=========' + item['profession'] + '=========]')

    #     for trade in item['trades']:
    #         print('----' + trade['level'] + '----')

    #         for exchange in item['trades']['exchanges']:
    #             pass
    return ['hi']




#################################################
#                    Display                    #
#################################################

def display_data(villagers):
    # for item in villagers:
    #     print('[=========' + item['profession'] + '=========]')

    #     for trade in item['trades']:
    #         print('----' + trade['level'] + '----')

    #         for exchange in item['trades']['exchanges']:
    #             print(exchange)
    print(villagers[0])






if __name__ == '__main__':
    main()