import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Rodrigo on 7/23/16.
 */
@Data
public class Node {
    private char prefix;
    private int sizeFirstDoc;
    private int sizeSecondDoc;
    private String fullNodeText;
    private int fullDocSize;
    private Node child;
    private List<Document> documents;

    public Node () {
        documents = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.valueOf(this.prefix)+this.getSizeFirstDoc()+":"+this.sizeSecondDoc+":";
    }

    public int calculateFullSize() {
        int totalSize = 0;
        for (Document doc : documents){
            totalSize +=doc.toString().length();
        }
        if (child == null) {
            totalSize += this.toString().length();
        }else{
            totalSize += this.toString().length()+this.child.calculateFullSize();
        }
        return totalSize;
    }

    public int calculateFullSizeAlternative(){
        int totalSize = 0;
        for (Document doc : documents){
            totalSize +=doc.toString().length();
        }
        totalSize += this.toString().length();
        return totalSize;
    }

}
