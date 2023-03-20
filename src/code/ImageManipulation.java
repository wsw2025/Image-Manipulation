package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
//        APImage image = new APImage("cyberpunk2077.jpg");
//        grayScale("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");
//        image.draw();

    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int x = image.getWidth();
        int y = image.getHeight();
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                Pixel pixel = image.getPixel(i, j);
                int average = (pixel.getBlue()+pixel.getGreen()+pixel.getRed())/3;
                pixel.setBlue(average);
                pixel.setGreen(average);
                pixel.setRed(average);
                image.setPixel(i, j, pixel);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int average = (pixel.getBlue()+ pixel.getGreen()+ pixel.getRed())/3;
        return average;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int x = image.getWidth();
        int y = image.getHeight();
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                Pixel pixel = image.getPixel(i, j);
                if(getAverageColour(pixel)<128){
                    pixel.setRed(0);
                    pixel.setBlue(0);
                    pixel.setGreen(0);
                }else{
                    pixel.setRed(255);
                    pixel.setBlue(255);
                    pixel.setGreen(255);
                }
                image.setPixel(i, j, pixel);
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        int x = image.getWidth();
        int y = image.getHeight();
        for (int i = 0; i < x-1; i++){
            for (int j = y-1; j > 0; j--){
                Pixel pixel1 = image.getPixel(i, j);
                Pixel pixel2 = image.getPixel(i, j-1);
                Pixel pixel3 = image.getPixel(i+1, j);
                if(Math.abs(getAverageColour(pixel1)-getAverageColour(pixel2))>threshold || Math.abs(getAverageColour(pixel1)-getAverageColour(pixel3))>threshold){
                    pixel1.setRed(0);
                    pixel1.setBlue(0);
                    pixel1.setGreen(0);
                }else{
                    pixel1.setRed(255);
                    pixel1.setBlue(255);
                    pixel1.setGreen(255);
                }
                image.setPixel(i, j, pixel1);
            }
        }

        image.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image1 = new APImage(pathToFile);
        APImage image2 = new APImage(pathToFile);
        for (int i = 0; i < image1.getWidth(); i++){
            for (int j = 0; j < image1.getHeight(); j++){
                Pixel pixel = image1.getPixel(image1.getWidth()-1-i, j);
                image2.setPixel(i, j, pixel);
            }
        }
        image2.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage image1 = new APImage(pathToFile);
        APImage image2 = new APImage(pathToFile);
        for (int i = 0; i < Math.min(image1.getHeight(), image1.getWidth()); i++){
            for (int j = 0; j < Math.min(image1.getHeight(), image1.getWidth()); j++){
                Pixel pixel = image1.getPixel(i, j);
                image2.setPixel(j, i, pixel);
            }
        }
        image2.draw();
    }

}
