package Main;

import java.util.ArrayList;

public class Robber extends Base {
    public Robber(int x, int y, int amount) {
        super(8, 3, new int[]{2, 4}, 10, 6, "Robber", amount);
        super.position = new Vector2(x, y);
    }

    @Override
    public void step(ArrayList<Base> enemy, ArrayList<Base> team) {
        if (this.status.equals("DEAD")) return;
        int nearestEnemy = -1;
        double minDistance = 10000;
        for (int i = 0; i < enemy.size(); i++) {
            if (((this.position.distance(enemy.get(i).getPosition())) < minDistance) && (enemy.get(i).getStatus().equals("ALIVE"))) {
                nearestEnemy = i;
                minDistance = (int) this.position.distance(enemy.get(i).getPosition());
            }
        }
        if (minDistance <= 1){
            enemy.get(nearestEnemy).damage(this.valueDamage(enemy.get(nearestEnemy)));
            return;}
        if (nearestEnemy != -1){
            int x = getPosition().x;
        int y = getPosition().y;
        if (enemy.get(nearestEnemy).getPosition().y > this.position.y && (checkPosibility(new Vector2(x, y+1), enemy, team) && (y<10))) {
            y++;
            setPosition(new Vector2(x, y));
        } else if (enemy.get(nearestEnemy).getPosition().y < this.position.y && (checkPosibility(new Vector2(x,y-1), enemy, team)) && (1<y)) {
            y--;
            setPosition(new Vector2(x, y));
        } else if (enemy.get(nearestEnemy).getPosition().x > this.position.x && (checkPosibility(new Vector2(x+1, y), enemy, team))&& (x<10)) {
            x++;
            setPosition(new Vector2(x, y));
        } else if (enemy.get(nearestEnemy).getPosition().x < this.position.x && (checkPosibility(new Vector2(x-1, y), enemy, team))&& (1<x)) {
            x--;
            setPosition(new Vector2(x, y));
        }
        }
    }

    private boolean checkPosibility(Vector2 position, ArrayList<Base> enemy, ArrayList<Base> team) {
        for (Base friend: team) {
            if (friend.position.isEqual(position) && friend.status.equals("ALIVE"))
                return false;
        }

        for (Base foe: enemy) {
            if (foe.position.isEqual(position) && foe.status.equals("ALIVE"))
                return false;
        }

        return true;

    }
}

