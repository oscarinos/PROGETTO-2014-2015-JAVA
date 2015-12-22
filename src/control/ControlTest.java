package control;

import model.Board;
import view.Gui;

import model.Piece;
import model.Player;

import static org.junit.Assert.*;

import org.junit.Test;

public class ControlTest {

	/*
	 * CONTROL TEST : classe junit per verificare se i pezzi si muovono
	 * correttamente
	 */

	Board s = new Board();
	Move m = new Move(s);
	Player p0 = new Player(0);
	Player p1 = new Player(1);
	Gui mv = new Gui();

	@Test
	public void test() {
		s.initBoard();

		assertEquals(Piece.PAWN, s.getPiece(1, 1, 1).getType()); // VERIFICO SE
																	// PEDONE
																	// BIANCO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p1, s.coordinatesToNotation(1, 1), s.coordinatesToNotation(2, 1));
		assertEquals(Piece.PAWN, s.getPiece(2, 1, 1).getType());

		assertEquals(Piece.PAWN, s.getPiece(6, 1, 0).getType()); // VERIFICO SE
																	// PEDONE
																	// NERO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p0, s.coordinatesToNotation(6, 1), s.coordinatesToNotation(5, 1));
		assertEquals(Piece.PAWN, s.getPiece(5, 1, 0).getType());

		assertEquals(Piece.PAWN, s.getPiece(1, 4, 1).getType()); // VERIFICO SE
																	// PEDONE
																	// BIANCO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p1, s.coordinatesToNotation(1, 4), s.coordinatesToNotation(2, 4));
		assertEquals(Piece.PAWN, s.getPiece(2, 4, 1).getType());

		assertEquals(Piece.PAWN, s.getPiece(6, 4, 0).getType()); // VERIFICO SE
																	// PEDONE
																	// NERO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p0, s.coordinatesToNotation(6, 4), s.coordinatesToNotation(5, 4));
		assertEquals(Piece.PAWN, s.getPiece(5, 4, 0).getType());

		assertEquals(Piece.KNIGHT, s.getPiece(0, 1, 1).getType()); // VERIFICO
																	// SE
																	// CAVALLO
																	// BIANCO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p1, s.coordinatesToNotation(0, 1), s.coordinatesToNotation(2, 2));
		assertEquals(Piece.KNIGHT, s.getPiece(2, 2, 1).getType());

		assertEquals(Piece.KNIGHT, s.getPiece(7, 1, 0).getType()); // VERIFICO
																	// SE
																	// CAVALLO
																	// NERO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p0, s.coordinatesToNotation(7, 1), s.coordinatesToNotation(5, 0));
		assertEquals(Piece.KNIGHT, s.getPiece(5, 0, 0).getType());

		assertEquals(Piece.BISHOP, s.getPiece(0, 2, 1).getType()); // VERIFICO
																	// SE
																	// ALFIERE
																	// BIANCO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p1, s.coordinatesToNotation(0, 2), s.coordinatesToNotation(2, 0));
		assertEquals(Piece.BISHOP, s.getPiece(2, 0, 1).getType());

		assertEquals(Piece.BISHOP, s.getPiece(7, 2, 0).getType()); // VERIFICO
																	// SE
																	// ALFIERE
																	// NERO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p0, s.coordinatesToNotation(7, 2), s.coordinatesToNotation(5, 0));
		assertEquals(Piece.BISHOP, s.getPiece(5, 0, 0).getType());

		assertEquals(Piece.ROOK, s.getPiece(0, 0, 1).getType()); // VERIFICO SE
																	// TORRE
																	// BIANCO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p1, s.coordinatesToNotation(0, 0), s.coordinatesToNotation(0, 2));
		assertEquals(Piece.ROOK, s.getPiece(0, 2, 1).getType());

		assertEquals(Piece.ROOK, s.getPiece(7, 0, 0).getType()); // VERIFICO SE
																	// TORRE
																	// NERO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p0, s.coordinatesToNotation(7, 0), s.coordinatesToNotation(7, 2));
		assertEquals(Piece.ROOK, s.getPiece(7, 2, 0).getType());

		assertEquals(Piece.QUEEN, s.getPiece(0, 3, 1).getType()); // VERIFICO SE
																	// REGINA
																	// BIANCO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p1, s.coordinatesToNotation(0, 3), s.coordinatesToNotation(4, 7));
		assertEquals(Piece.QUEEN, s.getPiece(4, 7, 1).getType());

		assertEquals(Piece.QUEEN, s.getPiece(7, 3, 0).getType()); // VERIFICO SE
																	// REGINA
																	// NERO SI
																	// MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p0, s.coordinatesToNotation(7, 3), s.coordinatesToNotation(3, 7));
		assertEquals(Piece.QUEEN, s.getPiece(3, 7, 0).getType());

		assertEquals(Piece.KING, s.getPiece(0, 4, 1).getType()); // VERIFICO SE
																	// RE BIANCO
																	// SI MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p1, s.coordinatesToNotation(0, 4), s.coordinatesToNotation(0, 3));
		assertEquals(Piece.KING, s.getPiece(0, 3, 1).getType());

		assertEquals(Piece.KING, s.getPiece(7, 4, 0).getType()); // VERIFICO SE
																	// RE NERO
																	// SI MUOVE
																	// CORRETTAMENTE
		mv.movePiece(s, m, p0, s.coordinatesToNotation(7, 4), s.coordinatesToNotation(6, 4));
		assertEquals(Piece.KING, s.getPiece(6, 4, 0).getType());

	}

}
