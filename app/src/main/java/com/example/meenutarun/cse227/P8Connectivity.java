package com.example.meenutarun.cse227;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;
import android.Manifest.permission;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class P8Connectivity extends AppCompatActivity {
    ConnectivityManager conn;
    static ImageView iv;

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p8_connectivity);
        iv = (ImageView) findViewById(R.id.iv);

        conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        ActivityCompat.requestPermissions(this,
                new String[]
                        {
                                Manifest.permission.ACCESS_FINE_LOCATION
                        }, 3);

//        An intent filter is an expression in an app's manifest file that specifies the
// type of intents that the component would like to receive. For instance, by declaring an
// intent filter for an activity, you make it possible for other apps to directly start
// your activity with a certain kind of intent.
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver1, filter1);

        IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver2, filter2);


    }

    private final BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            Toast.makeText(context,"Inside on Receive of receiver1", Toast.LENGTH_SHORT).show();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {

                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(P8Connectivity.this, "Bluetooth Off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(P8Connectivity.this, "Turning Bluetooth off...", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(P8Connectivity.this, "Bluetooth on", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(P8Connectivity.this, "Turning Bluetooth on...", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver2 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"Inside on Receive of receiver2", Toast.LENGTH_SHORT).show();
            String action = intent.getAction();

            //Parcelable process is much faster than Serializable(save objects). One of the reasons for this is that we are being explicit about the serialization process instead of using reflection to infer it. It also stands to reason that the code has been heavily optimized for this purpose.

              if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                Toast.makeText(P8Connectivity.this,deviceName,Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void NetworkStatus(View view) {

        String imagepath = "http://placeimg.com/640/360";
        String textpath = "https://www.dropbox.com/s/m83h320c153o0iw/myfile.txt?dl=1";

        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
        {
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            {
                Toast.makeText(this,"WI FI", Toast.LENGTH_SHORT).show();
            }

            if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
            {
                Toast.makeText(this,"Mobile", Toast.LENGTH_SHORT).show();
                new MyImageTask().execute(imagepath);
                //new MyTextTask().execute(textpath);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver1);

        bluetoothAdapter.cancelDiscovery();
        unregisterReceiver(mReceiver2);
    }

    public void discoverBluetoothDevices(View view)
    {
        if (bluetoothAdapter!=null)

            bluetoothAdapter.startDiscovery();
        //Toast.makeText(this,"Start Discovery"+bluetoothAdapter.startDiscovery(),Toast.LENGTH_SHORT).show();
    }

    public void turnOnBluetooth(View view) {
        if(bluetoothAdapter == null)
        {
            Toast.makeText(this, "Device not supported buletooth", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(!bluetoothAdapter.isEnabled())
            {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(i,1);
            }
            if(bluetoothAdapter.isEnabled())
            {
                Toast.makeText(this, "Bluetooth is enabled", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode ==1)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "Bluetooth turned On", Toast.LENGTH_SHORT).show();
            }
            if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "Bluetooth turned on Failed", Toast.LENGTH_SHORT).show();
            }
        }

        else if (requestCode == 2)
        {
            if (resultCode != RESULT_CANCELED)
            {
                Toast.makeText(this, "Device Discoverablility Start", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Device Discoverablility Canceled", Toast.LENGTH_SHORT).show();
        }
    }

    public void turnOffBluetooth(View view) {
        if(bluetoothAdapter != null)
        {
            bluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "Bluetooth Turned off" ,Toast.LENGTH_LONG).show();
        }
    }

    public void makeDiscoverable(View view) {

        if (bluetoothAdapter != null) {

            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);

            startActivityForResult(discoverableIntent,2);
        }
        else
            Toast.makeText(P8Connectivity.this,"hello",Toast.LENGTH_SHORT).show();
    }

    public void getPairedDevices(View view) {
        if(bluetoothAdapter != null)
        {
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                // There are paired devices. Get the name and address of each paired device.
                for (BluetoothDevice device : pairedDevices)
                {
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address

                    Toast.makeText(this,"Paired Devices are:- "+ deviceName, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

class MyImageTask  extends AsyncTask<String, Void, Bitmap>
{
    @Override
    protected Bitmap doInBackground(String... strings)
    {
        return downloadImage1(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null)
        {
            P8Connectivity.iv.setImageBitmap(bitmap);
            P8Connectivity.iv.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }


    Bitmap downloadImage1(String path)
    {
        Bitmap bitmap = null;

        try {
            URL url = new URL(path);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);

  //GET method is used to transfer data from client to server in HTTP protocol.
            httpURLConnection.setRequestMethod("GET");

            //setDoInput flag true if you intend to use URL connection for input.Bydefault it is true only
            httpURLConnection.setDoInput(true);

            httpURLConnection.connect();
            int code = httpURLConnection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK)
            {
                //Reading the data
                InputStream stream = httpURLConnection.getInputStream();
                if(stream != null)
                {
                    bitmap  = BitmapFactory.decodeStream(stream);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}

class MyTextTask extends AsyncTask<String, Void,String>
{
    @Override
    protected String doInBackground(String... strings) {
        return downLoadText(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("TAG",s);

    }

    String downLoadText(String path)
    {
        String text = null;
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            int code = httpURLConnection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                    text = sb.toString();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;

    }
}


