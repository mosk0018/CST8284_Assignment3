


package cst8284.quizMaster;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import java.io.File;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import javafx.event.EventHandler;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


/**<pre>
 * 	File name: QuizMain.java  
 *  Author:  modified by Anastasia Moskvinova, 040871379
 *  Course: CST8132 – OOP
 *  Assignment: 3
 *  Date: Dec 3, 2016
 *  Professor: Dave Houtman
 *	QuizMain  the main class of the program. 
 * 	The user selects the questions and answer each 
 * 	question and get his/her results. 
 * 	This class extends the Application Class which provide the main stage object.
 * 	It contains: start() and public static void main() methods </pre>
 *
 * @author Dave Houtman 
 * @version 3.0 
 * @see Application 
 * @see java.io.FileNotFoundException 
 * @see java.io.IOException 
 * @since [ Neon Release (4.6.0) Build id: 20160613-1800]
 */

// Copyright Dave Houtman 2016. For use in Fall 2016 - CST8284 classes only
public class QuizMain extends Application {



	/**
	 * QuizMain is the entry point to the QuizMaster Application
	 * Causes this thread to begin execution; 
	 * the Java Virtual Machine calls the run method 
	 * of this thread. The result is that two threads 
	 * are running concurrently: the current thread (which returns from the call to the start method)
	 * and the other thread (which executes its run method). 
	 * Description was taken from: https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html
	 * @param primaryStage
	 * @author  modified by Anastasia Moskinova
	 * @version 1.0
	 * @see Stage
	 * @since 1.0
	 */
	@Override
	public void start(Stage primaryStage) throws IOException, FileNotFoundException, ClassNotFoundException {

		// Display Splash Screen
		// Display Splash Screen
				primaryStage.setTitle("Quiz Master");

				
				Display display = new Display();
				Scene scene = new Scene(display.menuBar(primaryStage), 1024, 512, Color.WHITE);
				primaryStage.setScene(scene);
				primaryStage.show();
	}

	/**
	 * Main method
	 * java  program starts execution from  this method
	 * @author  modified by Anastasia Moskvinova
	 * @version 1.0
	 * @since 1.0
	 * @see ArrayList //Ana need to be moved to Display
	 * @see Stage
	 * @param args Application.launch
	 */
	


	public static void main(String[] args) {
		Application.launch(args);
	}
}