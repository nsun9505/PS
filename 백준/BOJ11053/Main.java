package Backjoon.BOJ11053;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        list.add(Integer.parseInt(st.nextToken()));

        for(int i=1; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(list.get(list.size()-1) < num)
                list.add(num);
            else{
                int idx = lowerBound(list, num);
                list.set(idx, num);
            }
        }
        sb.append(list.size());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lowerBound(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size()-1;

        while(left < right){
            int mid = (left + right) / 2;
            int num = list.get(mid);
            if(target <= num){
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
}
