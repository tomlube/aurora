package dev.encounter.aurora;

import android.content.Context;
import android.view.SurfaceHolder;

import org.libsdl.app.SDLSurface;

public class AuroraSurface extends SDLSurface {
    private static native void nativeSetSurfaceReady(boolean ready);
    private static native void nativeSetSurfaceResizing(boolean resizing);

    public AuroraSurface(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        nativeSetSurfaceReady(false);
        super.surfaceCreated(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        nativeSetSurfaceReady(false);
        super.surfaceDestroyed(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Same native window, new size: native code keeps its GPU surface and reconfigures it.
        nativeSetSurfaceResizing(true);
        nativeSetSurfaceReady(false);
        super.surfaceChanged(holder, format, width, height);
        nativeSetSurfaceReady(mIsSurfaceReady);
        nativeSetSurfaceResizing(false);
    }
}
