package Programmers.징검다리;

import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        long left = 1;
        long right = 1000000000;

        Arrays.sort(rocks);
        int[] arr = new int[rocks.length + 1];
        arr[0] = 0;
        for(int i=1; i<=rocks.length; i++)
            arr[i] = rocks[i-1];

        while(left <= right){
            long mid = (left + right) / 2;
            int start = 0;
            int cnt = 0;
            int maxDist = Integer.MAX_VALUE;
            for(int i=1; i<arr.length; i++){
                // arr[start] + mid : arr[start]에서 mid만큼 더했을 때
                // 몇 개의 돌을 건너뛰게 되는지를 카운트합니다.
                if(arr[start] + mid > arr[i])
                    cnt++;
                else{
                    // start에서 뛰어서 도착한 곳!
                    // 즉, arr[start] + mid보다 크거나 같은 arr[i]
                    // 건너뛰었을 때 arr[i]와 arr[start]의 사이 거리를 구해서 가장 구간 길이를 얻습니다.
                    maxDist = Math.min(arr[i] - arr[start], maxDist);
                    start = i;
                }
            }

            // 카운트한 돌의 개수 = 제거할 돌의 개수
            // 그 개수가 주어진 n보다 작거나 같다면 left를 mid + 1로 조정해서 더 탐색
            // 작거나 같다는 의미는 아직 좀 더 mid를 크게 해도 되는 것이므로 mid + 1로 left를 세팅
            if(cnt <= n){
                left = mid + 1;
                answer = Math.max(maxDist, answer);
            }
            // 큰 경우에는 덜 건너뛰어야 하므로 right를 mid - 1로 세팅해서
            // mid의 크기를 줄입니다!
            else {
                right = mid - 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(25, new int[] {2, 14, 11, 21, 17}, 2));
    }
}