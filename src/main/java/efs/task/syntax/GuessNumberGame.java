package efs.task.syntax;

import java.lang.IllegalArgumentException;
import java.util.Scanner;

public class GuessNumberGame {

    private final int secretNumber;
    private final int attemptLimit;
    private int upperBound;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            upperBound = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            handleInvalidArgument(argument, " nie może zostać skonwertowany na int");
        }
        if (upperBound < 1 || upperBound > UsefulConstants.MAX_UPPER_BOUND) {
            handleInvalidArgument(argument, "- powinien być pomiędzy 1 a " + UsefulConstants.MAX_UPPER_BOUND);
        }

        attemptLimit = (int)(Math.log(upperBound) / Math.log(2)) + 1;
        secretNumber = (int)(Math.random() * upperBound + 1);
    }

    public void play() {
        boolean win = false;

        // method that executes the game session
        Scanner input = new Scanner(System.in);
        int guess;
        System.out.println("<1," + upperBound + ">");

        for (int attempt=1; attempt <= attemptLimit; attempt++) {
            drawProgressBar(attempt);

            System.out.println(UsefulConstants.GIVE_ME);
            String text = input.next();
            try {
                guess = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                continue;
            }

            if (guess == secretNumber) {
                System.out.println(UsefulConstants.YES);
                win = true;
                break;
            }

            if (guess < secretNumber){
                System.out.println(UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.TO_MUCH);
            }
        }

        if (win){
            System.out.println(UsefulConstants.CONGRATULATIONS);
        } else {
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }
    }

    private void drawProgressBar(int attempt) {
        System.out.println('[' + "*".repeat(attempt) + ".".repeat(attemptLimit - attempt) + ']');
    }

    private void handleInvalidArgument(String argument, String additionalText) {
        System.out.println(
                "Value " + argument + "is invalid: " + UsefulConstants .WRONG_ARGUMENT + additionalText
        );
        throw new IllegalArgumentException();
    }
}
