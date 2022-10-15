package vladimir.loshchin;

@FunctionalInterface
public interface MatrixConsumer {

    void consume(int row, int col, int value);
}
