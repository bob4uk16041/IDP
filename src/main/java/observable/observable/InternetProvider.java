package observable.observable;

import java.util.Observable;
import java.util.Observer;

public class InternetProvider implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (true) {
            News news = (News) arg;
            System.out.println("InternetProvider: " + news.toString());
        }
    }
}
