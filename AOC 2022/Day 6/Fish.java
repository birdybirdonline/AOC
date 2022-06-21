public class Fish {
    public int daysUntil;
    public Fish(int start){
        daysUntil = start;
    }
    public boolean grow(){
        if(daysUntil == 0){
            daysUntil = 6;
            return true;
        }
        else{
            daysUntil--;
            return false;
        }
    }
}
