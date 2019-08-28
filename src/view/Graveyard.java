package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.pieces.Piece;
import model.pieces.sidekicks.SideKick;

@SuppressWarnings("serial")
public class Graveyard extends JDialog {
	
	public Graveyard(Controller controller, JFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));
		String player = "";
		if(controller.getGame().getCurrentPlayer() == controller.getGame().getPlayer1())
			player = "P1";
		else
			player = "P2";
		ArrayList<Piece> deadCharacters = controller.getGame().getCurrentPlayer().getDeadCharacters();
		if(deadCharacters.size() == 0) {
			JLabel label = new JLabel();
			label.setText("NO Dead Characters yet!");
			label.setFont(controller.getAssets().getGameFont(24f));
			setMaximumSize(new Dimension(200, 200));
			panel.add(label);
		} else {
			for (Piece p : deadCharacters) {
				if (p instanceof SideKick) {
					Image img = controller.getAssets().getCharacter(p.getClass().getSimpleName());
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(img));
					panel.add(label);
				} else {
					Image img = controller.getAssets().getCharacter(p.getClass().getSimpleName() + player);
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(img));
					panel.add(label);
				}
			}
		}
		add(panel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
