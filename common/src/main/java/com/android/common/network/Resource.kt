package com.android.common.network

/**
 * A sealed class that represents a resource with a state. It can be in one of the following states:
 * Success, Error, or Loading. Each state can hold relevant data or a message.
 *
 * @param T The type of data being handled by the resource.
 * @param data The data held by the resource, if any. It's nullable to accommodate the absence of data.
 * @param message An optional message, primarily used in the Error state to describe what went wrong.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}