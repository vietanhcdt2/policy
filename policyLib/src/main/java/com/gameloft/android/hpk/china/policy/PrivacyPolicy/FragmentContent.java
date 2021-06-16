package com.gameloft.android.hpk.china.policy.PrivacyPolicy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.gameloft.android.hpk.china.policy.R;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class FragmentContent extends Fragment {
    private static FragmentContent instance;
    LinearLayout linearLayout;
    TableLayout tableLayout;
    private TextView tvPrivacyTitle;
    private TextView tvPrivacyContent;

    private ScrollView scrollView;

    private TextView textView_01;

    public static FragmentContent newInstance(int pos) {
        Log.e("AddPrivacy", "FragmentContent newInstance");
        Bundle args = new Bundle();
        args.putInt("pos", pos);
        FragmentContent f = new FragmentContent();
        f.setArguments(args);
        instance = f;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("AddPrivacy", "FragmentContent onCreateView");
        View view = inflater.inflate(R.layout.hpk_privacy_content_layout, container);
        //tvPrivacyContent = view.findViewById(R.id.textView4);
        scrollView = view.findViewById(R.id.hpk_policy_scroll_view);

        linearLayout = view.findViewById(R.id.linearLayout);
        int pos = getArguments().getInt("pos", 0);

        switch (pos) {
            case 0:
                createText(R.string.condition_of_use_text);
                break;
            case 1:
                createText(R.string.privacy_note_text_01);
//                createTableView(4,2, "hpk_privacy_notice_table");
//                createText(R.string.hpk_privacy_notice_text_02);
                break;
            case 2:
                createText(R.string.showcase_cookie_policy_text_01);
                createTableView(14,3, "showcase_cookie_policy");
                createText(R.string.showcase_cookie_policy_text_02);
                break;
            case 3:
                createText(R.string.eula_policy);
                break;
        }

        return view;
    }
//    // Touch listener for the scroll view
//    float mPreviousY;
//    private View.OnTouchListener scrollViewTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            float y = event.getY();
//
//            if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                float dy = y - mPreviousY;
//
//                // if user scrolls up and people image hasn't slided left,
//                if (dy < -1 && mPeopleSlidedLeft == false) {
//                    DisplayMetrics dm = new DisplayMetrics();
//                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//                    int xDest = dm.widthPixels / 2;
//                    xDest += mPeopleImage.getMeasuredWidth() / 2;
//                    mPeopleImage.animate().setDuration(animationTime)
//                            .translationX(originalPeoplePosition[0] - xDest);
//                }
//            }
//
//            mPeopleSlidedLeft = true;
//            mPreviousY = y;
//            return false;
//        }
//    };
//
    private void createText(int resId) {
        textView_01 = new TextView(this.getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(50, 0, 50, 0);
        textView_01.setLayoutParams(lp);
        textView_01.setText(resId);
        textView_01.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        textView_01.setTextColor(-11776948);
        linearLayout.addView(textView_01);
    }

    private void createTableView(int rows, int cols, String info) {
        tableLayout = new TableLayout(this.getContext());
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(50, 0, 50, 0);
        tableLayout.setLayoutParams(lp);
        tableLayout.setShrinkAllColumns(true);
        createTable(rows, cols, info);
        linearLayout.addView(tableLayout);
    }

    private void createTable(int rows, int cols, String info) {

        for (int i = 0; i < rows; i++) {

            TableRow row = new TableRow(this.getContext());
            row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //row.setBackgroundResource(R.drawable.hpk_privacy_content_layout);

            for (int j = 0; j < cols; j++) {

                TextView textView = new TextView(this.getContext());
                textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                textView.setBackgroundResource(R.drawable.hpk_policy_table_cell_shape);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                textView.setTextColor(-11776948);
                if(i == 0)
                    textView.setTypeface(null, Typeface.BOLD);
                if(j == 0)
                    textView.setWidth((int)PrivacyDialog.dialogWidth/6);
                else if(j == 1)
                    textView.setWidth((int)PrivacyDialog.dialogWidth/2);
                else if(j == 2)
                    textView.setWidth((int)PrivacyDialog.dialogWidth/9);


                fillElement(textView, i, j, info);

                row.addView(textView);
            }
            tableLayout.addView(row);
        }
    }

    private void fillElement(TextView textView, int row, int col, String info) {

        Field[] fields = R.string.class.getDeclaredFields();
        String rowCol = (row + 1) + "" + (col + 1);
        for (int i = 0, max = fields.length; i < max; i++) {
            final int resourceId;
            try {
                String name = fields[i].getName();
                if (name.contains(rowCol) && name.contains(info)) {
                    String text = getResources().getString(getResources().getIdentifier(name, "string", this.getContext().getPackageName()));
                    textView.setText(text);
                }
            } catch (Exception e) {
                Log.e("va-test", "e = " + e.toString());
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("AddPrivacy", "FragmentContent onViewCreated");

        int pos = getArguments().getInt("pos", 0);
        switch (pos) {
            case 0:
                //tvPrivacyContent.setText(R.string.condition_of_use_text);
                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

    }


}
