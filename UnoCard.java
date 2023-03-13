import java.util.Random;
public class UnoCard {
    public enum Color {
        YELLOW("YELLOW"),
        GREEN("GREEN"),
        BLUE("BLUE"),
        RED("RED"),
        WILD("WILD");

        private final String name;

        private Color(String name){
            this.name = name;
        }

        public String toString(){
            return this.name;
        }

        static Color random(){
            int rand = new Random().nextInt(5);
            return Color.values()[rand];
        }

        static Color randomNonWild(){
            int rand = new Random().nextInt(4);
            return Color.values()[rand];
        }
    }
    public enum Card {
        ZERO("ZERO"),
        ONE("ONE"),
        TWO("TWO"),
        THREE("THREE"),
        FOUR("FOUR"),
        FIVE("FIVE"),
        SIX("SIX"),
        SEVEN("SEVEN"),
        EIGHT("EIGHT"),
        NINE("NINE"),
        SKIP("SKIP"),
        DRAWTWO("DRAW TWO"),
        REVERSE("REVERSE"),
        WILD("\b"), //formatting
        DRAWFOUR("DRAW FOUR");

        private final String name;

        private Card(String name){
            this.name = name;
        }

        public String toString(){
            return this.name;
        }

        static Card random(){
            int rand = new Random().nextInt(15);
            return Card.values()[rand];
        }

        static Card randomNonWild(){
            int rand = new Random().nextInt(13);
            return Card.values()[rand];
        }
    }
    private Card cardtype;
    private Color colortype;

    public UnoCard(Card cardtype, Color colortype) {
        this.cardtype = cardtype;
        this.colortype = colortype;
    }
    

    public Card getCard(){
        return this.cardtype;
    }

    public Color getColor(){
        return this.colortype;
    }

    public void setColor(Color color){
        this.colortype = color;
    }

    public String toString(){
        return this.colortype + " " + this.cardtype + " CARD";
    }

    public static UnoCard random(){
        Color color = Color.random();
        Card card;
        if (color.equals(Color.WILD)){
            int rand = new Random().nextInt(2);
            card = (rand == 1) ? Card.WILD : Card.DRAWFOUR;
        } else {
            card = Card.randomNonWild();
        }
        return new UnoCard(card,color);
    }
}
