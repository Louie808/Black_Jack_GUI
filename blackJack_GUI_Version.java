//Louie Bala, lab section 04
package Games.deckOfCards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class blackJack_GUI_Version extends JFrame{
	//game variables
	static deck gameDeck;//decks
	deck player;
	deck dealer;
	//split deck
//	static deck hand1, hand2;
//	static deck[] hands;
	static double money = 100; 
	static int bet_amount;
	
	//game run methods
	public void createDecks() {
		gameDeck = new deck();
		gameDeck.createFullDeck();
		player = new deck();
		player.playerName("You");
		dealer = new deck();
		dealer.playerName("Dealer");
		
		addDeckToPlayField(dealer);
		addDeckToPlayField(player);		
		
		for(deck d: decks) {
			d.deck_panel.setVisible(false);
		}
	}
	
	public void gameStart() {
		resetBoard();
		action_playButton.setEnabled(false);
		bet_button.setEnabled(true);
		bet_input.setEnabled(true);
		newRound();
	}
	
	public void newRound() {
		action_action.setText("Place bet");
		
		//comment out to switch mix mode
		{
			gameDeck.shuffle();
			player.draw(gameDeck);
			dealer.draw(gameDeck);
			player.draw(gameDeck);
		}
		{//cards are set by index
//			player.addcard(gameDeck.cards.get(10));
//			player.addcard(gameDeck.cards.get(23));
//			dealer.addcard(gameDeck.cards.get(11));
//			dealer.addcard(gameDeck.cards.get(19));
//			gameDeck.shuffle();
		}
	}
	
	//game window
	private JFrame game_frame;
	private JPanel contents;
	private ImageIcon game_icon;
	
	//title
	private JPanel title_panel;//NORTH
	private JLabel title_label;
	private JButton reset;
	private JButton exit;
	
	//action panel
	private JPanel action_panel;//EAST
	private JLabel action_title;
	private JLabel action_action;
	private JLabel[] action_labels;
	private JButton action_playButton;
	
	//playfield 
	private JPanel cards_panel;//CENTER
	private java.util.ArrayList<deck> decks;
	
	//money panel
	private JPanel money_panel;
	private JLabel money_label;
	private JLabel money_amount;
	
	//bet panel
	private JPanel bet_panel;//WEST
	
	private JLabel bet_inputlabel;
	private JTextField bet_input;
	private JLabel bet_setlabel1;
	private JLabel bet_setlabel2;
	private JLabel bet_setamount;
	private JButton bet_button;
	
	//option panel
	private JPanel option_panel;
	private static JButton[] option_button;
	private static String[] options = {" HIT ", " STAY ", " DOUBLE DOWN ", " SPLIT "};
		
	//border
	private Border borderContents = 
			BorderFactory.createEmptyBorder(10,10,10,10);
	private Border borderRegion = 
			BorderFactory.createLineBorder(Color.WHITE, 2);
	private Border borderButton = 
			BorderFactory.createEtchedBorder(10, Color.WHITE, Color.WHITE);
	
	//color
	private Color colorContents = Color.RED;//contents pane
	private Color colorPanels = Color.BLACK;//panel color
	private Color colorLabel = Color.GREEN;//contents label
	private Color colorButton = Color.YELLOW;//buttons and textfield
	private Color colorFont = new Color(0x9111FF);//foreground
	//Fonts
	private Font fontbig = new Font("Ink Free", Font.BOLD,35);
	private Font fontlabels = new Font("Ink Free", Font.BOLD,25);
	private Font fontsmall = new Font("Ink Free", Font.BOLD,18);

	public blackJack_GUI_Version(){
		setFonts();
		decks = new java.util.ArrayList<deck>();
		
		game_frame = new JFrame("Black Jack GUI");
		//sets icon of window
		game_icon = new ImageIcon("deckOfCards/SA.png");
		game_frame.setIconImage(game_icon.getImage());
		
		game_frame.setSize(new Dimension(1550,800));
		game_frame.setResizable(true);
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contents = new JPanel();
		contents.setLayout(new BorderLayout());
		contents.setBorder(borderContents);
		contents.setBackground(colorContents);
		game_frame.setContentPane(contents);
		
		//Creation of panels and their methods
		createTitlePanel();
		createMoneyPanel();
		createBetPanel();
		createOptionPanel(3);
		createActionPanel();
		createPlayField();//cards_panel
		
		//add panels
		contents.add(cards_panel, BorderLayout.CENTER);
		contents.add(title_panel, BorderLayout.NORTH);
		contents.add(bet_panel, BorderLayout.WEST);
		contents.add(option_panel, BorderLayout.SOUTH);
		contents.add(action_panel, BorderLayout.EAST);

//		//reset button used for testing the reset method
		reset = new JButton("RESET");
		reset.setVisible(true);
		reset.setFocusable(false);
		reset.setBackground(Color.RED);
		reset.addActionListener(e-> resetBoard());
//		option_panel.add(reset, BorderLayout.EAST);//remove comment out to have it show
		//Exit button
		exit = new JButton(" EXIT GAME ");
		exit.setVisible(true);
		exit.setFocusable(false);
		exit.setBackground(Color.RED);
		exit.addActionListener(e-> System.exit(0));
		option_panel.add(exit, BorderLayout.EAST);
		
		pack();
		game_frame.setVisible(true);
		createDecks();
	}
	public void resetBoard() {
		//reset bet panel
		bet_setlabel1.setText("");
		bet_setlabel2.setText("");
		bet_button.setEnabled(false);
		bet_input.setEnabled(false);
		
		//hide playfield and clear decks
		for(deck d : decks) {
			d.clearDeck();
			d.deck_panel.setVisible(false);
		}
		
		//reset buttons
		for(JButton b : option_button) {
			b.setEnabled(false);
		}
		
		//reset game action
		for(JLabel o : action_labels) {
			o.setText("");
		}
		//new round button
		action_action.setText("press to go again");
		action_playButton.setEnabled(true);
		action_playButton.setText("another Round");
		contents.repaint();
	}
	
	//adds and removes a deck from the playfield. remove method is used more with the SPLIT option
	public void addDeckToPlayField(deck deck) {
		cards_panel.add(deck.deck_panel);
		decks.add(deck);
	}
	public void removeDeckFromPlayField(deck deck) {
		cards_panel.remove(deck.deck_panel);
	}
	
	//create Panels
	private void createPlayField() {
		cards_panel = new JPanel();
		cards_panel.setLayout(new GridLayout(2,1,0,0));
		cards_panel.setBackground(new Color(0,150,0));
	}
	private void createActionPanel() {
		action_panel = new JPanel();
		action_panel.setLayout(new GridLayout(6,1));
		action_panel.setPreferredSize(new Dimension(220,game_frame.getHeight()));
		action_panel.setBackground(colorPanels);
		
		action_title = new JLabel();
		action_title.setText("Game Actions");
		action_title.setFont(fontbig);
		action_title.setForeground(colorLabel);
		
		action_action = new JLabel();
		action_action.setForeground(colorLabel);
		action_action.setForeground(colorLabel);
		
		action_panel.add(action_title);
		action_panel.add(action_action);
		
		action_labels = new JLabel[3];
		for(int i = 0; i<action_labels.length ; i++) {
			action_labels[i] = new JLabel();
			action_labels[i].setForeground(new Color(0x9111FF));
			action_panel.add(action_labels[i]);
		}
		action_playButton = new JButton();
		action_playButton.setText("Press to Start");
		action_playButton.addActionListener(e -> gameStart());
		action_playButton.setFont(fontsmall);
		action_playButton.setFocusable(false);
		action_playButton.setBackground(colorButton);
		action_playButton.setForeground(colorFont);
		action_playButton.setEnabled(true);
		
		action_panel.add(action_playButton);
		
	}
	private void createTitlePanel() {
		title_panel = new JPanel();
		title_panel.setBorder(borderRegion);
		title_panel.setBackground(colorPanels);
		
		title_label = new JLabel("Black Jack");
		title_label.setPreferredSize(new Dimension(game_frame.getWidth()-100,game_frame.getHeight()/8));
		title_label.setBackground(colorPanels);//label background
		title_label.setForeground(colorLabel);
		title_label.setFont(new Font("Ink Free", Font.BOLD, 70));
		title_label.setHorizontalAlignment(JLabel.CENTER);
		title_label.setVerticalAlignment(JLabel.NORTH);
		title_label.setOpaque(true);
		title_panel.add(title_label);
	}
	private void createMoneyPanel() {
		money_panel = new JPanel();
		money_panel.setLayout(new BorderLayout());
		money_panel.setPreferredSize(new Dimension(150,40));
		money_panel.setBackground(colorPanels);
		money_label = new JLabel("Chip: ");
		money_amount= new JLabel();
		money_label.setBackground(null);
		money_label.setForeground(colorLabel);
		money_amount.setText(""+money);
		money_amount.setBackground(null);
		money_amount.setForeground(colorLabel);
		money_panel.add(money_label, BorderLayout.NORTH);
		money_panel.add(money_amount, BorderLayout.SOUTH);
	}
	private void createBetPanel() {
		bet_panel = new JPanel();
		bet_panel.setBorder(borderRegion);
		bet_panel.setPreferredSize(new Dimension(150,200));
		bet_panel.setBounds(0, 100, 200, 900);
		bet_panel.setLayout(new GridLayout(10,1,1,0));
		bet_panel.setBackground(colorPanels);

		bet_inputlabel = new JLabel();
		bet_inputlabel.setText("Input Bet: ");
		bet_inputlabel.setForeground(colorLabel);

		bet_setlabel1 = new JLabel();
		bet_setlabel1.setFont(fontsmall);
		bet_setlabel1.setForeground(colorLabel);
		bet_setlabel2 = new JLabel();
		bet_setlabel2.setFont(fontsmall);
		bet_setlabel2.setForeground(colorLabel);

		bet_setamount = new JLabel();
		bet_setamount.setForeground(colorLabel);
		bet_setamount.setFont(fontsmall);

		bet_input = new JTextField(10);
		bet_input.setFont(fontlabels);
		bet_input.setBackground(Color.WHITE);
		bet_input.setForeground(colorFont);
		bet_input.setEnabled(false);
		
		bet_button = new JButton("Place bet");
		bet_button.setFont(fontsmall);
		bet_button.addActionListener(e -> bet());
		bet_button.setFocusable(false);
		bet_button.setBackground(colorButton);
		bet_button.setForeground(colorFont);
		bet_button.setEnabled(false);
		
		bet_panel.add(money_panel, BorderLayout.NORTH);
		bet_panel.add(bet_inputlabel, BorderLayout.CENTER);
		bet_panel.add(bet_input, BorderLayout.CENTER);
		bet_panel.add(bet_button, BorderLayout.CENTER);
		bet_panel.add(bet_setlabel1, BorderLayout.CENTER);
		bet_panel.add(bet_setlabel2, BorderLayout.CENTER);
		bet_panel.add(bet_setamount, BorderLayout.CENTER);
	}
	private void createOptionPanel(int z) {
		option_panel = new JPanel();
		option_panel.setBorder(borderRegion);
		option_panel.setBackground(colorPanels);
		option_panel.setPreferredSize(new Dimension(500,80));
		option_panel.setLayout(new FlowLayout());//GridLayout(1,option_button.length,80,10));
		option_button = new JButton[z];
		for(int i = 0; i<option_button.length;i++) {
			option_button[i] = new JButton(options[i]);
			option_button[i].setPreferredSize(new Dimension(300,65));
			option_button[i].addActionListener(new optionclass());
			option_button[i].setBorder(borderRegion);
			option_button[i].setFocusable(false);
			option_button[i].setBorder(borderButton);
			option_button[i].setFont(fontbig);
			option_button[i].setBackground(colorButton);
			option_button[i].setForeground(colorFont);
			option_button[i].setEnabled(false);
			option_panel.add(option_button[i]);
		}
		pack();
	}
	
	//enables a button, used with the bet input
	public void optionAvailable(int i) {
		for(int j = 0; j<i; j++) {
			option_button[j].setEnabled(true);
		}
	}
	public void bet() {
		String s = bet_input.getText();
		boolean betdouble;
		try {
			bet_amount = Integer.parseInt(s);
			if(bet_amount == 0) {//if bet is 0
				throw new java.lang.NumberFormatException();
			}
			else if(bet_amount<=money) {
				betdouble = bet_amount*2<=money;//if bet can be doubled down
			}
			else {//any other input
				throw new java.io.FileNotFoundException();
			}
			bet_setlabel1.setText("Bet Placed -");
			bet_setlabel2.setText(bet_amount+"");
			bet_button.setEnabled(false);
			bet_input.setEnabled(false);
			
			int turncount = player.deckSize();
			for(deck d : decks) {
				d.deck_panel.setVisible(true);
			}
			if(turncount==2 && betdouble) {//if its first 2 cards in hand and bet can be doubled
				optionAvailable(3);
			}
			else {
				optionAvailable(2);
			}
		} catch(java.lang.NumberFormatException nfe) {
			bet_setlabel1.setText("invalid bet");
			bet_setlabel2.setText("try again");
		} catch(java.io.FileNotFoundException fnfe) {
			bet_setlabel1.setText("You bet higher than");
			bet_setlabel2.setText("what you have");
		}
	}
	class optionclass implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==option_button[0]) {
				action_action.setText("You Hit");
				hit(player);
				option_button[2].setEnabled(false);
			}else
			if(e.getSource()==option_button[1]) {
				action_action.setText("You Stand");
				stand(player);
			}else
			if(e.getSource()==option_button[2]) {
				action_action.setText("You Doubled down");
				doubleDown(player);
			}else
			if(e.getSource()==option_button[3]) {
				action_action.setText("You Split");
				option_button[3].setEnabled(false);
			}
		}
	}
	
	//determines winner
	public void roundWinner(String q, deck d) {
		int s = -10;
		switch(q) {//if empty string passed. determines winner by their hand value
		case"pbust":s = 2; action_action.setText(d.getName()+" busted");break;
		case"dbust":s = 3; action_action.setText(d.getName()+" busted");break;
		case"skip": s = 4; break;//will be used with the split option
		default: s = Integer.compare(player.getHandValue(), dealer.getHandValue());break;
		}
		switch(s) {
		case -1: 
			action_labels[0].setText("Dealer won!");
			action_labels[1].setText("You lost ");
			action_labels[2].setText(""+bet_amount);
			money-=bet_amount;
			break;

		case 0: 
			action_labels[0].setText("Push!");
			action_labels[1].setText("returned bet ");
			action_labels[2].setText("");
			break;

		case 1: 
			action_labels[0].setText("You won!");
			action_labels[1].setText("You earned");
			action_labels[2].setText(""+bet_amount*1.5);
			money+=bet_amount*1.5;

			break;

		case 2:
			action_labels[0].setText("You bust!");
			action_labels[1].setText("You lost");
			action_labels[2].setText(""+bet_amount);
			money-=bet_amount;

			break;

		case 3: 
			action_labels[0].setText("Dealer bust!");
			action_labels[1].setText("You won");
			action_labels[2].setText(""+bet_amount*1.5);
			money+=bet_amount*1.5;break;

		}
		cards_panel.repaint();
		action_panel.repaint();
		money_amount.setText(""+money);
		//end of game
		if(money==0) {gameOver();}
		//new round
		action_playButton.setEnabled(true);
		action_playButton.setText("another Round");
	}
	
	//option methods
		public void hit(deck deck) {
			deck.draw(gameDeck);
			cards_panel.repaint();
			if(deck.getHandValue()>21) {
				roundWinner("pbust", deck);
				for(int i = 0; i<option_button.length; i++) {
					option_button[i].setEnabled(false);
				}
			}
		}
		public void stand(deck deck) {
			if(deck.getHandValue()>21) {
				roundWinner("pbust", deck);
				for(int i = 0; i<option_button.length; i++) {
					option_button[i].setEnabled(false);
				}
			}else {
				while(dealer.getHandValue()<17) {
					dealer.draw(gameDeck);
				}
				for(int i = 0; i<option_button.length; i++) {
					option_button[i].setEnabled(false);
				}
				cards_panel.repaint();
				if(dealer.getHandValue()>21) {
					roundWinner("dbust", dealer);
				}
				if(dealer.getHandValue()<=21 && player.getHandValue()<=21) {
					roundWinner("", player);
				}
			}
		}
		public void doubleDown(deck deck) {
			bet_amount*=2;
			bet_setlabel1.setText("Bet Doubled -");
			bet_setlabel2.setText(bet_amount+"");
			deck.draw(gameDeck);
			cards_panel.repaint();
			stand(deck);
		}
		public void gameOver() {
			contents.removeAll();
			JPanel gameover = new JPanel();
			gameover.setLayout(new GridLayout(3,1));
			JLabel game= new JLabel("Game Over!");
			JLabel over = new JLabel("You Lost All your CHIPS!");
			game.setFont(new Font("Ink Free", Font.BOLD, 200));
			over.setFont(new Font("Ink Free", Font.BOLD, 120));
			game.setForeground(colorFont);
			over.setForeground(colorFont);
			game.setBackground(null);
			over.setBackground(null);
			gameover.setBackground(Color.BLACK);
			exit.setText("Click to EXIT Game");
			exit.setBackground(colorLabel);
			exit.setForeground(colorFont);
			exit.setFont(new Font("Ink Free", Font.ITALIC, 100));
			gameover.add(game, BorderLayout.NORTH);
			gameover.add(over,BorderLayout.CENTER);
			gameover.add(exit, BorderLayout.SOUTH);
			
			contents.add(gameover, BorderLayout.CENTER);
			
			pack();
		}
		
	//set Universal font
	private void setFonts() {
		UIManager.put("Label.font", fontlabels);
	}
	
	public static void main(String[] args) {
		new blackJack_GUI_Version();
	}
}
