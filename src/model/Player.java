
package model;

import model.Piece;

public class Player {

	/* classe che implementa un giocatore */
	private int side; // white 0, black 1

	public Player() {

	}

	public Player(int side) {
		setSide(side);
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public int getEnemySide() {
		if (getSide() == 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean isOwnPiece(Piece piece) {
		if (piece.getColor() == getSide()) {
			return true;
		} else {
			return false;
		}
	}

}
