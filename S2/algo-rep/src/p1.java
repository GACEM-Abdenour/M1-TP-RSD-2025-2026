import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class p1 { // client

    public static void main(String[] args){
        
        try{
        System.out.print("p1 is Requestin access\n"); 
        Socket c = new Socket("localhost", 2004);
        System.out.print("p1 is Connected..\n"); 
        ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
        out.writeObject("RSD");
        c.close(); out.close();
        }
        catch(Exception e){
                        System.out.print("Exception - client \n" + e.toString());
        }
    }
    
}
