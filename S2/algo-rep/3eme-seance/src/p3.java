import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;


public class p3 {  // server-2

    public static void main(String[] args){
    try{
    System.out.println("Server IP: " + java.net.InetAddress.getLocalHost().getHostAddress());
    ServerSocket server2 = new ServerSocket(6001);
    System.out.println("Server-2 is listening on port 6001: ");
    while(true){
    System.out.println("\nWaiting for connection...");
    Socket socketp2 = server2.accept();
    ObjectInputStream inp2 = new ObjectInputStream(socketp2.getInputStream());
    ObjectOutputStream outp2 = new ObjectOutputStream(socketp2.getOutputStream());
    Integer N = (Integer)inp2.readObject();
    System.out.println("Received N = " + N + " from Server-1");
    N = N * 20;
    System.out.println("Multiplied by 20, N = " + N);
    
    Socket socketp4 = new Socket("localhost",6002);
    ObjectOutputStream n3 = new ObjectOutputStream(socketp4.getOutputStream());
    n3.writeObject(N);
    System.out.println("Sent N = " + N + " to Server-3");
    ObjectInputStream INresultat = new ObjectInputStream(socketp4.getInputStream());
    Integer resultat = (Integer)INresultat.readObject();
    System.out.println("Received final result = " + resultat + " from Server-3");
    outp2.writeObject(resultat);
    System.out.println("Sent final result back to Server-1");

    }
   
    }
    catch(Exception e){
        System.out.print(e.toString());
    }

    }
    
}