package Backjoon.BOJ2668;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
/*
*
*   사이클 체크 문제
*
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        boolean[] isVisited = new boolean[N+1];
        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int ans = 1;
        HashSet<Integer> set = new HashSet<>();
        for(int start = 1; start <= N; start++){
            int cnt = 1;
            Arrays.fill(isVisited, false);
            int cur = arr[start];
            while (true) {
                isVisited[cur] = true;
                cur = arr[cur];
                if(isVisited[cur])
                    break;
                cnt++;
            }
            if(!isVisited[start])
                continue;
            for (int i = 1; i <= N; i++) {
                if (isVisited[i])
                    set.add(i);
            }
        }

        System.out.println(set.size());
        int[] answer = new int[set.size()];
        int idx = 0;
        for(int num : set)
            answer[idx++] = num;

        Arrays.sort(answer);
        for(int i=0; i<answer.length; i++)
            System.out.println(answer[i]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
