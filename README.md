# DAYpoker
**Authors: Adron Vrona, Dina Pekelis, Yuxiang Huang, Steve Lee**

## ITERATION 1

##### What user stories were completed this iteration? #####

Currently, we have a mostly functioning game loop that will play a standard heads-up version of the Texas Hold'em variant of poker. This includes giving users the option to do things such as bet, raise bets, call bets, check, see their hand, fold, etc. Any action that can be taken in a Texas Hold'em game is currently implemented. Additionally, a fully functioning pot has been implemented, that keep tracks of bets and allocates to the winner after each hand.

##### What user stories do you intend to complete next iteration? #####

The main thing that needs to be implemented next iteration is the actual system that can score poker hands and correctly award a "winning hand". The program currently just awards every hand that reaches the river to player 1 if nothing else is triggered (a running out of chips, folding, etc). With the implemented system, two hands will be shipped to a function, and the winning hand will be determined. Another thing that needs to be implemented is user graphics, so that cards are more interesting to look at with ASCII art. 

##### Is there anything that you implemented but doesn't currently work? #####

There are some issues with folding that will skip a players turn during the next hand. Additionally, going into negative chip values is possible with a certain combination of inputs. Finally, there are some if else paths that are supposed to print certain error messages, but are currently not triggering.

##### What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!) #####

A script is included named "playpoker.sh" that will compile and run the poker game in Java. To run this script, locate the directory installed from Github and type "./playpoker.sh"
