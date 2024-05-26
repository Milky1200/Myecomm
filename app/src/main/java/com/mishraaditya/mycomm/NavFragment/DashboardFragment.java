package com.mishraaditya.mycomm.NavFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mishraaditya.mycomm.R;
import com.mishraaditya.mycomm.SharedPrefManager;


public class DashboardFragment extends Fragment {

    TextView txtName,txtEmail;
    SharedPrefManager sharedPrefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        txtName=view.findViewById(R.id.txtName);
        txtEmail=view.findViewById(R.id.txtEmail);
        sharedPrefManager=new SharedPrefManager(getActivity());

        String userName="The "+ sharedPrefManager.getUser().getUsername();
        txtName.setText(userName);
        txtEmail.setText(sharedPrefManager.getUser().getEmail());
        return view;
    }
}