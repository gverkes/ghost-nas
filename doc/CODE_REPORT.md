The code of the Ghost project is built out of the following components:

- Models
- Activities
- Helper functionality
- Database handling

##Models
The project contains the following Models:

- Dictionary
- Game
    - MultiplayerGame
    - SingleplayerGame
- User

###Dictionary

The *Dictionary* Model is used for saving wordlists and filter the wordlist
according to what it starts with.  The Dictionary's most important functions
are:

- *loadAssetFile*,    loading a wordlist from an assets file

- *addFilterChar*,    Adding a character to the filter string, and filter the
                        wordlist accordingly

- *possibleFilters*,  Makes a HashMap of every character showing words lefter
                        after adding this character to the filter

###Game
Game Model is an abstract model, used by the MultiplayerGame and
SingleplayerGame models. Every other class that uses either MultiplayerGame or
SingleplayerGame are guaranteed to have the functionality declared in this
abstract model. The most important functions in the Game Model are:

- *guess*,  functions to guess a character

- *checkIfWinner*,  function to check if there is an winner and for which reason

####MultiplayerGame
The MultiplayerGame Model is used for *player vs player* games.

####SingleplayerGame
The MultiplayerGame Model is used for *player vs computer* games.
The decisions of the computer are based on the number words left and on the
number of words left that put the computer in a winnable situation. Winnable
situation for the game Ghost means an odd number of letters left in the word so
that only the other player will make a full word, and therefore loses.

##User
The *User* model is used for saving data about a User. It has a Database
handler to save the user model to a database.  Containing the following
variables to save this data about the user:

- ID
- Name
- Score, number of games won
- Number of games


##Activities
The project contains the following Activities:
- GameActivity
    Activity used for the actual gameplay
- HighscoreActivity
    Activity used for displaying all users and their wins
- MainActivity
    Activity used for the main navigation through the app
- MultiplayerActivity
    Activity for starting a multiplayer game
- SingleplayerActivity
    Activity for starting a singleplayer game
- SettingsActivity
    Activity for changing app setting



##Helper functionality
The only helper function this project contains is a for displaying the
Highscore as a list using an Adapter.

##Database Handling
Handling the communication with the database only for the User model.
