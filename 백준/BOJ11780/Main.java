package Backjoon.BOJ11780;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] dist = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        int[][] prev = new int[N+1][N+1];

        StringTokenizer st = null;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(w < dist[u][v]) {
                dist[u][v] = w;
                prev[u][v] = v;
            }
        }

        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                if(i == k)
                    continue;
                for(int j=1; j<=N; j++){
                    if(j == i || j == k)
                        continue;

                    if(dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prev[i][j] = prev[i][k];
                    }
                }
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++)
                sb.append((dist[i][j] == INF ? 0 : dist[i][j]) + " ");
            sb.append("\n");
        }

        for(int start=1; start<=N; start++){
            for(int target=1; target<=N; target++){
                if(target == start || dist[start][target] == INF)
                    sb.append("0\n");
                else{
                    int count = 0;
                    int cur = start;
                    StringBuilder tmp = new StringBuilder();
                    while(true){
                        tmp.append(cur + " ");
                        cur = prev[cur][target];
                        count++;
                        if(cur == 0)
                            break;
                    }
                    sb.append(count + " " + tmp.toString() + "\n");
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
