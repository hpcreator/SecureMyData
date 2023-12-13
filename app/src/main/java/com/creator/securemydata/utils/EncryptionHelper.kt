package com.creator.securemydata.utils

import android.os.Build
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object EncryptionHelper {
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/ECB/PKCS5Padding"
    private const val ENCODING = "UTF-8"

    fun encrypt(input: String, key: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key.toByteArray(charset(ENCODING)).copyOf(16), ALGORITHM))
        val encryptedBytes = cipher.doFinal(input.toByteArray(charset(ENCODING)))
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(encryptedBytes)
        } else {
            ""
        }
    }

    fun decrypt(input: String, key: String): Result<String> {
        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(key.toByteArray(charset(ENCODING)).copyOf(16), ALGORITHM))
            val decryptedBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                cipher.doFinal(Base64.getDecoder().decode(input))
            } else {
                byteArrayOf()
            }
            Result.Success(String(decryptedBytes, charset(ENCODING)))
        } catch (e: Exception) {
            Result.Error("Decryption failed. Please check your key and try again.")
        }
    }
}