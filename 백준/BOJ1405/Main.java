package Backjoon.BOJ1405;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean[][] isVisited = new boolean[32][32];
    static double ans = 0;
    static int[] dx = {0, 0, -1 , 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dirs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dirs = new int[4];
        int max = 0;
        for(int i=0; i<dirs.length; i++){
            dirs[i] = Integer.parseInt(st.nextToken());
        }

        isVisited[15][15] = true;
        DFS(15, 15, 0, 1.0);

        sb.append(String.format("%.10f", ans));  // 포인트
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int row, int col, int cnt, double cur){
        if(cnt == N){
            ans += cur; // 포인트
            return;
        }

        for(int dir = 0; dir<4; dir++){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(isVisited[nx][ny])
                continue;

            isVisited[nx][ny] = true;
                                    // 포인트
            DFS(nx, ny, cnt + 1, cur * ((double)dirs[dir] / 100));
            isVisited[nx][ny] = false;
        }
    }
}
