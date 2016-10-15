package Model;

import Boot.Client;
import Boot.MyTcpIpServer;
import Controller.MyController;
import java.net.Socket;
import java.util.*;

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

      /*  TimerTask task = new TimerTask() {

                @Override
                public void run() {


                }
            };
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(task, 0,1000);
*/

        }
    public void push(Client nClient){
        myClients.add(nClient);

    }
    public void remove(Client nClient){
      for (Client c:myClients)
          if (c.equals(nClient))
               myClients.remove(c);

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



