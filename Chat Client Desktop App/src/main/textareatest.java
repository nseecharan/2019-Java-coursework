package main;

import implementations.Gui;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class textareatest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TextArea tx = new TextArea("aaaaaaaaa\naaaaaaaaaaaaaaaaa\naaaaaaaaa\naaaaaaaaaaaaaa"
				+ "\naaaaaaaaaaaaaa\naaaaaaa\naaa\naaaa\naaa\naaaaaa\naaaaa"
				+ "\naaaaaaaaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaaaa\n\naaaaa\n\n\n\n\naaaaaaaaa");
		tx.setEditable(false);
		//tx.
		tx.setMaxHeight(300);
		tx.setMaxWidth(200);
		Group gr = new Group();
		
		gr.getChildren().addAll(tx);
		
		Scene scene = new Scene(gr, 400,500);
		scene.setFill(Gui.grad);
		scene.getStylesheets().add("./ws8.css");
		primaryStage.setTitle("textarea");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[] args) {
		
		launch(args);
	}	

}
