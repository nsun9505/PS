package Backjoon.BOJ17413;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        StringBuilder tmpsb = new StringBuilder();
        boolean flag = false;
        for(int i=0; i<input.length(); i++){
            char ch = input.charAt(i);
            if(ch == '<'){
                sb.append(tmpsb.reverse().toString());
                sb.append(ch);
                tmpsb.setLength(0);
                flag = true;
            } else if(ch == '>'){
                sb.append(ch);
                flag = false;
            } else {
                if(flag)
                    sb.append(ch);
                else {
                    if(ch == ' '){
                        sb.append(tmpsb.reverse().toString());
                        sb.append(' ');
                        tmpsb.setLength(0);
                    } else {
                        tmpsb.append(ch);
                    }
                }
            }
        }

        sb.append(tmpsb.reverse().toString());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
