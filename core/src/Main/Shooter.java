package Main;

import java.util.ArrayList;

public abstract class Shooter extends Base {
    private int shots;
    private int maxShots;

    protected Shooter(int attack, int protection, int[] damage,
                      double health, int speed, String name, int shots, int maxShots, int amount) {
        super(attack, protection, damage, health, speed, name, amount);
        this.shots = shots;
        this.maxShots = shots;
    }

    @Override
    public String toString() {
        return super.toString() + " shots= " + shots;
    }

    @Override
    public void step(ArrayList<Base> enemy, ArrayList<Base> team) {
        if (this.status.equals("DEAD")) return;
        if (shots <= 0) return;
        if (shots < maxShots)
         {
            for (int i = 0; i < team.size(); i++) {
                if ((team.get(i).getName().equals("Peasant")) && (team.get(i).getStatus().equals("ALIVE")) && (team.get(i).getDelivery())) {
                    team.get(i).setDelivery(false);
                    this.shots++;
                    break;
                }
            }
        }
        if (shots <= 0) return;
        int nearestEnemy = 0;
        double minDistance = 10000;
        for (int i = 0; i < enemy.size(); i++) {
            if (((this.position.distance(enemy.get(i).getPosition())) < minDistance) && (enemy.get(i).getStatus().equals("ALIVE"))) {
                nearestEnemy = i;
                minDistance = this.position.distance(enemy.get(i).getPosition());
            }
        }
        enemy.get(nearestEnemy).damage(this.valueDamage(enemy.get(nearestEnemy)));
        this.shots--;
    }
}
