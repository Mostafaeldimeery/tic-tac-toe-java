# Tic Tac Toe (Java) ❌⭕

A simple **Java console Tic-Tac-Toe** game with two modes: **human vs. human** and **human vs. AI**.  
It renders a 3×3 board, validates input, detects all win lines, and keeps scores across rounds.

## Features
- Modes: `HUMAN vs HUMAN (h)` or `HUMAN vs AI (a)`
- Turn-based play with `X` and `O`
- Input validation (range 1–9, prevents overwriting taken squares)
- Win detection for rows, columns, and both diagonals
- Draw detection
- Running scoreboard (wins for X, wins for O, total draws)
- Simple AI (chooses the **first available** cell)

## File structure

How to Play
Choose a mode at the prompt: h (human vs human) or a (human vs AI).
Enter a position 1–9 where:
1 | 2 | 3
---*---*---
4 | 5 | 6
---*---*---
7 | 8 | 9
Players alternate turns; the game announces wins or a draw.
After each game, choose whether to play again; the scoreboard persists for the session.

Example (console)
Choose the mode (human vs. human (h) / human vs. AI (a))
a
   |   |  
---*---*---
   |   |  
---*---*---
   |   |  

Player (X) , Choose a position from 1-9 (1 is top left and 9 is bottom right)
5
   |   |  
---*---*---
   | X |  
---*---*---
   |   |  

The computer chose position 1. Your turn now
 X |   |  
---*---*---
   | X |  
---*---*---
   |   |  

...
player X won the game 
Player (X) has score: 1, Player (O) has score: 0, Draw times are: 0
Do you want to play again (yes / no)
