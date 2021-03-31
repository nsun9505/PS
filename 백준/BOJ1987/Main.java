package Backjoon.BOJ1987;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isUsed = new boolean[26];
    static int R, C;
    static int[][] map;
    static int ans = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = input.charAt(j) - 65;
            }
        }

        isUsed[map[0][0]] = true;
        DFS(0, 0, 1);

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }


    public static void DFS(int row, int col, int cnt){
        for(int dir=0; dir<4; dir++){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= R || ny >= C){
                ans = Math.max(cnt, ans);
                continue;
            }

            if(isUsed[map[nx][ny]]) {
                ans = Math.max(cnt, ans);
                continue;
            }

            isUsed[map[nx][ny]] = true;
            DFS(nx, ny, cnt+1);
            isUsed[map[nx][ny]] = false;
        }
    }
}
