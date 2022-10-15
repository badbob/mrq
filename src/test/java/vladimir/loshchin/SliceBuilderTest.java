package vladimir.loshchin;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SliceBuilderTest {

    @Test void testFirstSlice() {
        Matrix counter = new Matrix(3, 6);
        
        var slice = new SliceBuilder(new Range(1, 9), counter).build();
        System.out.println(slice.toString());

        counter = counter.plus(slice.mask());
        slice = new SliceBuilder(new Range(10, 19), counter).build();
        System.out.println(slice.toString());

        counter = counter.plus(slice.mask());
        slice = new SliceBuilder(new Range(20, 29), counter).build();
        System.out.println(slice.toString());
        
        counter = counter.plus(slice.mask());
        slice = new SliceBuilder(new Range(30, 39), counter).build();
        System.out.println(slice.toString());
        
        counter = counter.plus(slice.mask());
        slice = new SliceBuilder(new Range(40, 49), counter).build();
        System.out.println(slice.toString());
        
        counter = counter.plus(slice.mask());
        slice = new SliceBuilder(new Range(50, 59), counter).build();
        System.out.println(slice.toString());
        
        counter = counter.plus(slice.mask());
        slice = new SliceBuilder(new Range(60, 69), counter).build();
        System.out.println(slice.toString());
        
        counter = counter.plus(slice.mask());
        slice = new SliceBuilder(new Range(70, 79), counter).build();
        System.out.println(slice.toString());
        
//        counter = counter.plus(slice.mask());
//        slice = new SliceBuilder(new Range(80, 90), counter).build();
//        System.out.println(slice.toString());
    }
    
    @Test void testEdgeCase() {
        Matrix counter = new Matrix(new int[][] {
            {1, 2, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
        });

        var slice = new SliceBuilder(new Range(20, 29), counter).build();
        System.out.println(slice.toString());
    }
    
    @Test void testEdgeCaseFullfiledCol() {
        Matrix counter = new Matrix(new int[][] {
            {1, 1, 1},
            {1, 1, 1},
            {1, 0, 0},
            {1, 0, 0},
            {1, 0, 0},
            {1, 0, 0},
        });

        var slice = new SliceBuilder(new Range(10, 19), counter).build();
        System.out.println(slice.toString());
    }
    
    @Disabled
    @Test void testLastSlice() {
        Matrix counter = new Matrix(new int[][] {
            {4, 5, 5},
            {5, 4, 5},
            {3, 4, 5},
            {5, 3, 5},
            {5, 5, 4},
            {4, 3, 5}
        });
        new SliceBuilder(new Range(80, 90), counter.test(i -> i > 4)).build();
    }
}
