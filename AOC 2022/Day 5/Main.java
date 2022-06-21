import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;  

public class Main{
    public static int[][] coordsMap = new int[999][999];
    public static ArrayList<CoordsPath> coordsPaths = new ArrayList<>();
    public static void main (String[] args){
        String fileName = "input.txt";
        File input = new File(fileName);
        try {
            Scanner fileReader = new Scanner(input);
            while(fileReader.hasNextLine()){
                String nxt = fileReader.nextLine();
                ArrayList<String> splitString = new ArrayList<String>(Arrays.asList(nxt.split("(,+)|(\\s->\\s)")));
                int xS = Integer.parseInt(splitString.get(0));
                int yS = Integer.parseInt(splitString.get(1));
                int xE = Integer.parseInt(splitString.get(2));
                int yE = Integer.parseInt(splitString.get(3));
                CoordsPath thisCoord = new CoordsPath(xS, yS, xE, yE);
                coordsPaths.add(thisCoord);
                processCoords(thisCoord);
            }
            evaluateAll();
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found!",fileName));
            e.printStackTrace();
        }
    }
    public static void processCoords(CoordsPath path){
        int p = 1;
        if(path.xRange == 0){
            if(path.yRange < 0){
                p = -1;
            }
            for(int i = path.yStart; i != path.yEnd+p; i+=p){

                coordsMap[path.xStart][i] += 1;
            }
        }
        else if(path.yRange == 0){
            if(path.xRange < 0){
                p = -1;
            }
            for(int i = path.xStart; i != path.xEnd+p; i+=p){

                coordsMap[i][path.yStart] ++;
            }
        }
        else{
            // if axis range is positive, axis incrementer is positive, vice versa

            int ip = 1;
            int xp = 1;
            int yp = 1;

            int diagRange;
            if(Math.abs(path.xRange) > Math.abs(path.yRange)){
                diagRange = path.xRange;
            }
            else{
                diagRange = path.yRange;
            }

            if(diagRange < 0){
                ip = -1;
            }

            if(path.xRange < 0){
                xp = -1;
            }
            if(path.yRange < 0){
                yp = -1;
            }
            for(int i = 0; i != diagRange+ip; i+=ip){
                if(i == 0){
                coordsMap[path.xStart][path.yStart]++;
                }
                else{
                    coordsMap[path.xStart+=xp][path.yStart+=yp]++;
                }
            }

        }
        return;
    }
    public static void evaluateAll(){
        int count = 0;
        for(int i = 0; i < 999; i++){
            for(int j = 0; j < 999; j++){
                if(coordsMap[i][j] > 1){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    // public class Coord{
    //     int coordVal;
    // }
}