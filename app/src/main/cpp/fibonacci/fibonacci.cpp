//
// Created by littlefogcat on 2025/1/4.
//

#include "fibonacci.h"

int computeFibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return computeFibonacci(n - 1) + computeFibonacci(n - 2);
}