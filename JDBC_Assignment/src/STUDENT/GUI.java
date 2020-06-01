package STUDENT;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.Window.Type;
@SuppressWarnings("serial")
public class GUI implements ActionListener {

	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;
	private ResultSet rs2 = null;
	private JFrame frmJdbcassignment;
	private JComboBox medal,gender;
	private JTextField id;
	private JTextField fname;
	private JTextField lname;
	private JTextField age;
	private JTextField country;
	private JTextField sport;
	private JTextField weight;
	private Vector modelData;;
	private static QueryTableModel TableModel =new QueryTableModel();
	private JTable DBtable;
	private JButton btnInsert, btnExport, btnDelete, btnUpdate, btnClear, btnNewButton,btn2;
	private JLabel ageLabel;
	private JLabel weightLabel;
	private JTextField no;
	
	
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmJdbcassignment.setVisible(true);
					 UIManager.setLookAndFeel(
					            UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		initiate_db_conn();
		frmJdbcassignment = new JFrame();
		frmJdbcassignment.setFont(new Font("Calibri", Font.BOLD, 14));
		frmJdbcassignment.setType(Type.POPUP);
		frmJdbcassignment.getContentPane().setBackground(SystemColor.activeCaption);
		frmJdbcassignment.setTitle("AIT STUDENT DETAILS SYSTEM");
		frmJdbcassignment.setBounds(100, 100, 1241, 808);
		frmJdbcassignment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJdbcassignment.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		panel.setForeground(Color.BLACK);
		panel.setToolTipText("");
		panel.setBounds(12, 13, 439, 441);
		frmJdbcassignment.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel idLabel = new JLabel("");
		idLabel.setForeground(Color.RED);
		idLabel.setBackground(new Color(240, 240, 240));
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		idLabel.setBounds(147, 49, 97, 16);
		panel.add(idLabel);
		
		ageLabel = new JLabel("");
		ageLabel.setForeground(Color.RED);
		ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ageLabel.setBackground(SystemColor.menu);
		ageLabel.setBounds(147, 207, 86, 16);
		panel.add(ageLabel);
		
		weightLabel = new JLabel("");
		weightLabel.setForeground(Color.RED);
		weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		weightLabel.setBackground(SystemColor.menu);
		weightLabel.setBounds(147, 322, 97, 16);
		panel.add(weightLabel);
		JLabel lblNewLabel = new JLabel("StudentID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(36, 31, 78, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(36, 72, 76, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(36, 116, 78, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(58, 158, 56, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(84, 187, 30, 24);
		panel.add(lblAge);
		
		JLabel lblCounrty = new JLabel("Country:");
		lblCounrty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCounrty.setBounds(58, 224, 56, 16);
		panel.add(lblCounrty);
		
		JLabel lblSport = new JLabel("Course:");
		lblSport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSport.setBounds(58, 260, 56, 16);
		panel.add(lblSport);
		
		JLabel lblNewLabel_4 = new JLabel("Year:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(78, 298, 36, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Program:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(58, 341, 70, 16);
		panel.add(lblNewLabel_5);
		
		medal = new JComboBox();
		medal.setModel(new DefaultComboBoxModel(new String[] {"Engg", "Nursing ", "Business", "Pharmacy"}));
		medal.setBounds(147, 340, 116, 22);
		panel.add(medal);
		
		
		id = new JTextField();
		id.setBounds(147, 29, 116, 22);
		panel.add(id);
		id.setColumns(10);
		
		
		
		fname = new JTextField();
		fname.setBounds(147, 70, 116, 22);
		panel.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(147, 114, 116, 22);
		panel.add(lname);
		lname.setColumns(10);
		
		gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gender.setBounds(147, 156, 116, 22);
		panel.add(gender);
		
		age = new JTextField();
		age.setBounds(147, 189, 116, 22);
		panel.add(age);
		age.setColumns(10);
		
		country = new JTextField();
		country.setBounds(147, 222, 116, 22);
		panel.add(country);
		country.setColumns(10);
		
		sport = new JTextField();
		sport.setBounds(147, 258, 116, 22);
		panel.add(sport);
		sport.setColumns(10);
		
		weight = new JTextField();
		weight.setBounds(147, 297, 116, 22);
		panel.add(weight);
		weight.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBackground(SystemColor.desktop);
		btnInsert.setForeground(UIManager.getColor("Button.foreground"));
		btnInsert.setBounds(310, 49, 97, 25);
		panel.add(btnInsert);
		btnInsert.addActionListener(this);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(SystemColor.desktop);
		btnUpdate.setBounds(310, 114, 97, 25);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(SystemColor.desktop);
		btnDelete.setBounds(310, 186, 97, 25);
		panel.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnExport = new JButton("Export");
		btnExport.setBackground(SystemColor.desktop);
		btnExport.setBounds(310, 258, 97, 25);
		panel.add(btnExport);
		btnExport.addActionListener(this);
		
		btnClear = new JButton("Clear");
		btnClear.setBackground(SystemColor.desktop);
		btnClear.setBounds(310, 322, 97, 25);
		panel.add(btnClear);
		
		
		btnClear.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.BLACK));
		panel_1.setBounds(508, 13, 703, 469);
		frmJdbcassignment.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		DBtable = new JTable(TableModel);
		DBtable.setBounds(22, 13, 669, 443);
		panel_1.add(DBtable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(58, 520, 276, 108);
		frmJdbcassignment.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btnNewButton = new JButton("Program Chosen");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(12, 44, 247, 51);
		panel_2.add(btnNewButton);
		
		no = new JTextField();
		no.setBounds(12, 13, 247, 22);
		panel_2.add(no);
		no.setColumns(10);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel2.setLayout(null);
		panel2.setBounds(420, 520, 276, 108);
		frmJdbcassignment.getContentPane().add(panel2);
		
		JButton btn2 = new JButton("Country Name");
		btn2.setBackground(Color.BLACK);
		btn2.addActionListener(new ActionListener() {
			private Object btn2;

			public void actionPerformed(ActionEvent e) {
				Object target = null;
				if(target == this.btn2){

					String cmd = "select distinct country from student;";

					try{					
						rs2= stmt.executeQuery(cmd); 	
						writeToFile2(rs2);
					}
					catch(Exception e1){e1.printStackTrace();}

				}
			}
		});
		btn2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn2.setBounds(10, 39, 247, 46);
		panel2.add(btn2);
		btnNewButton.addActionListener(this);
		
		TableModel.refreshFromDB(stmt);
	}


public void initiate_db_conn()
{
	try
	{
		// Load the JConnector Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Specify the DB Name
		String url="jdbc:mysql://localhost:3306/boom";
		// Connect to DB using DB URL, Username and password
		con = DriverManager.getConnection(url, "root", "12345");
		//Create a generic statement which is passed to the TestInternalFrame1
		stmt = con.createStatement();
		
	}
	catch(Exception e)
	{
		System.out.println("Error: Failed to connect to database\n"+e.getMessage());
	}
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String cmd=null;
	Object target=e.getSource();
	String gen = (String) gender.getSelectedItem();
	if(gen == "Female") {
		gen = "F";
	}
	else {
		gen = "M";
	}
	String med  = (String) medal.getSelectedItem();
	
	if(target== btnInsert) {
		try
		{
			String updateTemp ="INSERT INTO student VALUES("+
			null +",'"+fname.getText()+"','"+lname.getText()+"','"+gen+"','"+age.getText()+"','"
			+country.getText()+"','"+sport.getText()+"','"+weight.getText()+"','"+med+"');";

			stmt.executeUpdate(updateTemp);

		}
		catch (SQLException sqle)
		{
			System.err.println("Error with  insert:\n"+sqle.toString());
		}
		finally
		{
			TableModel.refreshFromDB(stmt);
		}
	}
	
	else if (target == btnClear)
	{
		id.setText("");
		fname.setText("");
		lname.setText("");
		age.setText("");
		gender.setSelectedIndex(0);
		weight.setText("");
		country.setText("");
		sport.setText("");
		medal.setSelectedIndex(0);

	}
	
	else if (target == btnDelete)
	{

		try
		{
			String updateTemp ="DELETE FROM student WHERE id = "+id.getText()+";"; 
			stmt.executeUpdate(updateTemp);

		}
		catch (SQLException sqle)
		{
			System.err.println("Error with delete:\n"+sqle.toString());
		}
		finally
		{
			TableModel.refreshFromDB(stmt);
		}
	}
	
	else if (target == btnUpdate)
	{	 	
		try
		{ 			
			String updateTemp ="UPDATE student SET " +
			"FirstName = '"+fname.getText()+
			"', LastName = '"+lname.getText()+
			"', Gender ='"+gen+
			"', Age = '"+age.getText()+
			"', Country = '"+country.getText()+
			"', Course = '"+sport.getText()+
			"', Year = '"+weight.getText()+
			"', Program = '"+med +
			"' where id = '"+id.getText() +"'";


			stmt.executeUpdate(updateTemp);
			//these lines do nothing but the table updates when we access the db.
			rs = stmt.executeQuery("SELECT * from student ");
			rs.next();
			//rs.close();	
		}
		catch (SQLException sqle){
			System.err.println("Error with  update:\n"+sqle.toString());
		}
		finally{
			TableModel.refreshFromDB(stmt);
		}
	}
	
	else if(target == btnExport) {

		cmd = "select * from student;";

		try{					
			rs= stmt.executeQuery(cmd); 	
			writeToFile(rs);
		}
		catch(Exception e1){e1.printStackTrace();}
	}
	
	else if(target == btnNewButton){
		String country1 = no.getText();
    try {
		 CallableStatement st = con.prepareCall("{call country11(?)}");
		 
		 st.setString(1, country1);
		 rs1 = st.executeQuery();
		 writeToFile1(rs1);
	}
    catch(Exception e1) {
    	System.out.println("Enter Valid Country");
    }}	
	
	

	
	
}

private void writeToFile(ResultSet rs){
	try{
		System.out.println("In writeToFile");
		FileWriter outputFile = new FileWriter("Details.csv");
		PrintWriter printWriter = new PrintWriter(outputFile);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int numColumns = rsmd.getColumnCount();

		for(int i=0;i<numColumns;i++){
			printWriter.print(rsmd.getColumnLabel(i+1)+",");
		}
		printWriter.print("\n");
		while(rs.next()){
			for(int i=0;i<numColumns;i++){
				printWriter.print(rs.getString(i+1)+",");
			}
			printWriter.print("\n");
			printWriter.flush();
		}
		printWriter.close();
	}
	catch(Exception e){e.printStackTrace();}
}
private void writeToFile1(ResultSet rs1){
	try{
		System.out.println("In writeToFile1");
		FileWriter outputFile = new FileWriter("program.csv");
		PrintWriter printWriter = new PrintWriter(outputFile);
		
		ResultSetMetaData rsmd = rs1.getMetaData();
		int numColumns = rsmd.getColumnCount();

		for(int i=0;i<numColumns;i++){
			printWriter.print(rsmd.getColumnLabel(i+1)+",");
		}
		printWriter.print("\n");
		while(rs1.next()){
			for(int i=0;i<numColumns;i++){
				printWriter.print(rs1.getString(i+1)+",");
			}
			printWriter.print("\n");
			printWriter.flush();
		}
		printWriter.close();
	}
	catch(Exception e){e.printStackTrace();}
}
private void writeToFile2(ResultSet rs2){
	try{
		System.out.println("In writeToFile2");
		FileWriter outputFile = new FileWriter("file2.csv");
		PrintWriter printWriter = new PrintWriter(outputFile);
		
		ResultSetMetaData rsmd = rs2.getMetaData();
		int numColumns = rsmd.getColumnCount();

		for(int i=0;i<numColumns;i++){
			printWriter.print(rsmd.getColumnLabel(i+1)+",");
		}
		printWriter.print("\n");
		while(rs2.next()){
			for(int i=0;i<numColumns;i++){
				printWriter.print(rs2.getString(i+1)+",");
			}
			printWriter.print("\n");
			printWriter.flush();
		}
		printWriter.close();
	}
	catch(Exception e){e.printStackTrace();}
}
}