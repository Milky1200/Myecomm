package com.mishraaditya.mycomm.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mishraaditya.mycomm.NavFragment.DashboardFragment;
import com.mishraaditya.mycomm.NavFragment.ProfileFragment;
import com.mishraaditya.mycomm.NavFragment.UsersFragment;
import com.mishraaditya.mycomm.R;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.dashBoard){
            loadFragment(new DashboardFragment());
        }else if(menuItem.getItemId()==R.id.users){
            loadFragment(new UsersFragment());
        }else if(menuItem.getItemId()==R.id.profile){
            loadFragment(new ProfileFragment());
        }else Toast.makeText(HomeActivity.this,"Invalid Fragment ID",Toast.LENGTH_LONG).show();
        return true;
    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFragmentContainer,fragment).commit();
    }
}