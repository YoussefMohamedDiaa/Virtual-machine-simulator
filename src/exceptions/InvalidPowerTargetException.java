package exceptions;

import model.pieces.Piece;

@SuppressWarnings("serial")
public class InvalidPowerTargetException extends InvalidPowerUseException {

	private Piece target;

	public InvalidPowerTargetException(Piece trigger, Piece target) {
		super(trigger);
		this.target = target;
	}

	public InvalidPowerTargetException(String s, Piece trigger, Piece target) {
		super(s, trigger);
		this.target = target;
	}

	public Piece getTarget() {
		return target;
	}

}
