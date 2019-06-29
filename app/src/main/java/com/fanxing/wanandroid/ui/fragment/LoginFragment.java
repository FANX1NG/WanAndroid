package com.fanxing.wanandroid.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseFragment;
import com.fanxing.wanandroid.model.bean.LoginBean;
import com.fanxing.wanandroid.presenter.LoginFragmentPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


/**
 * 登录页
 * @author 繁星
 */
public class LoginFragment extends BaseFragment {
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_visible)
    Button btnVisible;
    private LoginFragmentPresenter presenter;


    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        presenter = new LoginFragmentPresenter(this);
        presenter.onCreate();
    }

    @Override
    public void initListener() {
        btnVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        etUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")) {
                    btnVisible.setVisibility(View.VISIBLE);
                } else {
                    btnVisible.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v, int id) {
        switch (id) {
            //密码是否显示按钮
            case R.id.btn_visible:
                if (etUserPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    btnVisible.setBackgroundResource(R.mipmap.visibility_off);
                    etUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    btnVisible.setBackgroundResource(R.mipmap.visibility);
                    etUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                etUserPassword.setSelection(etUserPassword.getText().length());
                break;
            //登录按钮
            case R.id.btn_login:
                String userName = etUserName.getText().toString();
                String usetPassword = etUserPassword.getText().toString();
                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(usetPassword)) {
                    showToast("账号密码为空");
                    break;
                }
                if (TextUtils.isEmpty(usetPassword)) {
                    showToast("密码为空");
                    break;
                }
                if (TextUtils.isEmpty(userName)) {
                    showToast("账号为空");
                    break;
                }
                Map<String, String> namePassword = new HashMap<>();
                namePassword.put("username", userName);
                namePassword.put("password", usetPassword);
                presenter.getLoginData(namePassword);
                break;

            default:
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        super.onHttpSuccess(reqType, msg);
        LoginBean loginBean = (LoginBean) msg.obj;
        showToast(loginBean.getData().getId() + "");
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onStop();
    }
}
