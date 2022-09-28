package com.amirhusseinsoori.domain.usecase.base


interface UseCaseResponse<Type> {
    fun onSuccess(result: Type)
    fun onError(Message: String)
}