package implementations;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.*;

public class AccountGUI extends Application {
	
	private RenderView rview = new RenderView();
	private int acctno = -1;//setup try catch stuff
	private boolean newpin= false;

	@Override
	public void start(Stage primaryStage) throws Exception {

		////do file check
			Account.deserialize();

		//rview.root.setTranslateX(10);
		rview.pane.setAlignment(Pos.CENTER); 
		rview.pane.setPadding(new Insets(5, 5, 5, 5));
		rview.pane.setHgap(4);
	    rview.pane.setVgap(20);
	    rview.pane.setMinSize(437, 245);	
		
	    rview.submit1.setOnAction(new EnterAccount());
	    rview.submit2.setOnAction(new RegisterPin());	
	    rview.btn_withdraw.setOnAction(new Withdraw());
	    rview.btn_deposit.setOnAction(new Deposit());
	    rview.btn_exit_act.setOnAction(new ExitAccount());
		Image image = new Image(getClass().getResourceAsStream("image/java ws5 window2.png"));
	    ImageView bg = new ImageView(image); 
	   
	    rview.root.getChildren().addAll(bg,rview.pane);
	   
	    rview.pane.setTranslateY(10);
	    
	    //rview.pane.setGridLinesVisible(true);//debug
		
	    Scene scene = new Scene(rview.root, 437, 245);
		primaryStage.setTitle("ATM");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	  class EnterAccount implements EventHandler<ActionEvent> {
	    
		@Override 
	    public void handle(ActionEvent e) {

	    	acctno = Integer.parseInt(rview.tx_acctno.getText());//setup try catch stuff
			newpin= false;

			System.out.println("Main menu submit button pressed");
			
			int i=0;
			boolean again = true;
			
			while(i<Account.act_arr.length && again) {
				
				if(Account.act_arr[i].getID() == acctno) {
					
					newpin = Account.act_arr[i].checkPin(acctno);
					again = false;
				}
				
				i++;
			}
			
			System.out.println("Account has pin?" + "/" + newpin);
			if(newpin == false) {
				
				rview.regMenu();
			}
			else {
				
				rview.accountMenu();
				}
			}
		}	
	  
	  class RegisterPin implements EventHandler<ActionEvent> {
	    
		@Override 
	    public void handle(ActionEvent e) {
			int pin = Integer.parseInt(rview.tx_pin.getText());// remember to add try catches to all of these variables
			String fname = rview.tx_fname.getText(), lname = rview.tx_lname.getText();
			
			Account.act_arr[acctno].registerAcct(fname, lname, pin, acctno);
			System.out.println("Account # " + acctno +" registered to: " + 
			Account.act_arr[acctno].getFullName() + ", Pin: " + Account.act_arr[acctno].getPin());

			try {
				Account.serialize(pin, acctno, fname, lname);
				System.out.println("Registration info written to file ");
			} catch (ClassNotFoundException e1) {
				System.out.println("Register error " + e1);
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("Register error " + e1);
				e1.printStackTrace();
			}
			
			rview.accountMenu();		
		}	
	  }
	  
	  class ExitAccount implements EventHandler<ActionEvent> {
	    
		@Override 
	    public void handle(ActionEvent e) {
			
			rview.clearElements();

			rview.mainMenu();
		}	
	  }
	  
	  class Deposit implements EventHandler<ActionEvent> {
		    
		@Override 
	    public void handle(ActionEvent e) {
			
			double money_amt = Double.parseDouble(rview.money.getText());// do the try stuff
			
			Account.act_arr[acctno].deposit(money_amt);
			rview.lb_display.setText(Account.act_arr[acctno].accountDisplay(money_amt, false));
			rview.money.clear();
			System.out.println(Account.act_arr[acctno].accountDisplay(money_amt, false));

				rview.writer(Account.act_arr[acctno].getBalance(), acctno);
		}	
	  }
	    
	  class Withdraw implements EventHandler<ActionEvent> {
		    
		@Override 
	    public void handle(ActionEvent e) {
			
			double money_amt = Double.parseDouble(rview.money.getText());// do the try stuff
			
			Account.act_arr[acctno].withdraw(money_amt);
			
			rview.lb_display.setText(Account.act_arr[acctno].accountDisplay(money_amt, true));
			rview.money.clear();
			System.out.println(Account.act_arr[acctno].accountDisplay(money_amt, true));	
			
			rview.writer(Account.act_arr[acctno].getBalance(), acctno);
		}	
	  }
	   
	public static void main(String[] args) {
		
		launch(args);
	}

	class RenderView extends GridPane{
		private Group root = new Group();
		private GridPane pane = new GridPane();
		
	    private Label label = new Label("Enter an Account number:");
	    private TextField tx_acctno = new TextField();//deliberately made it so that the field would not clear when returning to main menu
	    private Button submit1 = new Button("Submit");   
	   
	    private Label greeting = new Label("");//this label gets modified/updated
		private Label label2 = new Label("Enter first and last name:");
		private Label label3 = new Label("Create a new pin:");
		private TextField tx_fname = new TextField("");
		private TextField tx_lname = new TextField("");
		private TextField tx_pin = new TextField();
		private Button submit2 = new Button("Register");
		//private Button cancel = new Button("Go Back");
		
		private Button btn_withdraw = new Button("Withdraw");
		private Button btn_deposit = new Button("Deposit");
		private Button btn_exit_act = new Button("Exit");
		private TextField money = new TextField();
		private Label lb_display = new Label("");
		private Label label4 = new Label("Amount: $");

		public RenderView() {
				
			mainMenu();	
		}
		
		public void mainMenu() {
			
			clearElements();
			
			//pane.setGridLinesVisible(true);//debug

			pane.setHgap(4);
		    pane.setVgap(20);

			greeting = new Label("Main Menu");
			greeting.setFont(Font.font(null, FontWeight.BOLD, 12));
			greeting.setTextFill(Color.WHITE);
			label.setFont(Font.font(null, FontWeight.BOLD, 10));
			label.setTextFill(Color.WHITE);
		    pane.add(greeting,1,0);
			pane.add(label, 0,1);
		    pane.add(tx_acctno, 1,1);
		    pane.add(submit1, 1,2);
			//if adding a go back function, remember to hide the elements in rgMenu
		}	
			
		public void regMenu() {
			
			clearElements();
			//pane.setGridLinesVisible(true);//debug
			
			tx_fname.setText("First Name");
			tx_lname.setText("Last Name");
			greeting = new Label("Welcome! You must register\n to use this account");
			greeting.setFont(Font.font(null, FontWeight.BOLD, 12));
			greeting.setTextFill(Color.WHITE);
			label2.setFont(Font.font(null, FontWeight.BOLD, 10));
			label2.setTextFill(Color.WHITE);
			label3.setFont(Font.font(null, FontWeight.BOLD, 10));
			label3.setTextFill(Color.WHITE);
		    //fname
			pane.add(greeting, 1,0);
		    pane.add(label2, 0,1);
		    pane.add(tx_fname, 1,1);
		    //lname
		    pane.add(tx_lname, 2,1);
		    //pin
			pane.add(label3, 0,2);
			pane.add(tx_pin, 1,2);
			pane.add(submit2, 2,2);
		}
		
		public void accountMenu() {
			
			clearElements();

			greeting = new Label("Welcome " + Account.act_arr[acctno].getFullName());
			greeting.setFont(Font.font(null, FontWeight.BOLD, 12));
			greeting.setTextFill(Color.WHITE);
			
			lb_display = new Label(Account.act_arr[acctno].accountDisplay(0,true));//will only display if first value is > 0
			lb_display.setFont(Font.font(null, FontWeight.BOLD, 10));
			lb_display.setTextFill(Color.WHITE);
			
			label4.setFont(Font.font(null, FontWeight.BOLD, 10));
			label4.setTextFill(Color.WHITE);
			
			//pane.setGridLinesVisible(true);//debug
			//pane.setHgap(10);
			pane.setVgap(4);

			pane.add(greeting,2,0);
			pane.add(btn_withdraw, 0,1);
			pane.add(btn_deposit, 0,2);
			pane.add(label4,1,1);
			pane.add(money,2,1);
			pane.add(btn_exit_act,0,3); 
			pane.add(lb_display,2,3);
		}
		//did this to avoid duplicating this code twice
		public void writer( double bal, int id) {
			
			try {
				Account.serialize(bal, id);
				System.out.println("Balance written to file ");
			} catch (ClassNotFoundException e1) {
				System.out.println("Balance update error " + e1);
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("Balance update error " + e1);
				e1.printStackTrace();
			}
		}
		
		public void clearElements() {
			//pane.clearConstraints(pane);
			pane.getChildren().clear();
			tx_pin.clear();		
		}
	}
}
