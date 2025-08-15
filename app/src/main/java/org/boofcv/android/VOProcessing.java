package org.boofcv.android;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import boofcv.abst.feature.detect.interest.PointDetector;
import boofcv.abst.tracker.PointTracker;
import boofcv.alg.feature.detect.interest.FactoryDetectPoint;
import boofcv.factory.tracker.FactoryPointTracker;
import boofcv.factory.geo.FactoryVisualOdometry;
import boofcv.struct.calib.CameraPinholeBrown;
import boofcv.struct.image.GrayF32;

/**
 * Example processing class that feeds camera frames into a simple visual odometry pipeline.
 */
public class VOProcessing extends DemoProcessingAbstract<GrayF32> {

    // Visual odometry object
    boofcv.abst.sfm.VisualOdometry<GrayF32, GrayF32> vo;

    public VOProcessing() {
        super(GrayF32.class);

        // Placeholder intrinsics, replace with your calibrated values for real results
        CameraPinholeBrown intrinsics = new CameraPinholeBrown(
                500, 500, 0, 320, 240, 640, 480
        );

        // Create a simple KLT tracker
        PointDetector<GrayF32> detector = FactoryDetectPoint.createShiTomasi(null, GrayF32.class);
        PointTracker<GrayF32> tracker = FactoryPointTracker.klt(detector, GrayF32.class, null);

        // Create monocular visual odometry using BoofCV factory
        vo = FactoryVisualOdometry.monoBasic(null, tracker, GrayF32.class);
        vo.setCalibration(intrinsics);
    }

    @Override
    public void process(GrayF32 gray) {
        if (vo.process(gray)) {
            Log.i("POSE", vo.getCameraToWorld().toString());
        }
    }

    @Override
    public void onDraw(Canvas canvas, Matrix matrix) {
        // For now, do nothing. You could draw trajectory or tracked points here.
    }
            }
