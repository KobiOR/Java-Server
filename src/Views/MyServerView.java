package Views;

import Boot.Client;
import Controller.MyController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;

/**
 * Created by orrko_000 on 14/10/2016.
 */
public class MyServerView extends BasicWindow {
    MyController C;
    List userslist,noteList;
    Queue<Client> myClients;

    public void setC(MyController c) {
        C = c;
    }
    @Override
    protected void initWidgets() {
        shell.setLayout (new FillLayout());

        SashForm A = new SashForm(shell,SWT.HORIZONTAL);
        A.setLayout(new FillLayout());
       // A.setEnabled(false);

        SashForm AB = new SashForm(A,SWT.HORIZONTAL);
        AB.setLayout(new FillLayout());

        SashForm AC = new SashForm(A,SWT.VERTICAL);
        AC.setLayout(new FillLayout());

        Composite child1 = new Composite(AB,SWT.BORDER);
        child1.setLayout(new RowLayout(SWT.BORDER));

        userslist = new List(AC,SWT.BORDER);
        noteList = new List(AC,SWT.BORDER);

        Button bKick = new Button(child1, SWT.PUSH);
        bKick.setText("Kick");
        bKick.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String[] s = userslist.getSelection();
                if (s != null && s.length>0)
                    if (!s[0].isEmpty() && s[0] != "")
                        C.getM().removeUserByName(s);

            }
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                // TODO Auto-generated method stub

            }
        });

        Button bExit = new Button(child1, SWT.PUSH);
        bExit.setText("Exit");
        bExit.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                shell.close();
                exit(1);
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
                        shell.close();
                        exit(1);

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
                        userslist.removeAll();
                        for (Client c : myClients)
                            if(c.getName()!=null)userslist.add(c.getName());

                    }
                });
            }
        }.start();
    }
    public void display(String str){
        display.asyncExec(new Runnable() {

            @Override
            public void run() {
                noteList.add(str);
            }
        });

    }
    public void setMyClients(Queue<Client> m){
      myClients = m;

    }

}

