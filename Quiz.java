import java.util.*;
import java.io.*;

public class Quiz {
	private static String name;
	private static ArrayList<Question> questions = new ArrayList<>();

	public Quiz() {

	}

	public ArrayList<Question> getQuestions() {
		return this.questions;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addQuestions(Question question) {
		this.questions.add(question);
	}

	public static Quiz loadFromFile(String path) throws InvalidQuizFormatException {
		FileReader fr;
		Scanner scan;
		Quiz quiz = new Quiz();

		try {
			fr = new FileReader(path);
	       	scan = new Scanner(fr);
	        quiz = new Quiz();
	        quiz.setName(path.replace(".txt", ""));

	        while(scan.hasNextLine()) {
	       		String question = scan.nextLine();
	       		if(question.contains("{blank}")) {
	       			Fillin fillin = new Fillin();
	       			fillin.setDescription(question.replace("{blank}", "________"));
	       			fillin.setAnswer(scan.nextLine());
	       			quiz.addQuestions(fillin);
	       			if(!scan.hasNextLine()) {
	       				break;
	       			}
	       			scan.nextLine();

	       		} else {
	       			Test test = new Test();
	       			test.setDescription(question);
	       			test.setAnswer(scan.nextLine());
	       			String[] option = new String[]{test.getAnswer(),scan.nextLine(), scan.nextLine(),scan.nextLine()};
	       			test.setOptions(option);
	       			quiz.addQuestions(test);
	       			if(!scan.hasNextLine()) {
	       				break;
			    	}

	       			scan.nextLine();
	       		}
	       }
		} catch(Exception e) {
			throw new InvalidQuizFormatException(e.getMessage());
		}

		return quiz;
	}

	public String toString() {
		return null;
	}

	public void start() {
		Scanner in = new Scanner(System.in);
		System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = =\n");
		System.out.println("WELCOME TO \"" + getName() + "\" QUIZ!");
		System.out.println("_________________________________________________\n");
		int quizSize = questions.size();
		int correctAnswers = 0; 
		Collections.shuffle(questions);
		Test test = new Test();

		for(int i = 0; i < quizSize; i++) {
			System.out.println(i+1 + ". " + questions.get(i).getDescription());
			if(questions.get(i) instanceof Test) {
				System.out.println(questions.get(i).toString());
				System.out.println("--------------------");
				System.out.print("Enter the correct choice: ");
				boolean turn = true;
				int j = 0;
				while(turn) {
					String answer = in.nextLine();
					j = answer.charAt(0) - 65;
					if(j < 0 || j > 3) {
					System.out.print("Invalid choice! Try again (Ex: A, B, ...): ");
					} else {
						turn = false;
					}
				}
				String userAnswer = ((Test)questions.get(i)).getOptionsAt((int)j);
				if(questions.get(i).getAnswer().equals(userAnswer)){
					System.out.println("Correct!");
					correctAnswers++;
				} else {
					System.out.println("Incorrect!");
				}

				System.out.println("_________________________________________________\n");

			} else {
				System.out.println("--------------------");
				System.out.print("Type your answer: ");
				String answer = in.nextLine().toLowerCase();

				if(questions.get(i).getAnswer().toLowerCase().equals(answer)) {
					System.out.println("Correct!");
					correctAnswers++;
				} else {
					System.out.println("Incorrect!");
				}

				System.out.println("_________________________________________________\n");

			}
		}

		System.out.printf("Correct Answers: %d/%d (%.1f%%)\n", correctAnswers, quizSize, (float)correctAnswers / (float)quizSize * 100.0F);
	}
}