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
            new Question("What can you convert from a String and convert it back to a String?", new String[] {"reverse", "toCharArray", "toString", "StringBuilder"}, 4),
            new Question("In UML Class Diagrams, which symbol is to show that the class will be private?", new String[] {"-", "+", ":", "++"}, 1),
            new Question("Which UML Class Relationship is meant to show that one class depends in some way on another?", new String[] {"Association", "Association Class", "Dependency", "Composition"}, 3),
            new Question("What kind of class can only be accessed by class members and subclasses?", new String[] {"Public", "Protected", "Private Package", "Private"}, 2),
            new Question("What command is used in the debugger to print source code?", new String[] {"list", "fields", "step", "stop"}, 1),
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