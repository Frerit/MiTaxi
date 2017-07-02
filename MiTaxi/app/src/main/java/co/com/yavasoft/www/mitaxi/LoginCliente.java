package co.com.yavasoft.www.mitaxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import co.com.yavasoft.www.mitaxi._Cliente.Main;

public class LoginCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);
    }

    public void login_cliente(View view) {
       startActivity(new Intent(LoginCliente.this, Main.class));
    }

    public void solicitar_user(View view) {

    }
}
