import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserService {

    private TranslatorService translatorService;

    public ParserService(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    public String parse(List<String> text){
        int countCalls=0;
        List<Node> nodeList = new ArrayList<>();
        nodeList = parseFile(text, nodeList, countCalls);
        Map<String, String> translationMap = generateTranslationMap(nodeList);
        Map<String, String> translatedMap = translate(translationMap);
        modifyDocumentSizes(nodeList, translatedMap);
        return regenerateText(nodeList);
    }

    private List<Node> parseFile(List<String> text, List<Node> nodeList, int countCalls) {
        Node node;
        int count = countCalls;
        List<String> aux = new ArrayList<>();

        for (String line : text) {
            System.out.println(line);
            if ("".equalsIgnoreCase(line)) {
                break;
            }

            if (line.startsWith("s") && count > 1) {
                Document doc = new Document();
                String[] parts = line.split(":");
                doc.setPrefix(parts[0].charAt(0));
                doc.setSize(parts[0].charAt(1) - '0');
                doc.setFullDocText(line.split("(?<=:)")[0]);
                doc.setFullDocSize(doc.getFullDocText().length());
                doc.setWord(line.substring(doc.getFullDocText().length(), doc.getFullDocText().length() + doc.getSize()));
                doc.setIdentifier(count);
                nodeList.get(nodeList.size() - 1).getDocuments().add(doc);
                aux.add(line.substring(doc.toString().length(),
                        line.length()));
            }

            if (line.startsWith("s") && count == 1) {
                Document doc = new Document();
                String[] parts = line.split(":");
                doc.setPrefix(parts[0].charAt(0));
                doc.setSize(parts[0].charAt(1) - '0');
                doc.setFullDocText(line.split("(?<=:)")[0]);
                doc.setFullDocSize(doc.getFullDocText().length());
                doc.setWord(line.substring(doc.getFullDocText().length(), doc.getFullDocText().length() + doc.getSize()));
                doc.setIdentifier(count);
                nodeList.get(0).getDocuments().add(doc);
                aux.add(line.substring(doc.toString().length(),
                        line.length()));


            }
            if (count == 0 || line.startsWith("b")) {
                String[] parts = line.split(":");
                node = new Node();
                node.setPrefix(parts[0].charAt(0));
                node.setSizeFirstDoc(parts[0].charAt(1) - '0');
                node.setSizeSecondDoc(Integer.valueOf(parts[1]));
                node.setFullNodeText(line.split("(?=b|s)")[0]);
                if (!nodeList.isEmpty()) {
                    nodeList.get(nodeList.size() - 1).setChild(node);
                }
                nodeList.add(nodeList.size(), node);
                aux.add(line.substring(node.getFullNodeText().length(),
                        line.length()));
            }
            count++;
            parseFile(aux, nodeList, count);
        }
        return nodeList;
    }

    private Map<String, String> generateTranslationMap(List<Node> nodeList) {
        Map<String, String> translationMap = new HashMap<>();
        nodeList.stream()
                .forEach(node -> node.getDocuments()
                        .forEach(doc -> translationMap.put(String.valueOf(doc.getIdentifier()),
                                doc.getWord())));
        return translationMap;
    }


    public Map<String, String> translate(Map<String, String> translationMap) {
        return translatorService.translate(translationMap);
    }

    public void modifyDocumentSizes(List<Node>nodeList, Map<String, String> translatedMap) {
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.get(i).getDocuments().size(); j++) {
                String translatedWord = translatedMap.get(String.valueOf(nodeList.get(i).getDocuments().get(j).getIdentifier()));
                if (translatedWord != null && !"".equalsIgnoreCase(translatedWord)) {
                    nodeList.get(i).getDocuments().get(j).setWord(translatedWord);
                    nodeList.get(i).getDocuments().get(j).setSize(translatedWord.length());
                    if (j == 0) {
                        nodeList.get(i).setSizeFirstDoc(nodeList.get(i).getDocuments().get(j).toString().length());

                    } else {
                        nodeList.get(i).setSizeSecondDoc(nodeList.get(i).getDocuments().get(j).toString().length());
                    }
                }
            }
        }

        modifyNodeSizes(nodeList.get(0));
    }


    private void modifyNodeSizes(Node root) {
        if (root.getChild() != null && root.getDocuments().size()==0) {
            root.setSizeFirstDoc(root.getChild().calculateFullSizeAlternative());
            root.setSizeSecondDoc(root.getChild().getChild().calculateFullSizeAlternative());
        }else
        if (root.getChild() != null) {
            root.setSizeSecondDoc(root.getChild().calculateFullSize());
            modifyNodeSizes(root.getChild());
        }
    }

    private String regenerateText(List<Node> nodeList){
        StringBuilder regeneratedText = new StringBuilder();
        for (Node node : nodeList){
            regeneratedText.append(node.toString());
            for (Document doc : node.getDocuments()){
                regeneratedText.append((doc.toString()));
            }
        }
        return regeneratedText.toString();
    }

}
