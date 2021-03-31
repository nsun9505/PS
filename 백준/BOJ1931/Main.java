package Backjoon.BOJ1931;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        StringTokenizer st = null;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if(o1[1] > o2[1])
                            return 1;
                        else if(o1[1] == o2[1])
                            return o1[0] - o2[0];
                        return -1;
                    }
                }
        );

            int cnt = 1;
            int end = arr[0][1];
            for (int j = 1; j < N; j++) {
                if (end <= arr[j][0]) {
                    end = arr[j][1];
                    cnt++;
                }
            }

        sb.append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

