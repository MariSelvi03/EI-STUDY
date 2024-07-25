# Chat Application

**Chat Application** is a real-time chat system implemented in Java. This project demonstrates basic chat functionalities including joining chat rooms, sending and receiving messages, and handling private messages, all within a graphical user interface (GUI).

## Key Features

- **Real-Time Messaging**: Instantly send and receive messages within chat rooms.
- **Chat Rooms**: Users can join and interact in specific chat rooms identified by unique IDs.
- **Private Messaging**: Send private messages to other users within the same chat room.
- **Message History**: Retrieve and display previous messages from a chat room.
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

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/chatapp.git
   ```
