public enum Move {
    PIEDRA, PAPEL, TIJERA, NEBULOSA, FUEGO, AGUA;

    public String getImageName() {
        return switch (this) {
            case PIEDRA ->
                "piedra.jpg";
            case PAPEL ->
                "papel.jpg";
            case TIJERA ->
                "tijera.jpg";
            case NEBULOSA ->
                "nebulosa.jpg";
            case FUEGO ->
                "fuego.jpg";
            case AGUA ->
                "agua.jpg";
        };
    }
}