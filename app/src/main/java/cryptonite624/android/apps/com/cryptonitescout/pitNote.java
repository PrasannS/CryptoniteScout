package cryptonite624.android.apps.com.cryptonitescout;

import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class pitNote extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    public Button toNextPage;
    public FloatingActionButton toCamera;
    public Switch Programmer_On_Site;
    public Switch From_Cali;
    public Switch Penalties;
    public Button toDashboard;

    //Spinner variables for the wheels
    public Spinner wheelSpinner;
    public String wheels;
    public static String [] wheelArr = {"Roughtop", "Colson", "Mecanum", "Omni", "Pneu"};

    //Spinner variables for the layout
    public Spinner layoutSpinner;
    public String layouts;
    public static String [] layoutArr = {"4WD", "6WD", "8WD", "10WD", "Swerve", "Tank", "2+2", "Rhino", "Kiwi", "H","Comment"};

    //Spinner variables for Climbing Levels
    public Spinner ClimibLevels;
    public String Levels;
    public static String [] levels = {"Level 1","Level 2","Level 3","Buddy to one"};

    //Spinner variables for Cargo Intake
    public Spinner cargoIntake;
    public String Intake2;

    //Spinner variables for hatch Intake
    public Spinner hatchIntake;
    public String Intake;
    public static String [] Intakes = {"Floor","Station","Both","None","Other(List Comments"};

    //Spinner variables for programming languages
    public Spinner pgLanguage;
    public String language;
    public static String [] languages = {"Java","C++","LabView","Python","Other(List Comments)"};

    /**TODO
     * all the stuff from the discord should be on there
     * Go for Everything
     * comments section call (other notes
     * scouting map
     *  reminder to add
     *  green-scouted
     *  yellow-partial
     *  red-no data
     *  server updates
     *  excel

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toDashboard = findViewById(R.id.toDashboardPage);
        toDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboard();
            }
        });


        //Button that opens up the default camera app
        toCamera = findViewById(R.id.fab);
        toCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openCamera();
            }
        });


        //Check if Switch Button "programmer on site" is true or false
        Programmer_On_Site = findViewById(R.id.Programmer_On_Site);
        Programmer_On_Site.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Programmer on site :",""+isChecked);
            }
        });

        From_Cali = findViewById(R.id.Shifters);
        From_Cali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Shifters : ",""+isChecked);
            }
        });

        Penalties = findViewById(R.id.CrossBaseline);
        Penalties.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Cross Baseline : ",""+isChecked);
            }
        });

        pgLanguage = findViewById(R.id.Programming_Language);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,languages);
        pgLanguage.setAdapter(adapter);

        hatchIntake = findViewById(R.id.HatchIntake);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Intakes);
        hatchIntake.setAdapter(adapter1);

        cargoIntake = findViewById(R.id.CargoIntake);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Intakes);
        cargoIntake.setAdapter(adapter2);

        ClimibLevels = findViewById(R.id.ClimbLevel);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        ClimibLevels.setAdapter(adapter3);

        layoutSpinner = findViewById(R.id.LayoutSpinner);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, layoutArr);
        layoutSpinner.setAdapter(adapter4);

        wheelSpinner = findViewById(R.id.Wheels);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, wheelArr);
        wheelSpinner.setAdapter(adapter5);

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;




    //function that captures the picture
    private void dispatchCameraIntent(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try{
                //Create the file where the photo should go
                photoFile = createImageFile();
            }
            catch (IOException ex){
                //In case of error while creating the file
                //Log.i(Tag"IOException");
            }
            //Continue only if the File was created
            if (photoFile != null){
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    //Create an image file name (saving the image to gallery)fla
    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+timeStamp+"_";
        File storageDir = Environment.getExternalStorageDirectory();
        File image = File.createTempFile(imageFileName,".jpg",storageDir);
        mCurrentPhotoPath = "file:"+image.getAbsolutePath();
        return image;
    }

    protected void onActivityREsult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            try{
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                mImageView.setImageBitmap(mImageBitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //adding the picture to the gallery
    private void addPictoGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    //reducing the scale of image to save memory
    //Converting bitmap into
    private void setPic(){
        //get the dimension of the picture
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        //get the dimension of bitmap
        BitmapFactory.Options bmOPtions = new BitmapFactory.Options();
        bmOPtions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOPtions);
        int photoW = bmOPtions.outWidth;
        int photoH = bmOPtions.outHeight;

        //Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        //Decode the image file into a Bitmap sized to fill the view
        bmOPtions.inJustDecodeBounds = false;
        bmOPtions.inSampleSize = scaleFactor;
        bmOPtions.inPurgeable = true;

        Bitmap bitmap = new BitmapFactory().decodeFile(mCurrentPhotoPath, bmOPtions);
        mImageView.setImageBitmap(bitmap);
    }

    //Opening the camera app
    public void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }

    //function that allows the button to open up the login page
    public void openNextPage(){
        Intent intent = new Intent(this, pitNotePageTwo.class);
        startActivity(intent);
    }

    //Opening the dashboard page
    public void openDashboard(){
        Intent intent = new Intent(this,DataAccessActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //setting the dropdown elements and displaying the texts for the programming languages
        language = languages[position];
        Toast.makeText(parent.getContext(), language, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the hatch intakes
        Intake = Intakes[position];
        Toast.makeText(parent.getContext(), language, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the cargo intakes
        Intake2 = Intakes[position];
        Toast.makeText(parent.getContext(), language, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the climbing levels
        Levels = levels[position];
        Toast.makeText(parent.getContext(), language, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the climbing levels
        layouts = layoutArr[position];
        Toast.makeText(parent.getContext(), language, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the climbing levels
        wheels = wheelArr[position];
        Toast.makeText(parent.getContext(), language, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Nothing goes here
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Nothing goes here
    }
}