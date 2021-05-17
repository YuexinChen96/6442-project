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

#### GUI: 
Top left: Enemy info bar (Name, HP bar, MP bar, attack - defence)  
Top right: Enemy icon  
Botton right: User info bar (Name, HP bar, MP bar, attack - defence)  
Botton left: User icon  
Botton: Turn information.

#### Control: 
Right: HP poison(60% HP recover), MP poison(40 mana recover), leave the game.  
User control: Attack, Spell 1(20 mana cost), Spell 2(20 mana cost), Spell 3(80 mana cost).  

Project uses **Turn** system: User turn -> Enemy turn -> User turn -> Enemy turn ... (Until User/Enemy loses all HP)  
If user loses all HP, it will redirect to the Game Over page.  
If user win the battle, it will redirect to the **Map**.  


#### Skills Setting (All skills have animations)
Attack: Use (Attack - Defence) and deal damage. No mana cost.  
Spell 1: 20 mana cost.  
Spell 2: 20 mana cost.  
Spell 3: 80 mana cost.(more powerful)

##### Spell Table
Spell_ID|User effect|Enemy effect|icon
---|:--:|:--:|---:
0|deal (2 x Attack) damage|deal (2 x Attack) damage|<img src="Pokemon_Battle/src/Pics/Spell/thunder.png" width="50">

The skills for 5 pokemons:

0, 1, 10

2, 3, 11

4, 5, 12

6, 7, 13

8, 9, 14

小招耗蓝20，大招耗蓝80

百分号都是最大生命的百分之……



The skills for 8 enemies:

0～3没技能

4，5从小技能(0,1,2,3,4,7)里随机抽2个，有蓝就放

6，7一个大招一个小招，有预判，思考什么时候放

​	6: 3，8，13

​		在蓝够的时候，会计算自己的血量30%够不够，够的话放13；不够的话，3和8之间，e先判断攻击是否比u高，低的话先提攻（3技能），高的话放8技能；血量<30%强制优先放回血技能；

​		也就是在如下两个中有优劣势的判断：

- max血*40%+一回合或两回合要遭受的伤
- 在加攻击之后的收益直到自己死掉，所能产生的所有攻击

​	7: 5，9，14

技能14:改成自损40%

所有怪物的回蓝是10点/回合，怪物放小技能耗30点蓝，大招50点蓝



#### Game Ending and Response

### Character Upgrading System



## 4 Development Tools and Platforms

#### 4.1 Programming language

This project use Java as the main back-end programming language, and Javafx as the main front-end programming language.

#### 4.2 Tools

Our team is united in using Intellij IDEA as the IDE tool, and use gitlab as the platform for code commit and version control.

## 5 Project Schedule



## 6 Project Summary



## 7 Appedix

