package wafer;

import avro.shaded.com.google.common.collect.Maps;
import com.allinabc.aiyei.wafer.layout.WaferLayoutUtil;
import com.allinabc.aiyei.wafer.layout.bo.PixelCoord;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @Author gaoxing
 * @Date 2020-06-24 09:00
 */
public class Demo {

    public static void main(String[] args) {

        // wafer实际物理参数，单位um
        // wafer size
        double ws = 300000;
        // die size
        double dw = 30000;
        double dh = 24000;
        // 0,0 die位置偏移量
        double clx = 20000;
        double cly = -10000;

        // 前后端交互参数
        // canvas size
        int cs = 400;
        // 放大倍数
        double m = 1;
        // 放大中心位置
        int cpx = 200;
        int cpy = 200;

        // <die index, 画布上坐标位置>
        Map<PixelCoord, PixelCoord> dieLayout = WaferLayoutUtil.buildDieLayout(ws, dw, dh, clx, cly, cs, m, cpx, cpy);

        // <die index, bin code>
        Map<PixelCoord, Integer> cp = Maps.newHashMap();
        cp.put(new PixelCoord(0, 0), 0);
        cp.put(new PixelCoord(0, 4), 2);
        cp.put(new PixelCoord(1, -2), 2);
        cp.put(new PixelCoord(-1, 3), 7);
        cp.put(new PixelCoord(2, -0), 0);

        // 画布上die的尺寸
        int w = WaferLayoutUtil.lineStretch(dw, ws, cs, m);
        int h = WaferLayoutUtil.lineStretch(dh, ws, cs, m);

        // 画布上wafer圆心位置
        int wx = WaferLayoutUtil.pointOffset(cpx, m);
        int wy = WaferLayoutUtil.pointOffset(cpy, m);

        // 画布上wafer尺寸
        int waferRadius = (int) (cs * m);

        draw(dieLayout, cp, w, h, wx, wy, waferRadius);
    }

    public static void draw(Map<PixelCoord, PixelCoord> dieLayout, Map<PixelCoord, Integer> cp, int dw, int dh, int wx, int wy, int radius) {

        int canvasLength = 400;

        int l = 1000;
        int offset = (l-canvasLength) / 2;

        BufferedImage image = new BufferedImage(l, l, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, l, l);

        // 画布
        graphics.setPaint(Color.red);
        graphics.drawLine(offset, offset, l-offset, offset);
        graphics.drawLine(offset, offset, offset, l-offset);
        graphics.drawLine(l-offset, offset, l-offset, l-offset);
        graphics.drawLine(offset, l-offset, l-offset, l-offset);

        // wafer
        graphics.setPaint(Color.black);
        graphics.drawOval(wx + offset, wy + offset, radius, radius);

        // die
        dieLayout.forEach((p, v) -> {
            if (cp != null && cp.containsKey(p)) {
                graphics.setPaint(Color.getColor(cp.get(p).toString()));
                graphics.fillRect(v.getX() + offset, v.getY() + offset, dw, dh);
            } else {
                graphics.setPaint(Color.CYAN);
                graphics.drawRect(v.getX() + offset, v.getY() + offset, dw, dh);
            }
//            graphics.fillRect(v.getX() + offset, v.getY() + offset, dw, dh);
            graphics.drawString(p.toString(), v.getX() + offset, v.getY() + offset + dh/2);
        });

        // wafer圆心坐标系
        graphics.setPaint(Color.red);
        graphics.drawLine(l/2, 0, l/2, l);
        graphics.drawLine(0, l/2, l, l/2);

        graphics.dispose();
        String path = String.format("/Users/gaoxing/Downloads/%s.jpg", UUID.randomUUID());
        File file = new File(path);
        try {
            file.createNewFile();
            ImageIO.write(image, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
