import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ImageProcessor p1 = new ImageProcessor();
        p1.load("11729.jpg");

        long start = System.currentTimeMillis();
        p1.addBrightness(-100);
        long end = System.currentTimeMillis();

        System.out.println("Single: " + (end - start));


        ImageProcessor p2 = new ImageProcessor();
        p2.load("11729.jpg");

        start = System.currentTimeMillis();
        p2.AddBrightnessThreaded(-100);
        end = System.currentTimeMillis();

        System.out.println("Threaded: " + (end - start));

        //new
        int[] hist = processor.computeHistogramThreadPool("r");

        for (int i = 0; i < 256; i++) {
            System.out.println(i + ": " + hist[i]);
        }
    }
}