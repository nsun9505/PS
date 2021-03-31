package SWEA.SWEA1952;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] pay = new int[4];
        int[] month = new int[13];
        int[] dp = new int[13];
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++)
                pay[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=12; i++) {
                month[i] = Integer.parseInt(st.nextToken());
                dp[i] = 0;
            }

            for(int i=1; i<=12; i++) {
                int dayPrice = pay[0] * month[i] + dp[i-1];
                int monthPrice = pay[1] + dp[i-1];
                int minPrice = Math.min(dayPrice, monthPrice);

                if(i >= 3)
                    minPrice = Math.min(minPrice, pay[2] + dp[i-3]);
                if(i >= 12)
                    minPrice = Math.min(minPrice, pay[3]);
                dp[i] = minPrice;
            }
            sb.append("#" + t + " " + dp[12] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
