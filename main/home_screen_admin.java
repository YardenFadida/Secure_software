import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class home_screen_admin implements ActionListener {
	protected static JFrame f;
	protected static JPanel home_screen_admin;
	protected static JTextArea title,reviewTitle,usernameTitle;
	protected static JTextField userName;
	protected static JButton logout_btn,set_btn;
	protected static JList<String> reviewList;
	protected static DefaultListModel<String> listModel;
	protected static JScrollPane scrollPane;




	public home_screen_admin() {

	}
	
	public static void main(JFrame frame) {
		f=frame;
		home_screen_admin = new JPanel();
		frame.setContentPane(home_screen_admin);
		placeComponentsLogin(home_screen_admin);
	    scrollPane = new JScrollPane(reviewList);
	    scrollPane.setBounds(10,60,590,150);
	    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);   

	}
	
	private static void placeComponentsLogin(JPanel panel){
		home_screen_admin actionTrigger= new home_screen_admin(); 
		panel.setLayout(null);
		
		title = new JTextArea("Home page-Admin");
		title.setBounds(180,0,250,30);
		Font labelFont = title.getFont();
        title.setEditable(false);
        title.setOpaque(false);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		
		logout_btn = new JButton("Log out");
		logout_btn.addActionListener(actionTrigger);
		logout_btn.setBounds(240,330,110,30);
		
		reviewTitle = new JTextArea("Our reviews");
		reviewTitle.setBounds(240,35,250,30);
        reviewTitle.setEditable(false);
        reviewTitle.setOpaque(false);
		reviewTitle.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
		
		usernameTitle = new JTextArea("Set registered User- Admin privileges");
		usernameTitle.setBounds(140,210,300,30);
		usernameTitle.setEditable(false);
		usernameTitle.setOpaque(false);
		usernameTitle.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
		
		JLabel titleUser = new JLabel("Username");
		titleUser.setBounds(140,230,300,30);
		userName = new JTextField(null);
		userName.setBounds(135,250,300,30);
		
		set_btn = new JButton("Set");
		set_btn.addActionListener(actionTrigger);
		set_btn.setBounds(440,250,110,30);
		

		listModel = new DefaultListModel<>();
	    utilities.fetchReviewsFromDatabase(listModel);
	    reviewList = new JList<>(listModel);
	    reviewList.setFont(new Font(labelFont.getName(), Font.PLAIN, 16));
	    
		
		panel.add(title);
		panel.add(reviewList);
		panel.add(reviewTitle);
		panel.add(usernameTitle);
		panel.add(titleUser);
		panel.add(userName);
		panel.add(set_btn);
		panel.add(logout_btn);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout_btn) {
			f.dispose();
			login_screen.main(f);
		}
		else if(e.getSource() == set_btn) {
			if(utilities.setAdminPrivliges(userName.getText())) {
				JOptionPane.showMessageDialog(f, userName.getText() +" is an Admin now", "Result", JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(f, "The user does not exist, try again", "Result", JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	
}
