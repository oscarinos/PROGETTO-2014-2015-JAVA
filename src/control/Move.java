
package control;

import java.util.*;

import model.Board;

import model.Index;
import model.Piece;

public class Move {

	/*
	 * MOVE : classe che gestisce i movimenti di ogni singolo pezzo in base alle
	 * regole italiane degli scacchi
	 */

	private Board board;
	private HistoryOfMoves history;

	public Move(Board board) {
		this.board = board;
		history = new HistoryOfMoves();
	}

	private Board getBoard() {
		return board;
	}

	public HistoryOfMoves getHistoryOfMoves() {
		return history;
	}

	public void setHistoryOfMoves(HistoryOfMoves history) {
		this.history = history;
	}

	public boolean checkSqValidity(String sq) {
		char[] validColumns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
		char[] validRows = { '1', '2', '3', '4', '5', '6', '7', '8' };
		if (sq.length() == 2) {
			char letter = sq.charAt(0);
			char number = sq.charAt(1);
			for (char c : validColumns) {

				if (c == letter) {
					for (char r : validRows) {

						if (r == number) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/*
	 * metodo che torna vero se il pezzo si trova in scacchiera sapendo le
	 * coordinate altrimenti falso
	 */

	public boolean isPieceOnSquare(int x, int y) {
		Piece piece;
		for (Piece p : getBoard().getPieces()) {
			piece = p;

			if (piece.getRow() == x && piece.getCol() == y) {
				return true;
			}
		}
		return false;
	}

	/*
	 * metodo che torna vero se il pezzo si trova in scacchiera sapendo la
	 * notazione altrimenti falso
	 */

	public boolean isPieceOnSquare(String notation) {
		Index index = getBoard().notationToIndex(notation);
		Piece piece;
		for (Piece p : getBoard().getPieces()) {
			piece = p;
			if (piece.getRow() == index.getX() && piece.getCol() == index.getY()) {
				return true;
			}
		}
		return false;
	}

	public boolean isPieceOnSquare(int x, int y, int color) {
		for (Piece p : getBoard().getPieces()) {
			if (p.getColor() == color) {
				if (p.getRow() == x && p.getCol() == y) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * metodo che controlla se il re (nero i bianco) e' presente nella
	 * scacchiera
	 */

	public boolean isKingOnSquare(int x, int y, int color) {
		for (Piece p : getBoard().getPieces()) {
			if (p.getColor() == color) {
				if (p.getRow() == x && p.getCol() == y) {
					if (p.getType() == 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isPieceOnSquare(String notation, int color) {
		Index index = getBoard().notationToIndex(notation);
		for (Piece p : getBoard().getPieces()) {
			if (p.getColor() == color) {
				if (p.getRow() == index.getX() && p.getCol() == index.getY()) {
					return true;
				}
			}
		}
		return false;
	}

	private Boolean isOnBoard(int x, int y) {
		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
			return true;
		} else {
			return false;
		}
	}

	/* I seguenti metodo permettono di gestire le mosse di ogni singolo pezzo */

	private ArrayList<ArrayList<Integer>> possibleBishopMvmts(Piece piece, boolean kingCapture) {
		ArrayList<Integer> mvmtX, mvmtY;

		mvmtX = new ArrayList<Integer>();
		mvmtY = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> xAndY = new ArrayList<ArrayList<Integer>>();
		int x = piece.getRow();
		int y = piece.getCol();
		int color = piece.getColor();
		int opponentColor;
		if (color == 0) {
			opponentColor = 1;
		} else {
			opponentColor = 0;
		}

		int i = x, j = y;
		i--;
		j++;
		while (i >= 0 && j <= 7) {

			if (isPieceOnSquare(i, j, color)) {
				break;

			} else if (isPieceOnSquare(i, j, opponentColor)) {
				if (isKingOnSquare(i, j, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(i); mvmtY.add(j);
				} else {

					if (!isKingOnSquare(i, j, opponentColor)) {
						mvmtX.add(i);
						mvmtY.add(j);
					}
				}
				break;
			} else {
				mvmtX.add(i);
				mvmtY.add(j);
			}
			i--;
			j++;
		}

		i = x;
		j = y;
		i--;
		j--;
		while (i >= 0 && j >= 0) {

			if (isPieceOnSquare(i, j, color)) {
				break;

			} else if (isPieceOnSquare(i, j, opponentColor)) {
				if (isKingOnSquare(i, j, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(i); mvmtY.add(j);
					break;
				} else {

					if (!isKingOnSquare(i, j, opponentColor)) {
						mvmtX.add(i);
						mvmtY.add(j);
					}
				}
				break;
			} else {
				mvmtX.add(i);
				mvmtY.add(j);
			}
			i--;
			j--;
		}

		i = x;
		j = y;
		i++;
		j++;
		while (i <= 7 && j <= 7) {

			if (isPieceOnSquare(i, j, color)) {
				break;

			} else if (isPieceOnSquare(i, j, opponentColor)) {
				if (isKingOnSquare(i, j, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(i); mvmtY.add(j);
				} else {

					if (!isKingOnSquare(i, j, opponentColor)) {
						mvmtX.add(i);
						mvmtY.add(j);
					}
				}
				break;
			} else {
				mvmtX.add(i);
				mvmtY.add(j);
			}
			i++;
			j++;
		}

		i = x;
		j = y;
		i++;
		j--;
		while (i <= 7 && j >= 0) {

			if (isPieceOnSquare(i, j, color)) {
				break;

			} else if (isPieceOnSquare(i, j, opponentColor)) {
				if (isKingOnSquare(i, j, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n"
							+ "CTRL+N --> PER RICOMINCIARE UNA NUOVA PARTITA\n" + "Q --> PER USCIRE DAL GIOCO ");
					// mvmtX.add(i); mvmtY.add(j);
				} else {

					if (!isKingOnSquare(i, j, opponentColor)) {
						mvmtX.add(i);
						mvmtY.add(j);
					}
				}
				break;
			} else {
				mvmtX.add(i);
				mvmtY.add(j);
			}
			i++;
			j--;
		}
		xAndY.add(mvmtX);
		xAndY.add(mvmtY);
		return xAndY;
	}

	private ArrayList<ArrayList<Integer>> possibleRookMvmts(Piece piece, boolean kingCapture) {

		int x = piece.getRow();
		int y = piece.getCol();
		ArrayList<Integer> mvmtX, mvmtY;

		mvmtX = new ArrayList<Integer>();
		mvmtY = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> xAndY = new ArrayList<ArrayList<Integer>>();
		int opponentColor;
		if (piece.getColor() == 0) {
			opponentColor = 1;
		} else {
			opponentColor = 0;
		}
		int i, j;

		i = x;
		i--;
		while (i >= 0) {

			if (isPieceOnSquare(i, y, piece.getColor())) {
				break;

			} else if (isPieceOnSquare(i, y, opponentColor)) {
				if (isKingOnSquare(i, y, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT"); // mvmtX.add(i);
																												// mvmtY.add(y);
				} else {

					if (!isKingOnSquare(i, y, opponentColor)) {
						mvmtX.add(i);
						mvmtY.add(y);
					}
				}
				break;
			} else {
				mvmtX.add(i);
				mvmtY.add(y);
			}
			i--;
		}

		i = x;
		i++;
		while (i <= 7) {

			if (isPieceOnSquare(i, y, piece.getColor())) {
				break;

			} else if (isPieceOnSquare(i, y, opponentColor)) {
				if (isKingOnSquare(i, y, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT"); // mvmtX.add(i);
																												// mvmtY.add(y);
				} else {

					if (!isKingOnSquare(i, y, opponentColor)) {
						mvmtX.add(i);
						mvmtY.add(y);
					}
				}
				break;
			} else {
				mvmtX.add(i);
				mvmtY.add(y);
			}
			i++;
		}

		j = y;
		j++;
		while (j <= 7) {

			if (isPieceOnSquare(x, j, piece.getColor())) {
				break;

			} else if (isPieceOnSquare(x, j, opponentColor)) {
				if (isKingOnSquare(x, j, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT"); // mvmtX.add(x);
																												// mvmtY.add(j);
				} else {

					if (!isKingOnSquare(x, j, opponentColor)) {
						mvmtX.add(x);
						mvmtY.add(j);
					}
				}
				break;
			} else {
				mvmtX.add(x);
				mvmtY.add(j);
			}
			j++;
		}

		j = y;
		j--;
		while (j >= 0) {

			if (isPieceOnSquare(x, j, piece.getColor())) {
				break;

			} else if (isPieceOnSquare(x, j, opponentColor)) {
				if (isKingOnSquare(x, j, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT"); // mvmtX.add(i);
																												// mvmtY.add(j);
				} else {

					if (!isKingOnSquare(x, j, opponentColor)) {
						mvmtX.add(x);
						mvmtY.add(j);
					}
				}
				break;
			} else {
				mvmtX.add(x);
				mvmtY.add(j);
			}
			j--;
		}
		xAndY.add(mvmtX);
		xAndY.add(mvmtY);
		return xAndY;
	}

	private ArrayList<ArrayList<Integer>> possibleKnightMvmts(Piece piece, boolean kingCapture) {
		ArrayList<Integer> mvmtX, mvmtY;

		mvmtX = new ArrayList<Integer>();
		mvmtY = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> xAndY = new ArrayList<ArrayList<Integer>>();
		int x = piece.getRow();
		int y = piece.getCol();

		int opponentColor;
		if (piece.getColor() == 0) {
			opponentColor = 1;
		} else {
			opponentColor = 0;
		}

		if (isOnBoard(x + 2, y - 1)) {

			if (!isPieceOnSquare(x + 2, y - 1, piece.getColor())) {
				if (isKingOnSquare(x + 2, y - 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x+2); mvmtY.add(y-1);
				} else {

					if (!isKingOnSquare(x + 2, y - 1, opponentColor)) {
						mvmtX.add(x + 2);
						mvmtY.add(y - 1);
					}
				}
			}
		}

		if (isOnBoard(x + 1, y - 2)) {

			if (!isPieceOnSquare(x + 1, y - 2, piece.getColor())) {
				if (isKingOnSquare(x + 1, y - 2, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x+1); mvmtY.add(y-2);
				} else {

					if (!isKingOnSquare(x + 1, y - 2, opponentColor)) {
						mvmtX.add(x + 1);
						mvmtY.add(y - 2);
					}
				}
			}
		}

		if (isOnBoard(x - 2, y - 1)) {

			if (!isPieceOnSquare(x - 2, y - 1, piece.getColor())) {
				if (isKingOnSquare(x - 2, y - 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x-2); mvmtY.add(y-1);
				} else {

					if (!isKingOnSquare(x - 2, y - 1, opponentColor)) {
						mvmtX.add(x - 2);
						mvmtY.add(y - 1);
					}
				}
			}
		}

		if (isOnBoard(x - 1, y - 2)) {

			if (!isPieceOnSquare(x - 1, y - 2, piece.getColor())) {
				if (isKingOnSquare(x - 1, y - 2, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x-1); mvmtY.add(y-2);
				} else {

					if (!isKingOnSquare(x - 1, y - 2, opponentColor)) {
						mvmtX.add(x - 1);
						mvmtY.add(y - 2);
					}
				}
			}
		}

		if (isOnBoard(x + 2, y + 1)) {

			if (!isPieceOnSquare(x + 2, y + 1, piece.getColor())) {
				if (isKingOnSquare(x + 2, y + 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x+2); mvmtY.add(y+1);
				} else {

					if (!isKingOnSquare(x + 2, y + 1, opponentColor)) {
						mvmtX.add(x + 2);
						mvmtY.add(y + 1);
					}
				}
			}
		}

		if (isOnBoard(x + 1, y + 2)) {

			if (!isPieceOnSquare(x + 1, y + 2, piece.getColor())) {
				if (isKingOnSquare(x + 1, y + 2, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x+1); mvmtY.add(y+2);
				} else {

					if (!isKingOnSquare(x + 1, y + 2, opponentColor)) {
						mvmtX.add(x + 1);
						mvmtY.add(y + 2);
					}
				}
			}
		}

		if (isOnBoard(x - 2, y + 1)) {

			if (!isPieceOnSquare(x - 2, y + 1, piece.getColor())) {
				if (isKingOnSquare(x - 2, y + 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x-2); mvmtY.add(y+1);
				} else {

					if (!isKingOnSquare(x - 2, y + 1, opponentColor)) {
						mvmtX.add(x - 2);
						mvmtY.add(y + 1);
					}
				}
			}
		}

		if (isOnBoard(x - 1, y + 2)) {

			if (!isPieceOnSquare(x - 1, y + 2, piece.getColor())) {
				if (isKingOnSquare(x - 1, y + 2, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT"); // mvmtX.add(x-1);
																												// mvmtY.add(y+2);
				} else {

					if (!isKingOnSquare(x - 1, y + 2, opponentColor)) {
						mvmtX.add(x - 1);
						mvmtY.add(y + 2);
					}
				}
			}
		}
		xAndY.add(mvmtX);
		xAndY.add(mvmtY);
		return xAndY;
	}

	private ArrayList<ArrayList<Integer>> possiblePawnMvmts(Piece piece, boolean kingCapture) {
		ArrayList<Integer> mvmtX, mvmtY;

		mvmtX = new ArrayList<Integer>();
		mvmtY = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> xAndY = new ArrayList<ArrayList<Integer>>();
		int x = piece.getRow();
		int y = piece.getCol();

		int opponentColor;
		if (piece.getColor() == 0) {
			opponentColor = 1;
		} else {
			opponentColor = 0;
		}
		if (piece.getType() == 6 && piece.getColor() == 0) {
			if (x == 6) {
				if (!isPieceOnSquare(x - 1, y)) {
					mvmtX.add(x - 1);
					mvmtY.add(y);

				}
			} else {
				if (x - 1 >= 0 && !isPieceOnSquare(x - 1, y)) {
					mvmtX.add(x - 1);
					mvmtY.add(y);
				}
			}

			if (isPieceOnSquare(x - 1, y + 1, opponentColor)) {
				if (isKingOnSquare(x - 1, y + 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x-1); mvmtY.add(y+1);
				} else {

					if (!isKingOnSquare(x - 1, y + 1, opponentColor)) {
						mvmtX.add(x - 1);
						mvmtY.add(y + 1);
					}
				}
			}

			if (isPieceOnSquare(x - 1, y - 1, opponentColor)) {
				if (isKingOnSquare(x - 1, y - 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x-1); mvmtY.add(y-1);
				} else {

					if (!isKingOnSquare(x - 1, y - 1, opponentColor)) {
						mvmtX.add(x - 1);
						mvmtY.add(y - 1);
					}
				}
			}
		} else if (piece.getType() == 6 && piece.getColor() == 1) {
			if (x == 1) {
				if (!isPieceOnSquare(x + 1, y)) {
					mvmtX.add(x + 1);
					mvmtY.add(y);
				}
			} else {
				if (x + 1 < 8 && !isPieceOnSquare(x + 1, y)) {
					mvmtX.add(x + 1);
					mvmtY.add(y);
				}
			}

			if (isPieceOnSquare(x + 1, y + 1, opponentColor)) {
				if (isKingOnSquare(x + 1, y + 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x+1); mvmtY.add(y+1);
				} else {

					if (!isKingOnSquare(x + 1, y + 1, opponentColor)) {
						mvmtX.add(x + 1);
						mvmtY.add(y + 1);
					}
				}
			}

			if (isPieceOnSquare(x + 1, y - 1, opponentColor)) {
				if (isKingOnSquare(x + 1, y - 1, opponentColor)) {
					System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
					// mvmtX.add(x+1); mvmtY.add(y-1);
				} else {

					if (!isKingOnSquare(x + 1, y - 1, opponentColor)) {
						mvmtX.add(x + 1);
						mvmtY.add(y - 1);
					}
				}
			}
		}
		xAndY.add(mvmtX);
		xAndY.add(mvmtY);
		return xAndY;
	}

	private ArrayList<ArrayList<Integer>> possibleKingMvmts(Piece piece, boolean kingCapture) {
		ArrayList<Integer> mvmtX, mvmtY;

		mvmtX = new ArrayList<Integer>();
		mvmtY = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> xAndY = new ArrayList<ArrayList<Integer>>();
		int x = piece.getRow();
		int y = piece.getCol();
		int color = piece.getColor();
		int opponentColor;
		if (color == 0) {
			opponentColor = 1;
		} else {
			opponentColor = 0;
		}

		if (isOnBoard(x, y + 1) && !isPieceOnSquare(x, y + 1, color)) {
			if (isKingOnSquare(x, y + 1, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
				// mvmtX.add(x); mvmtY.add(y+1); //(x, y+1)
			} else {

				if (!isKingOnSquare(x, y + 1, opponentColor)) {
					mvmtX.add(x);
					mvmtY.add(y + 1); // (x, y+1)
				}
			}
		}
		if (isOnBoard(x, y - 1) && !isPieceOnSquare(x, y - 1, color)) {
			if (isKingOnSquare(x, y - 1, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
				// mvmtX.add(x); mvmtY.add(y-1);
			} else {

				if (!isKingOnSquare(x, y - 1, opponentColor)) {
					mvmtX.add(x);
					mvmtY.add(y - 1);
				}
			}
		}

		if (isOnBoard(x - 1, y) && !isPieceOnSquare(x - 1, y, color)) {
			if (isKingOnSquare(x - 1, y, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
				// mvmtX.add(x-1); mvmtY.add(y);
			} else {

				if (!isKingOnSquare(x - 1, y, opponentColor)) {
					mvmtX.add(x - 1);
					mvmtY.add(y); // (x-1, y)
				}
			}
		}
		if (isOnBoard(x + 1, y) && !isPieceOnSquare(x + 1, y, color)) {
			if (isKingOnSquare(x + 1, y, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
				// mvmtX.add(x+1); mvmtY.add(y);
			} else {

				if (!isKingOnSquare(x + 1, y, opponentColor)) {
					mvmtX.add(x + 1);
					mvmtY.add(y);
				}
			}
		}

		if (isOnBoard(x - 1, y + 1) && !isPieceOnSquare(x - 1, y + 1, color)) {
			if (isKingOnSquare(x - 1, y + 1, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT"); // mvmtX.add(x-1);
																											// mvmtY.add(y+1);
			} else {

				if (!isKingOnSquare(x - 1, y + 1, opponentColor)) {
					mvmtX.add(x - 1);
					mvmtY.add(y + 1);
				}
			}
		}

		if (isOnBoard(x - 1, y - 1) && !isPieceOnSquare(x - 1, y - 1, color)) {
			if (isKingOnSquare(x - 1, y - 1, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
				// mvmtX.add(x-1); mvmtY.add(y-1);
			} else {

				if (!isKingOnSquare(x - 1, y - 1, opponentColor)) {
					mvmtX.add(x - 1);
					mvmtY.add(y - 1);
				}
			}
		}

		if (isOnBoard(x + 1, y + 1) && !isPieceOnSquare(x + 1, y + 1, color)) {
			if (isKingOnSquare(x + 1, y + 1, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
				// mvmtX.add(x+1); mvmtY.add(y+1);
			} else {

				if (!isKingOnSquare(x + 1, y + 1, opponentColor)) {
					mvmtX.add(x + 1);
					mvmtY.add(y + 1);
				}
			}
		}

		if (isOnBoard(x + 1, y - 1) && !isPieceOnSquare(x + 1, y - 1, color)) {
			if (isKingOnSquare(x + 1, y - 1, opponentColor)) {
				System.out.println("VITTORIA - PARTITA CONCLUSA\n" + "N --> NEW GAME\n" + "Q --> QUIT");
				// mvmtX.add(x+1); mvmtY.add(y-1);
			} else {

				if (!isKingOnSquare(x + 1, y - 1, opponentColor)) {
					mvmtX.add(x + 1);
					mvmtY.add(y - 1);
				}
			}
		}
		xAndY.add(mvmtX);
		xAndY.add(mvmtY);
		return xAndY;
	}

	private ArrayList<ArrayList<Integer>> possibleQueenMvmts(Piece piece, boolean kingCapture) {
		ArrayList<Integer> mvmtX, mvmtY;
		mvmtX = new ArrayList<Integer>();
		mvmtY = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> bishopXAndY, rookXAndY, xAndY;

		xAndY = new ArrayList<ArrayList<Integer>>();

		bishopXAndY = possibleBishopMvmts(piece, kingCapture);
		mvmtX.addAll(bishopXAndY.get(0));
		mvmtY.addAll(bishopXAndY.get(1));

		rookXAndY = possibleRookMvmts(piece, kingCapture);
		mvmtX.addAll(rookXAndY.get(0));
		mvmtY.addAll(rookXAndY.get(1));
		xAndY.add(mvmtX);
		xAndY.add(mvmtY);
		return xAndY;
	}

	/* ARRAY contente tutte le mosse possibili di ogni pezzo */

	public ArrayList<ArrayList<Integer>> possiblePieceMoves(Piece piece, boolean kingCapture) {
		int type;
		ArrayList<Integer> mvmtX, mvmtY;
		ArrayList<ArrayList<Integer>> kingXAndY, queenXAndY, bishopXAndY, rookXAndY, knightXAndY, pawnXAndY;

		ArrayList<ArrayList<Integer>> mvmtXAndY = new ArrayList<ArrayList<Integer>>();

		type = piece.getType();
		mvmtX = new ArrayList<Integer>();
		mvmtY = new ArrayList<Integer>();
		switch (type) {
		case 1:
			kingXAndY = possibleKingMvmts(piece, kingCapture);
			mvmtX = kingXAndY.get(0);
			mvmtY = kingXAndY.get(1);
			break;
		case 2:
			queenXAndY = possibleQueenMvmts(piece, kingCapture);
			mvmtX = queenXAndY.get(0);
			mvmtY = queenXAndY.get(1);
			break;
		case 3:
			rookXAndY = possibleRookMvmts(piece, kingCapture);
			mvmtX = rookXAndY.get(0);
			mvmtY = rookXAndY.get(1);
			break;
		case 4:
			knightXAndY = possibleKnightMvmts(piece, kingCapture);
			mvmtX = knightXAndY.get(0);
			mvmtY = knightXAndY.get(1);
			break;
		case 5:
			bishopXAndY = possibleBishopMvmts(piece, kingCapture);
			mvmtX = bishopXAndY.get(0);
			mvmtY = bishopXAndY.get(1);
			break;
		case 6:
			pawnXAndY = possiblePawnMvmts(piece, kingCapture);
			mvmtX = pawnXAndY.get(0);
			mvmtY = pawnXAndY.get(1);
			break;
		default:
			break;
		}
		mvmtXAndY.add(mvmtX);
		mvmtXAndY.add(mvmtY);
		return mvmtXAndY;
	}

}
