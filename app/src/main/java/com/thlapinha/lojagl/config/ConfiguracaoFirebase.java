package com.thlapinha.lojagl.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autenticacao;

    //retorno

    public static FirebaseAuth getFirebaseAutenticacao() {
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}
