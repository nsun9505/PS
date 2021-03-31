package Jungol.JO1828;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Element[] arr = new Element[N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());
            arr[i] = new Element(min, max);
        }

        Arrays.sort(arr);

        int cnt = 1;
        int end = arr[0].max;
        for(int i=1; i<N; i++)
            if(end < arr[i].min){
                end = arr[i].max;
                cnt++;
            }
        sb.append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element implements Comparable<Element> {
        int min;
        int max;

        public Element(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Element o) {
            if(this.max > o.max)
                return 1;
            else if(this.max == o.max)
                return this.min - o.min;
            return -1;
        }
    }
}
