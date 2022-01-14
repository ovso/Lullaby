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
write a function solution that, given a string S consisting of N letters 'a' and/or 'b' returns true when
all occurrences of letter 'a' are before all occurrences of letter 'b' and returns false otherwise.
N개의 문자 'a' 및/또는 'b'로 구성된 문자열 S가 주어지면 문자 'a'의 모든 발생이 문자 'b'의 모든 발생보다 앞에 있으면
true를 반환하고 그렇지 않으면 false를 반환하는 함수 솔루션을 작성하십시오.
 */
class StreamiSolution2 {
  @Test
  fun main() {
    assertTrue {
      solution("ba").not()
    }
  }

  fun solution(S: String): Boolean {
    return isAb(S)
  }

  fun isAb(S: String): Boolean {
    val s: String = S
    var l = s.length - 1
    var i = 0
    var stat = 1
    var x = 1
    var y = 0
    while (l >= 0) {
      if (s[i] == 'a') {
        x = 0
        i += 1
        l -= 1
        if (x == 0 && y == 1) {
          stat = 0
          break
        }
      } else if (s[i] == 'b') {
        y = 1
        i += 1
        l -= 1
      }
    }
    return stat != 0
  }


}
