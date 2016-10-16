package Model;

import Boot.Client;
import Boot.MyTcpIpServer;
import Controller.MyController;
import java.net.Socket;
import java.util.*;

import static java.lang.Thread.sleep;

/**
 * Created by orrko_000 on 14/10/2016.
 */
public class MyModel implements Observer {
    MyController C;
    Queue<Client> myClients;

    public void setC(MyController c) {
        C = c;
    }
    public void start(){
        MyTcpIpServer server = new MyTcpIpServer(9000);
        server.addObserver(this);
        server.startServer(50);
        C.getV().setMyClients(myClients=new LinkedList<>());

        }
    public synchronized void push(Client nClient)   {
        myClients.add(nClient);
        C.getV().run();

    }
    public synchronized void remove(Client nClient){
        Iterator<Client> iter = myClients.iterator();
        while (iter.hasNext())
            if (nClient.equals(iter.next()))
            {iter.remove();myClients.remove(iter);}
        C.getV().run();
        C.getV().display(nClient.getName()+" is left");
    }
    @Override
    public void update(Observable o, Object arg) {
         if (arg.getClass().getName() == "Boot.Client") {
                    if (!myClients.contains((Client) arg))
                        push((Client) arg);
                    if (((Client) arg).getuRequest().isExit())
                        remove((Client) arg);
                    if (((Client) arg).getuRequest().isWantSolu()) {
                    }

                }

            }
    public synchronized void removeUserByName(String[] s) {
             Iterator<Client> iter = myClients.iterator();

            while (iter.hasNext()) {
                Client t=iter.next();
                String str = t.getName();
                if (s[0].equals(str))
                    remove(t);
            }
        }


}



