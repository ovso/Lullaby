package io.github.ovso.lullaby

import org.junit.Test

/*
Given four digits, count how many valid times can be displayed on a digital clock(in 24-hour format)
using those digits. the earliest time is 00:00 and the latest time is 23:59
 */
class Solution4 {
  @Test
  fun main() {
    solution()
  }

  private fun solution() {
    println(TimeSolution().toTime(intArrayOf(1,2,3,4)))
  }
}

class TimeSolution {
  private var maxTime = -1
  fun toTime(A: IntArray): String {
    maxTime = -1
    for (i1 in A.indices) for (i2 in A.indices) for (i3 in A.indices) {
      // skip duplicate elements
      if (i1 == i2 || i2 == i3 || i1 == i3) continue
      // the total sum of indices is 0 + 1 + 2 + 3 = 6.
      val i4 = 6 - i1 - i2 - i3
      val perm = intArrayOf(A[i1], A[i2], A[i3], A[i4])
      // check if the permutation can form a time
      validateTime(perm)
    }
    return if (maxTime == -1) "" else String.format("%02d:%02d", maxTime / 60, maxTime % 60)
  }

  private fun validateTime(perm: IntArray) {
    val hour = perm[0] * 10 + perm[1]
    val minute = perm[2] * 10 + perm[3]
    if (hour < 24 && minute < 60) maxTime = Math.max(maxTime, hour * 60 + minute)
  }
}
