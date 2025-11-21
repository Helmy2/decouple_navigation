package com.worldview.myapplication.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ConcurrentHashMap

/**
 * A thread-safe event bus for passing results between loosely coupled components (e.g., Navigation destinations).
 *
 * This class uses a [ConcurrentHashMap] to manage separate [MutableSharedFlow] instances for each result key,
 * allowing multiple independent result channels to exist simultaneously without interference.
 *
 * **Key Features:**
 * - **Thread Safety:** Uses `ConcurrentHashMap` for safe concurrent access on the JVM.
 * - **Dynamic Channels:** Channels are created lazily on demand.
 * - **Type Safety:** Uses reified inline functions to automatically use class names as keys.
 *
 * @see MutableSharedFlow
 */
class ResultBus {

    /**
     * Internal storage for flows.
     * Uses [ConcurrentHashMap] to ensure thread-safe creation and retrieval of flows.
     */
    private val _flows = ConcurrentHashMap<String, MutableSharedFlow<Any?>>()

    /**
     * Emits a result to the flow associated with [key].
     *
     * If the flow for [key] does not exist, it is created automatically.
     *
     * @param key The unique identifier for the result channel.
     * @param result The value to emit.
     */
    suspend fun setRawResult(key: String, result: Any?) {
        _flows.getOrPut(key) { MutableSharedFlow() }.emit(result)
    }

    /**
     * Retrieves the flow associated with [key].
     *
     * If the flow for [key] does not exist, it is created automatically.
     *
     * @param key The unique identifier for the result channel.
     * @return A [Flow] of [Any?] that emits updates for the given key.
     */
    fun getRawFlow(key: String): Flow<Any?> {
        return _flows.getOrPut(key) { MutableSharedFlow() }
    }

    /**
     * Emits a typed result using the class name of [T] as the default key.
     *
     * This is a convenient wrapper around [setRawResult] that enforces type safety at the call site.
     *
     * **Example:**
     * ```
     * resultBus.setResult("Success") // Key defaults to "kotlin.String"
     * resultBus.setResult(MyData(id = 1), "custom_key") // Custom key override
     * ```
     *
     * @param T The type of the result.
     * @param result The value to emit.
     * @param resultKey The unique key for the channel. Defaults to the full class name of [T].
     */
    suspend inline fun <reified T> setResult(result: T, resultKey: String = T::class.toString()) {
        setRawResult(resultKey, result)
    }

    /**
     * Retrieves a typed flow using the class name of [T] as the default key.
     *
     * This flow will cast emitted values to [T]. If a value of an incompatible type is emitted
     * to the same key, it will be filtered out (cast to null).
     *
     * **Example:**
     * ```
     * resultBus.getResult<String>().collect { value ->
     *     println("Received: $value")
     * }
     * ```
     *
     * @param T The expected type of the result.
     * @param resultKey The unique key for the channel. Defaults to the full class name of [T].
     * @return A [Flow] emitting values of type [T], or null if casting fails.
     */
    inline fun <reified T> getResult(resultKey: String = T::class.toString()): Flow<T?> {
        return getRawFlow(resultKey).map { it as? T }
    }
}
