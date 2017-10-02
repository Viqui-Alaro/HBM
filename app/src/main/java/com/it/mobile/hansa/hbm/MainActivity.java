package com.it.mobile.hansa.hbm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.it.mobile.hansa.hbm.adapters.MainAdapter;


public class MainActivity extends AppCompatActivity {

    //region Permisos
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] CAMERA_PERMS={
            Manifest.permission.CAMERA
    };
    private static final String[] CONTACTS_PERMS={
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final String[] WRITE_EXTERNAL_PERMS={
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final String[] CALL_PHONE_PERMS={
            Manifest.permission.CALL_PHONE
    };
    private static final String[] SEND_SMS_PERMS={
            Manifest.permission.SEND_SMS
    };
    private static final int INITIAL_REQUEST=999;
    private static final int CAMERA_REQUEST=INITIAL_REQUEST+1;
    private static final int CONTACTS_REQUEST=INITIAL_REQUEST+2;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;
    private static final int WRITE_EXTERNAL_REQUEST=INITIAL_REQUEST+4;
    private static final int CALL_PHONE_REQUEST=INITIAL_REQUEST+5;
    private static final int SEND_SMS_REQUEST=INITIAL_REQUEST+6;
    //endregion;

    private MainAdapter mMainAdapter;
    private ViewPager mViewPager;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.appbarMain);
        //setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.container);

        mMainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainAdapter);

        if (!puedeAccederLocation() || !puedeAccederContacts() || !puedeAccederCamera()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        updateTable();

        switch(requestCode) {
            case CAMERA_REQUEST:
                if (puedeAccederCamera()) {
                    doCameraThing();
                }
                else {
                    bzzzt();
                }
                break;

            case CONTACTS_REQUEST:
                if (puedeAccederContacts()) {
                    doContactsThing();
                }
                else {
                    bzzzt();
                }
                break;

            case LOCATION_REQUEST:
                if (puedeAccederLocation()) {
                    doLocationThing();
                }
                else {
                    bzzzt();
                }
                break;
            case WRITE_EXTERNAL_REQUEST:
                if (puedeAccederEscribirExternal()) {

                }
                else {
                    bzzzt();
                }
                break;
            case CALL_PHONE_REQUEST:
                if (puedeAccederLlamadas()) {

                }
                else {
                    bzzzt();
                }
                break;
            case SEND_SMS_REQUEST:
                if (puedeAccederEnviarSMS()) {

                }
                else {
                    bzzzt();
                }
                break;
        }
    }

    private void updateTable() {
        /*location.setText(String.valueOf(canAccessLocation()));
        camera.setText(String.valueOf(canAccessCamera()));
        internet.setText(String.valueOf(hasPermission(Manifest.permission.INTERNET)));
        contacts.setText(String.valueOf(canAccessContacts()));
        storage.setText(String.valueOf(hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)));*/
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean puedeAccederLocation() {
        return(tienePermiso(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean puedeAccederCamera() {
        return(tienePermiso(Manifest.permission.CAMERA));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean puedeAccederContacts() {
        return(tienePermiso(Manifest.permission.READ_CONTACTS));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean puedeAccederEscribirExternal() {
        return(tienePermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean puedeAccederLlamadas() {
        return(tienePermiso(Manifest.permission.CALL_PHONE));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean puedeAccederEnviarSMS() {
        return(tienePermiso(Manifest.permission.SEND_SMS));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean tienePermiso(String Permiso) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(Permiso));
    }

    private void bzzzt() {
        Toast.makeText(this, "Lo sentimos, pero no podemos hacer esto, porque no nos dio permiso!", Toast.LENGTH_LONG).show();
    }

    private void doCameraThing() {
        Toast.makeText(this, "Con permiso para camara", Toast.LENGTH_SHORT).show();
    }

    private void doContactsThing() {
        Toast.makeText(this, "Con permiso para contactos", Toast.LENGTH_SHORT).show();
    }

    private void doLocationThing() {
        Toast.makeText(this, "Con permiso para GPS", Toast.LENGTH_SHORT).show();
    }
}
