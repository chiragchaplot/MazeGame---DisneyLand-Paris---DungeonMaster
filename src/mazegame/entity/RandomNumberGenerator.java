package mazegame.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {

    private int numberOfAttempts;
    private int upperBound;
    
    private static final Random randomGenerator = new Random();

    public RandomNumberGenerator(int numberOfAttempts, int upperBound) {
        this.numberOfAttempts = numberOfAttempts;
        this.upperBound = upperBound;
    }

    public RandomNumberGenerator() {
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public int generateNumbers(boolean multipleAttempts) {
        List<Integer> results = new ArrayList<>();

        int attemptsToPerform = multipleAttempts ? this.numberOfAttempts : 1;

        for (int i = 0; i < attemptsToPerform; i++) {
            results.add(1 + randomGenerator.nextInt(this.upperBound));
        }

        return multipleAttempts ? Collections.max(results) : results.get(0);
    }

    public int generateRandomInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max value must be greater than Min value");
        }
        return randomGenerator.nextInt((max - min) + 1) + min;
    }
}
