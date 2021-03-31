package Jungol.JO1681;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int map[][];
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(0, 0, 1);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int cost, int cur, int visit){
        if(answer < cost)
            return;

        if(visit == ((1 << N) - 1)){
            if(map[cur][0] == 0)
                return;

            answer = Math.min(answer, cost + map[cur][0]);
            return;
        }

        for(int next = 0; next < N; next++){
            if((visit & (1 << next)) == 0 && map[cur][next] != 0){
                solution(cost + map[cur][next], next, visit | (1 << next));
            }
        }
    }
}