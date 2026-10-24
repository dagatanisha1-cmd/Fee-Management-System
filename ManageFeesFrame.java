import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManageFeesFrame extends JFrame implements ActionListener
{	
	JLabel lblTitle;
	JButton btnAssignFee,btnDepositFee,btnShowFeeStatus,btnShowDepositHistory;

	public ManageFeesFrame()
	{
		setTitle("Manage Fees");
		setSize(500,340);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		lblTitle=new JLabel("Manage Fees");
		lblTitle.setFont(new Font("Arial",Font.BOLD,22));
		lblTitle.setBounds(170,25,200,30);
		add(lblTitle);

		btnAssignFee=new JButton("Assign Fee");
		btnAssignFee.setBounds(70,90,150,35);
		btnAssignFee.addActionListener(this);
		add(btnAssignFee);

		btnDepositFee=new JButton("Deposit Fee");
		btnDepositFee.setBounds(260,90,150,35);
		btnDepositFee.addActionListener(this);
		add(btnDepositFee);

		btnShowFeeStatus=new JButton("Show Fee Status");
		btnShowFeeStatus.setBounds(70,155,150,35);
		btnShowFeeStatus.addActionListener(this);
		add(btnShowFeeStatus);

		btnShowDepositHistory=new JButton("Deposit History");
		btnShowDepositHistory.addActionListener(this);
		btnShowDepositHistory.setBounds(260,155,150,35);
		add(btnShowDepositHistory);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == btnAssignFee)
		{
			new AssignFeeFrame();
		}
		else if(ae.getSource() == btnDepositFee)
		{
			new DepositFeeFrame();
		}
		else if(ae.getSource() == btnShowFeeStatus)
		{
			new ShowFeeStatusFrame();
		}
		else if(ae.getSource() == btnShowDepositHistory)
		{
			new ShowFeeDepositHistoryFrame();
		}
	}
}