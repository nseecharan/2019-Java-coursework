package implementations;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BabyNameRankGui extends GridPane{
	public GridPane pane = new GridPane();
	public GridPane pane2 = new GridPane();
	public GridPane pane3 = new GridPane();
	public Group root = new Group();
	Label lbyear = new Label("Enter the Year");
	public TextField txyear = new TextField();
	Label lbgender = new Label("Enter the Gender (M/F)");
	public TextField txgender = new TextField();
	Label lbname = new Label("Enter the Name");
	public TextField txname = new TextField();
	public Label msg = new Label("");
	public Button btnsubmit = new Button("Submit Query");
	public Button btnexit = new Button("Exit");
	public Button btnexitY = new Button("Yes");
	public Button btnexitN = new Button("No");
	
	
	public BabyNameRankGui() {
		
		mainMenu();	
	}
	
	public void mainMenu() {
		
		clear();
		
		//pane.setGridLinesVisible(true);
		//pane2.setGridLinesVisible(true);
		
		pane.setAlignment(Pos.BASELINE_CENTER); 
		pane.setPadding(new Insets(5,5,5,5));
		pane.setMinSize(400, 300);
	    pane.setHgap(5);
	    pane.setVgap(20);
	    
		pane.add(lbyear, 0, 0);
		pane.add(txyear, 1,0);
		txgender.setMaxWidth(32);;
		pane.add(lbgender, 0,1);
		pane.add(txgender, 1,1);
		pane.add(lbname, 0,2);
		pane.add(txname, 1,2);

		pane2.setTranslateY(160);
		pane2.setAlignment(Pos.BASELINE_CENTER); 
		pane2.setPadding(new Insets(5,5,5,5));
		pane2.setMinSize(400, 300);
		btnexit.setMinWidth(112);
		btnsubmit.setMinWidth(112);
	    pane2.setHgap(32);
	    pane2.setVgap(5);
		pane2.add(btnsubmit, 0, 0);
		pane2.add(btnexit, 1, 0);
		
		pane3.setTranslateY(120);
		pane3.setAlignment(Pos.BASELINE_CENTER); 
		pane3.setPadding(new Insets(5,5,5,5));
		pane3.setMinSize(400, 32);
		pane3.add(msg, 1, 0);
		
		root.getChildren().addAll(pane, pane2, pane3);	
	}
	
	public void exitPrompt() {	
	
		clear();
		
		pane3.setTranslateY(64);
		pane3.setAlignment(Pos.BASELINE_CENTER); 
		pane3.setPadding(new Insets(5,5,5,5));
		pane3.setMinSize(400, 32);
		pane3.add(msg, 1, 0);
		
		pane2.setTranslateY(160);
		pane2.setAlignment(Pos.BASELINE_CENTER); 
		pane2.setPadding(new Insets(5,5,5,5));
		pane2.setMinSize(400, 300);
		btnexitY.setMinWidth(112);
		btnexitN.setMinWidth(112);
	    pane2.setHgap(32);
	    pane2.setVgap(5);
		pane2.add(btnexitY, 0, 0);
		pane2.add(btnexitN, 1, 0);
		msg.setText("Do you really want to exit?");
	
		root.getChildren().addAll(pane2, pane3);	
	}
	
	public void clear() {
		
		pane.getChildren().clear();
		pane2.getChildren().clear();
		pane3.getChildren().clear();
		root.getChildren().clear();
		
		lbyear.setText("Enter the Year");
		lbgender.setText("Enter the Gender (M/F)");
		lbname.setText("Enter the Name");
		msg.setText("");

		
	}
}
