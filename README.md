# Java Game- Text Twister

Team: Ahmed Hashmi – Noshin Nisa
Project: Text Twister

Data Structures Used:
1.	TreeMaps
2.	ArrayLists
3.	Arrays
Functions Created:
1. TextTwister (String fileName) → Constructor function. This function asks the user for the game difficulty and starts the game. It creates two maps from the word file; ‘map’ contains all the words greater than the set word length and dictionary contains all the words smaller than the set word length. So the game word is chosen from map, and all smaller words from it are made from dictionary. The constructor also calls all the other functions that make the game run.
2. printHelper (String word) ¬¬→ This function uses the PowerSet from the last homework to make another map, ‘gameMap’ which uses an ArrayList extracted from ‘dictionary’ to form the map that contains the keys and values that will be used to play the game.
3. createBoard() →This function creates the board that the user plays on. It’s uses a map ‘boardMap’ that has the keys of ‘gameMap’ and empty ArrayLists for values.
4. printBoard() → This function prints the ‘boardMap’ in the output.
5. rules() → This function returns an ArrayList that gives the ‘rules’ to createBoard() and printBoard(). The ‘rules’ are the different levels of word lengths that the user has to complete depending on the difficulty level.
6. playGame() → This function plays the actual game. It keeps asking the user for input till it wins. It checks the user input against the maps and adds to the board that the user is playing on.
7. isGameOver() → This function checks if the user has successfully entered 5 words of each word length. 
8. RNG( int, int) → This function generates a random number that is used to select the game word.
Functions used from previous homework:
1. getFileScanner(String fileName)
2. sorter (String word)

What is the game?

The machine decides a random word. The user has to make correct words of smaller word lengths (depends on difficulty level) from that word. The user either wins by completing all the words or he/she loses by quitting and accepting the fact that he/she has failed at life.


