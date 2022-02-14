package io.github.ovso.lullaby

import org.junit.Test

class KakaoW1 {
  @Test
  fun main() {
    println(
      solution(2613)
    )
  }

  fun solution(n:Int):Int {
    val toCharArray = n.toString().toCharArray()
    val ascending = toCharArray.sorted().joinToString("")
    toCharArray.sortDescending()
    val descending = toCharArray.joinToString("")
    return ascending.toInt() + descending.toInt()
  }
}
