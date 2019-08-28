package model.pieces;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;

public interface Movable {
	
	void move (Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException;
	
}
