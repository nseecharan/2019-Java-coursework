package implementations;
//was considering using your linked list example to help with this file


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.*;

public class AddressBook extends Application{
	public AddressBookGui view = new  AddressBookGui();
	static int fnsize = 20;
	static int lnsize = 20;
	static int citysize = 20;
	static int provsize = 2;
	static int pcsize = 5;
	static int curr_index = 0;
	@Override
	public void start(Stage primaryStage) throws Exception {
	

		view.btnadd.setOnAction(e -> add());
		view.btfirst.setOnAction(e -> first());
		view.btnprevious.setOnAction(e -> previous());
		view.btnlast.setOnAction(e -> last());
		view.btnupdate.setOnAction(e -> update());
		view.btnext.setOnAction(e -> next());
		//view.pane.setGridLinesVisible(true);
	
		
		Scene scene = new Scene(view.root, 640,150);
		scene.setFill(Color.LIGHTSTEELBLUE);
		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}	
	public static void main(String[] args) {
		
		launch(args);
		}


	public  void add() {

		try(RandomAccessFile inout = new RandomAccessFile("address.dat", "rw");){
				
			inout.seek(inout.length());
			write(inout);
			
			Message("Address added",'f');
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void first() {
		
		
		try(RandomAccessFile inout = new RandomAccessFile("address.dat", "rw");){
			
			
			if(inout.length() > 0) {
				
				inout.seek(0);
				read(inout);
				curr_index = 1;
				
				Message("Address # "+curr_index);
				
				System.out.println("The first addres is " + curr_index);
			}
			
			
		} catch (FileNotFoundException e) {
			
			System.out.println("file not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
		}
		
	}
		
	public  void next() {
		
		
		try(RandomAccessFile inout = new RandomAccessFile("address.dat", "rw");){
			
			if(curr_index * 67 < inout.length()) {
	
				inout.seek(curr_index * 67);
				
				read(inout);
				curr_index++;	
				
				Message("Address # "+curr_index);
				System.out.println("The next addres was " + curr_index);
			}
			else {
				
				Message("Address # "+curr_index+ " is currently the last entry",'w');
				System.out.println("End of file");
			}
			
			
		}
		catch(Exception ex) {
			
			System.out.println("next errror");
		};
	}

	public  void previous() {
		
		try(RandomAccessFile inout = new RandomAccessFile("address.dat", "rw");){
			
			
			if(curr_index > 1) {
				
				curr_index--;
			}
			else {
				
				curr_index = 1;
			}
			
			inout.seek((curr_index * 67) - 67);
			read(inout);
			
			Message("Address # "+curr_index);
			System.out.println("The previous addres was " + curr_index);
			
		}
		catch(Exception ex) {
			
			System.out.println("prevous errror");
		}
	}
	
	public  void last() {
		
		
		try(RandomAccessFile inout = new RandomAccessFile("address.dat", "rw");){
			
			curr_index = (int) ((inout.length()) / 67);
			inout.seek((curr_index * 67) - 67);
			read(inout);
			
			Message("Address # "+curr_index + " is currently the last address");
			System.out.println("The last address is " + curr_index);
		}
		catch(Exception ex) {
			
			System.out.println("last errror");
		}	
	}
	
	public  void update() {
		
		try(RandomAccessFile inout = new RandomAccessFile("address.dat", "rw");){
			
			
			inout.seek(curr_index * 67 - 67);
			write(inout);
			Message("Address updated",'f');
			
		}
		catch(Exception ex) {
			
			System.out.println("update errror");
			
		}
		
	}
	
	public  void write(RandomAccessFile inout) {
		
		try {
			System.out.println("first name field width");
			Message("First name is too long, 20 characters maximum", 'w');
			inout.write(fixedLength(view.txfname.getText().getBytes(), fnsize));
			
			
		} catch (IOException e4) {
			view.MSG.setText(e4+""); 
		}
		try {
			System.out.println("last name field width");
			Message("Last name is too long, 20 characters maximum", 'w');
			inout.write(fixedLength(view.txlname.getText().getBytes(), lnsize));
			
			
		} catch (IOException e3) {
			view.MSG.setText(e3+""); 
		}
		try {
			System.out.println("city field width");
			Message("City name is too long, 20 characters maximum", 'w');
			inout.write(fixedLength(view.txcity.getText().getBytes(), citysize));
			
			
		} catch (IOException e2) {
			view.MSG.setText(e2+""); 
		}
		try {
			System.out.println("province field width");
			Message("Province name is too long, 2 characters maximum", 'w');
			inout.write(fixedLength(view.txprovince.getText().getBytes(), provsize));
			
			
		} catch (IOException e1) {
			view.MSG.setText(e1+""); 
		}
		try {
			System.out.println("postal code field width");
			Message("Postal code is too long, 5 characters maximum", 'w');
			inout.write(fixedLength(view.txpostalcode.getText().getBytes(), pcsize));
			
			
		} catch (IOException e) {
			view.MSG.setText(e+""); 
		}
		
		view.MSG.setText(""); // ran out of time to really test this setup
		System.out.println("Address #" + curr_index + " saved!");
	}

	public  void read(RandomAccessFile inout) throws IOException {
		
		int pos;

		
		byte[] name = new byte[fnsize];
		pos = inout.read(name);
		view.txfname.setText(new String(name));
		byte[] street = new byte[lnsize];
		pos +=inout.read(street);
		view.txlname.setText(new String(street));
		byte[] city = new byte[citysize];
		pos +=inout.read(city);
		view.txcity.setText(new String(city));
		byte[] province = new byte[provsize];
		pos +=inout.read(province);
		view.txprovince.setText(new String(province));
		byte[] postalcode = new byte[pcsize];
		pos +=inout.read(postalcode);
		view.txpostalcode.setText(new String(postalcode));	
	}
	
	public byte[] fixedLength(byte[] checkbytes, int size) {
		
		byte[] b = new byte[size];
		
		for(int i = 0; i<checkbytes.length;i++) {
			
			b[i] = checkbytes[i];		
		}
		
		System.out.println(b.length);
		
		return b;
	}
	
	public  void Message(String str) {

		view.MSG.setTextFill(Color.WHITESMOKE);
		
		view.MSG.setText(str);
		
	}
	
	public  void Message(String str, char msgtype) {

		view.MSG.setTextFill(Color.LIGHTCORAL);

		view.MSG.setText(str);
		
	}
	
	
}
