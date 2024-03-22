import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class resetPass_screen implements ActionListener{
	protected static JFrame f;
	protected static JPanel reset_page;
	protected static JTextArea title, alert;
	protected static JTextField userName;
	protected static JPasswordField pass, confirm;
	protected static JButton reset_btn, back_btn;

	
	public resetPass_screen() {
		
	}
	public static void main(JFrame frame) {
		f=frame;
		reset_page = new JPanel();
		frame.setContentPane(reset_page);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponentsLogin(reset_page);
		frame.setVisible(true);   
		JOptionPane.showMessageDialog(f, "This feature is for functionality purpose only. (no security)", "Warning", JOptionPane.WARNING_MESSAGE);
		
	}
	
	private static void placeComponentsLogin(JPanel panel){
		resetPass_screen actionTrigger= new resetPass_screen(); // to define buttons action.	
		panel.setLayout(null);
		
		title = new JTextArea("Reset Password Page");
		title.setBounds(200,0,200,30);
		Font labelFont = title.getFont();
        title.setEditable(false);
        title.setOpaque(false);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));

		JLabel titleUser = new JLabel("Username");
		titleUser.setBounds(165,30,300,30);
		userName = new JTextField(null);
		userName.setBounds(160,50,300,30);
		
		
		JLabel titlePass = new JLabel("New Password");
		titlePass.setBounds(165,80,300,30);
		pass = new JPasswordField(null);
		pass.setBounds(160,100,300,30);
		
		JLabel titleConfirm = new JLabel("Confirm password");
		titleConfirm.setBounds(165,120,300,30);
		confirm = new JPasswordField(null);
		confirm.setBounds(160,140,300,30);

		reset_btn = new JButton("Reset Password");
		reset_btn.addActionListener(actionTrigger);
		reset_btn.setBounds(240,200,110,50);
		
		back_btn= new JButton("Back");
		back_btn.addActionListener(actionTrigger);
		back_btn.setBounds(30,300,110,30);
		
		

		alert = new JTextArea("This feature is for functionality purpose only. (no security)");
		alert.setBounds(50,250,500,30);
		Font labelFont2 = alert.getFont();
		alert.setEditable(false);
		alert.setOpaque(false);
		alert.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));
		

		panel.add(title);
		
		panel.add(titleUser);
		panel.add(userName);
		
		panel.add(titlePass);
		panel.add(pass);
		
		panel.add(titleConfirm);
		panel.add(confirm);
		
		panel.add(alert);
		
		panel.add(reset_btn);
		panel.add(back_btn);
	}
	
	@SuppressWarnings("deprecation")
	private void checkReset(String username, String pass, String confirmPass) {
		if(!username.isEmpty() && !pass.isEmpty()) {
			if(pass.length() >= 8 && utilities.hasSpecialCharacter(pass)) {
				if(pass.equals(confirmPass)) {
						if (utilities.resetPassword(URLEncoder.encode(username), URLEncoder.encode(pass)) == true) {
							login_screen.main(f);
						}
						else {
							alert.setText("Something went wrong. The username does not exits");
							utilities.resetFields(reset_page);
						}
				}
				else {
					alert.setText("Passwords must match.");
				}
			}
			else {
				alert.setText("Password must contain at least 8 characters and one special character.");
			}
		}
		else {
			alert.setText("Please fill valid username and password");
		}
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reset_btn) {
			checkReset(userName.getText(), pass.getText(), confirm.getText());
		}
		else if(e.getSource() == back_btn) {	
			login_screen.main(f);
		}
	}

}
