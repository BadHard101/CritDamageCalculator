package main.java.org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static double basicDmg = 1800;
    static int critRate = 40;
    static double critDmg = 2.13;
    static int fightHits = 50;
    static int fights = 5;
    static Random random = new Random();
    static ArrayList<Double> sums = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static double calcSumCritDmg() {
        double sum = 0;
        for (int j = 0; j < fightHits; j++) {
            int randomNumber = random.nextInt(100) + 1;
            //System.out.println(randomNumber);

            if (randomNumber > critRate) {
                sum += basicDmg;
            } else {
                sum += basicDmg * critDmg;
            }
        }
        return sum;
    }

    static void enterAttackParams() {
        System.out.print("Enter fights: ");
        fights = scanner.nextInt();
        System.out.print("Enter amount of hits for one fight: ");
        fightHits = scanner.nextInt();
        System.out.print("Enter your basic damage (not crit, for example 1800): ");
        basicDmg = scanner.nextDouble();
    }

    static boolean colorFlag = false;
    static void enterNewCritStats() {
        System.out.println();
        if (colorFlag) {
            System.out.println(ConsoleColors.PURPLE_UNDERLINED +
                    "CRIT STATS" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.BLUE_UNDERLINED +
                    "CRIT STATS" + ConsoleColors.RESET);
        }
        System.out.print("Enter your crit rate % (for example 40): ");
        critRate = scanner.nextInt();
        System.out.print("Enter your crit damage % (for example 110): ");
        critDmg = scanner.nextDouble();
        critDmg /= 100;
        critDmg += 1;
    }

    static void printResult(double average, double max, double min) {
        if (colorFlag) {
            System.out.println(ConsoleColors.PURPLE_UNDERLINED +
                    "RESULT" + ConsoleColors.RESET);
            colorFlag = false;
        } else {
            System.out.println(ConsoleColors.BLUE_UNDERLINED +
                    "RESULT" + ConsoleColors.RESET);
            colorFlag = true;
        }

        System.out.println("Avegare dmg: " + ConsoleColors.YELLOW_BOLD_BRIGHT + average +
                ConsoleColors.RESET + "\nFor " + fights + " fights with " +
                fightHits + " hits (without amplification)");

        System.out.println("Max dmg for 1 fight: " + ConsoleColors.GREEN_BOLD_BRIGHT + max + ConsoleColors.RESET);
        System.out.println("Min dmg for 1 fight: " + ConsoleColors.RED_BOLD_BRIGHT + min + ConsoleColors.RESET);
    }
    public static void main(String[] args) {
        enterAttackParams();

        double min;
        double max;
        double average;
        while (true) {
            enterNewCritStats();

            min = Double.MAX_VALUE;
            max = Double.MIN_VALUE;
            sums.clear();

            for (int i = 0; i < fights; i++) {
                double sum = calcSumCritDmg();
                if (sum < min) min = sum;
                if (sum > max) max = sum;
                sums.add(sum);
            }

            average = 0;
            for (Double sum : sums) {
                average += sum;
            }
            average = average/fights;

            printResult(average, max, min);
        }

    }
}