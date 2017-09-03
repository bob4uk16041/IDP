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
}
