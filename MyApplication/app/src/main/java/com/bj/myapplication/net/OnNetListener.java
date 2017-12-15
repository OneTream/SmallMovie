package com.bj.myapplication.net;

import java.io.IOException;

/**
 * Created by 朝朝暮暮 on 2017/10/16.
 */

public interface OnNetListener {
    public void onSuccess(Object o) throws IOException;
    public void onError(IOException e);
}
