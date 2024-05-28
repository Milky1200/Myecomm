package com.mishraaditya.mycomm.NavFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.mishraaditya.mycomm.Activities.HomeActivity;
import com.mishraaditya.mycomm.Activities.LoginActivity;
import com.mishraaditya.mycomm.ModelResponse.LoginResponse;
import com.mishraaditya.mycomm.R;
import com.mishraaditya.mycomm.RetrofitClient;
import com.mishraaditya.mycomm.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener{
    EditText username,email;
    int userId;
    SharedPrefManager sharedPrefManager;
    AppCompatButton btnUpdate;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        email=view.findViewById(R.id.userEmail);
        username=view.findViewById(R.id.userName);
        btnUpdate=view.findViewById(R.id.btnUserUpdate);
        sharedPrefManager=new SharedPrefManager(getActivity());
        userId=sharedPrefManager.getUser().getId();
        btnUpdate.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnUserUpdate){
            doUpdateUser();
        }
    }
    private void doUpdateUser(){
        String userName=username.getText().toString().trim();
        String userEmail=email.getText().toString().trim();

        if(userName.isEmpty()){
            username.requestFocus();
            username.setError("Please Enter Your Username");
            return;
            //If Not Applied return then your validation will not matter and User will be registered.
        }
        if(userEmail.isEmpty()){
            email.requestFocus();
            email.setError("Please Enter Your email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("Please Enter Valid Email");
            return;
        }

        Call<LoginResponse> call= RetrofitClient.getInstance().getApi().updateUserAcc(userId,userName,userEmail);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse updateResponse=response.body();
                if(response.isSuccessful()){
                    if(updateResponse.getError().equals("000")){
                        sharedPrefManager.saveUser(updateResponse.getUser());
                        Toast.makeText(getActivity(),updateResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),updateResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"Failure Occurred",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }


}
