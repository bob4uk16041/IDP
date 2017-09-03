package observable.observable;

import java.util.Date;

public class News {

    private String name = null;
    private Date date = null;
    private String description = null;

    public News(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!name.equals(news.name)) return false;
        if (!date.equals(news.date)) return false;
        return description.equals(news.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
