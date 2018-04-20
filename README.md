# YahtzeeFight

Yahtzee Fight is a Android app I made for my *Mobile Interactive Systems* module for my *MSc Computer Games Systems*. It was done using Java.  
It's a fusion between a 2D Fighting game that utilizes Yahtzee's rules for the attacks.  
All the player does is basically play Yahtzee, but some strategy is required in this game!  
If you download the APK, I hope you have fun with it!

## Prerequisites
If you want to run this game you'll need to download the apk first. You can do this either by downloading from my Google Drive [here](https://drive.google.com/open?id=1W3W5p6qOIicY85gDMrkQoX5geZDIgbUe).  
Or you can download it together with all the files in my repository. It's the file **YahtzeeFightFile.apk**.  
You can clone my repository by entering the following command:

```
git clone https://github.com/DarioHermann/YahtzeeFighter
```

## Instructions
To start the app simply open the Yahtzee Fight app on your android device (sorry it has de default app icon, my bad).  
The game should start, if it doesn't, open up an issue.

## How to Play
This is a 1v1 game, you'll need another player to play this (or if you're a loner like me, you can just play as the two characters).  
In the first scren three characters will appear, and each player chooses one character (you can both choose the same character).  
After that a simple game of Yahtzee Starts. If you don't know the rules of this game you can check them [here](http://www.yahtzee.org.uk/rules.html).  
I've put a preview for each box you can select, to help newcomers.

## Objective
The Objective of the game is, like in any other fighting game, defeat your opponent. You do this by playing Yahtzee.  
Here's how it works:  
-Player one finishes his rolls with 3 fives and 2 fours, he has a full house, so he scores 25 points this round.  
-Player two finishes the round with 3 sixes, 1 four and 1 two. He decides to use the Sixes box, so he scores 18 points.  
-Now the second Activity starts, and since player one had more points, he will attack and the second player will defend.  
-The damage dealt to the second player will be 25 - 18 = 7 points. Player two's health bar will drop from 100 to 93, if this is the first round obviously.  
  
There are two extra cases:
* If both players end the round with the same number of points, no one receives damage
* If a player scores a Yahtzee (5 of the same dice), the other player won't defend and receive full 50 points of damage.
  
The game ends either by one of the player's health dropping to zero or if all the boxes were ticked (13 rounds).  
If the game ends and no has dropped his HP to zero, then the winner is decided by the amount of points won.
A Draw is possible in this game.

## Requirements
Since I'm kind of an idiot, I made the Yahtzee Table as a Linear Layout occupping the entire size of the screen, if you have a small phone, it will probably suck to play this game on your device.  
I'll try to make it a Scroll View next time I touch this code, sorry for now.

## Built With
* **[Android Studio](https://developer.android.com/studio/index.html)** - Development IDE.
* **[Photoshop CS6](https://www.adobe.com/pt/products/photoshop.html)** - Used to make the dices and the game Logo.

## Version
It's V1.0. I want to come back to these game to add some online functionalities, new characters, Scroll View, special abilities, etc.

## Authors
* **Dário Herman** - [DarioHermann](https://github.com/DarioHermann)

## Acknowledgements
* All three Characters in this game belong to [Redshrike](https://opengameart.org/users/redshrike). Amazing 2D Sprites.
* The font used for the Logo is *[True Lies](https://www.dafont.com/true-lies.font)* by Jonathan Harris.
* I simply love Yahtzee, it's a game I played a lot with my grandma, and decided to make a android version of it for my phone. But I had to put in a twist, and what better than to add my favourite game genre!
