import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class feedback_screen implements ActionListener{
	protected static JFrame f;
	protected static JPanel feedbackPage;
	protected static JTextArea title, alert,review;
	protected static JTextField name;
	protected static JButton sent_btn,back_btn;
	protected static JScrollPane scrollPane;


	public feedback_screen() {
		
	}
	
	public static void main(JFrame frame) {
		f=frame;
		feedbackPage = new JPanel();
		frame.setContentPane(feedbackPage);
		placeComponentsLogin(feedbackPage);
		scrollPane = new JScrollPane(review);
	    scrollPane.setBounds(160,120,300,100);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
		sent_btn.requestFocusInWindow();
	}
	
	private static void placeComponentsLogin(JPanel panel){
		feedback_screen actionTrigger= new feedback_screen(); // to define buttons action.
		panel.setLayout(null);
		
		title = new JTextArea("Your feedback is important to us");
		title.setBounds(160,0,330,30);
		Font labelFont = title.getFont();
        title.setEditable(false);
        title.setOpaque(false);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		
		JLabel titleUser = new JLabel("Your name");
		titleUser.setBounds(165,30,300,30);
		name = new JTextField(null);
		name.setBounds(160,50,300,30);
		
		
		
		JLabel titlePass = new JLabel("Tell us what do you think");
		
		titlePass.setBounds(165,80,300,30);
		review = new JTextArea();
		review.setLineWrap(true);
		review.setWrapStyleWord(true);

		
		
		sent_btn = new JButton("Send");
		sent_btn.addActionListener(actionTrigger);
		sent_btn.setBounds(240,250,110,30);
		
		back_btn= new JButton("Back");
		back_btn.addActionListener(actionTrigger);
		back_btn.setBounds(30,300,110,30);
		
		alert = new JTextArea("");
		alert.setBounds(160,230,300,30);
		Font labelFont2 = alert.getFont();
		alert.setEditable(false);
		alert.setOpaque(false);
		alert.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));
		
		
		
		panel.add(title);
		panel.add(alert);
		
		panel.add(titleUser);
		panel.add(name);
		
		panel.add(titlePass);

		
		panel.add(sent_btn);
		panel.add(back_btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sent_btn) {
			if(name.getText().length() > 0) {
				if (review.getText().length() > 0 ){
					if(name.getText().length() < 49 && review.getText().length() < Integer.MAX_VALUE ) {	
						if(utilities.addReview(name.getText(), review.getText())) {
							home_screen.main(f);
						}
						else {
							alert.setText("Something went wrong, try again");
						}
					}
					else {
						alert.setText("Name or The review is too long!");
					}
				}
				else {
					alert.setText("Please insert your review");	
				}
			}
			else {
				alert.setText("Please insert your name");
			}
		}
		else if(e.getSource() == back_btn) {	
			home_screen.main(f);
		}
		
	}

}
