package Main;

import java.util.Collections;

public class ConsoleView {
    public static int step = 0;
    private static final String top10 = formatDiv("a") + String.join("",
            Collections.nCopies(9, formatDiv("-b"))) + formatDiv("-c");
    private static final String mid10 = formatDiv("d") + String.join("",
            Collections.nCopies(9, formatDiv("-e"))) + formatDiv("-f");
    private static final String bottom10 = formatDiv("g") + String.join("",
            Collections.nCopies(9, formatDiv("-h"))) + formatDiv("-i");

    public static void view() {
        if (step++ == 0) {
            System.out.println(AnsiColors.ANSI_RED + "First step!" + AnsiColors.ANSI_RESET);
        } else {
            System.out.println(AnsiColors.ANSI_RED + "Step: " + step + AnsiColors.ANSI_RESET);
        }

        System.out.println(ConsoleView.top10);
        String infoHeroWhiteTeam = " ";
        String infoHeroBlackTeam = " ";
        for (int i = 1; i <= 9; i++) {
            infoHeroWhiteTeam = " ";
            infoHeroBlackTeam = " ";
            for (int j = 1; j <= 10; j++) {
                System.out.print(getChar(new Vector2(i, j)));

                for (int k = 0; k < Main.GANG_SIZE; k++) {
                    if (Main.whiteteam.get(k).getPosition().isEqual(new Vector2(i, j))&&(!Main.whiteteam.get(k).getStatus().equals("DEAD")))
                        infoHeroWhiteTeam = printwithspaces(AnsiColors.ANSI_BLUE + Main.whiteteam.get(k).toString() + AnsiColors.ANSI_RESET);
                    if (Main.whiteteam.get(k).getPosition().isEqual(new Vector2(i, j))&&(Main.whiteteam.get(k).getStatus().equals("DEAD")))
                        infoHeroWhiteTeam = printwithspaces(AnsiColors.ANSI_RED + Main.whiteteam.get(k).toString() + AnsiColors.ANSI_RESET);

                    if (Main.blackteam.get(k).getPosition().isEqual(new Vector2(i, j))&&(!Main.blackteam.get(k).getStatus().equals("DEAD")))
                        infoHeroBlackTeam = printwithspaces(AnsiColors.ANSI_GREEN + Main.blackteam.get(k).toString() + AnsiColors.ANSI_RESET);
                    if (Main.blackteam.get(k).getPosition().isEqual(new Vector2(i, j))&&(Main.blackteam.get(k).getStatus().equals("DEAD")))
                        infoHeroBlackTeam = printwithspaces(AnsiColors.ANSI_RED + Main.blackteam.get(k).toString() + AnsiColors.ANSI_RESET);

                }
            }

            System.out.print("|");
            System.out.print(infoHeroWhiteTeam + "  "+  infoHeroBlackTeam);
            System.out.println();




            System.out.print(ConsoleView.mid10);

            System.out.println();
        }
        for (int j = 1; j <= 10; j++) {
            System.out.print(getChar(new Vector2(10, j)));
            for (int k = 0; k < Main.GANG_SIZE; k++) {
            if (Main.whiteteam.get(k).getPosition().isEqual(new Vector2(10, j))&&(!Main.whiteteam.get(k).getStatus().equals("DEAD")))
                infoHeroWhiteTeam = printwithspaces(AnsiColors.ANSI_BLUE + Main.whiteteam.get(k).toString()+ AnsiColors.ANSI_RESET);
            else if (Main.whiteteam.get(k).getPosition().isEqual(new Vector2(10, j))&&(Main.whiteteam.get(k).getStatus().equals("DEAD")))
                infoHeroWhiteTeam = printwithspaces(AnsiColors.ANSI_RED + Main.whiteteam.get(k).toString()+ AnsiColors.ANSI_RESET);
            if (Main.blackteam.get(k).getPosition().isEqual(new Vector2(10, j))&&(!Main.blackteam.get(k).getStatus().equals("DEAD")))
                infoHeroBlackTeam = printwithspaces(AnsiColors.ANSI_GREEN + Main.blackteam.get(k).toString()+ AnsiColors.ANSI_RESET);
            else if (Main.blackteam.get(k).getPosition().isEqual(new Vector2(10, j))&&(Main.blackteam.get(k).getStatus().equals("DEAD")))
                infoHeroBlackTeam = printwithspaces(AnsiColors.ANSI_RED + Main.blackteam.get(k).toString()+ AnsiColors.ANSI_RESET);}


        }
        System.out.print("|");
        System.out.print("");
        System.out.println(infoHeroWhiteTeam + "  "+  infoHeroBlackTeam);
        System.out.println(ConsoleView.bottom10);
    }

    private static String getChar(Vector2 position) {
        String str = "| ";
        for (int i = 0; i < Main.GANG_SIZE; i++) {
            if (Main.blackteam.get(i).getPosition().isEqual(position)&&(!Main.blackteam.get(i).getStatus().equals("DEAD")))
                str = "|" + AnsiColors.ANSI_GREEN + Main.blackteam.get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
            else if (Main.blackteam.get(i).getPosition().isEqual(position)&&(Main.blackteam.get(i).getStatus().equals("DEAD")))
                str = "|" + AnsiColors.ANSI_RED + Main.blackteam.get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
            if (Main.whiteteam.get(i).getPosition().isEqual(position)&&(!Main.whiteteam.get(i).getStatus().equals("DEAD")))
                str = "|" + AnsiColors.ANSI_BLUE + Main.whiteteam.get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
            else if (Main.whiteteam.get(i).getPosition().isEqual(position)&&(Main.whiteteam.get(i).getStatus().equals("DEAD")))
                str = "|" + AnsiColors.ANSI_RED + Main.whiteteam.get(i).getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;

        }
        return str;
    }



    public static String addspace (int s){
        String strspaces = "";
        for (int i = 1; i < s ; i++)
            strspaces = strspaces + " ";
        return strspaces;
    }
    public static String printwithspaces(String infohero) {

        if (infohero.length() < 93){
            int s = 93 - infohero.length();
            infohero = infohero + addspace(s);
        }
        return infohero;}



        private static String printInfo(Vector2 position, int i)
    {
        return "";
    }

    private static String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500')
                .replace("s", "...")
                .replace("o", "___");
    }
}
