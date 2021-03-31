package SWEA.SWEA1289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            int ans = 0;
            char[] input = br.readLine().toCharArray();
            char prev = '0';
            for(int i=0; i<input.length; i++){
                if(prev == input[i])
                    continue;
                ans++;
                prev = input[i];
            }
            System.out.println("#"+t+" " + ans);
        }
    }
}
