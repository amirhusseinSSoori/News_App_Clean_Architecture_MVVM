package com.amirhusseinsoori.newsapp.domain.usecase.base

import com.amirhusseinsoori.newsapp.domain.model.ApiError



interface UseCaseResponse<Type> {
    fun onSuccess(result: Type)
    fun onError(apiError: ApiError?)
}