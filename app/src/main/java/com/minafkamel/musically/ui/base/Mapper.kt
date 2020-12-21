package com.minafkamel.musically.ui.base

/**
 * General interface that apply the mapping from one type [Input] to another [Output]
 */
interface Mapper<Input, Output> {

    /**
     * Mapping method
     */
    fun toModel(input: Input): Output
}