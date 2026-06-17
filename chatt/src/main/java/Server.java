import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Server {
    private ServerSocket serverSocket;
    private HashMap<String, ClientHandler> handlers = new HashMap();
    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);

    }
    public void listen() throws IOException {
        System.out.println("SERVER STARTED");
        while(true){
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket,this);


            Thread thread = new Thread(handler);
            thread.start();

            handlers.put(handler.getLogin(),handler);
        }
    }
    public void online(ClientHandler sender){
        String userList = handlers.values().stream().map(ClientHandler::getLogin).collect(Collectors.joining("\n"));
        sender.send("Users online: \n"+userList);
    }
    public  void broadcast(String message,ClientHandler sender){
        handlers.values().stream().//filter(reciever -> reciever != sender).
                forEach(handler -> handler.send(message));
    }


    //4b
    public void whisper(String recipient, String message, ClientHandler sender) {
        ClientHandler receiver = handlers.get(recipient);

        if (receiver == null) {
            sender.send("User "+ recipient+ " is not online.");
            return;
        }

        receiver.send("[private] "+ sender.getLogin()+ ": "+ message);
    }
}