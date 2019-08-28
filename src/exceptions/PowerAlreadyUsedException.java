package exceptions;

import model.pieces.Piece;

@SuppressWarnings("serial")
public class PowerAlreadyUsedException extends InvalidPowerUseException {

	public PowerAlreadyUsedException(Piece trigger) {
		super(trigger);
	}

	public PowerAlreadyUsedException(String s, Piece trigger) {
		super(s, trigger);
	}

}
