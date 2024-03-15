import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class register_screen implements ActionListener{
	static JFrame f;
	static JPanel register_page;
	static JTextArea title, alert;
	static JTextField userName;
	static JPasswordField pass;
	static JButton signup_btn, back_btn;

	
	public register_screen() {
		
	}
	public static void main(JFrame frame) {
		f=frame;
		register_page = new JPanel();
		frame.setContentPane(register_page);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponentsLogin(register_page);
		frame.setVisible(true);   
		
	}
	
	private static void placeComponentsLogin(JPanel panel){
		register_screen actionTrigger= new register_screen(); // to define buttons action.	
		panel.setLayout(null);
		
		title = new JTextArea("Sign up Page");
		title.setBounds(240,0,130,30);
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

		signup_btn = new JButton("Sign up");
		signup_btn.addActionListener(actionTrigger);
		signup_btn.setBounds(240,150,110,30);
		
		back_btn= new JButton("Back");
		back_btn.addActionListener(actionTrigger);
		back_btn.setBounds(30,300,110,30);
		
		

		alert = new JTextArea("");
		alert.setBounds(50,200,500,30);
		Font labelFont2 = alert.getFont();
		alert.setEditable(false);
		alert.setOpaque(false);
		alert.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));
		

		panel.add(title);
		
		panel.add(titleUser);
		panel.add(userName);
		
		panel.add(titlePass);
		panel.add(pass);
		
		panel.add(alert);
		
		panel.add(signup_btn);
		panel.add(back_btn);
	}
	
	private void checkRegister(String username, String pass) {
			if(username.length() >= 5) {
				if(pass.length() >= 8 && utilities.hasSpecialCharacter(pass)) {
					if (utilities.registerClient(URLEncoder.encode(username), URLEncoder.encode(pass)) == true) {
						login_screen.main(f);
					}
					else {
						alert.setText("Unvalid username or password, try again.");
					}
				}
				else {
					alert.setText("Password must contain at least 8 characters and special characters.");
				}
			}
			else {
				alert.setText("Username must contain at least 5 characters.");
			}	
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signup_btn) {
			checkRegister(userName.getText(), pass.getText());
		}
		else if(e.getSource() == back_btn) {	
			login_screen.main(f);
		}
	}

}
