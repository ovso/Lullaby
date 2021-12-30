package io.github.ovso.lullaby

import org.junit.Assert.assertEquals
import org.junit.Test

/*
There are N coins, each showing either heads or tails.
We would like all the coins to show the same face.
What is the minimum number of coins that must be reversed?
 */
class SolutionTest2 {
    @Test
    fun main() {
        assertEquals(4, solution(intArrayOf(2, 2, 3, 4, 3, 3, 2, 2, 1, 1, 2, 5)))
    }

    fun solution(A: IntArray): Int {
        if (A.size == 1) return 1
        var count = 1
        var i = 0
        var j = i + 1
        while (i < A.size && j < A.size) {
            if (A[j] == A[i]) {
                ++i
                ++j
            } else if (A[j] > A[i]) {
                ++count
                var k = j + 1
                while (k < A.size && A[k] >= A[k - 1]) {
                    ++k
                }
                if (k == A.size) return count
                i = k - 1
                j = k
            } else {
                ++count
                var k = j + 1
                while (k < A.size && A[k] <= A[k - 1]) {
                    ++k
                }
                if (k == A.size) return count
                i = k - 1
                j = k
            }
        }
        return count
    }
}