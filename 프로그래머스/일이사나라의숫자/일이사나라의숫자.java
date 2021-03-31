package Programmers.일이사나라의숫자;

public class 일이사나라의숫자 {
    public String solution(int n) {
        int[] arr = {4, 1, 2};
        String answer = "";
        while(n!=0){
            int idx = n % 3;
            answer = String.valueOf(arr[idx]) + answer;
            if(idx == 0)
                n -= 1;
            n/=3;

        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 40;
        int[] arr = {4, 1, 2};
        String answer = "";
        while(n!=0){
            int idx = n % 3;
            answer = String.valueOf(arr[idx]) + answer;
            if(idx == 0)
                n -= 1;
            n/=3;
        }
        System.out.println(answer);
    }
}
