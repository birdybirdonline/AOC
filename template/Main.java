import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class Main{
    public static void main (String[] args){
        String fileName = "input.txt";
        File input = new File(fileName);
        try {
            Scanner fileReader = new Scanner(input);
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found!",fileName));
            e.printStackTrace();
        }
    }
}