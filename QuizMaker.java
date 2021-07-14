public class QuizMaker {
	public static void main(String[] args) throws InvalidQuizFormatException {
		Quiz quiz = new Quiz();
		quiz.loadFromFile(args[0]);
		quiz.start();
	}
}