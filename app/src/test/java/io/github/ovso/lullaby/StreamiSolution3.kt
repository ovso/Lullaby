/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.ovso.lullaby

import org.junit.Test

/*
we are given two arrays A and B, consisting of N integers each. We want to merge them into array C.
For each index K(from 0 to N -1 ), C[k] can be either A[K] or B[K]. For example, arrays A = [1,2,4,3]
and B = [1,3,2,3] can be merged in the following way:
 */
class StreamiSolution3 {
  @Test
  fun main() {
    println(
      solution(intArrayOf(1,2,4,3), intArrayOf(1,3,2,3))
    )
  }

  fun solution(A: IntArray, B: IntArray): Int {
    val len = A.size
    var minimumRejectedElement = Int.MAX_VALUE
    for (i in 0 until len) {

      // if numbers are same in both arrays for a given index then just skip the index
      if (A[i] == B[i]) continue

      // otherwise whichever number is smaller in both arrays is rejected from the
      // merged array
      val rejectElement = if (A[i] < B[i]) A[i] else B[i]

      // now comparing if its the lowest in all rejected ones.
      minimumRejectedElement =
        if (rejectElement < minimumRejectedElement) rejectElement else minimumRejectedElement
    }

    // if the arrays are identical then return largest element +1
    return if (minimumRejectedElement == Int.MAX_VALUE) A[len - 1] + 1 else minimumRejectedElement
  }
}
