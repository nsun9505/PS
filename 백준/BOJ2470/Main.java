package Backjoon.BOJ2470;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int ans[] = new int[2];
    static int base = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        ans[0] = arr[0];
        ans[1] = arr[N-1];
        base = ans[0] + ans[1];

        for(int i=0; i<N; i++){
            int left = i+1;
            int right = N-1;

            while(left <= right){
                int mid = (left + right) / 2;

                int sum = arr[i] + arr[mid];
                if(Math.abs(sum) < Math.abs(base)){
                    ans[0] = arr[i];
                    ans[1] = arr[mid];
                    base = sum;
                }

                // 0보다 크거나 같으면 0에 더 가까워지기 위해서 right가 위치한 값을 mid - 1로 낮춰야함.
                // 왜냐하면 arr은 정렬되어 있으므로 right가 mid-1 위치로 오면 right 위치의 값보다 더 작은 값을 가지므로
                // right를 낮춰서 0에 가까워지려고 노력해야 함
                if(sum >= 0)
                    right = mid - 1;
                // 위와 동일하게 0보다 작으면 left는 작은 수에 있으므로 mid+1로 와서 더 큰수를 가지게끔해서 값을 찾아야 함.
                else
                    left = mid+1;
            }
        }

        Arrays.sort(ans);
        sb.append(ans[0] + " " + ans[1]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
