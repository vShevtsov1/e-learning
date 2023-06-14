package com.example.e_learning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_learning.adminFragments.QuestionsFragment;
import com.example.e_learning.adminFragments.UsersFragment;
import com.example.e_learning.adminFragments.estimatesFragment;
import com.example.e_learning.adminFragments.examFragment;

public class AdminActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private String[] mDrawerItems = {"Оцінки", "Студенти", "Іспити", "Запитання","Вихід"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        );

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList = findViewById(R.id.left_drawer);
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
                        icon.setImageResource(R.drawable.rating);
                        break;
                    case 1:
                        icon.setImageResource(R.drawable.students);
                        break;
                    case 2:
                        icon.setImageResource(R.drawable.exams);
                        break;
                    case 3:
                        icon.setImageResource(R.drawable.questions);
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
                    getSupportActionBar().setTitle("Оцінки");
                    Fragment newFragment1 = new estimatesFragment();
                    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                    transaction1.replace(R.id.fragment_content, newFragment1);
                    transaction1.addToBackStack(null);
                    transaction1.commit();
                    break;
                case 1: // Item 2
                    getSupportActionBar().setTitle("Студенти");
                    Fragment newFragment = new UsersFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_content, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case 2: // Item 3
                    getSupportActionBar().setTitle("Іспити");
                    Fragment newFragment2 = new examFragment();
                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    transaction2.replace(R.id.fragment_content, newFragment2);
                    transaction2.addToBackStack(null);
                    transaction2.commit();
                    break;
                case 3:
                    getSupportActionBar().setTitle("Запитання");
                    Fragment newFragment3 = new QuestionsFragment();
                    FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                    transaction3.replace(R.id.fragment_content, newFragment3);
                    transaction3.addToBackStack(null);
                    transaction3.commit();
                    break;
                case 4:
                    SharedPreferences settings = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    settings.edit().clear().commit();
                    Intent i = new Intent(AdminActivity.this,LoginActivity.class);
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
}
