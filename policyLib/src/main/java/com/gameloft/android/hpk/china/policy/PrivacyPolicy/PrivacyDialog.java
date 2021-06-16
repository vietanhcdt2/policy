package com.gameloft.android.hpk.china.policy.PrivacyPolicy;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gameloft.android.hpk.china.policy.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager2.widget.ViewPager2;

public class PrivacyDialog extends DialogFragment {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 250;

    private Button btnNext;
    private Button btnDecline;
    private TextView tvDesc;

    private Button btnTerm;
    private Button btnPrivacy;
    private Button btnCookies;
    private Button btnEula;

    private ViewPager2 viewPager;
    private ConstraintLayout dialogLayout;
    private RelativeLayout contentLayout;
    private LinearLayout btnBoxLayout;
    private LinearLayout descLayout;
    private TabLayout tabLayout;

    public static int dialogWidth;
    public static int dialogHeight;

    private ArgeementListener argeementListener;

    public void setArgeementListener(ArgeementListener argeementListener) {
        this.argeementListener = argeementListener;
    }

    public static PrivacyDialog newInstance(String data) {
        Log.d("va-test", "newInstance");
        PrivacyDialog dialog = new PrivacyDialog();
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("va-test", "onCreate");
        setStyle(DialogFragment.STYLE_NORMAL, R.style.mydialog);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("va-test", "onCreateView");
        View view = inflater.inflate(R.layout.hpk_privacy_dialog_layout, container);
        dialogLayout = view.findViewById(R.id.hpk_privacy_dialog_layout);
        contentLayout = view.findViewById(R.id.hpk_privacy_content);
        descLayout = view.findViewById(R.id.hpk_policy_desc_layout);
        btnBoxLayout = view.findViewById(R.id.hpk_layout_button_box);
        tvDesc = view.findViewById(R.id.hpk_policy_desc);
        tvDesc.setTextColor(-11776948);
        viewPager = view.findViewById(R.id.vp_hpk_dialog_content);
//        tabLayout = view.findViewById(R.id.tl_hpk_privacy);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        Log.e("va-test", "den=" + getActivity().getResources().getDisplayMetrics().density);


        Log.d("va-test", Color.rgb(56,191,246) + "");
        btnTerm = view.findViewById(R.id.btn_hpk_policy_term);
        btnTerm.setTextColor(-16744320);

        btnTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBars(0);
            }
        });

        btnPrivacy = view.findViewById(R.id.btn_hpk_policy_privacy);
        btnPrivacy.setTextColor(Color.BLACK);

        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBars(1);
            }
        });

        btnCookies = view.findViewById(R.id.btn_hpk_policy_cookies);
        btnCookies.setTextColor(Color.BLACK);

        btnCookies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBars(2);
            }
        });

        btnEula = view.findViewById(R.id.btn_hpk_policy_eula);
        btnEula.setTextColor(Color.BLACK);

        btnEula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBars(3);
            }
        });

        viewPager = view.findViewById(R.id.vp_hpk_dialog_content);
        viewPager.setAdapter(new PagerAdapter(this));
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            public void onPageScrollStateChanged(int state) {

            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            public void onPageSelected(int position) {
                Log.d("va-test", " onPageSelected pos = " + position);
//                viewPager.setCurrentItem(position);
                updateBars(position);
            }
        });

        btnNext = view.findViewById(R.id.btn_hpk_privacy_next);
        btnNext.setText(R.string.hpk_privacy_button_agree);
        btnNext.setTextColor(-16744320);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("va-test", "dismisss dialog by agree 1");
                dismiss();
                argeementListener.onAgreementResult(true,"Agree");
                Log.d("va-test", "dismisss dialog by agree 2");
            }
        });

        btnNext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btnNext.setBackgroundResource(R.drawable.hpk_privacy_layout_btn_pressed);
                    btnNext.setTextColor(-1);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btnNext.setBackgroundResource(R.drawable.hpk_privacy_layout_btn);
                    btnNext.setTextColor(-16744320);
                }
                return false;
            }
        });

        btnDecline = view.findViewById(R.id.btn_hpk_privacy_decline);
        btnDecline.setText(R.string.hpk_privacy_button_decline);
        btnDecline.setTextColor(-16744320);
        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                argeementListener.onAgreementResult(false,"Decline");
            }
        });



        btnDecline.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btnDecline.setBackgroundResource(R.drawable.hpk_privacy_layout_btn_pressed);
                    btnDecline.setTextColor(-1);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btnDecline.setBackgroundResource(R.drawable.hpk_privacy_layout_btn);
                    btnDecline.setTextColor(-16744320);
                }
                return false;
            }
        });

        dialogWidth = (int)(width * 0.70 ); // table policy
        dialogHeight = (int)(height * 0.47 ); // table policy

        // resize dialog
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(dialogLayout);

//        constraintSet.constrainHeight(contentLayout.getId(), dialogHeight);
        constraintSet.constrainWidth(contentLayout.getId(), dialogWidth);

        constraintSet.constrainWidth(descLayout.getId(), dialogWidth);
        constraintSet.constrainHeight(descLayout.getId(), dialogHeight/3);

        constraintSet.constrainHeight(btnBoxLayout.getId(), dialogHeight/5);
//        constraintSet.constrainWidth(btnBoxLayout.getId(), dialogWidth/5);


        // apply update to layout
        constraintSet.applyTo(dialogLayout);

        updateButtonLayout(btnDecline);
        updateButtonLayout(btnNext);
        updatePageButtonLayout(btnTerm);
        updatePageButtonLayout(btnPrivacy);
        updatePageButtonLayout(btnCookies);
        updatePageButtonLayout(btnEula);


        return view;
    }

    private void updateButtonLayout(Button btn) {
        ViewGroup.LayoutParams params = btn.getLayoutParams();
        params.width = (int) (dialogWidth / 7);
        params.height = (int) (dialogHeight / 5);
        btn.requestLayout();
    }

    private void updatePageButtonLayout(Button btn) {
        ViewGroup.LayoutParams params = btn.getLayoutParams();
        params.width = (int) (dialogWidth * 0.22);
        params.height = (int) (dialogHeight * 0.23);
        btn.requestLayout();
    }

    private void updateBars(int pos) {
        Button[] btns = {btnTerm, btnPrivacy, btnCookies, btnEula};

        int index = 0;
        for(Button btn : btns) {
            if(pos == index) {
                viewPager.setCurrentItem(pos);
                btn.setTypeface(Typeface.DEFAULT_BOLD);
                btn.setTextColor(-16744320);
                btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            } else {
                btn.setTypeface(Typeface.DEFAULT);
                btn.setTextColor(Color.BLACK);
                btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            }
            index++;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("va-test", "onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("va-test", "onResume");
        long heightDesc = tvDesc.getHeight();
        Log.e("va-test", "1 heightDesc=" + heightDesc);
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("va-test", "onPause");
        long heightDesc = tvDesc.getHeight();
        Log.e("va-test", "2 heightDesc=" + heightDesc);

        long heightDlg = dialogLayout.getHeight();
        Log.e("va-test", "2 heightDlg=" + heightDlg);
    }

}