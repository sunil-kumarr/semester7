package com.example.meenutarun.cse227;
// source: https://developer.android.com/courses/advanced-training/toc#unit_1_expand_the_user_experience
/*we create an app with a customized EditText view that includes a clear (X) button on the right side of the EditText. The user can tap the X to clear the text. Specifically, you will:

Create an app with an EditText view as a placeholder.
Add layout attributes to position the view, and to support right-to-left (RTL) languages for text input.
Extend the EditText class to create a custom view.
Initialize the custom view with a drawable that appears at the end of the EditText.
Use a text listener to show the drawable only when text is entered into the EditText.
Use a touch listener to clear the text if the user taps the drawable.
Replace the placeholder EditText with the custom view in the layout.
 */
/*
Right-click the drawable/ folder and choose New > Vector Asset. Click the Android icon and choose the clear (X) icon. Its name changes to ic_clear_black_24dp. Click Next and Finish.
Repeat step 3, choosing the clear (X) icon again, but this time drag the Opacity slider to 50% as shown below. Change the icon's name to ic_clear_opaque_24dp.
*/

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by MeenuTarun on 8/28/2019.
 */


// AppCompatEditText is an EditText subclass that
// supports compatible features on older version of the Android platform.

public class P10MyEditText extends AppCompatEditText
{
    //Define a member variable for the drawable (the X button image).
    Drawable darkButton, lightButton;

    public P10MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init()
    {
//that initializes the member variable to the drawable resource ic_clear_opaque_24dp
        lightButton = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_close_black_24dp,null);
        darkButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_22dp,null);


//To show or hide the button, use the TextWatcher interface,
// whose methods are called if the text changes.

        // After entering  addText, choose the suggestion that appears for
        // addTextChangedListener(TextWatcher watcher).

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//If the text changes, show  Clear(X) button.
                showButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


//Other behaviors of the P10MyEditText custom view are to:
// Clear the text from the field if the user taps the clear (X) button.
// Render the clear (X) button as opaque before the user taps it,
// and black while the user is tapping it.

  //      To detect the tap and clear the text, use the View.OnTouchListener interface.
        // The interface's onTouch() method is called when a touch event occurs with the
        // button.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


         /*       boolean isClicked =false;

                float clearButtonStart = getWidth()-getPaddingEnd()-lightButton.getIntrinsicWidth();

                if(motionEvent.getX() > clearButtonStart)
                {
                    isClicked = true;
                }
                if(isClicked) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            getText().clear();
                            break;
                        case MotionEvent.ACTION_UP:
                            hideButton();
                            break;
                    }
                }
                return true;
            */
                getText().clear();
                hideButton();
                return true;

            }

        });
    }


    public void showButton()
    {
        //Parameters:  SetCompoundDrawablesRelativeWithIntrinsicBounds
        //(null,                      // Start of text.
          //      null,               // Above text.
            //    mClearButtonImage,  // End of text.
              //  null);              // Below text.

// setCompoundDrawablesRelativeWithIntrinsicBounds() method sets the drawable lightButton Image to the end of the text.
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,lightButton,null);
    }

    public void hideButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,darkButton,null);
    }


}
