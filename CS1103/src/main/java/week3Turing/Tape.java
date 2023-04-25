package week3Turing;

/**
 * This class represents the tapes used in the Turing Machine.
 * A tape is used in a turing machine.
 * A tape consists of a Cell that contains a character as content.
 * A tape can also be moved left or right and it is not limited by how far it can move.
 */
public class Tape {

    // The constructor for instantiating a tape and the current cell.
    public Tape() {
        this.currentCell = new Cell();
        this.currentCell.content = ' ';
    }

    // This variable keeps track of the tape's current cell.
    private Cell currentCell;

    /**
     * @return the tape's current cell.
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * @return the content contained in the current cell.
     */
    public char getContent() {
        return currentCell.content;
    }

    /**
     * A methods that changes the content of the current cell
     *
     * @param ch
     */
    public void setContent(char ch) {
        currentCell.content = ch;
    }

    /**
     * This method moves the current cell to the left (previous position on the tape).
     * If the cell to the left is null, in other words, the tape has reach the edge, a new Cell is constructed
     * and currentCell gets to move to the left.
     */
    public void moveLeft() {

        // Creates a new cell if currentCell is at the leftmost cell.
        if (currentCell.prev == null) {
            Cell newCell = new Cell();
            newCell.content = ' ';
            currentCell.prev = newCell;
        }
        // Move the current cell to the left.
        Cell temp = currentCell;
        currentCell = temp.prev;
        currentCell.next = temp;
    }

    /**
     * The method is the same as moveLeft(), except now the currentCell will move to the right.
     */
    public void moveRight() {
        if (currentCell.next == null) {
            Cell newCell = new Cell();
            newCell.content = ' ';
            currentCell.next = newCell;
        }
        Cell temp = currentCell;
        currentCell = temp.next;
        currentCell.prev = temp;
    }

    /**
     * This method returns all the content contained in each cell of the tape.
     * A variable (runner) is used to iterate through all the cells on the tape,
     * and accumulates all the characters to form a string.
     *
     * @return The String that comprises all the characters concatenated.
     */
    public String getTapeContents() {
        String tapeContents = "";
        Cell runner = getCurrentCell(); // The runner will be used to iterate through all the cells.

        // Move the runner to the leftmost cell on the tape.
        while (runner.prev != null) {
            Cell temp = runner;
            runner = temp.prev;
            runner.next = temp;
        }

        // Iterate through each cell and add each character to the string @tapeContent.
        while (runner.next != null) {
            tapeContents += runner.content;
            Cell temp = runner;
            runner = temp.next;
            runner.prev = temp;
        }
        return tapeContents; // returns all the concatenated characters as a string.
    }
} // end of Tape class
