package chatapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    private static final int PORT = 12345;
    private static ChatServer instance;
    private Map<String, ChatRoom> chatRooms;

    private ChatServer() {
        chatRooms = new HashMap<>();
    }

    public static synchronized ChatServer getInstance() {
        if (instance == null) {
            instance = new ChatServer();
        }
        return instance;
    }

    public ChatRoom getChatRoom(String roomId) {
        return chatRooms.computeIfAbsent(roomId, ChatRoom::new);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(this, clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatServer.getInstance().start();
    }
}

