# Chat Application

**Chat Application** is a real-time chat system implemented in Java. This project demonstrates basic chat functionalities including joining chat rooms, sending and receiving messages, and handling private messages, all within a graphical user interface (GUI).


## Key Features

- **Real-Time Messaging**: Instantly send and receive messages within chat rooms.
- **Chat Rooms**: Users can join and interact in specific chat rooms identified by unique IDs.
- **Private Messaging**: Send private messages to other users within the same chat room.
- **User-Friendly GUI**: Provides a graphical interface using Swing for an intuitive user experience.
  

## Components

- **ChatServer**: Manages chat rooms, user connections, and message broadcasting. Handles incoming client connections and user sessions.
- **ClientHandler**: Manages communication between the server and individual clients, processing user inputs and routing messages.
- **ChatRoom**: Represents a chat room, maintaining user lists and message history, and facilitating communication among users.
- **User**: Represents individual users, storing their username and managing their connection through the client handler.
- **ChatClientGUI**: Provides the client-side graphical interface for connecting to the server, joining chat rooms, and exchanging messages.
  

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Eclipse IDE (or any Java IDE of your choice)


## Running the Application

1. **Start the Server**:
   - Open ChatServer.java .
   - Run the ChatServer class to start the server.

2. **Start the Client**:
   - Open ChatClientGUI.java .
   - Run the ChatClientGUI class to start the client application

3. **Connect and Chat**:
   - In the client GUI, enter a username and a chat room ID.
   - Click Connect to join the chat room and start exchanging messages.
     
  
## Usage

- **Joining a Chat Room**: Type in a chat room ID and connect to join the specified room.
- **Sending Messages**: Type messages into the text field and click Send or press Enter.
- **Private Messaging**: Use the /pm command followed by the recipient's username and the message text to send private messages.


---
