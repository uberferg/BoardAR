package boardar.example.com.boardar.ArUtils;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class StereoUtils {

    public static Mat stereoResize(Mat inputImage) {
        int inputW = inputImage.cols();
        int inputH = inputImage.rows();
        int resizeW = Math.round(inputW/2)-1;
        int resizeH = Math.round(inputH/2);

        Mat resizedImage = new Mat(resizeH, resizeW, inputImage.type());
        Imgproc.resize(inputImage, resizedImage, new Size(resizeW, resizeH));
        return resizedImage;
    }

    public static Mat stereoRender(Mat resizedImage, Mat outputImage) {
        int outputW = outputImage.cols();
        int outputH = outputImage.rows();
        int resizeW = resizedImage.cols();
        int resizeH = resizedImage.rows();
        int resizeTopRow = (outputH - resizeH)/2;
        //Log.d("DEBUG stereoRender", "input height:"+inputH);
        //Log.d("DEBUG stereoRender", "input width:"+inputW);
        //Log.d("DEBUG stereoRender", "resized height:"+resizeH);
        //Log.d("DEBUG stereoRender", "resized width:"+resizeW);

        //Mat resizedImage = new Mat(resizeH, resizeW, inputImage.type());
        //Imgproc.resize(inputImage, resizedImage, new Size(resizeW, resizeH));
        //Log.d("DEBUG stereoRender", "actual re height:"+resizedImage.rows());
        //Log.d("DEBUG stereoRender", "actual re width:"+resizedImage.cols());

        outputImage = new Mat(outputH, outputW, resizedImage.type(), new Scalar(0,0,0));
        Mat leftEyeSubmat = outputImage.submat(resizeTopRow, resizeTopRow + resizeH, 0, resizeW);
        Mat rightEyeSubmat = outputImage.submat(resizeTopRow, resizeTopRow + resizeH, outputW-resizeW, outputW);

        resizedImage.copyTo(leftEyeSubmat);
        resizedImage.copyTo(rightEyeSubmat);

        System.gc();
        return outputImage;
    }

}
