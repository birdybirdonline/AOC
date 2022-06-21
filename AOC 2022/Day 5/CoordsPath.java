public class CoordsPath{
    public int xStart;
    public int yStart;
    public int xEnd;
    public int yEnd;
    public int xRange;
    public int yRange;
    public CoordsPath(int xS, int yS, int xE, int yE){
        xStart = xS;
        yStart = yS;
        xEnd = xE;
        yEnd = yE;
        xRange = xEnd - xStart;
        yRange = yEnd - yStart;
    }
}