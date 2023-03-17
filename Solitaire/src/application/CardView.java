package application;

import models.Card;

public class CardView {
	private final Card card;
	
	CardView(Card card) {
		this.card = card;
	}

	public Card getCard() {
		return card;
	}

}
