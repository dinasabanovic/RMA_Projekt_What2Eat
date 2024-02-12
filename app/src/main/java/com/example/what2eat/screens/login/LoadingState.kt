package com.example.what2eat.screens.login

data class LoadingState(val status: Status, val message: String? = null) {
    companion object {
        val IDLE = (Status.IDLE)
        val SUCCESS = (Status.SUCCESS)
        val LOADING = (Status.LOADING)
        val FAILED = (Status.FAILED)
    }

    enum class Status {
        SUCCESS,
        FAILED,
        LOADING,
        IDLE
    }
}
