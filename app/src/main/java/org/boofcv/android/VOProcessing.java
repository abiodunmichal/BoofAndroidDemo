package org.boofcv.android;

import android.graphics.Canvas;
import android.graphics.Matrix;

import boofcv.abst.sfm.d3.VisualOdometry;
import boofcv.factory.sfm.FactoryVisualOdometry;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.ImageType;
import georegression.struct.se.Se3_F64;

public class VOProcessing extends DemoProcessingAbstract<GrayF32> {

    VisualOdometry<GrayF32, Se3_F64> vo;

    public VOProcessing() {
        super(ImageType.single(GrayF32.class));

        // Create monocular visual odometry
        vo = FactoryVisualOdometry.monoPnP(null, GrayF32.class);
    }

    @Override
    public void process(GrayF32 grayImage) {
        if (vo.process(grayImage)) {
            Se3_F64 cameraToWorld = vo.getCameraToWorld();
            System.out.println("Camera Pose: " + cameraToWorld);
        } else {
            System.out.println("VO update failed");
        }
    }

    @Override
    public void onDraw(Canvas canvas, Matrix imageToView) {
        // For now, nothing drawn — later we can add trajectory or features
    }

    @Override
    public void stop() {
        // Cleanup if needed
    }
    }
