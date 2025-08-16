package org.boofcv.android;

import boofcv.abst.sfm.VisualOdometry;
import boofcv.factory.sfm.FactoryVisualOdometry;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.ImageType;
import georegression.struct.se.Se3_F64;

public class VOProcessing extends DemoProcessingAbstract<GrayF32> {

    VisualOdometry<GrayF32, Se3_F64> vo;

    public VOProcessing() {
        super(ImageType.single(GrayF32.class));

        // Create a simple monocular VO algorithm
        // You can swap this for stereo or depth-based later
        vo = FactoryVisualOdometry.monoDepthPnP(
                null,    // default configuration
                GrayF32.class
        );
    }

    @Override
    public void process(GrayF32 grayImage) {
        if (vo.process(grayImage)) {
            // Successfully updated VO state
            Se3_F64 cameraToWorld = vo.getCameraToWorld();
            System.out.println("Camera Pose: " + cameraToWorld);
        } else {
            System.out.println("VO update failed on this frame.");
        }
    }

    @Override
    public void stop() {
        // Clean up resources if needed
    }
}
