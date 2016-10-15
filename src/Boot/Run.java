package Boot;

import Controller.MyController;
import Model.MyModel;
import Views.MyServerView;

import java.io.IOException;
import java.util.Random;
// Defined in MyClass

public class Run {
    public static void main(String[] args) throws IOException {
        MyServerView SV = new MyServerView();
        MyModel M = new MyModel();
        MyController MC = new MyController(M, SV);
        M.start();
        SV.start();

    }

}
