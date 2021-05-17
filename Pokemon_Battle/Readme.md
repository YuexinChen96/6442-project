## 1 Document Description

This is the development document for the Pokemon Battle game. It is intended to explain the background, the central setting, the structure of the project and the features of the game.



## 2 Game Dverview

### Project Background

This project is to design an RPG game in Java.

### Story

### Character Design

### Event Flow



## 3 Game Design Notes

### Skeleton

The GUI skeleton of the game is as follow:

![image-20210514114819324](https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210514114819324.png)



### Introduction to Functions and Menus

### Basic Operations

### Map System

#### Obstacle Determination

#### Map Switching

### Backpack System

### Combat System

The basic GUI is implemented using Javafx. The example is as followed:

<img src="Pokemon_Battle/src/Pics/demo/battle.png" width="800">

#### GUI
Top left: Enemy info bar (Name, HP bar, MP bar, attack - defence)  
Top right: Enemy icon  
Botton right: User info bar (Name, HP bar, MP bar, attack - defence)  
Botton left: User icon  
Botton: Turn information.

#### Control 
Right: HP poison(60% HP recover), MP poison(40 mana recover), leave the game.  
User control: Attack, Spell 1(20 mana cost), Spell 2(20 mana cost), Spell 3(80 mana cost).  
If mana is not enough for spell, we block this button, same as poisons. 

#### Turn System
Project uses **Turn** system: User turn -> Enemy turn -> User turn -> Enemy turn ... (Until User/Enemy loses all HP)  
If user loses all HP, it will redirect to the Game Over page.  
If user win the battle, it will redirect to the **Map**.  


#### Skills Setting (All skills have animations)
Attack: Use (Attack - Defence) and deal damage. No mana cost.  
Spell 1: 20 mana cost.  
Spell 2: 20 mana cost.  
Spell 3: 80 mana cost.(more powerful)

#### Spell Table
Spell_ID|User effect|Enemy effect
:--:|:--:|:--:
0|deal (2xattack) damage|deal (2xattack) damage
1|enemy's defence:-2^n  (n: level/10)|user defence:-5
2|deal (2xattack) damage|deal (2xattack) damage
3|HP:-8  attack:+1|HP:-20  attack:+4
4|deal (1.5xattack) damage, recover same amount of HP|deal (1.5xattack) damage, recover same amount of HP
5|recover 40% max HP|recover 30% max HP
6|deal (4xlevel) damage|deal (40% current HP) damage
7|deal (2xenemy_attack) damage|deal (2xuser_attack) damage
8|if (user_attack < enemy_attack):deal (3xattack) damage, else: (1.6xattack)|if (enemy_attack > user_attack):deal (3xattack) damage, else: (1.6xattack)
9|HP:-10% enemy_attack:-5|HP:-20% user_attack:-5
10|if enemy_defence == 0:kill, else deal (40xn) damage|deal 200 damage
11|deal 200 damage|deal 200 damage
12|HP:-100 deal 400 damage|deal 260 damage
13|HP:-20% enemy_HP:-50%|HP:-30% user_HP:-50%
14|HP:-85%, 2xattack, 1.5xdefence|HP:-40%, 2xattack, 1.2xdefence

##### The skills for pokemons & enemies:
Name|Spell_1|Spell_2|Spell_3
:--:|:--:|:--:|:--:
Pokemon_1|0|1|10
Pokemon_2|2|3|11
Pokemon_3|4|5|12
Pokemon_4|6|7|13
Pokemon_5|8|9|14
Enemy_7|3|8|13
Enemy_8|5|9|14

Mana cost for user: 20, 20, 80  
Mana cost for enemy: 30, 30, 50  
Mana gain for user(each turn): 8
Mana gain for enemy(each turn): 10 (only for enemy 5-8)


#### Enemy System
Enemy 1-4 can only attack the user.  
Enemy 5-6 can have two random spells from the spell table. When it has 30 mana, it will use random spell.  
Enemy 7(Advanced): predict whether use Spell_3 is optimal(die if use Spell_3)./Combo spell on 3 & 8.  
  If user_attack > enemy_attack: use 3 to increase attack, else use 8 to deal more damage.  
Enemy 8(Advanced): predict optimal from using Spell_3(check the HP spend on and the damage gain).  
  If current HP is low, use 5 to recover HP. Otherwise, use 9 to reduce use attack(permanent).  
Enemy 7 & 8 will think of use save mana for Spell_3.


#### Game Ending and Response

### Character Upgrading System



## 4 Development Tools and Platforms

#### 4.1 Programming language

This project use Java as the main back-end programming language, and Javafx as the main front-end programming language.

#### 4.2 Tools

Our team is united in using Intellij IDEA as the IDE tool, and use gitlab as the platform for code commit and version control.

## 5 Project Schedule
Id|Task|Duration|Start Date|End Data
:--:|:--:|:--:|:--:|:--:
1|Page0(Background)|2 month||
2|Page1(Pokemon select)|1 month|3.31|4.30
3|Page2(Mapping)|1 month|3.31|4.30
3.1|GUI|4 weeks|3.31|4.30
3.2|4 maps|2 weeks|3.31|4.14
3.3|movement|1 week|3.31|4.7
3.4|collect elements|1 week|4.14|4.21
4|Page3(Battle)|2 month|3.31|5.20
4.1|Battle GUI|1.5 month|3.31|5.4
4.2|Spell design|4 weeks|4.17|5.15
4.3|Enemy design|2 weeks|5.1|5.15

## 6 Project Summary



## 7 Appedix

