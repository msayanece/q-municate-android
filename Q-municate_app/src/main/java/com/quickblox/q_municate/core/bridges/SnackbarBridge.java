package com.quickblox.q_municate.core.bridges;

import android.view.View;

public interface SnackbarBridge {

    void createSnackBar(int titleResId, int duration);

    void showSnackbar(int titleResId, int duration);

    void showSnackbar(int titleResId, int duration, int buttonTitleResId, View.OnClickListener onClickListener);

    void hideSnackBar();
}