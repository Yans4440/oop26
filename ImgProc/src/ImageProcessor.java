import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.clamp;

public class ImageProcessor {
    private BufferedImage img;

    public void load(String path) throws IOException {
        File file = new File(path);
        this.img = ImageIO.read(file);
    }

    public void save(String path) throws IOException {
        File file = new File(path);
        ImageIO.write(this.img, "png", file);
    }

    public void addBrightness(int amount) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int color = img.getRGB(x, y);
                int blue = color & 0x0000FF;
                int green = (color & 0x00FF00) >> 8;
                int red = (color & 0xFF0000) >> 16;

                blue = clamp(blue + amount, 0, 255);
                green = clamp(green + amount, 0, 255);
                red = clamp(red + amount, 0, 255);

                int newColor = blue | (green << 8) | (red << 16);
                img.setRGB(x, y, newColor);
            }
        }
    }

    public void AddBrightnessThreaded(int amount) throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();

        Thread[] threads = new Thread[cores];
        for (int i = 0; i < cores; i++) {
            int startRow = (img.getHeight() / cores) * i;
            int endRow = (i == cores - 1) ? img.getHeight() : (img.getHeight() / cores) * (i + 1);
            AddBrightnessWorker worker = new AddBrightnessWorker(img, amount, startRow, endRow);
            threads[i] = new Thread(worker);

            threads[i].start();
        }
        for (int j = 0; j < cores; j++) {
            threads[j].join();
        }
    }
    //New
    public void addBrightnessThreadPool(int amount) throws InterruptedException {
        int height = img.getHeight();
        int width = img.getWidth();

        ExecutorService executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        for (int y = 0; y < height; y++) {
            int row = y;

            executor.submit(() -> {
                for (int x = 0; x < width; x++) {
                    int color = img.getRGB(x, row);

                    int blue = color & 0x0000FF;
                    int green = (color & 0x00FF00) >> 8;
                    int red = (color & 0xFF0000) >> 16;
                    int alpha = (color >>> 24);

                    blue = clamp(blue + amount, 0, 255);
                    green = clamp(green + amount, 0, 255);
                    red = clamp(red + amount, 0, 255);

                    int newColor =
                            (alpha << 24) |
                                    (red << 16) |
                                    (green << 8) |
                                    blue;

                    img.setRGB(x, row, newColor);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }
    public int[] computeHistogramThreadPool(String channel) throws InterruptedException {
        int height = img.getHeight();
        int width = img.getWidth();

        int threadsCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        AtomicIntegerArray global = new AtomicIntegerArray(256);

        for (int t = 0; t < threadsCount; t++) {
            int startRow = (height / threadsCount) * t;
            int endRow = (t == threadsCount - 1)
                    ? height
                    : (height / threadsCount) * (t + 1);

            executor.submit(() -> {
                int[] local = new int[256];

                for (int y = startRow; y < endRow; y++) {
                    for (int x = 0; x < width; x++) {

                        int color = img.getRGB(x, y);

                        int value = switch (channel.toLowerCase()) {
                            case "r" -> (color >> 16) & 0xFF;
                            case "g" -> (color >> 8) & 0xFF;
                            case "b" -> color & 0xFF;
                            default -> throw new IllegalArgumentException("Channel must be R, G or B");
                        };

                        local[value]++;
                    }
                }

                for (int i = 0; i < 256; i++) {
                    if (local[i] != 0) {
                        global.addAndGet(i, local[i]);
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        int[] result = new int[256];
        for (int i = 0; i < 256; i++) {
            result[i] = global.get(i);
        }

        return result;
    }
}