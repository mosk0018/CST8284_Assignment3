
package cst8284.quizMaster;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/** <p> <h6>File name: Display.java  <br>
 * @author modified by  Anastasia Moskvinova, 040871379 <br>
 *  Course: CST8132 – OOP<br>
 *  Assignment: 2<br>
 *  Date: Nov 26, 2016<br>
 *  Professor: Dave Houtman<br>
 *  Purpose: [This class contains methods that are generate panes where the quiz questions and answers<br>
 *   are loaded as well as navigating buttons and final pane with results,<br>
 *  this class also contains methods that are read user answers, compare them with <br>
 *  correct answer indexes from .quiz file loaded, <br>
 *  calculating points and  *  and display pane with the Quiz result at the end <br>
 * @version 5.3 <br>
 * @see ArrayList<br>
 * @see File<br>
 * @see String<br>
 * @see StringBuilder<br>
 * @since Neon Release (4.6.0)<br>
 *</h6></p> 
 */


public class Display {
	/**	@ Array of QA objects, read from file */
	ArrayList<QA> QAList; 
	/**	@ Handle to QA file */
	private File f; 		
	/**	@ Current question number,zero-based */
	private static int currentQuestionNumber; 
	String scoretext = "";
	int total_score = 0;
	int max_points = 0;

	StringBuilder strResults = new StringBuilder();


	/**
	 * Builds the menu bar
	 * @author modified by Anastasia Moskvinova
	 * @version 2.0
	 * @since 1.0
	 * @see Stage
	 * @see Pane
	 * @see BorderPane
	 * @see MenuBar
	 * @see MenuItem
	 * @see File
	 * @see NullPointerException
	 * @see ClassNotFoundException
	 * @see IOException
	 * @see Platform
	 * @param primaryStage Stage
	 * @return mainWindowBorder
	 */
	public Pane menuBar(Stage primaryStage){


		BorderPane mainWindowBorderPane = new BorderPane();
		mainWindowBorderPane.setCenter(getSplashPane("Welcome to QuizMaster"));
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		Menu fileMenu = new Menu("File");
		MenuItem open = new MenuItem("Open");
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().addAll(open, exit);
		menuBar.getMenus().addAll(fileMenu);
		mainWindowBorderPane.setTop(menuBar);

		open.setOnAction(actionEvent -> {
			File f = FileUtils.getFileHandle(primaryStage);
			try 
			{
				QAList = FileUtils.getQAArray(FileUtils.getFileName(f));

				//String q, String[] a, int correctAnswer, int difficulty, int points, String expl, String author
				String[] sortAnswers = {"Sort by Diff", "Sort by Points", "Sort by Author", "No sorting"};
				QA qa0 = new QA("Would you like to sort the question?", sortAnswers, -1, 0, 0, "", "");
				QAList.add(0, qa0);
				print();

				displayQA(QAList, primaryStage);	
			}
			catch(NullPointerException e3)
			{
				System.out.println("Caught NullPointer");
			}
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				System.out.println("Caught Exeption e ");
			}
		});

		exit.setOnAction(actionEvent -> {
			Platform.exit();
			System.exit(0);
		});

		return mainWindowBorderPane;
	}
	/** in this class implements FunctionalInterface Comperator's compare method. 
	 *  we override  it  to get sorted by Difficulty  */
	public class SortByDiff
	implements Comparator<QA>{

		@Override
		public int compare(QA qa1, QA qa2) {
			if (qa1.getDifficulty()< qa2.getDifficulty())
				return -1;
			else if (qa1.getDifficulty()> qa2.getDifficulty())
				return 1;
			else
				return 0;
		}}
	/** in this class implements FunctionalInterface Comperator's compare method. 
	 *  we override  it  to get sorted by Points */
	public class SortByPoints
	implements Comparator<QA>{

		@Override
		public int compare(QA qa1, QA qa2)
		{
			if (qa1.getPoints()< qa2.getPoints())
				return -1;
			else if (qa1.getPoints()> qa2.getPoints())
				return 1;
			else
				return 0;
		}
	}
	/** in this class implements FunctionalInterface Comperator's compare method. 
	 *  we override  it  to get sorted by Author */
	public class SortByAuthor
	implements Comparator<QA>{

		@Override
		public int compare(QA qa1, QA qa2) 
		{

			/**to compare strings */  
			return qa1.getAuthor().compareTo(qa2.getAuthor());

		}	
	}
	/** in this class implements FunctionalInterface Comperator's compare method do not sort*/
	public class SortByNoSort
	implements Comparator<QA>{

		@Override
		public int compare(QA qa1, QA qa2) {

			return 0;
		}	
	}

	private void print() {
		System.out.println("================");
		for (int i=0; i<QAList.size(); i++)
		{
			QA item = QAList.get(i);
			System.out.println("Q#: "+i+", Diff: " +item.getDifficulty() + ", Points: " + item.getPoints()
			+", Author: " + item.getAuthor());
		}
	}

	/**
	 * displayQA() method, is where the scenes of the quiz is being loaded into the Quiz stage through 
	 * calling getCurrentQAPane() method, and then being shown scene by scene
	 *  @author modified by Anastasia Moskvinova
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see ArrayList 
	 * @see Stage
	 * @see Scene
	 * @param qaList2 ArrayList that contains the file objects
	 * @param pStage primary stage 
	 */
	private void displayQA(ArrayList<QA> qaList2, Stage pStage) {
		/** hold current scene object*/ Scene scene; 

		if (currentQuestionNumber < qaList2.size()) {
			QA thisQA = qaList2.get(currentQuestionNumber);
			try {
				if (thisQA.getAnswers().length < thisQA.getCorrectAnswerNumber())
					throw new BadQAAnswerChoice();
			}

			catch (BadQAAnswerChoice e2) {

				currentQuestionNumber++;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(
						"The following mistake is found in question number " + currentQuestionNumber + ":"
								+ " \n Correct answer index is "
								+ "larger than the number of answers.\n" 
								+ "You are getting free " +  thisQA.getPoints() 
								+" points for it and question  is assumed correct");
				alert.showAndWait();
			}

			boolean specialQuestion = currentQuestionNumber == 0;

			BorderPane bp = new BorderPane(); /** Use BorderPane to organize GUI objects*/

			/** Load Question as text and new AnswerPane with radio buttons and answers into VBox */
			String qNum = new String((currentQuestionNumber) + ". ");
			if (specialQuestion)
				qNum = "";

			Text txtQuestion = new Text(qNum + thisQA.getQuestion() + "\n");

			/** Instantiate AnswerPane object and load radio buttons/answers into VBox */
			AnswerPane ap = new AnswerPane(thisQA.getAnswers());
			VBox vbAnswerPane = ap.getAnswerPane();

			/** Instantiate and configure buttons */
			String checkText = specialQuestion ? "Choose how you want to sort" : "Check Answer";
			Button btnCheckAnswer = new Button(checkText);
			Button btnNextQuestion = new Button("Next Button");
			configureButtons(btnCheckAnswer, btnNextQuestion, bp, ap, thisQA, specialQuestion);

			// When 'Next' button clicked, re-enter this code with next question indexed in first parameter
			// btnNextQuestion.setOnAction((ActionEvent e) -> displayQA(++currentQuestionNumber, qaList2, pStage));




			/**
			 * Increments currentQuestionNumber, than when  displaQA is  method is called below, it is 
			 *  loading new question with answers .
			 * @param e Action event
			 * @author modified by Anastasia Moskvinova
			 * @version 1.0
			 * @see EventHandler
			 * @see ActionEvent
			 * @since Neon Release (4.6.0)
			 */
			btnNextQuestion.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					currentQuestionNumber++;
					displayQA(qaList2, pStage);
				}
			}); 


			/** Instantiate and configure sub-Panes before loadings into BorderPane */
			HBox hbCheckPane = new HBox(btnCheckAnswer);
			VBox vbNextPane = new VBox(btnNextQuestion);
			configurePanes(bp, hbCheckPane, vbNextPane);
			VBox vbQAPane = new VBox();
			vbQAPane.getChildren().addAll(txtQuestion, vbAnswerPane, hbCheckPane);

			/** Load BorderPane with Center and Right Panes */
			bp.setCenter(vbQAPane);
			bp.setRight(vbNextPane);
			scene = new Scene(bp, 800, 480); // Display the current question in the scene
		} else

			/** Display results in new scene*/
			scene = new Scene(getQAResults(), 800, 480);

		pStage.setScene(scene); // Display current scene in stage
	}

	/**
	 * Configures the properties of the two buttons, 
	 * tests for valid answer, then displays correct or incorrect message
	 * @author Dave Houtman,  
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see Button
	 * @see BorderPane
	 * @see AnswerPane
	 * @see QA
	 * @param btnCheck Check button
	 * @param btnNext Next button
	 * @param bp Border pane
	 * @param ap Answer pane
	 * @param thisQuestion QA
	 */
	private void configureButtons(Button btnCheck, Button btnNext, BorderPane bp, AnswerPane ap, QA thisQuestion, boolean specialQuestion) {
		/** Configure the properties of the two buttons*/
		btnCheck.setDisable(false); // When 'Check' is Enabled 'Next' is not
		btnNext.setDisable(true);

		btnCheck.setAlignment(Pos.BOTTOM_RIGHT);
		btnNext.setAlignment(Pos.BOTTOM_RIGHT);

		btnCheck.setOnAction((ActionEvent e) -> { // Check clicked; test for valid answer
			int selectedAnswer = (ap.getButtonSelected()); // Which button was selected?
			if (selectedAnswer <= 0) { // If invalid selection, show alert...
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Missing Selection");
				alert.setContentText("Please select an answer from the choices available");
				alert.showAndWait();
			} 
			else if (!specialQuestion) { // ...otherwise display result and explanation
				thisQuestion.setResult(selectedAnswer == thisQuestion.getCorrectAnswerNumber()); // set	result: correct or not?
				String rightWrong = thisQuestion.isCorrect() ? "Right! " : "Wrong. ";
				Text txtExplanation = new Text(rightWrong + thisQuestion.getExplanation());

				/** Explanation text formatting */
				BorderPane.setMargin(txtExplanation, new Insets(0, 0, 100, 100)); 
				bp.setBottom(txtExplanation);
				/** When 'Check' is Disable 'Next' is Enabled */
				btnCheck.setDisable(true); 
				btnNext.setDisable(false);
			} else {
				System.out.println("Sort answer: " + selectedAnswer);
				QAList.sort(getSortMethod(selectedAnswer));
				print();
				btnCheck.setDisable(true); 
				btnNext.setDisable(false);
				Text txtExplanation = new Text("Press NEXT button to start the Quiz. Good Luck");

				/** Explanation text formatting */
				BorderPane.setMargin(txtExplanation, new Insets(0, 0, 100, 100)); 
				bp.setBottom(txtExplanation);
			}
		});
	}

	private Comparator<QA> getSortMethod(int sortIndex)
	{
		switch(sortIndex)
		{
		case 1: return new SortByDiff();
		case 2: return new SortByPoints();
		case 3: return new SortByAuthor();
		case 4: return new SortByNoSort();
		}
		return null;
	}

	/**
	 * Configures VBox and HBox for Chek and Next buttons
	 * @author Dave Houtman
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see BorderPane
	 * @see HBox
	 * @see VBox
	 * @param bp BorderPane
	 * @param hbCheck HBox
	 * @param vbNext VBox
	 */
	private void configurePanes(BorderPane bp, HBox hbCheck, VBox vbNext) {
		/** Handles spacing requirements for BorderPane */
		VBox vbSpace = new VBox();
		vbSpace.setPrefHeight(100);
		bp.setTop(vbSpace);
		HBox hbSpace = new HBox();
		hbSpace.setPrefWidth(100);
		bp.setLeft(hbSpace);
		VBox vbBottom = new VBox();
		vbSpace.setPrefHeight(150);
		bp.setBottom(vbBottom);
		BorderPane.setMargin(vbBottom, new Insets(0, 0, 115, 100));
		vbNext.setAlignment(Pos.BOTTOM_RIGHT);
		hbCheck.setAlignment(Pos.CENTER_RIGHT);
	}

	// Modified from: Prasad Saya,
	// https://examples.javacodegeeks.com/desktop-java/
	// javafx/dialog-javafx/javafx-dialog-example/ Downloaded Nov. 20, 2016

	/**
	 * getSplashPane() returns BorderPane that  display "welcome" page
	 * @author modified by Anastasia Moskvinova 
	 * @author <a href="https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example">Prasad Saya</a>
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see Pane 
	 * @see String 
	 * @param str String str: is the splash text that will be displayed in the main window
	 * @return splashPane
	 */
	private Pane getSplashPane(String str) {
		Text text = new Text(str);
		text.setStyle("-fx-font: 60px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
		DropShadow dropShadow = new DropShadow();
		text.setEffect(dropShadow);
		text.setCache(true);
		text.setX(10.0);
		text.setY(70.0);
		text.setFill(Color.web("red"));
		text.setFont(Font.font(null, FontWeight.BOLD, 50));

		FadeTransition ft = new FadeTransition(Duration.millis(9000), text);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		final Path path = new Path();
		path.getElements().add(new MoveTo(20, 20));
		path.getElements().add(new CubicCurveTo(30, 10, 380, 120, 200, 120));
		path.getElements().add(new CubicCurveTo(200, 1120, 110, 240, 380, 240));
		path.setOpacity(0.5);

		final PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(8.0));
		pathTransition.setDelay(Duration.seconds(.5));
		pathTransition.setPath(path);
		pathTransition.setNode(text);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(true);
		pathTransition.play();

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(ft);
		parallelTransition.setCycleCount(Timeline.INDEFINITE);
		parallelTransition.play();

		BorderPane splashPane = new BorderPane();
		splashPane.setCenter(text);
		return splashPane;
	}

	/**
	 *  creates  QAResults BorderPane, where  the result of the quiz are loaded
	 *  in this method is checking if user answers were correct or not,
	 *  calculation point and giving user "free point" for question being 
	 *  caught by BadQAAnswerChoice exception 
	 * @author Dave Houtman
	 * @author modified by Anastasia Moskvinova
	 * @version 1.0
	 * @since Neon Release (4.6.0)
	 * @see BorderPane
	 * @see ArrayList
	 * @see StringBuilder
	 * @see String
	 * @see Text
	 * @param qaList contains the file objects
	 * @return QAResults
	 */
	@SuppressWarnings("unchecked")
	private BorderPane getQAResults() {


		/**return pane for display*/
		TableView<QA> table;	
		TableColumn<QA, String>  qN = new TableColumn<>("Question#");
		qN.setMaxWidth(100);
		qN.setCellValueFactory(new PropertyValueFactory<>("qn"));


		TableColumn<QA, String>  wr = new TableColumn<>("Rusult");
		wr.setMaxWidth(100);
		wr.setCellValueFactory(new PropertyValueFactory<>("rightWrong"));

		TableColumn<QA, String> qA = new TableColumn<>("Question asked");
		qA.setMinWidth(300);
		qA.setCellValueFactory(
				new PropertyValueFactory<>("question"));

		TableColumn<QA, String> p = new TableColumn<>("Points per question");
		p.setMinWidth(100);
		p.setCellValueFactory(
				new PropertyValueFactory<>("points"));

		TableColumn<QA, String> diff= new TableColumn<>("Difficulty");
		diff.setMinWidth(100);
		diff.setCellValueFactory(
				new PropertyValueFactory<>("difficulty"));

		TableColumn<QA, String> author = new TableColumn<>("Author");
		author.setMinWidth(100);
		author.setCellValueFactory(
				new PropertyValueFactory<>("author"));

		table = new TableView<>();
		table.setItems(getData());
		 final Label label = new Label("QUIZ RESULTS. Your total score: "+total_score+"/" +max_points+" points");
	        label.setFont(new Font("Arial", 30));
	        

		table.getColumns().addAll(qN,  wr, qA, p, diff, author);

		 final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(label, table);

		BorderPane QAResults = new BorderPane();
		QAResults.setCenter(vbox);


		/**return pane for display*/
		return QAResults; 

	}

	public ObservableList<QA> getData()
	{

		final ObservableList<QA> data = FXCollections.observableArrayList();

		for (int i = 1; i < QAList.size(); i++)
		{
			QA thisQA = QAList.get(i);
			int points = QAList.get(i).getPoints();
			if (QAList.get(i).isCorrect() == true) 
			{
				
				max_points += points;
				thisQA.setQn(i);
				thisQA.setRightwrong("Correct");
				data.add(thisQA);
				total_score += thisQA.getPoints();
			}
			else if (thisQA.getAnswers().length < thisQA.getCorrectAnswerNumber())
			{
				max_points += points;
				thisQA.setQn(i);
				thisQA.setRightwrong(" Correct");
				data.add(thisQA);
				total_score += thisQA.getPoints();}

			else
			{
				max_points += points;
				thisQA.setQn(i);
				thisQA.setRightwrong("Wring");
				data.add(thisQA);
			}

		}

		return data;
	}


}  





