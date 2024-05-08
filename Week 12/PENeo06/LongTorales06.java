//Java Swing Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//Neo Imports	
import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;
// import org.reactivestreams.*;

//Log message control imports
// import java.util.logging.Logger;
// import java.util.logging.Level;

//Java imports
// import java.util.Arrays;
// import java.util.Set;
// import java.util.ArrayList;
// import java.util.List;

public class LongTorales06 extends JFrame {

	JTextField input;
	JTextArea output;
	
	Driver driver = null;
	Session session = null;
	Result result = null;

   	WindowListener exitListener = null;
	
	public LongTorales06() {
		setSize(600, 200);
		setLocation(400, 500);
		setTitle("Access NeoDB");
		
		Container cont = getContentPane();
		cont.setLayout(new BorderLayout() );
		
		JButton search = new JButton("Search");
		JButton connect = new JButton("Connect");
		JButton clear = new JButton("Clear");
		
		input = new JTextField(20);
		
		output = new JTextArea(10, 30);
		JScrollPane spOutput = new JScrollPane(output);
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.add(connect);
		northPanel.add(input);
		northPanel.add(search);
		northPanel.add(clear);
		
		cont.add(northPanel, BorderLayout.NORTH);
				
		cont.add(spOutput, BorderLayout.CENTER);
		
		connect.addActionListener(new ConnectNeo());
		search.addActionListener(new GetNeo());
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
		// StartAccessNeo runIt = new StartAccessNeo();
		new LongTorales06();
	}//main
	
	class ConnectNeo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			// in this section open the connection to NeoDB. 
			// You should enter the code to connect to the database here
			// Remember to connect to NeoDB, connect to the database and connect to the 
			//    desired collection
		
			driver = GraphDatabase.driver("neo4j+s://4ea1832f.databases.neo4j.io", AuthTokens.basic("neo4j", "uw_CPRl1mYgHgrXiSSgNkTtrwaT5LwXAC7fgfNCCDEU"));
      		session = driver.session();
      
	   		output.append("Connection to server completed\n");          				
		} //actionPerformed
	} //class ConnectNeo
	
	class GetNeo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			// In this section you should retrieve the data from the collection
			// and use a cursor to list the data in the output JTextArea
      		
			String searchText = input.getText(); 
			
			//Normal Find text
			//Normal Find regex   
			//PLACE THE CURSOR RESULTS in a variable called "result" which is already declared  
         	Result result = session.run(
            	"MATCH (r:Region)-[rd:CONTAINS_DISTRICT]->(d:District)-[rc:CONTAINS_CUSTOMER]-(c:Customer)-[re:EARNINGS_OF]-(e:Earnings)" +
            	"WHERE c.name =~ '(?i)^' + $sW + '.*' and e.reportDate ENDS WITH '2010'" +
            	"RETURN r.regionName, d.distName, c.name, SUM(e.earnings) AS Total",
            	parameters("sW", searchText));

			//Set counter and print results (cursor)
         	int cnt = 0;         
         	while(result.hasNext()) {
            	org.neo4j.driver.Record record = result.next();

            	// display JSON doc
				output.append(record.get("r.regionName").asString() + ": ");
				output.append(record.get("d.distName").asString() + "\t");
				output.append(record.get("c.name").asString() + "\n");
				output.append("Total earnings: " + record.get("Total").asDouble() + "\n\n");
				cnt++;
			}
			output.append("Count: " + String.valueOf(cnt));
     	} //actionPerformed
	} //class GetNeo
	
	class ClearNeo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			// Clear the input and output fields.
			output.setText("");
         input.setText("");
		} //actionPerformed
	} //class ClearNeo

} //class