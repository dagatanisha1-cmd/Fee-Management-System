import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AdminDashboardFrame extends JFrame implements ActionListener
{
	JLabel lblTitle;
	JButton	btnmanageCourse,btnmanageBatch,btnmanageStudent,btnmanageFees;
	public AdminDashboardFrame()
	{
		setTitle("Admin Dashboard");
		setSize(500,350);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		lblTitle=new JLabel("Admin Dashboard");
		lblTitle.setFont(new Font("Arial", Font.BOLD,24));
		lblTitle.setBounds(140,30,250,30);
		add(lblTitle);

		btnmanageCourse = new JButton("Manage Course");
		btnmanageCourse.setBounds(70,100,150,40);
		btnmanageCourse.addActionListener(this);
		add(btnmanageCourse);

		btnmanageBatch = new JButton("Manage Batch");
		btnmanageBatch.setBounds(260,100,150,40);
		btnmanageBatch.addActionListener(this);
		add(btnmanageBatch);

		btnmanageStudent = new JButton("Manage Student");
		btnmanageStudent.setBounds(70,170,150,40);
		btnmanageStudent.addActionListener(this);
		add(btnmanageStudent);

		btnmanageFees = new JButton("Manage Fees");
		btnmanageFees.setBounds(260,170,150,40);
		btnmanageFees.addActionListener(this);
		add(btnmanageFees);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnmanageCourse)
		{
			new ManageCourseFrame();
		}
		else if(e.getSource() == btnmanageBatch)
		{
			new ManageBatchFrame();
		}
		else if(e.getSource() == btnmanageStudent)
		{
			new ManageStudentFrame();	
		}
		else if(e.getSource() == btnmanageFees)
		{
			new ManageFeesFrame();
		}
	}
}