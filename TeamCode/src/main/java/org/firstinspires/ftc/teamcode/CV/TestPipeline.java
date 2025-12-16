package org.firstinspires.ftc.teamcode.CV;

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

    public Scalar lowerPurple = new Scalar(130, 80, 80);
    public Scalar upperPurple = new Scalar(160, 255, 255);

    public Scalar lowerGreen = new Scalar(40, 80, 80);
    public Scalar upperGreen = new Scalar(80, 255, 255);

    private final Mat hsv = new Mat();
    private final Mat purpleMask = new Mat();
    private final Mat greenMask = new Mat();
    private final Mat hierarchy = new Mat();
    private final Mat output = new Mat();

    public Point purpleCenter = new Point(-1, -1);
    public double purpleRadius = 0;

    public Point greenCenter = new Point(-1, -1);
    public double greenRadius = 0;

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsv, lowerPurple, upperPurple, purpleMask);
        Core.inRange(hsv, lowerGreen, upperGreen, greenMask);

        Imgproc.GaussianBlur(purpleMask, purpleMask, new Size(5, 5), 0);
        Imgproc.GaussianBlur(greenMask, greenMask, new Size(5, 5), 0);

        List<MatOfPoint> purpleContours = new ArrayList<>();
        List<MatOfPoint> greenContours = new ArrayList<>();
        Imgproc.findContours(purpleMask, purpleContours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.findContours(greenMask, greenContours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        input.copyTo(output);

        processBall(purpleContours, output, new Scalar(255, 0, 255), "Purple");
        processBall(greenContours, output, new Scalar(0, 255, 0), "Green");

        telemetry.update();

        return output;
    }

    private void processBall(List<MatOfPoint> contours, Mat output, Scalar color, String label) {
        double maxArea = 0;
        MatOfPoint bestContour = null;

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > maxArea) {
                maxArea = area;
                bestContour = contour;
            }
        }

        if (bestContour != null) {
            MatOfPoint2f contour2f = new MatOfPoint2f(bestContour.toArray());
            Point center = new Point();
            float[] radius = new float[1];
            Imgproc.minEnclosingCircle(contour2f, center, radius);

            if (radius[0] > 10) {
                Imgproc.circle(output, center, (int) radius[0], color, 3);
                Imgproc.circle(output, center, 5, new Scalar(255, 255, 255), -1);

                Imgproc.putText(
                        output,
                        label,
                        new Point(center.x - 30, center.y - radius[0] - 10),
                        Imgproc.FONT_HERSHEY_SIMPLEX,
                        0.7,
                        color,
                        2
                );

                telemetry.addData(label + " Center", center);
                telemetry.addData(label + " Radius", radius[0]);

                if (label.equals("Purple")) {
                    purpleCenter = center;
                    purpleRadius = radius[0];
                } else {
                    greenCenter = center;
                    greenRadius = radius[0];
                }
            }
        }
    }
}
