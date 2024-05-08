import javax.swing.*;

import static org.neo4j.driver.Values.parameters;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//Neo Imports	
import org.neo4j.driver.*;
// import static org.neo4j.driver.Values.parameters;
// import org.reactivestreams.*;

//Log message control imports
// import java.util.logging.Logger;
// import java.util.logging.Level;

//Java imports

// import java.util.Arrays;
// import java.util.Set;
// import java.util.ArrayList;
// import java.util.List;

public class LongTorales07 extends JFrame {
	JTextField msg;
   JTextField ipCustID;
   JTextField ipName;
   JTextField ipAddr1;
   JTextField ipCity;
   JTextField ipState;
   JTextField ipZip;
	JTextArea output;
	
	Driver driver = null;
   Session session = null;
   WindowListener exitListener = null;
	
	public LongTorales07() {
		setSize(600, 400);
		setLocation(400, 300);
		setTitle("Access NeoDB");
		
		Container cont = getContentPane();
		cont.setLayout(new BorderLayout() );
      		
		JButton insert = new JButton("Insert");
		JButton connect = new JButton("Connect");
		JButton clear = new JButton("Clear");
		
		ipCustID = new JTextField(20);
      ipName = new JTextField(20);
      ipAddr1 = new JTextField(20);
      ipCity = new JTextField(20);
      ipState = new JTextField(20);
      ipZip = new JTextField(20);
      msg = new JTextField(20);
      
      JLabel lblCustID = new JLabel("CustID:", JLabel.LEFT);
      JLabel lblName = new JLabel("Name:", JLabel.LEFT);
      JLabel lblAddr1 = new JLabel("Addr1:", JLabel.LEFT);
      JLabel lblCity = new JLabel("City:", JLabel.LEFT);
      JLabel lblState = new JLabel("State:", JLabel.LEFT);
      JLabel lblZip = new JLabel("Zip:", JLabel.LEFT);

		
		output = new JTextArea(10, 30);
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.add(connect);
      northPanel.add(msg);
		northPanel.add(insert);
		northPanel.add(clear);
      
      JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 1));
      centerPanel.add(lblCustID);
      centerPanel.add(ipCustID);
      centerPanel.add(lblName);
      centerPanel.add(ipName);
      centerPanel.add(lblAddr1);
      centerPanel.add(ipAddr1);
      centerPanel.add(lblCity);
      centerPanel.add(ipCity);
      centerPanel.add(lblState);
      centerPanel.add(ipState);
      centerPanel.add(lblZip);
      centerPanel.add(ipZip);
		
		cont.add(northPanel, BorderLayout.NORTH);
      cont.add(centerPanel, BorderLayout.CENTER);
		
		connect.addActionListener(new ConnectNeo());
		insert.addActionListener(new InsertNeo());
		clear.addActionListener(new ClearNeo());
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      
      exitListener = new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(
               null, "Are You Sure to Close Application?", 
               "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
               JOptionPane.QUESTION_MESSAGE, null, null, null);
            
            if (confirm == 0) {
               System.exit(0);
            }
         }
      };
      
      addWindowListener(exitListener);
		setVisible(true);
	} //AccessNeo
	
	public static void main (String [] args) {
      // The following statements are used to eliminate NeoDB Logging
      //   information such as INFO messages that the user should not see.
      // It requires the import of Logger and Level classes.
      // Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
      // mongoLogger.setLevel(Level.INFO); 
   
      new LongTorales07();
	}//main
	
	class ConnectNeo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
         // in this section open the connection to NeoDB. 
         // You should enter the code to connect to the database here
		
   		//INSERT CONNECTION CODE HERE
	   	driver = GraphDatabase.driver("neo4j+s://4ea1832f.databases.neo4j.io", AuthTokens.basic("neo4j", "uw_CPRl1mYgHgrXiSSgNkTtrwaT5LwXAC7fgfNCCDEU"));
		   session = driver.session();

		   msg.setText("Connection Complete");		
		}//actionPerformed
	}//class ConnectNeo
	
	class InsertNeo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
	   	// In this section you should insert data 
		   
         String ipCustIDText = ipCustID.getText();
         String ipNameText = ipName.getText();
         String ipAddr1Text = ipAddr1.getText();
         String ipCityText = ipCity.getText();
         String ipStateText = ipState.getText();
         String ipZipText = ipZip.getText();

         session.run(
            "MERGE (c:Customer {custID: $cID, name: $cName, addr2: $cAddr, city: $cCity, state: $cState, zip: $cZip})",
            parameters(
               "cID", ipCustIDText,
               "cName", ipNameText,
               "cAddr", ipAddr1Text,
               "cCity", ipCityText,
               "cState", ipStateText,
               "cZip", ipZipText
            )
         );

         msg.setText("Customer Node Inserted");
	   }//actionPerformed
	}//class InsertNeo
	
	class ClearNeo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
		   // in this section clear the input fields.
			msg.setText("");
         ipCustID.setText("");
         ipName.setText("");
         ipAddr1.setText("");
         ipCity.setText("");
         ipState.setText("");
         ipZip.setText("");	
		}//actionPerformed
	}//class ClearNeo

} //class