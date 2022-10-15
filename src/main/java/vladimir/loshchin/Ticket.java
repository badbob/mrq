package vladimir.loshchin;

import java.io.StringWriter;

import static java.util.Objects.nonNull;

public class Ticket {

    private final Integer[][] table;
    
    /* package local */ Ticket(Integer[][] table) {
        this.table = table;
    }
    
    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        for (int i=0; i<3; ++i) {
            for (int j=0; j<9; ++j) {
                if (j == 0) {
                    sw.append("| ");
                }
                Integer v = table[i][j];
                sw.append(nonNull(v) ? (v > 9 ? v.toString() : " " + v.toString()) : "  ");
                sw.append(" | ");
            }
            sw.append('\n');
        }
        return sw.toString();
    }
}
