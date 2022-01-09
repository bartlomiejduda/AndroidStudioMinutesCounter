package bartek.duda.helloworld1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import bartek.duda.helloworld1.databinding.ActivityMainBinding;
import bartek.duda.helloworld1.ui.main.SectionsPagerAdapter;

public class MainMinutesCounterActivity extends AppCompatActivity {

    private ActivityMainBinding activity_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity_binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activity_binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = activity_binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        //TabLayout tabs = binding.tabs;
        //tabs.setupWithViewPager(viewPager);
        FloatingActionButton info_button = activity_binding.infoButton;

        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Program zrobił Bartłomiej Duda.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });














    }



}