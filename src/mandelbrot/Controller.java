package mandelbrot;

import complex.Complex;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Controller {
    int R = 4;
    private GraphicsContext gc;
    public Canvas canvas;
    int iterMax = 100;
    private double aX, bX, aY, bY;

    

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clear(gc);

        aX = -1;
        bX = 1;
        aY = 1;
        bY = -1;
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    int mandelbrot(Complex p) {
        Complex z = (Complex) p.clone();

        for (int i = 0; i < iterMax; i++) {
            z = (z.mul(z)).add(p);
            if (z.sqrAbs() > R) return i;
        }

        return iterMax;
    }

    public int getIntFromColor(int red, int green, int blue) {
        red = (red << 16) & 0x00FF0000;
        green = (green << 8) & 0x0000FF00;
        blue = blue & 0x000000FF;
        return 0xFF000000 | red | green | blue;
    }

    public void draw(ActionEvent actionEvent) {
        final int height = 512;
        final int width = 512;

        double ix = (bX - aX) / width;
        double iy = (bY - aY) / height;

        WritableImage wr = new WritableImage(width, height);
        PixelWriter pw = wr.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double re = aX + x * ix;
                double im = aY + y * iy;

                Complex c = new Complex(re, im);

                int iterCount = mandelbrot(c);
                if (iterCount == iterMax) {
                    pw.setPixels(x, y, 1, 1, PixelFormat.getIntArgbInstance(), new int[]{0xFF000000}, 0, 1);
                } else {
                    pw.setPixels(x, y, 1, 1, PixelFormat.getIntArgbInstance(), new int[]{getIntFromColor(0, 255 * iterCount / 100, 0)}, 0, 1);
                }
            }
        }
        pw.setArgb(0, 0, 0xFFFF0000);
        gc.drawImage(wr, 0, 0, width, height);
    }
}