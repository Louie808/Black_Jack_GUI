//Louie Bala, lab section 04
package Games.deckOfCards;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class deck {
	public java.util.ArrayList<card> cards;
	public JPanel deck_panel;
	private JLabel deck_label;
	public String name;
	//name
	public void playerName(String name) {
		this.name = name;
		deck_label.setText(name+" hand value : "+getHandValue());
		
	}
	public String getName() {
		return name;
	}
	//creates card list for storing
	public deck(){
		cards = new java.util.ArrayList<card>();
		deck_panel = new JPanel();
		deck_label = new JLabel();
		deck_label.setFont(new Font("Ink Free", Font.BOLD,20));
		deck_panel.add(deck_label);
		deck_panel.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT,20,0));
		deck_panel.setBackground(null);
	}
	
	//method to create a full deck and stores into arraylist
	public void createFullDeck() {
		int i = 0;
		for(cardSuit s: cardSuit.values()) {
			for(cardValue v: cardValue.values()) {
				card newcard = new card(s, v, i);
				cards.add(newcard);
				deck_panel.add(newcard.faceValue());
				i+=1;
			}
		}
	}
	public void clearDeck() {
		cards.removeAll(cards);
		deck_panel.removeAll();
		deck_panel.add(deck_label);
	}
	//set the cards manually
	public void addcard(card d) {
		cards.add(d);
		deck_panel.add(d.faceValue(), cards.size()-1);
		deck_label.setText(this.name+" hand value : "+getHandValue());
	}
	//mixes the deck so its not in order from the creation
	public void shuffle() {
		java.util.Collections.shuffle(cards);
	}
	//deck size
	public int deckSize() {
		return cards.size();
	}
	//draws a card from playdeck to be stored in the deck that called the function
	public void draw(deck playdeck) {
		card tempCard = playdeck.cards.get(0);
		cards.add(tempCard);
		//moves the card that was drawn to the back of the deck
		playdeck.cards.remove(0);
		playdeck.cards.add(tempCard);
		deck_panel.add(tempCard.faceValue(), cards.size()-1);
		deck_label.setText(name+" hand value : "+getHandValue());
	}
	public int getCardValue(int index) {
		card c = cards.get(index);
		return c.getCardValue(c);
	}
	public int getHandValue(){
		int totalValue = 0, aces = 0;
		for(card c: cards) {
			switch(c.cardValue()) {
			case TWO: totalValue+=2;break; 

			case THREE: totalValue+=3;break; 

			case FOUR: totalValue+=4;break; 

			case FIVE: totalValue+=5;break; 

			case SIX: totalValue+=6;break; 

			case SEVEN: totalValue+=7;break; 
			
			case EIGHT: totalValue+=8;break; 

			case NINE: totalValue+=9;break; 

			case TEN: totalValue+=10;break; 

			case JACK: totalValue+=10;break; 
			
			case QUEEN: totalValue+=10;break; 
			
			case KING: totalValue+=10;break; 
			
			case ACE: aces+=1;break;
			
			}
		}
		for(int i  = 0 ; i < aces; i++) {
			if(totalValue > 10) {totalValue += 1;}
			else {totalValue += 11;}
		}
		return totalValue;
		}
	//string of entire deck
	public String toString() {
		String s="";
		for(card c: cards) {s+=c.toString()+"\n";}
		return s;
	}
}
