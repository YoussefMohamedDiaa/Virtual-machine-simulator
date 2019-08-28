package exceptions;

import model.game.Direction;
import model.pieces.Piece;

@SuppressWarnings("serial")
public class OccupiedCellException extends InvalidMovementException {

	public OccupiedCellException(Piece trigger, Direction direction) {
		super(trigger, direction);
	}

	public OccupiedCellException(String s, Piece trigger, Direction direction) {
		super(s, trigger, direction);
	}

}
