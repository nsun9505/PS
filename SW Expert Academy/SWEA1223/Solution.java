package SWEA.SWEA1223;

import java.io.*;
import java.util.HashMap;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('*', 1);
        priority.put('+', 0);

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            int length = Integer.parseInt(br.readLine());
            String input = br.readLine();
            Stack<Character> stack = new Stack<>();
            String exp = "";
            for(int i=0; i<input.length(); i++) {
                char ch = input.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    exp += String.valueOf(ch);
                } else {
                    if (!stack.isEmpty()){
                        if (priority.get(stack.peek()) > priority.get(ch)) {
                            while (!stack.isEmpty()) {
                                if (stack.peek() == ch)
                                    break;
                                exp += String.valueOf(stack.pop());
                            }
                        }
                    }
                    stack.push(ch);
                }
            }

            while(!stack.isEmpty())
                exp += String.valueOf(stack.pop());

            Stack<Long> calc = new Stack<>();
            for(int i=0; i<exp.length(); i++){
                if(exp.charAt(i) == '*' || exp.charAt(i) == '+'){
                    long op1 = calc.pop();
                    long op2 = calc.pop();

                    if(exp.charAt(i) == '*')
                        calc.push(op1 * op2);
                    else
                        calc.push((op1 + op2));
                } else {
                  calc.push(Long.parseLong(exp.charAt(i)+""));
                }
            }
            sb.append("#" + t+ " " + calc.pop() + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
