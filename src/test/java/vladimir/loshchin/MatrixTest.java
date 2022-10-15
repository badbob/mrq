package vladimir.loshchin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MatrixTest {

    @Test void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(new int[][] {
            {1},
            {}
        }));
    }
    
    @Test void testEquals() {
        assertEquals(new Matrix(3, 2), new Matrix(3, 2));
        assertNotEquals(new Matrix(3, 2), new Matrix(2, 2));
        
        assertEquals(
                new Matrix(new int[][] {
                    {1, 2, 3},
                    {1, 2, 3}
                }), 
                new Matrix(new int[][] {
                    {1, 2, 3},
                    {1, 2, 3}
                }));
        
        assertNotEquals(
                new Matrix(new int[][] {
                    {0, 2, 3},
                    {1, 2, 3}
                }), 
                new Matrix(new int[][] {
                    {1, 2, 3},
                    {1, 2, 3}
                }));
    }

    @Test void testDisjunction() {
        var m1 = new Matrix(3, 2);
        m1.set(0, 0, 3);
        m1.set(0, 1, 5);
        
        var m2 = new Matrix(3, 2);
        
        var disjunction = m1.disjunction(m2);
        assertEquals(
                new Matrix(new int [][] {
                    {1, 1, 0},
                    {0, 0, 0}
                }), 
                disjunction);
    }
    
    @Test void test() {
        var m = new Matrix(new int[][] {
            {1, 2, 3},
            {4, 5, 6}
        });
        
        assertEquals(
                new Matrix(new int[][] {
                    {0, 0, 0},
                    {0, 1, 1}
                }), 
                m.test(i -> i > 4));
    }
}
