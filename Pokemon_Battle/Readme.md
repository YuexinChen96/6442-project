## 1 Document Description

This is the development document for the Pokemon Battle game. It is intended to explain the background, the central setting, the structure of the project and the features of the game.



## 2 Game Overview

### 2.1 Background and Story

This project is to design an RPG(Role-playing game) by using Java and Javafx as main developing languages. The name of game is **Pokemon Battle**. As with traditional RPGs, players will play as different pokemon characters with different skills to explore the maze, as well as battling with enemies in different levels. We designed an extensive skill pool, complex battle system, and strict upgrading system to help players get better experience.

The story of this game is about a tribe of Pokemon lived in a Far East island use their own power to fight against foreign invaders. In this game, the player will perform one of the Pokemon, and different pokemons will have different fighting skills and initial abilities. The maze is one of the four zones on the island. A lot of enemies lurk in various areas of the island and vary in level and combat power. There are also a number of different treasures to help you improve your fighting abilities and get through the island. Players will need to assess your own strength and the skills of your enemies, and allocate the use of props and routes of action accordingly.

### 2.2 Character Design

There are five different Pokemons in the game, each has different basic information. The basic information includes level, skills, Health point, Mana point(stands for the energy to use a magic skill), defense and attack abilities, experience, and the abilities to cross grass/water/stones. Players can choose any one of them in the beginning of the game.

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517203627034.png" alt="image-20210517203627034" style="zoom: 50%;" />

### 2.3 Event Flow

The entire game will follow the following flow.

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517225935372.png" alt="image-20210517225935372" style="zoom:50%;" />

Starting page presentation:

<img src="../../../Library/Application Support/typora-user-images/image-20210517232222452.png" alt="image-20210517232222452" style="zoom:25%;" />

The first event interface presentation:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517224633079.png" alt="image-20210517224633079" style="zoom: 25%;" />

The second event interface presentation:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517224706769.png" alt="image-20210517224706769" style="zoom: 25%;" />

The third event interface presentation:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517224915078.png" alt="image-20210517224915078" style="zoom: 25%;" />

The fourth event interface presentation:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517230634112.png" alt="image-20210517230634112" style="zoom:25%;" />

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517230716875.png" alt="image-20210517230716875" style="zoom:25%;" />

## 3 Game Design

### 3.1 Skeleton

The back-end skeleton of the project is as below:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517194523873.png" alt="image-20210517194523873" style="zoom:33%;" />

The GUI skeleton of the project is as below:

![image-20210518014942397](https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210518014942397.png)



### 3.2 Introduction to Functions and Menus

The summary of each class's main function are as below:

| Class Name | Main Function                                                |
| ---------- | :----------------------------------------------------------- |
| Pokemon    | Use json file to record each pokemon's initial basic information. Methods to record basic infromation's changes  and the players position in the map during the whole game. |
| Enemy      | Use json file to record each enemy's initial basic information. Methods to record basic infromation's changes during each battle. |
| Battle     | Use AtomicBoolean and AI(the method help judging which way to win is the best) as well as Javafx to update the changes of basic information both for Pokemon and the enemy, and update the front desk aminations at the same time. Record the result of battle(win or gameover) and Pokemon's level upgrading results. |
| Map        | Methods to decide map situation, including enemies in different types, props, obstacles, starting and ending of the map, map switching, etc. |
| GUI        | The main front-end display by using Javafx, including six pages from beginning to the end of the game. |
| Search     | Use bfs(breadth first search) algorithm to find the shortest path to a nearby accessible target. |



### 3.3 Basic Operations

#### 3.3.1 Map System

##### a.	Movement & Obstacle Determination & Props

Player can control the pokemon to move by pressing arrow keys on the keyboard. ArrowLeft stands for moving left, and ArrowRight stands for moving right, ArrowUp stands for moving up, ArrowDown stands for moving down.

There is 1 obstacle, "Fixed stone", on the map that can never be crossed, and 3 obstacles, "Grass", "Water", "Stone wall", that can only be crossed if the conditions are met. If players move the Pokemon to the position of those obstacles, the system will refuse to finish the movement.

There are 3 props, "Grass shoes", "Water shoes", "Wall shoes", help players to gain extra ability to cross the "Grass", "Water", "Stone wall" respectively. If players move the to the position of those props, the corresponding abilities will become available.

There are 4 props, "HP bottle", "MP bottle", "Sword strengthen", "Shield strengthen", help players to upgrade the number of health point, the number of mana point, the number of attack, and the number of defense respectively. If players move the to the position of "HP bottle" or "MP bottle", the bottles will be added to players backpack. If players move the to the position of "Sword strengthen" or "Shield strengthen", the attack or defense ability will be increased immediately.

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517234128275.png" alt="image-20210517234128275" style="zoom:33%;" />



##### b.	Map Switching

There are totally 4 different maps in this game. Each map has enemies in different levels and props in different usage. In each map, players will always start at the entrance or the exit of map. If players reach the exit of map, they can continue to the next map if they move the Pokemon to the right direction, e.g. if the player stands in the exit of the following map and continue moving downward, then the interface will switch to the next map:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517235252668.png" alt="image-20210517235252668" style="zoom: 50%;" />

However, if the current map is already the fourth map (the last one), then if the player reach the exit of the fourth map, the game will finish and the interface will switch to the chechout screen of the game.

Conversely, if the player reach the entrance of map and want to switch to the last map, he /she can control the Pokemon to move backwards, and the interface will switch to the last map. If the current map is the first map, it will not respond to the movement of switching to the last map.



#### 3.3.2 Backpack System

The game allow players to store HP bottle and MP bottle into the backpack. Players will be initially assigned two HP bottles and two MP bottles. If players reach another HP bottle or MP bottle in the map, the bottle will be automatically stored into the backpack. Players can check their props inside the backpack by clicking "My attributes" button in the bottom left corner of the interface:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210518001923156.png" alt="image-20210518001923156" style="zoom: 33%;" />

During each battle, players can use the bottles inside the backpack in their own turns by clicking the number of bottle onthe right side of the interface:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210518001827760.png" alt="image-20210518001827760" style="zoom: 33%;" />

#### 3.3.3 Combat System

The basic GUI is implemented using Javafx. The example is as followed:

<img src="Pokemon_Battle/src/Pics/demo/battle.png" width="800">

##### GUI
Top left: Enemy info bar (Name, HP bar, MP bar, attack - defence)  
Top right: Enemy icon  
Botton right: User info bar (Name, HP bar, MP bar, attack - defence)  
Botton left: User icon  
Botton: Turn information.

##### Control 
Right: HP poison(60% HP recover), MP poison(40 mana recover), leave the game.  
User control: Attack, Spell 1(20 mana cost), Spell 2(20 mana cost), Spell 3(80 mana cost).  
If mana is not enough for spell, we block this button, same as poisons. 

##### Turn System
Project uses **Turn** system: User turn -> Enemy turn -> User turn -> Enemy turn ... (Until User/Enemy loses all HP)  
If user loses all HP, it will redirect to the Game Over page.  
If user win the battle, it will redirect to the **Map**.  


##### Skills Setting (All skills have animations)
Attack: Use (Attack - Defence) and deal damage. No mana cost.  
Spell 1: 20 mana cost.  
Spell 2: 20 mana cost.  
Spell 3: 80 mana cost.(more powerful)

##### Spell Table
Spell_ID|User effect|Enemy effect
:--:|:--:|:--:
0|deal (2xattack) damage|deal (2xattack) damage
1|enemy's defence:-2^n  (n: level/10)|user defence:-5
2|deal (2xattack) damage|deal (2xattack) damage
3|HP:-8, attack:+1|HP:-20, attack:+4
4|deal (1.5xattack) damage, recover same amount of HP|deal (1.5xattack) damage, recover same amount of HP
5|recover 40% max HP|recover 30% max HP
6|deal (4xlevel) damage|deal (40% current HP) damage
7|deal (2xenemy_attack) damage|deal (2xuser_attack) damage
8|if (user_attack < enemy_attack):deal (3xattack) damage, else: (1.6xattack)|if (enemy_attack > user_attack):deal (3xattack) damage, else: (1.6xattack)
9|HP:-10%, enemy_attack:-5|HP:-20%, user_attack:-5
10|if enemy_defence == 0:kill, else deal (40xn) damage|deal 200 damage
11|deal 200 damage|deal 200 damage
12|HP:-100, deal 400 damage|deal 260 damage
13|HP:-20%, enemy_HP:-50%|HP:-30%, user_HP:-50%
14|HP:-85%, 2xattack, 1.5xdefence|HP:-40%, 2xattack, 1.2xdefence

###### The skills for pokemons & enemies:
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


#### 3.3.4 Enemy System
Enemy 1-4 can only attack the user.  
Enemy 5-6 can have two random spells from the spell table. When it has 30 mana, it will use random spell.  
Enemy 7(Advanced): predict whether use Spell_3 is optimal(die if use Spell_3)./Combo spell on 3 & 8.  
  If user_attack > enemy_attack: use 3 to increase attack, else use 8 to deal more damage.  
Enemy 8(Advanced): predict optimal from using Spell_3(check the HP spend on and the damage gain).  
  If current HP is low, use 5 to recover HP. Otherwise, use 9 to reduce use attack(permanent).  
Enemy 7 & 8 will think of use save mana for Spell_3.



#### 3.3.5 Player Upgrading System

After each battle, if players win they can gain some experience(depending on the enemy's level). And if the experience grows to a certain level, players can upgrade their Pokemons:

<img src="https://cdn.jsdelivr.net/gh/kath-t/picgo/image-20210517201053198.png" alt="image-20210517201053198" style="zoom: 25%;" />

Different levels will have different requirement of the number of experience to upgrade. The relationship between different levels and the required number of experience:

| Current Level region | number of experience needed to upgrade |
| :------------------: | :------------------------------------: |
|     Level 1 - 10     |              10 per level              |
|    Level 10 - 20     |             100 per level              |
|    Level 20 - 30     |             500 per level              |
|    Level 30 - 40     |             2000 per level             |

Different level will bring extra abilities for the Pokemon (except obtaining props to unlock the ability ahead):

| Level | Ability to cross grass |   Ability to cross water   |  Ability to cross Stone wall  |
| :---: | :--------------------: | :--: | :--: |
| Above 10 | True | False | False |
| Above 20 | True | True | False |
| Above 30 | True | True | True |
|       |                        |      |      |

Different Pokemon roles will have different upgrading system:

| Pokemon ID | add Attack |    add HP    | add Attack per 5 level | add Defense per 5 level |
| :--------: | :--------: | :----------: | :--------------------: | :---------------------: |
|     1      |   +2^n   | +2^{(n+3)} |           0            |         +2^n          |
|     2      |   +2^n   | +2^{(n+3)} |         +2^n         |         +2^n          |
|     3      |   +2^n   | +2^{(n+3)} |           0            |      +2^{(n+1)}       |
|     4      |   +2^n   | +2^{(n+3)} |      +2^{(n+1)}      |      +2^{(n+1)}       |
|     5      |   +2^n   | +2^{(n+3)} |           0            |         +2^n          |
|            |            |              |                        |                         |

After each upgrading, the HP of the pokemon will always be restored to the maximum number of HP.



## 4 Development Tools and Platforms

#### 4.1 Programming language

This project uses Java as the main back-end programming language, and Javafx as the main front-end programming language.

#### 4.2 Tools

Our team is united in using Intellij IDEA as the IDE tool, and use gitlab as the platform for code commit and version control.

## 5 Project Schedule
Id|Task|Duration|Start Date|End Data
:--:|:--:|:--:|:--:|:--:
1|Page0(Background)|2 month|3.31|5.15
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
