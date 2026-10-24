import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ManageStudentFrame extends JFrame implements ActionListener
{	
	JLabel lblTitle;
	JButton btnAdd,btnUpdate,btnDelete,btnShow,btnEnrollment;
	public ManageStudentFrame()
	{
		setTitle("Manage Student");
		setSize(450,300);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		lblTitle=new JLabel("Manage Student");
		lblTitle.setFont(new Font("Arial",Font.BOLD,22));
		lblTitle.setBounds(125,25,220,30);
		add(lblTitle);

		btnAdd=new JButton("Add");
		btnAdd.setBounds(70,90,120,35);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnUpdate=new JButton("Update");
		btnUpdate.setBounds(230,90,120,35);
		btnUpdate.addActionListener(this);
		add(btnUpdate);

		btnDelete=new JButton("Delete");
		btnDelete.setBounds(70,150,120,35);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnShow=new JButton("Show Student");
		btnShow.addActionListener(this);
		btnShow.setBounds(230,150,120,35);
		add(btnShow);

		btnEnrollment=new JButton("Student Enrollment");
		btnEnrollment.setBounds(145,215,180,35);
		btnEnrollment.addActionListener(this);
		add(btnEnrollment);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == btnAdd)
		{
			new AddStudentFrame();
		}
		else if(ae.getSource() == btnUpdate)
		{
			new UpdateStudentFrame();
		}
		else if(ae.getSource() == btnDelete)
		{
			new DeleteStudentFrame();
		}
		else if(ae.getSource() == btnShow)
		{
			new ShowStudentFrame();
		}
		else if(ae.getSource() == btnEnrollment)
		{
			new ManageEnrollmentFrame();
		}
	}
}