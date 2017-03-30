


package cst8284.quizMaster;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.EOFException;


/**File name: FileUtils.java 
 *  @author modified by  Anastasia Moskvinova, 040871379
 *  Course: CST8132 – OOP
 *  Assignment: 3
 *  Date: Dec 3, 2016
 *  Professor: Dave Houtman
 *  Provides the application/user with set of file management tools
 * @author Dave Houtman
 * @author  changed by Anastasia Moskvinova, 
 * @version 1.0
 * @see FileUtils
 * @since Neon Release (4.6.0)
 *
 */
public class FileUtils {

	/**
	 * Creates QuizFileAbsolutePathAndName
	 * @author Dave Houtman
	 * @version 1.0
	 * @see String
	 * @since Neon Release (4.6.0)
	 */
	private static String QuizFileAbsolutePathAndName = "";

/**
 * sets Title and creates new ExtensionFilters 
 * @author Dave Houtman
 * @version 1.0
 * @see File
 * @since Neon Release (4.6.0)
 * @param primaryStage Stage
 * @return quizfile quiz file
 */
	public static File getFileHandle(Stage primaryStage) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Quiz File");
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Quiz Files", "*.quiz"),
				new ExtensionFilter("All Files", "*.*"));
		File quizFile = fc.showOpenDialog(primaryStage);
		setFileName(quizFile);
		return (quizFile);
	}

	/**
	 * Inputs file stream, creates qaList (new ArrayList), then prints to console and catches end of file
	 * @author modified by Anastasia Moskvinova
	 * @author Dave Houtman
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see java.util.ArrayList 
	 * @param absPath String
	 * @return qaList
	 * @throws FileNotFoundException new FileInputStream(absPath)
	 * @throws ClassNotFoundException ois.readObject
	 */
	public static ArrayList<QA> getQAArray(String absPath) throws FileNotFoundException, ClassNotFoundException {

		FileInputStream fis = new FileInputStream(absPath);

		ArrayList<QA> qaList = new ArrayList<QA>();

		try {
			ObjectInputStream ois = new ObjectInputStream(fis);

			try {
				QA item = (QA) ois.readObject();
				// item.setCorrectAnswerNumber(11); //check BadQAAnswerChoice
				while (item != null) {
					qaList.add(item);
//					System.out.println("Number of answers " + item.getAnswers().length + ", Correct answer: "
//							+ item.getCorrectAnswerNumber()+ ", Points per question"+item.getPoints());
					item = (QA) ois.readObject();
				}
			} catch (EOFException e) {
				System.out.println("Caught EOF");
				// e.printStackTrace();
			}

			ois.close();
			fis.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return qaList;
	}

	/**
	 * sets QuizFileAbsolutePathAndName
	 * @author Dave Houtman
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see File
	 * @param f File
	 */
	private static void setFileName(File f) {
		QuizFileAbsolutePathAndName = (FileExists(f)) ? f.getAbsolutePath() : "";
	}

	/**
	 * Returns QuizFileAbsolutePathAndName
	 * @author Dave Houtman
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see String 
	 * @return QuizFileAbsolutePathAndName
	 */
	public static String getFileName() {
		return QuizFileAbsolutePathAndName;
	}

	/**
	 * Returns file absolute path
	 * @author Dave Houtman
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see String
	 * @see File
	 * @param f Quiz file
	 * @return f.getAbsolutePath( )
	 */
	public static String getFileName(File f) {
		return f.getAbsolutePath();
	}

/**
 * Checks if the file is readable, exists and is a file
 * @author Dave Houtman
 * @version 1.0
 * @since Neon Release (4.6.0)
 * @see Boolean
 * @see File
 * @param f Quiz file
 * @return true if file is readable (not null and exists) false if file isn't readable (null and doesn't exist)
 */
	private static Boolean FileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead());
	}
}
