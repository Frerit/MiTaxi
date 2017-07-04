package co.com.yavasoft.www.mitaxi._Cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import co.com.yavasoft.www.mitaxi.R;

public class RegistroC extends AppCompatActivity {

    TextView c_name, c_lastnane, c_cedula, c_email, c_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_c);
        c_name = (TextView) findViewById(R.id.clien_name);
        c_lastnane = (TextView) findViewById(R.id.client_lastname);
        c_cedula = (TextView) findViewById(R.id.client_cc);
        c_email = (TextView) findViewById(R.id.client_email);
        c_pass = (TextView) findViewById(R.id.client_pass);
    }

    public void registroCliente(View view) {

    }
}
