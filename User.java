package chatapp;

public class User {
    private String username;
    private ClientHandler clientHandler;
    private String chatRoomId;

    public User(String username, ClientHandler clientHandler) {
        this.username = username;
        this.clientHandler = clientHandler;
    }

    public String getUsername() {
        return username;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void receiveMessage(String message) {
        clientHandler.sendMessage(message);
    }
}

