package vladimir.loshchin;

import java.io.StringWriter;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Slice {

    public static int HEIGHT = 6;
    public static int WIDTH = 3;

    private final Matrix values = new Matrix(WIDTH, HEIGHT);

    public Slice() {}

    /* package local */ void set(int i, int j, int value) {
        if (values.set(i, j, value) > 0) {
            throw new IllegalStateException("You are trying to override existing value");
        }
    }

    public int[] column(int i) {
        return values.row(i);
    }

    public List<int[]> emptyCols() {
        return values.rows().stream().filter(row -> {
            for (int i = 0; i < row.length; ++i) {
                if (row[i] > 0) {
                    return false;
                }
            }
            return true;
        }).collect(toList());
    }
    
    public List<int[]> notFilledCols() {
        return values.rows().stream().filter(row -> {
            for (int i = 0; i < row.length; ++i) {
                if (row[i] == 0) {
                    return true;
                }
            }
            return false;
        }).collect(toList());
    }

    /**
     * @author Vladimir Loshchin <vladimir.loshchin@gmail.com>
     * 
     * @return the matrix of filled cells. 
     * If cell is filled, then matrix has 1 at position 
     * if cell is empty, then matrix has 0 at position
     */
    public Matrix mask() {
        return values.disjunction(new Matrix(WIDTH, HEIGHT));
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int v = values.get(i, j);
                if (j == 0) {
                    sw.append("| ");
                }
                String space = "  ";
                sw.append(v > 0 ? (v < 10 ? " " + String.valueOf(v) : String.valueOf(v)) : space);
                sw.append(" | ");
            }
            sw.append('\n');
        }

        return sw.toString();
    }

    public boolean valid() {
        for (int i = 0; i < HEIGHT; i++) {
            if (Utils.indexesOfHoles(values.row(i)).size() > 2) {
                return false;
            }
        }
        return true;
    }
}
