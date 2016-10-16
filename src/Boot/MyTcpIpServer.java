package Boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

public class MyTcpIpServer extends Observable {

    Set<ClientHandler> callables = new HashSet<>();
    private ServerSocket server;
    private ExecutorService executor;

    public MyTcpIpServer(int port) {

        try {
            server = new ServerSocket(port);
            check();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Cannot listen on port " + port);
        }
    }
    public void startServer(int maxClientsNum) {
        executor = Executors.newFixedThreadPool(maxClientsNum);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        String str;
                        Socket socket = server.accept();
                        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                ClientHandler handler = new ClientHandler(new Client(socket));
                                setChanged();
                                notifyObservers(handler.getC());
                                callables.add(handler);
                                handler.run();

                            }

                        });
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }
    public synchronized void check() {
        Thread thread = new Thread(new Runnable() {
            public void run()
            {
                while(true)
                {
                    if (!callables.isEmpty())
                        for (ClientHandler c : callables) {
                            if (!c.isStatus()) {
                                c.getC().getuRequest().setExit(true);
                                setChanged();
                                notifyObservers(c.getC());
                            }
                        }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }});thread.start();



    }
}



