import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board.length;
        Queue<Integer>[] queues = new Queue[N + 1];
        for (int i = 1; i <= N; i++)
            queues[i] = new LinkedList<>();


        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if (board[i][j] == 0)
                    continue;
                queues[j+1].add(board[i][j]);
            }
        }

        Stack<Integer> stack = new Stack<>();
        for(int index : moves){
            if(queues[index].isEmpty())
                continue;

            int number = queues[index].poll();

            stack.push(number);
            int cnt = 0;
            while(!stack.isEmpty()){
                if(stack.peek() != number)
                    break;
                stack.pop();
                cnt++;
            }

            if(cnt == 1)
                stack.push(number);
            else
                answer += cnt;
        }

        return answer;
    }
}
