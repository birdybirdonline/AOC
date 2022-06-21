import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main (String[] args){
        ArrayList<Card> cards = new ArrayList<>();
        String fileName = "input.txt";
        List<String> draws = new ArrayList<>();
        File input = new File(fileName);
        try {
            boolean firstLine = true;
            Scanner fileReader = new Scanner(input);
            while (fileReader.hasNextLine()){
                String nxt = fileReader.nextLine();
                if (firstLine == true) {
                    draws = new ArrayList<String>(Arrays.asList(nxt.split(",")));
                    firstLine = false;
                }
                else if (firstLine == false) {
                    cards = processCards(fileReader, cards);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found!",fileName));
            e.printStackTrace();
        }
        BingoDraw(draws, cards);
    }

    private static ArrayList<Card> processCards(Scanner fileReader, ArrayList<Card> cards){
        while (fileReader.hasNextLine()){
            ArrayList<String[]> linesForCard = new ArrayList<>();
            String nxt = fileReader.nextLine();
            if (nxt == ""){
                nxt = fileReader.nextLine();
            }
            int linesCount = 5;
            while(linesCount > 0){
                ArrayList<String> line = new ArrayList<>(Arrays.asList(nxt.split(" ")));
                int size = line.size();
                for(int i = 0; i < size; i++){
                    if(line.get(i) == ""){
                        line.remove(i);
                        size--;
                    }
                }
                String[] lineForCard = line.toArray(new String[size]);
                linesForCard.add(lineForCard);
                if(fileReader.hasNextLine()){
                nxt = fileReader.nextLine();
                }
                linesCount--;
            }
            cards.add(new Card(linesForCard));
        }
        return cards;
    }

    public static void BingoDraw(List<String> draws, ArrayList<Card> cards){

        ArrayList<WinNode> winNodes = new ArrayList<>();
        
        for(Card card : cards){
            for(String drawn : draws){
                int draw = Integer.parseInt(drawn);
                card.checkDraw(draw);
                if(card.checkWin()){
                    winNodes.add(new WinNode(card, draws.indexOf(drawn)));
                    break;
                }
            }
        }

        int bestDraw = 0;
        Card winner = null;
        for(WinNode node : winNodes){
            if(node.thisDraw > bestDraw){
                winner = node.thisCard;
                bestDraw = node.thisDraw;
            }
        }
        
        int score = winner.unDrawn * Integer.parseInt(draws.get(bestDraw));
        System.out.println(score); 
    }
}
class WinNode{
    public Card thisCard;
    public int thisDraw;
    public WinNode(Card card, int draw){
        thisCard = card;
        thisDraw = draw;
    }
}