package Jungol.JO1037;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] rowSum;
    static int[] colSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        rowSum = new int[N+1];
        colSum = new int[N+1];
        for(int i=1;i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    rowSum[i] += 1;
                    colSum[j] += 1;
                }
            }
        }

        String ans = "Corrupt";
        if(check())
            ans = "OK";
        else
            ans = checkChange();
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static String checkChange(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(map[i][j] == 0){
                    rowSum[i] += 1;
                    colSum[j] += 1;
                } else {
                    rowSum[i] -= 1;
                    colSum[j] -= 1;
                }

                if(check())
                    return "Change bit (" + i + "," + j + ")";

                if(map[i][j] == 0){
                    rowSum[i] -=1;
                    colSum[j] -=1;
                } else {
                    rowSum[i] += 1;
                    colSum[j] += 1;
                }
            }
        }
        return "Corrupt";
    }

    public static boolean check(){
        for(int i=1; i<=N; i++)
            if(rowSum[i] %2 == 1 || colSum[i] % 2 == 1)
                return false;
        return true;
    }
}
