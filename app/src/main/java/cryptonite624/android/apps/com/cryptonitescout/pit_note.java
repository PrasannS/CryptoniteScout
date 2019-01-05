package cryptonite624.android.apps.com.cryptonitescout;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class pit_note extends AppCompatActivity {

    public Button toNextPage;
    public FloatingActionButton toCamera;
    public Switch Programmer_On_Site;
    public Switch From_Cali;
    public Switch Penalties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toNextPage = (Button)findViewById(R.id.pit_note_nextPage);
        //Place holder until I make the next page
        // toNextPage.setOnClickListener();

        //Button that opens up the default camera app
        toCamera = (FloatingActionButton)findViewById(R.id.fab);
        toCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openCamera();
            }
        });


        //Check if Switch Button "programmer on site" is true or false
        Programmer_On_Site = (Switch)findViewById(R.id.Programmer_On_Site);
        Programmer_On_Site.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Programmer on site :",""+isChecked);
            }
        });

        From_Cali = (Switch)findViewById(R.id.From_Cali);
        From_Cali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("From Cali : ",""+isChecked);
            }
        });

        Penalties = (Switch)findViewById(R.id.Penalties);
        Penalties.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Penalties : ",""+isChecked);
            }
        });


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

    protected void onActivityREsult(int requestCode, int resultCode, Intent data)throws IOException{
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
    public void openLoginPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}