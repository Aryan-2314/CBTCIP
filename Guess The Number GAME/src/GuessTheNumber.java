import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumber extends JFrame {
    private int randomNumber;
    private int attempts;
    private int maxAttempts = 10;
    private int score;
    private int rounds = 3;
    private int currentRound = 1;

    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JLabel roundLabel;

    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guessText = guessField.getText();
            try {
                int guess = Integer.parseInt(guessText);
                attempts++;
                attemptsLabel.setText("Attempts: " + attempts);

                if (guess < randomNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else if (guess > randomNumber) {
                    messageLabel.setText("Too high! Try again.");
                } else {
                    score += (maxAttempts - attempts + 1);
                    scoreLabel.setText("Score: " + score);
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " + attempts + " attempts.");
                    nextRound();
                }

                if (attempts >= maxAttempts) {
                    JOptionPane.showMessageDialog(null, "You've reached the maximum number of attempts. The number was: " + randomNumber);
                    nextRound();
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        }
    }

    private void nextRound() {
        if (currentRound < rounds) {
            currentRound++;
            randomNumber = generateRandomNumber();
            attempts = 0;
            attemptsLabel.setText("Attempts: 0");
            guessField.setText("");
            messageLabel.setText("Enter your guess and click Guess.");
            roundLabel.setText("Round: " + currentRound + "/" + rounds);
        } else {
            JOptionPane.showMessageDialog(null, "Game over! Your final score is: " + score);
            resetGame();
        }
    }

    private void resetGame() {
        currentRound = 1;
        randomNumber = generateRandomNumber();
        attempts = 0;
        score = 0;
        attemptsLabel.setText("Attempts: 0");
        guessField.setText("");
        messageLabel.setText("Enter your guess and click Guess.");
        scoreLabel.setText("Score: 0");
        roundLabel.setText("Round: 1/" + rounds);
    }


    public GuessTheNumber() {
        setTitle("Guess the Number Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        randomNumber = generateRandomNumber();
        attempts = 0;
        score = 0;

        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        add(promptLabel);

        guessField = new JTextField(10);
        add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        add(guessButton);

        messageLabel = new JLabel("Enter your guess and click Guess.");
        add(messageLabel);

        attemptsLabel = new JLabel("Attempts: 0");
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        roundLabel = new JLabel("Round: 1/" + rounds);
        add(roundLabel);
    }

    public static void main(String[] args) {
        GuessTheNumber game = new GuessTheNumber();
        game.setVisible(true);
    }
}
