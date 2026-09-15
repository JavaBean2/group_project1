public class TestPlayer {
    public static void main(String[] args) {
        Player p = new Player("Andrew Luck", "Quarterback", 12, "Offense");
        System.out.println(p.getFormattedDetails());

        System.out.println("Formatted Details:");
        System.out.println(p.getFormattedDetails());

        System.out.println("toString():");
        System.out.println(p.toString());

        System.out.println("Random Rating:");
        System.out.println(p.getPlayerRating());
    }
}
