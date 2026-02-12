import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class p2 { // server

    public static void main(String[] args){

        try{
        System.out.print("Creation de service sure le port 2004 \n");    
        ServerSocket s = new ServerSocket(2004);
        while(true){
        System.out.print("Waiting ....\n"); 
        Socket connection = s.accept();
        System.out.print("Service Accepted ....\n"); 
        ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
        // in.readObject(); ( commented out because we cant read objects , the solution is in the following lines )
        String ch = (String)in.readObject();
        System.out.print("Ch = " + ch + "\n");
        }
        // s.close(); in.close(); connection.close(); // operation ended, commented out cus in a fucntional working system the server shouldnt shut down. so the solution is to implement a loop
        }
        catch(Exception e){
            System.out.print("Exception - server\n");
        }
    }
    
}


// first run p2 ( the server )
// then run p1 ( you should see ch = rsd )
// then run p3 ( you should see ch = il )
