package com.amirhusseinsoori.domain.usecase.base


import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

abstract class UseCase< in Params,Type>() where Type : Any {

    abstract suspend fun execute(params: Params? = null): Type

    fun invoke(scope: CoroutineScope, params: Params?, onResult: UseCaseResponse<Type>?) {
        scope.launch {
            try {
                val result = execute(params)
                onResult?.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult?.onError(e.message?:"")
            } catch (e: Exception) {
                e.printStackTrace()
                onResult?.onError(e.message?:"")
            }
        }
    }

}