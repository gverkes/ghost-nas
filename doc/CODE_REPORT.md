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

- *loadAssetFile()*,    loading a wordlist from an assets file

- *addFilterChar()*,    Adding a character to the filter string, and filter the
                        wordlist accordingly

- *possibleFilters()*,  Makes a HashMap of every character showing words lefter
                        after adding this character to the filter

##Game
Game Model is an abstract model, used by the MultiplayerGame and
SingleplayerGame models. Every other class that uses either MultiplayerGame or
SingleplayerGame are guaranteed to have the functionality declared in this
abstract model.

###MultiplayerGame
The MultiplayerGame Model is used for *player vs player* games,

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
        Activity used for
    - MainActivity
    - MultiplayerActivity
    - SettingsActivity
    - SingleplayerActivity


