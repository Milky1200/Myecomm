package com.mishraaditya.mycomm.NavFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mishraaditya.mycomm.ModelResponse.FetchUserResponse;
import com.mishraaditya.mycomm.R;
import com.mishraaditya.mycomm.RetrofitClient;
import com.mishraaditya.mycomm.UserAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         recyclerView=view.findViewById(R.id.recyclerView);
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<FetchUserResponse> call= RetrofitClient.getInstance().getApi().fetchUsers();
        call.enqueue(new Callback<FetchUserResponse>() {
            @Override
            public void onResponse(Call<FetchUserResponse> call, Response<FetchUserResponse> response) {
                FetchUserResponse fetchUserResponse=response.body();
                if (response.isSuccessful()){
                    UserAdapter userAdapter=new UserAdapter(getActivity(),fetchUserResponse.getUserList());
                    recyclerView.setAdapter(userAdapter);
                    Toast.makeText(getActivity(),fetchUserResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"Failed to Fetch",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchUserResponse> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}