import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;
// import org.reactivestreams.*;

public class TestNeo {
   public static void main (String[] args) {
      // connect to the server and Neo4j database
      Driver driver = GraphDatabase.driver( "neo4j+s://4ea1832f.databases.neo4j.io", 
                      AuthTokens.basic( "neo4j", "uw_CPRl1mYgHgrXiSSgNkTtrwaT5LwXAC7fgfNCCDEU" ));
      Session session = driver.session();
      
      //Run a query
      Result result = 
         session.run("MATCH (c:Customer)-[:EARNINGS_OF]->(e:Earnings) " +
                     "WHERE c.custID = $cID " +
                     "RETURN c.name AS CName, e.reportDate AS Date, e.earnings AS Earnings",
                     parameters ("cID", "22"));

      /* Old format pre 4.0
      StatementResult result = 
         session.run("MATCH (c:Customer)-[:EARNINGS_OF]->(e:Earnings) " +
                     "WHERE c.custID = {cID} " +
                     "RETURN c.name AS CName, e.reportDate AS Date, e.earnings AS Earnings",
                     parameters ("cID", "22"));
      */
     
      //Process cursor
      while(result.hasNext()) {
         org.neo4j.driver.Record record = result.next();
         
         System.out.println(record); //see the json document
         
         System.out.print(record.get("CName").asString() + " "); //see individual fields
         System.out.print(record.get("Date").asString() + " ");
         System.out.print(" " + record.get("Earnings").asFloat() +"\n");
      }

      session.close();
      driver.close();
   } //main

} //class
