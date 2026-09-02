# Bus Operator Information Console

## Project Description
This project is a bus operator’s information console, modeled after the onboard consoles used on TransLink buses in Metro Vancouver. The application provides a simple, interactive GUI that allows bus operators to view route information, browse full stop lists, and simulate sending operational radio messages to a central “Transit Communications” system. 

A system administrator can update the transit network by adding new stops to existing routes or modifying stop names that appear across multiple routes.  

This project reflects my long-standing interest in public transit. I have been passionate about Metro Vancouver’s transit system since early childhood, beginning with my first ride on the Canada Line in 2009. More recently, I’ve become fascinated by the computational systems behind buses, such as operator consoles and LED destination sign programming, which inspired the design and functionality of this application.

## Core Features
- Add new bus stops to existing routes  
- View full stop lists for any route  
- Modify stop names across multiple routes  
- Simulate operator radio messages to Transit Communications  

## Data Persistence
- Save all route, stop, and operator message data to a JSON file  
- Load previously saved data to restore the application state  

## How to Run
1. Clone this repository  
2. Open the project in VS Code or IntelliJ  
3. Run the `TransitAppUI` class in the ui package 
4. The GUI will launch automatically  

## Instructions for End Users
- View all stops added to the active route in the **Stop List Panel** on the left side of the GUI.  
- Add a new stop by filling in the stop fields (direction, name, ID, timing point) and clicking **Add Stop**.  
- Modify an existing stop by selecting it in the Stop List Panel, editing the fields, and clicking **Modify Stop**.  
- View transit‑related images in the **Visual Panel** on the right side of the GUI.  
- Save all route, stop, and operator message data by clicking **Save Data** (writes to `transit.json`).  
- Load previously saved data by clicking **Load Data** (reads from `transit.json`).  

## Future Improvements
Given additional development time, I would refactor the UI layer to introduce an abstract base class for all JPanel components (StopListPanel, OperatorMessagePanel, VisualPanel, and ControlPanel).  

Currently, each panel independently handles layout, styling, and component initialization, which leads to duplicated code and inconsistent structure. A shared abstract superclass could provide common behavior, such as standardized layouts, shared helper methods, or unified styling conventions.  

This abstraction would reduce duplication and make UI-wide changes easier to implement. While the current design is simple enough that duplication is not harmful, increasing UI complexity or adding new features would benefit from a more maintainable and consistent architecture.

