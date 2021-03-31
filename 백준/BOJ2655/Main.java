package Backjoon.BOJ2655;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static Brick[] bricks;
    static int[] top;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        bricks = new Brick[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            bricks[i] = new Brick(i+1, area, height, weight);
        }

        Arrays.sort(bricks, new Comparator<Brick>() {
            @Override
            public int compare(Brick o1, Brick o2) {
                return o1.area - o2.area;
            }
        });

        int[] dp = new int[N];
        top = new int[N];
        Arrays.fill(top, -1);
        int maxIdx = 0;
        int max = 0;
        for(int i=0; i<N; i++){
            dp[i] = bricks[i].height;
            for(int j=0; j<i; j++){
                if(bricks[i].weight > bricks[j].weight && dp[i] < dp[j] + bricks[i].height){
                    dp[i] = dp[j] + bricks[i].height;
                    top[i] = j;
                }
            }
            if(max < dp[i]){
                max = dp[i];
                maxIdx = i;
            }
        }

        int start = maxIdx;
        ArrayList<Integer> list = new ArrayList<>();
        while(start != -1){
            list.add(bricks[start].index);
            start = top[start];
        }

        sb.append(list.size() + "\n");
        for(int i=list.size()-1; i>=0; i--)
            sb.append(list.get(i)+"\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Brick{
        int index;
        int area;
        int height;
        int weight;

        public Brick(int index, int area, int height, int weight) {
            this.index = index;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }
    }
}