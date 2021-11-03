package com.labters.documentscannerandroid;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.kotlinpermissions.KotlinPermissions;
import com.labters.documentscanner.ImageCropActivity;
import com.labters.documentscanner.helpers.ScannerConstants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0003J\"\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010\u001f\u001a\u00020\u00162\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0006\u0010\"\u001a\u00020\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006#"},
        d2 = {"Lcom/labters/documentscannerandroid/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnPick", "Landroid/widget/Button;", "getBtnPick", "()Landroid/widget/Button;", "setBtnPick", "(Landroid/widget/Button;)V", "imgBitmap", "Landroid/widget/ImageView;", "getImgBitmap", "()Landroid/widget/ImageView;", "setImgBitmap", "(Landroid/widget/ImageView;)V", "mCurrentPhotoPath", "", "getMCurrentPhotoPath", "()Ljava/lang/String;", "setMCurrentPhotoPath", "(Ljava/lang/String;)V", "askPermission", "", "createImageFile", "Ljava/io/File;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setView", "AndroidDocumentScanner.app"}
)
public final class MainActivity extends AppCompatActivity {
    public Button btnPick;
    public ImageView imgBitmap;
    public String mCurrentPhotoPath;
    private HashMap _$_findViewCache;

    @NotNull
    public final Button getBtnPick() {
        Button var10000 = this.btnPick;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnPick");
        }

        return var10000;
    }

    public final void setBtnPick(@NotNull Button var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.btnPick = var1;
    }

    @NotNull
    public final ImageView getImgBitmap() {
        ImageView var10000 = this.imgBitmap;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgBitmap");
        }

        return var10000;
    }

    public final void setImgBitmap(@NotNull ImageView var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.imgBitmap = var1;
    }

    @NotNull
    public final String getMCurrentPhotoPath() {
        String var10000 = this.mCurrentPhotoPath;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCurrentPhotoPath");
        }

        return var10000;
    }

    public final void setMCurrentPhotoPath(@NotNull String var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.mCurrentPhotoPath = var1;
    }

    @SuppressLint("WrongConstant")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && resultCode == -1 && data != null) {
            Uri selectedImage = data.getData();
            Bitmap btimap = (Bitmap)null;

            try {
                InputStream var15;
                if (selectedImage != null) {
                    boolean var8 = false;
                    boolean var9 = false;
                    boolean var11 = false;
                    var15 = this.getContentResolver().openInputStream(selectedImage);
                } else {
                    var15 = null;
                }

                InputStream inputStream = var15;
                btimap = BitmapFactory.decodeStream(inputStream);
                ScannerConstants.selectedImageBitmap = btimap;
                this.startActivityForResult(new Intent((Context)this, ImageCropActivity.class), 1234);
            } catch (Exception var12) {
                var12.printStackTrace();
            }
        } else if (requestCode == 1231 && resultCode == -1) {
            ContentResolver var14 = this.getContentResolver();
            String var10001 = this.mCurrentPhotoPath;
            if (var10001 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurrentPhotoPath");
            }

            try {
                ScannerConstants.selectedImageBitmap = Media.getBitmap(var14, Uri.parse(var10001));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.startActivityForResult(new Intent((Context)this, ImageCropActivity.class), 1234);
        } else if (requestCode == 1234 && resultCode == -1) {
            if (ScannerConstants.selectedImageBitmap != null) {
                ImageView var10000 = this.imgBitmap;
                if (var10000 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imgBitmap");
                }

                var10000.setImageBitmap(ScannerConstants.selectedImageBitmap);
                var10000 = this.imgBitmap;
                if (var10000 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imgBitmap");
                }

                var10000.setVisibility(0);
                Button var13 = this.btnPick;
                if (var13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("btnPick");
                }

                var13.setVisibility(8);
            } else {
                Toast.makeText((Context)this, (CharSequence)"Not OK", 1).show();
            }
        }

    }

    @SuppressLint("ResourceType")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(1300009);
        View var10001 = this.findViewById(1000132);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.btnPick)");
        this.btnPick = (Button)var10001;
        var10001 = this.findViewById(1000143);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.imgBitmap)");
        this.imgBitmap = (ImageView)var10001;
        this.askPermission();
    }

    public final void askPermission() {
        if (ContextCompat.checkSelfPermission((Context)this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission((Context)this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission((Context)this, "android.permission.CAMERA") == 0) {
            this.setView();
        } else {
            KotlinPermissions.with((FragmentActivity)this).permissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA"}).onAccepted((Function1)(new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((List)var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull List permissions) {
                    Intrinsics.checkParameterIsNotNull(permissions, "permissions");
                    MainActivity.this.setView();
                }
            })).onDenied((Function1)(new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((List)var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull List permissions) {
                    Intrinsics.checkParameterIsNotNull(permissions, "permissions");
                    MainActivity.this.askPermission();
                }
            })).onForeverDenied((Function1)(new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((List)var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull List permissions) {
                    Intrinsics.checkParameterIsNotNull(permissions, "permissions");
                    Toast.makeText((Context)MainActivity.this, (CharSequence)"You have to grant permissions! Grant them from app settings please.", 1).show();
                    MainActivity.this.finish();
                }
            })).ask();
        }

    }

    public final void setView() {
        Button var10000 = this.btnPick;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnPick");
        }

        var10000.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                Builder builder = new Builder((Context)MainActivity.this);
                builder.setTitle((CharSequence)"Carbon");
                builder.setMessage((CharSequence)"Görseli nereden seçmek istesiniz ?");
                builder.setPositiveButton((CharSequence)"Gallery", (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent("android.intent.action.PICK");
                        intent.setType("image/*");
                        MainActivity.this.startActivityForResult(intent, 1111);
                    }
                }));
                builder.setNegativeButton((CharSequence)"Camera", (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                        if (cameraIntent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                            File photoFile = (File)null;

                            try {
                                photoFile = MainActivity.this.createImageFile();
                            } catch (IOException var6) {
                                Log.i("Main", "IOException");
                            }

                            if (photoFile != null) {
                                android.os.StrictMode.VmPolicy.Builder builder = new android.os.StrictMode.VmPolicy.Builder();
                                StrictMode.setVmPolicy(builder.build());
                                cameraIntent.putExtra("output", (Parcelable)Uri.fromFile(photoFile));
                                MainActivity.this.startActivityForResult(cameraIntent, 1231);
                            }
                        }

                    }
                }));
                builder.setNeutralButton((CharSequence)"Cancel", (android.content.DialogInterface.OnClickListener)null);
                AlertDialog var10000 = builder.create();
                Intrinsics.checkExpressionValueIsNotNull(var10000, "builder.create()");
                AlertDialog dialog = var10000;
                dialog.show();
            }
        }));
    }

    @SuppressLint({"SimpleDateFormat"})
    private final File createImageFile() throws IOException {
        String timeStamp = (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        StringBuilder var10001 = (new StringBuilder()).append("file:");
        Intrinsics.checkExpressionValueIsNotNull(image, "image");
        this.mCurrentPhotoPath = var10001.append(image.getAbsolutePath()).toString();
        return image;
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }
}
