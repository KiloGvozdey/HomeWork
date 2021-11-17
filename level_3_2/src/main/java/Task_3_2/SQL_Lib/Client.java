package Task_3_2.SQL_Lib;

public class Client {
    private final long id;
    private final String userName;
    private final String login;
    private final String password;


    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Client(long id, String userName, String login, String password) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public Client(String userName, String login, String password){
        this(0L, userName, login, password);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
