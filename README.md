# My Personal Project: a Bus Operator's Information Console

## Project Description:
My personal project is a bus operator's information console, modelled from real consoles found on all TransLink buses in the Metro Vancouver region. The application has a basic menu, and allows for viewing the route number and the full list of stops on the route. It also has a feature to simulate contacting a central "Transit Communications" network by radio. In addition, a system administrator can add bus stops to existing routes, and change stop names that might affect multiple different bus routes. The intended user is a bus operator interacting with the console while driving the bus, as well as a system administrator handling behind the scenes updates. This project is of interest to me because I have been passionate about public transit in the Metro Vancouver area since I was two years old, when my parents first took me to ride the Canada Line when it opened in 2009. Recently, I have become more interested in the computational side of features of TransLink buses, such as the information console that my project is modelled from, as well as the programs that go into running the LED displays on buses.

**Phase 1 (Console-based application): User Stories**
- As a system administrator, I want to be able to add a bus stop to an existing list of stops on a bus route.
- As a bus operator, I want be able to view the full list of all the stops on a particular route to verify the full sequence of stops.
- As a system administrator, I want to be able to modify a stop name so it reflects changes that may apply to multiple routes in the system.
- As a bus operator, I want to be able to simulate contacting Transit Communications by entering an operational message to practice sending radio communications.

**Phase 2 (Data Persistence): User Stories**
- As a bus operator, if I select the quit option, I want to be reminded to save all route, stop, and operator message data, and have the option to do so or not.
- As a bus operator, I want to be given the option to load all route, stop, and operator message data, so I can resume where I left off.

# Instructions for End User
- You can view the panel that displays the Xs that have already been added to the Y by looking at the *Stop List Panel* on the left side of the GUI. This panel displays all stops that have been added to the active route.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by filling in the stop fields (direction, name, ID, timing point) and clickind the **Add Stop** button in the bottom bar.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on an existing stop in the *Stop List panel*, editing the fields, then clicking the **Modify Stop** button.
- You can locate my visual component by looking at the *Visual Panel* on the right side of the GUI, which contains several transit-related images that satisfy the required visual component.
- You can save the state of my application by clicking the **Save Data** button in the top bar of the GUI. This will write all routes, stops, and operator messages to a JSON file titled `transit.json`.
- You can reload the state of my application by clicking the **Load Data** button in the top bar of the GUI. This will read all saved data from `transit.json` and display it in the GUI.
