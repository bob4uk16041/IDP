package observable.observable;



import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class NewsManager extends Observable {
    private LinkedList<Observer> observers = new LinkedList<>();

    public void registerInfo(Observer o) {
        observers.add(o);
    }

    public void removeInfo(Observer o) {
        observers.removeIf(item -> {
            return item.equals(o);
        });
    }

    public void notifyInfo(News news) {
        observers.forEach(iten -> {
            iten.update(this, news);
        });
    }
}
