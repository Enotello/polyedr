import javax.swing.*;
import java.awt.*;

/**
 * @author Е.А. Роганов
 * Класс AwtDrawer, обеспечивающий визуализацию
 * плоского изображения.
 */
public class AwtDrawer extends JFrame{
    /**
     * Ширина области рисования.
     */
    private static final int XLEN = 500;

    /**
     * Высота области рисования.
     */
    private static final int YLEN = 500;

    /**
     * Ширина "полей" вокруг области рисования.
     */
    private static final int DELTA = 100;

    /**
     * Внеэкранный буфер.
     */
    private Image offScrImage;

    /**
     * Графический контекст внеэкранного буфера.
     */
    private Graphics offScrGC;

    /**
     * Графический контекст фрейма на экране.
     */
    private Graphics g;

    /**
     * Конструктор класса.
     */
    public AwtDrawer(){
        super("Построение изображения полиэдра");
        setSize(XLEN + 2*DELTA, YLEN + 2*DELTA);
        setBackground(Color.white);

        g = getGraphics();
        //show();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        offScrImage = createImage(XLEN + 2*DELTA, YLEN + 2*DELTA);
        offScrGC = offScrImage.getGraphics();
        offScrGC.setColor(Color.white);
        offScrGC.fillRect(0, 0, XLEN + 2*DELTA, YLEN + 2*DELTA);
        offScrGC.setColor(Color.black);
    }

    /**
     * Изобразить отрезок на плоскости, заданный его концами.
     * @param xb X-координата начала отрезка (0 <= xb <= 1).
     * @param yb Y-координата начала отрезка (0 <= yb <= 1).
     * @param xe X-координата конца отрезка (0 <= xe <= 1).
     * @param ye Y-координата конца отрезка (0 <= ye <= 1).
     */
    public final void draw(double xb, double yb, double xe,double ye){
        int x0 = DELTA + (int)(XLEN*xb);
        int y0 = DELTA + (int)(YLEN*yb);
        int x1 = DELTA + (int)(XLEN*xe);
        int y1 = DELTA + (int)(YLEN*ye);

        offScrGC.drawLine(x0, y0, x1, y1);
    }

    /**
     * Переизобразить содержимое фрейма.
     */
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g2d); //!!!
        g2d.drawImage(offScrImage, 0, 0, this);
    }
}

