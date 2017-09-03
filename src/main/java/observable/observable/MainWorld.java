package observable.observable;

import java.util.Date;

public class MainWorld {

    public static void main(String [] args) {
        NewsManager newsManager = new NewsManager();
        InternetProvider internetProvider = new InternetProvider();
        TvProvider tvProvider = new TvProvider();


        newsManager.registerInfo(internetProvider);
        newsManager.registerInfo(tvProvider);

        News n1 = new News("Global war", new Date(), "in global war anything will be destroy");
        News n2 = new News("J. Robert Oppenheimer", new Date(), "I am become Death, the destroyer of worlds");
        News n3 = new News("Wernher von Braun", new Date(), "Apollo program director Sam Phillips was quoted as saying that he did not think that the United States would have reached the Moon as quickly as it did without von Braun's help. Later, after discussing it with colleagues, he amended this to say that he did not believe the United States would have reached the Moon at all.");
        newsManager.notifyInfo(n1);
        newsManager.notifyInfo(n2);
        newsManager.notifyInfo(n3);



    }
}
