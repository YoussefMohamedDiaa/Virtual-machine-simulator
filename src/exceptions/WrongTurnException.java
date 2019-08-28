package exceptions;

import model.pieces.Piece;

@SuppressWarnings("serial")
public class WrongTurnException extends GameActionException {

	public WrongTurnException(Piece trigger) {
		super(trigger);
	}

	public WrongTurnException(String s, Piece trigger) {
		super(s, trigger);
	}

}
