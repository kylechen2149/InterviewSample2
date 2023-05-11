package com.kylechen2149.taipeitravelsample.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @Author ChenXingYu
 * @Date 2020/4/9-15:21
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class FlowCallAdapter<R>(
    private val responseType: Type,
    private val isAsync: Boolean
) : CallAdapter<R, Flow<R?>> {

    override fun responseType() = responseType


    override fun adapt(call: Call<R>): Flow<R?> {
        return callFlow(call, isAsync)
    }

    private fun <R> callFlow(call: Call<R>, isAsync: Boolean): Flow<R> {
        val started = AtomicBoolean(false)
        return callbackFlow {
            if (started.compareAndSet(false, true)) {
                if (isAsync) callEnqueueFlow(call) else callExecuteFlow(call)
                awaitClose { call.cancel() }
            }
        }
    }
}