package Main;

import java.util.ArrayList;

public abstract class Base implements BaseInterface, Comparable<Base> {
    private final int attack;
    private final int protection;
    private final int[] damage;
    private double health;
    private final int speed;
    private final String name;
    private static int idCounter;
    private final int playerID;
    private final double maxHealth;
    public Vector2 position;
    protected String status;
    protected int amount;
    protected double stackHP;
    protected final double MAXstackHP;
    protected final int initialAmount;
    private boolean saved;
    public boolean hadSteped;


    public Base(int attack, int protection, int[] damage, double health, int speed, String name, int amount) {
        this.saved = false;
        this.attack = attack;
        this.protection = protection;
        this.damage = damage;
        this.health = health;
        this.speed = speed;
        this.name = name;
        playerID = idCounter++;
        maxHealth = health;
        this.status = "ALIVE";
        this.amount = amount;

        this.stackHP = (amount -1)*maxHealth + health;
        this.MAXstackHP = amount * maxHealth;
        this.initialAmount = amount;
    }


    public int valueDamage(Base enemy) {
        int damage;
        if (this.attack == enemy.protection) {
            damage = (this.damage[0] * this.amount + this.damage[1]) / 2;
        } else if (this.attack < enemy.protection) {
            damage = this.damage[1] * this.amount;
        } else {
            damage = this.damage[0] * this.amount;
        }
        if (this.position.distance(enemy.position) > this.speed) {
            damage = damage / 2;
        }
        return damage;
    }

    public void damage(int valueDamage) {

        double stackHP = (this.amount -1)*this.maxHealth + this.health;
        stackHP -= valueDamage; // 34
        if (stackHP <= 0) {
            this.status = "DEAD";
            this.health = 0;
            this.amount = 0;
            return;
        }
        this.amount = (int) (stackHP/ this.maxHealth);
        if (stackHP % this.maxHealth!=0){
            this.health = stackHP - this.maxHealth * this.amount;
            this.amount++;
        }
        if (this.health> this.maxHealth) {this.health = this.maxHealth;}
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean getDelivery() {
        return false;
    }

    public boolean getSaved(){
        return saved;
    }

//    public double getStackHP(){
//        return stackHP;
//    }
//
//    public double getMaxHealth() {
//        return maxHealth;
//    }

    public void heal (){
        this.health += 5;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setDelivery(boolean value) {
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


    public void step(ArrayList<Base> enemy, ArrayList<Base> team) {

    }

    public static ArrayList<Base> sortingByName(ArrayList<Base> unsortedTeam, String name) {
        ArrayList<Base> sortedTeam1 = new ArrayList<>();
        for (int i = 0; i < unsortedTeam.size(); i++) {
            if (unsortedTeam.get(i).getName().equals(name)) {
                sortedTeam1.add(unsortedTeam.get(i));
            }
        }
        return sortedTeam1;
    }

    public static ArrayList<Base> sortedWhiteTeam(ArrayList<Base> team) {
        ArrayList<Base> sortedTeam = new ArrayList<>();
            if (!sortingByName(team, "Sniper").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Sniper"));
            }
            if (!sortingByName(team, "Robber").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Robber"));
            }
            if (!sortingByName(team, "Wizard").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Wizard"));
            }
            if (!sortingByName(team, "Peasant").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Peasant"));
            }
            return sortedTeam;

    }

    public static ArrayList<Base> sortedBlackTeam(ArrayList<Base> team) {
        ArrayList<Base> sortedTeam = new ArrayList<>();
            if (!sortingByName(team, "Xbowman").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Xbowman"));
            }
            if (!sortingByName(team, "Spearman").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Spearman"));
            }

            if (!sortingByName(team, "Monk").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Monk"));
            }
            if (!sortingByName(team, "Peasant").isEmpty()) {
                sortedTeam.addAll(sortingByName(team, "Peasant"));
            return sortedTeam;
        }
        return sortedTeam;
    }


    public int compareTo(Base player) {
        if (this.health > player.health) return 1;
        else if (this.health < player.health) return -1;
        else return 0;
    }





    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + playerID +
                "  Amount  " + amount +
//                " attk= " + attack +
//                ", protect= " + protection +
//                ", damage= " + Arrays.toString(damage) +
                " Health " + health + "  MAXhealth" + maxHealth + "  speed= " + speed + this.coloringStatus()
                + " x " + this.position.x + " y " + this.position.y + " ";
    }

    public String coloringStatus() {
        String str = "null";
        if (this.status.equals("ALIVE")) {
            str = AnsiColors.ANSI_PURPLE + "  ALIVE " + AnsiColors.ANSI_RESET;
        }
        if (this.status.equals("DEAD")) {
            str = AnsiColors.ANSI_RED + "   DEAD" + AnsiColors.ANSI_RESET;
        }
        return str;
    }

}
