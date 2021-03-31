package SWEA.SWEA1210;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        char[][] map = new char[100][100];
        for(int i = 1; i<=10; i++){
            int t = Integer.parseInt(br.readLine());

            int curRow = 0;
            int curCol = 0;
            for(int row = 0; row < 100; row++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int col = 0; col < 100; col++) {
                    map[row][col] = st.nextToken().charAt(0);
                    if(map[row][col] == '2'){
                        curRow = row;
                        curCol = col;
                    }
                }
            }

            while(curRow > 0){
                map[curRow][curCol] = '0';
                if(curCol - 1 >= 0 && map[curRow][curCol-1] == '1'){
                    curCol -= 1;
                } else if(curCol + 1 < 100 && map[curRow][curCol+1] == '1') {
                    curCol += 1;
                } else{
                    curRow -= 1;
                }
            }

            sb.append("#" + t + " " + curCol + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
