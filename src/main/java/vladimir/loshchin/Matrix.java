package vladimir.loshchin;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.IntPredicate;

public class Matrix {

    private final int width;
    private final int height;
    private final int[][] values;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        values = new int[height][width];
    }

    public Matrix(int[][] values) {
        this.values = values;
        this.height = values.length;
        this.width = values[0].length;
        
        for (int[] row : values) {
            if (row.length != width) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    /**
     * @param i
     * @param j
     * @param value
     * @return previous value
     */
    public int set(int i, int j, int value) {
        int prev = values[i][j];
        values[i][j] = value;
        return prev;
    }

    public int get(int i, int j) {
        return values[i][j];
    }

    public int[] row(int i) {
        return values[i];
    }

    public List<int[]> rows() {
        return Arrays.asList(values);
    }

    public int max() {
        int max = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                max = values[i][j] > max ? values[i][j] : max;
            }
        }
        return max;
    }

    public Matrix test(IntPredicate predicate) {
        Matrix result = new Matrix(width, height);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                result.values[i][j] = predicate.test(values[i][j]) ? 1 : 0;
            }
        }
        return result;
    }
    
    public Matrix invert() {
        Matrix result = new Matrix(width, height);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                result.values[i][j] = values[i][j] > 0 ? 0 : 1;
            }
        }
        return result;
    }

    public boolean hasEmptyCells() {
        boolean result = false;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (values[i][j] == 0) {
                    return true;
                }
            }
        }
        return result;
    }

    /**
     * Arithmetical summarizing
     */
    public Matrix plus(Matrix m) {
        Matrix result = new Matrix(width, height);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                result.values[i][j] = values[i][j] + m.values[i][j];
            }
        }
        return result;
    }

    /**
     * Logical OR
     */
    public Matrix disjunction(Matrix arg) {
        Matrix result = new Matrix(width, height);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                result.values[i][j] = (values[i][j] > 0) || (arg.values[i][j] > 0) ? 1 : 0;
            }
        }
        return result;
    }

    public void forEach(MatrixConsumer consumer) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                consumer.consume(i, j, values[i][j]);
            }
        }
    }

    public void forEachCol(BiConsumer<Integer, int[]> consumer) {
        for (int i = 0; i < values.length; ++i) {
            consumer.accept(i, values[i]);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Matrix) {
            Matrix arg = (Matrix) obj;

            if (arg.width != width || arg.height != height) {
                return false;
            }

            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    if (values[i][j] != arg.values[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        sw.append('[');
        for (int i = 0; i < height; ++i) {
            sw.append(Arrays.toString(values[i]));
        }
        sw.append(']');
        return sw.toString();
    }
}
