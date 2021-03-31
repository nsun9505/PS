package Backjoon.BOJ1759;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static Set<Character> aeiou = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        aeiou.add('a');
        aeiou.add('e');
        aeiou.add('i');
        aeiou.add('o');
        aeiou.add('u');

        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[] arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        comb(arr, 0, "", L);

        for(String str : list)
            sb.append(str + "\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void comb(char[] arr, int idx, String ret, int L){
        if(ret.length() >= L){
            if(check(ret))
                list.add(ret);
            return;
        }

        if(idx >= arr.length)
            return;

        comb(arr, idx+1, ret + String.valueOf(arr[idx]), L);
        comb(arr, idx+1, ret, L);
    }

    private static boolean check(String ret) {
        int aeiouCnt = 0;
        int others = 0;
        for(int i=0; i<ret.length(); i++)
            if(aeiou.contains(ret.charAt(i)))
                aeiouCnt++;
            else
                others++;

        if(aeiouCnt < 1 || others < 2)
            return false;
        return true;
    }
}
