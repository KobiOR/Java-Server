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
      for (Client c:myClients)
          if (c.equals(nClient))
               myClients.remove(c);
        C.getV().run();

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



    }



