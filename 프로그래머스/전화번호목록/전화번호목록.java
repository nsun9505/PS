package Programmers.전화번호목록;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class 전화번호목록 {
    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        Set<Integer> lenSet = new HashSet<>();
        lenSet.add(phone_book[0].length());

        Set<String> phSet = new HashSet<>();
        for (String phnum : phone_book) {
            if (phSet.contains(phnum))
                return false;

            for(int i : lenSet){
                if (phSet.contains(phnum.substring(0, i)))
                    return false;
            }
            phSet.add(phnum);
            lenSet.add(phnum.length());
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"119", "97674223", "1195524421"}));
    }
}
