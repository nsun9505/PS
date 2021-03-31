package SWEA.SWEA6808;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final int MAX = 9;
    static int[] aCards = new int[MAX]; // 규영이
    static int[] bCards = new int[MAX]; // 인영이
    static boolean[] isUsed = new boolean[19];
    static int win, lose;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(isUsed, false);
            for(int i=0; i<MAX; i++) {
                aCards[i] = Integer.parseInt(st.nextToken());
                isUsed[aCards[i]] = true;
            }

            int idx = 0;
            for(int i=1; i<=18; i++)
                if(!isUsed[i])
                    bCards[idx++] = i;

            solution(0);

            sb.append("#" + t + " " + win + " " + lose + "\n");
            win = 0;
            lose = 0;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int idx){
        if(idx == MAX){
            int aSum = 0, bSum = 0;
            for(int i=0; i<MAX; i++){
                if(aCards[i] > bCards[i])
                    aSum += aCards[i] + bCards[i];
                else if(aCards[i] < bCards[i])
                    bSum += aCards[i] + bCards[i];
            }
            if(aSum > bSum)
                win++;
            else if(aSum < bSum)
                lose++;
            return;
        }

        for(int i=idx; i<MAX; i++){
            swap(bCards, idx, i);
            solution(idx+1);
            swap(bCards, idx, i);
        }

    }

    public static void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}