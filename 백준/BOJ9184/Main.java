package Backjoon.BOJ9184;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[][][] w = new int[21][21][21];

        for(int a=0; a<=20; a++){
            for(int b=0; b<=20; b++){
                for(int c=0; c<=20; c++){
                    if(a <= 0 || b <= 0 || c <= 0)
                        w[a][b][c] = 1;
                    else if(a < b && b < c){
                        w[a][b][c] = w[a][b][c-1] + w[a][b-1][c-1] - w[a][b-1][c];
                    }
                    else
                        w[a][b][c] = w[a-1][b][c] + w[a-1][b-1][c] + w[a-1][b][c-1] - w[a-1][b-1][c-1];
                }
            }
        }

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1)
                break;

            if(a <= 0 || b <= 0 || c <= 0)
                sb.append("w(" + a + ", " + b + ", " + c + ") = 1\n");

            else if(a > 20 || b > 20 || c > 20){
                sb.append("w(" + a + ", " + b + ", " + c + ") = " + w[20][20][20] + "\n");
            }
            else if(a < b && b < c)
                sb.append("w(" + a + ", " + b + ", " + c + ") = " + w[a][b][c] + "\n");
            else
                sb.append("w(" + a + ", " + b + ", " + c + ") = " + w[a][b][c] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
