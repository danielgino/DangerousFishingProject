<p align="center">
  <img src="https://github.com/user-attachments/assets/fb1f71ae-2206-4f82-8169-678a6386285a" alt="Bonus Fish" width="400"/>
</p>

# 🎮 Dangerous Fishing - Fishing Game
> 🎓 This project was created as part of my first year studying Computer Science.
> 
A fun fishing-themed Java game featuring enemies, bonus fish, lives, and interactive game states like menus and instructions and scoreboard.

## 🚀 How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/danielgino/DangerousFishingProject.git
   cd DangerousFishingProject
   Run the MainClass.java file located in src/main/MainClass.java.

### 📂 Project Structure

```plaintext
src/
├── audio/               # Sound effects and background music
├── enemies/             # Enemy classes (e.g. Shark, PufferFish)
│   ├── Enemy.java
│   ├── PufferFish.java
│   ├── Shark.java
│   └── EnemiesMethods.java
├── fishes/              # Basic fish and bonus fish
│   ├── Fish.java
│   └── BonusFish.java
├── gamestates/          # Game screens like menu, options, instructions
│   ├── BasePanel.java
│   ├── Instructions.java
│   └── Option.java
├── images/              # Image assets 
├── level/               # Game level management
│   └── Levels.java
├── main/                # Core game logic and startup
│   ├── GamePanel.java
│   ├── GameWindow.java
│   ├── MainClass.java
│   └── MainMenu.java
├── objects/             # Game objects 
│   ├── FishingRod.java
│   ├── Heart.java
│   └── KeyboardListener.java
└── utilz/               # Utility classes and constants
    └── Constants.java
```
# Instructions 
# Welcome to Dangerous Fishing!🐟
In this game, you can currently catch two types of fish:

🟡 Yellow Fish (Regular Fish) – Worth 1 point per catch.

⚪ Gray Fish (Bonus Fish) – Worth 3 points, but it rarely spawns.

⚠️ But it’s not that easy...
There are also dangerous creatures:

🦈 Sharks

🐡 Puffer Fish

These enemies spawn more frequently as your score increases.
You cannot catch sharks, and they must not touch the fishing line!
The game starts with 3 lives.
Every time your line touches a shark or puffer fish, you lose 1 life.

### ❤️ Bonus Tip:
Each level generates a new heart in the water.
If you catch it – you gain an extra life!

### 🎮 Controls
Use arrow keys or W/S to move the hook up and down.
When a fish is caught on the hook:
Bring it all the way to the top of the rod
Then press SPACE BAR to reel it in and earn the points.
Once the fish disappears after pressing SPACE BAR, the point is added to your score.

# Pictures from game
## Main Menu
![צילום מסך 2025-05-30 224218](https://github.com/user-attachments/assets/336815e9-dd33-4d18-83ac-22a9948e5a08)

## General Picture

![צילום מסך 2025-05-30 223803](https://github.com/user-attachments/assets/2505be94-2232-4586-bb7d-2bb97591e989)
## Top Bar


![צילום מסך 2025-05-30 223846](https://github.com/user-attachments/assets/0e82e9fe-b092-49b8-a2f9-36be2020d968)
## Instructions 
![צילום מסך 2025-05-30 224117](https://github.com/user-attachments/assets/94e69e14-4e6e-4517-b660-bd7066a57399)
## Options + Scoreboard

![צילום מסך 2025-05-30 232550](https://github.com/user-attachments/assets/e47c0ba8-a3d0-45f9-911a-2ba9cbdef2ac)
