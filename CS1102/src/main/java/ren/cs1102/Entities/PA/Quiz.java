package ren.cs1102.Entities.PA;

//import javax.swing.JOptionPane;
public class Quiz {

	static int nQuestions = 0;
	static int nCorrect = 0;
	
	public static void main(String[] args) {
		
		MultipleChoiceQuestion multiQuestion1 = new MultipleChoiceQuestion("What is the largest island in Thailand","Bangkok","Koh samuai","P.P. island","Greenland","Phuket","e");
		MultipleChoiceQuestion multiQuestion2 = new MultipleChoiceQuestion("What is the former name of Thailand","Big C","Siam","Family Mart","Ayutthaya","IDonotKnow","b");
		MultipleChoiceQuestion multiQuestion3 = new MultipleChoiceQuestion("Which language is spoken by the majority in Thailand","English","Latin","Thai","Spanish","Chinese","c");
		MultipleChoiceQuestion multiQuestion4 = new MultipleChoiceQuestion("What is the largest province in Thailand","Korat","Bangkok","California","Westminster","Lavo","a");
		MultipleChoiceQuestion multiQuestion5 = new MultipleChoiceQuestion("What is the currency used in Thailand","Dollar","Yen","Rupee","Bath","Gold","d");
		
		
		multiQuestion1.Check();
		multiQuestion2.Check();
		multiQuestion3.Check();
		multiQuestion4.Check();
		multiQuestion5.Check();
		
		multiQuestion1.showResult();
		
		
	}
	
	
	

}
