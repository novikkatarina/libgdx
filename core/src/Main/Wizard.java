package Main;

import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Mage {
    public Wizard(int x, int y, int amount) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Wizard", amount);
        this.position = new Vector2(x, y);
    }

    @Override
        public void step(ArrayList<Base> enemy, ArrayList<Base> team) {
            if (this.status.equals("DEAD")) return;
            for (int i = 0; i < team.size(); i++) {
                if ((team.get(i).stackHP) < 0.75 * (team.get(i).MAXstackHP)) {
                    if (saving(team.get(i), "Shooter")) {
                        return;
                    }
                    if (saving(team.get(i), "Mage")) {
                        return;
                    }
                }
            }
            for (int i = 0; i < team.size(); i++) {
                if ((team.get(i).stackHP) < 0.75 * (team.get(i).MAXstackHP)) {
                    if (team.get(i).getName().equals("Shooter") && (team.get(i).getStatus().equals("ALIVE"))) {team.get(i).heal();
                        return;}
                    if ((team.get(i).getName().equals("Mage")) && (team.get(i).getStatus().equals("ALIVE"))) {team.get(i).heal();
                        return;}
                }
            }
            boolean y = true;
            for (int i = 0; i < team.size(); i++) {
                if ((team.get(i).stackHP) < 0.75 * (team.get(i).MAXstackHP)) {
                    y = false;
                    break;
                }
            }
            Random random = new Random();
            int j = random.nextInt(enemy.size());
            if (y) enemy.get(j).damage(5);
        }

        public boolean saving(Base hero, String name) {
            if ((hero.getName().equals(name)) && (hero.amount < hero.initialAmount) && (!hero.getSaved())) {
                hero.amount++;
                hero.setHealth(1);
                hero.setSaved(true);
                if (hero.getStatus().equals("DEAD")) hero.setStatus("ALIVE");
                return true;
            }
            return false;
        }
}
