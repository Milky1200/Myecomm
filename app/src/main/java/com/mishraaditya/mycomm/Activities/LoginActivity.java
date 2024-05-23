package com.mishraaditya.mycomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mishraaditya.mycomm.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton btnLogin;
    TextView registerLink;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        email=findViewById(R.id.edtEmail);
        password=findViewById(R.id.edtPassword);
        registerLink=findViewById(R.id.registerLink);
        btnLogin=findViewById(R.id.btnLogin);

        registerLink.setOnClickListener(this);
        btnLogin.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnLogin){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        } else if (v.getId()==R.id.registerLink) {
            switchOnRegister();
        }

    }

    private void switchOnRegister() {
        Intent i=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}