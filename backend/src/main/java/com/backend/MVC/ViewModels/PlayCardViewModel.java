package com.backend.MVC.ViewModels;

/**
 * View model to pass the information for playing a card
 */
public class PlayCardViewModel {
    private int seatIndex;
    private int cardID;

    public int getSeatIndex() {
        return seatIndex;
    }

    public void setSeatIndex(int seatIndex) {
        this.seatIndex = seatIndex;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }
}
