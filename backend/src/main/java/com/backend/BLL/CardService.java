package com.backend.BLL;

import com.backend.Domain.Card.CardDAO;
import com.backend.Domain.Card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class for handling the business logic mapping the endpoints
 * to the Domain
 */
@Service
public class CardService {

    @Autowired
    CardDAO cardDAO;

    /**
     * Gets all the cards that are known to the system
     * @return The cards that are being used by the application
     */
    public List<Card> getAllCards() {
        return cardDAO.getAll();
    }
}
