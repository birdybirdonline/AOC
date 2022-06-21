import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
public class PartTwo {
    public static void main(String[] args) {
    
    // define sliding window via user input arg
    int windowSize = Integer.valueOf(args[0]);
    int count=0;
    int previous=0;
    String fileName = "input.txt";
    File input = new File(fileName);
    int[] triArray = new int[windowSize];
        try {
            Scanner myReader = new Scanner(input);
            int arrayEntryCount = 0;
            while (triArray[2] == 0 && myReader.hasNextInt()) {
                int data = myReader.nextInt();
                triArray[arrayEntryCount] = data;
                arrayEntryCount += 1;
            }
            while (myReader.hasNextInt()) {
                if (previous == 0) {
                    int sum = sum(triArray);
                    previous = sum;
                }
                else {
                    triArray = updateArray(triArray, windowSize);
                    int data = myReader.nextInt();
                    triArray[windowSize-1] = data;
                }
                int sum = sum(triArray);
                if (sum > previous) {
                    count += 1;
                }
                previous = sum;
            }
            System.out.println("count: "+count);
            myReader.close(); 
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found!",fileName));
            e.printStackTrace();
        }
    }
    // method to handle summing the sliding window
    public static int sum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            sum += element;
        }
        return sum;
    }
    // method to update the array to the new sliding window
    public static int[] updateArray(int[] triArray, int windowSize) {
        for (int i = 0; i < windowSize-1; i++) {
            triArray[i] = triArray[i+1];
        }
        return triArray;
    }
}
