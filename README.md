# Arkanoid Game - Assignment 5

**Student Name:** Tamar Werthaim  
**Course:** Object-Oriented Programming (89111)  

---

## 📋 Overview

This project is a Java-based implementation of the classic **Arkanoid / Brick Breaker** game, developed as part of Assignment 5 in the Object-Oriented Programming course. 

The application builds upon 2D geometry, physics collision engines, and object-oriented principles. Assignment 5 introduces an event-driven architecture using the **Observer Pattern**, object removal mechanisms, score tracking, dynamic game state monitoring (blocks and balls counting), and end-game detection (Win / Game Over logic).

---

## ✨ Key Features & Design Patterns

### 1. 📢 Observer Pattern (Event Listening)
- **`HitNotifier` Interface:** Objects that can trigger hit events (such as `Block`) implement this interface to maintain a subscriber list of `HitListener` instances.
- **`HitListener` Interface:** Components interested in hit events implement `hitEvent(Block beingHit, Ball hitter)` to respond reactively without tight coupling.

### 2. 🗑️ Object Removal Mechanism
- **`BlockRemover`:** Listens for hit events on blocks, decreases the remaining block count, removes destroyed blocks from the game environment and sprite collection, and unregisters itself.
- **`BallRemover`:** Listens for hits on the bottom screen boundary (death zone), removes out-of-bounds balls from the game, and updates the ball counter.

### 3. 🏆 Score Tracking & UI
- **`ScoreTrackingListener`:** Awards +5 points whenever a block is hit.
- **`ScoreIndicator`:** A dedicated top-screen sprite displaying the live score.
- **Completion Bonus:** Clearing all blocks awards a +100 point bonus upon winning.

### 4. 📐 Geometry & Collision Physics
- Precision trajectory ray-tracing using line segment intersection algorithms with rectangle borders (`Line`, `Point`, `Rectangle`).
- Divided paddle bounce zones: 5 distinct zones alter reflection angle dynamically based on impact position.

### 5. ⏱️ Main Game Loop & Frame Rate Management
- 60 FPS animation loop executed with smooth timing control (`biuoop.Sleeper`).

---

## 📁 Package & Code Structure

```text
src/
├── Ass5Game.java               # Main entry point of the application
├── Collision/                  # Physics, velocity, and collision interfaces
│   ├── Collidable.java         # Interface for obstacle objects
│   ├── CollisionInfo.java      # Encapsulates collision point & collidable object
│   └── Velocity.java           # Vector velocity (dx, dy) & angle-speed converters
├── Game/                       # Game flow, logic, state management, and listeners
│   ├── BallRemover.java        # Removes balls that hit the bottom wall
│   ├── BlockRemover.java       # Removes hit blocks from game state
│   ├── Counter.java            # Thread-safe integer counter utility
│   ├── Game.java               # Core controller and 60 FPS main loop manager
│   ├── GameEnvironment.java    # Spatial environment storing collidables
│   ├── HitListener.java        # Observer interface for hit events
│   ├── HitNotifier.java        # Subject interface for registering observers
│   └── ScoreTrackingListener.java # Listens to hits and updates player score
├── Geometry/                   # Mathematical primitives
│   ├── Line.java               # 2D line segment and intersection calculations
│   ├── Point.java              # 2D point coordinates (x, y)
│   └── Rectangle.java          # Rectangle primitive with edge intersection logic
└── Sprites/                    # Renderable objects and animations
    ├── Ball.java               # Moving ball sprite with collision physics
    ├── Block.java              # Rectangular block sprite (Notifies on hit)
    ├── Paddle.java             # User-controlled moving platform
    ├── ScoreIndicator.java     # Top bar displaying the player score
    ├── Sprite.java             # Interface for renderable game objects
    └── SpriteCollection.java   # Container managing game sprites
```

---

## 🛠️ Requirements & Dependencies

- **Java Development Kit (JDK):** Version 8 or higher.
- **`biuoop-1.4.jar`:** GUI and input handling library provided for the course.
- **Apache Ant:** Optional, used for automated building and running via `build.xml`.

---

## 🚀 How to Build and Run

### Option 1: Using Apache Ant (Recommended)

Run the following commands from the project root directory:

```bash
# Compile and run the game directly
ant run

# Compile source files to the bin directory
ant compile

# Clean compiled classes
ant clean
```

### Option 2: Using standard Java CLI

**On Windows (PowerShell / Command Prompt):**
```bash
# Compile
javac -cp "bin;biuoop-1.4.jar" -d bin src/*.java src/*/*.java

# Run
java -cp "bin;biuoop-1.4.jar" Ass5Game
```

**On Linux / macOS:**
```bash
# Compile
javac -cp "bin:biuoop-1.4.jar" -d bin src/*.java src/*/*.java

# Run
java -cp "bin:biuoop-1.4.jar" Ass5Game
```

### Option 3: IntelliJ IDEA / Eclipse
1. Open the project folder in your Java IDE.
2. Add `biuoop-1.4.jar` as a Project Library/Dependency.
3. Run `src/Ass5Game.java`.

---

## 🎮 Game Controls & Rules

- **Left Arrow (`←`):** Move paddle left.
- **Right Arrow (`→`):** Move paddle right.
- **Winning:** Destroy all color-coded block rows to win (+100 bonus points).
- **Game Over:** Lose all 3 balls past the bottom edge of the window.
