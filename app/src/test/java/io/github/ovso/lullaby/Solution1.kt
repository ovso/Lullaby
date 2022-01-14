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
import kotlin.test.assertTrue

/*
정수 배열에서 홀수 최대값 + 짝수 최대값 구하기
 */
class Solution1 {
  @Test
  fun main() {
    assertTrue {
      solution(intArrayOf(7, 13, 15, 13)) == 15
    }
  }

  fun solution(A: IntArray): Int {
    return 0
  }
}
