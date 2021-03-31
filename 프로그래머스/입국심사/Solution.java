package Programmers.입국심사;

import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 1;
        // 1 ~ 심사관 중 최대 검사 시간 * n
        /*
            심사관 중 최대 검사 시간 * n의 의미는
            심사관 중 가장 오랜 시간으로 n명을 처리하는 것이
            가장 오래걸리는 시간이기 때문이다.
         */
        // 심했네.. n까지 long으로 바꿔줘야 하는것인가?
        long right = times[times.length-1] * (long)n;
        // long ~ right의 범위는 중간 시간인 mid가 주어졌을 때
        // 각 심사관의 times[i]이 mid만큼의 시간이 주어졌을 때
        // 몇명만큼 처리할 수 있는가를 검사해본다.
        /*
            n = 6, times = {6, 10}이라고 해보자.
            그러면 처음에는 6 * 10이 right로 잡힌다.
            (1+60)/2 = 30이므로
            30 / 6 = 5, 30 / 10 = 3이므로 총 8명을 30초에 처리가 가능하다.
            하지만 n은 6이므로 8보다 작으니깐 2명을 더 처리하는 것은 불필요하다.
            그래서 몇명을 처리했는지를 cnt라고 했을 때,
            n <= cnt이면 right = mid로 줄여서 mid의 크기를 줄여서 나간다.
            n > cnt이면 해당 mid만큼을 시간만큼 심사관들이 처리하면 n만큼 처리하지 못하므로
            left = mid + 1로 해서 mid의 크기를 늘려서 탐색을 이어나간다.
         */

        // 이분탐색
        while(left < right){
            long mid = (left + right) / 2;
            long cnt = 0;

            for(int i=0; i<times.length; i++)
                cnt += mid / times[i];

            if(cnt < n){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(6, new int[] {7, 10}));
    }
}