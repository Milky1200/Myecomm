package com.mishraaditya.mycomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mishraaditya.mycomm.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView loginLink;
    EditText email, password, username;
    AppCompatButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        loginLink=findViewById(R.id.loginLink);
        email=findViewById(R.id.edtEmail);
        password=findViewById(R.id.edtPassword);
        username=findViewById(R.id.edtName);
        btnRegister=findViewById(R.id.btnRegister);

        loginLink.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnRegister){
            Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show();
            
        } else if (v.getId()==R.id.loginLink) {
            switchOnLogin();
        }
    }

    private void switchOnLogin() {
        Intent i=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}