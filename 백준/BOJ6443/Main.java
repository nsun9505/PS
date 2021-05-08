package Backjoon.BOJ6443;

import java.io.*;
import java.util.Arrays;

public class Main {
    static char[][] charArr;
    static int[] cnt = new int[26];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        charArr = new char[N][];
        for(int i=0; i<N; i++){
            Arrays.fill(cnt, 0);
            charArr[i] = br.readLine().toCharArray();
            for(int idx=0; idx<charArr[i].length; idx++)
                cnt[charArr[i][idx] - 'a']++;
            perm(0, charArr[i]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void perm(int index, char[] arr){
        if(index == arr.length){
            for(char ch : arr)
                sb.append(ch);
            sb.append("\n");
            return;
        }

        for(int i=0; i<26; i++){
            if(cnt[i] > 0) {
                cnt[i]--;
                arr[index] = (char)(i + 'a');
                perm(index+1, arr);
                cnt[i]++;
            }
        }
    }
}
