package Backjoon.BOJ14003;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(st.nextToken()) + 1000000000);

        /*
         * pos : i번째 수가 위치하는 곳
         * arr : 입력을 담아두는 곳
         */
        int[] pos = new int[N];
        int[] arr = new int[N];
        arr[0] = list.get(0);

        // maxIdx : 가장 긴 증가 부분 수열의 끝에 오는 숫자의 인덱스
        int maxIdx = 0;
        for(int i=1; i<N; i++){

            arr[i] = Integer.parseInt(st.nextToken()) + 1000000000;

            // 맨 뒤보다 크다면 바로 넣기
            if(list.get(list.size()-1) < arr[i]) {
                // i번째가 위치하는 곳은 list의 가장 마지막 위치
                pos[i] = list.size();
                maxIdx = pos[i];
                list.add(arr[i]);
            }
            else{
                // 들어갈 곳을 찾기
                int index = lowerbound(list, arr[i]);

                // 들어갈 곳에 넣어주기
                list.set(index, arr[i]);

                // i번째 수가 어느 곳에 들어가게 되는지 알아봄.
                pos[i] = index;
            }
        }

        int num = Integer.MAX_VALUE;
        int cur = maxIdx;
        
        // 스택을 사용해서 담음
        Stack<Integer> stack = new Stack<>();
        for(int i=N-1; i>=0; i--){
            // 배열의 뒤부터 보면서, 가장 긴 증가 부분 수열에서 cur번째에 있는 숫자이면서
            // cur+1번째에 나온 숫자보다 작은 경우 stack에 담음
            if(pos[i] == cur && arr[i] < num){
                // 찾았다면 스택에 담고
                stack.push(arr[i]);
                // 이제 cur-1번째에 나오는 숫자를 찾아야 하므로 num을 cur번째의 수로 바꿔줌
                num = arr[i];
                // cur번째를 찾았으므로 cur-1번째를 찾기 위해 cur의 값을 하나 줄임
                cur--;
            }
            // cur이 0미만이면 더 이상 나올 것이 없으므로 종료
            if(cur < 0)
                break;
        }

        sb.append(stack.size()+"\n");
        while(!stack.isEmpty())
            sb.append((stack.pop() - 1000000000) + " ");


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lowerbound(ArrayList<Integer> list, int num) {
        int left = 0;
        int right = list.size()-1;

        while(left < right){
            int mid = (left + right) / 2;

            if(list.get(mid) >= num)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
