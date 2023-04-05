package controllers;

import application.CardView;
import models.Card;

public class CardController {
	private CardView cardView;
	private Card card;
	
	public CardController(Card card) {
		this.card = card;
		cardView = new CardView(card);
	}

	public CardView getCardView() {
		return cardView;
	}

	public Card getCard() {
		return card;
	}
	
	public void flipCard() {
		card.flip();
	}
	
}
