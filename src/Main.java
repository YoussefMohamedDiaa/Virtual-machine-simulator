
//package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import controller.Controller;

//import com.sun.glass.events.WindowEvent;

import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.Window;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	
	// ==========GUI=================
	static 	String equation="";
	static Stage window;
	static Stage window2;
	static Scene scenez ;
	static Stage fileTextWindow;
	static TextArea cmdTextArea;
	static TextArea fileText;
	static TextArea nameField;
	static Button submitButton = new Button("Login");
	public static  XYChart.Series series1 = new XYChart.Series();
	public static  XYChart.Series series2 = new XYChart.Series();
	public static  XYChart.Series series3 = new XYChart.Series();

	public static  CategoryAxis xAxis = new CategoryAxis();
	public static  NumberAxis yAxis = new NumberAxis();
	public static  BarChart<String,Number> bc = 
         new BarChart<String,Number>(xAxis,yAxis);
	
	public static ListView<register> registers;
	static TextArea TextArea;
	static TextArea TextArea2;

	// ==========GUI=================

	public static Folder desktop;
	public static Folder recycleBin;
	public static Folder gameFolder;
	public static Folder marsFolder;
	public static Folder calculatorFolder;
	public static Memory memory;
	public static Disk disk;
	public static User user;
	public static Terminal terminal;
	public static Stack musicProcess = new Stack();
	public static userProcess lastOpenFile;
	public static GridPane grid;
	public static Stack pathStack = new Stack();
	public static boolean binFull = false;
	public static HashMap<String, Integer> fileIDMap = new HashMap<>();

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		pathStack.push("desktop");
		user = new User();
		schedulingAlgorithm(2500);
		memory = new Memory();
		disk = new Disk();
		desktop = new Folder("desktop");
		recycleBin = new Folder("RecycleBin");
		marsFolder = new Folder("Mars");
		calculatorFolder = new Folder("calculator");
		gameFolder = new Folder("Game");
		window2 = new Stage();
		
		
        bc.setTitle("statistcis");
     	scenez  = new Scene(bc,800,600);
	    statistics((int)(memory.getAvailable()*Math.random()), (int)(memory.getAvailable()*Math.random()), disk.getAvailable());	
		desktop.path = "desktop";
		terminal = new Terminal();
		terminal.getCurrentDirectory().arrayFolder.add(new Folder("joe"));
		terminal.getCurrentDirectory().arrayFolder.add(recycleBin);
		terminal.getCurrentDirectory().arrayFolder.add(marsFolder);
		terminal.getCurrentDirectory().arrayFolder.add(calculatorFolder);
		terminal.getCurrentDirectory().arrayFolder.add(gameFolder);
		terminal.getCurrentDirectory().arrayFolder.add(new Folder("moe"));
		terminal.getCurrentDirectory().arrayFolder.add(new Folder("zizo"));
		terminal.getCurrentDirectory().arrayFolder.get(0).arrayFolder.add(new Folder("minimoe"));
		terminal.getCurrentDirectory().arrayFolder.get(0).arrayFolder.get(0).arrayFolder.add((new Folder("smallmo")));
		terminal.getCurrentDirectory().arrayFile.add(new File("bye"));
		terminal.getCurrentDirectory().arrayFile.get(0).addText("Heydude");
		terminal.getCurrentDirectory().arrayFolder.get(0).arrayFile.add(new File("HELLOTHERE"));
		terminal.getCurrentDirectory().arrayFolder.get(0).arrayFolder.get(0).arrayFile.add(new File("HELLOMAN"));
		// ==================================================GUI===================================================================

		window = new Stage();
		window.setTitle("Command Prompt");
		cmdTextArea = new TextArea();
		cmdTextArea.setPrefHeight(400);
		cmdTextArea.setPrefWidth(900);
		cmdTextArea.setStyle("-fx-control-inner-background: #000000; -fx-font-size:16px;-fx-font-weight:bold;");
		cmdTextArea.appendText(
				"Microsoft Windows [Version 10.0.17134.648]" + "\n" + "(c) 2018 Microsoft Corporation." + "\n" + "\n");
		Scene cmdScene = new Scene(cmdTextArea);
		window.setScene(cmdScene);
		window.show();
		
		

		grid = new GridPane();
		grid.setPrefHeight(800);
		grid.setPrefWidth(1500);
		grid.setPadding(new Insets(2));
		grid.setHgap(50);
		grid.setVgap(50);
		grid.setStyle(
				"-fx-background-size: 1500px; -fx-background-repeat:no-repeat; -fx-background-image: url('windows.jpg')");
		int folder = 0;
		Button refreshButton = new Button();
		refreshButton.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				updateStatistics((int)(memory.getAvailable()*Math.random()), (int)(memory.getAvailable()*Math.random()), disk.getAvailable());
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				refresh();
			}
		});
		refreshButton.setStyle(
				"-fx-background-size: 21px; -fx-background-repeat: no-repeat;-fx-background-image: url('refresh.jpg');");

		Button backButton = new Button();
		backButton.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				updateStatistics((int)(memory.getAvailable()*Math.random()), (int)(memory.getAvailable()*Math.random()), disk.getAvailable());
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				back();
			}
		});
		backButton.setStyle(
				"-fx-background-size: 21px; -fx-background-repeat: no-repeat;-fx-background-image: url('back.jpg');");

		int r = 1;
		for (; r < 5 && folder < desktop.getArrayFolder().size(); r++)
			for (int j = 0; folder < desktop.getArrayFolder().size() && j < 5; folder++, j++) {

				Button button = new Button('\n' + desktop.getArrayFolder().get(folder).Name);
				
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("calculator")
						&& desktop.getArrayFolder().get(folder).Name.equals("calculator"))
					button.setOnMouseClicked(new EventHandler() {
						@Override
						public void handle(Event e) {
							updateStatistics((int)(memory.getAvailable()*Math.random()),(int)(memory.getAvailable()*Math.random()), disk.getAvailable());
							ClassLoader CLDR = this.getClass().getClassLoader();
							InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
							AudioStream audioStream = null;
							try {
								audioStream = new AudioStream(soundName);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							AudioPlayer.player.start(audioStream);
							Stage windowMars = new Stage();
							windowMars.setTitle("calculator");
						
							Scene Calulator = Calulator();
							windowMars.setScene(Calulator);
							windowMars.show();
						}
					});
				else 
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("Game")
						&& desktop.getArrayFolder().get(folder).Name.equals("Game")) {
					button.setOnMouseClicked(new EventHandler() {
						@Override
						public void handle(Event e) {
							updateStatistics((int)(memory.getAvailable()*Math.random()),(int)(memory.getAvailable()*Math.random()), disk.getAvailable());
							ClassLoader CLDR = this.getClass().getClassLoader();
							InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
							AudioStream audioStream = null;
							try {
								audioStream = new AudioStream(soundName);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							AudioPlayer.player.start(audioStream);
							try {
								
								Controller shc = new Controller();
								shc.main(null);
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					});
				}
				else
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("Mars")
						&& desktop.getArrayFolder().get(folder).Name.equals("Mars"))
					button.setOnMouseClicked(new EventHandler() {
						@Override
						public void handle(Event e) {
							updateStatistics((int)(memory.getAvailable()*Math.random()),(int)(memory.getAvailable()*Math.random()), disk.getAvailable());
							ClassLoader CLDR = this.getClass().getClassLoader();
							InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
							AudioStream audioStream = null;
							try {
								audioStream = new AudioStream(soundName);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							AudioPlayer.player.start(audioStream);
							Stage windowMars = new Stage();
							windowMars.setTitle("Mars");
						
							Scene marsScene = createMarsGUI();
							windowMars.setScene(marsScene);
							windowMars.show();
						}
					});
				else 
					button.setOnMouseClicked(new EventHandler() {
					@Override
					public void handle(Event e) {
						updateStatistics((int)(memory.getAvailable()*Math.random()),(int)(memory.getAvailable()*Math.random()), disk.getAvailable());
						ClassLoader CLDR = this.getClass().getClassLoader();
						InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
						AudioStream audioStream = null;
						try {
							audioStream = new AudioStream(soundName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						AudioPlayer.player.start(audioStream);
						openedFolder(button.getText().substring(1));
					}
				});
				
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("calculator")
						&& desktop.getArrayFolder().get(folder).Name.equals("calculator")) {
					button.setStyle(
							"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('calPic.png');");
				}
				else
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("Game")
						&& desktop.getArrayFolder().get(folder).Name.equals("Game")) {
					button.setStyle(
							"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('startPage.png');");
				}
				else
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("Mars")
						&& desktop.getArrayFolder().get(folder).Name.equals("Mars")) {
					button.setStyle(
							"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('marsPic.png');");
				}
				else if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("RecycleBin")
						&& desktop.getArrayFolder().get(folder).Name.equals("RecycleBin")) {
					if (!binFull)
						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('recyclebin.jpg');");
					else
						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('fullrecyclebin.jpg');");

				} 
				else {
					if (desktop.getArrayFolder().get(folder).getArrayFolder().size()
							+ desktop.getArrayFolder().get(folder).getArrayFile().size() == 0)
						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 30px; -fx-background-repeat: no-repeat;-fx-background-image: url('folderEmpty.jpg');");
					else
						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 30px; -fx-background-repeat: no-repeat;-fx-background-image: url('folder.jpg');");
				}
				grid.add(button, j, r);
			}
		int file = 0;
		for (; r < 10 && file < desktop.getArrayFile().size(); r++)
			for (int j = 0; file < desktop.getArrayFile().size() && j < 5; file++, j++) {
				Button button = new Button('\n' + desktop.getArrayFile().get(file).Name);
				button.setOnMouseClicked(new EventHandler() {
					@Override
					public void handle(Event e) {
						updateStatistics((int)(memory.getAvailable()*Math.random()), (int)(memory.getAvailable()*Math.random()), disk.getAvailable());
						ClassLoader CLDR = this.getClass().getClassLoader();
						InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
						AudioStream audioStream = null;
						try {
							audioStream = new AudioStream(soundName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						AudioPlayer.player.start(audioStream);
						Process process = new userProcess(user.getID(), 4, 5,
								"openFile " + button.getText().substring(1));
						user.pushProcess(process);
//						openedFile(button.getText().substring(1));
					}
				});
				button.setStyle(
						"-fx-background-color: transparent; -fx-background-size: 30px; -fx-background-repeat: no-repeat;-fx-text-fill: #000000;-fx-background-image: url('file.jpg');");
				grid.add(button, j, r);
			}
		ScrollPane scrollPane = new ScrollPane(grid);
		BorderPane root2 = new BorderPane();

		HBox hbox1 = new HBox(backButton);
		hbox1.setStyle("-fx-border-style: solid inside;   -fx-background-color: #2f4f4f;\n" + "    -fx-spacing: 10;");

		root2.setTop(hbox1); // Set header
		root2.setCenter(scrollPane); // add your table
		
		//--------------------
		Button startButton = new Button("Start");
		
	
		HBox footer = new HBox(startButton);
		footer.setStyle("-fx-border-style: solid inside;   -fx-background-color: #147fcc;\n" + "    -fx-spacing: 10;");
		

		startButton.setStyle(" -fx-background-color:\n" + 
				"        linear-gradient(#f0ff35, #a9ff00),\n" + 
				"        radial-gradient(center 50% -40%, radius 200%, #0f9e30 45%, #0e8c2b 50%);\n" + 
				"   -fx-background-radius: 9,8,5,4;\n" + 
				"    -fx-background-insets: 0,1,4,5;\n" + 
				"-fx-padding: 15 65 15 65;\n"+
				"-fx-font-weight: bold;\n"+
				"-fx-font-size: 22px;\n"+
				"-fx-font-style	:italic ;\n"+
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" + 
				"    -fx-text-fill: #ffffff;");
		
		VBox basix = new VBox();
	
		basix.getChildren().addAll(root2,footer);
		//---------------------------------------------
		Scene scene2 = new Scene(basix);

		// ========================================================================================================================
		GridPane gridPane = createRegistrationFormPane();

		addUIControls(gridPane);

		Scene scene1 = new Scene(gridPane);
		primaryStage.setScene(scene1);
		primaryStage.show();

		submitButton.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				updateStatistics((int)(memory.getAvailable()*Math.random()), (int)(memory.getAvailable()*Math.random()), disk.getAvailable());
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("oxp.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				if (nameField.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your name");
					return;
				} else {
					
			
					
			
					
					
					

					primaryStage.setScene(scene2);
				}

			}
		});

		// ========================================================================================================================

		// ==================================================GUI===================================================================

		Scanner sc = new Scanner(System.in);

		cmdTextArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					String[] arr = cmdTextArea.getText().split("\n");

					cmdTextArea.appendText("\n");
					String cmd = arr[arr.length - 1];

					if (cmd.equals("hacker mode")) {
						cmd = "clear";
						cmdTextArea.setStyle(
								"-fx-control-inner-background: #000000; -fx-text-fill:green; -fx-font-size: 16px;-fx-font-weight:bold;");
						cmdTextArea.appendText("Microsoft Windows [Version 10.0.17134.648]" + "\n"
								+ "(c) 2018 Microsoft Corporation." + "\n" + "\n");
					}

					if (cmd.equals("clear")) {
						cmdTextArea.setText("");
						cmdTextArea.appendText("Microsoft Windows [Version 10.0.17134.648]" + "\n"
								+ "(c) 2018 Microsoft Corporation." + "\n" + "\n");
					}

					String firstPart = cmd.split(" ")[0];
					// System.out.println(firstPart);
					Process process = null;
					// System.out.println("here" + cmd);
					if (!cmd.equals("clear"))
						switch (firstPart) {
						case "ls":
							// System.out.println("in ls");
							process = new userProcess(user.getID(), 3, 15, cmd);
							break;
						case "cd":
							process = new userProcess(user.getID(), 5, 4, cmd);
							break;
						case "touch":
							process = new userProcess(user.getID(), 4, 4, cmd);
							break;
						case "create":
							process = new userProcess(user.getID(), 6, 4, cmd);
							break;
						case "pwd":
							process = new userProcess(user.getID(), 3, 4, cmd);
							break;
						case "deleteFolder":
							process = new userProcess(user.getID(), 4, 4, cmd);
							binFull = true;
							break;
						case "deleteFile":
							process = new userProcess(user.getID(), 7, 4, cmd);
							binFull = true;
							break;
						case "play": {
							process = new userProcess(user.getID(), 3, 7, cmd);
							musicProcess.push(process);
							break;
						}
						case "stop":
							process = new userProcess(user.getID(), 1, 5, cmd);
							break;
						case "openFile":
							process = new userProcess(user.getID(), 4, 5, cmd);
							lastOpenFile = new userProcess(process.getID(), 4, 5, cmd);
							break;
						case "closeFile":
							process = new userProcess(user.getID(), 1, 0, cmd); // change weight in memory to 0!!!
							break;
						case "editFile":
							process = new userProcess(user.getID(), 2, 10, cmd);
							break;
						}
					if (process != null) {
						System.out.println("pushed process");
						user.pushProcess(process);
					}
				}
			}
		});

	}
	
public static void statistics(int cpu, int memory, int disk ) {
		
		window2.setTitle("statistcis");
		
		
	        xAxis.setLabel("pc perfromance keys");       
	        yAxis.setLabel("Value");
	 
	        series1.setName("cpu avaliable");       
	        series1.getData().add(new XYChart.Data("cpu avaliable", cpu));
	     
	        series2.setName("memory avaliable");
	        series2.getData().add(new XYChart.Data("memory avaliable", memory));
	    
	        
	        series3.setName("disk avaliable");
	        series3.getData().add(new XYChart.Data("disk avaliable", disk));
	       
	        
	     
	        bc.getData().addAll(series1, series2, series3);
	       
	    	window2.setScene(scenez);
			window2.show();		
	}
	
public static void updateStatistics(int cpu, int memory, int disk ) {


     window2.setTitle("statistcis");
		
		
     xAxis.setLabel("pc perfromance keys");       
     yAxis.setLabel("Value");

      series1.getData().clear();
     series1.setName("cpu avaliable");       
     series1.getData().add(new XYChart.Data("cpu avaliable", cpu));
  
     series2.getData().clear();
     series2.setName("memory avaliable");
     series2.getData().add(new XYChart.Data("memory avaliable", memory));
 
     
     series3.getData().clear();

     series3.setName("disk avaliable");
     series3.getData().add(new XYChart.Data("disk avaliable", disk));
    

	}

	public static void main(String[] args) {
		launch(args);
	}

	public  void schedulingAlgorithm(int delay) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.print("");
					try {
						if (!user.getPriorityQueue().isEmpty()) {
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									refresh();

								}

							});
							System.out.println("in thread");
							// System.out.println(Arrays.toString(memory.memory));
							Process process = user.getPriorityQueue().peek();
							boolean assigned = memory.assignProcess(process);
							if (assigned) {
								terminal.executeCommand(user.getPriorityQueue().poll().getPcb());
								if (process.getPcb().split(" ")[0].equals("openFile")) {
									System.out.println(terminal.getCurrentDirectory().path);
									fileIDMap.put(
											terminal.getCurrentDirectory().path + "/" + process.getPcb().split(" ")[1],
											process.getID());
									openedFile(process.getPcb().split(" ")[1]);
								}
							}
							if (assigned && !process.getPcb().split(" ")[0].equals("play")
									&& !process.getPcb().split(" ")[0].equals("openFile")) {
								System.out.println(process.getPcb().split(" ")[0]);
								if (process.getPcb().split(" ")[0].equals("stop")) {
									memory.removeProcess((Process) musicProcess.pop());
								}
								if (process.getPcb().split(" ")[0].equals("closeFile")) {
									int id = fileIDMap.get(
											terminal.getCurrentDirectory().path + "/" + process.getPcb().split(" ")[1]);
									memory.removeProcess(id);
									Platform.runLater(new Runnable() {

										@Override
										public void run() {
//											 TODO Auto-generated method stub
											fileTextWindow.close();
										}
									});
									System.out.println("id: " + id);
								}
																memory.removeProcess(process);
								user.getPriorityQueue().poll();
							}
							System.out.println(Arrays.toString(memory.memory)); // memory
						}
					} catch (Exception e) {
				
						System.out.println(e.getMessage());
					}
				}
			}
		}.start();
	}

	public  void openedFolder(String path) {
		for (Folder f : terminal.getCurrentDirectory().getArrayFolder())
			if (f.Name.equals(path)) {
				terminal.changeDirectory(f);
				pathStack.push(pathStack.peek() + "/" + path);
				System.out.println(pathStack.toString());
				break;
			}
		refresh();

	}

	public  void openedFile(String path) {
		
		System.out.println("Path: " + path);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				String text = "";
				fileTextWindow = new Stage();
				fileTextWindow.setTitle(path);
				fileText = new TextArea();
				fileText.setPrefHeight(400);
				fileText.setPrefWidth(900);
				fileText.setStyle("-fx-control-inner-background: #ffffff; -fx-font-size: 16px;-fx-font-weight:bold;");
				for (File f : terminal.getCurrentDirectory().getArrayFile())
					if (f.Name.equals(path)) {
						text = f.getText();
						break;
					}
				fileText.appendText(text);
				// TODO Auto-generated method stub
				fileText.setEditable(false);
				Scene textScene = new Scene(fileText);
				fileTextWindow.setScene(textScene);
				fileTextWindow.show();
				System.out.println("Current directory: " + terminal.getCurrentDirectory().path);
				fileTextWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						Process process = new userProcess(user.getID(), 4, 5, "closeFile " + path); // how to handle
																									// this?
						user.pushProcess(process);
						System.out.println("Stage is closing");
					}
				});
			}
		});

		
	}

	public  void refresh() {
		grid.getChildren().clear();
		grid.setPadding(new Insets(2));
		grid.setHgap(50);
		grid.setVgap(50);
		grid.setStyle("-fx-background-size: 1500px; -fx-background-repeat:no-repeat;");
		if (terminal.getCurrentDirectory().path != null && terminal.getCurrentDirectory().path.equals("desktop"))
			grid.setStyle(
					"-fx-background-size: 1500px; -fx-background-repeat:no-repeat; -fx-background-image: url('windows.jpg')");
		int folder = 0;
		int r = 1;
		for (; r < 5 && folder < terminal.getCurrentDirectory().getArrayFolder().size(); r++)
			for (int j = 0; folder < terminal.getCurrentDirectory().getArrayFolder().size() && j < 5; folder++, j++) {
				Button button = new Button('\n' + terminal.getCurrentDirectory().getArrayFolder().get(folder).Name);
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("calculator")
						&& desktop.getArrayFolder().get(folder).Name.equals("calculator"))
					button.setOnMouseClicked(new EventHandler() {
						@Override
						public void handle(Event e) {
							updateStatistics((int)(memory.getAvailable()*Math.random()),(int)(memory.getAvailable()*Math.random()), disk.getAvailable());
							ClassLoader CLDR = this.getClass().getClassLoader();
							InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
							AudioStream audioStream = null;
							try {
								audioStream = new AudioStream(soundName);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							AudioPlayer.player.start(audioStream);
							Stage windowMars = new Stage();
							windowMars.setTitle("calculator");
						
							Scene Calulator = Calulator();
							windowMars.setScene(Calulator);
							windowMars.show();
						}
					});
				else 
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("Mars")
						&& desktop.getArrayFolder().get(folder).Name.equals("Mars"))
					button.setOnMouseClicked(new EventHandler() {
						@Override
						public void handle(Event e) {
							updateStatistics((int)(memory.getAvailable()*Math.random()),(int)(memory.getAvailable()*Math.random()), disk.getAvailable());
							ClassLoader CLDR = this.getClass().getClassLoader();
							InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
							AudioStream audioStream = null;
							try {
								audioStream = new AudioStream(soundName);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							AudioPlayer.player.start(audioStream);
							Stage windowMars = new Stage();
							windowMars.setTitle("Mars");
						
							Scene marsScene = createMarsGUI();
							windowMars.setScene(marsScene);
							windowMars.show();
						}
					});
				else 
				button.setOnMouseClicked(new EventHandler() {
					@Override
					public void handle(Event e) {
						updateStatistics((int)(memory.getAvailable()*Math.random()), (int)(memory.getAvailable()*Math.random()), disk.getAvailable());
						ClassLoader CLDR = this.getClass().getClassLoader();
						InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
						AudioStream audioStream = null;
						try {
							audioStream = new AudioStream(soundName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						AudioPlayer.player.start(audioStream);
						
						openedFolder(button.getText().substring(1));
					}
				});
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("calculator")
						&& desktop.getArrayFolder().get(folder).Name.equals("calculator")) {
					button.setStyle(
							"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('calPic.png');");
				}
				else
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("Game")
						&& desktop.getArrayFolder().get(folder).Name.equals("Game")) {
					button.setStyle(
							"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('startPage.png');");
				}
				else
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("Mars")
						&& desktop.getArrayFolder().get(folder).Name.equals("Mars")) {
					button.setStyle(
							"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('marsPic.png');");
				}
			else
				if (terminal.getCurrentDirectory().getArrayFolder().get(folder).Name.equals("RecycleBin")
						&& desktop.getArrayFolder().get(folder).Name.equals("RecycleBin")) {
					if (!binFull)
						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('recyclebin.jpg');");
					else
						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 35px; -fx-background-repeat: no-repeat;-fx-background-image: url('fullrecyclebin.jpg');");

				} else {

					if (terminal.getCurrentDirectory().getArrayFolder().get(folder).getArrayFolder().size()
							+ terminal.getCurrentDirectory().getArrayFolder().get(folder).getArrayFile().size() == 0)

						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 30px; -fx-background-repeat: no-repeat;-fx-background-image: url('folderEmpty.jpg');");
					else
						button.setStyle(
								"-fx-background-color: transparent; -fx-background-size: 30px; -fx-background-repeat: no-repeat;-fx-background-image: url('folder.jpg');");
				}
				grid.add(button, j, r);
			}
		int file = 0;
		for (; r < 10 && file < terminal.getCurrentDirectory().getArrayFile().size(); r++)
			for (int j = 0; file < terminal.getCurrentDirectory().getArrayFile().size() && j < 5; file++, j++) {
				Button button = new Button('\n' + terminal.getCurrentDirectory().getArrayFile().get(file).Name);
				button.setOnMouseClicked(new EventHandler() {
					@Override
					public void handle(Event e) {
						updateStatistics((int)(memory.getAvailable()*Math.random()), (int)(memory.getAvailable()*Math.random()), disk.getAvailable());
						ClassLoader CLDR = this.getClass().getClassLoader();
						InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
						AudioStream audioStream = null;
						try {
							audioStream = new AudioStream(soundName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						AudioPlayer.player.start(audioStream);
						Process process = new userProcess(user.getID(), 4, 5,
								"openFile " + button.getText().substring(1));
						user.pushProcess(process);
					}
				});
				button.setStyle(
						"-fx-background-color: transparent; -fx-background-size: 30px; -fx-background-repeat: no-repeat;-fx-text-fill: #000000;-fx-background-image: url('file.jpg');");
				grid.add(button, j, r);
			}
	}

	public void back() {
		if (pathStack.size() > 1) {
			pathStack.pop();
		}
		terminal.executeCommand("cd " + pathStack.peek());
		refresh();
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	public void render() {
		Folder currDir = terminal.getCurrentDirectory();
		ArrayList<File> fileList = currDir.getArrayFile();
		ArrayList<Folder> folderList = currDir.getArrayFolder();

	}

	private GridPane createRegistrationFormPane() {
		// Instantiate a new Grid Pane
		GridPane gridPane = new GridPane();

		gridPane.setPrefHeight(800);
		gridPane.setPrefWidth(1500);
		gridPane.setPadding(new Insets(2));
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setStyle(
				"-fx-background-size: 1500px; -fx-background-repeat:no-repeat; -fx-background-image: url('login.jpg')");

		gridPane.setAlignment(Pos.CENTER);

		return gridPane;
	}
	
	private Scene Calulator() {
		BorderPane root = new BorderPane();

		//////////////////////// display/////////////////////////////////////////////////////////////////////////////
		TextArea TextArea = new TextArea();
		TextArea.setStyle("-fx-border-color: Black;"
				+ "-fx-control-inner-background: Black	; -fx-font-size:30px;-fx-font-weight:bold;");
		HBox base2 = new HBox();
		base2.getChildren().addAll(TextArea);
		//////////////////////// display-end/////////////////////////////////////////////////////////////////////////////

		//////////////////////////// 789/////////////////////////////////////////////////////////////////////////////////
		Button Button7 = new Button("7");
		Button Button8 = new Button("8");
		Button Button9 = new Button("9");
		Button Buttonx = new Button("x");
		Button7.setStyle("-fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Button8.setStyle(" -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Button9.setStyle(" -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Buttonx.setStyle(" -fx-background-color: \r\n" + "        linear-gradient(#ffd65b, #e68400),\r\n"
				+ "        linear-gradient(#ffef84, #f2ba44),\r\n" + "        linear-gradient(#ffea6a, #efaa22),\r\n"
				+ "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n"
				+ "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0)); "
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,15,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #ffffff;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		HBox base3 = new HBox();
		base3.getChildren().addAll(Button7, Button8, Button9, Buttonx);
		//////////////////////////// 789end/////////////////////////////////////////////////////////////////////////////////

		/////////////////////////// 456/////////////////////////////////////////////////////////////////////////////////////
		Button Button4 = new Button("4");
		Button Button5 = new Button("5");
		Button Button6 = new Button("6");
		Button Button_ = new Button("-");
		Button4.setStyle("-fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Button5.setStyle(" -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Button6.setStyle(" -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Button_.setStyle(" -fx-background-color: \r\n" + "        linear-gradient(#ffd65b, #e68400),\r\n"
				+ "        linear-gradient(#ffef84, #f2ba44),\r\n" + "        linear-gradient(#ffea6a, #efaa22),\r\n"
				+ "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n"
				+ "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0)); "
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,15,5,4;\r\n"
				+ "    -fx-padding: 15 33 15 33;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #ffffff;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		HBox base4 = new HBox();
		base4.getChildren().addAll(Button4, Button5, Button6, Button_);
		/////////////////////////////////// 456end/////////////////////////////////////////////////////////////////////////////

		/////////////////////////// 123/////////////////////////////////////////////////////////////////////////////////////
		Button Button1 = new Button("1");
		Button Button2 = new Button("2");
		Button Button3 = new Button("3");
		Button Buttonp = new Button("+");
		Button1.setStyle("-fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Button2.setStyle(" -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Button3.setStyle(" -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Buttonp.setStyle(" -fx-background-color: \r\n" + "        linear-gradient(#ffd65b, #e68400),\r\n"
				+ "        linear-gradient(#ffef84, #f2ba44),\r\n" + "        linear-gradient(#ffea6a, #efaa22),\r\n"
				+ "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n"
				+ "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0)); "
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,15,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #ffffff;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		HBox base5 = new HBox();
		base5.getChildren().addAll(Button1, Button2, Button3, Buttonp);
		/////////////////////////////////// 123end/////////////////////////////////////////////////////////////////////////////
 
		/////////////////////////// 0.=/////////////////////////////////////////////////////////////////////////////////////
		Button Button0 = new Button("0");
		Button Buttond = new Button(".");
		Button Buttoneq = new Button("=");
		Button0.setStyle("-fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 65 15 65;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		Buttond.setStyle(" -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n"
				+ "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,8,5,4;\r\n"
				+ "    -fx-padding: 15 34 15 34;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #333333;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
	
		Buttoneq.setStyle(" -fx-background-color: \r\n" + "        linear-gradient(#ffd65b, #e68400),\r\n"
				+ "        linear-gradient(#ffef84, #f2ba44),\r\n" + "        linear-gradient(#ffea6a, #efaa22),\r\n"
				+ "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n"
				+ "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0)); "
				+ "    -fx-background-insets: 0,1,4,5;\r\n" + "    -fx-background-radius: 9,15,5,4;\r\n"
				+ "    -fx-padding: 15 30 15 30;\r\n" + "    -fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: #ffffff;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
		HBox base6 = new HBox();
		base6.getChildren().addAll(Button0, Buttond, Buttoneq);
		/////////////////////////////////// 0.=end/////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////listeners///////////////////////////////////////////////////////////////////////////
		double LastResult=0;
		double currentResult=0;
		char LastOperation = '+';
		Button0.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"0");
			}
		
		});
		Button1.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"1");
			}
		
		});
		Button2.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"2");
			}
		});
		Button3.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"3");
			}
		});
		Button4.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"4");
			}
		});
		Button5.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"5");
			}
		});
		Button6.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"6");
			}
		});
		Button7.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"7");
			}
		});
		Button8.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"8");
			}
		});
		Button9.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				String s=TextArea.getText();
				if(s.length()!=0)
				if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='x'||s.charAt(s.length()-1)=='-')
					TextArea.clear();
				TextArea.appendText(
						"9");
			}
		});
		Button_.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				equation+=TextArea.getText()+"-";
				TextArea.clear();
				TextArea.appendText(
						"-");

			}
		});
		Buttonp.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				equation+=TextArea.getText()+"+";
				TextArea.clear();
				TextArea.appendText(
						"+");
			}
		});
		Buttonx.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				equation+=TextArea.getText()+"x";
				TextArea.clear();
				TextArea.appendText(
						"x");
			}
		});
		Buttond.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
			
				TextArea.appendText(
						".");
			}
		});
		Buttoneq.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				ClassLoader CLDR = this.getClass().getClassLoader();
				InputStream soundName = CLDR.getResourceAsStream("mouse.wav");
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(soundName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AudioPlayer.player.start(audioStream);
				equation+=TextArea.getText();
				TextArea.clear();
				TextArea.appendText(""+calculate(equation+"+"));
			}
		});
		///////////////////////////lisnterend////////////////////////////////////////////////////////////////////////////
		VBox base = new VBox();
		base.getChildren().addAll(base2, base3, base4, base5, base6);
		Scene scene = new Scene(base, 285, 325);

       	return scene;
		
	}
	
	public static double calculate(String eq) {
		double last=0.0;
		double current=0.0;
		char lastop='+';
		String cur="";
		for(int i=0;i<eq.length();i++) {
			System.out.println(last);
			if(eq.charAt(i)=='-'||eq.charAt(i)=='+'||eq.charAt(i)=='x') {
				current=Double.parseDouble(cur);
				cur="";
				if(lastop=='+')
					last+=current;
				if(lastop=='-')
					last-=current;
				if(lastop=='x')
					last*=current;
				
				if(eq.charAt(i)=='-')
					lastop='-';
				if(eq.charAt(i)=='+')
					lastop='+';
				if(eq.charAt(i)=='x')
					lastop='x';
				
				
			}else {
				cur+=eq.charAt(i);
			}
		}		
		return last;
	}
	
	private Scene createMarsGUI() {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
	
		/////////////selection box for registers///////////////////////
		ListView<register> registers = new ListView<>();
		ListView<register> registers2 = new ListView<>();
		registers.setMaxWidth(70);
		registers2.setMaxWidth(70);
	
		
		HBox selection0 = new HBox();
		selection0.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: BlueViolet;");
		
		
		
	
		
		selection0.getChildren().addAll( registers,registers2);
		
		ArrayList<register> registersar = new ArrayList<register>();
     
    	
		ArrayList<register> registersar2 = new ArrayList<register>();
        registersar.add(new register("$s0"));
        registersar.add(new register("$s1"));
        registersar.add(new register("$s2"));
        registersar.add(new register("$s3"));
        registersar.add(new register("$s4"));
        registersar.add(new register("$t0"));
        registersar.add(new register("$t1"));
        registersar.add(new register("$t2"));
        registersar.add(new register("$ra"));
        registersar.add(new register("$sp"));
        registersar.add(new register("$v0"));
        registersar.add(new register("$v1"));
        registersar.add(new register("$a0"));
        registersar.add(new register("$a1"));
        registersar.add(new register("$a2"));
        registersar.add(new register("$pc"));
        
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
        registersar2.add(new register("0"));
      
        
		
		registers.getItems().addAll(registersar);
		registers2.getItems().addAll(registersar2);
		
		////////////////////////////////////////////////////

		
		/////////////////////////////////////////////////////////////////////////////////////
		
		HBox selection2 = new HBox();

		TextArea = new TextArea();
		
		TextArea.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: BlueViolet;"
                  + "-fx-control-inner-background: LightSalmon; -fx-font-size:16px;-fx-font-weight:bold;");
		
		TextArea.setPrefHeight(700);
		TextArea.setPrefWidth(1000);
		selection2.getChildren().addAll( TextArea,selection0);
		
		//////////////////////////////////////////////////////////////////////////////////////
		Button runButton = new Button();
		Button debugButton = new Button();
		Button memoButton = new Button();
		runButton.setStyle("-fx-background-size: 22px; -fx-background-repeat: no-repeat;-fx-background-image: url('run.jpg');");
		debugButton.setStyle("-fx-background-size: 22px; -fx-background-repeat: no-repeat;-fx-background-image: url('bug.png');");
		memoButton.setStyle("-fx-background-size: 22px; -fx-background-repeat: no-repeat;-fx-background-image: url('memory.png');");
		HBox header = new HBox(runButton,debugButton,memoButton);
		header.setStyle("-fx-border-style: solid inside;   -fx-background-color: #ffffff;\n" + "    -fx-spacing: 10;");
		
		VBox selection3 = new VBox();
		TextArea2 = new TextArea();
		TextArea2.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: BlueViolet;"
                  + "-fx-control-inner-background: LightSlateGray	; -fx-font-size:16px;-fx-font-weight:bold;");
		TextArea2.setPrefHeight(200);
		TextArea2.setPrefWidth(1000);
		selection3.getChildren().addAll(header ,selection2, TextArea2);
		
		
		Scene scene2 = new Scene(selection3);
		scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		runButton.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				String code = TextArea.getText();
				try {
					dataPath.PC=0;
					for(int i=0;i<dataPath.registers.length;i++)
						Arrays.fill(dataPath.registers[i], false);
					for(int i=0;i<dataPath.dataMemorySize;i++)
						Arrays.fill(dataPath.dataMemory[i], false);
					for(int i=0;i<dataPath.instructionMemorySize;i++)
						Arrays.fill(dataPath.instructionMemory[i], true);
					
					Compiler.parse(code);
		            for(int i=0;i<Compiler.commandsList.size();i++)
		            	dataPath.instructionMemory[i]=Compiler.commandsList.get(i);
		            
		            boolean [] allOnes=new boolean [18];
		            Arrays.fill(allOnes, true);
		            
		            while(!deepEquals(dataPath.instructionMemory[dataPath.PC], allOnes))
		            	dataPath.run1Cycle();
		        	
		            ArrayList<register> registersar3 = new ArrayList<register>();
		            for(int j=0;j<dataPath.registers.length;j++) {
		            	registersar3.add(new register(""+dataPath.toInt(dataPath.registers[j])));
		            }  	
	            	registersar3.add(new register(""+(dataPath.PC)));

		            
		            registers2.getItems().clear();
					registers2.getItems().addAll(registersar3);
//					selection0.getChildren().clear();
//					selection0.getChildren().addAll( registers,registers2);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		debugButton.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				String code = null;
				String[] arr = TextArea.getText().split("\n");
				int f=0;
				for(int i=0;i<arr.length;i++) {
					if((arr[i].charAt(0)<'a' || arr[i].charAt(0)>'z') &&f==0) {
						f=1;
						arr[i]=arr[i].substring(1);
						if(i==arr.length-1) {
							code = arr[0];
							arr[0]="\u2192"+arr[0];
						}else{
							code = arr[i+1];
							arr[i+1]="\u2192"+arr[i+1];
							registers2.getItems().clear();
						}
					}
				}
				if(f==0) {
					code = arr[0];
					arr[0]="\u2192"+arr[0];
				}
				String backString = "";
				for(int i=0;i<arr.length;i++) {
					backString+=arr[i]+"\n";
				}
				TextArea.setText(backString);
				try {
					dataPath.PC=0;
					for(int i=0;i<dataPath.registers.length;i++)
						Arrays.fill(dataPath.registers[i], false);
					for(int i=0;i<dataPath.dataMemorySize;i++)
						Arrays.fill(dataPath.dataMemory[i], false);
					for(int i=0;i<dataPath.instructionMemorySize;i++)
						Arrays.fill(dataPath.instructionMemory[i], true);
					
					Compiler.parse(code);
		            for(int i=0;i<Compiler.commandsList.size();i++)
		            	dataPath.instructionMemory[i]=Compiler.commandsList.get(i);
		            
		            boolean [] allOnes=new boolean [18];
		            Arrays.fill(allOnes, true);
		            
		            while(!deepEquals(dataPath.instructionMemory[dataPath.PC], allOnes))
		            	dataPath.run1Cycle();
		        	
		            ArrayList<register> registersar3 = new ArrayList<register>();
		            for(int j=0;j<dataPath.registers.length;j++) {
		            	registersar3.add(new register(""+dataPath.toInt(dataPath.registers[j])));
		            }  	
	            	registersar3.add(new register(""+(dataPath.PC)));

		            
		            registers2.getItems().clear();
					registers2.getItems().addAll(registersar3);		
			
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		memoButton.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event e) {
				String code = TextArea2.getText();
				int memadd = Integer.parseInt(code);
				try {
					TextArea2.setText("Memory at address "+code+" is having value "+dataPath.toInt(dataPath.dataMemory[memadd]));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		return scene2;
	}
	
	public class registerCellFactory implements Callback<ListView<register>, ListCell<register>> {
		@Override
		public ListCell<register> call(ListView<register> listview) {
			return new registerCell();
		}
	}
	
	public class registerCell extends ListCell<register> {
		@Override
		public void updateItem(register item, boolean empty) {
			super.updateItem(item, empty);

			int index = this.getIndex();
			String name = null;
			
		}
	}
	
	public static class register {
		// Declaring the attributes
		private String Name;

		public register(String Name) {
			this.Name = Name;

		}

		public String getFirstName() {
			return Name;
		}

		public void setFirstName(String firstName) {
			this.Name = Name;
		}

		@Override
		public String toString() {
			return Name;
		}
	}
	
	public static boolean deepEquals(boolean [] a,boolean [] b)
	{
		if(a.length!=b.length)return false;
		for(int i=0;i<a.length;i++)
			if(a[i]!=b[i])return false;
		return true;
	}

	private void addUIControls(GridPane gridPane) {

		nameField = new TextArea();
		nameField.setPrefHeight(40);
		nameField.setStyle("-fx-control-inner-background: #ffffff;");

		gridPane.add(nameField, 0, 7, 2, 1);

		submitButton.setPrefHeight(40);
		submitButton.setDefaultButton(true);
		submitButton.setPrefWidth(100);
		gridPane.add(submitButton, 0, 8, 2, 1);
		GridPane.setHalignment(submitButton, HPos.CENTER);
		GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

	}
}
