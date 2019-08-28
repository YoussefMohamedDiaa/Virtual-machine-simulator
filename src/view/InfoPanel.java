package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.sidekicks.SideKick;

@SuppressWarnings("serial")
public class InfoPanel extends BackPanel {

	private Controller controller;
	
	private JPanel info;
	private JPanel buttons;
	
	private JLabel name;
	private JLabel nameDesc;
	private JLabel category;
	private JLabel categoryDesc;
	private JLabel power;
	private JLabel powerDesc;
	
	private JButton abilityButton;
	private JButton graveyardButton;

	public InfoPanel(Controller controller) {
		super(controller.getAssets().getInfoBackground());
		this.controller = controller;
		setLayout(new BorderLayout());
		setOpaque(false);
		
		info = new JPanel();
		info.setLayout(new GridLayout(1, 6));
		info.setOpaque(false);
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		
		abilityButton = new JButton();
		abilityButton.setIcon(new ImageIcon(controller.getAssets().getAbilityIcon()));
		abilityButton.addActionListener(controller);
		abilityButton.setBorderPainted(false);
		abilityButton.setContentAreaFilled(false);
		abilityButton.setOpaque(false);
		abilityButton.setFocusable(false);
		
		graveyardButton = new JButton();
		graveyardButton.setIcon(new ImageIcon(controller.getAssets().getGraveyardIcon()));
		graveyardButton.addActionListener(controller);
		graveyardButton.setBorderPainted(false);
		graveyardButton.setContentAreaFilled(false);
		graveyardButton.setOpaque(false);
		graveyardButton.setFocusable(false);
		
		buttons.add(abilityButton);
		buttons.add(graveyardButton);
		
		add(buttons, BorderLayout.EAST);
		
		name = new JLabel("");
		Font font = controller.getAssets().getGameFont(20f);
		name.setFont(font);
		nameDesc = new JLabel("");
		nameDesc.setFont(font);
		category = new JLabel("");
		category.setFont(font);
		categoryDesc = new JLabel("");
		categoryDesc.setFont(font);
		power = new JLabel("");
		power.setFont(font);
		powerDesc = new JLabel("");
		powerDesc.setFont(font);
		
		info.add(name);
		info.add(nameDesc);
		info.add(category);
		info.add(categoryDesc);
		info.add(power);
		info.add(powerDesc);
		
		add(info, BorderLayout.WEST);
	}

	public void updateInfoPanel() {
		Piece p = controller.getInfoPiece();
		name.setText("   Name: ");
		name.setForeground(Color.WHITE);
		nameDesc.setText(p.getName());
		nameDesc.setForeground(Color.WHITE);
		
		category.setText("Class: ");
		category.setForeground(Color.WHITE);
		if(p instanceof SideKick)
			categoryDesc.setText("SideKick");
		else
			categoryDesc.setText(p.getClass().getSimpleName());
		categoryDesc.setForeground(Color.WHITE);

		if (p instanceof ActivatablePowerHero) {
			power.setText("Power:");
			power.setForeground(Color.WHITE);
			if (((ActivatablePowerHero) p).isPowerUsed()) {
				powerDesc.setText("Power Used");
				powerDesc.setForeground(Color.GRAY.brighter());
				
			} else {
				powerDesc.setText("Power Not Used");
				powerDesc.setForeground(Color.GREEN.darker());
			}
		} else if (p instanceof Armored) {
			power.setText("Armor:");
			power.setForeground(Color.WHITE);
			if (((Armored) p).isArmorUp()) {
				powerDesc.setText("Armor Is Up");
				powerDesc.setForeground(Color.GREEN.darker());
			} else {
				powerDesc.setText("Armor Is Down");
				powerDesc.setForeground(Color.GRAY.brighter());
			}
		} else {
			power.setText("");
			powerDesc.setText("");
		}
		
	}

	public JButton getAbilityButton() {
		return abilityButton;
	}

	public JButton getGraveyardButton() {
		return graveyardButton;
	}

}
