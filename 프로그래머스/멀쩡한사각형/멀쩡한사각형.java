package Programmers.멀쩡한사각형;

public class 멀쩡한사각형 {
    public long solution(int w, int h) {
        long a = Math.max(w, h);
        long b = Math.min(w, h);

        long g = gcd(a, b);
        return a * b - (a + b - g);
    }

    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}