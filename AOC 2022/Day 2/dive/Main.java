import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class Main{
    public static void main (String[] args){
        int position[] = new int[3];
        String fileName = "input.txt";
        File input = new File(fileName);
        Pattern[] directions = new Pattern[3];
        directions[0] = Pattern.compile("forward");
        directions[1] = Pattern.compile("down");
        directions[2] = Pattern.compile("up");
        try {
            Scanner myReader = new Scanner(input);
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                movement (data, directions, position);
                }
            int resultPartOne = position[0]*position[1];
            int resultPartTwo = position[0]*position[2];
            System.out.println("Part One result: "+resultPartOne);
            System.out.println("Part One result: "+resultPartTwo);
            myReader.close();
            }
            catch (FileNotFoundException e) {
                System.out.println(String.format("File %s not found!",fileName));
                e.printStackTrace();
            }
    }
    // iterate over directions and run regex against the line to match to direction + int value
    public static void movement (String data, Pattern[] directions, int[] position) {
        for (int i = 0; i <= 2; i++) {
            Pattern digit = Pattern.compile("\\d+");
            Pattern direction = directions[i];
            Matcher matcher = direction.matcher(data);
            boolean found = matcher.find();
            if(found) {
                Matcher digitMatcher = digit.matcher(data);
                if (digitMatcher.find()) {
                    int matchedDigit = Integer.valueOf(digitMatcher.group());
                    if (i == 0) {
                        position[0] += matchedDigit;
                        position[2] += position[1] * matchedDigit;
                        }
                    else if (i == 1)  {
                        position[1] += matchedDigit;
                    }
                    else {
                        position[1] -= matchedDigit;
                    }
                }
            }
        }
    }
}