package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//public class CardGameWeek6 {

class Card {
    private int value;
    private String name;

    // Constructor for Card class
    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    // Getter for card value
    public int getValue() {
        return value;
    }

    // Getter for card name
    public String getName() {
        return name;
    }

    // Method to describe a card
    public void describe() {
        System.out.println(name);
    }
}

class Deck {
    private List<Card> cards = new ArrayList<>();

    // Constructor for Deck class, populates the deck with standard 52 cards
    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int value = 2; value <= 14; value++) {
                String name;
                if (value <= 10) {
                    name = value + " of " + suit;
                } else {
                    String[] faceCards = {"Jack", "Queen", "King", "Ace"};
                    name = faceCards[value - 11] + " of " + suit;
                }
                cards.add(new Card(value, name));
            }
        }
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Method to draw a card from the deck
    public Card draw() {
        if (cards.isEmpty()) {
            return null; // Deck is empty
        }
        return cards.remove(0);
    }
}

class Player {
    private List<Card> hand = new ArrayList<>();
    private int score = 0;
    private String name;

    // Constructor for Player class
    public Player(String name) {
        this.name = name;
    }

    // Getter for player score
    public int getScore() {
        return score;
    }

    // Method to increment player score
    public void incrementScore() {
        score++;
    }

    // Method to describe a player and their hand
    public void describe() {
        System.out.println(name + "'s hand:");
        for (Card card : hand) {
            card.describe();
        }
    }

    // Method to flip a card from the player's hand
    public Card flip() {
        if (hand.isEmpty()) {
            return null; // Hand is empty
        }
        return hand.remove(0);
    }

    // Method to draw a card from the deck and add to the player's hand
    public void draw(Deck deck) {
        Card card = deck.draw();
        if (card != null) {
            hand.add(card);
        }
    }
}

public class CardgameWeek6 {
    public static void main(String[] args) {
        // Instantiate a Deck and two Players, and call the shuffle method on the deck
        Deck deck = new Deck();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        deck.shuffle();

        // Draw 52 cards using a traditional for loop
        for (int i = 0; i < 52; i++) {
            player1.draw(deck);
            player2.draw(deck);
        }

        // Flip 26 cards for each player using a traditional for loop
        for (int i = 0; i < 26; i++) {
            Card card1 = player1.flip();
            Card card2 = player2.flip();

            System.out.println("Round " + (i + 1) + ":");
            System.out.println("Player 1 flips: " + card1.getName());
            System.out.println("Player 2 flips: " + card2.getName());

            // Compare the value of each card and update scores
            if (card1.getValue() > card2.getValue()) {
                player1.incrementScore();
                System.out.println("Player 1 receives a point!");
            } else if (card2.getValue() > card1.getValue()) {
                player2.incrementScore();
                System.out.println("Player 2 receives a point!");
            } else {
                System.out.println("It's a tie! No point awarded.");
            }

            // Print the updated scores
            System.out.println("Player 1 Score: " + player1.getScore());
            System.out.println("Player 2 Score: " + player2.getScore());
            System.out.println();
        }

        // Compare the final score and determine the winner
        System.out.println("Final Scores:");
        System.out.println("Player 1 Score: " + player1.getScore());
        System.out.println("Player 2 Score: " + player2.getScore());

        if (player1.getScore() > player2.getScore()) {
            System.out.println("Player 1 wins!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}

