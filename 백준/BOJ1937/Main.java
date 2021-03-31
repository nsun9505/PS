package Backjoon.BOJ1937;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dist;
    static int ans = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];


        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1;
            }
        }

        ans = 0;
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = solution(i, j);
                ans = Math.max(dist[i][j], ans);
            }
        }


        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // dist[row][col] : row, col에서 출발했을 때 최대로 살 수 있는 날
    public static int solution(int row, int col){
        // 메모이제이션
        if(dist[row][col] != -1)
            return dist[row][col];

        // row, col에서 최소로 갈 수 있는 경로
        dist[row][col] = 1;

        for(int dir=0; dir<4; dir++){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                continue;

            if(map[row][col] >= map[nx][ny])
                continue;

            dist[row][col] = Math.max(dist[row][col], solution(nx, ny)+1);
        }

        return dist[row][col];
    }
}
