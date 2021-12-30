package io.github.ovso.lullaby;

import org.junit.Test;

import java.util.Arrays;

public class SolutionTest_java {

    @Test
    public void main() {
//        Assert.assertEquals(0, solution(new int[]{1,4,7,3,3,5}));
        int solution = solution(new int[]{7, 5, 2, 7, 2, 7, 4, 7});
        System.out.println("solution:" + solution);
    }

    public int solution(int[] A) {
        int N = A.length;
        int[][] connections = new int[N][N];
        int[] size = new int[N];
        for (int i = 0; i < N; i++) {
            if (A[i] == i)
                continue;
            connections[i][size[i]++] = A[i];
            connections[A[i]][size[A[i]]++] = i;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int[] stack = new int[N];
            int stackstart = 0;
            int stackstop = 0;
            boolean[] visited = new boolean[N];
            stack[stackstop++] = i;
            int n = i - 1;
            int nextPossibleNumber = -1;
            visited[i] = true;
            while (stackstop > stackstart) {
                n++;
                int currentNumber = stack[stackstart++];
                nextPossibleNumber = Math.max(nextPossibleNumber, currentNumber);
                if (nextPossibleNumber == n)
                    answer++;
                for (int j = 0; j < size[currentNumber]; j++) {
                    int x = connections[currentNumber][j];
                    if (x < i)
                        continue;
                    if (visited[x])
                        continue;
                    visited[x] = true;
                    stack[stackstop++] = x;
                }
                Arrays.sort(stack, stackstart, stackstop);
            }
        }
        return answer;
    }
}