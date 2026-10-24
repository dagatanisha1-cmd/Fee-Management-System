import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class DeleteFees 
{
	private JFrame frm;	
	private JLabel deleteFees,batchDeleteFees,courseDeleteFees,studentDeleteFees,amountDeleteFees,dueDeleteFees;
	private JTextField amountDeleteFieldFees,dueDeleteFieldFees;
	private JButton savebtnDeleteFees;
	public DeleteFees(String title)
	{
		frm=new JFrame(title);
		frm.setSize(500,500);
		frm.setLayout(null);
		
		deleteFees=new JLabel("Delete fee");
		deleteFees.setBounds(75,15,150,40);
		frm.add(deleteFees);

		batchDeleteFees=new JLabel("Batch ");
		batchDeleteFees.setBounds(15,60,150,40);
		frm.add(batchDeleteFees);

		String batches[]={"batch1","batch2"};
		JComboBox cb = new JComboBox(batches);
		cb.setBounds(130,60,150,40);
		frm.add(cb);

		courseDeleteFees=new JLabel("course ");
		courseDeleteFees.setBounds(15,110,150,40);
		frm.add(courseDeleteFees);

		String courses[]={"java","c","c++"};
		cb = new JComboBox(courses);
		cb.setBounds(130,110,150,40);
		frm.add(cb);

		batchDeleteFees=new JLabel("Student ");
		batchDeleteFees.setBounds(15,160,150,40);
		frm.add(batchDeleteFees);

		String students[]={"tanisha","arshdeep"};
		cb = new JComboBox(students);
		cb.setBounds(130,160,150,40);
		frm.add(cb);

		amountDeleteFees=new JLabel("Amount");
		amountDeleteFees.setBounds(15,210,100,40);
		frm.add(amountDeleteFees);

		amountDeleteFieldFees=new JTextField();
		amountDeleteFieldFees.setBounds(130,210,100,40);
		frm.add(amountDeleteFieldFees);
		amountDeleteFieldFees.setEditable(false);

		dueDeleteFees=new JLabel("Due");
		dueDeleteFees.setBounds(15,260,100,40);
		frm.add(dueDeleteFees);

		dueDeleteFieldFees=new JTextField();
		dueDeleteFieldFees.setBounds(130,260,100,40);
		frm.add(dueDeleteFieldFees);
		dueDeleteFieldFees.setEditable(false);


		savebtnDeleteFees=new JButton("Delete");
		savebtnDeleteFees.setBounds(75,310,150,40);
		frm.add(savebtnDeleteFees);


		frm.setVisible(true);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

// class Main
// {
// 	public static void main(String[] args) 
// 	{
// 		DeleteFees en=new DeleteFees("Delete Fees");
// 	}
// }