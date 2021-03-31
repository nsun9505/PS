package SWEA.SWEA1208;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[100];
        for(int t=1; t<=10; t++) {
            int dumpCnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<arr.length; i++)
                arr[i] = Integer.parseInt(st.nextToken());
            
            for(int cnt=0; cnt<dumpCnt; cnt++){
                int max = 0;
                int maxIdx = 0;
                int min = 101;
                int minIdx = 0;

                // 가장 높은 곳, 가장 낮은 곳 찾기
                for(int i=0; i<arr.length; i++){
                    if(max <= arr[i]) {
                        max = arr[i];
                        maxIdx = i;
                    }

                    if(arr[i] <= min){
                        min = arr[i];
                        minIdx = i;
                    }
                }

                // 평탄화 작업 완료
                if(maxIdx == minIdx)
                    break;

                // 평탄화 작업 진행
                arr[maxIdx] -= 1;
                arr[minIdx] += 1;
            }

            // 가장 높은 곳과 가장 낮은 곳의 차이
            int max = 0;
            int min = 101;
            for(int i=0; i<arr.length; i++){
                if(max < arr[i])
                    max = arr[i];
                if(arr[i] < min)
                    min = arr[i];
            }
            sb.append("#" + t + "  " + (max - min) + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
