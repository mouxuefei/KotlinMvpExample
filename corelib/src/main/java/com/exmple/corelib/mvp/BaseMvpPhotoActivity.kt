package com.exmple.corelib.mvp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.SystemClock
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import com.exmple.corelib.showToastCenter
import com.exmple.corelib.utils.CommonUtil
import com.exmple.corelib.utils.PhotoUtils
import com.sihaiwanlian.baselib.mvp.BaseMvpTitleActivity
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by mou on 2017/8/14.
 * desc：需要的权限CAMERA，READ_EXTERNAL_STORAGE，
 */
abstract class BaseMvpPhotoActivity<V : ITopView, P : ITopPresenter> : BaseMvpTitleActivity<V, P>() {
    private val fileUri = File(Environment.getExternalStorageDirectory().path + "/" + SystemClock.currentThreadTimeMillis() + ".jpg")
    private var imageUri: Uri? = null

    companion object {
        private val CAMERA_PERMISSIONS_REQUEST_CODE = 111
        private val STORAGE_PERMISSIONS_REQUEST_CODE = 222
        val CAMERA_REQUEST_CODE = 200
        val GALLERY_REQUEST_CODE = 300
    }

    /**
     * 打开相机
     */
    fun openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                //                showToastCenter("您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE), CAMERA_PERMISSIONS_REQUEST_CODE)
        } else {
            if (CommonUtil.hasSdcard()) {
                imageUri = Uri.fromFile(fileUri)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0
                    imageUri = FileProvider.getUriForFile(this, "$packageName.fileprovider", fileUri)
                }
                PhotoUtils.takePicture(this, imageUri, CAMERA_REQUEST_CODE)
            } else {
                showToastCenter("设备没有SD卡！")
            }
        }
    }


    /**
     * 打开相册
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun openGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSIONS_REQUEST_CODE)
        } else {
            PhotoUtils.openPic(this, GALLERY_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
        //调用系统相机申请拍照权限回调
            CAMERA_PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (CommonUtil.hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            imageUri = FileProvider.getUriForFile(this, "$packageName.fileprovider", fileUri)
                        }
                        PhotoUtils.takePicture(this, imageUri, CAMERA_REQUEST_CODE)
                    } else {
                        showToastCenter("设备没有SD卡！")
                    }
                } else {
                    showToastCenter("请允许打开相机！！")
                }
            }
        //调用系统相册申请Sdcard权限回调
            STORAGE_PERMISSIONS_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                PhotoUtils.openPic(this, GALLERY_REQUEST_CODE)
            } else {
                showToastCenter("您拒绝了权限，无法使用相机功能")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                imageUri?.let {
                    val bitmapFromUri = PhotoUtils.getBitmapFromUri(it, this)
                    val bitmapDegree = getBitmapDegree(it.path)
                    val bitmap = rotateBitmapByDegree(bitmapFromUri, bitmapDegree)
                    if (bitmap == null) {
                        showToast("您拍摄的照片不符合要求!")
                        return
                    }
                    try {
                        val fileIcon = File(Environment.getExternalStorageDirectory().path + "/" + SystemClock.currentThreadTimeMillis() + "4.jpg")
                        saveBitmapToUri(bitmap, fileIcon.absolutePath)
                        getbitmap(fileIcon.path, bitmap)
                    } catch (e: IOException) {
                        showToast("图片压缩异常")
                    }
                }
            } else if (GALLERY_REQUEST_CODE == requestCode) {
                if (CommonUtil.hasSdcard()) {
                    var newUri = Uri.parse(PhotoUtils.getPath(this, data?.data))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        newUri = FileProvider.getUriForFile(this, "$packageName.fileprovider", File(newUri.path))
                    }
                    val bitmapDegree = getBitmapDegree(newUri.path)

                    val bitmapFromUri = PhotoUtils.getBitmapFromUri(newUri, this)
                    if (bitmapFromUri == null) {
                        showToast("您选择的图片不正确!")
                        return
                    }
                    val bitmap = rotateBitmapByDegree(bitmapFromUri, bitmapDegree)
                    if (bitmap == null) {
                        showToast("您选择的图片不正确!")
                        return
                    }
                    try {
                        val fileIcon = File(Environment.getExternalStorageDirectory().path + "/" + SystemClock.currentThreadTimeMillis() + "4.jpg")
                        saveBitmapToUri(bitmap, fileIcon.absolutePath)
                        getbitmap(fileIcon.path, bitmap)
                    } catch (e: IOException) {
                        showToast("图片压缩异常")
                    }
                } else {
                    showToastCenter("您的设备没有SD卡！")
                }
            }
        }

    }

    protected abstract fun getbitmap(imgPath: String, bitmap: Bitmap)

    /**
     * 读取图片的旋转的角度
     */
    private fun getBitmapDegree(path: String): Int {
        var degree = 0
        try {
            val exifInterface = ExifInterface(path)
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL)
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return degree
    }

    /**
     * 将图片按照某个角度进行旋转
     */
    fun rotateBitmapByDegree(bm: Bitmap?, degree: Int): Bitmap? {
        var returnBm: Bitmap? = null
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        try {
            bm?.let {
                returnBm = Bitmap.createBitmap(it, 0, 0, bm.width, bm.height, matrix, true)
            }
        } catch (e: OutOfMemoryError) {

        }

        if (returnBm == null) {
            returnBm = bm
        }
        if (bm != returnBm) {
            bm!!.recycle()
        }
        return returnBm
    }

    /**
     * 把压缩后的bitmap转化为file;
     */
    private fun saveBitmapToUri(bitmap: Bitmap, path: String): Boolean {
        val file = File(path)
        if (file.exists()) {
            if (file.delete()) {
                if (!file.createNewFile()) {
                    return false
                }
            }
        }
        val outStream = BufferedOutputStream(FileOutputStream(file))
        //100为压缩的品质,100为100%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream.flush()
        outStream.close()
        return true
    }
}
