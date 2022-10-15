package vladimir.loshchin;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Range of numbers 
 * @author Vladimir Loshchin <vladimir.loshchin@gmail.com>
 *
 */
public class Range {

    private final Stack<Integer> values = new Stack<>();

    public Range(int begin, int end) {
        var list = IntStream.range(begin, end+1).boxed().collect(toList());
        Collections.shuffle(list);
        values.addAll(list);
    }

    public boolean empty() {
        return values.isEmpty();
    }

    public int size() {
        return values.size();
    }
    
    public int pop() {
        return values.pop();
    }

    public void removeAll(Set<Integer> v) {
        values.removeAll(v);
    }

    @Override
    public String toString() {
        return values.toString();
    }

//    public List<Integer> pullAll() {
//        var result = new ArrayList<Integer>();
//
//        while (!values.isEmpty()) {
//            result.add(values.pop());
//        }
//
//        return result;
//    }
}
