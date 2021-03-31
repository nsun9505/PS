package SWEA.SWEA3307;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for(int t=1; t<=T; t++){
            int N = Integer.parseInt(br.readLine());
            list.clear();
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(Integer.parseInt(st.nextToken()));
            for(int i=1; i<N; i++){
                int num = Integer.parseInt(st.nextToken());
                if(list.get(list.size()-1) < num)
                    list.add(num);
                else{
                    int index = lowerBound(list, num);
                    list.set(index, num);
                }
            }

            sb.append("#" + t + " " + list.size() + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int lowerBound(ArrayList<Integer> list, int target){
        int left = 0;
        int right = list.size()-1;

        while(left < right){
            int mid = (left + right) / 2;
            int num = list.get(mid);

            if(num >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
