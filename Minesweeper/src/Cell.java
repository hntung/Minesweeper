import javax.swing.*;
public class Cell extends JButton {
	private int type;
	private int position;
	private boolean discovered;
	private boolean flagged;
	
	public Cell(int type, int position, boolean discovered, boolean flagged) {
		this.type = type;
		this.position = position;
		this.discovered = discovered;
		this.flagged = flagged;
	}
	
	public int getType() {
        // TYPES -- 0: Empty, 1: Mine, 2: Number
        return type;
    }

    public int getPosition() {
        return position;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean d) {
        this.discovered = d;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean f) {
        this.flagged = f;
    }
}
