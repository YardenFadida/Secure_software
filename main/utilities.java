
import java.awt.Component;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;




public class utilities {
	private static Connection con;
	protected static String name=null;

	public utilities() {
		
	}
	
	
	protected static void resetFields(JPanel p) {
        Component[] components = p.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
	  }
		
	

	protected static boolean establishConnection() {
		try {
			String jdbcUrl = DataBaseConfig.getJdbcUrl();
	        String username = DataBaseConfig.getUsername();
	        String password = DataBaseConfig.getPassword();//in real life software case the SQL cred will be exclusively stored on the server side.
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        con = DriverManager.getConnection(jdbcUrl, username, password);
	        System.out.println("Connected to the database!");
	        return true;
	        
		} 
		catch (CommunicationsException e) {
			System.out.println("check the server operation");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
        
	}
	
	//verify login info with database.
	@SuppressWarnings("deprecation")
	protected static int loginCheck(String username, String pass) { 
			String sql = "SELECT password,privilege FROM users WHERE user_id = ?";
			try {
				if(establishConnection()) {
					PreparedStatement preparedStatement = con.prepareStatement(sql);
					String user_id = calculateSHA256(URLEncoder.encode(username)); 
					preparedStatement.setString(1, user_id);
					ResultSet rs = preparedStatement.executeQuery();
					String password = utilities.calculateSHA256(DataBaseConfig.getSalt()+URLEncoder.encode(pass)); 
					if(rs.next() && rs.getString("password").equals(password)) {
						int prev = rs.getInt("privilege");
						name = URLEncoder.encode(username);
						return prev;
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			finally {
				try {
					if (con != null && !con.isClosed())
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return -1;
	}
	
	@SuppressWarnings("deprecation")
	protected static boolean addReview(String name, String r) {
		String sql = "INSERT INTO reviews (name, review) VALUES (?,?)";
		try {
		if(establishConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, URLEncoder.encode(name));
            preparedStatement.setString(2, URLEncoder.encode(r));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
		}
		else
			return false;
        
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

		
	
	@SuppressWarnings("deprecation")
	protected static void fetchReviewsFromDatabase(DefaultListModel<String> list) {
		String sql = "SELECT review FROM reviews";
		try {
			if(establishConnection()) {
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				int i=1;
				while (rs.next()) {
                    String reviewText = i+": "+ rs.getString("review");
                    list.addElement(URLDecoder.decode(reviewText));
                    i++;
                }
			}
				
		}
		catch (Exception e) {
			e.printStackTrace();
				
		}
			finally {
				try {
					if (con != null && !con.isClosed())
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
    }
	
	private static String calculateSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
	
	protected static boolean registerClient(String username, String password){
	        String sql = "INSERT INTO users (user_id, password, privilege) VALUES (?, ?, ?)";
	        try {
	            String user_id = calculateSHA256(username);
		        String pass = calculateSHA256(DataBaseConfig.getSalt()+password);
	    		if(establishConnection()) {
	                PreparedStatement preparedStatement = con.prepareStatement(sql);
	                preparedStatement.setString(1, user_id);
	                preparedStatement.setString(2, pass);
	                preparedStatement.setInt(3, 0);
	                int rowsAffected = preparedStatement.executeUpdate();
	                if (rowsAffected > 0) {
	                    return true;
	                }
	    		}
	            
	    	}
	        catch (SQLIntegrityConstraintViolationException e) { // duplicate username 
	        	e.printStackTrace();
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
			}
	        return false;
	}
	
	protected static boolean resetPassword(String username, String password){
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        try {
    		if(establishConnection()) {
    			String user_id = calculateSHA256(username); 
    			String pass = calculateSHA256(DataBaseConfig.getSalt()+password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, pass);
                preparedStatement.setString(2, user_id);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                }
    		}
    	}
        catch (SQLIntegrityConstraintViolationException e) {
        	e.printStackTrace();
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
        return false;
	}
	
	protected static boolean setAdminPrivliges(String username){
        String sql = "UPDATE users SET privilege = 1 WHERE user_id = ?";
        try {
    		if(establishConnection()) {
    			String user_id = calculateSHA256(username); 
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, user_id);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                } 
    		}
    	}
        catch (SQLIntegrityConstraintViolationException e) {
        	e.printStackTrace();
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
        return false;
	}
	
	 protected static boolean hasSpecialCharacter(String password) {
	        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
	        Matcher matcher = pattern.matcher(password);
	        return matcher.find();
	    }
	 

}
