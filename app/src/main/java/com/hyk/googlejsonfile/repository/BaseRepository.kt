package com.hyk.googlejsonfile.repository

import com.hyk.googlejsonfile.e
import kotlinx.coroutines.withTimeout

abstract class BaseRepository {
    protected suspend fun <T> coroutineFunc(msg: String, block: suspend () -> T): T? {
        return try { //인터넷이 안될 때 등등 처리 추가
            return withTimeout(5_000) {
                block()
            }
        } catch (error: Throwable) {
            e("[$msg] error : ", error.toString())
            //throw CoroutineError("$msg error", error)
            null
        }
    }
    //protected class CoroutineError(message: String, cause: Throwable) : Throwable(message, cause)
}