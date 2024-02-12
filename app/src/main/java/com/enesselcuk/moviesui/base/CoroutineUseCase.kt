package com.enesselcuk.moviesui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class CoroutineUseCase {

    private var scope = CoroutineScope(Dispatchers.Main)
    private val dispatcher = Dispatchers.IO
    private var job: Job? = null

    fun execute() {

    }
}