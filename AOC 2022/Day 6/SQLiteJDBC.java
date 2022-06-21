import java.sql.*;

public class SQLiteJDBC{
   static Connection c = null;
   static Statement stmt = null;
  public static void main( String args[]){
      try {
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS FISH " +
                        "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                        " DAYSUNTIL      INT     NOT NULL)"; 
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } 
      catch (Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
   public void insertNewFish(int start){
      try{
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         stmt = c.createStatement();
         String sql = String.format("INSERT INTO FISH (DAYSUNTIL) "+
                      "VALUES (%d);", start);
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
         return;
      }
      catch(Exception e){
         System.out.println("yikes!");
         e.printStackTrace();
      }
   }
   public long getPop(){
      try{
         
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         stmt = c.createStatement();
         String sql = String.format("SELECT COUNT(*) AS COUNT FROM FISH;");
         ResultSet rs = stmt.executeQuery(sql);
         long result = rs.getInt("COUNT");
         stmt.close();
         c.close();
         return result;
      }
      catch(Exception e){
         System.out.println("yikes!");
         e.printStackTrace();
      }
      return 0;
   }
   public boolean grow(int id){
      try{
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         stmt = c.createStatement();
         String sql = String.format("SELECT daysuntil FROM FISH WHERE ID = (%d);", id);
         ResultSet rs = stmt.executeQuery(sql);
         int result = rs.getInt("daysuntil");
         stmt.close();
         if(result == 0){
            try{
               stmt = c.createStatement();
               sql = String.format("UPDATE FISH set DAYSUNTIL=6 WHERE ID = (%d);", id);
               stmt.executeUpdate(sql);
               stmt.close();
               c.close();
               return true;  
            }
            catch(Exception e){
               System.out.println("yikes!");
               e.printStackTrace();
            }
         }
         else{
            try{
               stmt = c.createStatement();
               sql = String.format("UPDATE FISH set DAYSUNTIL=(%d) WHERE ID=(%d);",result-1, id);
               stmt.executeUpdate(sql);
               stmt.close();
               c.close();
               return false;  
            }
            catch(Exception e){
               System.out.println("yikes!");
               e.printStackTrace();
            }
         }
         c.close();
      }
      catch(Exception e){
         System.out.println("yikes!");
         e.printStackTrace();
      }
      return false;
   }
}