package implementations;

import javafx.animation.ScaleTransition;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Gui extends BorderPane{
			
	public static Group root = new Group();
	public static Group root2 = new Group();//intended for images
	public static Button btnconnect = new Button("Connect");
	public static Label lbMessage = new Label("Not connected to chat server\nMessages could not be loaded");
	public static TextField input = new TextField();
	public static Label lbFeedback = new Label();//error messages and notifications	
	public static Label lbConnectionStat = new Label("Will Show Connection Status");
	public static Button btnNextTheme = new Button(">>");
	public static Button btnPreviousTheme = new Button("<<");
	static GridPane pane = new GridPane();
	public static ScrollPane scroll = new ScrollPane();
	static Rectangle rect = new Rectangle();
	static Rectangle rect2 = new Rectangle();
	static StackPane stpane = new StackPane();//for input text ara
	static StackPane stpane2 = new StackPane();//for scroll pane

	//stuff for themes menu
	public static TextField name = new TextField("Your Name");
	static int themenum = 1;
	static String imgPath ="images/"+themenum+".png";
	static Label lbTheme = new Label("Theme " + themenum);
	static GridPane btnpane = new GridPane();//for theme buttons
	static StackPane stpaneNext = new StackPane();//for next theme btn
	static StackPane stpanePrev = new StackPane();//for previous theme btn
	static StackPane stpaneTheme = new StackPane();//for theme message
	static Rectangle rect3 = new Rectangle();
	static Circle circle = new Circle();
	static Circle circle2 = new Circle();
	
	//effects
	
	static Color menuColor = Color.BLACK;
	public static ScaleTransition scale = new ScaleTransition();
	public static LinearGradient grad = new LinearGradient(
			0,0,0,1,true, CycleMethod.NO_CYCLE,
			new Stop(0.1, Color.color(0.7,0.8, 1)), 
			new Stop(1.0,Color.color(0.2,0.3, 0.5))
			);
	static LinearGradient grad2 = new LinearGradient(
			50,172,172,50,false, CycleMethod.REFLECT,
			new Stop(0.1, Color.color(0.8,0.8,0.8)), 
			new Stop(1.0,Color.color(0,0, 0))
			);
	
	static LinearGradient grad3 = new LinearGradient(
			0,0,0,1,true, CycleMethod.NO_CYCLE,
			new Stop(0.1, Color.color(0.0,0.0,0.0, 1)), 
			new Stop(1.0,Color.color(0.0,0.0, 0.0, 0.4))
			);

	Image img;
	static ImageView bg;

	//toolbar
	
	//methods
	
	public void nextTheme() {
		
		if(themenum <11) {
			
			themenum++;
		}
		
		//consider changing the menu colors for different backgrounds
		
		lbTheme.setText("Theme " + themenum);
		root2.getChildren().remove(bg);
		root2.getChildren().remove(root);
		root2.getChildren().addAll(renderImg("images/"+themenum+".png"), root);	
	}
	
	public void previousTheme() {
		
		if(themenum > 0) {
			
			themenum--;
		}
		
		//consider changing the menu colors for different backgrounds

		lbTheme.setText("Theme " + themenum);
		root2.getChildren().remove(bg);
		root2.getChildren().remove(root);	
		
		//due to error checking in the renderImg() method, this would run
		//into conflicts when trying to load the default stage gradient
		//as this method and the renderImg() method would both be trying
		//to add root to root2	

		if(themenum != 0) {
			

			root2.getChildren().addAll(renderImg("images/"+themenum+".png"), root);	
		}
		else {
			
			root2.getChildren().addAll(root);	
		}
	}
	
	public void startUp() {
		
		btnNextTheme.setId("themebtn");
		btnPreviousTheme.setId("themebtn");
		lbMessage.setId("text");
		input.setId("textIPT");
		lbTheme.setId("text");
		lbFeedback.setTranslateY(64);
		name.setTranslateY(-64);
		name.setId("userNm");

		root.getChildren().addAll(btnconnect,name,lbFeedback);
		root.setTranslateX(175);
		root.setTranslateY(250);
		
		//will add image and root to root2, otherwise app will use the scene's gradient color
		if(renderImg(imgPath) != null) {
			
			root2.getChildren().addAll(renderImg(imgPath), root);
		}
		else {
			
			root2.getChildren().addAll(root);
		}
	}
	
	public void chatWindow() {
		
		clear();
		
		//pane.setGridLinesVisible(true);
		pane.setVgap(20);
		pane.setMaxWidth(350);
		input.setPrefWidth(350);
		scroll.setMinHeight(300);
		scroll.setMaxHeight(300);
		scroll.setMaxWidth(350);

		rect.setWidth(350);
		rect.setHeight(32);
		rect.setArcHeight(10);
		rect.setArcWidth(10);

		rect.setFill(grad3);
		rect.setOpacity(0.4);
		
		rect2.setWidth(350);
		rect2.setHeight(320);
		rect2.setArcHeight(10);
		rect2.setArcWidth(10);
		rect2.setOpacity(0.4);
		
		rect2.setFill(grad3);
		
		rect3.setWidth(64);
		rect3.setHeight(32);
		rect3.setArcHeight(10);
		rect3.setArcWidth(10);
		rect3.setFill(menuColor);
		rect3.setOpacity(0.4);
		
		circle.setRadius(16);
		circle.setOpacity(0.4);
		circle.setFill(menuColor);
		circle2.setRadius(16);
		circle2.setOpacity(0.4);
		circle2.setFill(menuColor);
	
		lbMessage.setMaxWidth(330);
		lbMessage.setWrapText(true);
		lbMessage.setPadding(new Insets(0,5,0,5));

		scroll.setContent(lbMessage);	
		stpane2.getChildren().addAll(rect2,scroll);
		stpane.getChildren().addAll(rect,input);

		stpaneNext.getChildren().addAll(circle, btnNextTheme);
		stpanePrev.getChildren().addAll(circle2,btnPreviousTheme);
		stpaneTheme.getChildren().addAll(rect3, lbTheme);
		btnpane.setHgap(5);
		btnpane.add(stpanePrev, 0, 0);
		btnpane.add(stpaneTheme, 1, 0);
		btnpane.add(stpaneNext, 2, 0);
		btnpane.setAlignment(Pos.CENTER);
		
		pane.add(lbFeedback, 0, 0);
		pane.add(stpane2, 0, 2);			
		pane.add(stpane, 0, 3);	
		pane.add(btnpane, 0, 4);

		root.getChildren().addAll(pane);
		root.setTranslateX(30);	

		//will add image and root to root2, otherwise app will use the scene's gradient color
		if(renderImg(imgPath) != null) {
			
			root2.getChildren().addAll(renderImg(imgPath), root);
		}
		else {
			
			root2.getChildren().addAll(root);
		}
		
		System.out.println("scroll style "+ scroll.getStyle());//TEST
		displayMessageAreaAnim();	
	}
	
	static void displayMessageAreaAnim() {
		
		System.out.println("display message area");//TEST
		
		scroll.setDisable(true);
		scroll.setVisible(false);
		lbMessage.setVisible(false);
		input.setDisable(true);
		rect.setOpacity(0);
		
		scale.setFromY(0);
		scale.setToY(1);
		scale.setDuration(Duration.seconds(0.5));
		//scale.setRate(1.5);
		scale.setNode(Gui.rect2);
		scale.play();
		
		//System.out.println(scale.getStatus());
	
		scale.setOnFinished(e -> {
			
			scroll.setDisable(false);
			scroll.setVisible(true);
			lbMessage.setVisible(true);
			input.setDisable(false);
			rect.setOpacity(0.4);
			scale.stop();
			//System.out.println(scale.getStatus());
			}
		);	
	}
	
	static void clear() {
		
		System.out.println("clear window");//TEST
		
		pane.getChildren().clear();	
		root.getChildren().clear();
		root.setTranslateX(0);
		root.setTranslateY(0);	
		root.minWidth(0);
		root.minHeight(0);
		root2.getChildren().clear();
		lbFeedback.setTranslateY(0);
	}
	
	 ImageView renderImg(String image) {
		
		try {
			img = new Image(getClass().getResourceAsStream(image));
			bg = new ImageView(img);
			bg.setFitWidth(432); 
			bg.setPreserveRatio(true);
		} catch(NullPointerException nex) {
			
			System.out.println("Image File Not Found");
		}

		return bg;
	}	
}