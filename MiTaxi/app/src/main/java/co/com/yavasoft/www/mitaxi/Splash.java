package co.com.yavasoft.www.mitaxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Splash extends AppCompatActivity {

    Button log_user;
    Button log_cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        log_user = (Button) findViewById(R.id.Log_user);
        log_cliente = (Button) findViewById(R.id.Log_cliente);

        log_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splash.this, LoginCliente.class));
            }
        });

        log_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splash.this, LoginUser.class));
            }
        });
    }
}
