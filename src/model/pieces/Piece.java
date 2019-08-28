package model.pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Move;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.sidekicks.SideKick;

public abstract class Piece implements Movable {

	private String name;
	private Player owner;
	private Game game;
	private int posI;
	private int posJ;
	private int step;
	private ArrayList<Direction> allowedDirections;

	public Piece(Player p, Game g, String name) {
		this.owner = p;
		this.game = g;
		this.name = name;
		this.step = 1;
		allowedDirections = new ArrayList<>(Arrays.asList(Direction.DOWN, Direction.DOWNLEFT, Direction.DOWNRIGHT, 
				Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.UPLEFT, Direction.UPRIGHT));
	}

	public void attack(Piece target) {
		Player attacked = target.getOwner();
		Player attacker = owner;
		boolean wasArmored = false;
		if (target instanceof SideKick) {
			attacker.setSideKilled(attacker.getSideKilled() + 1);
			if (attacker.getSideKilled() % 2 == 0)
				attacker.setPayloadPos(attacker.getPayloadPos() + 1);
			attacked.getDeadCharacters().add(target);
			game.getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
		} else if (target instanceof Armored && ((Armored) target).isArmorUp()) {
			((Armored) target).setArmorUp(false);
			wasArmored = true;
		} else {
			attacked.getDeadCharacters().add(target);
			attacker.setPayloadPos(attacker.getPayloadPos() + 1);
			game.getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
		}
		if(!wasArmored)
			game.checkWinner();
	}
	
	public void move(int step, Direction r, ArrayList<Direction> allowedMoves) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		if (this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException("That is not your turn", this);
		boolean allowed = false;
		for(Direction d : allowedMoves)
			if(r == d)
				allowed = true;
		if(!allowed)
			throw new UnallowedMovementException("This move is unallowed", this, r);
		int oldI = getPosI();
		int oldJ = getPosJ();
		Point moveLocation = getMoveLocation(oldI, oldJ, step, r, true);
		int newI = moveLocation.x;
		int newJ = moveLocation.y;
		doMove(oldI, oldJ, newI, newJ, r);
	}
	
	public Point getMoveLocation(int i, int j, int step, Direction r, boolean wrap) {
		switch (r) {
		case DOWN:
			i += step;
			break;
		case DOWNLEFT:
			i += step;
			j -= step;
			break;
		case DOWNRIGHT:
			i += step;
			j += step;
			break;
		case LEFT:
			j -= step;
			break;
		case RIGHT:
			j += step;
			break;
		case UP:
			i -= step;
			break;
		case UPLEFT:
			i -= step;
			j -= step;
			break;
		case UPRIGHT:
			i -= step;
			j += step;
			break;
		}
		int h = getGame().getBoardHeight();
		int w = getGame().getBoardWidth();
		if(i < 0 && wrap) i += h;
		if(j < 0 && wrap) j += w;
		if(i >= h && wrap) i -= h;
		if(j >= w && wrap) j -= w;
		return new Point(i, j);
	}

	public void doMove(int oldI, int oldJ, int i, int j, Direction r) throws OccupiedCellException {
		if (isEmptyCell(i, j)) {
			game.getCellAt(i, j).setPiece(this);
			game.getCellAt(oldI, oldJ).setPiece(null);
			this.setPosI(i);
			this.setPosJ(j);
		} else if (isFriendly(i, j)) {
			throw new OccupiedCellException("This an Occupied Cell by a friendly", this, r);
		} else {
			attack(game.getCellAt(i, j).getPiece());
			Piece attacker = game.getCellAt(oldI, oldJ).getPiece();
			if (game.getCellAt(i, j).getPiece() == null) {
				game.getCellAt(i, j).setPiece(attacker);
				game.getCellAt(oldI, oldJ).setPiece(null);
				attacker.setPosI(i);
				attacker.setPosJ(j);
			}
		}
		game.switchTurns();
	}
	
	public boolean isEmptyCell(int i, int j) {
		return game.getCellAt(i, j).getPiece() == null;
	}
	
	public boolean isFriendly(Piece p) {
		return owner == p.owner;
	}
	
	public boolean isFriendly(int i, int j) {
		return owner == game.getCellAt(i, j).getPiece().owner;
	}
	
	public ArrayList<Move> getAllowedMoves() {
		ArrayList<Move> allowedMoves = new ArrayList<>();
		for(Direction d : allowedDirections) {
			Point p = getMoveLocation(posI, posJ, step, d, true);
			if(isEmptyCell(p.x, p.y) || !isFriendly(p.x, p.y))
				allowedMoves.add(new Move(p, d));
		}
		return allowedMoves;
	}
	
	public boolean isAllowdMove(Point p) {
		ArrayList<Move> allowedMoves = getAllowedMoves();
		for (Move m : allowedMoves) {
			if (m.samePoint(p))
				return true;
		}
		return false;
	}

	public Direction mapToMoveDirection(Point p) {
		ArrayList<Move> allowedMoves = getAllowedMoves();
		for (Move m : allowedMoves) {
			if (m.samePoint(p))
				return m.getDirection();
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public int getPosI() {
		return posI;
	}

	public void setPosI(int i) {
		posI = i;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int j) {
		posJ = j;
	}

	public Game getGame() {
		return game;
	}

	public Player getOwner() {
		return owner;
	}
	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public ArrayList<Direction> getAllowedDirections() {
		return allowedDirections;
	}

	public void setAllowedDirections(ArrayList<Direction> allowedDirections) {
		this.allowedDirections = allowedDirections;
	}
}
