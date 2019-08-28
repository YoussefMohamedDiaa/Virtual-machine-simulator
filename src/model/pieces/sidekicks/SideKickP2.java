package model.pieces.sidekicks;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP2 extends SideKick {

	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name);
		setAllowedDirections(new ArrayList<>(Arrays.asList(Direction.DOWN, Direction.DOWNLEFT, Direction.DOWNRIGHT, Direction.LEFT,
				Direction.RIGHT)));
	}

	@Override
	public void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException {
		move(1, r, getAllowedDirections());
	}
}
