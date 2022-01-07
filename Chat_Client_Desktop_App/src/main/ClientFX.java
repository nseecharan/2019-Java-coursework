package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

import implementations.*;

public class ClientFX extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Gui a = new Gui();

		a.startUp();
		
		//kind of works; updates scroll position
		//disabled for now
		/*
	    Gui.lbMessage.textProperty().addListener(e -> {
	    	
	    	System.out.println("Scrolling params" + Gui.scroll.vmaxProperty() +"\n"+ Gui.scroll.getVvalue());
	    	
	    	if(Gui.scroll.getVvalue() >= 0.8) {

	    		Gui.scroll.setVvalue(1); 	
	    	}
	    	   	
	    });
	    */
		
		Gui.btnconnect.setOnAction(e -> {
			
			//System.out.println(Gui.name.getText());
			
			//System.out.println(Gui.name.getText().toString().matches(".* .*"));
			//System.out.println(Gui.name.getText().toString());
			
			if(!(Gui.name.getText().toString().matches(".* .*"))) {

				Client.runClient();
				a.chatWindow();	
				
				if(Client.connectstat) {
					
					Gui.lbFeedback.setId("text");
					Gui.lbFeedback.setText("User "+Client.name+" Connected");
				}
				else {
					
					Gui.lbFeedback.setId("warning");
				}
				
				Thread msgwinT = new Thread( new Runnable() {
					
					@Override
					public void run() {
						
						Runnable updater = new Runnable() {
							
							@Override
							public void run() {
								
								if(Client.connectstat) {
									
									String tstr = "";
									try {
										
										Client.ClientInput();
										
										File file = new File("ChatLog.txt");
										
										if(file.exists()) {
											
											Scanner scan = new Scanner(file);
											
											while(scan.hasNextLine()) {
												
												tstr += scan.nextLine()+"\n";			
											}
										
											if(tstr != null) {
												//System.out.println(tstr);
																							
												Gui.lbMessage.setText(tstr);					
											}								
											tstr ="";
										}								
									}
									catch(Exception ex) {						
									}
								}			
							}						
						};
						
						while(true) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								
							}
							
							 Platform.runLater(updater);
						}
					}						
				});
		
				msgwinT.setDaemon(true);
				msgwinT.start();
				System.out.println("Platform later is running");
			}
			else {
				
				Gui.lbFeedback.setId("warning");
				Gui.lbFeedback.setText("Name can not contain spaces, use '_'");
			}
		});
		
		Gui.btnNextTheme.setOnAction(e -> {
			
			a.nextTheme();
		});
		
		Gui.btnPreviousTheme.setOnAction(e -> {
			
			a.previousTheme();
		});
			
		Gui.input.setOnKeyPressed(
				
				event -> {
	    			
	    			if(event.getCode() == KeyCode.ENTER) {

	    				Client.Cline(Gui.input.getText());
	    				Gui.input.clear();
	    				}  			
	    			}
				);
		
		Scene scene = new Scene(Gui.root2, 400,500);
		scene.setFill(Gui.grad);
		scene.getStylesheets().add("implementations/ws8.css");
		primaryStage.setTitle("Instant Message App");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
			
		launch(args);
	}	
}
