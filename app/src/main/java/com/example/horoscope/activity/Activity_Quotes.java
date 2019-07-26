package com.example.horoscope.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.MainActivity;
import com.example.horoscope.R;
import com.example.horoscope.adapter.Quotes_Adapter;
import com.example.horoscope.ultil.ReadJson;
import com.example.horoscope.ultil.SharePrefManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class Activity_Quotes extends AppCompatActivity {

    private String zodiac;
    private List<String> images;
    private TextView tvTitle;
    private DiscreteScrollView myQuotes;
    private InfiniteScrollAdapter infiniteAdapter;

    private String imageURL;
    private Target target;
    private ProgressDialog progressDialog;
    private PermissionListener permissionlistener;

    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        init();
        setUpInfiniteScroll();
        setUpTargetToShare();
        buildProgresDialog();
    }

    private void init() {
        zodiac = SharePrefManager.getZodiac(this);
        myQuotes = findViewById(R.id.item_picker);
        try {
            images = readCompanyJSONFile(this, "zodiac_quote.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(zodiac + " Quotes");
    }

    private void setUpInfiniteScroll() {
        myQuotes.setOrientation(DSVOrientation.HORIZONTAL);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new Quotes_Adapter(images, this));
        myQuotes.setAdapter(infiniteAdapter);
        myQuotes.setItemTransitionTimeMillis(350);
        myQuotes.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        myQuotes.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
                int positionInDataSet = infiniteAdapter.getRealPosition(position);
                imageURL = images.get(positionInDataSet);
            }
        });
    }

    public List<String> readCompanyJSONFile(Context context, String fileName) throws IOException, JSONException {
        List<String> images = new ArrayList<>();
        String jsonText = ReadJson.readText(context, fileName);
        JSONObject jsonRoot = new JSONObject(jsonText);

        JSONArray jsonArray = jsonRoot.getJSONArray(zodiac.toLowerCase());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String image = object.getString("url");

            images.add(image);
        }
        return images;
    }

    public void btnDownload(View view) {
        if (state == 1) {
            Picasso.with(getApplicationContext())
                    .load(imageURL)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            saveBitmap(bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
        } else {
            requestPermisstion();
            TedPermission.with(this)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("Ứng dụng cần quyền truy cập vào Thư Viện ")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .check();
        }
    }

    private void saveBitmap(Bitmap bitmap) {
        String root = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(Activity_Quotes.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MediaScannerConnection.scanFile(Activity_Quotes.this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

    private void requestPermisstion() {
        permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(Activity_Quotes.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                SharePrefManager.setPermisstionState(getApplicationContext(), 1);
                state = 1;
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(Activity_Quotes.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                SharePrefManager.setPermisstionState(getApplicationContext(), 0);
                state = 0;
            }

        };
    }

    public void btnShare(View view) {
        if (state == 1) {
            Picasso.with(this)
                    .load(imageURL)
                    .into(target);
        } else {
            requestPermisstion();
            TedPermission.with(this)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("Ứng dụng cần quyền truy cập vào Thư Viện ")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .check();
        }
    }

    private void setUpTargetToShare() {
        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                progressDialog.dismiss();
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "SomeText", null);
                Intent intent = new Intent(Intent.ACTION_SEND);
                //intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");
                Uri screenshotUri = Uri.parse(path);
                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                intent.setType("image/*");
                startActivity(Intent.createChooser(intent, "Share image via..."));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Toast.makeText(Activity_Quotes.this, "Photo dowload failed...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                progressDialog.show();
            }
        };
    }

    private void buildProgresDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Wating....");
        progressDialog.setMessage("Wait for image downloading...");

    }

    public void imgBack(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String[] permissionArrays = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        for (int i = 0; i < permissionArrays.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissionArrays[i]) == PackageManager.PERMISSION_GRANTED) {
                SharePrefManager.setPermisstionState(this, 1);
            } else {
                SharePrefManager.setPermisstionState(this, 0);
            }
        }
        state = SharePrefManager.getPermisstionState(this);
    }
}
