package TesterClass;

import java.util.Scanner;

import implementations.BabyNameRank;
import implementations.BabyNameRankGui;
import implementations.BeanMachine;//temporarily disabled
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Test extends Application{

	BabyNameRankGui view = new BabyNameRankGui();
	
	@Override
	public void start(Stage primaryStage) throws Exception {


		//create the event listeners
		
		view.btnexit.setOnAction(new SayGoodbye());
		view.btnsubmit.setOnAction(new Submit());
		
		view.btnexitY.setOnAction(e ->System.exit(0));
		view.btnexitN.setOnAction(new Return());

		Scene scene = new Scene(view.root, 400,200);
		scene.setFill(Color.LIGHTSTEELBLUE);
		primaryStage.setTitle("Baby Name Ranker 2001-2010");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	//event handlers
	class Submit implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			
			String gender = "";
			String year = "";
			String name = "";
			
			year = view.txyear.getText();
			gender = view.txgender.getText();
			name = view.txname.getText();

			if(BabyNameRank.getFile(year) == true) {
				
				BabyNameRank.getName(name,gender);
			}
			view.msg.setText(BabyNameRank.message());
			System.out.println(BabyNameRank.getFile(year));
		}	
	}	

	class Return implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			
			view.mainMenu();
			}
		}
	
	class SayGoodbye implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent event) {

				view.exitPrompt();
				}
			}
	
	//main method
	public static void main(String[] args) {
		
		boolean again = true;
		Scanner input = new Scanner(System.in);

		while(again) {
			
			System.out.println("Please choose a program to run:\n"
					+ "1)Bean Machine\n"
					+ "2)Baby Name Ranker (2001 -2010)\n"
					+ "0)Exit\n"
					+ "==>");
			
			
			try {
				int decision = input.nextInt();
				
				if(decision > -1 && decision <3) {
					
					if(decision == 1) {
						
						again = false;	
						BeanMachine.run();
					}
					else if(decision == 2) {//always to do .charAt!!
						
						again = false;
						input.close();
						launch(args);
					}
					else if(decision == 0) {
						
						System.out.println("Goodbye!");		
						input.close();
						System.exit(1);		
					}
				}
				else {
					
					System.out.println("\n**Enter either a '0', a '1', or a '2'**\n");	
				}
			}
			catch(Exception ex) {
				
				System.out.println("\n**Enter either a '0', a '1', or a '2'**\n");	
				input.next();
			}
	
		}
		input.close();
		System.exit(1);
	
	}
}
