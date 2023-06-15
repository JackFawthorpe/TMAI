package com.backend.MVC;

import com.backend.BLL.CardService;
import com.backend.Domain.Card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller responsible for interaction with cards
 */
@RestController
public class CardController {

    @Autowired
    CardService cardService;

    /**
     * Sends back the list of cards that are to be used within the application
     * @return The cards that are being used by the application
     */
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }
}