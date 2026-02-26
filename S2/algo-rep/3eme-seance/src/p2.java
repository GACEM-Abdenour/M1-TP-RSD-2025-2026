import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;


public class p2 {  // server-1

    public static void main(String[] args){
    try{
    ServerSocket server1 = new ServerSocket(6000);
    System.out.println("Server-1 is listening on port 6000: ");
    while(true){
    System.out.println("\nWaiting for connection...");
    Socket socketp1 = server1.accept();
    ObjectInputStream inp1 = new ObjectInputStream(socketp1.getInputStream());
    ObjectOutputStream outp1 = new ObjectOutputStream(socketp1.getOutputStream());
    Integer N = (Integer)inp1.readObject();
    System.out.println("Received N = " + N + " from client");
    N = N * 10;
    System.out.println("Multiplied by 10, N = " + N);
    
    Socket socketp3 = new Socket("localhost",6001);
    ObjectOutputStream n2 = new ObjectOutputStream(socketp3.getOutputStream());
    n2.writeObject(N);
    System.out.println("Sent N = " + N + " to Server-2");
    ObjectInputStream INresultat = new ObjectInputStream(socketp3.getInputStream());
    Integer resultat = (Integer)INresultat.readObject();
    System.out.println("Received final result = " + resultat + " from Server-2");
    outp1.writeObject(resultat);
    System.out.println("Sent final result to client");

    }
   
    }
    catch(Exception e){
        System.out.print(e.toString());
    }

    }
    
}