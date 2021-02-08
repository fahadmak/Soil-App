package com.example.bujimuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.bujimuapp.AppConfig.NITROGEN_REC;
import static com.example.bujimuapp.AppConfig.NITROGEN_STATE;
import static com.example.bujimuapp.AppConfig.PHOSPHOROUS_REC;
import static com.example.bujimuapp.AppConfig.PHOSPHOROUS_STATE;
import static com.example.bujimuapp.AppConfig.adequatePhosphorous;
import static com.example.bujimuapp.AppConfig.inAdequateNitrogen;

public class NitrogenCameraActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION_RESULT = 0;
    private static final int STATE_PREVIEW = 0;
    private int mCaptureState = STATE_PREVIEW;
    private TextureView mTextureView;
    private View mPointerView;
    private View mPointerRingView;
    private Bitmap bitmap;
    private Size mImageSize;
    private ImageReader mImageReader;

    private ImageReader.OnImageAvailableListener onImageAvailableListener = new
            ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                }
            };
    private TextureView.SurfaceTextureListener mSurfaceListener = new TextureView.SurfaceTextureListener() {

        boolean processing;

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            Toast.makeText(getApplicationContext(), "Is available", Toast.LENGTH_SHORT).show();
            setupCamera(width, height);
            connectCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            Bitmap photo = mTextureView.getBitmap();

            int pixel = photo.getPixel(photo.getWidth() / 2, photo.getHeight() / 2);
            int redValue1 = Color.red(pixel);
            int blueValue1 = Color.blue(pixel);
            int greenValue1 = Color.green(pixel);
            int color = Color.rgb(redValue1, greenValue1, blueValue1);
            mPointerRingView.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            String noNitrogen = AppConfig.noNitro.get(color);

            String inAdequate = AppConfig.inAdequateNitro.get(color);

            String adequate = AppConfig.adequateNiTro.get(color);

            if (noNitrogen != null) {
                Bundle colorBundle = new Bundle();
                colorBundle.putInt(AppConfig.COLOR_RED, Color.red(color));
                colorBundle.putInt(AppConfig.COLOR_BLUE, Color.blue(color));
                colorBundle.putInt(AppConfig.COLOR_GREEN, Color.green(color));
                colorBundle.putString(NITROGEN_STATE,noNitrogen);
                colorBundle.putString(NITROGEN_REC,"NITROGEN: UREA = 20 KG, NPK = 40 KG ");
                Intent resultIntent = new Intent();
                resultIntent.putExtras(colorBundle);
                setResult(AppConfig.NITROGEN_COLOR_CODE, resultIntent);
                finish();
            } else if (inAdequate != null) {
                Bundle colorBundle = new Bundle();
                colorBundle.putInt(AppConfig.COLOR_RED, Color.red(color));
                colorBundle.putInt(AppConfig.COLOR_BLUE, Color.blue(color));
                colorBundle.putInt(AppConfig.COLOR_GREEN, Color.green(color));
                colorBundle.putString(NITROGEN_STATE,inAdequate);
                colorBundle.putString(NITROGEN_REC,"NITROGEN: UREA = 10 KG, NPK= 20 KG ");
                Intent resultIntent = new Intent();
                resultIntent.putExtras(colorBundle);
                setResult(AppConfig.NITROGEN_COLOR_CODE, resultIntent);
                finish();
            } else if (adequate != null) {
                Bundle colorBundle = new Bundle();
                colorBundle.putInt(AppConfig.COLOR_RED, Color.red(color));
                colorBundle.putInt(AppConfig.COLOR_BLUE, Color.blue(color));
                colorBundle.putInt(AppConfig.COLOR_GREEN, Color.green(color));
                colorBundle.putString(NITROGEN_STATE,adequate);
                colorBundle.putString(NITROGEN_REC,"NITROGEN: UREA = 0 KG, NPK = 0 KG ");
                Intent resultIntent = new Intent();
                resultIntent.putExtras(colorBundle);
                setResult(AppConfig.NITROGEN_COLOR_CODE, resultIntent);
                finish();
            }
        }
    };

    private interface ImageResponse {
        void processFinished();
    }

    private class ImageTask extends AsyncTask<Void, Void, Exception> {
        private Bitmap photo;
        private ImageResponse imageResponse;

        ImageTask(Bitmap photo, ImageResponse imageResponse) {
            this.photo = photo;
            this.imageResponse = imageResponse;
        }

        @Override
        protected Exception doInBackground(Void... params) {
            // do background work here
            imageResponse.processFinished();
            return null;
        }

        @Override
        protected void onPostExecute(Exception result) {

        }
    }

    private HandlerThread mBackgroundThread;
    private Handler mHandler;
    private static SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);

    }

    private static class CompareSizeByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() /
                    (long) rhs.getWidth() * rhs.getHeight());
        }
    }

    private CameraDevice mCameraDevice;
    private CameraDevice.StateCallback mStateCallBack = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            startPreview();
//            Toast.makeText(getApplicationContext(), "Camera is connected", Toast.LENGTH_SHORT)
//                    .show();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            camera.close();
            mCameraDevice = null;

        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            camera.close();
            mCameraDevice = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nitrogen);
        mTextureView = (TextureView) findViewById(R.id.textureView);
        mPointerRingView = findViewById(R.id.activity_color_picker_pointer_ring);
        mPointerView = findViewById(R.id.activity_main_pointer);
    }

    private String mCameraId;
    private Size mPreviewSize;
    private CameraCaptureSession mPreviewCaptureSession;
    private CameraCaptureSession.CaptureCallback mPreviewCaptureCallback = new
            CameraCaptureSession.CaptureCallback() {
                private void process(CaptureResult result) {
                    Toast.makeText(getApplicationContext(), "hjkdkd", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCaptureProgressed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureResult partialResult) {
                    super.onCaptureProgressed(session, request, partialResult);
                    process(partialResult);
                }

                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                }
            };
    private CaptureRequest.Builder mCaptureRequestBuilder;

    @Override
    protected void onResume() {
        startBackgroundThread();
        super.onResume();
        if (mTextureView.isAvailable()) {
            setupCamera(mTextureView.getWidth(), mTextureView.getHeight());
            connectCamera();
        } else {
            mTextureView.setSurfaceTextureListener(mSurfaceListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "App won't run without camera service",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onPause() {
        closeCamera();
        stopBackgroundThread();
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decarView = getWindow().getDecorView();
        if (hasFocus) {
            decarView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
    }

    private void setupCamera(int width, int height) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics = cameraManager
                        .getCameraCharacteristics(cameraId);
                if (cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)
                        == CameraCharacteristics.LENS_FACING_FRONT) {
                    continue;
                }
                StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics
                        .SCALER_STREAM_CONFIGURATION_MAP);
                int deviceRotation = getWindowManager().getDefaultDisplay().getRotation();
                int totalRotation = sensorToDeviceRotation(cameraCharacteristics, deviceRotation);
                boolean swapRotation = totalRotation == 90 || totalRotation == 270;
                int rotatedWidth = width;
                int rotatedHeight = height;
                if (swapRotation) {
                    rotatedHeight = width;
                    rotatedWidth = height;
                }
                mPreviewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class),
                        rotatedWidth, rotatedHeight);
                mImageSize = chooseOptimalSize(map.getOutputSizes(ImageFormat.JPEG), rotatedWidth, rotatedHeight);
                mImageReader = ImageReader.newInstance(mImageSize.getWidth(), mImageSize.getHeight(), ImageFormat.JPEG, 1);
                mImageReader.setOnImageAvailableListener(onImageAvailableListener, mHandler);
                mCameraId = cameraId;
                return;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void connectCamera() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    cameraManager.openCamera(mCameraId, mStateCallBack, mHandler);
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        Toast.makeText(this,
                                "Camera app needs permission to use Camera",
                                Toast.LENGTH_SHORT).show();
                    }

                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION_RESULT);
                }
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void startPreview() {
        SurfaceTexture surfaceTexture = mTextureView.getSurfaceTexture();
        surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
        Surface previewSurface = new Surface(surfaceTexture);
        try {
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mCaptureRequestBuilder.addTarget(previewSurface);
            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface,
                    mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    mPreviewCaptureSession = session;
                    try {
                        mPreviewCaptureSession.setRepeatingRequest(mCaptureRequestBuilder.build(), null, mHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    Toast.makeText(getApplicationContext(), "Un able to setup camera",
                            Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void closeCamera() {
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraImage");
        mBackgroundThread.start();
        mHandler = new Handler(mBackgroundThread.getLooper());
    }

    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int sensorToDeviceRotation(CameraCharacteristics cameraCharacteristics, int deviceRotation) {
        int sensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        deviceRotation = ORIENTATIONS.get(deviceRotation);
        return (sensorOrientation + deviceRotation + 360) % 360;
    }

    private static Size chooseOptimalSize(Size[] choices, int width, int height) {
        List<Size> bigEnough = new ArrayList<Size>();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * height / width
                    && option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }

        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizeByArea());
        } else return choices[0];
    }
}


