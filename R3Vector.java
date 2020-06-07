import java.util.Scanner;

/**
 * @author Е.А. Роганов
 * Класс R3Vector, реализующий вектор (Vector) в пространстве (R3).
 */
public class R3Vector{
    /**
     * Координаты вектора.
     */
    private double x, y, z;

    /**
     * Конструктор вектора, заданного его координатами.
     *      * @param x X-координата вектора.
     *      * @param y Y-координата вектора.
     * @param z Z-координата вектора.
     */
    public R3Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Конструктор вектора, координаты которого вводятся с клавиатуры.
     * @exception Exception Исключительная ситуация, возникающая
     * при ошибках ввода координат с клавиатуры.
     */
    public R3Vector() throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.print("x-координата вектора проектирования -> ");
        x = in.nextInt();
        System.out.print("\n");

        System.out.print("y-координата вектора проектирования -> ");
        y = in.nextInt();
        System.out.print("\n");

        System.out.print("z-координата вектора проектирования -> ");
        z = in.nextInt();
        System.out.print("\n");
    }

    /**
     * Получить x-координату вектора.
     * @return X-координата вектора.
     */
    public final double getX(){
        return x;
    }

    /**
     * Получить y-координату вектора.
     * @return Y-координата вектора.
     */
    public final double getY(){
        return y;
    }

    /**
     * Получить z-координату вектора.
     * @return Z-координата вектора.
     */
    public final double getZ(){
        return z;
    }

    /**
     * Нормировать ненулевой вектор.
     */
     public final R3Vector normalize(){
        double norm = Math.sqrt(x*x+y*y+z*z);
        x /= norm;
        y /= norm;
        z /= norm;
        return this;
     }

     /**
      * Найти сумму двух векторов.
      * @param a Первый вектор-слагаемое.
      * @param b Второй вектор-слагаемое.
      * @return Сумма векторов.
      */
     public static R3Vector plus(R3Vector a, R3Vector b){
         return new R3Vector(a.x + b.x, a.y + b.y, a.z + b.z);
     }

    /**
     * Добавить заданный вектор.
     * @param b Добавляемый вектор.
     * @return Вектор-результат.
     */
    public final R3Vector plus(R3Vector b){
        x += b.x;
        y += b.y;
        z += b.z;
        return this;
    }

    /**
     * Найти разность двух векторов.
     * @param a Вектор-уменьшаемое.
     * @param b Вектор-вычитаемое.
     * @return Разность векторов.
     */
    public static R3Vector minus(R3Vector a, R3Vector b){
        return new R3Vector(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    /**
     * Вычесть заданный вектор.
     * @param b Вычитаемый вектор.
     * @return Вектор результат.
     */
    public final R3Vector minus(R3Vector b){
        x -= b.x;
        y -= b.y;
        z -= b.z;
        return this;
    }

    /**
     * Найти произведение вектора на число.
     * @param k Число, на которое умножается вектор.
     * @param a Исходный вектор.
     * @return Вектор результат.
     */
    public static R3Vector mul(double k, R3Vector a){
        return new R3Vector(k*a.x, k*a.y, k*a.z);
    }

    /**
     * Умножить вектор на заданное число.
     * @param k Число, на которое уменожается вектор.
     * @return Вектор-результат.
     */
    public final R3Vector mul(double k){
        x *= k;
        y *= k;
        z *= k;
        return this;
    }

    /**
     * Найти скалярное произвдение векторов.
     * @param a Первый вектор.
     * @param b Второй вектор.
     * @return Скалярное произведение векторов.
     */
    public static double scalMul(R3Vector a, R3Vector b){
        return a.x*b.x + a.y*b.y + a.z*b.z;
    }

    /**
     * Найти векторное произведение векторов.
     * @param a Первый вектор.
     * @param b Второй вектор.
     * @return Векторное произведение векторов.
     */

     public static R3Vector vectMul(R3Vector a, R3Vector b){
         return new R3Vector(a.y*b.z - a.z*b.y, a.z*b.x - a.x*b.z, a.x*b.y - a.y*b.x);
     }
}