
package cst8284.quizMaster;

/**<pre>
 * 	File name: QA.java 
 *  Author: modified by  Anastasia Moskvinova, 040871379 
 *  Course: CST8132 – OOP
 *  Assignment: 3
 *  Date: Dec 03, 2016
 *  Professor: Dave Houtman
 *  Purpose:  QA is a Concrete implementation for an object of Question/Answers,
 *  it extends the abstract class QuesAns class and provide the implementation
 *  of the abstracted functions.<pre>
 * @Copyright Dave Houtman 2016.  For use in Fall 2016 - CST8284 classes only 
 * @author Dave Houtman
 * @version 2.0
 * @since 1.0
 * @see String
 * 
 */
public class QA extends QuesAns {

	private static final long serialVersionUID = 1L;
	private String question, explanation, author, rightWrong;
	private int correctAnswer, difficulty, points, qn;
	private String[] answers;
	private boolean result;
	
	
	
	/**mutator for result*/
	public void setRightwrong(String rightWrong) {
		this.rightWrong = rightWrong;
	}
	/**accessor for rightWrong*/
	public String getRightWrong() {
		return rightWrong;}


	/**mutator to set Question asked*/
	public void setQuestion(String question){this.question = question;}
	/**accessor to get  Question asked*/
	public String getQuestion(){return question;}

	/**mutator to set Explanation*/
	public void setExplanation(String explanation){this.explanation = explanation;}
	/**accessor to get Explanation*/
	public String getExplanation(){return explanation;}
	
	/**mutator to set Author*/
	public void setAuthor(String author){this.author = author;}
	/**accessor to get Author*/
	public String getAuthor(){return author;}
	
	/**mutator to set correct answer index*/
	public void setCorrectAnswerNumber(int correctAnswer){this.correctAnswer = correctAnswer;}
	/**accessor to correct answer index*/
	public int getCorrectAnswerNumber(){return correctAnswer;}

	/**mutator to set difficulty*/
	public int getDifficulty(){return difficulty;}
	/**accessor to get difficulty*/
	public void setDifficulty(int difficulty){this.difficulty = difficulty;}

	/**mutator to set points awarded per question*/
	public int getPoints(){return points;}
	/**accessor to  get points awarded per question*/
	public void setPoints(int points){this.points = points;}

	/**mutator added  to set for Question number*/
	public int getQn(){return qn;}
	/**accessor added to get  for Question number*/
	public void setQn(int qn){this.qn = qn;}

	/**mutator to set  Array of Questions*/
	public String[] getAnswers(){return answers;}
	/**accessor to get  Array of Questions*/
	public void setAnswers(String[] answers){
		this.answers = new String[answers.length];
		for (int i=0; i < answers.length; i++) this.answers[i]=answers[i];
	}
	/**mutator to set  result*/
	public void setResult(boolean b){result = b;}
	
	public boolean isCorrect(){return result;}

	public QA(String q, String[] a, int correctAnswer, int difficulty, int points, String expl, String author){
		setQuestion(q);
		setAnswers(a);
		setCorrectAnswerNumber(correctAnswer);
		setDifficulty(difficulty);
		setPoints(points);
		setExplanation(expl);
		setAuthor(author);
	}
	
	/** constractor created to add two variables*/
	public QA(int qn, String rightWrong, String q2, int difficulty2, int points2, String author2) {
		setQn(qn);
		setRightwrong(rightWrong);
		setQuestion(q2);
		setDifficulty(difficulty2);
		setPoints(points2);
		setAuthor(author2);
		// TODO Auto-generated constructor stub
	}


}
