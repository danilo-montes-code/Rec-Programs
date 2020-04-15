def display_board(board):
    print(board[7] + '|' + board[8] + '|' + board[9])
    print('-----')
    print(board[4] + '|' + board[5] + '|' + board[6])
    print('-----')
    print(board[1] + '|' + board[2] + '|' + board[3])


def player_input():
    player1choice = ''
    while player1choice != 'X' and player1choice != 'O':
        player1choice = input('Does Player 1 want to be X or O?\n')
        player1choice = player1choice.upper()
    print(f'Player 1 has chosen to be {player1choice}')
    if player1choice == 'X':
        player2choice = 'O'
        print(f'Player 2 will be {player2choice}')
    else:
        player2choice = 'X'
        print(f'Player 2 will be {player2choice}')
    return player1choice, player2choice


def place_marker(board, marker, position):
    board[position] = marker


def win_check(board, mark):
    if board[7] == board[8] == board[9] and board[7] == mark:
        return True
    elif board[4] == board[5] == board[6] and board[4] == mark:
        return True
    elif board[1] == board[2] == board[3] and board[1] == mark:
        return True
    elif board[7] == board[4] == board[1] and board[1] == mark:
        return True
    elif board[8] == board[5] == board[2] and board[2] == mark:
        return True
    elif board[9] == board[6] == board[3] and board[3] == mark:
        return True
    elif board[7] == board[5] == board[3] and board[3] == mark:
        return True
    elif board[1] == board[5] == board[9] and board[1] == mark:
        return True
    else:
        return False


def full_board_check(board):
    return board[1] != ' ' and board[2] != ' ' and board[3] != ' ' and board[4] != ' ' and board[5] != ' ' \
           and board[6] != ' ' and board[7] != ' ' and board[8] != ' ' and board[9] != ' '


def check_tie(board, marker1, marker2):
    if full_board_check(board) and not win_check(board, marker1) or \
            full_board_check(board) and not win_check(board, marker2):
        return True
    else:
        return False


def space_check(board, position):
    return board[position] == ' '


def player_choice(board):
    pos = input('What space would you like to place your marker in? ')
    while pos != '1' and pos != '2' and pos != '3' and pos != '4' and pos != '5' and pos != '6' and \
            pos != '7' and pos != '8' and pos != '9':
        print('Invalid input, please pick a number from 1 to 9')
        print('')
        pos = input('What space would you like to place your marker in? ')
    if space_check(board, int(pos)):
        return int(pos)
    else:
        return 'Error. Space already filled'


def replay():
    again = input('Do you want to play again? (Yes/No) ').lower()
    while again != 'yes' and again != 'no':
        print('Invalid input, please enter either "yes" or "no"')
        again = input('Do you want to play again? (Yes/No) ').lower()
    if again == 'yes':
        return True
    else:
        return False


print('Welcome to Tic Tac Toe!')
print('The board follows the same layout as the number pad. '
      'Enter the space you want to put your marker in with the number pad.')
print('The player who is X will go first')
print('')
game = True
win = False
switcher = 'X'
# Game Setup
player1, player2 = player_input()
board = ['#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
print('\n')
while game:
    if switcher == 'X':
        if player1 == 'X':
            print("Player 1's turn")
        else:
            print("Player 2's turn")
    else:
        if player1 == 'O':
            print("Player 1's turn")
        else:
            print("Player 2's turn")
    display_board(board)
    move = player_choice(board)
    while move == 'Error. Space already filled':
        print('Space already filled, please pick another space')
        move = player_choice(board)
    if switcher == 'X':
        if player1 == 'X':
            place_marker(board, player1, move)  # Player 1 Move if X
        else:
            place_marker(board, player2, move)  # Player 2 Move if X
    else:
        if player1 == 'O':
            place_marker(board, player1, move)  # Player 1 Move if O
        else:
            place_marker(board, player2, move)  # Player 2 Move if O
    if switcher == 'X':
        switcher = 'O'
    else:
        switcher = 'X'
    print('\n')
    if not check_tie(board, player1, player2):  # If there is no tie
        if win_check(board, player1):
            print('Player 1 wins!')
            win = True
            display_board(board)
        elif win_check(board, player2):
            print('Player 2 wins!')
            win = True
            display_board(board)
    else:
        print('Tie! No one wins.')
        display_board(board)
        if not replay():
            break
        else:  # Remakes the board and asks for mark assignment again for rematch
            board = ['#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
            win = False
            print('\n'*50)
            player1, player2, = player_input()
    if win:  # Win check and check if players want a rematch
        if not replay():
            break
        else:  # Remakes the board and asks for mark assignment again for rematch
            board = ['#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
            win = False
            print('\n'*50)
            switcher = 'X'
            player1, player2, = player_input()
