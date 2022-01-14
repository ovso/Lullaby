package io.github.ovso.lullaby;

import org.junit.Test;

public class Solution1_java {
    @Test
    public void solution() {
        int[] A = {5,4,10,6,11};
        int size = A.length, i, even = 1, odd = 0;
        System.out.println("enter size::");
        int a[] = new int[size];
        System.out.println("enter array elements..");
        for (i = 0; i < size; i++) {
            a[i] = A[i];
        }
        for (i = 0; i < size; i++) {
            if (a[i] % 2 == 0) {
                if (even == 1) {
                    even = a[i];
                }
                if (even < a[i]) {
                    even = a[i];
                }
            } else {
                if (a[i] % 2 != 0) {
                    if (odd == 0) {
                        odd = a[i];
                    }

                    if (odd < a[i]) {
                        odd = a[i];
                    }
                }
            }

        }
        System.out.println("max of even is::" + even);
        System.out.println("max of odd is::" + odd);
    }

}
