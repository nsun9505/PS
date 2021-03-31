package Backjoon.BOJ1992;

import java.io.*;

public class Main {
    static char[][] map;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++)
                map[i][j] = input.charAt(j);
        }

        sb.append(DFS(0, 0, N));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static String DFS(int row, int col, int N){
        if(N == 1)
            return String.valueOf(map[row][col]);

        String r1 = DFS(row, col, N/2);
        String r2 = DFS(row, col + N/2, N/2);
        String r3 = DFS(row + N/2, col, N/2);
        String r4 = DFS(row + N/2, col+N/2, N/2);
        if(r1.length() == 1 && r2.length() == 1 && r3.length() == 1 && r4.length() == 1)
            if(r1.equals(r2) && r1.equals(r3) && r1.equals(r4))
                return r1;
        return "("+r1 + r2 + r3 + r4+")";
    }
}
