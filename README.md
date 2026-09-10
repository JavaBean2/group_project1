# Indianapolis Colts — 2026 Season Manager

## Course / Assignment
CSC-151 — Group Project 01 (Module 2)

## What This Project Does
A Java GUI application that lets the user interact with the Indianapolis Colts
organization for the 2026 season — browsing Players, Coaches, and Support Staff
through a menu-driven interface built with JOptionPane and Swing dialog boxes
(showMessageDialog, showInputDialog). The Main Menu ties each team member's
section together into a single runnable program.

## Expected Outcomes
- A working Java/Swing program, run from Visual Studio Code, that lets a user:
  - View, search, and filter the Colts' 2026 roster (Players)
  - View, search, and filter the Colts' coaching staff (Coaches)
  - View and search the Colts' front-office/support staff (Support Staff)
  - Navigate between all three sections from a single Main Menu
- Each contributor's section works as a standalone panel that plugs into the
  shared Main Menu without needing to be rewritten.

## Team & Contributions

| Section | File(s) | Contributor |
|---|---|---|
| Main GUI/Menu | `Main.java`, `MainMenu.java` | Sarah Ayres — also responsible for combining all sections into the final program |
| Players | `Player.java`, `PlayersGUI.java` | Nicholas Molina (initial concept), Sarah Ayres (implementation, built with AI assistance) |
| Coaches | `Coach.java`, `CoachesGUI.java` | Ashley Mitchell |
| Support Staff | `SupportStaff.java`, `SupportStaffGUI.java` | David Irvin — originally built as a standalone directory app; refactored to match the shared panel pattern |

## Structure
All files sit in a single flat directory (no packages) so they compile and
reference each other directly:

\```
GroupProject_01/
├── Main.java
├── MainMenu.java
├── Coach.java
├── CoachesGUI.java
├── Player.java
├── PlayersGUI.java
├── SupportStaff.java
└── SupportStaffGUI.java
\```

## Note on Individual Work
Per assignment requirements, each contributor's individually authored file(s)
are identified above. The Main GUI/Menu file (Sarah Ayres' individual
submission) depends on the Coaches, Players, and Support Staff panel files
being present in the same directory to compile and run — it is not a
standalone file.
