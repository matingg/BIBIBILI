package com.mashaoting.bibibili.recommend.fragment;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/23.
 * <p>
 * 推荐  之 动态页面
 */

public class DongTaiFragment extends BaseFragment {

    @InjectView(R.id.edittex1t)
    EditText edittex1t;
    @InjectView(R.id.usernameWrapper)
    TextInputLayout usernameWrapper;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.dongtai, null);
        return view;
    }

    @Override
    public void initData() {

    }
//    private void hideKeyboard() {
//        View view = getCurrentFocus();
//        if (view != null) {
//            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
//                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }


    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void onClick(View v) {
//        hideKeyboard();

        String username = usernameWrapper.getEditText().getText().toString();
        if (!validateEmail(username)) {
            usernameWrapper.setError("Not a valid email address!");
        }
        else {
            usernameWrapper.setErrorEnabled(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}





