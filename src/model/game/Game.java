package model.game;

import java.util.ArrayList;

import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game {

	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		assemblePieces();
	}
	
	public void assemblePieces() {
		ArrayList<Hero> p1 = new ArrayList<>(6);
		ArrayList<Hero> p2 = new ArrayList<>(6);
		p1.add(new Armored(player1, this, "A1")); p2.add(new Armored(player2, this, "A2"));
		p1.add(new Medic(player1, this, "M1")); p2.add(new Medic(player2, this, "M2"));
		p1.add(new Ranged(player1, this, "R1")); p2.add(new Ranged(player2, this, "R2"));
		p1.add(new Super(player1, this, "P1")); p2.add(new Super(player2, this, "P2"));
		p1.add(new Speedster(player1, this, "S1")); p2.add(new Speedster(player2, this, "S2"));
		p1.add(new Tech(player1, this, "T1")); p2.add(new Tech(player2, this, "T2"));
	    for(int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = new Cell();
		for (int j = 0; j < boardWidth; j++) {
			int idx = (int)(Math.random()*p2.size());
			board[1][j].setPiece(p2.get(idx));
			Piece curr = board[1][j].getPiece();
			curr.setPosI(1);
			curr.setPosJ(j);
			p2.remove(idx);
		}
		for (int j = 0; j < boardWidth; j++) {
			int idx = (int)(Math.random()*p1.size());
			board[5][j].setPiece(p1.get(idx));
			Piece curr = board[5][j].getPiece();
			curr.setPosI(5);
			curr.setPosJ(j);
			p1.remove(idx);
		}
		for (int j = 0; j < boardWidth; j++) {
			board[4][j].setPiece(new SideKickP1(this, "K1"));
			Piece curr = board[4][j].getPiece();
			curr.setPosI(4);
			curr.setPosJ(j);
			board[2][j].setPiece(new SideKickP2(this, "K2"));
			curr = board[2][j].getPiece();
			curr.setPosI(2);
			curr.setPosJ(j);
		}
	}
	
	public Cell getCellAt(int i, int j) {
		return board[i][j];
	}
	
	public void switchTurns() {
		if(currentPlayer == player1)
			currentPlayer = player2;
		else 
			currentPlayer = player1;
	}
	
	public boolean checkWinner() {
		return currentPlayer.getPayloadPos() >= 6;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
    
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public int getPayloadPosTarget() {
		return payloadPosTarget;
	}

	public int getBoardWidth() {
		return boardWidth;
	}
	
	public int getBoardHeight() {
		return boardHeight;
	}
	
	@Override
	public String toString() {
		String s = "";
		System.out.println("      " + getPlayer2().getName());
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null)
					System.out.print("n ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		System.out.println("      " + getPlayer1().getName());
		return s;
	}
}
