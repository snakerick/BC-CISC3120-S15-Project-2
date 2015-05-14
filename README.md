# README.md

The long assignment description is [here](http://bc-cisc3120-s15.github.io/project2-networkedobjects).

## Description and Instructions
This is a "SimpleGame" rework, using Model View Controlled. It also can connect through websocket and played with 2 or more people. You main goal is to avoid the space objects and get the highest score. Highscore is kept track by who survives the longest.
####Steps
######Single Player:
1. Just run SimpleChecker.java

######Multiplayer: 
1. Run GameServer.java
2. Run SimpleClient after as many as you like
3. It will open up another window wiht an ID screen along with "Start" button
4. When players are ready they should click "start" 
5. Once all the players are ready a new window will come up and the game will start
6. The player with the highest "score" will win.

## Works Cited
http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller

WebSocketLab was used

JavaDocs for subclasses and class information

## Code Summary
The code was written with the Model View Control concept. The Model created the objects of the game and did all the logic of the game. The Control part is the SimpleChecker this gives the SimpleModel an update whenever the user presses a button or moves the object. The SimpleChecker also allows the user to move the spaceship. Then there is the SimpleGame which is the "paint" concept that paints everything together allow the user to see the graphics of the game. The SimpleGame repaints each time when something happens the SimpleModel. There is also the GameServer which is the "server" that allows multipule user to connect onto the game which is for more then 1 player. When the user connects the GameServer will keep count of how many people are in the game. Then tehre is the SimpleClient which is used to play online with other people it a SimpleGUI that with only a button that allows the user to click "start" when they are ready. When everyone is ready the game will begin and your main goal is to avoid everything and be the last man standing.


## Extra Credit Behavior
None.
