package Backjoon.BOJ14620;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] isVisited;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        DFS(1, 1, 0, 0);
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int row, int col, int cnt, int sum){
        if(cnt == 3){
            ans = Math.min(ans, sum);
            return;
        }

        for(int i=row; i<N-1; i++){
            for(int j= i == row ? col : 1; j < N-1; j++){
                if(isVisited[i][j])
                    continue;

                boolean flag = true;
                for(int dir = 0; dir<4; dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if(isVisited[nx][ny]) {
                        flag = false;
                        break;
                    }
                }

                if(!flag)
                    continue;

                int tmpSum = map[i][j];
                isVisited[i][j] = true;
                for(int dir = 0; dir < 4; dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    isVisited[nx][ny] = true;
                    tmpSum += map[nx][ny];
                }

                DFS(row, col+1, cnt+1, sum + tmpSum);
                isVisited[i][j] = false;
                for(int dir = 0; dir<4; dir++)
                    isVisited[i+dx[dir]][j+dy[dir]] = false;
            }
        }
    }
}
