import java.util.*;
import java.io.*;

/**
 * @author Е.А. Роганов
 */
public class PolyedrTest{
    /**
     * Функция main.
     * @param args Массив аргументов командной строки.
     * @exception Exception Исключительная ситуация, возникающая
     * при ошибках чтения или преобразования данных из файла.
     */
    public static void main(String[] args) throws Exception{
        Polyedr p = new Polyedr("G:\\Polyedr\\src\\data\\cow.geom");

        System.out.print("Возможные типы изображения полиэдра;\n" +
                "0) без удаления невидимых линий - 0,\n" +
                "1) с удалением невидимых ребер - 1,\n" +
                "2) с использованием хеширования - 2.\n"+
                "Выберите тип изображения (от 0 до 2) -> ");

        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        System.out.print("\n");
        while(true){
            R3Vector pr = new R3Vector();
            System.out.print("Угол поворота в плоскости проекции (в градусах) -> ");
            double angle = in.nextDouble();
            System.out.print("\n");
            System.out.println("Количество ребёр, удаленных от начала координат менее чем на единицу: " + Edge.getCounter());
            switch(type){
                case 0:
                    SimpleDrawer d = new SimpleDrawer(p, pr, Math.PI*angle/180.0);
                    d.draw();
                    break;
                case 1:
                    ShadowDrawer sd = new ShadowDrawer(p, pr, Math.PI*angle/180.0);
                    sd.draw();
                    break;
                case 2:
                    SmartDrawer smd = new SmartDrawer(p, pr, Math.PI*angle/180.0);
                    smd.draw();
                    break;
                default:
                    System.out.println("Неверный тип изображения.");
            }

            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        }
    }
}
