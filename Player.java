package GameofGhost;

public class Player {
    private String name;
    private int numLetters;

    public Player(String name) {
        this.name = name;
        this.numLetters = 0;
    }

	public void loseRound() {
        this.numLetters++;
    }

    public boolean isEliminated() {
        return this.numLetters == 5;
    }

    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.name);
    	sb.append(" : ( ");
    	for (int i = 0; i < this.numLetters; i++) {
    		sb.append("GHOST".charAt(i));
    	}
    	sb.append(" ) ");
    	return sb.toString();
    }
}

