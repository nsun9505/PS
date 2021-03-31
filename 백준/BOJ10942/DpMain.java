package Backjoon.BOJ10942;

import java.io.*;
import java.util.StringTokenizer;

public class DpMain {
    static int[][] map;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++) {
            map[i][i] = 1;
            if(arr[i] == arr[i-1])
                map[i-1][i] = 1;
        }

        for(int i=2; i<=N-1; i++){
            for(int j=1; j<=N-i; j++)
                // 왼쪽 끝과 오른쪽 끝이 같고, 그 사이의 숫자가 팰린드롬이면 j ~ j+i는 팰린드롬
                if(arr[j] == arr[j+i] && map[j+1][j + i - 1] == 1)
                    map[j][j+i] = 1;
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            sb.append(map[s][t] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
