
package view;

import model.Board;
import control.Move;
import model.Piece;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import model.Player;
import model.Index;

public class Gui extends JPanel implements Runnable, MouseListener {

	/*
	 * classe che implementa l'interfaccia grafica e gestisce le interazioni con
	 * le altri classi secondo il pattern MVC
	 */

	private static final long serialVersionUID = -5154317893203520132L;

	private static Board board;
	private Graphics g1;
	Player player;
	private boolean srcSelected = false;
	private boolean destSelected = false;
	private String selectedSrc;
	private String selectedDest;
	private String clickedSquare;
	private int sizeX, sizeY;
	private Player player1, player2;
	private Move move;
	private int concecutMEvent = 0;
	private boolean showLegalMoves = true;
	private static boolean humanVsHumanMode;
	private int whosTurn;

	public String jarGraphicsPath = "/view/immagini/";

	private JFrame f;
	private JMenuBar menuBar;
	private JMenu menu;

	private JMenuItem menuItem;
	private JMenuItem menuItem1;

	public Move getMove() {
		return move;
	}

	public int getWhosTurn() {
		return whosTurn;
	}

	public void setWhosTurn(int whosTurn) {
		this.whosTurn = whosTurn;
	}

	public boolean isSrcSelected() {
		return srcSelected;
	}

	public void setSrcSelected(boolean srcSelected) {
		this.srcSelected = srcSelected;
	}

	public boolean isDestSelected() {
		return destSelected;
	}

	public void setDestSelected(boolean destSelected) {
		this.destSelected = destSelected;
	}

	public String getClickedSquare() {
		return clickedSquare;
	}

	public void setClickedSquare(String clickedSquare) {
		this.clickedSquare = clickedSquare;
	}

	public String getSelectedSrc() {
		return selectedSrc;
	}

	public void setSelectedSrc(String selectedSrc) {
		this.selectedSrc = selectedSrc;
	}

	public String getSelectedDest() {
		return selectedDest;
	}

	public void setSelectedDest(String selectedDest) {
		this.selectedDest = selectedDest;
	}

	private int getSizeX() {
		return sizeX;
	}

	private void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	private int getSizeY() {
		return sizeY;
	}

	private void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public void setSize(int x, int y) {
		setSizeX(x);
		setSizeY(y);
	}

	private Graphics getG1() {
		return g1;
	}

	private void setG1(Graphics g1) {
		this.g1 = g1;
	}

	public boolean isHumanVsHumanMode() {
		return humanVsHumanMode;
	}

	private void createPlayers() {

		player1 = new Player(0);
		player2 = new Player(1);
	}

	private void initGame() {
		board = new Board();
		move = new Move(board);
		createPlayers();
	}

	/* costruttore della classe GUI */

	public Gui() {

		humanVsHumanMode = true;
		player = new Player();
		addMouseListener(this);
		setSize(400, 400);
		Dimension d = new Dimension(getSizeX(), getSizeY());
		setPreferredSize(d);
		initGuiComponents();
		initGame();
		setWhosTurn(0);
	}

	/* metodo che fa partire tutta l'interfaccia grafica */

	private void initGuiComponents() {
		// ======== menuBar ========

		menuBar = new JMenuBar();

		menu = new JMenu("File");

		menuItem1 = new JMenuItem("Nuovo Gioco");
		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitMenuItemActionPerformed(e);
				playHumanVsHumanActionPerformed(e);
			}
		});
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
		menu.add(menuItem1);

		// ---- menuItem ----
		menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitMenuItemActionPerformed(e);
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
		menu.add(menuItem);

		menuBar.add(menu);

	}
	/* metodo che si occupa di mostrare la scacchiera */

	protected void paintComponent(Graphics g) {
		setG1(g);
		Graphics2D g2 = (Graphics2D) getG1();
		drawBoard(getG1());
		Color blue = new Color(0, 135, 205);
		Color orange = new Color(246, 213, 83);
		if (isSrcSelected()) {
			if (showLegalMoves) {

				paintSquare(getSelectedSrc(), blue);

				Piece piece = board.notationToPiece(getSelectedSrc());

				ArrayList<ArrayList<Integer>> legalMoves = move.possiblePieceMoves(piece, false);
				ArrayList<Integer> x = legalMoves.get(0);
				ArrayList<Integer> y = legalMoves.get(1);
				ListIterator<Integer> xList = x.listIterator();
				ListIterator<Integer> yList = y.listIterator();
				while (xList.hasNext() && yList.hasNext()) {

					paintSquare(board.coordinatesToNotation(xList.next(), yList.next()), orange);
				}
			}
		}
		drawPieces(g2);
	}

	/* metodo che disegna i pezzi */

	public void drawPieces(Graphics2D g2) {

		ArrayList<Piece> pieces = board.getPieces();
		Toolkit toolkit = getToolkit();
		Image img = null;
		URL imgURL = null;

		for (Piece p : pieces) {
			if (p.getColor() == 0) {

				switch (p.getType()) {
				case 1:

					imgURL = this.getClass().getResource(jarGraphicsPath + "wk.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 2:

					imgURL = this.getClass().getResource(jarGraphicsPath + "wq.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 3:

					imgURL = this.getClass().getResource(jarGraphicsPath + "wr.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 4:

					imgURL = this.getClass().getResource(jarGraphicsPath + "wn.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 5:

					imgURL = this.getClass().getResource(jarGraphicsPath + "wb.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 6:

					imgURL = this.getClass().getResource(jarGraphicsPath + "wp.gif");
					img = toolkit.getImage(imgURL);

					break;
				default:
					break;
				}
			} else if (p.getColor() == 1) {

				switch (p.getType()) {
				case 1:

					imgURL = this.getClass().getResource(jarGraphicsPath + "bk.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 2:

					imgURL = this.getClass().getResource(jarGraphicsPath + "bq.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 3:

					imgURL = this.getClass().getResource(jarGraphicsPath + "br.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 4:

					imgURL = this.getClass().getResource(jarGraphicsPath + "bn.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 5:

					imgURL = this.getClass().getResource(jarGraphicsPath + "bb.gif");
					img = toolkit.getImage(imgURL);

					break;
				case 6:

					imgURL = this.getClass().getResource(jarGraphicsPath + "bp.gif");
					img = toolkit.getImage(imgURL);

					break;
				default:
					break;
				}
			}
			if (p != null) {
				String notation = board.coordinatesToNotation(p.getRow(), p.getCol());
				Index idx = notationToPieceLoc(notation);
				g2.drawImage(img, idx.getX(), idx.getY(), this);
			}
		}
	}

	public void movePiece(Board board, Move move, Player player, String src, String dest) {

		Piece p = board.notationToPiece(src);

		Index newLoc = board.notationToIndex(dest);

		board.removePiece(newLoc.getX(), newLoc.getY());
		p.setRow(newLoc.getX());
		p.setCol(newLoc.getY());

		if (player.getSide() == 0) {

			move.getHistoryOfMoves().addWhiteMove(src, dest);
		} else if (player.getSide() == 1) { // if black

			move.getHistoryOfMoves().addBlackMove(src, dest);
		}

		board.promotePawnsToQueen(player.getSide());
	}

	/* metodo che disegna la scacchiera */

	public void drawBoard(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		int even = 0, x, y, w, h;
		Color color1 = Color.lightGray;
		Color color2 = Color.darkGray;
		for (int i = 0; i < 8; i++) {

			if (even % 8 == 0) {
				Color temp = color1;
				color1 = color2;
				color2 = temp;
			}
			for (int j = 0; j < 8; j++) {
				if (even % 2 == 0) {
					g.setColor(color2);
				} else {
					g.setColor(color1);
				}
				if (i == 0) {
					y = 0;
					h = (height / 8) - 1;
				} else {
					y = i * (height / 8) - 1;
					h = height / 8;
				}
				if (j == 0) {
					x = 0;
					w = (width / 8) - 1;
				} else {
					x = j * (width / 8) - 1;
					w = width / 8;
				}
				g.fillRect(x, y, w, h);
				even++;
			}
		}
	}

	/* disegna le caselle */

	public void paintSquare(String notation, Color color) {

		Index squareLoc = notationToPieceLoc(notation);

		getG1().setColor(color);
		getG1().fillRect(squareLoc.getX(), squareLoc.getY(), 50, 50);
	}

	private Index notationToPieceLoc(String notation) {

		int width = getWidth();
		int height = getHeight();

		int[] columnCoord = { 0, (width / 8) - 1, 2 * (width / 8) - 1, 3 * (width / 8) - 1, 4 * (width / 8) - 1,
				5 * (width / 8) - 1, 6 * (width / 8) - 1, 7 * (width / 8) - 1 };

		int[] rowCoord = { 0, (height / 8) - 1, 2 * (height / 8) - 1, 3 * (height / 8) - 1, 4 * (height / 8) - 1,
				5 * (height / 8) - 1, 6 * (height / 8) - 1, 7 * (height / 8) - 1 };

		Index loc, gLoc = new Index(0, 0);

		loc = new Board().notationToIndex(notation);

		gLoc.setX(columnCoord[loc.getY()]);
		gLoc.setY(rowCoord[loc.getX()]);

		return gLoc;
	}

	/* metodo che avvia e crea la finestra principale */

	public void run() {

		f = new JFrame("Progetto Scacchi 2014-2015");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.getContentPane().add(new Gui());
		f.setJMenuBar(menuBar);

		try {
			f.setIconImage(new ImageIcon("./graphics/icon.jpg").getImage());
		} catch (Exception e) {

		}

		f.setVisible(true);

		Dimension d = new Dimension(getSizeX(), getSizeY() + menuBar.getHeight());
		f.setSize(d);
		f.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int w = f.getSize().width;
		int h = f.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		f.setLocation(x, y);

		f.pack();
	}

	public int getImmagini(String path) {
		int value_piece = 0;

		switch (path) {

		case "jarGraphicsPath" + "wk.gif":
		case "jarGraphicsPath" + "bk.gif":
			value_piece += 1;
			break;

		case "jarGraphicsPath" + "wq.gif":
		case "jarGraphicsPath" + "bq.gif":
			value_piece += 2;
			break;

		case "jarGraphicsPath" + "wr.gif":
		case "jarGraphicsPath" + "br.gif":
			value_piece += 3;
			break;

		case "jarGraphicsPath" + "wn.gif":
		case "jarGraphicsPath" + "bn.gif":
			value_piece += 4;
			break;

		case "jarGraphicsPath" + "wb.gif":
		case "jarGraphicsPath" + "bb.gif":
			value_piece += 5;
			break;

		case "jarGraphicsPath" + "wp.gif":
		case "jarGraphicsPath" + "bp.gif":
			value_piece += 6;
			break;

		default:
			break;
		}

		return value_piece;

	}

	public String preciseCoordinatesToNotationX(int x) {
		if (0 <= x && x <= 49) { // a
			return "a";
		} else if (50 <= x && x <= 99) { // b
			return "b";
		} else if (100 <= x && x <= 149) { // c
			return "c";
		} else if (150 <= x && x <= 190) { // d
			return "d";
		} else if (200 <= x && x <= 249) { // e
			return "e";
		} else if (250 <= x && x <= 299) { // f
			return "f";
		} else if (300 <= x && x <= 349) { // g
			return "g";
		} else if (350 <= x && x <= 399) { // h
			return "h";
		} else {
			return "";
		}
	}

	public String preciseCoordinatesToNotationY(int y) {
		if (0 <= y && y <= 49) { // 8
			return "8";
		} else if (50 <= y && y <= 99) { // 7
			return "7";
		} else if (100 <= y && y <= 149) { // 6
			return "6";
		} else if (150 <= y && y <= 199) { // 5
			return "5";
		} else if (200 <= y && y <= 249) { // 4
			return "4";
		} else if (250 <= y && y <= 299) { // 3
			return "3";
		} else if (300 <= y && y <= 349) { // 2
			return "2";
		} else if (350 <= y && y <= 399) { // 1
			return "1";
		} else {
			return "";
		}
	}

	public String preciseCoordinatesToNotation(int x, int y) {
		String s1 = preciseCoordinatesToNotationX(x);
		String s2 = preciseCoordinatesToNotationY(y);

		if (s1 == "" || s2 == "") {
			s1 = "";
			s2 = "";
		}
		String notation = s1 + s2;
		return notation;
	}

	public boolean isSrcSqValid(String srcSq, Move move, Board board, Player player) {

		if (!move.checkSqValidity(srcSq)) {
			return false;
		}

		ArrayList<Piece> ownPieces = board.getPiecesFromOneSide(player.getSide());
		for (Piece p : ownPieces) {
			if (board.coordinatesToNotation(p.getRow(), p.getCol()).equalsIgnoreCase(srcSq)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDestSqValid(String srcSq, String destSq, Move move, Board board, Player player) {

		if (!move.checkSqValidity(destSq)) {
			return false;
		}
		Piece piece = board.notationToPiece(srcSq);

		ArrayList<ArrayList<Integer>> legalMoves = move.possiblePieceMoves(piece, false);

		Index newLoc = board.notationToIndex(destSq);

		ArrayList<Integer> x = legalMoves.get(0);
		ArrayList<Integer> y = legalMoves.get(1);
		ListIterator<Integer> xList = x.listIterator();
		ListIterator<Integer> yList = y.listIterator();
		int xL, yL;
		while (xList.hasNext() && yList.hasNext()) {

			xL = xList.next();
			yL = yList.next();
			if (newLoc.getX() == xL && newLoc.getY() == yL) {
				return true;
			}
		}
		return false;
	}

	public void mouseClicked(MouseEvent e) {

		int color = getWhosTurn();
		if (color == 0) {
			player = player1;
		} else {
			player = player2;
		}

		if (concecutMEvent == 1) {
			concecutMEvent = 2;
		}

		switch (e.getModifiers()) {

		case InputEvent.BUTTON1_MASK: {

			setClickedSquare(preciseCoordinatesToNotation(e.getX(), e.getY()));

			if (!isSrcSelected()) {

				if (isSrcSqValid(getClickedSquare(), getMove(), board, player)) {

					setSrcSelected(true);

					setSelectedSrc(getClickedSquare());
					concecutMEvent = 1;
					if (showLegalMoves) {
						repaint();
					}
				} else {
					JOptionPane.showMessageDialog(f, "Casella selezionata non valida ", "Attenzione",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
			if (concecutMEvent == 2) {

				if (isSrcSelected() && !isDestSelected()) {

					if (isDestSqValid(getSelectedSrc(), getClickedSquare(), getMove(), board, player)) {

						setDestSelected(true);

						setSelectedDest(getClickedSquare());

						concecutMEvent = 0;
					} else {
						JOptionPane.showMessageDialog(f, "Casella di destinazione non valida", "Attenzione",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			}

			if (isSrcSelected() && isDestSelected()) {

				movePiece(board, getMove(), player, getSelectedSrc(), getSelectedDest());

				repaint();

				setSrcSelected(false);
				setDestSelected(false);

				if (isHumanVsHumanMode()) {
					if (getWhosTurn() == 0) {
						setWhosTurn(1);
					} else {
						setWhosTurn(0);
					}
				}

			}
			break;
		}

		case InputEvent.BUTTON3_MASK: {

			setSrcSelected(false);
			repaint();
			break;
		}
		}

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	private void quitMenuItemActionPerformed(ActionEvent e) {
		f.setVisible(false);
		f.dispose();
	}

	private void playHumanVsHumanActionPerformed(ActionEvent e) {
		initHumanVsHumanMode();
	}

	private void initHumanVsHumanMode() {
		EventQueue.invokeLater(new Gui());
		/*
		 * board.setPieces(new ArrayList<Piece>(32)); board.createPieces();
		 * createPlayers();
		 * 
		 * humanVsHumanMode = true;
		 */
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Gui());
		JFrame window = new SchermataPrincipaleView();
		window.setVisible(true);
	}

}