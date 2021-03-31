package Backjoon.BOJ2567;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        int[][] arr = new int[102][102];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            for(int r = row; r < row + 10; r++){
                for(int c = col; c < col + 10; c++)
                    arr[r][c] = 1;
            }
        }

        int ans = 0;

        for(int i=1; i<=100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (arr[i][j] == 0)
                    continue;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    // 경계일 경우 둘레 증가
                    if (arr[nx][ny] == 0)
                        ans++;
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
