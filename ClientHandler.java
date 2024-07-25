package chatapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private ChatServer server;
    private Socket socket;
    private User user;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Enter your username:");
            String username = in.readLine();
            user = new User(username, this);

            out.println("Enter chat room ID:");
            String roomId = in.readLine();
            ChatRoom chatRoom = server.getChatRoom(roomId);
            chatRoom.addUser(user);

            String message;
            while ((message = in.readLine()) != null) {
                if (message.startsWith("/pm")) {
                    handlePrivateMessage(message);
                } else if (message.startsWith("/history")) {
                    chatRoom.sendHistory(user);
                } else {
                    chatRoom.addMessage(user.getUsername() + ": " + message);
                }
            }

            chatRoom.removeUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlePrivateMessage(String message) {
        String[] parts = message.split(" ", 3);
        if (parts.length < 3) return; // Invalid format

        String recipientUsername = parts[1];
        String privateMessage = parts[2];
        ChatRoom chatRoom = server.getChatRoom(user.getChatRoomId());
        User recipient = chatRoom.getUserByUsername(recipientUsername);
        if (recipient != null) {
            recipient.receiveMessage("[PM from " + user.getUsername() + "]: " + privateMessage);
        } else {
            sendMessage("User " + recipientUsername + " not found.");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}

