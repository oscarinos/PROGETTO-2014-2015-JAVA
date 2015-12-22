package view;

import model.Board;

import static org.junit.Assert.*;

import org.junit.Test;


public class ViewTest {
	Board s = new Board();
        Gui m= new Gui();
    	public String jarGraphicsPath="/view/immagini/";   
	

	@Test
	public void test() {
		s.initBoard();
                    
		         assertEquals(s.getPiece(0, 4, 1).getType(), m.getImmagini("jarGraphicsPath"+"wk.gif"));
                 assertEquals(s.getPiece(7, 4, 0).getType(), m.getImmagini("jarGraphicsPath"+"bk.gif"));

                assertEquals(s.getPiece(0, 3, 1).getType(), m.getImmagini("jarGraphicsPath"+"wq.gif"));
                assertEquals(s.getPiece(7, 3, 0).getType(), m.getImmagini("jarGraphicsPath"+"bq.gif"));

	            assertEquals(s.getPiece(0, 0, 1).getType(), m.getImmagini("jarGraphicsPath"+"wr.gif"));
                assertEquals(s.getPiece(7, 0, 0).getType(), m.getImmagini("jarGraphicsPath"+"br.gif"));

	            assertEquals(s.getPiece(0, 1, 1).getType(), m.getImmagini("jarGraphicsPath"+"wn.gif"));
                assertEquals(s.getPiece(7, 6, 0).getType(), m.getImmagini("jarGraphicsPath"+"bn.gif"));

	            assertEquals(s.getPiece(0, 5, 1).getType(), m.getImmagini("jarGraphicsPath"+"wb.gif"));
                assertEquals(s.getPiece(7, 5, 0).getType(), m.getImmagini("jarGraphicsPath"+"bb.gif"));

	            assertEquals(s.getPiece(1, 4, 1).getType(), m.getImmagini("jarGraphicsPath"+"wp.gif"));
                assertEquals(s.getPiece(6, 4, 0).getType(), m.getImmagini("jarGraphicsPath"+"bp.gif")); 
               
	}


}