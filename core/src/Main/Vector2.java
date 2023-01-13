package Main;

public class Vector2 {
    public int x,y;
    public Vector2(int x,int y){
        this.x = x;
        this.y = y;
    }
    public boolean isEqual(Vector2 pos){
        if (pos.y==y & pos.x==x) return true;
        else return false;
    }

    public double distance (Vector2 enemy){
        double distance = Math.sqrt(Math.pow((this.x - enemy.x),2) + Math.pow((this.y - enemy.y),2));
        return distance;
    }
}
