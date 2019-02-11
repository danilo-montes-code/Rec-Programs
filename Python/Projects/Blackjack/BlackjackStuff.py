import random


class GamePlayer:
    def __init__(self):
        self.hand = []
        self.hand_value = 0
        self.busted = False

    def add_to_hand(self, card):
        self.hand.append(card)
        self.hand_value = self.calc_val()
        if self.hand_value > 21 and self.has_ace():
            for card in self.hand:
                if card.card_number() == 'A':
                    if not card.change_ace():
                        card.make_one()
                        self.hand_value = self.calc_val()
                        self.busted = False
                        break
                    else:
                        self.busted = True
        elif self.hand_value > 21:
            self.busted = True

    def show_card(self, place):
        return self.hand[place]

    def show_hand(self):
        ubuh = ''
        for i in self.hand:
            ubuh += str(i) + ' '
        return ubuh

    def hand_val(self):
        return self.hand_value

    def bust(self):
        return self.busted

    def calc_val(self):
        total = 0
        for i in self.hand:
            total += i.card_val()
        return total

    def has_ace(self):
        for i in self.hand:
            if i.card_number() == 'A':
                return True
        return False

    def clear_hand(self):
        self.hand = []
        self.hand_value = 0
        self.busted = False


class Dealer(GamePlayer):
    def __init__(self):
        GamePlayer.__init__(self)


class Player(GamePlayer):
    def __init__(self, chips=0):
        GamePlayer.__init__(self)
        self.chips = chips

    def bet(self, money):
        if self.chips < money:
            print(f'You don\'t have enough chips, please bet at most {self.chips} chips.')
            new_bet = int(input('How many chips do you bet? '))
            self.bet(new_bet)
        else:
            self.chips -= money

    def get_chips(self):
        return self.chips

    def add_winnings(self, winnings):
        self.chips += winnings


class Deck:
    deck = []

    def __init__(self):
        self.cards = 52
        suits = ['♠', '♥', '♦', '♣']
        values = ['A', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K']
        for i in range(4):
            for x in range(13):
                self.deck.append(Card(suits[i], values[x]))
        random.shuffle(self.deck)

    def draw(self):
        return self.deck.pop(0)

    def show_deck(self):
        for i in range(len(self.deck)):
            print(self.deck[i])

    def remake_deck(self):
        self.deck = []
        self.__init__()


class Card:
    def __init__(self, suit, number):
        self.suit = suit
        self.number = number
        try:
            int(number)
        except ValueError:
            if number == 'A':
                self.value = 11
                self.ace_is_one = False
            else:
                self.value = 10
        else:
            self.value = int(number)

    def __str__(self):
        return self.number+self.suit

    def card_number(self):
        return self.number

    def card_val(self):
        return self.value

    def change_ace(self):
        return self.ace_is_one

    def make_one(self):
        self.value = 1
        self.ace_is_one = True
