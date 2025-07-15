import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RecordMethods {
    // insert records
    public static void insertRecord(Connection connection, int userId, int bookId) throws SQLException {
        String insertRecordQuery = "INSERT INTO `borrow_records`(user_id, book_id, date, return_status) VALUES (?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(insertRecordQuery);
        ps.setInt(1, userId);
        ps.setInt(2, bookId);
        ps.setDate(3, Date.valueOf(LocalDate.now()));
        ps.setBoolean(4, false);

        int rowsAffected = ps.executeUpdate();

        if(rowsAffected > 0){
            System.out.println("Record added successfully");
        }else{
            System.out.println("Failed to add record");
        }
    }

    //update return status
    public static void updateReturnStatus(Connection connection, int userId, int bookId) throws SQLException{
        String updateReturnStatusQuery = "UPDATE borrow_records SET return_status=true WHERE user_id=? AND book_id=?;";
        PreparedStatement ps = connection.prepareStatement(updateReturnStatusQuery);
        ps.setInt(1,userId);
        ps.setInt(2,bookId);

        int rowsAffected = ps.executeUpdate();

        if(rowsAffected > 0){
            System.out.println("Book quantity updated successfully");
        }else{
            System.out.println("Failed to update book quantity");
        }

    }


}
