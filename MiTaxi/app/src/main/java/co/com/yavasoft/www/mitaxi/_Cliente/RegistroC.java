package co.com.yavasoft.www.mitaxi._Cliente;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import co.com.yavasoft.www.mitaxi.R;

public class RegistroC extends AppCompatActivity {

    TextView c_name, c_lastnane, c_cedula, c_email, c_pass;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener autListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_c);
        c_name = (EditText) findViewById(R.id.clien_name);
        c_lastnane = (EditText) findViewById(R.id.client_lastname);
        c_cedula = (EditText) findViewById(R.id.client_cc);
        c_email = (EditText) findViewById(R.id.client_email);
        c_pass = (EditText) findViewById(R.id.client_pass);
    }

    public void registroCliente(View view) {
       auth = FirebaseAuth.getInstance();

        Log.d("valor", c_name.getText().toString() + "sii //// " );

        if (c_name.getText().toString().isEmpty()
                || c_lastnane.getText().toString().isEmpty()
                || c_cedula.getText().toString().isEmpty()
                || c_email.getText().toString().isEmpty()
                || c_pass.getText().toString().length() < 8
                || c_pass.getText().toString().isEmpty() ) {
            if (c_name.getText().toString().isEmpty()) {
                c_name.setError("Falta Nombre");
            }
            if (c_lastnane.getText().toString().isEmpty()) {
                c_lastnane.setError("Falta Apellido");
            }
            if (c_cedula.getText().toString().isEmpty()) {
                c_cedula.setError("Falta la edula");
            }
            if (c_email.getText().toString().isEmpty()) {
                c_email.setError("Falta el correo");
            }
            if (c_pass.getText().toString().isEmpty() || c_pass.getText().toString().length() < 8) {
                c_pass.setError("Falta la contraseña o es muy corta");
            }
            Log.d("asdsa", "Tambien");
        } else {
            String email = c_email.getText().toString().trim();
            String pass = c_pass.getText().toString().trim();
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(RegistroC.this, "Error el crear el registro",
                                        Toast.LENGTH_SHORT).show();
                                Toast.makeText(RegistroC.this, task.hashCode(), Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(RegistroC.this, Main.class));
                            }

                        }
                    });
        }

    }
}
