package chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientGUI extends JFrame {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JTextArea chatArea;
    private JTextField messageField;
    private JTextField usernameField;
    private JTextField roomField;
    private JButton sendButton;
    private JButton connectButton;
    private JButton disconnectButton;
    private JLabel statusLabel;
    private DefaultListModel<String> userListModel;

    public ChatClientGUI() {
        setTitle("Chat Client");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        messageField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.setEnabled(false);

        usernameField = new JTextField("Enter username");
        roomField = new JTextField("Enter room ID");
        connectButton = new JButton("Connect");
        disconnectButton = new JButton("Disconnect");
        disconnectButton.setEnabled(false);
        statusLabel = new JLabel("Disconnected");

        userListModel = new DefaultListModel<>();
        JList<String> userList = new JList<>(userListModel);
        userList.setBorder(BorderFactory.createTitledBorder("Users"));
        userList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane userScrollPane = new JScrollPane(userList);
        userScrollPane.setPreferredSize(new Dimension(150, 0));

        JPanel connectPanel = new JPanel(new GridLayout(2, 2));
        connectPanel.setBorder(BorderFactory.createTitledBorder("Connect"));
        connectPanel.add(usernameField);
        connectPanel.add(roomField);
        connectPanel.add(connectButton);
        connectPanel.add(disconnectButton);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(chatScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        add(connectPanel, BorderLayout.NORTH);
        add(userScrollPane, BorderLayout.EAST);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String roomId = roomField.getText().trim();
                if (!username.isEmpty() && !roomId.isEmpty()) {
                    connectToServer(username, roomId);
                }
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectFromServer();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Enter username")) {
                    usernameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Enter username");
                }
            }
        });

        roomField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (roomField.getText().equals("Enter room ID")) {
                    roomField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (roomField.getText().isEmpty()) {
                    roomField.setText("Enter room ID");
                }
            }
        });
    }

    private void connectToServer(String username, String roomId) {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(username);
            out.println(roomId);

            chatArea.setText("");
            messageField.setText("");
            sendButton.setEnabled(true);
            disconnectButton.setEnabled(true);
            connectButton.setEnabled(false);
            statusLabel.setText("Connected");

            new Thread(new MessageReceiver()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disconnectFromServer() {
        try {
            if (socket != null && !socket.isClosed()) {
                out.println("DISCONNECT");
                socket.close();
                socket = null;
                out = null;
                in = null;

                chatArea.setText("");
                messageField.setText("");
                sendButton.setEnabled(false);
                disconnectButton.setEnabled(false);
                connectButton.setEnabled(true);
                statusLabel.setText("Disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        if (out != null) {
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                out.println(message);
                messageField.setText("");
            }
        }
    }

    private class MessageReceiver implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("Enter your username:") || message.startsWith("Enter chat room ID:")) {
                        // Skip these initial prompts
                        continue;
                    }
                    chatArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatClientGUI client = new ChatClientGUI();
            client.setVisible(true);
        });
    }
}
