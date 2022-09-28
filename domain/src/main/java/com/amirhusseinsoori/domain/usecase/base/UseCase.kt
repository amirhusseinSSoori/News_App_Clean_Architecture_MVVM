package com.amirhusseinsoori.domain.usecase.base


abstract class UseCase< in Params,Type>() where Type : Any {
    abstract suspend fun execute(params: Params? = null): Type
}