package org.firstinspires.ftc.teamcode.EOCV;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class TestPipeline extends OpenCvPipeline {

    Telemetry telemetry;

    public TestPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    // Color range for purple in HSV
    public Scalar lowerPurple = new Scalar(130, 80, 80);
    public Scalar upperPurple = new Scalar(160, 255, 255);

    private final Mat hsv = new Mat();
    private final Mat mask = new Mat();
    private final Mat hierarchy = new Mat();
    private final Mat output = new Mat();

    // Detected ball properties
    public Point ballCenter = new Point(-1, -1);
    public double ballRadius = 0;

    @Override
    public Mat processFrame(Mat input) {

        // Convert RGB to HSV
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        // Threshold the image to keep only purple
        Core.inRange(hsv, lowerPurple, upperPurple, mask);

        // Reduce noise
        Imgproc.GaussianBlur(mask, mask, new Size(5, 5), 0);

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw results on a copy of the original image
        input.copyTo(output);

        double maxArea = 0;
        MatOfPoint bestContour = null;

        // Find the largest contour (likely the ball)
//
//        telemetry.addData("[Hello]", "World!");
//        telemetry.update();

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > maxArea) {
                maxArea = area;
                bestContour = contour;
            }
        }

        if (bestContour != null) {
            // Approximate contour to circle
            MatOfPoint2f contour2f = new MatOfPoint2f(bestContour.toArray());
            Point center = new Point();
            float[] radius = new float[1];
            Imgproc.minEnclosingCircle(contour2f, center, radius);

            // Filter out very small blobs / noise
            if (radius[0] > 10) {
                ballCenter = center;
                ballRadius = radius[0];

                // Draw circle and center point
                Imgproc.circle(output, center, (int) radius[0], new Scalar(255, 0, 255), 3); // Purple outline
                Imgproc.circle(output, center, 5, new Scalar(0, 255, 0), -1); // Green center

                telemetry.addData("Center", ballCenter);
                telemetry.addData("Radius",  ballRadius);
                telemetry.update();
            }
        }

        return output;
    }
}

