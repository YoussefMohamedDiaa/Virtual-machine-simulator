package view;

import javax.swing.JButton;

import controller.Controller;
import model.pieces.Piece;

@SuppressWarnings("serial")
public class BoardButton extends JButton{
	
	private Controller controller;
	private int i;
	private int j;
	
	public BoardButton(Controller controller, int i, int j) {
		this.controller = controller;
		this.i = i;
		this.j = j;
	}
	
	public Piece getPiece() {
		return controller.getGame().getCellAt(j, i).getPiece();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
}
