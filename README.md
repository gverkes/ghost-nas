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

Start screen, this is the home screen. You can go to settings page, multiplayer, singleplayer and highscores
![Start Screen](/doc/start_screen.JPG)
Singleplayer page, choose dictionary, enter name and specify difficulty level.
![Singelplayer](/doc/singeplayer.JPG)
Choose type of multiplayer game
![Multiplayer choose](/doc/multiplayer_choose.JPG)
Multiplayer offline, enter both player names and select dictionary
![Multiplayer offline](/doc/multiplayer_offline.JPG)
Multiplayer online, choose dictionary, either look for random opponent or challenge user from friendlist
![Multiplayer online](/doc/multiplater_online.JPG)
Game, enter letter. Background changes to difficulty (*light* a lot of options open vs. *dark* little options left)
![Game](/doc/game.JPG)
Settings, change volume and change theme color
![Settings](/doc/settings.JPG)
Highscores, seperate highscores for singeplayer, offline multiplayer, online multiplayer.
![Highscores](/doc/highscores.JPG)

### Frameworks
    - Google play services (Multiplayer support)
    - Access to gallery
