package org.boofcv.android;

import android.util.Log;
import boofcv.struct.image.GrayF32;
import boofcv.struct.calib.CameraPinholeBrown;

public class VOProcessing extends DemoProcessingAbstract<GrayF32> {

    VisualOdometryProcessor voProcessor;

    public VOProcessing() {
        super(GrayF32.class);

        CameraPinholeBrown intrinsics = new CameraPinholeBrown(
                500, 500, 0, 320, 240, 640, 480
        );
        voProcessor = new VisualOdometryProcessor(intrinsics);
    }

    @Override
    public void process(GrayF32 gray) {
        voProcessor.processFrame(gray);
        Log.i("POSE", voProcessor.getCurrentPose().toString());
    }
}
