package SWEA.SWEA1225;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<10; t++) {
            int T = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Integer> queue = new LinkedList<>();
            for(int k=0; k<8; k++)
                queue.offer(Integer.parseInt(st.nextToken()));


            int add = 1;
            while(true) {
                int num = queue.poll() - add++;

                if(add > 5)
                    add = 1;

                if(num > 0)
                    queue.offer(num);
                else {
                    queue.offer(0);
                    break;
                }
            }
            sb.append("#" + T + " ");
            while(!queue.isEmpty()){
                sb.append(queue.poll() + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
