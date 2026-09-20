# MinDrawFX

A modular 2D vector graphics editor built with **Java 21** and **JavaFX 21**, designed to demonstrate object-oriented software architecture, GoF design patterns, persistence, and separation of concerns.

MinDrawFX provides an interactive drawing canvas where users can create geometric shapes, apply visual effects, save and load drawings, perform multi-level Undo/Redo operations, and export the canvas as a PNG image.

---

## Features

- Draw **Rectangles**, **Circles**, and **Lines**
- Interactive click-and-drag shape creation
- Select graphical objects on the canvas
- Delete selected shapes
- Multi-level **Undo / Redo**
- Apply dynamic visual decorators:
  - Glow
  - Shadow
  - Red Border
  - Dashed Border
  - Thick Border
- Save and load drawings using **SQLite**
- Alternative text-file persistence
- Export the JavaFX canvas to **PNG**
- Console and file logging
- Activity history and UI status updates

---

## Technologies

| Technology | Usage |
|---|---|
| Java 21 | Core application language |
| JavaFX 21 | Desktop UI and Canvas rendering |
| FXML | User interface definition |
| CSS | JavaFX interface styling |
| SQLite | Embedded database |
| JDBC | Database access |
| SQLite JDBC Driver | SQLite connectivity |
| Java ImageIO | PNG image export |

---

## Architecture

MinDrawFX follows an MVC-oriented architecture complemented by dedicated **Service** and **Repository** layers.

```text
View
  │
  ▼
Controller
  │
  ▼
Service Layer
  │
  ├── Domain Model
  │
  ├── Commands
  │
  ├── Factories
  │
  └── Decorators
  │
  ▼
Repository Layer
  │
  ├── SQLite
  └── Text File
```

### Main Components

- **View** — JavaFX FXML interface and CSS styling
- **Controller** — Handles user interactions and coordinates application behavior
- **Model** — Contains shapes, commands, observers, and strategies
- **Service Layer** — Provides high-level application operations
- **Repository Layer** — Abstracts persistence mechanisms
- **Factory Layer** — Centralizes shape and repository creation

---

## Design Patterns

MinDrawFX applies several software design patterns to keep the application modular and extensible.

### Command Pattern

The Command pattern encapsulates drawing operations into command objects.

Main classes:

```text
Command
DrawShapeCommand
DeleteShapeCommand
CommandManager
```

`CommandManager` maintains Undo and Redo stacks, allowing drawing and deletion operations to be reversed or replayed.

---

### Decorator Pattern

Visual effects are dynamically applied to existing shapes without modifying their original implementations.

Implemented decorators include:

```text
GlowDecorator
ShadowDecorator
RedBorderDecorator
DashedDecorator
ThickBorderDecorator
```

All decorators extend the common `ShapeDecorator` abstraction.

---

### Factory Pattern

Shape creation is delegated to dedicated factories:

```text
ShapeFactory
RectangleFactory
CircleFactory
LineFactory
ShapeFactoryProducer
```

This separates object creation logic from the application controller.

---

### Observer Pattern

The application uses the Observer pattern to synchronize command execution with interface updates.

```text
Subject
Observer
CommandManager
MainController
```

`CommandManager` notifies registered observers whenever commands are executed, undone, or redone.

---

### Strategy Pattern

Logging behavior can be switched dynamically through a common logging abstraction.

```text
LoggerStrategy
ConsoleLogger
FileLogger
LoggingContext
```

This allows the application to use different logging destinations without changing the calling code.

---

### Adapter Pattern

`CanvasExportAdapter` bridges JavaFX canvas rendering with Java's image-writing APIs.

```text
JavaFX Canvas
      │
      ▼
CanvasExportAdapter
      │
      ▼
SwingFXUtils
      │
      ▼
ImageIO
      │
      ▼
PNG File
```

---

### Singleton Pattern

`DatabaseConnection` manages the application's shared SQLite JDBC connection using a lazy-initialized Singleton.

---

## Architectural Patterns

In addition to GoF design patterns, MinDrawFX uses architectural patterns to organize application responsibilities.

### MVC

The application separates:

```text
Model      → shapes and domain behavior
View       → FXML + CSS
Controller → MainController
```

### Repository Pattern

Persistence is abstracted through:

```text
ShapeRepository
├── SQLiteShapeRepository
└── JsonShapeRepository
```

Despite its historical class name, `JsonShapeRepository` currently stores data using a semicolon-separated text format rather than JSON.

### Service Layer

The application exposes high-level operations through:

```text
ShapeService
ShapePersistenceService
LoggingService
ExportService
```

---

## Undo / Redo Workflow

Drawing and deletion operations are implemented as commands.

```text
User Action
    │
    ▼
Command
    │
    ▼
CommandManager
    │
    ├── execute()
    ├── undo()
    └── redo()
```

The manager maintains separate Undo and Redo stacks.

---

## Persistence

MinDrawFX supports two persistence approaches.

### SQLite

The primary persistence implementation uses SQLite through JDBC.

Stored shape properties include:

```text
id
type
x
y
width
height
color
```

The database is created locally at runtime.

### Text File

A second repository implementation serializes shapes to a simple text format:

```text
type;x;y;width;height;color
```

This provides an alternative persistence mechanism without requiring database storage.

---

## PNG Export

The current canvas can be exported as a PNG image.

The export workflow is:

```text
JavaFX Canvas
   ↓
Canvas Snapshot
   ↓
SwingFXUtils
   ↓
ImageIO
   ↓
export.png
```

The generated image is a runtime artifact and is not committed to the repository.

---

## Project Structure

```text
MinDrawFX/
├── lib/
│   └── sqlite-jdbc-3.53.1.0.jar
│
├── src/
│   ├── adapter/
│   │   └── CanvasExportAdapter.java
│   │
│   ├── app/
│   │   └── MainApp.java
│   │
│   ├── command/
│   │   └── CommandManager.java
│   │
│   ├── controller/
│   │   └── MainController.java
│   │
│   ├── decorator/
│   │   ├── DashedDecorator.java
│   │   ├── GlowDecorator.java
│   │   ├── RedBorderDecorator.java
│   │   ├── ShadowDecorator.java
│   │   ├── ShapeDecorator.java
│   │   └── ThickBorderDecorator.java
│   │
│   ├── factory/
│   │   ├── CircleFactory.java
│   │   ├── LineFactory.java
│   │   ├── RectangleFactory.java
│   │   ├── RepositoryFactory.java
│   │   ├── ShapeFactory.java
│   │   ├── ShapeFactoryProducer.java
│   │   └── ShapeType.java
│   │
│   ├── model/
│   │   ├── commands/
│   │   ├── observers/
│   │   ├── shapes/
│   │   └── strategies/
│   │
│   ├── repository/
│   │   ├── JsonShapeRepository.java
│   │   ├── SQLiteShapeRepository.java
│   │   └── ShapeRepository.java
│   │
│   ├── service/
│   │   ├── ExportService.java
│   │   ├── LoggingService.java
│   │   ├── ShapePersistenceService.java
│   │   └── ShapeService.java
│   │
│   ├── singleton/
│   │   └── DatabaseConnection.java
│   │
│   └── view/
│       ├── main-view.fxml
│       └── style.css
│
├── .gitignore
└── README.md
```

---

## Running the Project

### Requirements

- JDK 21
- JavaFX SDK 21
- SQLite JDBC driver

The SQLite JDBC driver is included in:

```text
lib/sqlite-jdbc-3.53.1.0.jar
```

### JavaFX Modules

The project requires the following JavaFX modules:

```text
javafx.controls
javafx.fxml
javafx.swing
```

Configure the JavaFX SDK in your IDE, add the SQLite JDBC driver to the classpath, and run:

```text
app.MainApp
```

---

## Runtime Files

The application may generate the following files locally:

```text
mindraw.db
shapes.txt
export.png
```

These files are runtime artifacts and are excluded from the Git repository.

---

## Current Limitations

The current version focuses primarily on architecture and design-pattern implementation.

The following features are not currently implemented:

- Moving shapes after placement
- Shape resizing
- Custom color picker
- Text tool
- Zoom
- Layers
- Copy / Paste
- Keyboard shortcuts
- Automated JUnit tests
- Maven or Gradle build automation

---

## Software Engineering Focus

MinDrawFX was developed primarily as a software architecture project to explore:

- Object-Oriented Programming
- Separation of concerns
- SOLID-oriented design
- GoF Design Patterns
- MVC architecture
- Persistence abstraction
- Reversible commands
- Extensible application structure

---

## Author

**Bassem Wali**

Software Engineering Student  
Full-Stack Development & AI Integration

- GitHub: [bassem2002](https://github.com/bassem2002)
- LinkedIn: [bassem-wali](https://www.linkedin.com/in/bassem-wali)

---

## Project Status

MinDrawFX is an academic software architecture project and is currently maintained as part of my software engineering portfolio.
