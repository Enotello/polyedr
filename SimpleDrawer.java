/**
 * @author Е.А. Роганов
 * Класс SimpleDrawer, обеспечивающий изображение проекции полиэдра.
 */
public class SimpleDrawer extends AwtDrawer{
    /**
     * Полиэдр.
     */
    protected Polyedr p;

    /**
     * Единичный вектор проектирования.
     */
    protected R3Vector pr;

    /**
     * Единичный x-вектор плоскости проектирования.
     */
    private R3Vector x;

    /**
     * Единичный y-вектор плоскости проектирования.
     */
    private R3Vector y;

    /**
     * Минимальная x-координата проекции полиэдра.
     */
    private double xmin;

    /**
     * Минимальная y-координата проекции пролиэдра.
     */
    private double ymin;

    /**
     * Размер проекции полиэдра.
     */
    private double size;

    /**
     * Вычислить x-координату проекции точки.
     * @param v Трехмерный вектор.
     * @return X-координата проекции этого вектора.
     */
    private double xProection(R3Vector v){
        return R3Vector.scalMul(v, x);
    }

    /**
     * Вычислить y-координату проекции точки.
     * @param v Трехмерный вектор.
     * @return Y-координата проекции этого вектора.
     */
    private double yProection(R3Vector v){
        return R3Vector.scalMul(v, y);
    }

    /**
     * Вычислить нормализованную x-координату проекции точки.
     * @param v Трехмерный вектор.
     * @return Нармализованная x-координата проекции этого вектора.
     */
    protected double xnProection(R3Vector v){
        return (xProection(v) - xmin)/size;
    }

    /**
     * Вычислить нормализованную y-координату проекции точки.
     * @param v Трехмерный вектор.
     * @return Нармализованная y-координата проекции этого вектора.
     */
    protected double ynProection(R3Vector v){
        return (yProection(v) - ymin)/size;
    }

    /**
     * Конструктор класса.
     * @param p Полиэдр.
     * @param pr Вектор проектирования.
     * @param angle Угол поворота в плоскости проекции.
     */
    public SimpleDrawer(Polyedr p, R3Vector pr, double angle){
        this.p = p;
        this.pr = pr.normalize();
        double a = pr.getX();
        double b = pr.getY();
        double c = pr.getZ();

        if (a != 0. || b != 0.){
            x = new R3Vector(-b, a, 0.);
        }else {
            x = new R3Vector(0., c, -b);
        }

        y = R3Vector.vectMul(x, pr);
        x.normalize();
        y.normalize();

        R3Vector nx = R3Vector.plus(R3Vector.mul(Math.cos(angle), x), R3Vector.mul(-Math.sin(angle), y));
        R3Vector ny = R3Vector.plus(R3Vector.mul(Math.sin(angle), x), R3Vector.mul(Math.cos(angle), y));

        x = nx;
        y = ny;
        xmin = ymin = Double.MAX_VALUE;

        double xmax, ymax;
        xmax = ymax = Double.MIN_VALUE;

        for(int i = 0; i < p.getVertexesQuantity(); i++){
            double xi = xProection(p.getVertex(i));
            double yi = yProection(p.getVertex(i));

            if(xi < xmin) xmin = xi;
            if(yi < ymin) ymin = yi;
            if(xi > xmax) xmax = xi;
            if(yi > ymax) ymax = yi;
        }

        size = ymax - ymin;
        if(xmax - xmin > size) size = xmax- xmin;
    }

    /**
     * Изобразить проекцию полиэдра.
     */
    public final void draw(){
        for(int i = 0; i < p.getEdgesQuantity(); i++)
            drawEdge(p.getEdge(i));

            System.out.print("\n");
    }

    /**
     * Изобразить ребро полиэдра.
     * @param s Обрабатываемое ребро полиэдра.
     */
    public void drawEdge(Edge s){
        Vertex begin = s.getBegin();
        Vertex end = s.getEnd();

        draw(xnProection(begin), ynProection(begin), xnProection(end), ynProection(end));

        System.out.print(".");
    }
}
