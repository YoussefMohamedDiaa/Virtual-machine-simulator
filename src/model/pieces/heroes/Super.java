package model.pieces.heroes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Move;
import model.game.Player;
import model.pieces.Piece;

public class Super extends ActivatablePowerHero {

	public Super(Player player, Game game, String name) {
		super(player, game, name);
		setAllowedDirections(new ArrayList<>(Arrays.asList(Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP)));
	}

	@Override
	public void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException {
		move(1, r, getAllowedDirections());
	}
	
	public boolean inBounds(int i, int j) {
		if (i < 0 || i > 6 || j < 0 || j > 5)
			return false;
		return true;
	}
	
	public boolean isValidPower(int i, int j) {
		if (i < 0 || i > 6 || j < 0 || j > 5)
			return false;
		Piece p = getGame().getCellAt(i, j).getPiece();
		if (p == null || p.getOwner() == this.getOwner())
			return false;
		return true;
	}

	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerDirectionException, InvalidPowerTargetException {
		super.usePower(d, target, newPos);
		Direction[] allowedPowerMoves = { Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP };
		boolean allowed = false;
		for(Direction r : allowedPowerMoves)
			if(d == r)
				allowed = true;
		if(!allowed)
			throw new InvalidPowerDirectionException("You can't apply the ability diagonaly", this, d);
		int i = this.getPosI();
		int j = this.getPosJ();
		Point p1 = getMoveLocation(i, j, 1, d, false);
		Point p2 = getMoveLocation(i, j, 2, d, false);
		if (isValidPower(p1.x, p1.y))
			attack(getGame().getCellAt(p1.x, p1.y).getPiece());
		if (isValidPower(p2.x, p2.y))
			attack(getGame().getCellAt(p2.x, p2.y).getPiece());
		setPowerUsed(true);
		getGame().switchTurns();
	}
	
	public ArrayList<Move> getAllowedAbilityMoves() {
		ArrayList<Move> allowedAbilityMoves = new ArrayList<>();
		for(Direction d : getAllowedDirections()) {
			Point p1 = getMoveLocation(getPosI(), getPosJ(), 1, d, false);
			Point p2 = getMoveLocation(getPosI(), getPosJ(), 2, d, false);
			if(inBounds(p1.x, p1.y))
				allowedAbilityMoves.add(new Move(p1, d));
			if(inBounds(p2.x, p2.y))
				allowedAbilityMoves.add(new Move(p2, d));
		}
		return allowedAbilityMoves;
	}
	
	public boolean isAllowdAbility(Point p) {
		ArrayList<Move> allowedAbilityMoves = getAllowedAbilityMoves();
		for (Move m : allowedAbilityMoves) {
			if (m.samePoint(p))
				return true;
		}
		return false;
	}
	
	public Direction mapToAbilityDirection(Point p) {
		ArrayList<Move> allowedAbilityMoves = getAllowedAbilityMoves();
		for (Move m : allowedAbilityMoves) {
			if (m.samePoint(p))
				return m.getDirection();
		}
		return null;
	}

	@Override
	public String toString() {
		return "P";
	}
}
