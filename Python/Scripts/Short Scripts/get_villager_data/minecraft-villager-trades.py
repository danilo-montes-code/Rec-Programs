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
from bs4 import Tag



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
                data = make_into_dicts(prof_list)
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
        job_sites, trade_tables = get_list(dom)
        data = make_into_dicts(job_sites, trade_tables)
        display_data(data)
    


#################################################
#                 File Handling                 #
#################################################

def create_file() -> bool:
    """Create file for local storage of villager data

    Returns
    -------
    true,  if file was created successfully /
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


def get_list(dom: BeautifulSoup) -> tuple:
    """Parses the DOM to get the required tables and job sites

    Parameters
    ----------
    dom : BeautifulSoup
        the DOM of the website

    Returns
    -------
    tuple
        a tuple that contains both job sites and trade tables
    """

    ##### The wiki is *very* annoyingly formatted so parsing isn't as
    ##### simple as it should be

    # get job sites for each profession
    job_sites_span = dom.select('h3 ~ p > a[href^="/wiki/"] > span > span.sprite-text')

    job_sites = []
    for job in job_sites_span:
        job_sites.append(job.get_text().lower())

    job_sites = job_sites[:13]



    # get tables related to villager trades
    tables = dom.select('h3 + p + figure + table.wikitable')

    # get mason table (due to extra note making it not appear before)
    mason_table = dom.select_one('h3 + p + figure + div + table.wikitable')
    tables = tables[:13]

    # shift array down and place mason in right position
    for i in range(len(tables) - 1, 8, -1):
        tables[i] = tables[i-1]
    tables[9] = mason_table

    return (job_sites, tables)



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
    ]
}
'''
def make_into_dicts(job_sites: list[str], data: list[Tag]) -> list:
    """Traverses the tables to assemble the JSON for storage

    Parameters
    ----------
    job_sites : list[str]
        list of job site blocks for each villager
    data : list[Tag]
        list of tables containing villager trade info

    Returns
    -------
    list
        a list of dicts holding the data of villager trades
    """

    villager_data = []  # stores all the villager trade data

    # traverse the tables
    for i, table in enumerate(data):

        info = {}
        table_rows = table.select('tr')

        # tr[0] = <PROFESSION> Economic Trade
        profession = table_rows[0].contents[1].get_text().split(' ')[0].lower().strip()
        info['profession'] = profession
        info['job-site-block'] = job_sites[i]

        print('[=====' + profession.upper() + '=====]')


        # tr[2] = Novice row, includes first trade
        #         has attr 'rowspan' that holds the number of trades

        row_tracker = 2  # track the rows in the table
        trade_info = []  # holds the trade info

        # handle each level of trade
        for i in range(5):

            trades = []

            top_row = table_rows[row_tracker].contents[1]
            if top_row.has_attr('rowspan'):
                num_of_trades = int(top_row['rowspan'])
            else:
                num_of_trades = 1

            trade_level = top_row.get_text().lower().strip()

            print(trade_level + ' has ' + str(num_of_trades) + ' trades')


            rows = table_rows[row_tracker : row_tracker+num_of_trades]
            row_tracker += num_of_trades



            # handle each trade within a level
            first_row = True
            for row in rows:

                # first row has additional table header changing format
                columns = [content for content in row.contents if content.get_text() != '\n']
                if first_row:
                    columns = columns[1:]
                    first_row = False

                # actually get the trade info
                item_wanted           = columns[0].get_text().strip()
                default_quantity      = columns[1].get_text().strip()
                price_multiplier      = columns[2].get_text().strip()
                item_given            = columns[3].get_text().strip()
                quantity              = columns[4].get_text().strip()
                trades_until_disabled = columns[5].get_text().strip()
                xp_to_villager        = columns[6].get_text().strip()





    return villager_data




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