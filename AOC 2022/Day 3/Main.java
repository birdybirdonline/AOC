
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main (String[] args){
        String gamma = "";
        String epsilon = "";
        String fileName = "input2.txt";
        File input = new File(fileName);
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<String> linesCopy = new ArrayList<String>();
        try {
            int count = 0;
            Scanner fileReader = new Scanner(input);
            int byteLength = fileReader.nextLine().length();
            int[] tracker = new int[byteLength];
            while (fileReader.hasNextLine()){
                count += 1;
                String line = fileReader.nextLine();
                lines.add(line);
                linesCopy.add(line);
                for(int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) -'0' == 1) {
                        tracker[i] += 1;
                    }
                }
            }
            for(int i = 0; i < byteLength; i++) {
                if (count / 2 < tracker[i]) {
                    gamma += "1";
                    epsilon += "0";
                }
                else{
                    gamma += "0";
                    epsilon += "1";
                }
            }
            int solution = Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
            System.out.println("Part one: "+solution);
            int partTwoSolution = partTwo(lines, linesCopy, byteLength);
            System.out.println("Part two: "+partTwoSolution);
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found!",fileName));
            e.printStackTrace();
        }
    }
    public static int partTwo(ArrayList<String> lines, ArrayList<String> linesCopy, int byteLength) {

        int bit;
        
        for (int bitPos = 0; bitPos < byteLength; bitPos++) {
            if(lines.size() == 1) {
                break;
            }
            else {
                bit = getGammaEpsilon(lines, bitPos);
                if (bit == 2) {
                    for(int line = 0; line < lines.size(); line++) {
                        String nxt = lines.get(line);
                        if (nxt.charAt(bitPos)-'0' == 0) {
                            lines.remove(line);
                            line -= 1;
                        }
                    }
                }
                else {
                    for(int line = 0; line < lines.size(); line++) {
                        String nxt = lines.get(line);
                        if (nxt.charAt(bitPos)-'0' != bit) {
                            lines.remove(line);
                            line -= 1;
                        }
                    }
                }
            }
        }   
        
        for (int bitPos = 0; bitPos < byteLength; bitPos++) {
            if(linesCopy.size() == 1) {
                break;
            }
            else {
                bit = getGammaEpsilon(linesCopy, bitPos);
                if (bit == 2) {
                    for(int line = 0; line < linesCopy.size(); line++) {
                        String nxt = linesCopy.get(line);
                        if (nxt.charAt(bitPos)-'0' == 1) {
                            linesCopy.remove(line);
                            line -= 1;
                        }
                    }
                }
                else {
                    for(int line = 0; line < linesCopy.size(); line++) {
                        String nxt = linesCopy.get(line);
                        if (nxt.charAt(bitPos)-'0' == bit) {
                            linesCopy.remove(line);
                            line -= 1;
                        }
                    }
                }
            }
        }
        System.out.println(lines.size());
        int solution = Integer.parseInt(lines.get(0), 2) * Integer.parseInt(linesCopy.get(0), 2);
        return solution;
    }
    public static int getGammaEpsilon(ArrayList<String> lines, int bitPosition) {

        int zeroTracker = 0;
        int oneTracker = 0;

        for (int i = 0; i < lines.size(); i++) {
            String nxt = lines.get(i);
            if (nxt.charAt(bitPosition)-'0' == 0){
                zeroTracker += 1;
            }
            else{
                oneTracker += 1;
            }
        }
        if (zeroTracker > oneTracker) {
            return 0;
        }
        if (zeroTracker < oneTracker) {
            return 1;
        }
        else {
            return 2;
        }
    }
}