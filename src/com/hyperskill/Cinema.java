package com.hyperskill;

import java.util.Scanner;
import java.lang.String;

public class Cinema {


    public static void main(String[] args){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = read.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = read.nextInt();

        boolean simPrice = rows * seats <= 60;
        boolean stopMenu = false;


        String[][] cinema = new String[rows + 1][seats + 1];

        cinema[0][0] = " ";

        for (int i = 1; i <= seats; i++) {
            cinema[0][i] = String.valueOf(i);
        }

        for (int i = 1; i <= rows; i++) {
            cinema[i][0] = String.valueOf(i);
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                cinema[i][j] = "S";
            }
        }
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = read.nextInt();

            switch (choice) {
                case 1:
                    showSits(cinema, rows, seats);
                    break;
                case 2:
                    buyTicket(cinema, simPrice, rows);
                    break;
                case 3:
                    statistics(cinema, rows, seats, simPrice);
                    break;
                case 0:
                    stopMenu = true;
                    break;
            }
        } while (!stopMenu);
    }


    public static void buyTicket(String[][] cinema, boolean simPrice, int rows) {
        boolean isFree;

        do {
            Scanner read = new Scanner(System.in);
            System.out.println("Enter a row number:");
            int rowNum = read.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNum = read.nextInt();

            if (rowNum < 0 || seatNum < 0 || rowNum > cinema.length - 1 || seatNum > cinema[0].length - 1) {
                System.out.println("Wrong input!");
                isFree = false;
            } else if (cinema[rowNum][seatNum].contains("B")) {
                System.out.println("That ticket has already been purchased!");
                isFree = false;
            } else {
                if (simPrice) {
                    System.out.println("Ticket price: $10");
                } else {
                    if (rowNum <= rows / 2) {
                        System.out.println("Ticket price: $10");
                    } else {
                        System.out.println("Ticket price: $8");
                    }
                }
                isFree = true;
                cinema[rowNum][seatNum] = "B";
            }
        } while (!isFree);
    }

    public static void showSits(String[][] cinema, int rows, int seats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void statistics(String[][] cinema, int rows, int seats, boolean simPrice) {
        int soldTickets = 0;
        int total = 0;
        int currentIncome = 0;

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (cinema[i][j].contains("B") && simPrice) {
                    currentIncome += 10;
                } else if (cinema[i][j].contains("B")){
                    if (i > rows / 2) {
                        currentIncome += 8;
                    } else {
                        currentIncome += 10;
                    }
                }
            }
        }

        if (simPrice) {
            total = rows * seats * 10;
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    total += i >= rows / 2 ? 8 : 10;
                }
            }
        }

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (cinema[i][j].contains("B")) {
                    soldTickets++;
                }
            }
        }
        System.out.printf("Number of purchased tickets: %d", soldTickets);

        double percentageOfSoldTickets = (soldTickets * 1.0 / (rows * 1.0 * seats * 1.0)) * 100;
        System.out.printf("%nPercentage: %.2f%%", percentageOfSoldTickets);

        System.out.printf("%nCurrent income: $%d", currentIncome);

        System.out.println("\nTotal income: $" + total);


    }
}

