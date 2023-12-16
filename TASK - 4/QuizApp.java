import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private Map<String, String[]> questions; // Map of questions with options
    private Map<String, String> correctAnswers; // Map of correct answers for each question
    private int userScore;

    public Quiz() {
        questions = new HashMap<>();
        correctAnswers = new HashMap<>();
        userScore = 0;

        // Add sample quiz questions (you can replace these with your own questions)
        addQuestion("the term used to refer to horizontal page orientation?",
                new String[]{"A. portrait", "B. alignment", "C. table", "D. landscape"}, "D");
        addQuestion("Which planet is Largest In Solar System?",
                new String[]{"A. Venus", "B. Mars", "C. Jupiter", "D. Saturn"}, "C");
        addQuestion(" Which Is Most Sensitive Organ In Our Body ",
                new String[]{"A. eye", "B. face ", "C. hands", "D. skin"}, "D");
    }

    private void addQuestion(String question, String[] options, String correctAnswer) {
        questions.put(question, options);
        correctAnswers.put(question, correctAnswer);
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (String question : questions.keySet()) {
            displayQuestion(question);

            // Set up a timer for each question (10 seconds)
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up! Moving to the next question.");
                    scanner.nextLine(); // Consume the newline character
                    timer.cancel(); // Stop the timer
                }
            }, 10000);

            // Get user's answer
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().toUpperCase();

            // Check user's answer
            if (userAnswer.equals(correctAnswers.get(question))) {
                System.out.println("Correct!\n");
                userScore++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + correctAnswers.get(question) + "\n");
            }

            // Cancel the timer as the user has submitted an answer
            timer.cancel();
        }

        // Display final score and summary
        System.out.println("Quiz completed! Your final score: " + userScore + "/" + questions.size());
        displaySummary();

        scanner.close();
    }

    private void displayQuestion(String question) {
        System.out.println("\n" + question);
        String[] options = questions.get(question);
        for (String option : options) {
            System.out.println(option);
        }
    }

    private void displaySummary() {
        System.out.println("Summary:");
        for (String question : questions.keySet()) {
            System.out.println(question);
            System.out.println("Correct Answer: " + correctAnswers.get(question));
            System.out.println();
        }
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}
