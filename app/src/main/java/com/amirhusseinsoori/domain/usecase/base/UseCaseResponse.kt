package com.amirhusseinsoori.domain.usecase.base

import com.amirhusseinsoori.domain.model.ApiError



interface UseCaseResponse<Type> {
    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}