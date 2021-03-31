package SWEA.SWEA2805;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t<=T; t++){
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];

            for(int i=0; i<N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++)
                    map[i][j] = Integer.parseInt(input.charAt(j) + "");
            }

            int start = N / 2;
            int end = N / 2;
            int sum = 0;
            for(int row = 0; row < N; row++) {
                for (int col = start; col <= end; col++)
                    sum += map[row][col];
                if(row < N/2) {
                    start--;
                    end++;
                } else {
                    start++;
                    end--;
                }
            }
            sb.append("#" + t + " " + sum + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
