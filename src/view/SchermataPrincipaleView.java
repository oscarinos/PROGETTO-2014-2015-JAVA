package view;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class SchermataPrincipaleView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6643290427754551009L;

	public SchermataPrincipaleView() {
		
		//Layout principale
		JPanel borderl = new JPanel();
		borderl.setLayout(new BorderLayout());
		
		
		JButton tastoinizio = new JButton("Inizia a giocare!");
		tastoinizio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				}
		});
		
	
		
		//Layout bottoni
		JPanel zonaBottoni = new JPanel();
		zonaBottoni.setLayout(new FlowLayout(FlowLayout.CENTER));
		zonaBottoni.add(tastoinizio);
		

		//Layout titolo
		JPanel sopra = new JPanel();
		sopra.setLayout(new BoxLayout(sopra, BoxLayout.Y_AXIS));

		//Titolo
		JTextPane presentazione = new JTextPane();
		presentazione.setText("\n Università  degli studi di Verona - Scacchi 2014/2015  \n\n"
				+ "Andrea Sorze VR 365772 \n"
				+ "Oscar Inostroza VR 359551 \n\n\n"
				+ "PROGRAMMAZIONE II\n "
				+ "NICOLA FAUSTO SPOTO \n"
				
				
				);

		presentazione.setEditable(false);

		//Centro il testo all'interno di presentazione
		StyledDocument doc = presentazione.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center1, false);

		sopra.add(presentazione);

		borderl.add(sopra, BorderLayout.NORTH);
		borderl.add(zonaBottoni, BorderLayout.SOUTH);

		//Inizializzo la finestra..
		setContentPane(borderl);
		setTitle("Presentazione progetto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();

		// centro dello schermo
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
}