package Boot;

import java.net.Socket;

/**
 * Created by orrko_000 on 15/10/2016.
 */
public class Client {


    private String name;
    private Socket uSocket;
    private Request uRequest;

    private Client(){}
    public String getName() {
        return name;
    }
    public Socket getuSocket() {
        return uSocket;
    }
    public Client(Socket uSocket) {
        this.uSocket = uSocket;
        uRequest=new Request();
    }
    public Request getuRequest() {
        return uRequest;
    }
    public void setuRequest(Request uRequest) {
        this.uRequest = uRequest;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Client))
            return false;
        if (((Client) obj).getName().equals(this.name) && ((Client) obj).getuSocket().equals(this.uSocket))
            return true;
        return false;

    }
}
