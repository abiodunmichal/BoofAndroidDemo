package org.boofcv.android;

import android.graphics.Canvas;
import android.graphics.Matrix;

import boofcv.abst.sfm.VisualOdometry;
import boofcv.factory.sfm.FactoryVisualOdometry;
import boofcv.struct.image.GrayF32;
import georegression.struct.se.Se3_F64;

public class VOProcessing extends DemoProcessingAbstract<GrayF32> {

    VisualOdometry<GrayF32> vo;

    public VOProcessing() {
        super(GrayF32.class);
    }

    @Override
    public void initialize(int width, int height, int sensorOrientation) {
        // Create a monocular VO algorithm (PnP with depth)
        vo = FactoryVisualOdometry.monoDepthPnP(null, GrayF32.class);
    }

    @Override
    public void process(GrayF32 input) {
        if (vo != null) {
            vo.process(input);
        }
    }

    @Override
    public void onDraw(Canvas canvas, Matrix imageToView) {
        // TODO: draw VO results (camera pose, trajectory, etc.)
    }
    }
