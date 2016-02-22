package com.build_it_bigger.testing;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.TextView;

import app.sunshine.android.example.com.build_it_biffer.R;
import app.sunshine.android.example.com.build_it_biffer.paid.MainActivity;


public class JokeGenTestPaid extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;
    private Button mButton;
    private TextView mTextView;

    public JokeGenTestPaid() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();

        setActivityInitialTouchMode(true);
        mActivity = getActivity();
        mButton = (Button) mActivity.findViewById(R.id.joke_button);
        mTextView = (TextView) mActivity.findViewById(R.id.test_result_text);
    }

    @MediumTest
    public void testValidReturnString(){
        TouchUtils.clickView(this, mButton);
        assertNotNull(mTextView.getText());
        assertFalse(mTextView.getText().toString().isEmpty());
        assertFalse(mTextView.getText().equals(getActivity().getString(R.string.fetch_error_user)));
    }
}