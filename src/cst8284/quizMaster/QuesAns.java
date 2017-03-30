
package cst8284.quizMaster;

import java.io.Serializable;

/**<pre>
 *	File name: QuesAns.java 
 *  Author: Anastasia Moskvinova, 040871379 
 *  Course: CST8132 – OOP
 *  Assignment: 2
 *  Date: Nov 26, 2016
 *  Professor: Dave Houtman
 *  Purpose: Abstract class which contains the methods used to created objects for QA.java </pre>
 * @Copyright Dave Houtman 2016.  For use in Fall 2016 - CST8284 classes only 
 * @author Dave Houtman
 * @version 2.0
 * @since 1.0
 * @see Serializable
 * @see String
 * @see Boolean
 */
public abstract class QuesAns implements Serializable {
	
	public abstract String getQuestion();
	public abstract void setQuestion(String question);
	
	public abstract String getExplanation();
	public abstract void setExplanation(String explanation);
	
	public abstract String getAuthor();
	public abstract void setAuthor(String author);
	
	public abstract int getCorrectAnswerNumber();
	public abstract void setCorrectAnswerNumber(int correctAnswer);
	
	public abstract int getDifficulty();
	public abstract void setDifficulty(int difficulty);
	
	public abstract int getPoints();
	public abstract void setPoints(int points);
	
	public abstract String[] getAnswers();
	public abstract void setAnswers(String[] answers);
	
	public abstract boolean isCorrect();
	public abstract void setResult(boolean b);

	
}
