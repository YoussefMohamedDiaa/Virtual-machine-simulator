package model.game;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;

public class Main {
	public static void main(String[] args) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		Game game = new Game(new Player("P1"), new Player("P2"));
		System.out.println(game.toString());
		game.getCellAt(4, 1).getPiece().move(Direction.UP);
		game.getCellAt(2, 4).getPiece().move(Direction.DOWN);
		System.out.println(game.toString());
		game.getCellAt(3, 1).getPiece().move(Direction.UP);
		game.getCellAt(3, 4).getPiece().move(Direction.DOWN);
		System.out.println(game.toString());
		game.getCellAt(2, 1).getPiece().move(Direction.UP);
		game.getCellAt(4, 4).getPiece().move(Direction.DOWN);
		System.out.println(game.toString());
		
	}
}
