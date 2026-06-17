package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.reader = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()
                ));
        this.writer = new PrintWriter(
                socket.getOutputStream(), true);
    }

    public void send(String message){
        writer.println(message);
    }

    @Override
    public void run() {
        String message;
        try{
            while((message = reader.readLine())!= null){
                System.out.println(message);

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
