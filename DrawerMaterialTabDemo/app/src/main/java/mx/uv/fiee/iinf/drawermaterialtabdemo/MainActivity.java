package mx.uv.fiee.iinf.drawermaterialtabdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        // establecemos la toolbar como barra de acciones
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        //habilitamos el indicador home en la toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        }

        drawerLayout = findViewById (R.id.drawerLayout);

        //la clase DrawerToogle vincula la toolbar con el drawer layout
        drawerToggle = new ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.setDrawerIndicatorEnabled (true);
        drawerToggle.syncState ();

        drawerLayout.addDrawerListener (drawerToggle);

        navView = findViewById (R.id.navView);
        navView.setNavigationItemSelectedListener (menuItem -> {
            NavViewItemSelected (menuItem);
            return true;
        });

    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if (item.getItemId () == android.R.id.home) {
            drawerLayout.openDrawer (GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    void NavViewItemSelected (MenuItem menuItem) {
        switch (menuItem.getItemId ()) {
            case R.id.nav_first_fragment:
                break;
            case R.id.nav_second_fragment:
                break;
            case R.id.nav_third_fragment:
                break;
            case R.id.nav_subitem_1:
                break;
            case R.id.nav_subitem_2:
                Intent intent = new Intent (this, TabsActivity.class);
                startActivity (intent);
                break;
        }

        menuItem.setChecked (true);
        setTitle (menuItem.getTitle ());
        drawerLayout.closeDrawers ();
    }

}
