

package cst8284.quizMaster;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;


/**<pre> 
 * 	File name: AnswerPane.java
 *  Author: modified by Anastasia Moskvinova, 040871379
 *  Course: CST8132 – OOP
 *  Assignment: 3 
 *  Date: Dec 3, 2016 
 *  Professor: Dave Houtman
 *  Purpose: AnswerPane class creates a pane that provides the user with multiple selection radio buttons (i.e. answers)</pre>
 * @author Dave Houtman 
 * @version 1.0
 * 
 * @see VBox
 * @see ToggleGroup
 * @see RadioButton
 * @see String
 * @since Neon Release (4.6.0) Build id: 20160613-1800
 */
public class AnswerPane {
	private VBox answerBox = new VBox();
	private ToggleGroup group = new ToggleGroup();
	private RadioButton[] rbAr;
	private String[] answers;

	/**
	 * Create an answerPane object with a list of answers
	 * @param answers an array of String objects that contains the available answers of a question
	 * @author Dave Houtman
	 * @version 1.0
	 * @see String
	 * @since 1.0
	 */
	public AnswerPane(String[] answers) {
		this.answers = answers;
	}

	/**
	 * getAnswePane Creates a list of radio buttons according to the size of answers' array passed to the class constructor.
	 * @return answerBox
	 * @author Dave Houtman
	 * @version 1.0
	 * @see RadioButton
	 * @since 1.0
	 */
	public VBox getAnswerPane() {
		rbAr = new RadioButton[answers.length];
		int rbCtr = 0;
		for (String ans : answers) {
			rbAr[rbCtr] = new RadioButton(ans);
			rbAr[rbCtr].setToggleGroup(group);
			answerBox.getChildren().add(rbAr[rbCtr++]);
		}
		return answerBox;
	}

	/**
	 * Determine the user selection (i.e., which radio button has been selected).
	 * @return btnCtr
	 * @author Dave Houtman
	 * @version 1.0
	 * @see RadioButton
	 * @since 1.0
	 */
	public int getButtonSelected() {
		int btnCtr = 1;
		if (rbAr != null) {
			for (RadioButton rb : rbAr) {
				if (rb.isSelected())
					break;
				btnCtr++;
			}
		}
		return (btnCtr > answers.length) ? -1 : btnCtr;
	}
}
