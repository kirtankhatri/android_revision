package com.example.revision;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class Animation extends AppCompatActivity {

    Button blinkBtn,fadeBtn,rotateBtn,moveBtn,slideBtn,zoomBtn;
    View targetElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        targetElement = findViewById(R.id.targetElement);
        blinkBtn = findViewById(R.id.blinkBtn);
        fadeBtn = findViewById(R.id.fadeBtn);
        rotateBtn = findViewById(R.id.rotateBtn);
        moveBtn = findViewById(R.id.moveBtn);
        slideBtn = findViewById(R.id.slideBtn);
        zoomBtn = findViewById(R.id.zoomBtn);

        SpringAnimation springAnimationY = new SpringAnimation(targetElement, DynamicAnimation.TRANSLATION_Y);
        springAnimationY.setSpring(new SpringForce(SpringForce.STIFFNESS_VERY_LOW));
        springAnimationY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnimationY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);

        FlingAnimation flingAnimation = new FlingAnimation(targetElement,DynamicAnimation.TRANSLATION_X);
        flingAnimation.setStartVelocity(1000f);
        flingAnimation.setFriction(0.5f);
        flingAnimation.setMinValue(0f);
        flingAnimation.setMaxValue(1000f);

        DynamicAnimation.OnAnimationEndListener endListener = new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                springAnimationY.animateToFinalPosition(0f);
            }
        };

        springAnimationY.addEndListener(endListener);

        targetElement.setOnClickListener((e)->{
//            springAnimationY.start();
            flingAnimation.start();
        });

        blinkBtn.setOnClickListener((e)->{
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink_animation);
            targetElement.startAnimation(animation);
        });

        fadeBtn.setOnClickListener((e)->{
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_animation);
            targetElement.startAnimation(animation);
        });

        moveBtn.setOnClickListener((e)->{
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_animation);
            targetElement.startAnimation(animation);
        });

        rotateBtn.setOnClickListener((e)->{
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_animation);
            targetElement.startAnimation(animation);
        });

        zoomBtn.setOnClickListener((e)->{
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_animation);
            targetElement.startAnimation(animation);
        });
    }
}