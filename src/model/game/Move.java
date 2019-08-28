package model.game;

import java.awt.Point;

public class Move {
	
	private Point point;
	private Direction direction;
	
	public Move(Point point, Direction direction) {
		this.point = point;
		this.direction = direction;
	}
	
	public boolean samePoint(Point p) {
		return this.point.x == p.getX() && this.point.y == p.getY();
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
