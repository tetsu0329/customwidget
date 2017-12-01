package com.example.asus.customwidget;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Asus on 12/1/2017.
 */

public class CustomEditText extends EditText {
    private Drawable imageCloseButton = getResources().getDrawable(R.drawable.close2);

    public CustomEditText(Context context) {
        super(context);
        init();
    }
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init (){
        //Set bounds of the Clear button
        imageCloseButton.setBounds(0, 0, 25, 25);
        handleClearButton();

        //there may be initial text in the field, so we may need to display the button

        //if the close image is displayed and the user remove his finger from the button clear it, otherwise do nothingt
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CustomEditText et = CustomEditText.this;
                if(et.getCompoundDrawables()[2] == null){
                    return false;
                }
                if(motionEvent.getAction() != motionEvent.ACTION_UP){
                    return false;
                }
                if(motionEvent.getX() > et.getWidth() - et.getPaddingRight() - 25){
                    et.setText("");
                    CustomEditText.this.handleClearButton();
                }
                return false;

            }
        });
        //if text changes, take care of the button
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CustomEditText.this.handleClearButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void handleClearButton(){
        if(this.getText().toString().equals("")){
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
        }
        else{
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imageCloseButton, this.getCompoundDrawables()[3]);
        }
    }
}
