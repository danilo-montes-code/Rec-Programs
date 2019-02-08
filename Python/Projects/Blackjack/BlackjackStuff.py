import random

class GamePlayer():
    def __init__(self):
        self.hand = []


class Dealer(GamePlayer):
    def __init__(self):
        GamePlayer.__init__(self)

    def get_hand(self):
        return self.hand

class Player(GamePlayer):
    def __init__(self, name, chips=0):
        GamePlayer.__init__(self)
        self.name = name
        self.chips = chips

    def bet(self, money):
        if self.chips < money:
            print(f'You don\'t have enough chips, please bet at most {self.chips} chips.')
            new_bet = int(input('How many chips do you bet?'))
            bet(new_bet)
        else:
            self.chips -= money

    def get_name(self):
        return self.name

    def get_chips(self):
        return self.chips

class Deck():
    def __init__(self):
        for i in range(0,53):
            self.deck.append(i)
        random.shuffle(deck)

    def draw(self):
        return self.deck.pop(0)

class Card():
    def __init__(self, suit, number):
        self.suit = suit
        self.number = number
        
    def __str__(self):
        return self.number+self.suit
