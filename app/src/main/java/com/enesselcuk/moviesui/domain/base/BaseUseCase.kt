package com.enesselcuk.moviesui.domain.base


interface BaseUseCase<in input, output:Any> {

    suspend fun execute(input: input): BaseUiSateUseCase<output>

}