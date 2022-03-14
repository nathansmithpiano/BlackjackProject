# BlackjackProject

### Description

  -- Developed for Skill Distillery Bootcamp Cohort 32 --

  This is a Blackjack game using standard rules.
  
  A user can choose how many human players (1-6) they would like.
  For each round, each player can hit, stay, or exit back to the main menu.  The dealer hits if under 17 and stays if 17 or greater.
  Aces count as either 1 or 9.
  
  A player's turn ends either when they choose "stay" or when they "bust" (go over 21) or hit a blackjack (21).
  
  The deck contains a standard 52 cards.  After each round, players return their cards to the discard pile.
  When the deck is out of cards, the discard pile is shuffled and placed back into the deck.
  
  Settings contains many adjustable settings.  For the same of time, these features were not user changeable but could easily be implemented as one field controls them throughout the app.

### Cool Features

 A good deal of time was spent experimenting with display formatting.  
 - Each player name and card info
 - Similar to a real-life game, the first card is hidden from all but the current player.  Dealer first card is always hidden.
 - At the end of each round, all cards are shown.
 - The current player has their name marked with -> Player <-
 - Options for the player to hit, stay, and exit appear underneath a player's column, making it clear who is going when.
 - Blackjack (total value of 21) and bust (total value over 21) evaluations consider Ace as either 1 or 9.
 - Settings can adjust:
 - - Which player goes first
 - - Min/max human players
 - - Column width
 - - Space between columns
 - - Empty margin to the left before any output
 - - Which character is repeated as a line break
 - - Prompts before user imput
 - - General messages at the end of each round and when exiting the app

### Technologies Used

- Java
- Eclipse
- Atom
- Github
- Terminal
- MacBook Pro Retina 2015

### What was fun?

- Playing with display formatting.
- I would have liked to go crazy with ASCII art cards but there simply wasn't enough time.
- - I wanted to display ASCII cards instead of names, and could have done so with another day or so...
- I also enjoyed using a Settings class and building most everything from those fields.

### Thoughts For The Future

- It's very useful to test small functionality seperately from the app and implement incrementally.  In my development, this was more common at the beginning stages than at the end.  For the future, I want to remember to do so continuously and to not write too much before testing.
- Things seemed inconsistent - i.e., using an array or an ArrayList, setting another primitive to store output from another method instead of just using the method call in the loop definition, etc.  I can see why a style guide comes in handy.
- It would be fun to do another card game, thought it would likely take more than one weekend.
- Some of the suggested methods in the UML seemed unnecessary (i.e. isHard() and isSoft(), when it seems !isHard() could suffice instead of isSoft()
- I would enjoy seeing the logic for different 1/9 ace combinations when assessing bust and blackjack status.  I simply hard-coded in for a possibility of 5 aces but I know it's possible to do so via loops or a formula... just had to move on for the time being.  Implementing more than one deck would not have been very difficult but would have caused issues with the hard-coded ace logic.
- It would be fun to do something like this on a team, where one person works out display functionality, one person works out ace logic, one person works out game rules, etc.
