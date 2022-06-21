import java.util.ArrayList;

public class Card{
    public int winningDraw;
    public boolean winner = false;
    public int unDrawn = 0;
    public CardNum[][] cardNum = new CardNum[5][5];
    public Card(ArrayList<String[]> lines){
        int linesLength = lines.size();
        for(int i=0; i < linesLength; i++) {
            for(int j=0; j < 5; j++){   
                int currentNum = Integer.parseInt(lines.get(i)[j]);   
                cardNum[i][j] = new CardNum();
                cardNum[i][j].num = currentNum;
                cardNum[i][j].check = false;
                unDrawn += currentNum;
            }
        }
    }
    public class CardNum{
        public int num;
        public boolean check;
    }

    public void checkDraw(int draw){
        // check for draw and mark as true
        for(int i = 0; i < 5; i++){
            for (int j = 0; j <5; j++){
                if(cardNum[i][j].num == draw){
                    cardNum[i][j].check = true;
                    unDrawn -= draw;
                }
            }
        }
    }
    
    public boolean checkWin(){
        // check for win
        int count = 0;
        for(int i = 0 ; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(cardNum[i][j].check){
                    count++;
                }
            }
            if(count == 5){
                winner = true;
                return true;
            }
            else{
                count = 0;
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(cardNum[j][i].check){
                    count++;
                }
            }
            if(count == 5){
                winner = true;
                return true;
            }
            else{
                count = 0;
            }
        }
        return false;
    }
}


