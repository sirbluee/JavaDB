import model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "1023";

        try (Connection connection = DriverManager.getConnection(url, username, password)){
            //1. create sql statement object
            String sql = "SELECT*FROM topic";
            PreparedStatement statement =connection.prepareStatement(sql);
            //2. Execute sql statement
            ResultSet resultSet = statement.executeQuery();
            //3. Process result with resultSet
            List<Topic>topics = new ArrayList<>();
            while (resultSet.next()){
                Integer id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String description = resultSet.getString("description");
                Boolean status = resultSet.getBoolean("status");
                topics.add(new Topic(id,name,description,status));
            }
            for (Topic topic: topics){
                System.out.println(topic);
            }
            System.out.println(connection.getSchema());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
