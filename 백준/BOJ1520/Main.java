package Backjoon.BOJ1520;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1;
            }
        }

        solution(0, 0);
        sb.append(dist[0][0]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int solution(int row, int col){
        if(row == N-1 && col == M-1)
            return 1;

        if(dist[row][col] != -1)
            return dist[row][col];

        int cnt = 0;
        for(int dir=0; dir<4; dir++){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;

            if(map[nx][ny] >= map[row][col])
                continue;

            int ret = solution(nx, ny);
            if(ret > 0)
                cnt += ret;
        }
        dist[row][col] = cnt;

        return dist[row][col];
    }
}
