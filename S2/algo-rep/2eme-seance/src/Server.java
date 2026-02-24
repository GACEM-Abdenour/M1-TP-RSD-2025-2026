import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server { // server
    // 192.168.56.1 

    public static void main(String[] args){

        try{
        System.out.println("Server starting on port 2004..."); 
        System.out.println("Server IP: " + java.net.InetAddress.getLocalHost().getHostAddress());
        ServerSocket s = new ServerSocket(2004);
        System.out.println("Server listening...");
        
        while(true){
            System.out.println("Waiting for connection..."); 
            Socket connection = s.accept();
            System.out.println("Client connected from: " + connection.getInetAddress().getHostAddress()); 
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            String ch = (String)in.readObject();
            System.out.println("Received: " + ch);
            
            ObjectOutputStream newout = new ObjectOutputStream(connection.getOutputStream());
            newout.writeObject("IL");
            newout.flush();
            System.out.println("Sent: IL");

            in.close();
            newout.close();
            connection.close();

        }
        }
        catch(Exception e){
            System.out.println("Exception - server: " + e.toString());
            e.printStackTrace();
        }
    }
    
}
