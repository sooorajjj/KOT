package online.klok.kot;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import online.klok.kot.customAdapters.Floor;
import online.klok.kot.customAdapters.Order;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    boolean isEmptyEmail;
    boolean isEmptyPassword;
    private LinearLayout mRoot;
    private TextInputLayout mInputLayout, mPasswordLayout;
    private EditText mInputText, mPasswordText;
    private Button login;
//    private Button registerLink;
    private View.OnClickListener mSnackBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        Initialize();
        listener();
    }

    private void Initialize() {
//        registerLink = (Button) findViewById(R.id.tvRegister);
        mRoot = (LinearLayout) findViewById(R.id.root_activity_second);
        mInputLayout = (TextInputLayout) findViewById(R.id.input_layout);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.password_layout);
        mInputText = (EditText) findViewById(R.id.edtInput);
        mPasswordText = (EditText) findViewById(R.id.edtPassword);
        login = (Button) findViewById(R.id.btnLogin);
    }

    private boolean isEmptyEmail() {
        return mInputText.getText() == null
                || mInputText.getText().toString() == null
                || mInputText.getText().toString().isEmpty();
    }

    private boolean isEmptyPassword() {
        return mPasswordText.getText() == null
                || mPasswordText.getText().toString() == null
                || mPasswordText.getText().toString().isEmpty();
    }

    private void listener() {
//        registerLink.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLogin){

            isEmptyEmail = isEmptyEmail();
            isEmptyPassword = isEmptyPassword();

            if (isEmptyEmail && isEmptyPassword) {
                Snackbar.make(mRoot, "one or more Field are blank", Snackbar.LENGTH_SHORT).setAction(getString(R.string.txt_dismiss), mSnackBarClickListener).show();
            } else if (isEmptyEmail && !isEmptyPassword) {
                mInputLayout.setError("Email cannot be empty");
                mPasswordLayout.setError(null);
            } else if (!isEmptyEmail && isEmptyPassword) {
                mInputLayout.setError(null);
                mPasswordLayout.setError("Password cannot be empty");
            } else if (!isEmptyEmail && !isEmptyPassword) {
                mInputLayout.setError(null);
                mPasswordLayout.setError(null);
            }

            if (mInputText.getText().toString().equals("chef") && mPasswordText.getText().toString().equals("chef")) {
                Intent loginIntent = new Intent(this, Floor.class);
                startActivity(loginIntent);
                finish();

            } else if(mInputText.getText().toString().equals("waiter") && mPasswordText.getText().toString().equals("waiter")) {
                Intent loginIntent = new Intent(this, Floor.class);
                startActivity(loginIntent);
                finish();

//            }else if(mInputText.getText().toString().equals("billing") && mPasswordText.getText().toString().equals("billing")) {
//                Intent loginIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
//                startActivity(loginIntent);
//                finish();

            }else {
                Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
            }
        }

//        if(v.getId() == R.id.tvRegister){
//            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
//            startActivity(registerIntent);
//
//        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.testmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_about:
                aboutMenuItem();
                break;
            }
        return true;
    }

    private void aboutMenuItem(){
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("Klok Innovations"+" Copyright 2015 ©. All rights reserved")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }*/
}

