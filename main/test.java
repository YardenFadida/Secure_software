import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}
//	public static void main(String[] args) throws Exception {
//		System.out.println(calculateSHA256("password"));
//	}
//	
//	public static String calculateSHA256(String input) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        byte[] hash = md.digest(input.getBytes());
//
//        StringBuilder hexString = new StringBuilder();
//        for (byte b : hash) {
//            String hex = Integer.toHexString(0xff & b);
//            if (hex.length() == 1) {
//                hexString.append('0');
//            }
//            hexString.append(hex);
//        }
//
//        return hexString.toString();
//    }

	/*public static void main(String[] args) throws ClassNotFoundException {
		String jdbcUrl = "jdbc:mysql://localhost:3306/application";
        String username = "root";
        String password = "0525889494";
        
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to the database!");
            insertData(connection);
            showMyFans(connection);


        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }

	}*/
	
	private static void insertData(Connection c) {
		// Execute the INSERT statement
		try {
        String sql = "INSERT INTO users (user_id,userName) VALUES(?,?)";
        PreparedStatement preparedStmt = c.prepareStatement(sql);
        preparedStmt.setString (1, "1234" );
        preparedStmt.setString (2, "world");
       // preparedStmt.setString(3, password.getText());
        preparedStmt.execute();
        System.out.println("Executed");
		} 
		catch (Exception e) {
			e.printStackTrace();
    	} 
	}
	
	public static void showMyFans(Connection c) {
		String sql = "SELECT * FROM users";
		try {
        PreparedStatement preparedStmt = c.prepareStatement(sql);
		ResultSet rs = preparedStmt.executeQuery();

        // Loop through the result set
		String str="";
        while (rs.next()) {
           str+="Name ID: "+rs.getString("user_id") + "  , User Name: " + rs.getString("userName")+"\n";
           System.out.println(str);
           
        }
     /* Clear the frame and present all the fans on the screen.
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        title.setBounds(0, 0, 590, 400);
        title.setText(str);
        title.setEditable(false);
        panel.add(title);*/
		}
		catch (Exception e) {
	        e.printStackTrace();

		}
	}
	

}
