package com.thlapinha.lojagl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.thlapinha.lojagl.R;
import com.thlapinha.lojagl.config.ConfiguracaoFirebase;
import com.thlapinha.lojagl.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText campoNome, campoEmail, campoSenha;
    private Button buttonEntrar;

    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {

                        usuario = new Usuario();

                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);



                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Preencha o E-mail!",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,
                            "Sucesso ao fazer Login!",
                            Toast.LENGTH_LONG).show();

                }else {
                    String exccao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        exccao = "Usuário não está cadastrado!";
                    }catch (FirebaseAuthInvalidCredentialsException e ){

                        exccao = "E-mail e senha não corresponde ao Usuário cadastrado!";
                    }catch (Exception e){
                        exccao = "Erro ao fazer Login!:" + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this,
                            exccao,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}