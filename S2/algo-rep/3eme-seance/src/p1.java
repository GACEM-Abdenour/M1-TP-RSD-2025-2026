import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Scanner;


public class p1 {  // client-1

    public static void main(String[] args){

    try{
    System.out.print("Choose an N : "); 
    Scanner NInput = new Scanner(System.in);    
    Integer N = NInput.nextInt();
    Socket Connect = new Socket("localhost",6000);
    System.out.print("Connected\n");
    ObjectOutputStream outN = new ObjectOutputStream(Connect.getOutputStream());
    outN.writeObject(N);


    ObjectInputStream inN = new ObjectInputStream(Connect.getInputStream());
    Integer result = (Integer)inN.readObject();
    System.out.print("Resultant N is : " + result);
    }
    catch(Exception e){
        System.out.print(e.toString());
    }

    }
    
}
