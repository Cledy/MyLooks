package iberotec.edu.pe.mylooks;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import iberotec.edu.pe.mylooks.fragment.CalzadoFragment;
import iberotec.edu.pe.mylooks.fragment.ParteAbajoFragment;
import iberotec.edu.pe.mylooks.fragment.ParteArribaFragment;
import iberotec.edu.pe.mylooks.fragment.VestidoFragment;
import iberotec.edu.pe.mylooks.adapter.ViewPagerAdapter;

public class ArmarioActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

//This is our viewPager
    private ViewPager viewPager;

    //Fragments
    ParteArribaFragment parteArribaFragment;
    ParteAbajoFragment parteAbajoFragment;
    CalzadoFragment calzadoFragment;
    VestidoFragment vestidoFragment;
    MenuItem prevMenuItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armario);

        //Initializing viewPager
        viewPager =(ViewPager)findViewById(R.id.viewpager);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Initializing the bottomNavigationView
        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_parte_arriba:
                                viewPager.setCurrentItem(0);




                                break;
                            case R.id.action_parte_abajo:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_calzado:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.action_vestido:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

        setupViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_armario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_agregar) {
            Intent intent= new Intent(ArmarioActivity.this,AgregarActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        parteArribaFragment=new ParteArribaFragment();
        parteAbajoFragment=new ParteAbajoFragment();
        calzadoFragment=new CalzadoFragment();
        vestidoFragment=new VestidoFragment();
        adapter.addFragment(parteArribaFragment);
        adapter.addFragment(parteAbajoFragment);
        adapter.addFragment(calzadoFragment);
        adapter.addFragment(vestidoFragment);
        viewPager.setAdapter(adapter);
    }

}





