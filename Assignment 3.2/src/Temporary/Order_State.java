package Temporary;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Using the Observer Pattern to get the GUI to respond to the change in the variable
 * order_state
 *
 * The implementation is in 3 parts
 * 1st create a class encapsulating the state variable (this is the observable)
 * 2nd create a class which becomes the observer
 * 3rd in the main ... create the state variable (which is now observable)
 *      ... create an observer
 *      ... finally add this observer to the observable.
 */

public class Order_State extends Observable {
    AtomicInteger prevState = new AtomicInteger(-2);
    AtomicInteger state = new AtomicInteger(-2);
    // constants
    AtomicInteger NO_ORDER = new AtomicInteger(-2);
    AtomicInteger BEFORE = new AtomicInteger(-1);
    AtomicInteger COOKING = new AtomicInteger(0);
    AtomicInteger FINISHED = new AtomicInteger(1);
    AtomicInteger DELIVERED = new AtomicInteger(2);
    AtomicInteger EXIT = new AtomicInteger(10);

    public void setState(AtomicInteger state) {
        synchronized (this) {
            this.prevState = this.state;
            this.state = state;
        }
        setChanged();
        System.out.println("Changing State from : " + toString(prevState) + " to " + toString(this.state) + " -- ");
        notifyObservers();
    }

    public boolean equals(AtomicInteger newState) {
        return(state.get() == newState.get());
    }

    public synchronized AtomicInteger getState() {
        return state;
    }

    public synchronized AtomicInteger getPrevState() {
        return prevState;
    }

    public String toString(AtomicInteger state) {
        String str = "";
        if (state.get() == NO_ORDER.get())  str = "NO_ORDER";
        if (state.get() == BEFORE.get())    str = "BEFORE";
        if (state.get() == COOKING.get())   str = "COOKING";
        if (state.get() == FINISHED.get())  str = "FINISHED";
        if (state.get() == DELIVERED.get()) str = "DELIVERED";
        if (state.get() == EXIT.get())      str = "EXIT";
        return str;
    }
}

class GuiObserver implements Observer {
    MainWindow mainWindow;

    public GuiObserver(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public void observe(Observable o) {
        o.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        AtomicInteger state  = ((Order_State) o).getState();
        AtomicInteger prevState  = ((Order_State) o).getPrevState();
        mainWindow.changeMainWindowState();
        mainWindow.repaint();
    }
}

