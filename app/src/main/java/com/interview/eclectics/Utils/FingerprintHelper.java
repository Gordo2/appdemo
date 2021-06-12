package com.interview.eclectics.Utils;

import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.P)
public class FingerprintHelper extends BiometricPrompt.AuthenticationCallback {

    private FingerprintHelperListener listener;

    public FingerprintHelper(FingerprintHelperListener listener) {
        this.listener = listener;
    }

    //interface for the listner
    public interface FingerprintHelperListener {
        void authenticationFailed(String error);
        void authenticationSuccess(FingerprintManager.AuthenticationResult result);
    }

}