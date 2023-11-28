package com.creator.securemydata.utils

import android.os.Build
import java.security.Key
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object EncryptionHelper {
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/ECB/PKCS5Padding"
    private const val ENCODING = "UTF-8"

    private val key: Key = SecretKeySpec("It is top secret Key 🤫".toByteArray(charset(ENCODING)).copyOf(16), ALGORITHM)

    fun encrypt(input: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedBytes = cipher.doFinal(input.toByteArray(charset(ENCODING)))
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(encryptedBytes)
        } else {
            ""
        }
    }

    fun decrypt(input: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decryptedBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            cipher.doFinal(Base64.getDecoder().decode(input))
        } else {
            byteArrayOf()
        }
        return String(decryptedBytes, charset(ENCODING))
    }
}