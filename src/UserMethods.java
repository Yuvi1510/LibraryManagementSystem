import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMethods {
//    get user by username
    public static User getUserByUsername(Connection connection, String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();

        User user = null;
        while(rs.next()){
            int id = rs.getInt("user_id");
            String name = rs.getString("username");
            String contact = rs.getString("contact");
            int borrowCount = rs.getInt("borrow_count");
            user = new User(name, contact);
            user.setId(id);
            user.setBorrowCount(borrowCount);
        }

        return user;
    }

    // update user
    public static boolean updateUser(Connection connection, User user) throws SQLException {
        String query = "UPDATE users SET username=?, contact=?, borrow_count=? WHERE user_id=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getContact());
        ps.setInt(3, user.getBorrowCount());
        ps.setInt(4, user.getId());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected > 0;
    }

    // insert user
    public static  void insertUser(Connection connection, User user) throws SQLException {

        String insertUserQuery = "INSERT INTO users(username,contact,borrow_count) VALUES(?,?,?);";
        PreparedStatement ps2 = connection.prepareStatement(insertUserQuery);
        ps2.setString(1,user.getUsername());
        ps2.setString(2, user.getContact());
        ps2.setInt(3, user.getBorrowCount());
        if(ps2.executeUpdate() > 0 ){
            System.out.println("User inserted successfully");
        }else{
            System.out.println("Failed to insert user");
        }
    }

    // select user
    public static List<User> selectUsers(Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();
        String selectBook = "SELECT * FROM users";
        PreparedStatement ps = connection.prepareStatement(selectBook);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int id = rs.getInt("user_id");
            String username = rs.getString("username");
            String contact = rs.getString("contact");
            int borrowCount = rs.getInt("borrow_count");
            User user = new User(username, contact);
            user.setBorrowCount(borrowCount);
            user.setId(id);
            users.add(user);
        }

        return users;
    }


    public static void deleteUser(Connection connection, Scanner input) throws SQLException {
        System.out.println("Enter username: ");
        String username = input.nextLine();

        String query = "DELETE FROM users WHERE username=?;";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);

        if(ps.executeUpdate() > 0){
            System.out.println("User deleted successfully!");
        }else {
            System.out.println("Failed to delete user!");
        }
    }

    // add user
    public static void addUser(Connection connection, Scanner input){
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter contact: ");
        String contact = input.nextLine();

        User user = new User(username, contact);

        try{
            UserMethods.insertUser(connection, user);
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
