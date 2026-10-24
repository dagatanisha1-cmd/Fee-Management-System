import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ManageEnrollmentFrame extends JFrame implements ActionListener
{	
	JLabel lblTitle;
	JButton btnEnroll,btnShowEnrollment,btnRemoveEnrollment;
	public ManageEnrollmentFrame()
	{
		setTitle("Student Enrollment");
		setSize(450,300);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		lblTitle=new JLabel("Student Enrollment");
		lblTitle.setFont(new Font("Arial",Font.BOLD,22));
		lblTitle.setBounds(125,25,220,30);
		add(lblTitle);
 
		btnEnroll=new JButton("Enroll Student");
		btnEnroll.setBounds(135,85,160,35);
		btnEnroll.addActionListener(this);
		add(btnEnroll);

		btnShowEnrollment=new JButton("Show Enrollment");
		btnShowEnrollment.setBounds(135,135,160,35);
		btnShowEnrollment.addActionListener(this);
		add(btnShowEnrollment);

		btnRemoveEnrollment=new JButton("Remove Enrollment");
		btnRemoveEnrollment.setBounds(135,185,160,35);
		btnRemoveEnrollment.addActionListener(this);
		add(btnRemoveEnrollment);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == btnEnroll)
		{
			new EnrollStudentFrame();
		}
		else if(ae.getSource() == btnShowEnrollment)
		{
			new ShowEnrollmentFrame();
		}
		else if(ae.getSource() == btnRemoveEnrollment)
		{
			new DeleteEnrollmentFrame();
		}
	}
}