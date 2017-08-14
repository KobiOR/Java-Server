package Controller;

import Model.MyModel;


/**
 * Created by orrko_000 on 14/10/2016.
 */
public class MyController {

    MyModel M;


    public MyModel getM() {
        return M;
    }
    public MyController(MyModel m) {
        M = m;
        M.setC(this);

    }



}
