package Jungol.JO2577;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int size = N+K-1;
        int[] arr = new int[size];
        int[] set = new int[D+1];
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(br.readLine());
        for(int i=N, j=0; i<size; i++, j++)
            arr[i] = arr[j];

        int cnt = 0;
        for(int i=0; i<K; i++) {
            set[arr[i]] += 1;
            if(set[arr[i]] == 1)
                cnt++;
        }

        int answer = cnt;
        for(int start=0, end = K; end < N+K-1;start++, end++){
            set[arr[start]]--;
            if(set[arr[start]] == 0)
                cnt--;

            set[arr[end]]++;
            if(set[arr[end]] == 1)
                cnt++;

            int tmp = cnt;
            if(set[C] == 0)
                tmp++;

            if(tmp > answer)
                answer = tmp;
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
