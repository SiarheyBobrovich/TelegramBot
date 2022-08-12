package by.bobrovich.telegram.bot.dao.entity;

public class User {

    private long id;
    private String city;

    public User(long id, String city) {
        this.id = id;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
