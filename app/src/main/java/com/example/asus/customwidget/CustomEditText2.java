package com.example.asus.customwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Asus on 12/1/2017.
 */

public class CustomEditText2 extends EditText {

    private Drawable unhidebutton = getResources().getDrawable(R.drawable.unhide);
    public CustomEditText2(Context context) {
        super(context);
        init();
    }

    public CustomEditText2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    void init(){
        unhidebutton.setBounds(0, 0, 25, 25);
        handleUnhideButton();

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CustomEditText2 ct = CustomEditText2.this;
                if(motionEvent.getX() > ct.getWidth() - ct.getPaddingRight() - 25){
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // The user just touched the screen
                            ct.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            break;
                        case MotionEvent.ACTION_UP:
                            ct.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            CustomEditText2.this.handleUnhideButton();
                            break;
                    }
                }
                return false;
            }
        });
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CustomEditText2.this.handleUnhideButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    void handleUnhideButton(){
        if(this.getText().toString().equals("")){
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
        }
        else{
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], unhidebutton,this.getCompoundDrawables()[3]);
        }
    }
}
