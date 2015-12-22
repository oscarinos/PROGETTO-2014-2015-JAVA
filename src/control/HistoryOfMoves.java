
package control;

import java.util.*;



public class HistoryOfMoves {

	/*
	 * HISTORY OF MOVES : classe java che tiene traccia di ogni singolo
	 * movimento dei pezzi
	 */

	private ArrayList<String> whiteSrc = new ArrayList<String>();
	private ArrayList<String> whiteDest = new ArrayList<String>();
	private ArrayList<String> blackSrc = new ArrayList<String>();
	private ArrayList<String> blackDest = new ArrayList<String>();

	public ArrayList<String> getWhiteSrc() {
		return whiteSrc;
	}

	public void setWhiteSrc(ArrayList<String> whiteSrc) {
		this.whiteSrc = whiteSrc;
	}

	public ArrayList<String> getWhiteDest() {
		return whiteDest;
	}

	public void setWhiteDest(ArrayList<String> whiteDest) {
		this.whiteDest = whiteDest;
	}

	public ArrayList<String> getBlackSrc() {
		return blackSrc;
	}

	public void setBlackSrc(ArrayList<String> blackSrc) {
		this.blackSrc = blackSrc;
	}

	public ArrayList<String> getBlackDest() {
		return blackDest;
	}

	public void setBlackDest(ArrayList<String> blackDest) {
		this.blackDest = blackDest;
	}

	public void addWhiteSrc(String notation) {
		whiteSrc.add(notation);
	}

	public void addWhiteDest(String notation) {
		whiteDest.add(notation);
	}

	public void addBlackSrc(String notation) {
		blackSrc.add(notation);
	}

	public void addBlackDest(String notation) {
		blackDest.add(notation);
	}

	public void addWhiteMove(String src, String dest) {
		addWhiteSrc(src);
		addWhiteDest(dest);
	}

	public void addBlackMove(String src, String dest) {
		addBlackSrc(src);
		addBlackDest(dest);
	}

	
	}


