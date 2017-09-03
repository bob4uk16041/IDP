package observable.observable;

import java.util.Observable;
import java.util.Observer;

public class TvProvider implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        News news = (News) arg;
        System.out.println("TvProvider: " + news.toString());
    }
}
