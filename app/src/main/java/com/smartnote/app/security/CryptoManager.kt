package com.smartnote.app.security

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.KeyStore
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoManager @Inject constructor(
    context: Context
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        KEY_PREFS_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val keyStore: KeyStore = KeyStore.getInstance(ANDROID_KEYSTORE_PROVIDER).apply {
        load(null)
    }

    init {
        getOrCreateKeystoreAesKey()
    }

    private fun getOrCreateKeystoreAesKey(): SecretKey {
        val existingKey = keyStore.getKey(KEYSTORE_ALIAS, null) as? SecretKey
        if (existingKey != null) return existingKey

        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            ANDROID_KEYSTORE_PROVIDER
        )
        val keyGenSpec = KeyGenParameterSpec.Builder(
            KEYSTORE_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()

        keyGenerator.init(keyGenSpec)
        return keyGenerator.generateKey()
    }

    fun getOrCreateDatabasePassphrase(): ByteArray {
        val savedPassphraseHex = encryptedSharedPreferences.getString(KEY_DB_PASSPHRASE, null)
        if (savedPassphraseHex != null) {
            return hexToBytes(savedPassphraseHex)
        }

        val randomBytes = ByteArray(32)
        SecureRandom().nextBytes(randomBytes)
        val passphraseHex = bytesToHex(randomBytes)

        encryptedSharedPreferences.edit()
            .putString(KEY_DB_PASSPHRASE, passphraseHex)
            .apply()

        return randomBytes
    }

    fun hashPin(pin: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val saltedPin = "$PIN_SALT_PREFIX$pin$PIN_SALT_SUFFIX"
        val hashBytes = digest.digest(saltedPin.toByteArray(Charsets.UTF_8))
        return bytesToHex(hashBytes)
    }

    fun saveHashedPin(hashedPin: String) {
        encryptedSharedPreferences.edit()
            .putString(KEY_HASHED_PIN, hashedPin)
            .apply()
    }

    fun getHashedPin(): String? {
        return encryptedSharedPreferences.getString(KEY_HASHED_PIN, null)
    }

    fun verifyPin(pin: String): Boolean {
        val storedHash = getHashedPin() ?: return false
        return storedHash == hashPin(pin)
    }

    fun clearPin() {
        encryptedSharedPreferences.edit()
            .remove(KEY_HASHED_PIN)
            .apply()
    }

    fun setAppLockEnabled(enabled: Boolean) {
        encryptedSharedPreferences.edit()
            .putBoolean(KEY_APP_LOCK_ENABLED, enabled)
            .apply()
    }

    fun isAppLockEnabled(): Boolean {
        return encryptedSharedPreferences.getBoolean(KEY_APP_LOCK_ENABLED, false)
    }

    fun setBiometricLockEnabled(enabled: Boolean) {
        encryptedSharedPreferences.edit()
            .putBoolean(KEY_BIOMETRIC_LOCK_ENABLED, enabled)
            .apply()
    }

    fun isBiometricLockEnabled(): Boolean {
        return encryptedSharedPreferences.getBoolean(KEY_BIOMETRIC_LOCK_ENABLED, false)
    }

    fun setLockTimeoutMillis(timeoutMillis: Long) {
        encryptedSharedPreferences.edit()
            .putLong(KEY_LOCK_TIMEOUT_MILLIS, timeoutMillis)
            .apply()
    }

    fun getLockTimeoutMillis(): Long {
        return encryptedSharedPreferences.getLong(KEY_LOCK_TIMEOUT_MILLIS, 0L)
    }

    private fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun hexToBytes(hex: String): ByteArray {
        val len = hex.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(hex[i], 16) shl 4) + Character.digit(hex[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    companion object {
        private const val KEY_PREFS_NAME = "smart_note_secret_prefs"
        private const val ANDROID_KEYSTORE_PROVIDER = "AndroidKeyStore"
        private const val KEYSTORE_ALIAS = "SmartNoteAES256KeyAlias"
        private const val KEY_DB_PASSPHRASE = "key_db_passphrase"
        private const val KEY_HASHED_PIN = "key_hashed_pin"
        private const val KEY_APP_LOCK_ENABLED = "key_app_lock_enabled"
        private const val KEY_BIOMETRIC_LOCK_ENABLED = "key_biometric_lock_enabled"
        private const val KEY_LOCK_TIMEOUT_MILLIS = "key_lock_timeout_millis"

        private const val PIN_SALT_PREFIX = "SmartNoteSaltPrefix#"
        private const val PIN_SALT_SUFFIX = "@SmartNoteSaltSuffix"
    }
}
