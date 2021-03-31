package Backjoon.BOJ10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == '(')
                stack.push(i);
            else{
                int start = stack.pop();
                if(i - start == 1)
                    ans += stack.size();
                else
                    ans++;
            }
        }

        System.out.println(ans);
    }
}
