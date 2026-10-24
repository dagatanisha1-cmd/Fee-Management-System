import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DepositFeeFrame extends JFrame implements ActionListener
{
	JLabel lblTitle,lblFeeRecord,lblTotalFee,lblDeposited,lblPending,lblAmount,lblMessage;
	JComboBox<String> cmbFeeRecord;
	JTextField txtTotalFee,txtDeposited,txtPending,txtAmount;
	JButton btnLoad,btnDeposit;

	int selectedStudentId = -1;
	int selectedBatchId = -1;
	int selectedCourseId = -1;

	public DepositFeeFrame()
	{
		setTitle("Deposit Fee");
		setSize(760,420);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblTitle=new JLabel("Deposit Fee");
		lblTitle.setFont(new Font("Arial",Font.BOLD,22));
		lblTitle.setBounds(300,20,150,30);
		add(lblTitle);

		lblFeeRecord=new JLabel("Fee Record : ");
		lblFeeRecord.setBounds(50,80,120,25);
		add(lblFeeRecord);

		cmbFeeRecord = new JComboBox<>();
		cmbFeeRecord.setBounds(180,80,500,25);
		add(cmbFeeRecord);

		lblTotalFee = new JLabel("Total Fee : ");
		lblTotalFee.setBounds(50,130,120,25);
		add(lblTotalFee);

		txtTotalFee = new JTextField();
		lblTotalFee.setBounds(180,130,180,25);
		txtTotalFee.setEditable(false);
		add(lblTotalFee);

		lblDeposited = new JLabel("Deposited : ");
		lblDeposited.setBounds(390,130,120,25);
		add(lblDeposited);

		txtDeposited = new JTextField();
		txtDeposited.setBounds(500,130,180,25);
		txtDeposited.setEditable(false);
		add(txtDeposited);

		lblPending = new JLabel("Pending : ");
		lblPending.setBounds(50,175,120,25);
		add(lblPending);
	
		txtPending = new JTextField();
		txtPending.setBounds(180,175,180,25);
		txtPending.setEditable(false);
		add(txtPending);

		lblAmount = new JLabel("Deposited Amount : ");
		lblAmount.setBounds(50,220,120,25);
		add(lblAmount);
	
		txtAmount = new JTextField();
		txtAmount.setBounds(180,220,180,25);
		txtAmount.setEditable(false);
		add(txtAmount);

		btnLoad = new JButton("Load");
		btnLoad.setBounds(250,270,100,35);
		btnLoad.addActionListener(this);
		add(btnLoad);

		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(380,270,100,35);
		btnDeposit.addActionListener(this);
		add(btnDeposit);

		lblMessage = new JLabel("");
		lblMessage.setBounds(50,330,600,25);
		lblMessage.setForeground(Color.RED);
		add(lblMessage);

		loadFeeRecords();

		setVisible(true);
	}

	private void loadFeeRecords()
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try
		{	
			cmbFeeRecord.removeAllItems();
			cmbFeeRecord.addItem("Select Enrollment");

			con=DBConnection.getConnection();
			stmt=con.createStatement();

			String query="select fs.fsr_id, fs.s_id, fs.b_id, fs.c_id,fs.fees,"
				+"s.firstname,s.lastname,b.batch_name,c.course_name"
				+"from fees_student fs,student s, batch b, course c"
				+"where fs.s_id = s.s_id"
				+"AND fs.b_id = b.b_id"
				+"AND fs.c_id = c.c_id";
				
			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				String item = rs.getInt("s_id")+"|"+rs.getInt("b_id")+"|"+rs.getInt("c_id")
				+" - "+rs.getString("firstname")+" "+rs.getString("lastname")
				+" - "+rs.getString("batch_name")
				+" - "+rs.getString("course_name")
				+"- Total: "+rs.getString("fees");

				cmbFeeRecord.addItem(item);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			lblMessage.setText("Error while loading fee records.");
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
			}
			catch(Exception ex){}

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

	private void loadFeeDetails()
	{
		String selected = (String)cmbFeeRecord.getSelectedItem();

		if(selected == null || selected.equals("Select Fee Record"))
		{
			lblMessage.setText("Please select Fee Record.");
			return;
		}

		try
		{
			String firstPart=selected.split(" - ")[0];
			String []ids = firstPart.split("\\|");

			selectedStudentId = Integer.parseInt(ids[0]);
			selectedBatchId = Integer.parseInt(ids[1]);
			selectedCourseId = Integer.parseInt(ids[2]); 
		}
		catch (Exception e) 
		{
			lblMessage.setText("Unable to read selected fee record.");
			return;
		}

		Connection con=null;
		Statement stmt=null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try
		{
			con = DBConnection.getConnection();
			stmt = con.createStatement();

			String totalQuery = "select fees from fees_student where s_id ="+selectedStudentId
			+" AND b_id = "+selectedBatchId +"AND c_id"+selectedCourseId;
			rs1 = stmt.executeQuery(totalQuery);

			double totalFee = 0;

			if(rs1.next())
			{
				totalFee = rs1.getDouble("fees");
				txtTotalFee.setText(String.valueOf(totalFee));
			}
			else
			{
				lblMessage.setText("Fee record not found.");
			}

			rs1.close();


			String depositedQuery = "select IFNULL(SUM(amount),0) AS total_deposited from fees_student_deposited"
			+"where s_id = "+selectedStudentId+" AND b_id="+selectedBatchId+" AND c_id="+selectedCourseId;
			rs1 = stmt.executeQuery(depositedQuery);

			double deposited = 0;

			if(rs2.next())
			{
				deposited = rs2.getDouble("Total_deposited");
			}

			double pending = totalFee - deposited;

			if(pending < 0)
			{
				pending = 0;
			}

			txtDeposited.setText(String.valueOf(deposited));
			txtPending.setText(String.valueOf(pending));
			lblMessage.setText("");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			lblMessage.setText("Error while loading fee details.");
		}
		finally
		{
			try
			{
				if(rs1 != null)
					rs1.close();
			}
			catch(Exception ex){}

			try
			{
				if(rs2 != null)
					rs2.close();
			}
			catch(Exception ex){}


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

	public void depositFee()
	{
		if(selectedStudentId == -1 || selectedBatchId == -1 || selectedCourseId == -1)
		{
			lblMessage.setText("Please load fee details first.");
			return;
		}

		String amountText = txtAmount.getText().trim();
		if(amountText.equals(""))
		{
			lblMessage.setText("Please Enter deposit amount.");
			return ;
		}

		double amount = 0;
		double pending = 0;

		try
		{
			amount = Double.parseDouble(amountText);
			pending = Double.parseDouble(txtPending.getText().trim());

			if(amount <= 0)
			{
				lblMessage.setText("Deposit amount must be greater than zero.");
				return;
			}

			if(amount > pending)
			{
				lblMessage.setText("Deposit amount cannot be greater than pending fee.");
				return ;
			}
		}catch(Exception ex)
		{
			lblMessage.setText("Please Enter Valid numeric amount.");
			return;
		}

		Connection con = null;
		Statement stmt = null;

		try
		{			
			con = DBConnection.getConnection();
			stmt = con.createStatement();

			String query = "INSERT INTO fees_student_deposited(s_id,b_id,c_id,amount) values("
			+selectedStudentId+", "
			+selectedBatchId+", "
			+selectedCourseId+", "
			+amount+")";

			int result = stmt.executeUpdate(query);

			if(result > 0)
			{
				JOptionPane.showMessageDialog(this,"Fee deposited successfully.");
				txtAmount.setText("");
				loadFeeDetails();
			}

			else
			{
				lblMessage.setText("Fee deposit Failed");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			lblMessage.setText("Database Error while depositing fee.");
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
			loadFeeDetails();
		}
		else if(ae.getSource() == btnDeposit)
		{
			depositFee();
		}
	}
}