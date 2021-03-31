package Backjoon.BOJ1793;

import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        BigInteger[] dp = new BigInteger[251];
        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");
        BigInteger mul = new BigInteger("2");
        for(int i=3; i<=250; i++)
            dp[i] = dp[i-2].multiply(mul).add(dp[i-1]);

        String input = "";
        while((input = br.readLine()) != null){
            int num = Integer.parseInt(input);
            sb.append(dp[num] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
