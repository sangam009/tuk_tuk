package com.traveldiary.home;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.tasks.OnSuccessListener;
import com.traveldiary.R;
import com.traveldiary.base.BaseActivity;
import com.traveldiary.base.BaseModel;
import com.traveldiary.base.Wrapper;
import com.traveldiary.home.adapters.HomeCardAdapter;
import com.traveldiary.home.model.PackageOverViewModel;
import com.traveldiary.home.services.HomeService;
import com.traveldiary.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 3456;
    private static final String TAG = "TAG";
    private static final int SOURCE_LOC = 123;
    private static final int DEST_LOC = 234;
    public static final int SOURCE_DATE_PICK = 123456;
    public static final int DEST_DATE_PICK = 234567;
    private Locations source = new Locations();
    private Locations destination = new Locations();


    private TextView sourceLoc, destinationLoc;
    private TextView tripStartDate, tripEndDate;
    private RecyclerView homeCardRecycler;
    private HomeCardAdapter homeCardAdapter;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        callService(null);
        locationMethod(new MainActivity.FetchLocation(){
            @Override
            public void setLocation(Location location) {
                //changeLocaionOnUi
            }
        });
    }

    private void locationMethod(final FetchLocation fetchLocation){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known locations. In some rare situations this can be null.
                    if (location != null) {
                        fetchLocation.setLocation(location);
                        // Logic to handle locations object
                    }
                }
            });
        }else{
            //Request Permission for gps
        }


    }

    public void enableGpsWithinApp() {
        if (googleApiClient == null) {

            googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                    .addApi(LocationServices.API).build();
            googleApiClient.connect();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);
            builder.setNeedBle(true);


            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final com.google.android.gms.common.api.Status status = result.getStatus();
                    final LocationSettingsStates state = result.getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                status.startResolutionForResult(MainActivity.this, 1/*REQUEST_CHECK_SETTINGS*/);
                            } catch (IntentSender.SendIntentException e) {
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            });
        }
    }

    private void callService(Bundle bundle) {
        // call service then set Adapter
        if(bundle == null){
            bundle = new Bundle();
            bundle.putString("sourceLat","0");
            bundle.putString("sourceLng","0");
            bundle.putString("destinationLat","0");
            bundle.putString("destinationLng","0");
        }
        new HomeService().callPackages(MainActivity.this, HomeService.RequestMode.WITHOUT_LOCATION,bundle, new HomeService.PackagesListener(){
            @Override
            public void onResponse(BaseModel baseModel, String jsonString, String reasonOfError, Constants.ReceivedFrom receivedFrom) {
                if(baseModel != null){
                    Wrapper<PackageOverViewModel> wrapper = (Wrapper<PackageOverViewModel>) baseModel;
                    setAdapter(wrapper.getWrapper());
                }
            }
        });

//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(R.drawable.images1);
//        list.add(R.drawable.images2);
//        list.add(R.drawable.images3);
//        list.add(R.drawable.images4);
//
//        setAdapter(list);
    }

    private void setAdapter(ArrayList<PackageOverViewModel> list) {

        homeCardAdapter = new HomeCardAdapter(MainActivity.this, list, new HomeCardAdapter.CardClickListener(){
            @Override
            public void onCardClick() {

            }
        });
        homeCardRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        homeCardRecycler.setAdapter(homeCardAdapter);


    }

    private void findViews() {
        sourceLoc = (TextView) findViewById(R.id.source_location_text);
        tripStartDate = (TextView) findViewById(R.id.source_date_text);
        destinationLoc = (TextView) findViewById(R.id.destination_location_text);
        tripEndDate = (TextView) findViewById(R.id.destination_date_text);
        homeCardRecycler = (RecyclerView) findViewById(R.id.home_card_recycler);

        sourceLoc.setOnClickListener(this);
        tripStartDate.setOnClickListener(this);
        destinationLoc.setOnClickListener(this);
        tripEndDate.setOnClickListener(this);
    }

    public void findPlace(int id) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, id);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    // A place has been received; use requestCode to track the request.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SOURCE_LOC || requestCode == DEST_LOC) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                if(requestCode == SOURCE_LOC){
                    sourceLoc.setText(place.getName());
                    source.setId(place.getId());
                    source.setName(String.valueOf(place.getName()));
                    source.setLat(String.valueOf(place.getLatLng().latitude));
                    source.setLng(String.valueOf(place.getLatLng().longitude));

                }else{
                    destinationLoc.setText(place.getName());
                    destination.setId(place.getId());
                    destination.setName(String.valueOf(place.getName()));
                    destination.setLat(String.valueOf(place.getLatLng().latitude));
                    destination.setLng(String.valueOf(place.getLatLng().longitude));
                }
                Log.i(TAG, "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.source_location_text:{
                findPlace(SOURCE_LOC);
                break;
            }case R.id.source_date_text:{
                long time = -1;

                if(destination.getDate() > 0){
                    time = destination.getDate() - (24 * 60 * 60 *1000);
                }

                datePick(SOURCE_DATE_PICK , time);
                break;
            }case R.id.destination_location_text:{
                findPlace(DEST_LOC);
                break;
            }case R.id.destination_date_text:{
                long time = -1;

                if(source.getDate() > 0){
                    time = source.getDate() + (24 * 60 * 60 * 1000);
                }else{
                    time = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
                }

                datePick(DEST_DATE_PICK, time);
                break;
            }
        }
    }

    private interface FetchLocation {
        void setLocation(Location location);
    }

    private class Locations {
        private String id;
        private String name;
        private String lat;
        private String lng;
        private long date = -1;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public long getDate() {
            return date;
        }
    }

    @Override
    protected void onShowAlertNotOkPressed(int tag) {
        super.onShowAlertNotOkPressed(tag);
    }

    @Override
    protected void onShowAlertOkPressed(int tag, Object datepick) {
        super.onShowAlertOkPressed(tag, datepick);

        if (tag == SOURCE_DATE_PICK || tag == DEST_DATE_PICK) {
            DatePicker datePicker = (DatePicker) datepick;
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String formatedDate = sdf.format(new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth()));
            try {
                date = sdf.parse(formatedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (tag == SOURCE_DATE_PICK) {
                tripStartDate.setText(datePicker.getDayOfMonth() + " / " + (datePicker.getMonth() + 1) + " / " + datePicker.getYear());
                source.setDate(date.getTime());
            }

            if (tag == DEST_DATE_PICK) {
                tripEndDate.setText(datePicker.getDayOfMonth() + " / " + (datePicker.getMonth() + 1) + " / " + datePicker.getYear());
                destination.setDate(date.getTime());
            }
        }

    }
}
