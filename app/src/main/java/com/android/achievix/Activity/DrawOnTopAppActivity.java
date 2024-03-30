package com.android.achievix.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.achievix.R;

import java.util.Objects;

public class DrawOnTopAppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_on_top_app);

        TextView appName = findViewById(R.id.app_block_app_name);
        ImageView appIcon = findViewById(R.id.app_block_app_icon);
        Button exitButton = findViewById(R.id.exit_app_block);

        PackageManager packageManager = getApplicationContext().getPackageManager();

        try {
            String name = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(Objects.requireNonNull(getIntent().getStringExtra("packageName")), PackageManager.GET_META_DATA));
            Drawable icon = getPackageManager().getApplicationIcon(Objects.requireNonNull(getIntent().getStringExtra("packageName")));
            appName.setText(name);
            appIcon.setImageDrawable(icon);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("DrawOnTopAppActivity", "Package name not found", e);
        }

        exitButton.setOnClickListener(view -> {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(startMain);
            finish();
        });
    }
}
