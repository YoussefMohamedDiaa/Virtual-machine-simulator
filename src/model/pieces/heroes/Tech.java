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
import model.game.Player;
import model.pieces.Piece;

public class Tech extends ActivatablePowerHero {

	public Tech(Player player, Game game, String name) {
		super(player, game, name);
		setAllowedDirections(new ArrayList<>(Arrays.asList(Direction.DOWNLEFT, Direction.DOWNRIGHT, Direction.UPLEFT, Direction.UPRIGHT )));
	}

	@Override
	public void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException {
		move(1, r, getAllowedDirections());
	}

	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerTargetException, InvalidPowerDirectionException {
		super.usePower(d, target, newPos);
		if (newPos != null) {
			if (!isFriendly(target))
				throw new InvalidPowerTargetException("You can't teleport an enemy", this, target);
			if (isEmptyCell(newPos.x, newPos.y)) {
				getGame().getCellAt(newPos.x, newPos.y).setPiece(target);
				int oldI = target.getPosI();
				int oldJ = target.getPosJ();
				target.setPosI(newPos.x);
				target.setPosJ(newPos.y);
				getGame().getCellAt(oldI, oldJ).setPiece(null);
			} else {
				throw new InvalidPowerTargetException("This cell is Occuiped you can't teleport", this, target);
			}
		} else if (!isFriendly(target)) {
			if (target instanceof ActivatablePowerHero) {
				if (((ActivatablePowerHero) target).isPowerUsed() == false)
					((ActivatablePowerHero) target).setPowerUsed(true);
				else
					throw new InvalidPowerTargetException("Doesn't have power", this, target);
			}
			if (target instanceof Armored) {
				if (((Armored) target).isArmorUp() == true)
					((Armored) target).setArmorUp(false);
				else
					throw new InvalidPowerTargetException("Doesn't have Armor", this, target);
			}
		} else {
			if (target instanceof ActivatablePowerHero) {
				if (((ActivatablePowerHero) target).isPowerUsed() == true)
					((ActivatablePowerHero) target).setPowerUsed(false);
				else
					throw new InvalidPowerTargetException("Already has power", this, target);
			}
			if (target instanceof Armored) {
				if (((Armored) target).isArmorUp() == false)
					((Armored) target).setArmorUp(true);
				else
					throw new InvalidPowerTargetException("Already has Armor", this, target);
			}
		}
		setPowerUsed(true);
	}

	@Override
	public String toString() {
		return "T";
	}
}
