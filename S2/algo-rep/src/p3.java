import java.io.ObjectOutputStream;
import java.net.Socket;

public class p3 {

    public static void main(String[] args) {
        try{
            
        System.out.print("p3 is Requestin access\n"); 
        Socket c = new Socket("localhost", 2004);
        System.out.print("p3 is Connected..\n"); 
        ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
        out.writeObject("IL");
        c.close(); out.close();

        }
        catch(Exception e){
            System.out.print("exception p3 \n " + e.toString() );
        }
    }
    
}
