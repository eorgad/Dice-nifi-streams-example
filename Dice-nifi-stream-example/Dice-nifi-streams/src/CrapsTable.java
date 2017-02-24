/*
 *   CrapsTable calculate win or lose for a table 
 *  eorgad - Hortonworks.com 02/15/2017
 */

public class CrapsTable {

	private int marker;
	private int four;
	private int five;
	private int six;
	private int eight;
	private int nine;
	private int ten;
	
	private int sumofwinnings;
	private int bet=5;
	private int betFourFiveNineTen=5;
	private int betSixEight=6;
	
	
	
	public int getSumofwinnings() {
		return sumofwinnings;
	}
	public void setSumofwinnings(int sumofwinnings) {
		this.sumofwinnings = sumofwinnings;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public int getBetFourFiveNineTen() {
		return betFourFiveNineTen;
	}
	public void setBetFourFiveNineTen(int betFourFiveNineTen) {
		this.betFourFiveNineTen = betFourFiveNineTen;
	}
	public int getBetSixEight() {
		return betSixEight;
	}
	public void setBetSixEight(int betSixEight) {
		this.betSixEight = betSixEight;
	}
	public int getMarker() {
		return marker;
	}
	public void setMarker(int marker) {
		this.marker = marker;
	}
	public int getFour() {
		return four;
	}
	public void setFour(int four) {
		this.four = four;
	}
	public int getFive() {
		return five;
	}
	public void setFive(int five) {
		this.five = five;
	}
	public int getSix() {
		return six;
	}
	public void setSix(int six) {
		this.six = six;
	}
	public int getEight() {
		return eight;
	}
	public void setEight(int eight) {
		this.eight = eight;
	}
	public int getNine() {
		return nine;
	}
	public void setNine(int nine) {
		this.nine = nine;
	}
	public int getTen() {
		return ten;
	}
	public void setTen(int ten) {
		this.ten = ten;
	}
	
	
	
}
