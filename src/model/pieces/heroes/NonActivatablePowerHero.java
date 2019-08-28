package model.pieces.heroes;

import model.game.Game;
import model.game.Player;

public abstract class NonActivatablePowerHero extends Hero {

	public NonActivatablePowerHero(Player player, Game game, String name) {
		super(player, game, name);
	}

}
