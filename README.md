# Ghost (Android App)
Ghost is an Android application replicating the Game 'Ghost', a two-player word game. For more info on Ghost, see [Wikipedia](http://en.wikipedia.org/wiki/Ghost_%28game%29)

### Main Features:
- versus Computer( Easy, Medium, Hard)
- versus Player
    - Offline, one phone
    - Online, turn-based based. Using *Google play game services*
- Highscore
- Multi language support
- Color themes
- Avatar per user
- Sounds (minimal, can be turned off)

### Sketches, user experience (Activities)
Main goal is keeping it as simple as possible, only add stuff most (>80%) of the users want.

[Pictures of Sketches](SKETCHES.md)

### Activities
##### MainActivity

##### SinglePlayerActivity

##### MultiPlayerActivity

##### GameActivity

##### HighscoresActivity


### Models
##### Dictionary
    Fields
    - Public wordlist         : String[]
    - Public wordlistFiltered : String[]
    - Private language         : String
    Methods
    - Public void filter(Sring filter)
        info
    - Public int count()
    - Public String result()
    - Public void reset()

##### Game
###### Fields
- Private dictionary      : Dictionary[]
- Private playerIds       : int[]
- Private turnPlayerId    : int
- Private word            : String
###### Methods
- Public int ended()
- Public Dictionary getDictionary()
- Public int[] getPlayerIds()
- Public void guess(char geussedChar)
- Public void setPlayerIds(int[] playerIds)
- Public void setDictionary(Dictionary dictionary)
- Public int turn()
- Public int winner()

##### User
###### Fields
- Private id               : int
- Private name             : String
- Private score            : int
###### Methods
- Public String getName()
- Public int getScore()
- Public int getId()
- Public void setName(Sring name)
    info


### Frameworks
- Google play services (Multiplayer support)
- Access to gallery
