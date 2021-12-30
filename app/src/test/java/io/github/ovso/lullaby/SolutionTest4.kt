package io.github.ovso.lullaby

import org.junit.Test
import java.util.*

/*
You want to spend your next vacation in a foreign country.
In the summer you are free for N consecutive days.
 */
class SolutionTest4 {
    @Test
    fun main() {
//        assertEquals(2, solution(intArrayOf(1, 4, 7, 3, 3, 5)))
        println("-------------")
        println(solution(intArrayOf(7,3,7,3,1,3,4,1)))
        println("-------------")
    }

    fun solution(A: IntArray): Int {
        val N = A.size
        val connections = Array(N) { IntArray(N) }
        val size = IntArray(N)
        for (i in 0 until N) {
            if (A[i] == i) continue
            connections[i][size[i]++] = A[i]
            connections[A[i]][size[A[i]]++] = i
        }
        var answer = 0
        for (i in 0 until N) {
            val stack = IntArray(N)
            var stackstart = 0
            var stackstop = 0
            val visited = BooleanArray(N)
            stack[stackstop++] = i
            var n = i - 1
            var nextPossibleNumber = -1
            visited[i] = true
            while (stackstop > stackstart) {
                n++
                val currentNumber = stack[stackstart++]
                nextPossibleNumber = Math.max(nextPossibleNumber, currentNumber)
                if (nextPossibleNumber == n) answer++
                for (j in 0 until size[currentNumber]) {
                    val x = connections[currentNumber][j]
                    if (x < i) continue
                    if (visited[x]) continue
                    visited[x] = true
                    stack[stackstop++] = x
                }
                Arrays.sort(stack, stackstart, stackstop)
            }
        }
        return answer
    }
}