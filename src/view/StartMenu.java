package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import view.Assets.Assets;

@SuppressWarnings("serial")
public class StartMenu extends JFrame{
	
	final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private JTextField textBox1;
	private JTextField textBox2;
	
	private JButton newGameButton;
	private JButton quitButton;
	
	public String getPlayer1() {
		return textBox1.getText();
	}
	
	public String getPlayer2() {
		return textBox2.getText();
	}
	
	public StartMenu(Controller controller) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		int width = controller.getAssets().getStartPageIcon().getIconWidth();
		int height =controller.getAssets().getStartPageIcon().getIconHeight();
		
		setBounds(SCREEN_WIDTH/2 - width/2, SCREEN_HEIGHT/2 - height/2, width, height);
		setLayout(new BorderLayout());
		
		ImageIcon inputBackgroundIcon = new ImageIcon(Assets.class.getResource("inputBackground.png"));
		Image iputBackgroundImage = inputBackgroundIcon.getImage().getScaledInstance(width/2, height/2, Image.SCALE_DEFAULT);
		
		BackPanel inputPanel = new BackPanel(iputBackgroundImage);
		inputPanel.setMaximumSize(new Dimension(width/2, height/2));
		inputPanel.setLayout(new GridLayout(5, 1));
		inputPanel.setOpaque(false);
		
		textBox1 = new JTextField("Player1");
		textBox1.setFont(controller.getAssets().getGameFont(18f));
		textBox1.setForeground(Color.GRAY.brighter());
		textBox2 = new JTextField("Player2");
		textBox2.setFont(controller.getAssets().getGameFont(18f));
		textBox2.setForeground(Color.GRAY.brighter());
		
		JLabel label1 = new JLabel("   Enter The First Player's Name:\n");
		label1.setFont(controller.getAssets().getGameFont(24f));
		label1.setForeground(Color.WHITE);
		inputPanel.add(label1);
		inputPanel.add(textBox1);
		
		JLabel label2 = new JLabel("   Enter The Second Player's Name:\n");
		label2.setFont(controller.getAssets().getGameFont(24f));
		label2.setForeground(Color.WHITE);
		inputPanel.add(label2);
		inputPanel.add(textBox2);
		inputPanel.add(new JLabel("\n\n"));
		
		BackPanel buttonsPanel = new BackPanel(iputBackgroundImage);
		buttonsPanel.setLayout(new GridLayout(1, 2));
		buttonsPanel.setOpaque(false);
		newGameButton = new JButton();
		int scaleWidth = controller.getAssets().getCellWidth()/2;
		int scaleHeight = controller.getAssets().getCellHeight()/2;
		ImageIcon btn1 = new ImageIcon(Assets.class.getResource("startButton.png"));
		Image start = btn1.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_DEFAULT);
		newGameButton.setIcon(new ImageIcon(start));
		newGameButton.addActionListener(controller);
		newGameButton.setBorderPainted(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setOpaque(false);
		newGameButton.setFocusable(false);
		
		quitButton = new JButton();
		ImageIcon btn2 = new ImageIcon(Assets.class.getResource("exitButton.png"));
		Image exit = btn2.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_DEFAULT);
		quitButton.setIcon(new ImageIcon(exit));
		quitButton.addActionListener(controller);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setOpaque(false);
		quitButton.setFocusable(false);
		
		buttonsPanel.add(newGameButton);
		buttonsPanel.add(quitButton);
		
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	public JButton getNewGameButton() {
		return newGameButton;
	}

	public JButton getQuitButton() {
		return quitButton;
	}
	
	
}
