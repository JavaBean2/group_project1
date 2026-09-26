# Indianapolis Colts Directory App — Module 3 (Polished)

## Overview
A Java Swing GUI application for browsing the Indianapolis Colts organization — Coaches, Players, and Staff — built with JOptionPane dialogs and a CardLayout-based main menu. This is the polished, cleaned-up version of the Module 2 build, extended with CSV file read/write for all three sections.

## How to Run
1. Open the project folder in Visual Studio Code.
2. Make sure `colts_logo.png`, `coaches.csv`, `players.csv`, and `staff.csv` are in the same directory as the `.java` files.
3. Compile and run `MainMenu.java` — it's the entry point (`public static void main`).

## File Structure
| File | Purpose |
|---|---|
| `MainMenu.java` | Main window; hosts all sections behind a CardLayout with a persistent nav bar |
| `CoachesGUI.java` / `Coach.java` | Coaches directory — view all, filter by unit, search, profile view |
| `PlayersGUI.java` / `Player.java` | Players directory — same feature set as Coaches |
| `ColtsStaffDirectory.java` | Staff directory — searchable table, add/delete staff members |
| `coaches.csv` | Data source for the Coaches section |
| `players.csv` | Data source for the Players section |
| `staff.csv` | Data source for the Staff section |
| `colts_logo.png` | Logo shown on the home screen |

## File I/O
Each section reads its data from its matching CSV on startup and writes changes back out when returning to the main menu. Each CSV has a header row on line 1.

## Individual Contributions
Contributions are marked with `// Written by: [Name]` comments directly above the relevant code:
- **Sarah Ayres** — Main GUI/Menu, CardLayout navigation, Players section
- **Ashley Mitchell** — Coaches section
- **David Irvin** — Staff section
- **Nicholas Molina** — Players CSV read/write

## Notes
This is the polished version — commented-out code, unused methods, and excess blank lines from the working draft have been removed. See the working draft folder for full development history.
