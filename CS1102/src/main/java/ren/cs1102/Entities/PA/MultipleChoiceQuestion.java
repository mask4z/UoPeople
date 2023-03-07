package ren.cs1102.Entities.PA;

import javax.swing.JOptionPane;
public class MultipleChoiceQuestion {
	
	static int nQuestions = 0;
	static int nCorrect = 0;
	
	String question;
	String correctAnswer;
	
	
	public MultipleChoiceQuestion(String query, String a, String b, String
			c, String d, String e, String answer) {
		question = query+"\n A. "+a+"\n B. "+b+"\n C. "+c+"\n D. "+d+"\n E. "+e;
		correctAnswer = answer.toUpperCase();  
	}

	public String Ask() {
		 String answer = JOptionPane.showInputDialog(question);
		 String updated = answer.toUpperCase();
		 return updated;
	}
	
	public void Check() {
		
		nQuestions++;
		boolean validAnswer = false;
		
		while(!validAnswer) {
			
			String answer = Ask();
			
			if(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D") || answer.equals("E")) {
				
				validAnswer = true;
				
				if(answer.equals(correctAnswer)) {
					JOptionPane.showMessageDialog(null,"Correct!");
					nCorrect++;
				}else{
					JOptionPane.showMessageDialog(null,"Incorrect answer :(");
				}
			}else {
				JOptionPane.showMessageDialog(null,"Invalid answer X0");
			}
		}
		
	}
	
	public void showResult() {
		JOptionPane.showMessageDialog(null,nCorrect+" correct out of "+nQuestions+ " questions");
	}
	
}
