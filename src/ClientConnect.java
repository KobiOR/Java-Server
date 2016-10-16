import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import static java.lang.Thread.sleep;


public class ClientConnect {

    public static void main(String args[]) throws IOException, InterruptedException {


                Socket socket = new Socket(InetAddress.getLocalHost(), 9000);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println("A" + new Random().nextInt(1000));
                while(true)sleep(2000);


    }
}
