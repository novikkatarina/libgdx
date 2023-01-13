package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static int GANG_SIZE = 10;
    public static ArrayList<Base> whiteteam = new ArrayList<>();
    public static ArrayList<Base> blackteam = new ArrayList<>();

    public static void main(String[] args) {

        FillWhiteTeam(whiteteam);
        FillBlackTeam(blackteam);
        ArrayList<Base> sortedWhiteTeam = Base.sortedWhiteTeam(whiteteam);
        ArrayList<Base> sortedBlackTeam = Base.sortedBlackTeam(blackteam);

//
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//            for (int i = 0; i < blackteam.size(); i++) {
//                System.out.println(whiteteam.get(i));
//                System.out.println(blackteam.get(i));
//            }
//            ConsoleView.view();
//            scanner.nextLine();
//
//            for (int i = 0; i < whiteteam.size(); i++) {
//                sortedWhiteTeam.get(i).step(blackteam, whiteteam);
//                sortedBlackTeam.get(i).step(whiteteam, blackteam);
//            }

        }



    static void FillWhiteTeam(ArrayList<Base> list) {
        int x = 1;
        int y = 1;

        list.add(new Peasant(x++, y, 4));
        list.add(new Robber(x++, y, 3));
        list.add(new Sniper(x++, y, 2));
        list.add(new Wizard(x, y, 1));
    }

    static void FillBlackTeam(ArrayList<Base> list) {
        int x = 1;
        int y = 10;
        list.add(new Peasant(x++, y, 4));
        list.add(new Monk(x++, y, 3));
        list.add(new Spearman(x++, y, 2));
        list.add(new Xbowman(x, y, 1));
    }

    public void step(){
        step();
    }

}


