package com.example.webncrafttest.data.networkmodel

/**
 * This is a utility class that will be responsible to communicate the current state of Network Call to the UI Layer
 *
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */
data class NetworkResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): NetworkResult<T> {
            return NetworkResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): NetworkResult<T> {
            return NetworkResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): NetworkResult<T> {
            return NetworkResult(Status.LOADING, data, null)
        }
    }
}