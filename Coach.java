public class Coach {
    private String name;
    private String title;
    private String unit;
    private int yearsExperience;

    // Constructor
    public Coach(String name, String title, String unit, int yearsExperience) {
        this.name = name;
        this.title = title;
        this.unit = unit;
        this.yearsExperience = yearsExperience;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getUnit() {
        return unit;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    // Helper method to display coach info neatly
    public String getFormattedDetails() {
        return "Name: " + name + "\n" +
               "Position/Title: " + title + "\n" +
               "Unit/Category: " + unit + "\n" +
               "Coaching Experience: " + yearsExperience + " years\n";
    }
}
