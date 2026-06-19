
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler implements Runnable{
    private String login;
    private final Socket socket;
    private final Server server;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(),true);
        this.login = reader.readLine();

        System.out.println("Login: "+this.login);
    }
    public String getLogin(){
        return login;
    }


    public void send(String message){
        writer.println(message);
    }

    @Override
    public void run() {

        try {
            String message;


            while ((message = reader.readLine()) != null) {
                if (message.startsWith("/")) {
                    String[] tokens = message.split(" ", 3);
                    String command = tokens[0];
                    switch (command) {
                        case "/online" -> {
                            server.online(this);
                        }
                        //4b
                        case "/w" -> {
                            if (tokens.length > 2) {
                                send("Usage: /w recipient message");
                                continue;
                            }
                            String recipient = tokens[1];
                            String privateMessage = String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length));
                            server.whisper(privateMessage, this, recipient);
                        }
                    }
                    continue;
                }
                server.broadcast(message,this);
            }
            socket.close();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

}