package model.pieces.sidekicks;

import model.game.Cell;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void attack(Piece target) {
		super.attack(target);
		if (getGame().getCellAt(target.getPosI(), target.getPosJ()).getPiece() != null)
			return;
		Cell attackerCell = getGame().getCellAt(getPosI(), getPosJ());
		if (target instanceof Armored)
			attackerCell.setPiece(new Armored(getOwner(), getGame(), getName()));
		else if (target instanceof Medic)
			attackerCell.setPiece(new Medic(getOwner(), getGame(), getName()));
		else if (target instanceof Ranged)
			attackerCell.setPiece(new Ranged(getOwner(), getGame(), getName()));
		else if (target instanceof Super)
			attackerCell.setPiece(new Super(getOwner(), getGame(), getName()));
		else if (target instanceof Speedster)
			attackerCell.setPiece(new Speedster(getOwner(), getGame(), getName()));
		else if (target instanceof Tech)
			attackerCell.setPiece(new Tech(getOwner(), getGame(), getName()));
		attackerCell.getPiece().setPosI(getPosI());
		attackerCell.getPiece().setPosJ(getPosJ());
	}

	@Override
	public String toString() {
		return "K";
	}
}
