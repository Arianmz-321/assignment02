# Project information: 
    This project is a Java-based chat room application that works in real time.
    - allows users to join chat rooms
    - send and receive messages


# Improvements: 
    - Improved front end development for a more streamlined user interface
        - Changed the colour of the Chat Server title
        - Moved the "About" button so that the buttons are on the left side of the page


# How to run: 
    - Install Java Development Kit (JDK): Download and install the appropriate JDK version for your operating system
    - Install GlassFish: Download and install the GlassFish application server
    - Clone the repository: Open a terminal (or command prompt) and navigate to the directory where you want to clone the project.
    - Open the folder in an IDE
    - Build the project
    - Access the application: Open your web browser and navigate to the application URL of http://localhost:8080/chat-room.html
    - Use the chatroom by creating a room and chatting (see screenshots for output)
        - User 1 clicks "Create and join new room" and the program prompts the user to enter a username
        - After typing in a username, User 1 can now type a message
        - User 2 clicks "Create and join new room" and the program prompts the user to enter a username
        - After typing in a username, User 2 can now type a message
        - User 1 can reply to user 2 by clicking "Create and join new room" and entering their username again, and typing in a message
        - User 2 can reply to user 1 by clicking "Create and join new room" and entering their username again, and typing in a message


# Other resources
    We didn't use any other resources.


# Other notes
    - We noticed that the messages appear multiple times. However, we haven't found a way to fix this yet.
    - We noticed that the room number doesn't appear on the left and top of the HTML page (the function to generate the room number is in ChatServlet.java). We haven't found a way to fix this yet.
