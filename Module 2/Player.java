public class Player{
    // Player's full name (same idea as Nick's)
    private String name;

    // Postion (e.g Quarterback) (same idea as Nick's)
    private String position;

    // Jersey number (same idea as Nick's)
    private int jerseyNumber;

    // Offense, Defense, or Special Teams(new field, not in Nick's verison)
    private String unit;

    // Builds a new player
    public Player(String name, String position, int jerseyNumber, String unit) {
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.unit = unit;
    } // <- closes the constructor
    
    // Returns the name
    public String getName(){
        return name;
    }
    // Returns the position
    public String getPosition(){
        return position;
    }
    // Returns the jersey number
    public int getJerseyNumber(){
        return jerseyNumber;
    }
    // Returns the unit
    public String getUnit(){
        return unit;
    }
    // Formats player info for display
    public String getFormattedDetails(){
        return "Name: " + name + "\n" +
        "Position: " + position + "\n"+
        "Jersey Number: " + jerseyNumber + "\n" +
        "Unit: " + unit + "\n";
    }
    
} // <- closes the class