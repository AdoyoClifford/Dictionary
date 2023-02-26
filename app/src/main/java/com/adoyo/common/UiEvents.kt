package com.adoyo.common

sealed class UiEvents{
    data class SnackBarEvent(val message: String): UiEvents()
}
