import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteStudentFrame extends JFrame implements ActionListener  
{
	JLabel lblTitle, lblId,lblMobile,lblEmail,lblDob,lblFirstName,lblLastName,lblMessage;
	JTextField txtMobile, txtId,txtEmail,txtDob,txtFirstName,txtLastName;
	JButton btnLoad, btnDelete;
	public DeleteStudentFrame()
	{
		setTitle("Delete Student");
		setSize(580,460);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		lblTitle=new JLabel("Delete Student");
		lblTitle.setFont(new Font("Arial",Font.BOLD,22));
		lblTitle.setBounds(200,20,180,30);
		add(lblTitle);

		lblId=new JLabel("Student ID:");
		lblId.setBounds(60,70,120,25);
		add(lblId);

		txtId=new JTextField();
		txtId.setBounds(190,70,120,25);
		add(txtId);

		btnLoad=new JButton("Load");
		btnLoad.setBounds(330,70,80,25);
		add(btnLoad);

		lblFirstName=new JLabel("First Name :");
		lblFirstName.setBounds(60,120,120,25);
		add(lblFirstName);

		txtFirstName=new JTextField();
		txtFirstName.setBounds(190,120,250,25);
		add(txtFirstName);

		lblLastName=new JLabel("Last Name:");
		lblLastName.setBounds(60,160,120,25);
		add(lblLastName);

		txtLastName=new JTextField();
		txtLastName.setBounds(190,160,250,25);
		add(txtLastName);

		lblEmail=new JLabel("Email");
		lblEmail.setBounds(60,200,120,25);
		add(lblEmail);

		txtEmail=new JTextField();
		txtEmail.setBounds(190,200,250,25);
		add(txtEmail);

		lblMobile=new JLabel("Mobile:");
		lblMobile.setBounds(60,240,120,25);
		add(lblMobile);

		txtMobile=new JTextField();
		txtMobile.setBounds(190,240,250,25);
		add(txtMobile);

		lblDob=new JLabel("DOB:");
		lblDob.setBounds(60,280,120,25);
		add(lblDob);

		txtDob=new JTextField();
		txtDob.setBounds(190,280,250,25);
		add(txtDob);

		btnDelete=new JButton("Delete");
		btnDelete.setBounds(210,330,100,25);
		btnDelete.addActionListener(this);
		add(btnDelete);

		lblMessage = new JLabel("");
		lblMessage.setBounds(60,380,430,25);
		lblMessage.setForeground(Color.RED);
		add(lblMessage);

		setVisible(true);
	}

	private void loadStudent()
	{
		String idText =txtId.getText().trim();

		if(idText.equals(""))
		{
			lblMessage.setText("Please enter Student id.");
			return;
		}

		int studentId=0;

		try
		{
			studentId=Integer.parseInt(idText);
		}
		catch (Exception e) 
		{
			lblMessage.setText("Please Enter Valid Student id");
			return;
		}

		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();

			String query="Select * from student Where s_id ="+studentId;
			rs = stmt.executeQuery(query);

			if(rs.next())
			{
				txtFirstName.setText(rs.getString("firstname"));
				txtLastName.setText(rs.getString("lastname"));
				txtEmail.setText(rs.getString("email"));
				txtMobile.setText(rs.getString("mobile"));
				txtDob.setText(rs.getString("dob"));
				lblMessage.setText("");
			}
			else
			{
				txtFirstName.setText("");
				txtLastName.setText("");
				txtEmail.setText("");
				txtMobile.setText("");
				txtDob.setText("");
				lblMessage.setText("Student Not Found");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			lblMessage.setText("Error while loading Student.");
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
			}
			catch(Exception e){}

			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(Exception e){	}

			try
			{
				if(con != null)
					con.close();
			}
			catch(Exception e) {}

		}
	}

	private void deleteStudent()
	{
		String idText = txtId.getText().trim();

		if(idText.equals("") )
		{
			lblMessage.setText("Please Enter student Id.");
			return;
		}

		int studentId=0;
		try
		{
			studentId = Integer.parseInt(idText);
		}
		catch (Exception e) 
		{
			lblMessage.setText("Please Enter Valid Student id.");			
			return;
		}

		if(txtFirstName.getText().trim().equals(""))
		{
			lblMessage.setText("Please load student first.");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(
			this,
			"Are you sure you want to delete this batch?",
			"Confirm Delete ",
			JOptionPane.YES_NO_OPTION
		);

		if(confirm != JOptionPane.YES_OPTION)
		{
			return;
		}

		Connection con=null;
		Statement stmt=null;

		try
		{
			con = DBConnection.getConnection();
			stmt=con.createStatement();

			String query="Delete from student Where s_id="+studentId;

			int result = stmt.executeUpdate(query);

			if(result > 0)
			{
				JOptionPane.showMessageDialog(this,"Student Deleted successfully.");
				txtId.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				txtEmail.setText("");
				txtMobile.setText("");
				txtDob.setText("");
				lblMessage.setText("");
			}
			else
			{
				lblMessage.setText("Student id not found");
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			lblMessage.setText("Error while Deleting Student.");
		}

		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(Exception ex){}

			try
			{
				if(con != null)
					con.close();
			}
			catch(Exception ex){}

		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == btnLoad)
		{
			loadStudent();
		}
		else if(ae.getSource() == btnDelete)
		{
			deleteStudent();
		}
	}
}