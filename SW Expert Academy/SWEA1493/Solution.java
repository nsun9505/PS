package SWEA.SWEA1493;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        HashMap<Integer, Pos> posMap = new HashMap<>();
        int[][] arr = new int[1001][1001];

        int colVal = 2;
        int rowVal = 0;
        int rowStart = 1;
        arr[0][1] = 1;
        for(int i=1; i<=1000; i++){
            arr[i][1] = arr[i-1][1] + rowVal;
            posMap.put(arr[i][1], new Pos(i, 1));
            int tmp = colVal;
            for(int j=2; j<=1000; j++, tmp++){
                arr[i][j] = arr[i][j-1] + tmp;
                posMap.put(arr[i][j], new Pos(i, j));

            }
            colVal += 1;
            rowVal += 1;
        }


        StringTokenizer st = null;
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if(p > q){
                int tmp = p;
                p = q;
                q = tmp;
            }

            Pos pPos = posMap.get(p);
            Pos qPos = posMap.get(q);
            int row = pPos.row + qPos.row;
            int col = pPos.col + qPos.col;


            sb.append("#" + t + " " + arr[row][col] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
