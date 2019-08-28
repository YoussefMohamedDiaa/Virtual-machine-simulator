package model.pieces.heroes;

import java.awt.Point;
import java.util.ArrayList;

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

public class Ranged extends ActivatablePowerHero {

	public Ranged(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException {
		move(1, r, getAllowedDirections());
	}

	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerDirectionException, InvalidPowerTargetException {
		super.usePower(d, target, newPos);
		int i = this.getPosI();
		int j = this.getPosJ();
		Piece p = null;
		if (d == Direction.DOWN) {
			for (int k = i+1; k < 7; k++) {
				p = getGame().getCellAt(k, j).getPiece();
				if (p != null)
					break;
			}
		} else if (d == Direction.UP) {
			for (int k = i-1; k > -1; k--) {
				p = getGame().getCellAt(k, j).getPiece();
				if (p != null)
					break;
			}
		} else if (d == Direction.LEFT) {
			for (int k = j-1; k > -1; k--) {
				p = getGame().getCellAt(i, k).getPiece();
				if (p != null)
					break;
			}
		} else if (d == Direction.RIGHT) {
			for (int k = j+1; k < 6; k++) {
				p = getGame().getCellAt(i, k).getPiece();
				if (p != null)
					break;
			}
		} else {
			throw new InvalidPowerDirectionException("You can't attack diagnoally", this, d);
		}
		if (p == null) {
			throw new InvalidPowerDirectionException("You can't attack Empty cells", this, d);
		} else {
			if (p.getOwner() == this.getOwner()) {
				throw new InvalidPowerDirectionException("You can't attack a friend", this, d);
			} else {
				attack(p);
				setPowerUsed(true);
				getGame().switchTurns();
			}
		}
	}
	
	public ArrayList<Move> getAllowedAbilityMoves() {
		ArrayList<Move> allowedAbilityMoves = new ArrayList<>();
		int i = this.getPosI();
		int j = this.getPosJ();
		Piece p;
		for (int k = i+1; k < 7; k++) {
			p = getGame().getCellAt(k, j).getPiece();
			if (p != null && !isFriendly(p)) {
				allowedAbilityMoves.add(new Move(new Point(p.getPosI(), p.getPosJ()), Direction.DOWN));
				break;
			}
		}
		for (int k = i-1; k > -1; k--) {
			p = getGame().getCellAt(k, j).getPiece();
			if (p != null && !isFriendly(p)) {
				allowedAbilityMoves.add(new Move(new Point(p.getPosI(), p.getPosJ()), Direction.UP));
				break;
			}
		}
		for (int k = j-1; k > -1; k--) {
			p = getGame().getCellAt(i, k).getPiece();
			if (p != null && !isFriendly(p)) {
				allowedAbilityMoves.add(new Move(new Point(p.getPosI(), p.getPosJ()), Direction.LEFT));
				break;
			}
		}
		for (int k = j+1; k < 6; k++) {
			p = getGame().getCellAt(i, k).getPiece();
			if (p != null && !isFriendly(p)) {
				allowedAbilityMoves.add(new Move(new Point(p.getPosI(), p.getPosJ()), Direction.RIGHT));
				break;
			}
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

	public String toString() {
		return "R";
	}
}
