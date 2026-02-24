import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Scanner;

public class client { // client
    public static void main(String[] args){
        DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
        try{
        System.out.print("we are requesting\n"); 
        Scanner sc = new Scanner(System.in);    
        String message = sc.nextLine();

        Socket c = new Socket("10.115.164.173", 2004);
        System.out.print("Connected....\n"); 
        ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
        out.writeObject(message);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(c.getInputStream());
        Object message_recieved = in.readObject();
        System.out.print("<RECIEVED MESSAGE> SERVER <RECIEVED MESSAGE>: " + message_recieved.toString() + "\n");




        c.close(); out.close();
        }
        catch(Exception e){
                        System.out.print("Exception client \n" + e.toString());
        }
    }
    
}
