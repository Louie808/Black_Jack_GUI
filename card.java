//Louie Bala, lab section 04
package Games.deckOfCards;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

enum cardSuit{
	SPADES,
	HEARTS,
	CLUBS,
	DIAMONDS
}
enum cardValue{
	TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, 
	JACK, QUEEN, KING, ACE
}
public class card {
	private cardSuit suit;
	private cardValue value;
	
	//guiface
	private JLabel face;
	private String[] picSource = {	
		"/S2.png", "/S3.png", "/S4.png" ,"/S5.png", "/S6.png", "/S7.png", "/S8.png","/S9.png", "/S10.png", "/SJ.png", "/SQ.png", "/SK.png","/SA.png",
		"/H2.png", "/H3.png", "/H4.png" ,"/H5.png", "/H6.png", "/H7.png", "/H8.png","/H9.png", "/H10.png", "/HJ.png", "/HQ.png", "/HK.png","/HA.png",
		"/C2.png", "/C3.png", "/C4.png" ,"/C5.png", "/C6.png", "/C7.png", "/C8.png","/C9.png", "/C10.png", "/CJ.png", "/CQ.png", "/CK.png","/CA.png",			"/D2.png", "/D3.png", "/D4.png" ,"/D5.png", "/D6.png", "/D7.png", "/D8.png","/D9.png", "/D10.png", "/DJ.png", "/DQ.png", "/DK.png","/DA.png"};
	
	
	card(cardSuit suit, cardValue value, int index){
		this.suit = suit;
		this.value = value;
		face = new JLabel();
		face.setIcon(new ImageIcon("C:\\Users\\louie\\eclipse_WorkSpace\\Games\\src\\Games\\deckOfCards"+picSource[index]));//this.getClass().getResource((picSource[index]))));
	}
	//return value of card
	cardValue cardValue() {
		return this.value;
	}
	//returns gui face label
	JLabel faceValue() {
		face.setText(this.toString());
		face.setHorizontalTextPosition(JLabel.CENTER);
		face.setVerticalTextPosition(JLabel.BOTTOM);
		face.setIconTextGap(-10);
		face.setFont(new Font("Ink Free", Font.BOLD, 20));
		return face;
	}
	public int getCardValue(card c) {
		int value = 0;
		switch(this.value) {
		case TWO: value=2;break; 

		case THREE: value=3;break; 

		case FOUR: value=4;break; 

		case FIVE: value=5;break; 

		case SIX: value=6;break; 

		case SEVEN: value=7;break; 
		
		case EIGHT: value=8;break; 

		case NINE: value=9;break; 

		case TEN: value=10;break; 

		case JACK: value=10;break; 
		
		case QUEEN: value=10;break; 
		
		case KING: value=10;break; 
		
		case ACE: value=11;break;
		
		}
		return value;
	}
	public String toString() {
		return this.value+" of "+this.suit;
	}
}
