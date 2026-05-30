public class Player {

    private Move lastMove;

    public boolean canUse(Move move) {
        return true;
    }

    public void setLastMove(Move move) {
        this.lastMove = move;
    }

    public Move getLastMove() {
        return lastMove;
    }
    
    private int puntaje;

    public Player(String Jugador) {
        this.puntaje = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void sumarPunto() {
        puntaje++;
    }
}
