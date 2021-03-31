package SWEA.SWEA3499;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++){
            int N = Integer.parseInt(br.readLine());

            int left = N/2;
            if(N % 2 == 1)
                left++;

            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<String> top = new LinkedList<>();

            for(int i=0; i<left; i++)
                top.offer(st.nextToken());

            sb.append("#" + t + " ");
            for(int i=0; i<N/2; i++)
                sb.append(top.poll() + " " + st.nextToken()+ " ");

            if(N % 2 == 1)
                sb.append(top.poll() + "\n");
            else
                sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
