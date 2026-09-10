import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CoachesGUI extends JPanel {

    private List<Coach> coachList;

    public CoachesGUI() {
        coachList = new ArrayList<>();
        loadCoachingStaff();

        // Setup Panel Layout
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Section
        JLabel titleLabel = new JLabel("INDIANAPOLIS COLTS — 2026 COACHES DIRECTORY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Center Button Grid
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnViewAll = new JButton("1. View All Coaches");
        JButton btnViewByUnit = new JButton("2. Filter Coaches by Unit");
        JButton btnSearch = new JButton("3. Search Coach");
        JButton btnDetails = new JButton("4. View Coach Profile");
        JButton btnBack = new JButton("5. Back to Main Menu");

        buttonPanel.add(btnViewAll);
        buttonPanel.add(btnViewByUnit);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDetails);
        buttonPanel.add(btnBack);

        add(buttonPanel, BorderLayout.CENTER);

        // Button Event Listeners
        btnViewAll.addActionListener(e -> viewAllCoaches());
        btnViewByUnit.addActionListener(e -> viewCoachesByUnit());
        btnSearch.addActionListener(e -> searchCoach());
        btnDetails.addActionListener(e -> viewCoachDetails());
        btnBack.addActionListener(e -> backToMainMenu());
    }

    // Pre-populates the exact 18 Indianapolis Colts Coaches
    private void loadCoachingStaff() {
        // Head Coach
        coachList.add(new Coach("Shane Steichen", "Head Coach", "Head Coach", 20));

        // Coordinators
        coachList.add(new Coach("Lou Anarumo", "Defensive Coordinator", "Defense", 38));
        coachList.add(new Coach("Jim Bob Cooter", "Offensive Coordinator", "Offense", 20));
        coachList.add(new Coach("Brian Mason", "Special Teams Coordinator", "Special Teams", 20));

        // Assistants & Position Coaches
        coachList.add(new Coach("James Bettcher", "Linebackers Coach", "Defense", 23));
        coachList.add(new Coach("Joe Hastings", "Sr. Assistant Special Teams Coach", "Special Teams", 11));
        coachList.add(new Coach("Jerome Henderson", "Defensive Backs Coach", "Defense", 21));
        coachList.add(new Coach("Chris Hewitt", "Pass Game Coordinator / Secondary Coach", "Defense", 24));
        coachList.add(new Coach("Marion Hobby", "Defensive Line Coach", "Defense", 32));
        coachList.add(new Coach("Kalon Humphries", "Assistant Defensive Line Coach", "Defense", 12));
        coachList.add(new Coach("Cato June", "Assistant Linebackers Coach", "Defense", 16));
        coachList.add(new Coach("Tom Manning", "Tight Ends Coach", "Offense", 21));
        coachList.add(new Coach("DeAndre Smith", "Running Backs Coach", "Offense", 27));
        coachList.add(new Coach("Tony Sparano Jr.", "Offensive Line Coach", "Offense", 17));
        coachList.add(new Coach("Alex Tanney", "Passing Game Coordinator", "Offense", 6));
        coachList.add(new Coach("Cam Turner", "Quarterbacks Coach", "Offense", 17));
        coachList.add(new Coach("Chris Watt", "Assistant Offensive Line Coach", "Offense", 7));
        coachList.add(new Coach("Reggie Wayne", "Wide Receivers Coach", "Offense", 5));
    }

    // 1. View All Coaches
    private void viewAllCoaches() {
        StringBuilder builder = new StringBuilder();
        for (Coach c : coachList) {
            builder.append("• ").append(c.getName()).append(" — ").append(c.getTitle())
                   .append(" (").append(c.getYearsExperience()).append(" yrs)\n");
        }
        showScrollableDialog(builder.toString(), "All Coaches (" + coachList.size() + " Total)");
    }

    // 2. View Coaches by Unit
    private void viewCoachesByUnit() {
        String[] units = {"Head Coach", "Offense", "Defense", "Special Teams"};
        String selectedUnit = (String) JOptionPane.showInputDialog(
                this,
                "Select a Coaching Unit:",
                "Filter by Unit",
                JOptionPane.QUESTION_MESSAGE,
                null,
                units,
                units[0]
        );

        if (selectedUnit != null) {
            StringBuilder builder = new StringBuilder();
            int count = 0;
            for (Coach c : coachList) {
                if (c.getUnit().equalsIgnoreCase(selectedUnit)) {
                    builder.append("• ").append(c.getName()).append(" — ").append(c.getTitle())
                           .append(" (").append(c.getYearsExperience()).append(" yrs)\n");
                    count++;
                }
            }
            if (count == 0) {
                builder.append("No coaches found in this unit.");
            }
            showScrollableDialog(builder.toString(), "Coaches in Unit: " + selectedUnit + " (" + count + ")");
        }
    }

    // 3. Search for a Coach
    private void searchCoach() {
        String query = JOptionPane.showInputDialog(
                this, 
                "Type any name or title keyword (e.g., 'Wayne', 'Linebackers', 'Offensive'):", 
                "Search Directory", 
                JOptionPane.QUESTION_MESSAGE
        );
        if (query != null && !query.trim().isEmpty()) {
            StringBuilder builder = new StringBuilder();
            boolean found = false;

            for (Coach c : coachList) {
                if (c.getName().toLowerCase().contains(query.trim().toLowerCase()) ||
                    c.getTitle().toLowerCase().contains(query.trim().toLowerCase())) {
                    builder.append(c.getFormattedDetails()).append("\n-----------------------\n");
                    found = true;
                }
            }

            if (found) {
                showScrollableDialog(builder.toString(), "Search Results for: \"" + query + "\"");
            } else {
                JOptionPane.showMessageDialog(this, "No coach found matching keyword: \"" + query + "\"", "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // 4. View Coach Details
    private void viewCoachDetails() {
        String[] names = new String[coachList.size()];
        for (int i = 0; i < coachList.size(); i++) {
            names[i] = coachList.get(i).getName();
        }

        String selectedName = (String) JOptionPane.showInputDialog(
                this,
                "Select a coach from the dropdown list to view their full profile card:",
                "Individual Profile Card",
                JOptionPane.QUESTION_MESSAGE,
                null,
                names,
                names[0]
        );

        if (selectedName != null) {
            for (Coach c : coachList) {
                if (c.getName().equals(selectedName)) {
                    JOptionPane.showMessageDialog(this, c.getFormattedDetails(), "Coach Profile: " + c.getName(), JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }

    // 5. Back to Main Menu
    private void backToMainMenu() {
        JOptionPane.showMessageDialog(
                this,
                "Returning to Main Menu... (Main Menu integration ready)",
                "Navigation",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Helper method to show long text neatly inside a scrollable box
    private void showScrollableDialog(String text, String title) {
        if (text == null || text.trim().isEmpty()) {
            text = "No details to display.";
        }

        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);

        // Fix: Explicit black text on white background prevents dark mode OS text blending
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(480, 280));

        JOptionPane.showMessageDialog(this, scrollPane, title, JOptionPane.PLAIN_MESSAGE);
    }

    // Independent Test Runner (Allows running file without Person 1's code)
    public static void main(String[] args) {
        JFrame frame = new JFrame("Indianapolis Colts - Coaches Module");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(new CoachesGUI());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
