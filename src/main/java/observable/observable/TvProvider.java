package observable.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TvProvider implements Observer {

    private List<News> newsHistoryList = new ArrayList<>();

    public List<News> getAllNews() {
        return newsHistoryList;
    }

    public void clearHistoryNews() {
        newsHistoryList = new ArrayList<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        News news = (News) arg;
        System.out.println("TvProvider: " + news.toString());
        newsHistoryList.add(news);
    }

}
