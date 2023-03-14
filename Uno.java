import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Uno {
    public static void main(String[] args){
        ArrayList<UnoCard> comDeck = new ArrayList<>();
        ArrayList<UnoCard> playerDeck = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        UnoCard faceUpCard = UnoCard.random();
        for (int i = 0; i < 7; i++){
            comDeck.add(UnoCard.random());
            playerDeck.add(UnoCard.random());
        }
        while (comDeck.size() > 0 & playerDeck.size() > 0){
            boolean playerTurn = true;
            // Player turn
            while (playerTurn){
                System.out.println();
                System.out.println("Face Up Card: " + faceUpCard);
                System.out.println("Computer's hand: " + comDeck.size() + " Cards");
                System.out.println("Your Hand:");
                for (int i = 0; i<playerDeck.size(); i++){
                    System.out.println(i + " " + playerDeck.get(i));
                }
                System.out.println("Enter a card index to play the card, or type 'D' to draw a new card");
                String userInput = input.nextLine();

                if (userInput.equals("D")){
                    playerDeck.add(UnoCard.random());
                    continue;
                }

                int cardIndex = Integer.parseInt(userInput);
                UnoCard playCard;

                //Checks if card exists
                try{
                    playCard = playerDeck.get(cardIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Not A Card");
                    continue;
                }

                //Checks if card can be played
                if (playCard.getCard().equals(faceUpCard.getCard()) | playCard.getColor().equals(faceUpCard.getColor()) | playCard.getColor().equals(UnoCard.Color.WILD)){
                    faceUpCard = playerDeck.remove(cardIndex);
                } else {
                    System.out.println("Card cannot be played");
                    continue;
                }
                
                // Change card color if color is wild
                while(faceUpCard.getColor().equals(UnoCard.Color.WILD)){
                    System.out.println("R for red, G for green, Y for yellow, B for blue");
                    System.out.println("Enter new color");
                    String colorID = input.nextLine();
                    switch (colorID){
                        case "R": faceUpCard.setColor(UnoCard.Color.RED);
                            break;
                        case "G": faceUpCard.setColor(UnoCard.Color.GREEN);
                            break;
                        case "Y": faceUpCard.setColor(UnoCard.Color.YELLOW);
                            break;
                        case "B": faceUpCard.setColor(UnoCard.Color.BLUE);
                            break;
                        default: System.out.println("Invalid Color");
                            break;
                    }
                }

                //card special actions
                switch(faceUpCard.getCard()){
                    case REVERSE: break;
                    case SKIP: break;
                    case DRAWFOUR: {
                        comDeck.add(UnoCard.random());
                        comDeck.add(UnoCard.random());
                        /* FALLTHROUGH */
                    }
                    case DRAWTWO: {
                        comDeck.add(UnoCard.random());
                        comDeck.add(UnoCard.random());
                        /* FALLTHROUGH */
                    }
                    default: {
                        playerTurn = false; // Ends player turn
                        break;
                    }
                }      
            }
            //Computer turn
            while (!playerTurn){
                System.out.println();
                boolean playedCard = false;
                //Tries to play a card
                for (int i = 0; i < comDeck.size(); i++){
                    UnoCard playCard = comDeck.get(i);
                    if (playCard.getCard().equals(faceUpCard.getCard()) | playCard.getColor().equals(faceUpCard.getColor()) | playCard.getColor().equals(UnoCard.Color.WILD)){
                        faceUpCard = comDeck.remove(i);
                        System.out.println("Computer played " + faceUpCard);
                        playedCard = true;
                        break;
                    }
                }
                //Draws a card if none are available
                if (!playedCard){
                    comDeck.add(UnoCard.random());
                    System.out.println("Computer drew a card!");
                    continue;
                }

                //changes color to most frequent color in hand if it played a wild card
                if (faceUpCard.getColor().equals(UnoCard.Color.WILD)){
                    int R = 0, G = 0, B = 0, Y = 0;
                    // finds most frequent color
                    for (UnoCard card : comDeck){
                        switch (card.getColor()){
                            case RED: R++;
                                break;
                            case BLUE: B++;
                                break;
                            case GREEN: G++;
                                break;
                            case YELLOW: Y++;
                                break;
                            case WILD: break;
                        }
                    }
                    int max = Math.max(Math.max(Math.max(R,G),B),Y);
                    if (max == R){
                        faceUpCard.setColor(UnoCard.Color.RED);
                    } else if (max == B){
                        faceUpCard.setColor(UnoCard.Color.BLUE);
                    } else if (max == G){
                        faceUpCard.setColor(UnoCard.Color.GREEN);
                    } else {
                        faceUpCard.setColor(UnoCard.Color.YELLOW);
                    }
                }
                //card special actions
                switch(faceUpCard.getCard()){
                    case REVERSE: break;
                    case SKIP: break;
                    case DRAWFOUR: {
                        playerDeck.add(UnoCard.random());
                        playerDeck.add(UnoCard.random());
                        /* FALLTHROUGH */
                    }
                    case DRAWTWO: {
                        playerDeck.add(UnoCard.random());
                        playerDeck.add(UnoCard.random());
                        /* FALLTHROUGH */
                    }
                    default: {
                        playerTurn = true; // Ends player turn
                        break;
                    }
                }

            }
        }
        System.out.println((playerDeck.size() == 0) ? "The Player Won!" : "The Evil AI Won!");
        input.close();
    }
}
