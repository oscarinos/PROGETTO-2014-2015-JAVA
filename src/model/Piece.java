
package model;

public class Piece {

	public static final int KING = 1;
	public static final int QUEEN = 2;
	public static final int ROOK = 3;
	public static final int KNIGHT = 4;
	public static final int BISHOP = 5;
	public static final int PAWN = 6;

	public static final int KING_VALUE = 1000000;
	public static final int QUEEN_VALUE = 9;
	public static final int ROOK_VALUE = 5;
	public static final int KNIGHT_VALUE = 3;
	public static final int BISHOP_VALUE = 3;
	public static final int PAWN_VALUE = 1;

	private int color; // white 0, black 1
	private int type;
	private int row, col;

	public Piece() {
	}

	public Piece(int color, int type, int row, int col) {
		this.color = color;
		this.type = type;
		this.row = row;
		this.col = col;
	}

	public String getAcronym() {
		String acro = "";
		if (getColor() == 0 && getType() != 0) {
			acro = "W"; // white
		}
		if (getColor() == 1) {
			acro = "B"; // black
		}
		switch (getType()) {
		case 1:
			acro += "K";
			break;
		case 2:
			acro += "Q";
			break;
		case 3:
			acro += "R";
			break;
		case 4:
			acro += "N";
			break;
		case 5:
			acro += "B";
			break;
		case 6:
			acro += "P";
			break;
		default:
			break;
		}
		return acro;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		int type = this.getType();
		int value = 0;
		switch (type) {
		case 1:
			value = KING_VALUE;
			break;
		case 2:
			value = QUEEN_VALUE;
			break;
		case 3:
			value = ROOK_VALUE;
			break;
		case 4:
			value = KNIGHT_VALUE;
			break;
		case 5:
			value = BISHOP_VALUE;
			break;
		case 6:
			value = PAWN_VALUE;
			break;
		default:
			break;
		}
		return value;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
