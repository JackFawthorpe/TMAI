package com.backend.Domain.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardBuilderTest {

    CardBuilder builder;

    @BeforeEach
    void resetBuilder() {
        builder = new CardBuilder("Default Builder");
    }

    @Test
    void createBuilder_WithTitle_SetsTitle() {
        CardBuilder builder = new CardBuilder("Test Title");
        Card result = builder.build();
        assertEquals("Test Title", result.getTitle());
    }

    @Test
    void createBuilder_NoTitle_DefaultTitle() {
        CardBuilder builder = new CardBuilder(null);
        Card result = builder.build();
        assertEquals("No Title Provided", result.getTitle());
    }

    @Test
    void createBuilder_EmptyString_DefaultTitle() {
        CardBuilder builder = new CardBuilder("");
        Card result = builder.build();
        assertEquals("No Title Provided", result.getTitle());
    }

    @Test
    void withCost_ValidCost_SetsCost() {
        builder.withCost("55");
        Card result = builder.build();
        assertEquals(55, result.getCost());
    }

    @Test
    void withCost_EmptyString_FallsToNegativeOne() {
        builder.withCost("");
        Card result = builder.build();
        assertEquals(-1, result.getCost());
    }

    @Test
    void withCost_NullCost_FallsToNegativeOne() {
        builder.withCost(null);
        Card result = builder.build();
        assertEquals(-1, result.getCost());
    }

    @Test
    void withCost_AWord_FallsToNegativeOne() {
        builder.withCost("Test");
        Card result = builder.build();
        assertEquals(-1, result.getCost());
    }

    @Test
    void withCost_AFloat_FallsToNegativeOne() {
        builder.withCost("5.6");
        Card result = builder.build();
        assertEquals(-1, result.getCost());
    }
}
