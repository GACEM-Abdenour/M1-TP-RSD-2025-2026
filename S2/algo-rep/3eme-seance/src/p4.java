import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;


public class p4{  // server-3

    public static void main(String[] args){
    try{
    System.out.println("Server IP: " + java.net.InetAddress.getLocalHost().getHostAddress());
    ServerSocket server3 = new ServerSocket(6002);
    System.out.println("Server-3 is listening on port 6002: ");
    while(true){
    System.out.println("\nWaiting for connection...");
    Socket socketp3 = server3.accept();
    ObjectInputStream inp3 = new ObjectInputStream(socketp3.getInputStream());
    ObjectOutputStream outp3 = new ObjectOutputStream(socketp3.getOutputStream());
    Integer N = (Integer)inp3.readObject();
    System.out.println("Received N = " + N + " from Server-2");
    N = N * 30;
    System.out.println("Multiplied by 30, Final result = " + N);
    
    outp3.writeObject(N);
    System.out.println("Sent final result back to Server-2");

    }
   
    }
    catch(Exception e){
        System.out.print(e.toString());
    }

    }
    
}