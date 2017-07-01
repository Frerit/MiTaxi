package co.com.yavasoft.www.mitaxi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class LoginUser extends AppCompatActivity {

    TextView log_v_user;
    TextView log_v_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        log_v_user = (TextView) findViewById(R.id.log_user);
        log_v_pass = (TextView) findViewById(R.id.log_pass);


    }
}
