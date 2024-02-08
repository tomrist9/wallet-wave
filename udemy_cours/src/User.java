public class User {
    private String name;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User:" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' ;
    }
}
