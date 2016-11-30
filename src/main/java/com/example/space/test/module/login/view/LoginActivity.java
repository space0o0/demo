package com.example.space.test.module.login.view;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.space.test.R;
import com.example.space.test.module.login.presenter.LoginPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends Activity implements View.OnClickListener ,ILoginView{


    @Bind(R.id.USERNAME)
    EditText ed_username;
    @Bind(R.id.PASSWORD)
    EditText ed_password;
    @Bind(R.id.LOGIN)
    Button bt_login;
    @Bind(R.id.CLEAR)
    Button bt_clear;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private LoginPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //bind view
        ButterKnife.bind(this);

        //set listener
        bt_login.setOnClickListener(this);
        bt_clear.setOnClickListener(this);

        //init
        presenter=new LoginPresenterImpl(this);
        presenter.setProgressBarVisiblity(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.LOGIN:
                presenter.Login(ed_username.getText().toString(),ed_password.getText().toString());
                break;

            case R.id.CLEAR:
                presenter.clear();
                break;
        }
    }

    @Override
    public void onClearText() {
        ed_password.setText("");
        ed_username.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, String code) {
        if (result){
            Toast.makeText(this,code,Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this,code,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
