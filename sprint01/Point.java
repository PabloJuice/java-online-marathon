package Main;

public class Point {
    private int x,y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int[] getXYPair(){
        return (new int[]{x,y});
    }
    public double distance(int x, int y){
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }
    public double distance(Point p){
        return Math.sqrt(Math.pow(p.getX() - this.x, 2) + Math.pow(p.getY() - this.y, 2));
    }
    public double distance(){
        return Math.sqrt(Math.pow(-this.x, 2) + Math.pow(-this.y, 2));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
