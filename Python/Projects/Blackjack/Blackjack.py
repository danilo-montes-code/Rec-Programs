import BlackjackStuff
'''
To play a hand of Blackjack the following steps must be followed:

Create a deck of 52 cards //
Shuffle the deck //
Ask the Player for their bet //
Make sure that the Player's bet does not exceed their available chips //
Deal two cards to the Dealer and two cards to the Player //
Show only one of the Dealer's cards, the other remains hidden //
Show both of the Player's cards //
Ask the Player if they wish to Hit, and take another card //
If the Player's hand doesn't Bust (go over 21), ask if they'd like to Hit again. //
If a Player Stands, play the Dealer's hand. The dealer will always Hit until the Dealer's value meets or exceeds 17 //
Determine the winner and adjust the Player's chips accordingly //
Ask the Player if they'd like to play again //
'''


def main():
    bet = input(f'What do you wish to bet? You currently have {player.get_chips()} chips. ')
    try:
        int(bet)
    except:
        print('Please enter an integer number of chips to bet.')
        main()
    else:
        play_game(int(bet))


def play_game(starting_bet):
    print()
    global dealer, player, deck
    player.bet(starting_bet)
    for i in range(4):
        if i % 2 == 0:
            dealer.add_to_hand(deck.draw())
        else:
            player.add_to_hand(deck.draw())
    print('Dealer\'s First Card: ' + str(dealer.show_card(0)))
    print('Your Hand: ' + player.show_hand())
    print('Your Hand\'s Value: ' + str(player.hand_val()))
    player_turn()
    dealer_turn()
    end_game(starting_bet)


def player_turn():
    keep_playing = True
    while not player.bust() and keep_playing:
        choice = input('Do you want to hit or stand? ')
        choice = choice.lower()
        while choice != 'hit' and choice != 'stand':
            print('Input valid action')
            choice = input('Do you want to hit or stand? ')
            choice = choice.lower()
        if choice == 'hit':
            player.add_to_hand(deck.draw())
            print('Your hand: ' + player.show_hand())
            print('Your hand\'s value: ' + str(player.hand_val()))
        else:
            keep_playing = False
    print('Your turn is over.')
    dealer_turn()


def dealer_turn():
    while dealer.hand_val() < 17:
        dealer.add_to_hand(deck.draw())


def end_game(bet):
    print()
    print('Dealer\'s Hand: ' + dealer.show_hand())
    print('Dealer\'s Hand\'s Value: ' + str(dealer.hand_val()))
    print('Your Hand: ' + player.show_hand())
    print('Your Hand\'s Value: ' + str(player.hand_val()))
    if player.bust() and not dealer.bust():
        print('You busted and lost! The dealer won!')
    elif dealer.bust() and not player.bust():
        print('The dealer busted! You won!')
        player.add_winnings(2*bet)
    elif dealer.bust() and player.bust():
        print('Both you and the dealer busted!')
    elif dealer.hand_val() > player.hand_val():
        print('The dealer won!')
    elif dealer.hand_val() == player.hand_val():
        print('You tied with the dealer!')
        player.add_winnings(bet)
    else:
        print('You won!')
        player.add_winnings(2*bet)
    print()
    if player.get_chips() > 0:
        choice = input('Would you like to play again (Yes/No) ')
        choice = choice.lower()
        while choice != 'yes' and choice != 'no':
            print('Input valid action')
            choice = input('Would you like to play again (Yes/No) ')
            choice = choice.lower()
        if choice == 'yes':
            print()
            bet = input(f'What do you wish to bet? You currently have {player.get_chips()} chips. ')
            player.clear_hand()
            dealer.clear_hand()
            deck.remake_deck()
            play_game(int(bet))
        else:
            print(f'You now have {player.get_chips()} chips.')
            print('Thank you for playing!')
    else:
        print('You have no more chips!')
        print('Thank you for playing!')


if __name__ == '__main__':
    print('Welcome to Blackjack!')
    player = BlackjackStuff.Player(10)
    dealer = BlackjackStuff.Dealer()
    deck = BlackjackStuff.Deck()
    main()
