package Backjoon.BOJ2098;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static final int MAX = 16;
    static final int INF = 100000000;
    static int[][] dist = new int[MAX][MAX];
    static int[][] cache = new int[MAX][1<<MAX];
    static int start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(cache[i], -1);
            for(int j=0; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int answer = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            start = i;
            for(int j=0; j<N; j++)
                Arrays.fill(cache[j], -1);
            answer = Math.min(answer, solve(start, 1 << i));
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int solve(int here, int visited){
        if(visited == (1 << N) - 1)
            return dist[here][start] == 0 ? INF : dist[here][start];

        if(cache[here][visited] >= 0)
            return cache[here][visited];

        cache[here][visited] = INF;
        for(int next = 0; next < N; next++){
            if((visited & (1 << next)) > 0 || dist[here][next] == 0)
                continue;

            int ret = dist[here][next] + solve(next, visited | (1 << next));
            cache[here][visited] = Math.min(cache[here][visited], ret);
        }
        return cache[here][visited];
    }
}
