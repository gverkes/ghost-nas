The code of the Ghost project is built out of the following components:
    * Models
    * Activities
    * Helper functions
    * Database handler functions

##Models
The project contains the following Models:
    - Dictionary
    - Game
        - MultiplayerGame
        - SingleplayerGame
    - User

###Dictionary

The *Dictionary* Model is used for saving wordlists and filter the wordlist according to what it starts with.
The Dictionary's most important functions are:
    - *loadAssetFile*, loading a wordlist from an assets file

    - *addFilterChar*, Adding a character to the filter string, and filter the wordlist accordingly

    - *possibleFilters*, Map every character to its corresponding words left, and words left with odd number of characters left

##User
The *User* model is used for saving data about a User. It has a Database handler to save the user model to a database.
    -


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


