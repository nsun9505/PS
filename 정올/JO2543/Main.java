package Jungol.JO2543;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        map[x][y] = -1;

        solution(0, 0, N);
        map[x][y] = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                sb.append(map[i][j] + " ");
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int row, int col, int N){
        if(N == 1)
            return;

        // 1사분면
        if(check(row, col, N/2))
            map[row + N/2-1][col + N/2] = map[row + N/2][col + N/2] = map[row + N/2][col + N/2-1] = 1;
        else if(check(row, col + N/2, N/2))
            map[row + N/2-1][col + N/2-1] = map[row + N/2][col + N/2-1] = map[row + N/2][col + N/2] = 2;
        else if(check(row + N/2, col,N/2))
            map[row + N/2][col + N/2] = map[row + N/2-1][col + N/2-1] = map[row + N/2-1][col + N/2] = 3;
        else if(check(row + N/2, col + N/2, N/2))
            map[row + N/2-1][col + N/2-1] = map[row + N/2-1][col + N/2] = map[row + N/2][col + N/2-1] = 4;

        solution(row, col, N/2);
        solution(row, col + N/2, N/2);
        solution(row + N / 2, col, N / 2);
        solution(row + N/2, col + N/2, N/2);
    }

    public static boolean check(int row, int col, int N){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                if(map[row+i][col+j] != 0)
                    return true;
        }
        return false;
    }
}