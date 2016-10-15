package Boot;

/**
 * Created by orrko_000 on 15/10/2016.
 */
public class Request {

    private boolean exit;
    private boolean wantSolu;
    //Solution

    public Request() {
        this.exit =false;
        this.wantSolu=false;
    }
    public boolean isExit() {
        return exit;
    }
    public void setExit(boolean exit) {
        this.exit = exit;
    }
    public boolean isWantSolu() {
        return wantSolu;
    }
    public void setWantSolu(boolean wantSolu) {
        this.wantSolu = wantSolu;
    }
}
