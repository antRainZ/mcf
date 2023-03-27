package org.antrain.app.wordfilter;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 参考文档
 * ansj分词史上最详细教程 https://blog.csdn.net/bitcarmanlee/article/details/53607776
 * Java---根据停用词表对文本进行过滤 https://blog.csdn.net/qq_41982466/article/details/99733666
 *
 */
public class FilterStopWord {

    //停用词表路径
    public String stop_word_path = "/stopWord.txt";
    public Map<String, Integer> map ; //停用词map表
//    Set<String> expectedNature = new HashSet<String>() {{
//        add("n");add("nt");add("nz");add("nw");add("nl");
//        add("ng");add("nr1");add("nr2");add("ns");
//        add("nrf");add("nrj");
//        add("t");add("tg");
//        add("s");add("f");add("d");
//        add("v");add("vd");add("vn");add("vf");
//        add("vx");add("vi");add("vl");add("vg");
//        add("ad");add("an");add("ag");add("al");
//    }};

    public FilterStopWord() {
        map = getMap();
    }

    /**
     * 根据停用词表，过滤句子中包含的停用词
     * @param oldString：原中文文本
     * @return 去除停用词之后的中文文本
     */
    public String RemoveStopWords(String oldString) {
        String newString = "";
        // 去除非字母类或者非汉字类的字符
        oldString = oldString.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]","");
        // 首先对句子进行分词
        List<Term> terms = ToAnalysis.parse(oldString).getTerms();
        // System.out.println(terms);
        // 遍历分词后的每个词，看它是否在map中，若在则过滤，若不在，则保存
        for (int i = 0; i < terms.size(); i++) {
            String word = terms.get(i).getName(); // 拿到词
            // String natureStr = terms.get(i).getNatureStr();
            if (!map.containsKey(word)){ // 判断该词是否在停用词字典内
                newString += word;
            }
        }
        return newString.toUpperCase();
    }

    /**
     * 根据停用词表，过滤句子中包含的停用词,
     * @param oldString：原中文文本
     * @return 去除停用词之后返回分词的集合
     */
    public List<String> getWordList(String oldString) {
        // 去除非字母类或者非汉字类的字符
        oldString = oldString.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]","");
        // 首先对句子进行分词
        List<Term> terms = ToAnalysis.parse(oldString).getTerms();
        List<String> comes = new ArrayList<>();
        // System.out.println(terms);
        // 遍历分词后的每个词，看它是否在map中，若在则过滤，若不在，则保存
        for (int i = 0; i < terms.size(); i++) {
            String word = terms.get(i).getName(); // 拿到词
            // String natureStr = terms.get(i).getNatureStr();
            if (!map.containsKey(word)){ // 判断该词是否在停用词字典内
               comes.add(word);
            }
        }
        return comes;
    }

    /**
     * 获取停用词的map形式
     * @return
     * @throws IOException
     */
    public Map<String, Integer> getMap()  {
        Map<String, Integer> Dic = new HashMap<String, Integer>();
        // 加载字典
        try {
            InputStreamReader isr = new InputStreamReader(
                    this.getClass().getResourceAsStream(stop_word_path), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                Dic.put(line.trim(), 1);
            }
            // 关闭文件
            br.close();
            isr.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Dic;
    }
}