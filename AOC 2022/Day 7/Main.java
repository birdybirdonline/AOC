import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.Arrays;

public class Main{
    public static void main (String[] args){
        String fileName = "input.txt";
        File input = new File(fileName);
        try {
            Scanner fileReader = new Scanner(input);
            String[] pos = fileReader.nextLine().split(",");
            int posLength = pos.length;
            int[] crabPos = new int[posLength];
            for(int i = 0; i < posLength; i++){
                crabPos[i] = Integer.parseInt(pos[i]);
            }
            Arrays.sort(crabPos);
            int medianPos = Math.round(posLength/2);
            int median = crabPos[medianPos];
            int total = 0;
            for(int i = 0; i < posLength; i++){
                total+= Math.abs(crabPos[i]-median);
            }
            System.out.println(total);
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found!",fileName));
            e.printStackTrace();
        }
        
    }
}