package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.e_learning.adminFragments.QuestionsFragment;
import com.example.e_learning.adminFragments.UsersFragment;
import com.example.e_learning.adminFragments.estimatesFragment;
import com.example.e_learning.adminFragments.examFragment;
import com.example.e_learning.studentFragments.MyProfileFragment;
import com.example.e_learning.studentFragments.archivedExams;
import com.example.e_learning.studentFragments.examProcessFragment;
import com.example.e_learning.studentFragments.examsFragment;
import com.example.e_learning.studentFragments.myEstimates;

public class StudentActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private String[] mDrawerItems = {"Мій профіль", "Мої оцінки", "Іспити", "Архів іспитів","Вихід"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mDrawerLayout = findViewById(R.id.drawer_layoutS);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        );

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList = findViewById(R.id.left_drawerS);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                R.id.taskquest,
                mDrawerItems
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ImageView icon = view.findViewById(R.id.icon);
                switch (position) {
                    case 0:
                        icon.setImageResource(R.drawable.myprofile);
                        break;
                    case 1:
                        icon.setImageResource(R.drawable.rating);
                        break;
                    case 2:
                        icon.setImageResource(R.drawable.exams);
                        break;
                    case 3:
                        icon.setImageResource(R.drawable.archive);
                        break;
                    case 4:
                        icon.setImageResource(R.drawable.exit_svgrepo_com);
                        break;
                }
                return view;
            }
        };

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0: // Item 1
                    getSupportActionBar().setTitle("Мій профіль");
                    Fragment newFragment = new MyProfileFragment();
                    FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                    transaction3.replace(R.id.fragment_contentS, newFragment);
                    transaction3.addToBackStack(null);
                    transaction3.commit();
                    break;
                case 1: // Item 2
                    getSupportActionBar().setTitle("Мої оцінки");
                    Fragment newFragment4 = new myEstimates();
                    FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                    transaction4.replace(R.id.fragment_contentS, newFragment4);
                    transaction4.addToBackStack(null);
                    transaction4.commit();

                    break;
                case 2: // Item 3
                    getSupportActionBar().setTitle("Іспити");
                    Fragment newFragment1 = new examsFragment();
                    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                    transaction1.replace(R.id.fragment_contentS, newFragment1);
                    transaction1.addToBackStack(null);
                    transaction1.commit();
                    break;
                case 3:
                    getSupportActionBar().setTitle("Архів іспитів");
                    Fragment newFragment2 = new archivedExams();
                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    transaction2.replace(R.id.fragment_contentS, newFragment2);
                    transaction2.addToBackStack(null);
                    transaction2.commit();
                    break;
                case 4:
                    SharedPreferences settings = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    settings.edit().clear().commit();
                    Intent i = new Intent(StudentActivity.this,LoginActivity.class);
                    startActivity(i);
                    break;
            }
            mDrawerLayout.closeDrawer(mDrawerList);
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_contentS);

        if (currentFragment instanceof examProcessFragment) {
            Toast.makeText(this, "Здайте іспит, щоб повернутися", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}