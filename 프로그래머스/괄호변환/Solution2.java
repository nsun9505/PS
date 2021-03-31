package Programmers.괄호변환;

import java.util.Stack;

public class Solution2 {
    Stack<Character> stack = new Stack<>();

    public String solution(String p){
        if(isCorrect(p))
            return p;
        return recursive(p);
    }
    public String recursive(String str){
        if(str.length() == 0)
            return "";

        String u = getBalance(str);
        String v = str.substring(u.length());
        v = recursive(v);

        String ret = "";
        if(isCorrect(u))
            ret = u + v;
        else{
            ret = "(" + v + ")";
            for(int i=1; i<u.length()-1; i++)
                ret += u.charAt(i) == '(' ? ")" : "(";
        }
        return ret;
    }

    public String getBalance(String str){
        int open = 0;
        int close = 0;
        int idx = 0;

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(') open++;
            else close++;

            if(open == close){
                idx = i;
                break;
            }
        }

        return str.substring(0, idx+1);
    }

    public boolean isCorrect(String str){
        stack.clear();
        String ret = "";
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(')
                stack.push(ch);
            else{
                if(stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        if(stack.isEmpty())
            return true;
        return false;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.solution("()))((()"));
    }
}
