import java.util.ArrayList;
import java.util.Scanner;

public class QuizManager {
    private ArrayList<Quiz> quizzes;

    public QuizManager() {
        quizzes = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Quiz Generator!");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Take a quiz");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createQuiz(scanner);
                    break;
                case 2:
                    takeQuiz(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void createQuiz(Scanner scanner) {
        System.out.print("Enter the title of the quiz: ");
        String title = scanner.nextLine();
        Quiz quiz = new Quiz(title);

        boolean addingQuestions = true;

        while (addingQuestions) {
            System.out.print("Enter the question: ");
            String question = scanner.nextLine();

            System.out.print("Enter option 1: ");
            String option1 = scanner.nextLine();

            System.out.print("Enter option 2: ");
            String option2 = scanner.nextLine();

            System.out.print("Enter option 3: ");
            String option3 = scanner.nextLine();

            System.out.print("Enter option 4: ");
            String option4 = scanner.nextLine();

            System.out.print("Enter the number of the correct option: ");
            int correctOption = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            quiz.addQuestion(new Question(question, new String[]{option1, option2, option3, option4}, correctOption));

            System.out.print("Do you want to add another question? (yes/no): ");
            String addMore = scanner.nextLine();
            if (!addMore.equalsIgnoreCase("yes")) {
                addingQuestions = false;
            }
        }

        quizzes.add(quiz);
        System.out.println("Quiz created successfully!");
    }

    private void takeQuiz(Scanner scanner) {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Select a quiz:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }

        int quizChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (quizChoice < 1 || quizChoice > quizzes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(quizChoice - 1);
        int score = 0;

        for (Question question : selectedQuiz.getQuestions()) {
            System.out.println(question.getQuestion());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int answer = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (answer == question.getCorrectOption()) {
                score++;
            }
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + selectedQuiz.getQuestions().size());
    }
}

