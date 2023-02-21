package com.example.aptitudetest.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;


import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.activities.Login;
import com.example.aptitudetest.application.MainApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Muhammad Ahmad on 07/06/2020.
 */
public class AppUtils {

    private static final double IMAGE_MAX_SIZE = 1024;
    public static boolean isPinSet = true;


    public static void logOutApplication() {

        PreferenceUtils.getInstance().clearPreferences();

        PreferenceUtils.getInstance().clearPreferences();
        Intent intent = new Intent(MainApplication.getAppContext(),
                Log.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        MainApplication.getAppContext()
                .startActivity(intent);
    }

    public static ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    public static boolean hasPermission(String permission) {
        if (canMakeSmores() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            return (MainApplication.getAppContext()
                    .checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }


    private static boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    /**
     * For phone like Sony, Samsung and HTC gets the photo.
     * in landscape. To correct that the following code has been used.
     * The image display size to be passed or else it will create bitmap
     * .as per the captured image resolution.
     * Then it will lead to out of memory issue.
     *
     * @param photoPath - String
     * @return - Bitmap
     */
    public static Bitmap checkPhotoOrientationAndGetImage(String photoPath) {
        try {
            ExifInterface ei = new ExifInterface(photoPath);
            int photoOrientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            Log.d("ahmad", "photoOrientation: " + photoOrientation);
            switch (photoOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateBitmap(decodeImageFile(photoPath), 90.f);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateBitmap(decodeImageFile(photoPath), 180.f);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateBitmap(decodeImageFile(photoPath), 270.f);
                default:
                    return decodeImageFile(photoPath);
            }
        } catch (IOException ie) {
            Log.d("ahmad", "IO Exception while creating bitmap");
        }
        return null;
    }

    /**
     * This is useful to rotate the image. But this will take more memory.
     *
     * @param source - Bitmap
     * @param angle  - float
     * @return - Bitmap
     */
    private static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        Bitmap resultImage = Bitmap.createBitmap(source, 0, 0, source.getWidth(),
                source.getHeight(), matrix, true);
        if (resultImage != source) {
            source.recycle();
        }
        if (source.getWidth() != resultImage.getWidth()) {
            resultImage = Bitmap.createScaledBitmap(resultImage, source.getWidth(),
                    source.getHeight(), false);
        }
        System.gc();
        return resultImage;
    }

    /**
     * To convert and scale image from the path to bitmap.
     *
     * @param imagePath - String
     * @return - Bitmap
     */
    private static Bitmap decodeImageFile(String imagePath) {
        File file = new File(imagePath);
        Bitmap bitmap = null;

        //Decode image size
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            BitmapFactory.decodeStream(fis, null, options);
            fis.close();

            int scale = 1;
            if (options.outHeight > IMAGE_MAX_SIZE || options.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                        (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
            }
            //Decode with inSampleSize
            BitmapFactory.Options options1 = new BitmapFactory.Options();
            options1.inSampleSize = scale;
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis, null, options1);
            fis.close();
        } catch (IOException io) {
            //  io.printStackTrace();
        }
        return bitmap;
    }




    public static void  sweetAlertDialogueOk(String title, String content, Context context){
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });

        sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = ((SweetAlertDialog) dialog)
                        .getButton(SweetAlertDialog.BUTTON_POSITIVE);
                positiveButton.setBackgroundDrawable(context.getResources()
                        .getDrawable(R.drawable.circular_purple_round));
                positiveButton.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        sweetAlertDialog.show();
    }

    public static SweetAlertDialog  loading(Context context){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#f2b578"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);

        return pDialog;
    }
}
