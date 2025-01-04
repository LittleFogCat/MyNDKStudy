package tech.xiaoniu.myndkstudy

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author littlefogcat
 * @email littlefogcat@foxmail.com
 */
class NdkBridge {
    companion object {
        init {
            System.loadLibrary("my-native-code")
        }
    }

    private external fun computeFibonacciNative(i: Int): FibonacciResult

    suspend fun computeFibonacci(i: Int): FibonacciResult {
        return withContext(Dispatchers.Default) {
            computeFibonacciNative(i)
        }
    }
}