package SWEA.SWEA1954;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            boolean[][] isVisited = new boolean[N][N];
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};

            int num = 1;
            int dir = 0;
            int row = 0;
            int col = 0;

            arr[row][col] = num;
            int min = 0;
            int max = N;
            while(num <= N*N){
                arr[row][col] = num++;
                isVisited[row][col] = true;

                int nx = row + dx[dir];
                int ny = col + dy[dir];

                if(nx < min || ny < min || nx >= max || ny >= max || isVisited[nx][ny]) {
                    dir = (dir + 1) % 4;
                    nx = row + dx[dir];
                    ny = col + dy[dir];
                }

                row = nx;
                col = ny;
            }

            sb.append("#" + t + "\n");
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++)
                    sb.append(arr[i][j] + " ");
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
