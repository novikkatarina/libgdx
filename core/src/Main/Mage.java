package Main;

public abstract class Mage extends Base {
    private boolean magic;

    protected Mage(int attack, int protection, int[] damage,
            double health, int speed, String name, int amount) {
        super(attack, protection, damage, health, speed, name, amount);
        this.magic = true;
    }

    @Override
    public String toString() {
        return super.toString() + " magic ";
    }


}
