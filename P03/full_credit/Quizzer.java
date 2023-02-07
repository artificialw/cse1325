import java.util.Scanner;

class Question {
    private String question;
    private String[] answers;
    private int rightAnswer;
    private final int questionNumber;
    private static int nextQuestionNumber = 1;

    public Question(String question, String[] answers, int rightAnswer) {
        this.question = question;
        this.answers = answers;
        if (rightAnswer < 1 || rightAnswer > answers.length) {
            throw new IllegalArgumentException("Invalid answer.");
        }
        this.rightAnswer = rightAnswer;
        this.questionNumber = nextQuestionNumber++;
    }

    public boolean checkAnswer(int proposedAnswer) {
        return proposedAnswer == rightAnswer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionNumber + ". " + question + "\n");
        for (int i = 0; i < answers.length; i++) {
            sb.append(" " + (i + 1) + ") " + answers[i] + "\n");
        }
        return sb.toString();
    }
}

class Quiz {
    private Question[] questions;

    public Quiz() {
        loadQuiz();
    }

    private void loadQuiz() {
        questions = new Question[] {
            new Question("How many timezones does Russia have?", new String[] {"Nine", "Ten", "Eleven", "Twelve"}, 3),
            new Question("What is the most populated city in the world?", new String[] {"Mexico City", "Tokyo", "Mumbai", "New York City"}, 2),
        };
    }

    public double takeQuiz() {
        Scanner sc = new Scanner(System.in);
        int correctAnswers = 0;
        for (Question question : questions) {
            System.out.println(question);
            System.out.println("Your answer? ");
            int proposedAnswer = sc.nextInt();
            if (question.checkAnswer(proposedAnswer)) {
                correctAnswers++;
            }
        }
        return (double) correctAnswers / questions.length;
    }
}

class Quizzer {
    public static void main(String[] args) {
        try {
            Quiz quiz = new Quiz();
            double score = quiz.takeQuiz();
            System.out.println("Your score is: " + score * 100 + "%");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}