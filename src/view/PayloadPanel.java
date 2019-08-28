package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.Controller;

@SuppressWarnings("serial")
public class PayloadPanel extends BackPanel{
	Controller controller;
	
	private JLabel p1 ;
	private JLabel p2 ;
	private JLabel curName ;
	
	public PayloadPanel(Controller controller) {
		super(controller.getAssets().getPayloadBackground());
		this.controller = controller;
		setLayout(new BorderLayout());
		p1 = new JLabel();
		p2 = new JLabel();
		curName = new JLabel();
		curName.setFont(controller.getAssets().getGameFont(24f));
		curName.setForeground(Color.WHITE);
		p1.setIcon(new ImageIcon(controller.getAssets().getPayload("0p1")));
		p2.setIcon(new ImageIcon(controller.getAssets().getPayload("0p2")));
		controller.getGame();
		curName.setText(controller.getGame().getPlayer1().getName());
		curName.setHorizontalAlignment(JLabel.CENTER);
		this.add(p1,BorderLayout.WEST);
		this.add(p2,BorderLayout.EAST);
		this.add(curName, BorderLayout.CENTER);	
		
	}
	public void updatePayload() {
		String payloadP1 = "" + controller.getGame().getPlayer1().getPayloadPos() + "p1";
		String payloadP2 = "" + controller.getGame().getPlayer2().getPayloadPos() + "p2";
		p1.setIcon(new ImageIcon(controller.getAssets().getPayload(payloadP1)));
		p2.setIcon(new ImageIcon(controller.getAssets().getPayload(payloadP2)));
		curName.setText(controller.getGame().getCurrentPlayer().getName());
		
	}
}
