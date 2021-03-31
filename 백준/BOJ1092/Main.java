package Backjoon.BOJ1092;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] ship = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            ship[i] = -Integer.parseInt(st.nextToken());

        Arrays.sort(ship);

        int M = Integer.parseInt(br.readLine());
        boolean[] isMove = new boolean[M];
        st = new StringTokenizer(br.readLine());
        int[] boxList = new int[M];
        for(int i=0; i<M; i++)
            boxList[i] = -Integer.parseInt(st.nextToken());

        Arrays.sort(boxList);

        int sec = 0;

        int sum = 0;
        while(sum < M){
            int cnt = 0;
            int idx = 0;
            for(int i=0; i<M; i++){
                if(isMove[i])
                    continue;

                if(idx >= ship.length)
                    break;

                // 담을 수 있는 경우
                if((ship[idx]*(-1)) >= (boxList[i] * (-1))){
                    isMove[i] = true;
                    cnt++;
                    idx++;
                }
            }

            sum += cnt;

            if(cnt == 0) {
                sec = -1;
                break;
            }

            sec++;
        }
        sb.append(sec);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
