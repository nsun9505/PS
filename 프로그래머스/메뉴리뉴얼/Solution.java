package Programmers.메뉴리뉴얼;
import java.util.*;

class Solution {
    static Map<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();

        //중요
        for(int i=0; i<orders.length; i++){
            char[] tmp = orders[i].toCharArray();
            Arrays.sort(tmp);
            orders[i] = String.valueOf(tmp);
        }

        for(int i=0; i<course.length; i++){
            map.clear();
            for(String order : orders){
                if(order.length() < course[i])
                    continue;
                boolean isUsed[] = new boolean[order.length()];
                comb("", 0, course[i], 0, order, isUsed);
            }
            Set<String> keys = map.keySet();
            int max = 0;
            for(String key : keys){
                if(map.get(key) >= 2)
                    max = Math.max(max, map.get(key));
            }

            for(String key : keys){
                if(max == map.get(key))
                    list.add(key);
            }
        }

        Collections.sort(list);
        for(String str : list)
            System.out.println(str);

        return answer;
    }

    public static void comb(String key, int idx, int N, int start, String input, boolean[] isUsed){
        if(idx >= N){
            if(!map.containsKey(key))
                map.put(key, 1);
            else
                map.put(key, map.get(key) + 1);
            return;
        }

        for(int i=start ; i<input.length(); i++){
            if(!isUsed[i]){
                isUsed[i] = true;
                comb(key + String.valueOf(input.charAt(i)), idx+1, N, i+1, input, isUsed);
                isUsed[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2,3,4});
    }
}