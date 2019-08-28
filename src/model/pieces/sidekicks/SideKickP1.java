package model.pieces.sidekicks;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP1 extends SideKick {

	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name);
		setAllowedDirections(new ArrayList<>(Arrays.asList(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.UPLEFT,
				Direction.UPRIGHT)));
	}

	@Override
	public void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException {
		move(1, r, getAllowedDirections());
	}
}
