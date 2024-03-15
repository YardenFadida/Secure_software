
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;






public class login_screen implements ActionListener {
	static JFrame frame;
	static JPanel login_page;
	static JTextArea title, alert;
	static JTextField userName;
	static JPasswordField pass;
	static JButton login_btn,register_btn,cngPass_btn;
	static Connection con;

	public login_screen() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		frame = new JFrame("Yarden application");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
		login_page = new JPanel();
		frame.setContentPane(login_page);
		placeComponentsLogin(login_page);
		frame.setVisible(true);
		login_btn.requestFocusInWindow();

	}
	public static void main(JFrame f) {
		frame=f;
		login_page = new JPanel();
		frame.setContentPane(login_page);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponentsLogin(login_page);
		frame.setVisible(true);   
		
	}
	
	private static void placeComponentsLogin(JPanel panel){
		login_screen actionTrigger= new login_screen(); // to define buttons action.
		panel.setLayout(null);
		
		title = new JTextArea("Login Page ");
		title.setBounds(240,0,110,30);
		Font labelFont = title.getFont();
        title.setEditable(false);
        title.setOpaque(false);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		
		JLabel titleUser = new JLabel("Username");
		titleUser.setBounds(165,30,300,30);
		userName = new JTextField(null);
		userName.setBounds(160,50,300,30);
		
		
		JLabel titlePass = new JLabel("Password");
		titlePass.setBounds(165,80,300,30);
		pass = new JPasswordField(null);
		pass.setBounds(160,100,300,30);

		
		login_btn = new JButton("Log In");
		login_btn.addActionListener(actionTrigger);
		login_btn.setBounds(240,150,110,30);
		
		register_btn = new JButton("Sign up");
		register_btn.addActionListener(actionTrigger);
		register_btn.setBounds(30,300,110,30);
		
		cngPass_btn = new JButton("Change Password");
		cngPass_btn.addActionListener(actionTrigger);
		cngPass_btn.setBounds(420,300,150,30);
		
		
		
		alert = new JTextArea("");
		alert.setBounds(160,200,300,30);
		Font labelFont2 = alert.getFont();
		alert.setEditable(false);
		alert.setOpaque(false);
		alert.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));
		
		
		panel.add(title);
		
		panel.add(titleUser);
		panel.add(userName);
		
		panel.add(titlePass);
		panel.add(pass);
		
		panel.add(login_btn);
		panel.add(alert);
		
		panel.add(register_btn);
		
		panel.add(cngPass_btn);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == login_btn) {
				if(userName.getText().length() > 3 && pass.getText().length() > 4) { 
					int prev = utilities.loginCheck(userName.getText(), pass.getText());
					if(prev == -1){ //error
						alert.setText("Something wrong try again");
					}
					else if(prev != 1) { // user logged in
				        	home_screen.main(frame);
				    }
					else if(prev == 1) { // admin logged in
						home_screen_admin.main(frame);
				    }    	
				}
				else
			        alert.setText("Something wrong with cred");
		}
		else if(event.getSource() == register_btn) {
			register_screen.main(frame);
		}
		else if(event.getSource() == cngPass_btn) {
			resetPass_screen.main(frame);
		}
		else
			alert.setText("Something wrong");
	}
		
	

}
