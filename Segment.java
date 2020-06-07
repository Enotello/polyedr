/**
 * @author Е.А. Роганов
 * Класс Segment, реализующий одномерный отрезок.
 */

public class Segment{
    /**
     * Координаты начала и конца отрезка.
     */
    private double begin, end;

    /**
     * Конструктор отрезка.
     * @param begin Начало отрезка.
     * @param end  Конец отрезка.
     */
    public Segment(double begin, double end){
        this.begin = begin;
        this.end = end;
    }

    /**
     * Вырожден ли отрезок?
     * @return Вырожден ли отрезок?
     */
    public final boolean degenerate(){
        return begin >= end;
    }

    /**
     * Найти левый отрезок разности с отрезком s.
     * @param s Вычитаемый отрезок.
     * @return Левый отрезок разности.
     */
    public final Segment leftSub(Segment s){
        return new Segment(begin, Math.min(end, s.begin));
    }

    /**
     * Найти правфй отрезок разности с отрезком s.
     * @param s Вычитаемый отрезок.
     * @return Правый отрезок разности.
     */
    public final Segment rightSub(Segment s) {
        return new Segment(Math.max(begin, s.end), end);
    }

    /**
     * Найти пересечение с отрезком s.
     * @param s  Отрезок, с которым находится пересечение.
     * @return Отрезок-пересечение.
     */
    public final Segment intersection(Segment s){
        begin = Math.max(begin, s.begin);
        end = Math.min(end, s.end);
        return this;
    }

    /**
     * Получить начало отрезка.
     * Начало отрезка.
     */
    public final double getBegin(){
        return begin;
    }

    /**
     * Получить конец отрезка.
     * Конец отрезка.
     */
    public final double getEnd(){
        return end;
    }
}