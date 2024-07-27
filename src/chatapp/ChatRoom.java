package chatapp;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private List<User> users;
    private List<String> messages;
    private String roomId;

    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        user.setChatRoomId(roomId);
        notifyUsers(user.getUsername() + " has joined the room.");
    }

    public void removeUser(User user) {
        users.remove(user);
        notifyUsers(user.getUsername() + " has left the room.");
    }

    public void addMessage(String message) {
        messages.add(message);
        notifyUsers(message);
    }

    public void sendHistory(User user) {
        for (String message : messages) {
            user.receiveMessage(message);
        }
    }

    private void notifyUsers(String message) {
        for (User user : users) {
            user.receiveMessage(message);
        }
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<String> getMessages() {
        return messages;
    }
}

