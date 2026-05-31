public class ResultLogic {

    public static String getWinner(Move p1, Move p2) {
        if (p1 == p2) {
            return "Empate";
        }

        // Agujero negro gana a todos
        if (p1 == Move.NEBULOSA) {
            return "Jugador";
        }
        if (p2 == Move.NEBULOSA) {
            return "Computadora";
        }

        // Agua gana a Fósforo y Pólvora
        if (p1 == Move.AGUA && p2 == Move.FUEGO) {
            return "Jugador";
        }
        if (p2 == Move.AGUA && p1 == Move.FUEGO) {
            return "Computadora";
        }

        // Fósforo y Pólvora gana a Papel y Tijera
        if (p1 == Move.FUEGO && (p2 == Move.PAPEL || p2 == Move.TIJERA)) {
            return "Jugador";
        }
        if (p2 == Move.FUEGO && (p1 == Move.PAPEL || p1 == Move.TIJERA)) {
            return "Computadora";
        }

        // Reglas básicas
        if ((p1 == Move.PIEDRA && p2 == Move.TIJERA)
                || (p1 == Move.PAPEL && p2 == Move.PIEDRA)
                || (p1 == Move.TIJERA && p2 == Move.PAPEL)) {
            return "Jugador";
        }

        return "Computadora";
    }
}
