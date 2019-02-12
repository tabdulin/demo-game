# Demo Game application

## Task description

Develop a command line based role playing game.

Here are the stories:
 - As a player I want to create a character
 - As a player I want to explore
 - As a player I want to gain experience through fighting
 - As a player I want to save and resume a game

These will be the technical constraints:
 - Use Java
 - Libraries and Frameworks are only allowed for testing and build pipelines
 - Use best in industry agile engineering practices
 - Please build for the command line

## Implementation

This project is a demo application of Rock-Scissors-Paper Luck Fight game.

You'll check your luck with famous physicists like Isaac Newton, Albert Einstein, Jacob Bernoulli and Richard Feynman.

This is a console game where you can:
 - create or resume saved before game
 - create your character by providing your name
 - explore for the luck fight with some of rivals
 - give your hit rock/scissors/paper and have a luck fight
 - exit the game (with or without saving)
 
Games are saved under .game file  

### Typical game scenario

```
Welcome to Demo Luck Fight game!
Select mode:
1. New game
2. Resume game

Your input: 1

Create new character.
Enter your name: Talgat

Talgat, your experience is 0.
Please select next step:
1. Explore for the fight
2. Exit and save game
3. Exit without saving game

Your input: 1

Your rival is Jacob Bernoulli. Good luck!
Select your hit:
1. Rock
2. Paper
3. Scissors

Your input: 1

It's a victory :-). You gain experience by +1.
Your rival Jacob Bernoulli hit with Scissors.

Talgat, your experience is 1.
Please select next step:
1. Explore for the fight
2. Exit and save game
3. Exit without saving game

Your input: 3

Goodbye and come back ;-)!

```

### Build and run

To build a game you need jdk 11 & maven 3 installed.

To build application run: `mvn clean verify`

To run application run: `java -jar target/demo-game.jar`
