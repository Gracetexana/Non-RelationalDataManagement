import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;
// import org.reactivestreams.*;

public class TestNeoCreate {
   public static void main (String[] args) {
      // connect to the server and Neo4j database
      Driver driver = GraphDatabase.driver( "neo4j+s://4ea1832f.databases.neo4j.io", 
                      AuthTokens.basic( "neo4j", "uw_CPRl1mYgHgrXiSSgNkTtrwaT5LwXAC7fgfNCCDEU" ));
      Session session = driver.session();
      
      //Create a node
      String custID = "999"; // use as a parameter value
      String custName = "My New Company";
      session.run("MERGE (c:Customer {custID: $cID, name: $cname})",
         parameters("cID", custID, "cname", custName));
        
      // Notice no cursor for a MERGE (create / insert)
      
      //Run a query
      //MATCH (a:Region) WHERE a.regionName = 'Western' RETURN a.regionName AS name
      Result result = session.run("MATCH (c:Customer) WHERE c.custID = $cID " +
                      "RETURN c.custID AS ID, c.name AS Name",
                      parameters("cID", custID));
      
      //Process cursor
      while(result.hasNext()) {
         org.neo4j.driver.Record record = result.next();
         System.out.println(record.get("ID").asString() + " " + record.get("Name"));
      }

      session.close();
      driver.close();
   } //main
   
} //class
