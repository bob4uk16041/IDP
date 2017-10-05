import observable.observable.InternetProvider;
import observable.observable.News;
import observable.observable.NewsManager;
import observable.observable.TvProvider;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObservableTest {

    @Test
    public void NewsManagerTest() {

        NewsManager newsManager = new NewsManager();
        InternetProvider internetProvider = new InternetProvider();
        TvProvider tvProvider = new TvProvider();

        newsManager.registerInfo(internetProvider);

        News n1 = new News("Test1", new Date(), "TestD1");
        News n2 = new News("Test2", new Date(), "TestD2");
        News n3 = new News("Test3", new Date(), "TestD3");
        News n4 = new News("Test4", new Date(), "TestD4");

        newsManager.notifyInfo(n1);

        assertThat(internetProvider.getAllNews().size(), is(1));

        newsManager.registerInfo(tvProvider);
        newsManager.notifyInfo(n2);
        assertThat(internetProvider.getAllNews().size(), is(2));
        assertThat(tvProvider.getAllNews().size(), is(1));

        newsManager.removeInfo(internetProvider);
        newsManager.notifyInfo(n3);
        newsManager.notifyInfo(n4);

        assertThat(internetProvider.getAllNews().size(), is(2));
        assertThat(tvProvider.getAllNews().size(), is(3));

        assertThat(internetProvider.getAllNews().get(1).getName(), is(n2.getName()));
        assertThat(tvProvider.getAllNews().get(2).getName(), is(n4.getName()));

    }

}
