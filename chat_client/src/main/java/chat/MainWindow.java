package chat;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private JPanel rootPanel;
    private JTextArea chatArea;
    private JList<String> user_list;
    private JTextField inputField;
    private JButton sendButton;
    private Client client;

    public MainWindow(String login, Client client){
        this.setTitle(login);
        this.setMinimumSize(new Dimension(800,600));

        this.setContentPane(rootPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();

        this.client = client;

        sendButton.addActionListener(actionEvent -> send());
        inputField.addActionListener(actionEvent -> send());

    }
    private void send(){
        String message = inputField.getText();
        if(message.isEmpty()) return;
        //chatArea.append(message + "\n"); //placeholder
        client.send(message);
        inputField.setText("");



    }
    public void appendMessage(String message){
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }


}
