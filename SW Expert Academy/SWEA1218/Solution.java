package SWEA.SWEA1218;

import java.io.*;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int t=1; t<=10; t++) {
            Stack<Character> stack = new Stack<>();
            int len = Integer.parseInt(br.readLine());
            String input = br.readLine();

            boolean flag = true;
            for(int i=0; i<len; i++){
                char bracket = input.charAt(i);
                if(bracket == '<' || bracket == '{' || bracket == '[' || bracket == '(')
                    stack.push(bracket);
                else{
                    if(stack.isEmpty()){
                        flag = false;
                        break;
                    }

                    if (getPair(bracket) == stack.peek())
                        stack.pop();
                    else {
                        flag = false;
                        break;
                    }
                }
            }

            if(!stack.isEmpty() || !flag)
                bw.write("#" + t + " 0\n");
            else
                bw.write("#" + t + " 1\n");

        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static char getPair(char pair){
        if(pair == ')')
            return '(';
        else if(pair == ']')
            return '[';
        else if(pair == '}')
            return '{';
        return '<';
    }
}
