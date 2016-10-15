package Controller;

import Boot.MyTcpIpServer;
import Model.MyModel;
import Views.MyServerView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by orrko_000 on 14/10/2016.
 */
public class MyController {

    MyModel M;
    MyServerView V;


    public MyModel getM() {
        return M;
    }
    public MyServerView getV() {
        return V;
    }
    public MyController(MyModel m, MyServerView v) {
        M = m;
        V = v;
        M.setC(this);
        V.setC(this);

    }



}
