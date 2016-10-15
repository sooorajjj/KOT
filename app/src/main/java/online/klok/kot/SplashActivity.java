package online.klok.kot;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import online.klok.kot.floors_tables.TablesActivity;

public class SplashActivity extends Activity {

    ImageView imageView;
    TextView tvRestName, tvAddress, tvPhone;
    SharedPreferences loginCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        imageView = (ImageView) findViewById(R.id.splash);
        tvRestName = (TextView) findViewById(R.id.tv_rest_name);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvPhone = (TextView) findViewById(R.id.tv_phone);

        tvRestName.setText("KLOK INNOVATIONS");
        tvAddress.setText("Door No. M7, 7th Floor Heavenly Plaza, Vazhakkala, Kakkand");
        tvPhone.setText("Off: +91 7559911555, +91 7559911555");

        loginCheck = PreferenceManager.getDefaultSharedPreferences(this);
        final String value = loginCheck.getString("login_mode", "1");

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    if (value.equals("1"))
                    {
                        Intent intent = new Intent(SplashActivity.this, TablesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        timerThread.start();

    }

}
