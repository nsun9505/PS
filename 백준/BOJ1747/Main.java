package Backjoon.BOJ1747;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N == 1)
            N++;
        for(int num = N;;num++){
            if(!isPrime(num))
                continue;

            if(!isPallinedrom(String.valueOf(num)))
                continue;

            System.out.println(num);
            break;
        }
    }

    private static boolean isPrime(int num) {
        for(int i=2; i*i <= num; i++)
            if(num % i == 0)
                return false;
        return true;
    }

    private static boolean isPallinedrom(String str) {
        int len = str.length() / 2;
        if(str.length() % 2 == 0)
            len -= 1;
        for(int low = 0, high = str.length()-1; low <= len; low++, high--){
            if(str.charAt(low) != str.charAt(high))
                return false;
        }
        return true;
    }
}
