package Views;

import Boot.Client;
import Controller.MyController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by orrko_000 on 14/10/2016.
 */
public class MyServerView extends BasicWindow {
    MyController C;
    List list;
    Queue<Client> myClients;

    public void setC(MyController c) {
        C = c;
    }
    @Override
    protected void initWidgets() {

        shell.setLayout(new GridLayout(2, false));
        Composite buttonsPos = new Composite(shell,SWT.NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        buttonsPos.setLayout(rowLayout);

        Composite listPos = new Composite(shell,SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        RowLayout asd = new RowLayout(SWT.VERTICAL);
        listPos.setLayout(asd);

        list = new List(listPos,SWT.BORDER );
        list.setBounds(650,650,400,400);

        Button bExit = new Button(buttonsPos, SWT.PUSH);
        bExit.setText("Exit");
        bExit.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String[] s=list.getSelection();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                // TODO Auto-generated method stub

            }
        });

        Button aa = new Button(buttonsPos, SWT.PUSH);
        aa.setText("Kick");
        aa.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String[] s=list.getSelection();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                // TODO Auto-generated method stub

            }
        });

        shell.addListener(SWT.Close, new Listener(){
            public void handleEvent(Event event) {
                display.asyncExec(new Runnable() {
                    @Override
                    public void run() {

                    }
                });


            }
        });

    }
    @Override
    public synchronized void run() {

        new Thread()
        {
            public synchronized void run() {
                display.asyncExec(new Runnable() {
                    @Override
                    public synchronized void run() {
                        list.removeAll();
                        for (Client c : myClients)
                            if(c.getName()!=null)list.add(c.getName());

                    }
                });
            }
        }.start();
    }
    public void display(String str){
        display.asyncExec(new Runnable() {

            @Override
            public void run() {
                System.out.print(str+ " connected");
                list.add(str);
                shell.redraw();
            }
        });

    }
    public void setMyClients(Queue<Client> m){
      myClients = m;

    }

}

