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
import com.mishraaditya.mycomm.Activities.MainActivity;
import com.mishraaditya.mycomm.ModelResponse.LoginResponse;
import com.mishraaditya.mycomm.ModelResponse.RegisterResponse;
import com.mishraaditya.mycomm.R;
import com.mishraaditya.mycomm.RetrofitClient;
import com.mishraaditya.mycomm.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment implements View.OnClickListener{
    EditText username,email,currPass,newPass;

    int userId;
    String emailPass;
    SharedPrefManager sharedPrefManager;
    AppCompatButton btnUpdate,btnUpdatePass,btnLogout,btnDelete;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        //UpdateAccountInfo
        email=view.findViewById(R.id.userEmail);
        username=view.findViewById(R.id.userName);
        btnUpdate=view.findViewById(R.id.btnUserUpdate);
        //UpdateUserPassword
        btnUpdatePass=view.findViewById(R.id.btnUpdatePassword);
        currPass=view.findViewById(R.id.currentPassword);
        newPass=view.findViewById(R.id.newPassword);
        btnLogout=view.findViewById(R.id.btnLogout);
        btnDelete=view.findViewById(R.id.btnDelete);

        //Logout&DeleteAcc

        //shared Pref
        sharedPrefManager=new SharedPrefManager(getActivity());
        userId=sharedPrefManager.getUser().getId();
        emailPass=sharedPrefManager.getUser().getEmail();

        btnUpdate.setOnClickListener(this);
        btnUpdatePass.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnDelete.setOnClickListener(this);




        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnUserUpdate){
            doUpdateUser();
        }else if(v.getId()==R.id.btnUpdatePassword){
            doUpdatePassword();
        } else if (v.getId()==R.id.btnLogout) {
            userLogout();
        }
    }

    private void userLogout() {
        sharedPrefManager.logout();
        //clear all task and again move to login activity
        Intent iNext=new Intent(getActivity(),LoginActivity.class);
        iNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(iNext);
        Toast.makeText(getActivity(),"You Are Logged Out",Toast.LENGTH_LONG).show();
    }

    private void doUpdatePassword() {
        String currentPass=currPass.getText().toString().trim();
        String newPassword=newPass.getText().toString().trim();
        if(currentPass.isEmpty()){
            currPass.requestFocus();
            currPass.setError("Enter Current Password");
            return;
        }
        if(currentPass.length()<8){
            currPass.requestFocus();
            currPass.setError("Password Should Have 8digits");
            return;
        }
        if(newPassword.isEmpty()){
            newPass.requestFocus();
            newPass.setError("Enter Current Password");
            return;
        }
        if(newPassword.length()<8){
            newPass.requestFocus();
            newPass.setError("Password Should Have 8digits");
            return;
        }

        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi()
                .updateUserPassword(emailPass,currentPass,newPassword);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse passwordResponse=response.body();
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),passwordResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),passwordResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
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
