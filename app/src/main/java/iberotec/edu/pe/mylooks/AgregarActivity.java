package iberotec.edu.pe.mylooks;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.jar.Manifest;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class AgregarActivity extends AppCompatActivity implements View.OnClickListener {

    //para guardar los datos en el armario
        EditText edtName, edtTipo;
        BottomNavigationView parte_arriba, parte_abajo, calzado,vestido;

    private LinearLayout mRevealView;
    private boolean hidden = true;
    private ImageButton gallery_btn, photo_btn;
    ImageView imageView;


    private BottomNavigationView bottomNavigationView;
    private RadioGroup mRadioGrup;
    private RadioButton mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4;


    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);


        init();

        sqLiteHelper = new SQLiteHelper(this, "ParteArribaDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS P(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, tipo VARCHAR, image BLOB)");


        imageView = (ImageView) findViewById(R.id.imageView);
        //relacionamos con el xml
        photo_btn = (ImageButton) findViewById(R.id.photo_img_btn);

        initView();

//CODIGO DE ALERTA DIALOGO
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.ic_save:
                        showChangeLangDialog();

                        break;
                    case R.id.ic_Camara:


                        break;
                }
                return true;
            }
        });
//Añadimos el Listener Boton
        photo_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creamos el Intent para llamar a la Camara
                Intent cameraIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //Creamos una carpeta en la memeria del terminal
                File imagesFolder = new File(
                        Environment.getExternalStorageDirectory(), "Tutorialeshtml5");
                imagesFolder.mkdirs();
                //añadimos el nombre de la imagen
                File image = new File(imagesFolder, "foto.jpg");
                Uri uriSavedImage = Uri.fromFile(image);
                //Le decimos al Intent que queremos grabar la imagen
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                //Lanzamos la aplicacion de la camara con retorno (forResult)
                startActivityForResult(cameraIntent, 1);
            }
        });

    }


    private void init() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtTipo = (EditText) findViewById(R.id.edtTipo);
        gallery_btn = (ImageButton) findViewById(R.id.gallery_img_btn);
        mRadioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        mRadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        mRadioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        mRadioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        parte_arriba = (BottomNavigationView) findViewById(R.id.action_parte_arriba);
        parte_abajo = (BottomNavigationView) findViewById(R.id.action_parte_abajo);
        calzado = (BottomNavigationView) findViewById(R.id.action_calzado);
        vestido = (BottomNavigationView) findViewById(R.id.action_vestido);
        imageView = (ImageView) findViewById(R.id.imageView);

    }

    //ALERTADIALOGO CON RadioGrup
    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final RadioGroup mRadioGrup = (RadioGroup) dialogView.findViewById(R.id.radio_grup);
        final RadioButton mRadioButton1 = (RadioButton) dialogView.findViewById(R.id.radioButton1);

        //PARA GUARDAR EN LA BASE DE DATO
            mRadioButton1.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    try {
                        sqLiteHelper.insertData(
                                edtName.getText().toString().trim(),
                                edtTipo.getText().toString().trim(),
                                imageViewToByte(imageView)
                                );
                        Toast.makeText(getApplicationContext(), "Se guardo correctamente en la categoria Parte Arriba", Toast.LENGTH_SHORT).show();
                        edtName.setText("");
                        edtTipo.setText("");
                       imageView.setImageResource(R.mipmap.ic_launcher);

                    } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            });
        final RadioButton mRadioButton2 = (RadioButton) dialogView.findViewById(R.id.radioButton2);

        mRadioButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Toast.makeText(getApplicationContext(), "Se guardo correctamente en la categoria Parte Arriba", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        final RadioButton mRadioButton3 = (RadioButton) dialogView.findViewById(R.id.radioButton3);

        mRadioButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Toast.makeText(getApplicationContext(), "Se guardo correctamente en la categoria Parte Arriba", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        final RadioButton mRadioButton4 = (RadioButton) dialogView.findViewById(R.id.radioButton4);

        mRadioButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Toast.makeText(getApplicationContext(), "Se guardo correctamente en la categoria Parte Arriba", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        dialogBuilder.setTitle("Elija que tipo de prenda es.");
        dialogBuilder.setIcon(R.drawable.smiley);
        dialogBuilder.setMessage("Tienes cuatro opciones.");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();


}
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        mRevealView.setVisibility(View.GONE);

        gallery_btn = (ImageButton) findViewById(R.id.gallery_img_btn);
        photo_btn = (ImageButton) findViewById(R.id.photo_img_btn);

        gallery_btn.setOnClickListener(this);
        photo_btn.setOnClickListener(this);

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AgregarActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
    //RELACIONANDO CON EL XML


    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

        //Comprovamos que la foto se a realizado
        if (requestCode ==1 && resultCode ==RESULT_OK){
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria
            Bitmap bMap = BitmapFactory.decodeFile(
                    Environment.getExternalStorageDirectory() +
                            "/Tutorialeshtml5/" + "foto.jpg");
            //Añadimos el bitmap al imageView para
            //mostrarlo por pantalla
            imageView.setImageBitmap(bMap);
        }

    }

    @Override
    public void onClick(View v) {
        hideRevealView();
        switch (v.getId()) {

            case R.id.gallery_img_btn:

                break;
            case R.id.photo_img_btn:

                break;
        }
    }
    private void hideRevealView() {
        if (mRevealView.getVisibility() == View.VISIBLE) {
            mRevealView.setVisibility(View.GONE);
            hidden = true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_clip:

                int cx = (mRevealView.getLeft() + mRevealView.getRight());
                int cy = mRevealView.getTop();
                int radius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());

                //Below Android LOLIPOP Version
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    SupportAnimator animator =
                            ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(700);

                    SupportAnimator animator_reverse = animator.reverse();

                    if (hidden) {
                        mRevealView.setVisibility(View.VISIBLE);
                        animator.start();
                        hidden = false;
                    } else {
                        animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                            @Override
                            public void onAnimationStart() {

                            }

                            @Override
                            public void onAnimationEnd() {
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;

                            }

                            @Override
                            public void onAnimationCancel() {

                            }

                            @Override
                            public void onAnimationRepeat() {

                            }
                        });
                        animator_reverse.start();
                    }
                }
                // Android LOLIPOP And ABOVE Version
                else {
                    if (hidden) {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                        mRevealView.setVisibility(View.VISIBLE);
                        anim.start();
                        hidden = false;
                    } else {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, radius, 0);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;
                            }
                        });
                        anim.start();
                    }
                }
                return true;

            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


