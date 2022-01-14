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

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Solution3 {
  @Test
  fun main() {
    assertTrue {
      solution(intArrayOf(7, 13, 15, 13)) == 15
    }
  }

  fun solution(A: IntArray): Int {
    val size = A.size
    var even = 0
    var odd = 0
    println("enter size::")
    val a = IntArray(size)
    println("enter array elements..")
    var i = 0
    while (i < size) {
      a[i] = A[i]
      i++
    }
    i = 0
    while (i < size) {
      if (a[i] % 2 == 0) {
        if (even == 1) {
          even = a[i]
        }
        if (even < a[i]) {
          even = a[i]
        }
      } else {
        if (a[i] % 2 != 0) {
          if (odd == 0) {
            odd = a[i]
          }
          if (odd < a[i]) {
            odd = a[i]
          }
        }
      }
      i++
    }
    println("even: $even")
    println("odd: $odd")
    return even + odd
  }
}
