package com.appdevpwl.hashgen

import androidx.lifecycle.ViewModel
import java.security.MessageDigest

class HashViewModel : ViewModel() {
    fun generateHash(s: String, textToHash: String): String {
        val bytes = MessageDigest.getInstance(s).digest(textToHash.toByteArray())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray): String {
        return byteArray.joinToString("") { "%02x".format(it) }
    }
}