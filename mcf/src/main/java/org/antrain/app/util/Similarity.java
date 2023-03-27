package org.antrain.app.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Similarity {
    public static String getSimilarity(List<String> list1, List<String> list2) {
        if (list1==null || list2 == null) return "0.0";
        int size1 = list1.size(),size2 = list2.size();
        if (size1 < 1 || size2 < 1 ) return  "0.0";
        Map<String, int[]> result = new HashMap<>();
        for(String str:list1) {
            int[] temp = result.get(str);
            if (temp == null) {
                temp = new int[2];
                temp[0] = 1;
                temp[1] = 0;
            } else {
                temp[0] ++ ;
            }
            result.put(str,temp);
        }
        for(String str:list2) {
            int[] temp = result.get(str);
            if (temp == null) {
                temp = new int[2];
                temp[0] = 0;
                temp[1] = 1;
            } else {
                temp[1] ++ ;
            }
            result.put(str,temp);
        }
        Iterator<String> iterator = result.keySet().iterator();
        double sqdoc1 = 0;
        double sqdoc2 = 0;
        double denominator = 0;
        while (iterator.hasNext()) {
            int[] temp = result.get(iterator.next());
            denominator += temp[0] * temp[1];
            sqdoc1 += temp[0]*temp[0];
            sqdoc2 += temp[1]*temp[1];
        }
        DecimalFormat df = new DecimalFormat("0.000000");
        return df.format( denominator / Math.sqrt(sqdoc1*sqdoc2) );
    }

}
