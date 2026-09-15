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
    

    //--------Getters---------


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

        // ------------------ NEW FEATURES BELOW THIS LINE ------------------

    // 1. toString() method
    @Override
    public String toString() {
        return name + " (#" + jerseyNumber + ", " + position + ", " + unit + ")";
    }

    // 2. Setters (optional)
    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // 3. Optional: simple validation
    public void validate() {
        if (jerseyNumber <= 0) {
            throw new IllegalArgumentException("Jersey number must be positive.");
        }
    }

    // 4. Optional: fun rating method
    public int getPlayerRating() {
        return (int)(Math.random() * 100);
    }
    
} 