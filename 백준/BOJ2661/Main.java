import java.io.*;

public class Main {
    static int N;
    static char[] str;
    static char[] arr = {'1', '2', '3'};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        str = new char[N];

        solution('0');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean solution(char prev){
        if(sb.length() == N)
            return true;

        for(char ch : arr){
            if(ch == prev)
                continue;
            sb.append(ch);
            if(!check(sb.toString())) {
                sb.setLength(sb.length()-1);
                continue;
            }

            if(solution(ch))
                return true;

            sb.setLength(sb.length()-1);
        }
        return false;
    }

    public static boolean check(String str){
        int length = str.length();
        int loop = length / 2;
        int start = length - 1;
        int end = length;

        for(int i=1; i<=loop; i++){
            String left = str.substring(start-i, end-i);
            String right = str.substring(start, end);
            if(left.equals(right))
                return false;
            start--;
        }
        return true;
    }
}