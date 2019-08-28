package exceptions;

import model.pieces.Piece;

@SuppressWarnings("serial")
public abstract class GameActionException extends Exception {

	private Piece trigger;

	public GameActionException(Piece trigger) {
		super();
		this.trigger = trigger;
	}

	public GameActionException(String s, Piece trigger) {
		super(s);
		this.trigger = trigger;
	}

	public Piece getTrigger() {
		return trigger;
	}

}
