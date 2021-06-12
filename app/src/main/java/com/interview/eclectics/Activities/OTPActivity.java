package com.interview.eclectics.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kevalpatel.passcodeview.PinView;
import com.kevalpatel.passcodeview.authenticator.PasscodeViewPinAuthenticator;
import com.kevalpatel.passcodeview.indicators.CircleIndicator;
import com.kevalpatel.passcodeview.interfaces.AuthenticationListener;
import com.kevalpatel.passcodeview.keys.KeyNamesBuilder;
import com.kevalpatel.passcodeview.keys.RoundKey;


import androidx.appcompat.app.AppCompatActivity;


import com.interview.eclectics.R;
import com.kevalpatel.passcodeview.PinView;
import com.kevalpatel.passcodeview.keys.RoundKey;


public class OTPActivity extends AppCompatActivity {

    PinView mPinView ;
    private static final String ARG_CURRENT_PIN = "current_pin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        mPinView = (PinView) findViewById(R.id.pin_view);


        //REQUIRED
        final int[] correctPattern = new int[]{1, 2, 3, 4};
        mPinView.setPinAuthenticator(new PasscodeViewPinAuthenticator(correctPattern));

        //Build the desired key shape and pass the theme parameters.
        //REQUIRED
        mPinView.setKey(new RoundKey.Builder(mPinView)
                .setKeyPadding(R.dimen.key_padding)
                .setKeyStrokeColorResource(R.color.button)
                .setKeyStrokeWidth(R.dimen.key_stroke_width)
                .setKeyTextColorResource(R.color.button)
                .setKeyTextSize(R.dimen.key_text_size));

        //Build the desired indicator shape and pass the theme attributes.
        //REQUIRED
        mPinView.setIndicator(new CircleIndicator.Builder(mPinView)
                .setIndicatorRadius(R.dimen.indicator_radius)
                .setIndicatorFilledColorResource(R.color.button)
                .setIndicatorStrokeColorResource(R.color.button)
                .setIndicatorStrokeWidth(R.dimen.indicator_stroke_width));

        mPinView.setPinLength(PinView.DYNAMIC_PIN_LENGTH);

        //Set the name of the keys based on your locale.
        //OPTIONAL. If not passed key names will be displayed based on english locale.
        mPinView.setKeyNames(new KeyNamesBuilder()
                .setKeyOne(this, R.string.key_1)
                .setKeyTwo(this, R.string.key_2)
                .setKeyThree(this, R.string.key_3)
                .setKeyFour(this, R.string.key_4)
                .setKeyFive(this, R.string.key_5)
                .setKeySix(this, R.string.key_6)
                .setKeySeven(this, R.string.key_7)
                .setKeyEight(this, R.string.key_8)
                .setKeyNine(this, R.string.key_9)
                .setKeyZero(this, R.string.key_0));

        mPinView.setTitle("Enter the PIN");

        mPinView.setAuthenticationListener(new AuthenticationListener() {
            @Override
            public void onAuthenticationSuccessful() {
                //User authenticated successfully.
                //Navigate to secure screens.
                startActivity(new Intent(OTPActivity.this, MainActivity.class));
                finish();

                //Next activity
            }

            @Override
            public void onAuthenticationFailed() {
                //Calls whenever authentication is failed or user is unauthorized.
                //Do something

                startActivity(new Intent(OTPActivity.this, MainActivity.class));
                finish();
            }
        });







    }

}