import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        int days = 256;
        long[] perms = new long[9];
        try {
            File input = new File("input.txt");
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                String[] fishies = reader.nextLine().split(",");
                for (String fish : fishies) {
                    perms[Integer.parseInt(fish)]++;
                }
            }
            reader.close();
        }
        catch(FileNotFoundException e){
                System.out.println("File not found!");
        }
        while(days != 0){
            long newFish = perms[0];
            perms[0] = perms[1];
            perms[1] = perms[2];
            perms[2] = perms[3];
            perms[3] = perms[4];
            perms[4] = perms[5];
            perms[5] = perms[6];
            perms[6] = perms[7]+newFish;
            perms[7] = perms[8];
            perms[8] = newFish;
            days--;
        }
        long totalFish = 0;
        for(int i = 0; i < 9; i++){
            totalFish += perms[i];
        }
        System.out.println(totalFish);
    }
}