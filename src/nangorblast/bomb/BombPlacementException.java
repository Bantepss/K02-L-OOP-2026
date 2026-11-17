package nangorblast.bomb;

/** Dilempar saat bomb tidak bisa dipasang, misalnya kapasitas bomb penuh. */
public class BombPlacementException extends Exception {

    public BombPlacementException(String message) {
        super(message);
    }
}
