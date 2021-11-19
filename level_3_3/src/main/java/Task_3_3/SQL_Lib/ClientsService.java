package Task_3_3.SQL_Lib;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ClientsService {
    public static final  ClientsService SINGLETON_SERVICE = new ClientsService();

    private ClientsService() {
    }

    public List<Client> findAll(){
        Connection connection = DBConnector.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM clients");
            List<Client> clients = new ArrayList<>();
            while (rs.next()){
                clients.add(new Client(
                        rs.getLong("id"),
                        rs.getString("user_name"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
            return clients;
        } catch (SQLException e){
            throw new RuntimeException("SWW during a showing table", e);
        } finally {
            DBConnector.close(connection);
        }
    }
    public void save(Client client){
        Connection connection = DBConnector.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clients (user_name, login, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, client.getUserName());
            preparedStatement.setString(2, client.getLogin());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            DBConnector.rollback(connection);
            throw new RuntimeException("SWW during a adding clients", e);
        } finally {
            DBConnector.close(connection);
        }
    }

    public void update(Client client) {
        Connection connection = DBConnector.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE clients SET user_name = ?, login = ?, password = ? WHERE id = ?"
            );
            preparedStatement.setString(1, client.getUserName());
            preparedStatement.setString(2, client.getLogin());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setLong(4, client.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DBConnector.rollback(connection);
            throw new RuntimeException("SWW during an updating operation.", e);
        } finally {
            DBConnector.close(connection);
        }
    }


    public void delete (long id){
        Connection connection = DBConnector.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM clients WHERE id = ?"
            );
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            DBConnector.rollback(connection);
            throw new RuntimeException("SWW during a deleting clients", e);
        } finally {
            DBConnector.close(connection);
        }
    }
    public synchronized Client authentication(String login, String password){
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, user_name FROM clients WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            long id = 0;
            String userName = null;

            while (resultSet.next()){
                id = resultSet.getLong("id");
                userName = resultSet.getString("user_name");
            }
           if(userName != null) return new Client(id, userName, login, password);

           else return null;

        } catch (SQLException e){
            throw new RuntimeException("SWW during authentication client.", e);
        } finally {
            DBConnector.close(connection);
        }
    }

    public String updateName(Client client, String message){
        Connection connection = DBConnector.getConnection();
        String[] strings = message.split("\\s");
        String newUsername = strings[1];

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE clients SET user_name = ? WHERE id = ?"
            );
            preparedStatement.setString(1, newUsername);
            preparedStatement.setLong(2, client.getId());


            preparedStatement.executeUpdate();
            connection.commit();
            return newUsername;
        } catch (SQLException e) {
            DBConnector.rollback(connection);
            throw new RuntimeException("SWW during an updating operation.", e);
        } finally {
            DBConnector.close(connection);
        }
    }

}
