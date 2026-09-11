import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
// Players section panel
public class PlayersGUI extends JPanel{
    // Holds all the players
    private List<Player> playerList;

    //code to run when "back to main menu" is clicked
    private Runnable onBack;

    // Builds the players panel
    public PlayersGUI(Runnable onBack) {
        this.onBack = onBack;

        playerList = new ArrayList<>();
        loadPlayers();

        // Set up panel Layout
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title section
        JLabel titleLabel = new JLabel("INDIANAPOLIS COLTS - 2026 PLAYERS DIRECTORY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Center button Grid
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnViewAll = new JButton("1. View All Players");
        JButton btnViewByUnit = new JButton("2. Filter Players by Unit");
        JButton btnSearch = new JButton("3. Search Player");
        JButton btnDetails = new JButton("4. View Player Profile");
        JButton btnBack = new JButton("5. Back to Main Menu");

        buttonPanel.add(btnViewAll);
        buttonPanel.add(btnViewByUnit);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDetails);
        buttonPanel.add(btnBack);

        add(buttonPanel, BorderLayout.CENTER);

        // Button event Listeners
        btnViewAll.addActionListener(e -> viewAllPlayers());
        btnViewByUnit.addActionListener(e -> viewPlayersByUnit());
        btnSearch.addActionListener(e -> searchPlayer());
        btnDetails.addActionListener(e -> viewPlayerDetails());
        btnBack.addActionListener(e -> backToMainMenu());
    }
        // Loads a sample of the current Colts roster
    private void loadPlayers() {
        // Offense
        playerList.add(new Player("Daniel Jones", "Quarterback", 17, "Offense"));
        playerList.add(new Player("Anthony Richardson", "Quarterback", 5, "Offense"));
        playerList.add(new Player("Jonathan Taylor", "Running Back", 28, "Offense"));
        playerList.add(new Player("DJ Giddens", "Running Back", 21, "Offense"));
        playerList.add(new Player("Josh Downs", "Wide Receiver", 2, "Offense"));
        playerList.add(new Player("Alec Pierce", "Wide Receiver", 14, "Offense"));
        playerList.add(new Player("Tyler Warren", "Tight End", 84, "Offense"));
        playerList.add(new Player("Quenton Nelson", "Offensive Guard", 56, "Offense"));

        // Defense
        playerList.add(new Player("DeForest Buckner", "Defensive Tackle", 99, "Defense"));
        playerList.add(new Player("Grover Stewart", "Defensive Tackle", 90, "Defense"));
        playerList.add(new Player("Laiatu Latu", "Defensive End", 97, "Defense"));
        playerList.add(new Player("Ahmad Gardner", "Defensive Back", 1, "Defense"));
        playerList.add(new Player("Camryn Bynum", "Defensive Back", 0, "Defense"));
        playerList.add(new Player("Charvarius Ward", "Defensive Back", 7, "Defense"));
        playerList.add(new Player("CJ Allen", "Linebacker", 53, "Defense"));
        playerList.add(new Player("Jaylon Carlies", "Linebacker", 57, "Defense"));

        // Special Teams
        playerList.add(new Player("Spencer Shrader", "Kicker", 3, "Special Teams"));
        playerList.add(new Player("Rigoberto Sanchez", "Punter", 8, "Special Teams"));
    }
    // Shows every player in the list
    private void viewAllPlayers(){
        StringBuilder builder = new StringBuilder();
        for (Player p : playerList){
            builder.append("•" ).append(p.getName()).append(" — ").append(p.getPosition()).append(" (#").append(p.getJerseyNumber()).append(")\n");
        }
        showScrollableDialog(builder.toString(), "All Players(" + playerList.size() + " Total)");
    }
        // Filters players by unit
    private void viewPlayersByUnit() {
        String[] units = {"Offense", "Defense", "Special Teams"};
        String selectedUnit = (String) JOptionPane.showInputDialog(
                this,
                "Select a Unit:",
                "Filter by Unit",
                JOptionPane.QUESTION_MESSAGE,
                null,
                units,
                units[0]
        );

        if (selectedUnit != null) {
            StringBuilder builder = new StringBuilder();
            int count = 0;
            for (Player p : playerList) {
                if (p.getUnit().equalsIgnoreCase(selectedUnit)) {
                    builder.append("• ").append(p.getName()).append(" — ").append(p.getPosition())
                           .append(" (#").append(p.getJerseyNumber()).append(")\n");
                    count++;
                }
            }
            if (count == 0) {
                builder.append("No players found in this unit.");
            }
            showScrollableDialog(builder.toString(), "Players in Unit: " + selectedUnit + " (" + count + ")");
        }
    }
        // Searches players by name or position keyword
    private void searchPlayer() {
        String query = JOptionPane.showInputDialog(
                this,
                "Type any name or position keyword (e.g., 'Taylor', 'Quarterback', 'Wide'):",
                "Search Roster",
                JOptionPane.QUESTION_MESSAGE
        );
        if (query != null && !query.trim().isEmpty()) {
            StringBuilder builder = new StringBuilder();
            boolean found = false;

            for (Player p : playerList) {
                if (p.getName().toLowerCase().contains(query.trim().toLowerCase()) ||
                    p.getPosition().toLowerCase().contains(query.trim().toLowerCase())) {
                    builder.append(p.getFormattedDetails()).append("\n-----------------------\n");
                    found = true;
                }
            }

            if (found) {
                showScrollableDialog(builder.toString(), "Search Results for: \"" + query + "\"");
            } else {
                JOptionPane.showMessageDialog(this, "No player found matching keyword: \"" + query + "\"", "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
        // Shows one player's full profile
    private void viewPlayerDetails() {
        String[] names = new String[playerList.size()];
        for (int i = 0; i < playerList.size(); i++) {
            names[i] = playerList.get(i).getName();
        }

        String selectedName = (String) JOptionPane.showInputDialog(
                this,
                "Select a player from the dropdown list to view their full profile card:",
                "Individual Profile Card",
                JOptionPane.QUESTION_MESSAGE,
                null,
                names,
                names[0]
        );

        if (selectedName != null) {
            for (Player p : playerList) {
                if (p.getName().equals(selectedName)) {
                    JOptionPane.showMessageDialog(this, p.getFormattedDetails(), "Player Profile: " + p.getName(), JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }
    private void backToMainMenu(){
        onBack.run();
    }
        // Shows long text neatly inside a scrollable box
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

        // Explicit colors prevent dark mode text blending
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(480, 280));

        JOptionPane.showMessageDialog(this, scrollPane, title, JOptionPane.PLAIN_MESSAGE);
    }
} // <- closes the class