import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ColtsStaffDirectory extends JFrame {
    private final DefaultTableModel model;
    private final JTable table;
    private final JTextField searchField;

    public ColtsStaffDirectory() {
        setTitle("Indianapolis Colts Staff Directory");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
            new String[]{"Name", "Position", "Department"}, 0
        );

        table = new JTable(model);
        table.setDefaultRenderer(Object.class, new DepartmentHeaderRenderer());
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        searchField = new JTextField(25);
        searchField.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent e) {
                        filter(sorter);
                    }

                    public void removeUpdate(javax.swing.event.DocumentEvent e) {
                        filter(sorter);
                    }

                    public void changedUpdate(javax.swing.event.DocumentEvent e) {
                        filter(sorter);
                    }
                }
        );

        JButton addButton = new JButton("Add Staff Member");
        JButton deleteButton = new JButton("Delete Selected");
        JButton clearButton = new JButton("Clear Search");
        JButton returnButton = new JButton("Return to Main Menu");

        addButton.addActionListener(e -> showAddDialog());
        clearButton.addActionListener(e -> searchField.setText(""));
        deleteButton.addActionListener(e -> deleteSelectedRow());
        returnButton.addActionListener(e -> returnToMainMenu());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(clearButton);
        topPanel.add(addButton);
        topPanel.add(deleteButton);
        topPanel.add(returnButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        initializeStaffSlots();
    }

    private void filter(TableRowSorter<DefaultTableModel> sorter) {
        String text = searchField.getText().trim();

        if (text.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + escapeRegex(text)));
        }
    }

    private String escapeRegex(String text) {
        return text.replace("\\", "\\\\")
                   .replace(".", "\\.")
                   .replace("*", "\\*")
                   .replace("+", "\\+")
                   .replace("?", "\\?")
                   .replace("^", "\\^")
                   .replace("$", "\\$")
                   .replace("(", "\\(")
                   .replace(")", "\\)")
                   .replace("[", "\\[")
                   .replace("]", "\\]")
                   .replace("{", "\\{")
                   .replace("}", "\\}")
                   .replace("|", "\\|");
    }

    private void showAddDialog() {
        JTextField nameField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField departmentField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Position:"));
        panel.add(positionField);
        panel.add(new JLabel("Department:"));
        panel.add(departmentField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Staff Member",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            if (nameField.getText().trim().isEmpty()
                    || positionField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Name and position are required.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            model.addRow(new Object[]{
                    nameField.getText().trim(),
                    positionField.getText().trim(),
                    departmentField.getText().trim()
            });
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Select a staff member first."
            );
            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        if (model.getValueAt(modelRow, 0).toString().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Department headers cannot be deleted."
            );
            return;
        }
        model.removeRow(modelRow);
    }

    /**
     * 
     */
    private void initializeStaffSlots() {
        Object[][] staffMembers = {
            {"Carlie Irsay-Gordon", "Owner & CEO", "Executives"},
            {"Casey Foyt", "Owner & Executive Vice President", "Executives"},
            {"Kalen Jackson", "Owner & Chief Brand Officer and President of the Indianapolis Colts Foundation", "Executives"},
            {"Chris Ballard", "General Manager", "Executives"},
            {"Pete Ward", "Chief Operating Officer", "Executives"},
            {"Dan Emerson", "Chief Legal Officer", "Executives"},
            {"Roger VanDerSnick", "Chief Commercial Officer", "Executives"},
            {"EJ Tolentino", "Chief Financial Officer", "Executives"},
            {"Elijah Hammans", "General Counsel", "Executives"},
            {"Ed Dodds", "Assistant General Manager", "Player Personnel"},
            {"Kevin Rogers", "Director of Player Personnel", "Player Personnel"},
            {"Jon Shaw", "Director of Pro Personnel", "Player Personnel"},
            {"Matt Terpening", "Director of College Scouting", "Player Personnel"},
            {"Jamie Moore", "Assistant Director of College Scouting", "Player Personnel"},
            {"Todd Vasvari", "Senior Player Personnel Scout", "Player Personnel"},
            {"Joey Elliott", "Assistant Director of Pro Scouting", "Player Personnel"},
            {"Anthony Coughlan", "Southeast Area Scout", "Player Personnel"},
            {"Chad Henry", "Northeast Area Scout", "Player Personnel"},
            {"Tyler Hughes", "Midwest Area Scout", "Player Personnel"},
            {"Chris McGaha", "National Scout", "Player Personnel"},
            {"Mike Lacy", "Southwest Area Scout", "Player Personnel"},
            {"Kasia Omilian", "West Area Scout", "Player Personnel"},
            {"Mitch Chester", "College Scouting Coordinator", "Player Personnel"},
            {"Boyd Jackson", "Pro Personnel Assistant", "Player Personnel"},
            {"Anthony Foyt IV", "Scouting Assistant", "Player Personnel"},
            {"Skylar Hillmann", "Scouting Assistant", "Player Personnel"},
            {"Joe Tsaiho", "Scouting Assistant", "Player Personnel"},
            {"Andrew Hoyle", "Pro Personnel Scout", "Player Personnel"},
            {"Gregory Liverpool III", "Midlands Area Scout/Pro Scout", "Player Personnel"},
            {"Tanner Chastain", "NFS Scout", "Player Personnel"},
            {"Charlie Shin", "Vice President of Analytics & Digital Innovation", "Analytics & Digital Innovation"},
            {"Austin Reed", "Senior Director of Performance Marketing & Digital Media", "Analytics & Digital Innovation"},
            {"Daron Williams", "Director of Digital Media", "Analytics & Digital Innovation"},
            {"Chris Grecco", "Director of CRM & Fan Insights", "Analytics & Digital Innovation"},
            {"Sherard Allen", "Digital Marketing & Media Manager", "Analytics & Digital Innovation"},
            {"Michael Cruz", "Senior Data Scientist", "Analytics & Digital Innovation"},
            {"Haley Stessman", "Senior CRM Business Analyst", "Analytics & Digital Innovation"},
            {"Maggie Massey", "Performance Marketing Analyst", "Analytics & Digital Innovation"},
            {"Maureen Williams", "Community Manager", "Analytics & Digital Innovation"},
            {"Steve Campbell", "Vice President of Communications & External Affairs", "Communications"},
            {"Matt Conti", "Vice President of Football Communications", "Communications"},
            {"Christian Edwards", "Director of Football Communications", "Communications"},
            {"Hayden Clark", "Football Communications Assistant Director", "Communications"},
            {"Pamela Humphrey", "Communications Coordinator / Alumni Relations", "Communications"},
            {"Maggie Evers", "Football Communications Assistant", "Communications"},
            {"Ryan Lobsiger", "Vice President of Corporate Partnerships", "Corporate Partnerships"},
            {"Brian Healey", "Sr. Corporate Partnership Executive", "Corporate Partnerships"},
            {"Brad Beery", "Sr. Corporate Partnership Executive", "Corporate Partnerships"},
            {"Andy Schwartz", "Sr. Corporate Partnership Executive", "Corporate Partnerships"},
            {"Eric Cole", "Corporate Partnership Executive", "Corporate Partnerships"},
            {"Nick Moreland", "Corporate Partnership Executive", "Corporate Partnerships"},
            {"Lindsay Catavolos", "Director of Partnership Marketing", "Corporate Partnerships"},
            {"Laura Reckman", "Senior Partnership Marketing Manager", "Corporate Partnerships"},
            {"Blake Heitman", "Partnership Marketing Manager", "Corporate Partnerships"},
            {"Jordan Gawarecki", "Partnership Marketing Manager", "Corporate Partnerships"},
            {"Caytee Wright", "Partnership Marketing Manager", "Corporate Partnerships"},
            {"Colleen Gudeman", "Partnership Marketing Manager", "Corporate Partnerships"},
            {"Susie Peters", "Sales Operations", "Corporate Partnerships"},
            {"Geoff Sakaguchi", "Vice President of Creative, Content & Media", "Creative, Content & Media"},
            {"Amber Derrow", "Senior Director of Content Strategy and Operations", "Creative, Content & Media"},
            {"Chris Albright", "Senior Creative Director of Colts Content", "Creative, Content & Media"},
            {"Matt Bowen", "Photographic Services Creative Director", "Creative, Content & Media"},
            {"Zack Baker", "Creative Director of Game Presentation & Motion Graphics", "Creative, Content & Media"},
            {"Rodolfo Galvan-Rivera", "Director of Colts Production", "Creative, Content & Media"},
            {"Courtney Terrell", "Videoboard Operations Director", "Creative, Content & Media"},
            {"Bill Brooks", "Colts Ambassador", "Creative, Content & Media"},
            {"Larra Overton", "Executive Producer Colts Productions/Host and Sideline Reporter", "Creative, Content & Media"},
            {"JJ Stankevitz", "Senior Manager of Editorial and Audio", "Creative, Content & Media"},
            {"Matt Taylor", "Manager of Radio Production/Voice of the Colts", "Creative, Content & Media"},
            {"Ali Demas", "Social Media Operations Manager", "Creative, Content & Media"},
            {"Veronica Smith", "Social Media Coordinator", "Creative, Content & Media"},
            {"Emily Fields", "Media Asset Manager", "Creative, Content & Media"},
            {"Caitlin Flowers", "Project Manager", "Creative, Content & Media"},
            {"Conner Handel", "Senior Creative Video Producer", "Creative, Content & Media"},
            {"Chris Buckley", "Senior Broadcast Engineer", "Creative, Content & Media"},
            {"Nathan Gardner", "Broadcast Engineer", "Creative, Content & Media"},
            {"Bryan Mitchell", "Post Production Specialist - Motion Graphics and Editing", "Creative, Content & Media"},
            {"Shelby Simpson", "Video Operations Manager & Gameday Producer", "Creative, Content & Media"},
            {"Casey Vallier", "Coordinator of Radio Production", "Creative, Content & Media"},
            {"Erin Webster", "Senior Graphic Designer", "Creative, Content & Media"},
            {"Grant Wayner", "Senior Graphic Designer", "Creative, Content & Media"},
            {"Grace Ramsey", "Senior Graphic Designer", "Creative, Content & Media"},
            {"Jacob Clouse", "Creative Video Producer", "Creative, Content & Media"},
            {"Alex Leachman", "Creative Video Producer", "Creative, Content & Media"},
            {"Dimia Guy", "Creative Video Producer", "Creative, Content & Media"},
            {"Sean Sullivan", "Director of Equipment Operations", "Equipment"},
            {"Mike Mays", "Assistant Equipment Manager", "Equipment"},
            {"Brian Seabrooks", "Assistant Equipment Manager", "Equipment"},
            {"Jamie Ostrom", "Assistant Equipment Manager", "Equipment"},
            {"Brandon Seitz", "Equipment Coordinator", "Equipment"},
            {"Gavin Rogers", "Equipment Coordinator", "Equipment"},
            {"Philip Hopson", "Equipment Coordinator", "Equipment"},
            {"Lindsey Hammond", "Vice President of Finance", "Finance"},
            {"Tina McKnight", "Payroll Manager", "Finance"},
            {"Jaci Rowland", "Director of Accounting", "Finance"},
            {"Jenny Ryan", "Accounts Payable Coordinator", "Finance"},
            {"Mario Buscemi", "Staff Accountant", "Finance"},
            {"Mike Bluem", "Director of Football Administration", "Football Operations"},
            {"Joe Fonderoli", "Vice President of Operations", "Football Operations"},
            {"David Thornton", "Vice President of Team Engagement", "Football Operations"},
            {"Melainey Lowe", "Director of Football Operations", "Football Operations"},
            {"Greg Starek", "Director of Football Analytics", "Football Operations"},
            {"Aditya Krishnan", "Game Management Coordinator", "Football Operations"},
            {"Leigh Hullett", "Director of Performance Nutrition", "Football Operations"},
            {"Debbie Finn", "Assistant to the General Manager", "Football Operations"},
            {"Ashleigh Prugh", "Football Data Analyst", "Football Operations"},
            {"Ashley Holcomb", "Football Operations Assistant", "Football Operations"},
            {"Danielle Wrubel", "Performance Nutrition Associate", "Football Operations"},
            {"Sam Swift", "Football Data Analyst", "Football Operations"},
            {"Traci Morgan", "Executive Assistant to the Chief Operating Officer", "Front Office Staff"},
            {"David Liptak", "Executive Assistant", "Front Office Staff"},
            {"Edward Hayes", "Office Services Manager", "Front Office Staff"},
            {"Cal Handelman", "Director of Administration", "Front Office Staff"},
            {"Michelle Rodriguez", "Executive Assistant", "Front Office Staff"},
            {"Leslie Stockton", "Executive Assistant to the Owner & CEO", "Front Office Staff"},
            {"Heather Chaykowski", "Executive Assistant to the Owner & Executive Vice President", "Front Office Staff"},
            {"Diana Richardson", "Executive Assistant to the Owner & Chief Brand Officer", "Front Office Staff"},
            {"Nichole Miller", "Administrative Assistant to the Chief Legal Officer", "Front Office Staff"},
            {"Jacob Sheff", "Curator and Director of Colts Archives", "Front Office Staff"},
            {"Steve Randall", "Director of Security", "Front Office Staff"},
            {"Paul Spall", "Team Security", "Front Office Staff"},
            {"Dennis Wilkes", "Team Security", "Front Office Staff"},
            {"Ian Ligocki", "Complex Security", "Front Office Staff"},
            {"Matt Boothby", "Vice President of Information Technology", "Information Technology"},
            {"Sean Welch", "Director of Football Information Systems", "Information Technology"},
            {"Graham Howe", "Director of IT Operations and Services", "Information Technology"},
            {"Chelsey Dowden", "Director of Information Security, Risk, and Compliance", "Information Technology"},
            {"Doug Snelling", "IT Systems Administrator", "Information Technology"},
            {"Danny Dowell", "Football Systems Developer", "Information Technology"},
            {"Kyle Earnest", "Junior IT Systems Administrator", "Information Technology"},
            {"Jack Miller", "Senior IT Support Analyst", "Information Technology"},
            {"Bobby Ezenwelu", "IT Support Analyst", "Information Technology"},
            {"Anish Singla", "Data Engineer", "Information Technology"},
            {"Troy Hoffman", "Cybersecurity Engineer", "Information Technology"},
            {"Troy Glendenning", "Director of Grounds", "Maintenance & Grounds"},
            {"Andrew Cooper", "Director of Facilities", "Maintenance & Grounds"},
            {"Chris Anderson", "Facilities Manager", "Maintenance & Grounds"},
            {"Gabriel Morris", "Facilities Manager", "Maintenance & Grounds"},
            {"Tyler Holloway", "Assistant Manager of Building & Grounds", "Maintenance & Grounds"},
            {"Darrell Chandler", "Grounds and Maintenance", "Maintenance & Grounds"},
            {"Sean Click", "Turf & Grounds Assistant", "Maintenance & Grounds"},
            {"Luke Stewart", "Turf & Grounds Assistant", "Maintenance & Grounds"},
            {"Stephanie Pemberton", "Senior Vice President of Marketing", "Marketing & Community Impact"},
            {"Trey Mock", "Creative Director of Mascot Program", "Marketing & Community Impact"},
            {"Kelly Tilley", "Director of Cheerleading and Entertainment", "Marketing & Community Impact"},
            {"Andy Matis", "Director of Football Development", "Marketing & Community Impact"},
            {"Hayden Barnack", "Director of Marketing", "Marketing & Community Impact"},
            {"Brett Kramer", "Director of Kicking the Stigma", "Marketing & Community Impact"},
            {"Caleb Bailey", "Senior Director of Events and Operations", "Marketing & Community Impact"},
            {"Ande Sadtler", "Director of Community Impact", "Marketing & Community Impact"},
            {"Taylor Sidwell", "Senior Manager of Events", "Marketing & Community Impact"},
            {"Erin Smith", "Cheerleader Manager", "Marketing & Community Impact"},
            {"Nicole Minor", "Manager of Hospitality and Gridiron Hall", "Marketing & Community Impact"},
            {"Claire Kirby", "Community Impact Manager", "Marketing & Community Impact"},
            {"Isaac Soares", "Mascot Program Specialist", "Marketing & Community Impact"},
            {"Colin Burns", "Senior Events Coordinator", "Marketing & Community Impact"},
            {"Bryce Chretien", "Football Development Senior Coordinator", "Marketing & Community Impact"},
            {"JP Osafo", "Football Development Camp Coordinator", "Marketing & Community Impact"},
            {"Athena Markowksi", "Marketing & Social Media Coordinator", "Marketing & Community Impact"},
            {"Zephaniah Vonderheit", "Kicking The Stigma Coordinator", "Marketing & Community Impact"},
            {"Nicole Englin", "Mascot Program Coordinator", "Marketing & Community Impact"},
            {"Grace Worcester", "Mascot Social Media Coordinator", "Marketing & Community Impact"},
            {"Anna Furstenau", "Events Coordinator", "Marketing & Community Impact"},
            {"Jayden Thurmand", "Community Impact Coordinator", "Marketing & Community Impact"},
            {"Regan Dorsey", "Marketing and Licensing Coordinator", "Marketing & Community Impact"},
            {"Erin Barill", "Director of Sports Medicine", "Medical Staff"},
            {"Kyle Davis", "Head Athletic Trainer", "Medical Staff"},
            {"MacKenzie Scovill", "Staff Physical Therapist", "Medical Staff"},
            {"Brian Buening", "Assistant Athletic Trainer", "Medical Staff"},
            {"Andre Washington", "Assistant Athletic Trainer", "Medical Staff"},
            {"Stephen Galvan", "Assistant Athletic Trainer", "Medical Staff"},
            {"Mckayla Tallman", "Administrative Assistant/Assistant Athletic Trainer", "Medical Staff"},
            {"G. Peter Maiers, MD", "Head Team Physician / Orthopedic Surgeon", "Medical Staff"},
            {"Dave Porter, MD", "Team Physician / Orthopedic Surgeon", "Medical Staff"},
            {"Lance Rettig, MD", "Team Physician / Orthopedic Surgeon", "Medical Staff"},
            {"Mark Ritter, MD", "Team Physician / Orthopedic Surgeon", "Medical Staff"},
            {"Steve Ritter, MD", "Team Physician / Orthopedic Surgeon", "Medical Staff"},
            {"Dale Snead, MD", "Team Physician / Orthopedic Surgeon", "Medical Staff"},
            {"Thurman Alvey, DO", "Physician", "Medical Staff"},
            {"Matt Negaard, MD", "Physician", "Medical Staff"},
            {"Marcus McCray", "Team Chiropractor", "Medical Staff"},
            {"Elizabeth White", "Counselor", "Medical Staff"},
            {"Jasmine Park", "Vice President of People, Culture, & Inclusion", "People, Culture, & Inclusion"},
            {"Sarah Dan", "People & Culture Manager", "People, Culture, & Inclusion"},
            {"Juliet Cortez", "People & Culture Specialist", "People, Culture, & Inclusion"},
            {"Jim Van Dam", "Vice President of Ticket Sales and Service", "Ticket Sales & Services"},
            {"Bob Parenteau", "Senior Director of Ticket Operations", "Ticket Sales & Services"},
            {"Justin Gentile", "Senior Director of Premium Sales and Service", "Ticket Sales & Services"},
            {"Stevie Sammons", "Director of Membership Services", "Ticket Sales & Services"},
            {"Bret Cranston", "Director of Ticket Sales", "Ticket Sales & Services"},
            {"Jody Henton", "Senior Premium Sales and Service Manager", "Ticket Sales & Services"},
            {"Becca Schuman", "Senior Manager of Ticket Marketing and Events", "Ticket Sales & Services"},
            {"Hannah Glaser", "Suite Sales Manager", "Ticket Sales & Services"},
            {"Tyson Smith", "Suite Sales Manager", "Ticket Sales & Services"},
            {"Dylan Sheldon", "Account Manager, Premium Services", "Ticket Sales & Services"},
            {"Katy Johnson", "Account Manager, Premium Services", "Ticket Sales & Services"},
            {"Hayden Birkey", "Senior Account Executive, Season Sales", "Ticket Sales & Services"},
            {"TJ Kocher", "Account Executive, Membership Sales", "Ticket Sales & Services"},
            {"Molly Spitznogle", "Account Executive, Group Sales", "Ticket Sales & Services"},
            {"Kelsey Luce", "Manager of Consumer Sales", "Ticket Sales & Services"},
            {"Cade Wilemon", "Account Manager, Membership Services", "Ticket Sales & Services"},
            {"Fiona McManus", "Account Manager, Membership Services", "Ticket Sales & Services"},
            {"Trevor Stobbe", "Account Manager, Membership Services", "Ticket Sales & Services"},
            {"Donald Wilson", "Account Manager, Membership Services", "Ticket Sales & Services"},
            {"Benjamin Joseph", "Account Manager, Membership Services", "Ticket Sales & Services"},
            {"Caroline Conrad", "Account Manager, Membership Services", "Ticket Sales & Services"},
            {"Tessa Tomaso", "Account Manager, Membership Services", "Ticket Sales & Services"},
            {"Stewart Cramer", "Director of Video", "Video"},
            {"Jack Benitez", "Assistant Video Director", "Video"},
            {"Walt Konovsek", "Senior Video Assistant", "Video"},
            {"Billy Furman", "Video Assistant", "Video"},
            {"Blake Hermsen", "Video Assistant", "Video"}
        };

        String currentDepartment = "";
        for (int slot = 1; slot <= 211; slot++) {
            if (slot <= staffMembers.length) {
                String department = staffMembers[slot - 1][2].toString();
                if (!department.equals(currentDepartment)) {
                    model.addRow(new Object[]{"", "", department});
                    currentDepartment = department;
                }
                model.addRow(staffMembers[slot - 1]);
            } else {
                model.addRow(new Object[]{
                    "Example Staff Member " + slot,
                    "Example Position " + slot,
                    "Example Department " + slot
                });
            }
        }
    }

    private void returnToMainMenu() {
        dispose();
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }

    private static class DepartmentHeaderRenderer extends DefaultTableCellRenderer {
        @Override
        public java.awt.Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component component = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            int modelRow = table.convertRowIndexToModel(row);
            boolean isDepartmentHeader = table.getModel().getValueAt(modelRow, 0).toString().isEmpty();

            if (isDepartmentHeader) {
                component.setBackground(new Color(0, 51, 160));
                component.setForeground(Color.WHITE);
                component.setFont(component.getFont().deriveFont(Font.BOLD));
                setText(column == 0
                        ? "Department: " + table.getModel().getValueAt(modelRow, 2)
                        : "");
            } else {
                component.setBackground(Color.WHITE);
                component.setForeground(Color.BLACK);
                component.setFont(component.getFont().deriveFont(Font.PLAIN));
            }
            return component;
        }
    }

    private static class MainMenu extends JFrame {
        public MainMenu() {
            setTitle("Indianapolis Colts Main Menu");
            setSize(450, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JLabel label = new JLabel("Welcome to the Colts Staff Directory", SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 18));

            JButton openDirectoryButton = new JButton("Open Staff Directory");
            JButton exitButton = new JButton("Exit");

            openDirectoryButton.addActionListener(e -> {
                dispose();
                SwingUtilities.invokeLater(() -> new ColtsStaffDirectory().setVisible(true));
            });
            exitButton.addActionListener(e -> System.exit(0));

            JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.add(label);
            panel.add(openDirectoryButton);
            panel.add(exitButton);

            add(panel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
