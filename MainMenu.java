import javax.swing.*;
import java.awt.*;

// Main app window, hosts all section panels
public class MainMenu extends JFrame {

    // Controls which panel is showing
    private CardLayout cardLayout;

    // Holds all the section panels
    private JPanel cardContainer;

    // Builds the main window
    public MainMenu() {
        setTitle("Indianapolis Colts - Main Menu");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up card switching
        cardLayout = new CardLayout();
        cardContainer = new JPanel(cardLayout);

        // Build the welcome/home screen
        JPanel menuHomePanel = new JPanel();
        menuHomePanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("INDIANAPOLIS COLTS APP — SELECT A SECTION ABOVE",SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        // Load and add the team logo
        ImageIcon logoIcon = new ImageIcon("colts_logo.png");
        JLabel logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        
        // Stack logo + text close together
        JPanel logoTextPanel = new JPanel();
        logoTextPanel.setLayout(new BoxLayout(logoTextPanel, BoxLayout.Y_AXIS));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoTextPanel.add(Box.createVerticalGlue());
        logoTextPanel.add(logoLabel);
        logoTextPanel.add(Box.createVerticalGlue());
        logoTextPanel.add(welcomeLabel);
        logoTextPanel.add(Box.createVerticalGlue());

        menuHomePanel.add(logoTextPanel, BorderLayout.CENTER);
        // Register the home screen under "Menu"
        cardContainer.add(menuHomePanel, "Menu");

        // Create each section's panel
        CoachesGUI coachesPanel = new CoachesGUI(() -> cardLayout.show(cardContainer, "Menu"));
        PlayersGUI playersPanel = new PlayersGUI(() -> cardLayout.show(cardContainer, "Menu"));
        ColtsStaffDirectory staffPanel = new ColtsStaffDirectory(() -> cardLayout.show(cardContainer, "Menu"));

        // Register them with the card container under a name
        cardContainer.add(coachesPanel, "COACHES");
        cardContainer.add(playersPanel, "PLAYERS");
        cardContainer.add(staffPanel, "STAFF");
        cardLayout.show(cardContainer, "Menu");

        // Put the card container into the window
        add(cardContainer);

        // Build the menu bar of section buttons
        JPanel menuPanel = new JPanel();
        JButton coachesButton = new JButton("Coaches");
        JButton playersButton = new JButton("Players");
        JButton staffButton = new JButton("Staff");

        // Switch cards when clicked
        coachesButton.addActionListener(e -> cardLayout.show(cardContainer, "COACHES"));
        playersButton.addActionListener(e -> cardLayout.show(cardContainer, "PLAYERS"));
        staffButton.addActionListener(e -> cardLayout.show(cardContainer, "STAFF"));

        menuPanel.add(coachesButton);
        menuPanel.add(playersButton);
        menuPanel.add(staffButton);

        //Put the menu at the top of the window
        add(menuPanel, BorderLayout.NORTH);


    }  // <- closes the constructor

    // Entry point, starts the app
    public static void main(String[] args){
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }
}  // <- closes the class

