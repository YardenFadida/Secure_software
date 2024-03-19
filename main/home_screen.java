import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class home_screen implements ActionListener {
	static JFrame f;
	static JPanel home_screen;
	static JTextArea title,reviewTitle;
	static JButton review_btn, logout_btn;
	static JList<String> reviewList;
	static DefaultListModel<String> listModel;
	static JScrollPane scrollPane;




	public home_screen() {

	}
	
	public static void main(JFrame frame) {
		f=frame;
		home_screen = new JPanel();
		frame.setContentPane(home_screen);
		placeComponentsLogin(home_screen);
	    scrollPane = new JScrollPane(reviewList);
	    scrollPane.setBounds(10,60,590,230);
	    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);   

	}
	
	private static void placeComponentsLogin(JPanel panel){
		home_screen actionTrigger= new home_screen(); // to define buttons action.
		panel.setLayout(null);
		
		title = new JTextArea("Home page");
		title.setBounds(230,0,250,30);
		Font labelFont = title.getFont();
        title.setEditable(false);
        title.setOpaque(false);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		

		review_btn = new JButton("Add review");
		review_btn.addActionListener(actionTrigger);
		review_btn.setBounds(240,300,110,30);
		
		
		logout_btn = new JButton("Log out");
		logout_btn.addActionListener(actionTrigger);
		logout_btn.setBounds(240,330,110,30);
		
		reviewTitle = new JTextArea("Our reviews");
		reviewTitle.setBounds(240,35,250,30);
        reviewTitle.setEditable(false);
        reviewTitle.setOpaque(false);
		reviewTitle.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));


		listModel = new DefaultListModel<>();
	    utilities.fetchReviewsFromDatabase(listModel);
	    reviewList = new JList<>(listModel);
	    reviewList.setFont(new Font(labelFont.getName(), Font.PLAIN, 16));
	    
		
		panel.add(title);
		panel.add(reviewList);
		panel.add(reviewTitle);
		panel.add(review_btn);
		panel.add(logout_btn);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout_btn) {
			f.dispose();
			login_screen.main(f);
		}
		else if(e.getSource() == review_btn) {
			feedback_screen.main(f);
		}
		
		
	}
	
}
