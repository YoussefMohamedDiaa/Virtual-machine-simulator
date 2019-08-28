package exceptions;

import model.pieces.Piece;

@SuppressWarnings("serial")
public abstract class InvalidPowerUseException extends GameActionException {

	public InvalidPowerUseException(Piece trigger) {
		super(trigger);
	}

	public InvalidPowerUseException(String s, Piece trigger) {
		super(s, trigger);
	}

}
