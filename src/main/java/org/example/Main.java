package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main{
    private static final int MAX_PANCAKES = 12;
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int NUM_USERS = 3;

    public static void main(String[] args) {
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("Starting Time: " + startTime.format(formatter));

        int pancakesMade = random.nextInt(MAX_PANCAKES + 1);
        System.out.println("Number of pancakes made by the shopkeeper: " + pancakesMade);

        int[] pancakeOrders = new int[NUM_USERS];
        for (int i = 0; i < NUM_USERS; i++) {
            pancakeOrders[i] = random.nextInt(MAX_PANCAKES_PER_USER + 1);
        }

        boolean isAllOrdersMet = true;
        int totalPancakesEaten = 0;
        int totalPancakeWaste = 0;
        for (int i = 0; i < NUM_USERS; i++) {
            int pancakesToEat = Math.min(pancakeOrders[i], MAX_PANCAKES_PER_USER);
            totalPancakesEaten += pancakesToEat;
            if (pancakesToEat < pancakeOrders[i]) {
                isAllOrdersMet = false;
                totalPancakeWaste += pancakeOrders[i] - pancakesToEat;
            }
        }

        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("Ending Time: " + endTime.format(formatter));

        System.out.println("Shopkeeper successfully met the needs of the 3 users: " + isAllOrdersMet);
        System.out.println("Total pancakes eaten: " + totalPancakesEaten);
        System.out.println("Total pancakes wasted: " + totalPancakeWaste);
        System.out.println("Number of pancake orders not met: " + (isAllOrdersMet ? 0 : NUM_USERS));
    }
}
