# Ghost (Android App)
Ghost is an Android application replicating the Game 'Ghost', a two-player word game. For more info on Ghost, see [Wikipedia](http://en.wikipedia.org/wiki/Ghost_%28game%29).

Other files:

[Project Code Report](doc/CODE_REPORT.md)

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

### Activities
Main goal is keeping it as simple as possible, only add stuff most (>80%) of the users want.
[Pictures of Sketches](doc/SKETCHES.md)

##### MainActivity
Select a game mode here, change the setting or see highscores

##### SinglePlayerActivity
Start SinglePlayer game here, specifying language of dictionary and A.I. difficulty

##### MultiPlayerActivity
Start MultiPlayer game here, specifying language and player names

##### GameActivity
Play the game here

##### HighscoresActivity
See highscores for SinglePlayer and Multiplayer


### Models
#### Dictionary
<pre><b>Fields</b>
    Public wordlist         : String[]
    Public wordlistFiltered : String[]
    Private language        : String
<b>Methods</b>
    Public void filter(Sring filter)
      info
    Public int count()
    Public String result()
    Public void reset()
</pre>

#### Game
<pre>
<b>Fields</b>
    Private dictionary      : Dictionary[]
    Private playerIds       : int[]
    Private turnPlayerId    : int
    Private word            : String
<b>Methods</b>
    Public int ended()
    Public Dictionary getDictionary()
    Public int[] getPlayerIds()
    Public void guess(char geussedChar)
    Public void setPlayerIds(int[] playerIds)
    Public void setDictionary(Dictionary dictionary)
    Public int turn()
    Public int winner()
</pre>

#### User
<pre>
<b>Fields</b>
    Private id               : int
    Private name             : String
    Private score            : int
<b>Methods</b>
    Public String getName()
    Public int getScore()
    Public int getId()
    Public void setName(Sring name)
      info
</pre>

###Further work

- Better Singleplayer computer decisions
- Better layout, adding screen tint to all activities
- Online Multiplayer

### Frameworks
- Google play services (Multiplayer support)
- Access to gallery
