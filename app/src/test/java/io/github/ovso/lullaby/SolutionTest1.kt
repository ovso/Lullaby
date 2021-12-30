package io.github.ovso.lullaby

import org.junit.Assert.assertEquals
import org.junit.Test

/*
There are N coins, each showing either heads or tails.
We would like all the coins to show the same face.
What is the minimum number of coins that must be reversed?
 */
class SolutionTest1 {
    @Test
    fun main() {
        assertEquals(2, solution(intArrayOf(1, 0, 0, 1, 0, 0)))
    }

    fun solution(A: IntArray):Int {
        val n = A.size
        var result = 0
        for (i in 0 until n - 1) {
            if (A[i] == A[i + 1]) result += 1
        }
        return result
    }
}