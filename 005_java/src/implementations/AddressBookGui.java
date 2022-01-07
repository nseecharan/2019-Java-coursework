package implementations;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AddressBookGui extends GridPane {
		public GridPane pane = new GridPane();
		public GridPane pane2 = new GridPane();
		public GridPane msgPane = new GridPane();
		public Group root = new Group();
		public Button btnadd = new Button("Add");
		public Button btfirst = new Button("First");
		public Button btnext = new Button("Next");
		public Button btnprevious = new Button("Previous");
		public Button btnlast = new Button("Last");
		public Button btnupdate = new Button("Update");
		public TextField txfname = new TextField();
		public TextField txcity = new TextField();
		public TextField txprovince = new TextField();
		public TextField txlname = new TextField();
		public TextField txpostalcode = new TextField();
		public Label lbfname = new Label("First Name");
		public Label lblname = new Label("Last Name");
		public Label lbcity = new Label("City");
		public Label lbprov = new Label("Province");
		public Label lbpcode = new Label("Postal Code");
		public Label MSG = new Label("");
			
		public AddressBookGui() {
			
			menuDisplay();
		};
		
		public void menuDisplay() {
			
			//pane.setGridLinesVisible(true);//debug
			pane.setAlignment(Pos.CENTER); 
			pane.setPadding(new Insets(5,5,5,5));
		    pane.setHgap(5);
		    pane.setVgap(5);
			
		    pane.add(lbfname, 0,0);
			pane.add(txfname,1,0);
			pane.add(lblname,0,1);
			pane.add(txlname,1,1);
			
			pane.add(lbcity, 0,2);
			pane.add(txcity, 1,2);
			pane.add(lbprov, 2,2);
			pane.add(txprovince, 3,2);
			
			pane.add(lbpcode, 4,2);
			pane.add(txpostalcode, 5,2);

			pane.add(MSG, 0, 3);
			
			pane2.setHgap(5);
			pane2.setVgap(5);
			pane2.setTranslateY(130);
			//pane2.setGridLinesVisible(true);//debug
			pane2.add(btnadd, 0,0);
			pane2.add(btfirst, 1,0);
			pane2.add(btnext, 2,0);
			pane2.add(btnprevious, 3,0);
			pane2.add(btnlast, 4,0);
			pane2.add(btnupdate, 5,0);
			MSG.setFont(Font.font(null, FontWeight.BOLD, 12));
			MSG.setTextFill(Color.WHITESMOKE);
			msgPane.setTranslateY(96);
			msgPane.add(MSG, 0, 0);
			
			root.getChildren().addAll(pane, pane2, msgPane);
		}

}
		   			

	

