package com.mishraaditya.mycomm.NavFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mishraaditya.mycomm.R;
import com.mishraaditya.mycomm.ServerClients.ProductModel;
import com.mishraaditya.mycomm.ServerClients.ProductResponse;
import com.mishraaditya.mycomm.ServerClients.ProductsClient;
import com.mishraaditya.mycomm.ServerClients.RetrofitClient;
import com.mishraaditya.mycomm.ServerClients.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {


    RecyclerView recyclerView;
    ProductResponse productResponse;
    List<ProductModel> productModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = view.findViewById(R.id.rvProducts);
        getProds();
        return view;
    }
    private void getProds() {
        Call<ProductResponse> call = ProductsClient.getInstance().getProductApi().getProds();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                productResponse=response.body();
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),"Got Products",Toast.LENGTH_SHORT).show();
                    productModels=productResponse.getProducts();
                    setMyAdaptor();
                }
                else{
                    Toast.makeText(getActivity(),"Failed In SuccessResponse: "+response.message(),Toast.LENGTH_LONG).show();
                    Log.e("Mishraaditya","OnFailure: "+ response);
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable throwable) {
                Toast.makeText(getActivity(),"Failed in OnFailure: "+throwable.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("Mishraaditya","OnFailure: "+ throwable.getMessage());
            }
        });
    }
    private void setMyAdaptor(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RVProductAdaptor adaptor=new RVProductAdaptor(getActivity(),productModels);
        recyclerView.setAdapter(adaptor);
    }
}