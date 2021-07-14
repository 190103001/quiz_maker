import java.util.*;

public class Test extends Question {
	private final int numOfOptions = 4;
	private String[] options = new String[numOfOptions];
	private ArrayList<Character> labels = new ArrayList<>(); 

	public Test() {
		byte letter = 65;
		for(int i = 0; i < this.numOfOptions; ++i) {
			this.labels.add((char)(letter + i));
		}
	}

	public String getOptionsAt(int option) {
		return this.options[option];
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public String toString() {
		String option = "";
		List list = Arrays.asList(this.options);
        Collections.shuffle(list);
		for(int i = 0; i < 4; i++) {
			option += labels.get(i)+") "+ list.get(i) + (i == 3 ? "" : "\n");
		}

		return option;
	}
}