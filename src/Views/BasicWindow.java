package Views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;

public abstract class BasicWindow implements Runnable {
    protected Display display;
    protected Shell shell;

    protected abstract void initWidgets();

    public void start() throws IOException {
        display = new Display();
        shell = new Shell(display);

        initWidgets();
        shell.setSize(500,300);
        shell.open();

        // main event loop
        while (!shell.isDisposed()) { // window isn't closed
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
    public Display getDisplay()
    {
        return this.display;
    }
}

