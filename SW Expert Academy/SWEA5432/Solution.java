package SWEA.SWEA5432;

import java.io.*;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            int ans = 0;
            String input = br.readLine();
            Stack<Integer> stack = new Stack<>();

            for(int i=0; i<input.length(); i++){
                if(input.charAt(i) == '('){
                    stack.push(i);
                } else {
                    int idx = stack.pop();
                    if(i - idx == 1)
                        ans += stack.size();
                    else
                        ans++;
                }
            }
            sb.append("#" + t + " " + ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
