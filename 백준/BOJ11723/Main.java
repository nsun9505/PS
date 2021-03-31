package Backjoon.BOJ11723;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int set = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int M = Integer.parseInt(br.readLine());
        int num = 0;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch(cmd){
                case "add":
                    add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    remove(Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    sb.append((check(Integer.parseInt(st.nextToken())) == true ? 1 : 0) + "\n");
                    break;
                case "toggle":
                    toggle(Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    set = (1 << 21) - 1;
                    break;
                case "empty":
                    set = 0;
            }
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean check(int num) {
        int ret = set & (1 << num);
        if (ret > 0)
            return true;
        return false;
    }

    public static void add(int num){
        if(!check(num))
            set |= 1 << num;
    }

    public static void remove(int num){
        if(check(num))
            set &= (~(1 << num));
    }

    public static void toggle(int num){
        if(check(num))
            remove(num);
        else
            add(num);
    }
}
