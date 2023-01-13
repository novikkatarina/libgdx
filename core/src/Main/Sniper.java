package Main;

public class Sniper extends Shooter {
    public Sniper(int x, int y, int amount) {
        super(12, 10, new int[]{8,10}, 15, 9, "Sniper", 32, 32, amount);
        this.position = new Vector2(x, y);
    }
}
