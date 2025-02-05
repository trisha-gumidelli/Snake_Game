# Snake_Game
## Project Overview

This project is an advanced implementation of the classic Snake Game using Java Swing. It introduces multiple difficulty levels, dynamic speed adjustments, customizable snake colors, and a modular design to enhance user experience and maintainability. 
### The game features:
- Three difficulty levels (Easy, Medium, Hard), each affecting the snake's speed, growth, and scoring system.
- Dynamic difficulty adjustments where speed increases over time in higher difficulty modes.
- A graphical user interface (GUI) with a home screen, gameplay screen, and an end screen.
- Customizable snake color allowing users to personalize their game experience.
- Collision detection for both wall and self-collisions to determine game over.
- Super Food mechanics where special food appears randomly with additional benefits.

### Tools and Technologies Used:
The project is built using the following tools and concepts:
- Programming Language
  Java: The entire game is implemented in Java, leveraging object-oriented principles to maintain modularity.
- Graphical User Interface (GUI)
  Java Swing: Used to create the interactive game interface, including panels, buttons, and dialogs.
  JPanel & JFrame: For rendering game elements and user navigation.
  JOptionPane: Used for the snake color selection pop-up.

### Game Logic and Mechanics
- ActionListener & Timer: Utilized to continuously update game states and move the snake at fixed time intervals.
  Random: Used for food placement and spawning special items.
  Array-based movement: The snake's body positions are stored in arrays, allowing smooth movement and length adjustments.
  Event-driven programming: Handles user input for controlling the snake and managing the game lifecycle.
  Difficulty Levels & Speed Adjustments

### Object-Oriented Programming (OOP):
- Level class (base class for difficulty levels)
  EasyLevel, MediumLevel, and HardLevel (subclasses implementing difficulty-specific behavior)

### Dynamic speed adjustments:
- Medium and Hard levels progressively increase snake speed, making the game more challenging over time.

### Project Structure
The project is organized into multiple classes to ensure modularity and reusability.
1. SnakeGame.java (Main Window Controller)
- Manages different screens (Home, Game, End Panel)
- Switches between game states

2. HomePanel.java (Start Screen)
- Allows users to start the game
- Provides an option to select snake color

Lets users choose difficulty level

3. GamePanel.java (Gameplay & Rendering)
- Handles snake movement, collision detection, and score updates
- Uses Timer for real-time game updates
- Renders snake, food, and score display

4. EndPanel.java (Game Over Screen)
- Displays final score
- Provides "Play Again" and "Home" buttons

5. Snake.java (Snake Logic)
- Stores the snake's position and movement logic
- Manages snake growth and collision detection

6. Food.java (Food Mechanics)
- Randomly places food on the board
- Introduces a special Super Food mechanic

7. GameController.java (Game Logic Manager)
- Handles interactions between the snake and food
- Manages score updates and difficulty scaling

8. Level.java (Difficulty Management)
- Provides a base class for different difficulty levels

9. EasyLevel.java, MediumLevel.java, HardLevel.java (Difficulty Subclasses)
- Define game behavior for each difficulty setting

### Uniqueness of the Project

This project stands out due to its innovative approach to enhancing the traditional Snake Game with modern gameplay mechanics. Below are the key aspects that make it unique:

1. Multi-Level Dynamic Difficulty System
Unlike basic Snake implementations, this project introduces a difficulty system where:
- Easy Mode keeps the game slow and beginner-friendly.
- Medium Mode progressively increases speed, adding challenge over time.
- Hard Mode not only speeds up dynamically but also increases the snake’s growth rate, making the game progressively harder.

2. Dynamic Speed Adjustment (Adaptive Gameplay)
- The snake’s speed automatically increases as the game progresses (for Medium and Hard levels), unlike traditional Snake games where speed remains constant.
- Ensures a gradual difficulty curve to engage players for longer sessions.

3. Object-Oriented and Modular Design
- Instead of having difficulty levels hardcoded into the game loop, each level is a separate class, making it easy to extend the game with new levels or mechanics in the future.
- The game logic is separated from the UI, enhancing maintainability and readability.

4. Customizable Snake Appearance
- Players can select their own snake color, offering personalization options not found in many classic Snake implementations.
- Uses JOptionPane with a dropdown for easy selection.

5. Special Food Mechanics (Super Food)
- Adds an element of surprise with randomly appearing Super Food, which provides extra benefits beyond regular food.
- Encourages players to strategize between chasing Super Food and avoiding self-collisions.

6. Interactive User Interface with Multiple Screens
- Unlike traditional console-based Snake games, this project features a graphical interface with dedicated screens for Home, Gameplay, and Game Over states.
- Enhances user experience with smooth transitions and intuitive controls.

### How to Run the Game

#### Prerequisites
- Install Java (JDK 8 or later).
- Ensure Java Swing is supported on your system.

#### Steps to Run
- Clone the repository: git clone https://github.com/trisha-gumidelli/Snake_Game
- Navigate to the project folder: cd SnakeGame
- Compile the Java files: javac SnakeGame.java
- Run the game: java SnakeGame

### Future Enhancements
- More Game Modes (e.g., Time Attack, Survival Mode, Tackling the hurdles).
- Online Leaderboards to track high scores.
- Additional Power-ups (Speed Boost, Shield, Slow-motion effect).

### Conclusion
This advanced Snake Game takes a classic concept and modernizes it with object-oriented principles, dynamic difficulty adjustments, and UI improvements. The project demonstrates strong game development concepts, event-driven programming, and modular design, making it an excellent foundation for further expansion.
If you have any feedback or suggestions, feel free to contribute or open an issue in the repository!

