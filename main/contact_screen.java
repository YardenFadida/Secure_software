import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class contact_screen {
	static Frame frame;
	static JPanel feedbackPage;
	static JTextArea title;
	static TextField review;
	static JTextField userName;
	static JButton login_btn;
	static Connection con;


	public contact_screen() {
		frame = new JFrame("Yarden application");
		frame.setSize(600, 400);
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		feedbackPage = new JPanel();
		frame.add(feedbackPage);
		placeComponentsLogin(feedbackPage);
		frame.setVisible(true);
		login_btn.requestFocusInWindow();
	}
	
	private static void placeComponentsLogin(JPanel panel){
		feedback_screen actionTrigger= new feedback_screen(); // to define buttons action.
		panel.setLayout(null);
		
		title = new JTextArea("Contact us");
		title.setBounds(160,0,330,30);
		Font labelFont = title.getFont();
        title.setEditable(false);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		
		JLabel titleUser = new JLabel("Your email");
		titleUser.setBounds(165,30,300,30);
		userName = new JTextField(null);
		userName.setBounds(160,50,300,30);
		
		
		
		JLabel titlePass = new JLabel("Tell us what you think");
		titlePass.setBounds(165,80,300,30);
		review = new TextField(null);
		review.setBounds(160,120,300,100);
		
		
		login_btn = new JButton("Send");
		login_btn.addActionListener(actionTrigger);
		login_btn.setBounds(240,250,110,30);
		
		
		panel.add(title);
		
		panel.add(titleUser);
		panel.add(userName);
		
		panel.add(titlePass);
		panel.add(review);
		
		panel.add(login_btn);
	}


}
