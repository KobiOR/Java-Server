package Boot;
import java.io.*;


import static java.lang.Thread.sleep;

public class ClientHandler implements Runnable{
    private Client c;

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status=true;

    public ClientHandler(Client cli) {
        this.c = cli;
    }
    private int solveExercise(MathExercise ex) {
        switch (ex.getOp()) {
            case "+":
                return ex.getNum1() + ex.getNum2();
            case "-":
                return ex.getNum1() - ex.getNum2();
            case "*":
                return ex.getNum1() * ex.getNum2();
            case "/":
                return ex.getNum1() / ex.getNum2();
            default:
                return Integer.MAX_VALUE;
        }
    }
    @Override
    public void run() {

        while (c.getuSocket().isBound())
        {
            PrintWriter pw = null;
            String str;
            try {
                pw = new PrintWriter(c.getuSocket().getOutputStream(), true);
                if (pw==null){sleep(1000);continue;}
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getuSocket().getInputStream()));
                if (c.getuSocket().isBound())c.setName(br.readLine());
                else break;
                sleep(1500);

            } catch (IOException e) {
                status=false;
                return;

            } catch (InterruptedException e) {
                status=false;
                return;
            }
        }

        c.getuRequest().setExit(true);
        status=false;
        return;


    }
    public boolean isStatus() {
        return status;
    }
    public Client getC() {
        return c;
    }
}

