package com.smartnote.app.security

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecurityManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cryptoManager: CryptoManager
) {
    private val _isUnlocked = MutableStateFlow(
        !cryptoManager.isAppLockEnabled() || cryptoManager.getHashedPin() == null
    )
    val isUnlocked: StateFlow<Boolean> = _isUnlocked.asStateFlow()

    private var backgroundTimestamp: Long = 0L

    fun unlockWithPin(pin: String): Boolean {
        val isValid = cryptoManager.verifyPin(pin)
        if (isValid) {
            _isUnlocked.value = true
        }
        return isValid
    }

    fun unlockWithBiometrics() {
        _isUnlocked.value = true
    }

    fun lockApp() {
        if (cryptoManager.isAppLockEnabled()) {
            _isUnlocked.value = false
        }
    }

    fun onAppBackgrounded() {
        backgroundTimestamp = System.currentTimeMillis()
    }

    fun onAppForegrounded() {
        if (!cryptoManager.isAppLockEnabled()) {
            _isUnlocked.value = true
            return
        }

        val timeoutMillis = cryptoManager.getLockTimeoutMillis()
        val elapsed = System.currentTimeMillis() - backgroundTimestamp

        // If timeout is immediate (0) or elapsed time exceeds timeout threshold
        if (backgroundTimestamp > 0 && (timeoutMillis <= 0L || elapsed >= timeoutMillis)) {
            _isUnlocked.value = false
        }
    }

    fun setupPin(pin: String) {
        val hashed = cryptoManager.hashPin(pin)
        cryptoManager.saveHashedPin(hashed)
        cryptoManager.setAppLockEnabled(true)
        _isUnlocked.value = true
    }

    fun disableAppLock() {
        cryptoManager.clearPin()
        cryptoManager.setAppLockEnabled(false)
        cryptoManager.setBiometricLockEnabled(false)
        _isUnlocked.value = true
    }
}
