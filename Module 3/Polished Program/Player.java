// Based on Nick Molina's original Player concept (name, postion, jerseyNumber fields)
// Written by: Sarah Ayres-Removed package/inheritance, dropped id and stats filed, added "unit" field
public class Player{
    // Player's full name (same idea as Nick's)
    private String name;
    // Postion (e.g Quarterback) (same idea as Nick's)
    private String position;
    // Jersey number (same idea as Nick's)
    private int jerseyNumber;
    // Offense, Defense, or Special Teams(new field, not in Nick's verison)
    private String unit;
    // Written by Sarah Ayres- Changed constructor parameters to match fields, removed super() call
    // Builds a new player
    public Player(String name, String position, int jerseyNumber, String unit) {
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.unit = unit;
    }
    // Written by: Sarah Ayres- added getName() directly since Person superclass was removed
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
    // Written by: Sarah Ayres- New Method, no equivalent in original (supports the added unit field)
    // Returns the unit
    public String getUnit(){
        return unit;
    }
    // Written by: Sarah Ayres _ Replaced toString() override with a new getFormattedDetails() method, formats unit instead of stats
    // Formats player info for display
    public String getFormattedDetails(){
        return "Name: " + name + "\n" +
        "Position: " + position + "\n"+
        "Jersey Number: " + jerseyNumber + "\n" +
        "Unit: " + unit + "\n";
    }
    
}