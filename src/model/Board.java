
package model;

import java.util.*;

import model.Index;

public class Board {
	/* metodo java per visualizzare la scacchiera */

	private Piece[][] board = new Piece[8][8];
	private ArrayList<Piece> pieces = new ArrayList<Piece>(32);

	public Board() {
		initBoard();
	}

	public String coordinatesToNotation(int x, int y) {
		int number = 0;
		char letter = 'z';
		String notation;

		switch (x) {
		case 0:
			number = 8;
			break;
		case 1:
			number = 7;
			break;
		case 2:
			number = 6;
			break;
		case 3:
			number = 5;
			break;
		case 4:
			number = 4;
			break;
		case 5:
			number = 3;
			break;
		case 6:
			number = 2;
			break;
		case 7:
			number = 1;
			break;
		default:
			break;
		}

		switch (y) {
		case 0:
			letter = 'a';
			break;
		case 1:
			letter = 'b';
			break;
		case 2:
			letter = 'c';
			break;
		case 3:
			letter = 'd';
			break;
		case 4:
			letter = 'e';
			break;
		case 5:
			letter = 'f';
			break;
		case 6:
			letter = 'g';
			break;
		case 7:
			letter = 'h';
			break;
		default:
			break;
		}

		notation = letter + Integer.toString(number);
		return notation;
	}

	public Index notationToIndex(String notation) {
		char letter;
		char number;
		int x = 0, y = 0;
		letter = notation.charAt(0);
		number = notation.charAt(1);

		switch (letter) {
		case 'a':
			y = 0;
			break;
		case 'b':
			y = 1;
			break;
		case 'c':
			y = 2;
			break;
		case 'd':
			y = 3;
			break;
		case 'e':
			y = 4;
			break;
		case 'f':
			y = 5;
			break;
		case 'g':
			y = 6;
			break;
		case 'h':
			y = 7;
			break;
		default:
			break;
		}

		switch (number) {
		case '1':
			x = 7;
			break;
		case '2':
			x = 6;
			break;
		case '3':
			x = 5;
			break;
		case '4':
			x = 4;
			break;
		case '5':
			x = 3;
			break;
		case '6':
			x = 2;
			break;
		case '7':
			x = 1;
			break;
		case '8':
			x = 0;
			break;
		default:
			break;
		}

		Index index = new Index(x, y);
		return index;
	}

	public Piece notationToPiece(String notation) {
		boolean pieceFound = false;

		Index srcCrd = notationToIndex(notation);
		ArrayList<Piece> pieces = getPieces();
		Piece piece = null;

		findPiece: for (Piece p : pieces) {
			piece = p;

			if (piece.getRow() == srcCrd.getX() && piece.getCol() == srcCrd.getY()) {
				pieceFound = true;
				break findPiece;
			}
		}
		if (pieceFound) {
			return piece;
		} else {
			return null;
		}
	}

	public void removePiece(int x, int y) {
		ArrayList<Piece> pieces = getPieces();
		for (Piece p : pieces) {
			if (p.getRow() == x && p.getCol() == y) {
				pieces.remove(p);
				setPieces(pieces);
				updateGameState();
				break;
			}
		}
	}

	/* rimuovi i pezzi della scacchiera */

	public void removePiece(String notation) {
		Piece piece = notationToPiece(notation);
		ArrayList<Piece> pieces = getPieces();
		for (Piece p : pieces) {
			if (p.getRow() == piece.getRow() && p.getCol() == piece.getCol()) {
				pieces.remove(piece);
				setPieces(pieces);
				updateGameState();
				break;
			}
		}
	}

	public void addPiece(Piece p) {
		ArrayList<Piece> pieces = getPieces();
		pieces.add(p);
		setPieces(pieces);
		updateGameState();
	}

	public void emptyBoard() {
		for (int i = 0; i < 8; i++) { // rows
			for (int j = 0; j < 8; j++) { // columns
				board[i][j] = null;
			}
		}
	}

	/* metodo per creare i pezzi */

	public void createPieces() {
		for (int i = 0; i < 8; i++) {
			pieces.add(new Piece(1, Piece.PAWN, 1, i));
			pieces.add(new Piece(0, Piece.PAWN, 6, i));
		}
		// torri
		pieces.add(new Piece(1, Piece.ROOK, 0, 0));
		pieces.add(new Piece(1, Piece.ROOK, 0, 7));
		pieces.add(new Piece(0, Piece.ROOK, 7, 0));
		pieces.add(new Piece(0, Piece.ROOK, 7, 7));
		// cavalli
		pieces.add(new Piece(1, Piece.KNIGHT, 0, 1));
		pieces.add(new Piece(1, Piece.KNIGHT, 0, 6));
		pieces.add(new Piece(0, Piece.KNIGHT, 7, 1));
		pieces.add(new Piece(0, Piece.KNIGHT, 7, 6));
		// alfieri
		pieces.add(new Piece(1, Piece.BISHOP, 0, 2));
		pieces.add(new Piece(1, Piece.BISHOP, 0, 5));
		pieces.add(new Piece(0, Piece.BISHOP, 7, 2));
		pieces.add(new Piece(0, Piece.BISHOP, 7, 5));
		// regine
		pieces.add(new Piece(1, Piece.QUEEN, 0, 3));
		pieces.add(new Piece(0, Piece.QUEEN, 7, 3));
		// re
		pieces.add(new Piece(1, Piece.KING, 0, 4));
		pieces.add(new Piece(0, Piece.KING, 7, 4));
	}

	private void populateBoard() {
		for (Piece p : pieces) {
			board[p.getRow()][p.getCol()] = p;
		}
	}

	public void initBoard() {

		createPieces();

	}

	public void updateGameState() {
		emptyBoard();
		populateBoard();
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public ArrayList<Piece> getPiecesFromOneSide(int color) {
		ArrayList<Piece> sidePieces = new ArrayList<Piece>();

		ArrayList<Piece> pieces = getPieces();
		for (Piece p : pieces) {
			if (p.getColor() == color) {
				sidePieces.add(p);
			}
		}
		return sidePieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

	public void promotePiece(int x, int y, int type) {
		int i = 0;
		ArrayList<Piece> pieces = getPieces();
		for (Piece p : pieces) {
			if (p.getRow() == x && p.getCol() == y) {
				p.setType(type);
				pieces.set(i, p);
				setPieces(pieces);
				updateGameState();
				break;
			}
			i++;
		}
	}

	public void promotePawnsToQueen(int color) {
		int enemyEndRow;
		if (color == 0) {
			enemyEndRow = 0;
		} else {
			enemyEndRow = 7;
		}

		ArrayList<Piece> pieces = getPieces();
		int i = 0;
		for (Piece p : pieces) {

			if (p.getRow() == enemyEndRow && p.getColor() == color && p.getType() == 6) {
				p.setType(2);
				pieces.set(i, p);
				setPieces(pieces);
				updateGameState();
				break;
			}
			i++;
		}
	}

	public Piece getPiece(int x, int y, int color) {

		ArrayList<Piece> pieces = getPiecesFromOneSide(color);
		for (Piece p : pieces) {

			if (p.getRow() == x && p.getCol() == y) {
				return p;
			}
		}
		return new Piece();
	}

}
