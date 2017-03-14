package com.demo.webview.protocol.impl.jsapi.tools;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.demo.RequestCodes;
import com.demo.webview.IAct;
import com.demo.webview.callback.ICallBack;
import com.demo.webview.protocol.BaseProtocolInstance;
import com.demo.webview.protocol.param.PickImageParam;
import com.demo.webview.util.FileUtils;

import java.io.File;

/**
 * 用途：
 * Created by milk on 17/3/14.
 * 邮箱：649444395@qq.com
 */

public class UploadTakePhotoExecute extends BaseProtocolInstance<PickImageParam> {
    private String newPath;

    @Override
    public void doExecute(IAct iAct, ICallBack iCallBackm, PickImageParam params) {
        super.doExecute(iAct, iCallBackm, params);
        newPath = FileUtils.getCachePath( iAct.getContext()) + FileUtils.getImgName(".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File mImagePath = new File(newPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImagePath));
        iAct.startActivityForResult(intent, 1001);
    }

    @Override
    public void onActivityResult(IAct iAct, ICallBack iCallBack, int requestCode, int resultCode, Intent data) {
        super.onActivityResult(iAct, iCallBack, requestCode, resultCode, data);
        if (requestCode == 1001) {
            Uri uri = data.getData();
            if (uri != null) {
                String path = uri.getPath();
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = iAct.getActivity().managedQuery(uri, proj, null, null, null);
                if (cursor != null) {
                    int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    path = cursor.getString(colum_index);
                }
                if (!TextUtils.isEmpty(path)) {
                    File compressFile = FileUtils.compressFile(path, newPath);
                    if (compressFile == null) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public int[] useCode() {
        return new int[]{RequestCodes.REQ_CAMERA};
    }
}
