import lombok.Data;

/**
 * Created by Rodrigo on 7/23/16.
 */

@Data
public class Document {
    private int identifier;
    private char prefix;
    private int size;
    private String word;
    private String fullDocText;
    private int fullDocSize;

    @Override
    public String toString(){
        return String.valueOf(this.prefix)+this.size+":"+this.word;
    }
}
