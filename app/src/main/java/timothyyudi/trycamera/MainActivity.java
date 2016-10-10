package timothyyudi.trycamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int REQUEST_IMAGE_CAPTURE = 1001; //bisa menggunakan angka berapapun

    Button btnImgCamera, btnImgStorage;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImgCamera = (Button) findViewById(R.id.btnImgCamera);
        btnImgCamera.setOnClickListener(this);
        btnImgStorage = (Button) findViewById(R.id.btnImgStorage);
        btnImgStorage.setOnClickListener(this);

        ivImage = (ImageView) findViewById(R.id.ivImage);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnImgCamera){
            dispatchTakePictureIntent();
        }else if(view.getId()==R.id.btnImgStorage){}
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivImage.setImageBitmap(imageBitmap);
        }
    }
}
