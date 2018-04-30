package e.subsh.configurabluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_SOLICITUD_HABILItAR_BLUETOOTH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onHsbilitarBluetooth (View v)
    {
        solicitarPermiso();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(),"No se ha encontrado el Bluetooth",Toast.LENGTH_LONG).show();
        }
        if(!bluetoothAdapter.isEnabled())
        {
            Intent intentHabiitarBlue = new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intentHabiitarBlue,CODIGO_SOLICITUD_HABILItAR_BLUETOOTH);
        }
        if(bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.disable();
        }
    }

    public boolean VerificarEstadoPermiso()
    {
        int resultado = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.BLUETOOTH);
        if(resultado == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void solicitarPermiso()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH))
        {
            Toast.makeText(getApplicationContext(),"El permiso fue otorgado, si deseas desactivarlo ve a la configuración de Android", Toast.LENGTH_LONG).show();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.BLUETOOTH},CODIGO_SOLICITUD_HABILItAR_BLUETOOTH);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case CODIGO_SOLICITUD_HABILItAR_BLUETOOTH:
                if(VerificarEstadoPermiso())
                {
                    Toast.makeText(getApplicationContext(), "Ya está activo el permiso",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"El permiso no está activo",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
