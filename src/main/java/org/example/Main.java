package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    private static final int MAX_PANCAKES = 12;
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int NUM_USERS = 3;

    public static void main(String[] args) {
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("Starting Time: " + startTime.format(formatter));

        LocalDateTime endTime = startTime.plusSeconds(30);
        System.out.println("Ending Time: " + endTime.format(formatter));

        int[] userPancakes = new int[NUM_USERS];
        int remainingPancakes = MAX_PANCAKES;

        while (LocalDateTime.now().isBefore(endTime) && remainingPancakes > 0) {
            for (int i = 0; i < NUM_USERS; i++) {
                if (userPancakes[i] < MAX_PANCAKES_PER_USER) {
                    int maxPancakesToEat = Math.min(MAX_PANCAKES_PER_USER - userPancakes[i], remainingPancakes);
                    int pancakesToEat = random.nextInt(maxPancakesToEat) + 1;
//                    int pancakesToEat = 1;

                    userPancakes[i] += pancakesToEat;
                    remainingPancakes -= pancakesToEat;
                }
            }
        }

        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current Time: " + currentTime.format(formatter));

        boolean allOrdersMet = true;
        int totalPancakeWaste = 0;
        for (int i = 0; i < NUM_USERS; i++) {
            if (userPancakes[i] < MAX_PANCAKES_PER_USER) {
                allOrdersMet = false;
            } else if (userPancakes[i] > MAX_PANCAKES_PER_USER) {
                totalPancakeWaste += userPancakes[i] - MAX_PANCAKES_PER_USER;
            }
        }

        System.out.println("Shopkeeper successfully met the needs of the 3 users: " + allOrdersMet);
        System.out.println("Total pancakes eaten by each user:");
        for (int i = 0; i < NUM_USERS; i++) {
            System.out.println("User " + (i + 1) + ": " + userPancakes[i]);
        }
        System.out.println("Total pancakes wasted: " + totalPancakeWaste);
        System.out.println("Number of pancake orders not met: " + (allOrdersMet ? 0 : NUM_USERS));
    }
}
