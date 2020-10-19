# MAD - Level 4: Task 2

This Mobile Application is developed as part of the introduction to the Mobile Application Development at HvA.
The application is used as an introduction to using local storage to make the data persisted.

This application is to simulate a game of rock, paper, scissors. Users can view the total amount
of wins, losses, and draws, and they're also able to see and wipe the match history.

## Level 4 questions

-	A singleton pattern is used in the class that defines the database. What is the purpose of this pattern?  
The reason that this pattern is used is to make sure that there is only one instance of the database. This prevents unnecessary data duplication and the unexpected results that would come from using more then 1 instance of the database.

-	Why should you load the data in a background thread?  
You should load the data in the background thread to prevent the UI from freezing while the data is fetched. Otherwise the main thread will freeze up and do nothing until the data is received.

-	What are the three major components of ROOM and what are their responsibilities?  
A room database largely consists of the database, repositories and entities. Entities are the objects or classes that are stored into the database and the repositories are used to talk to the database in order to get, update, save, or delete an Entity or multiple Entities. The database itself holds all the data.
Another component that could play a large role is a dao service. Although strictly not necessary, these services can hold static data for when the database is not available.

-	How can you extract the current database so that you can see the table, columns, and data?  
https://stackoverflow.com/questions/44429372/view-contents-of-database-created-with-room-persistence-library
