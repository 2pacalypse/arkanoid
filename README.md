## arkanoid

Simple game project written in Java (JDK12) for a Software Development Methodologies course.

It is a classic brick breaker game where there is a paddle, a ball, and a set of bricks in the scene. The player controls the paddle and gives angle to the ball depending on where the ball hits the paddle. There are bricks of different types and it takes different number of hits to break each of them.

## Compilation

- `cd ./arkanoid/src`
- `javac ./main/java/*.java -d ../bin` 
- `cd ../bin`
- `cp ../src/main/resources/ ./main/resources -r`
- `java -classpath "." main.java.Game`
- Alternatively, just import the project to Eclipse.

## Design

In the project text, we are instructed to use only classes that derive from `JComponent`. Therefore, the objects in the game are mostly `JLabel, JButton, JTable, JScrollPane`. Since there are several pages (Home, Game, Options, Scores), a `CardLayout` has been used to switch between different pages. Only a single card is shown at any time to the user. There are lots of event listeners implemented for enabling the player to interact with the game with mouse or keyboard. Some of these which are visible to the user are:

| Action to be listened | Effect                                                  |
| --------------------- | ------------------------------------------------------- |
| Hit backspace         | Go back to home from the game, scores, or options page. |
| Press left arrow key  | Move the paddle to left.                                |
| Press right arrow     | Move the paddle to right.                               |
| Move mouse to left    | Move the paddle to left.                                |
| Move mouse to right   | Move the mouse to right.                                |
| Hit space             | Get the ball going in the play screen.                  |
| Press CTRL+Q          | Exit the game.                                          |

When the user clicks Play, the while loop responsible for 