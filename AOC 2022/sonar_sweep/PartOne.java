import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class PartOne {
  public static void main(String[] args) {
    int count=0;
    int previous=0;
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextInt()) {
                int data = myReader.nextInt();
                if (previous == 0) {
                    previous = data;
                }
                else if (data > previous) {
                    count += 1;
                }
                previous = data;
            }
            System.out.println("count: "+count);
            myReader.close(); 
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}