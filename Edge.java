
/**
 * @author Е.А. Роганов
 * Класс Edge, реализующий ребро полиэдра.
 */
public class Edge{
    Vertex zero = new Vertex(0, 0, 0);
    /**
     * Начало ребра.
     */
    private Vertex begin;

    /**
     * Конец ребра.
     */
    private Vertex end;
    /**
     * Единичные вершина для сравнения
     */
    public static int counter = 0;
    /**
     * Конструктор.
     * @param begin Начало ребра.
     * @param end Конец ребра.
     */
    public Edge(Vertex begin, Vertex end){
        if(checkForOne(begin, end)){
            counter++;
        }
        this.begin = begin;
        this.end = end;
    }

    /**
     * Получить начало ребра.
     * @return Начало ребра.
     */
    public final Vertex getBegin(){
        return begin;
    }

    /**
     * Получить конец ребра.
     * @return Конец ребра.
     */
    public final Vertex getEnd(){
        return end;
    }

    /**
     * Проверить на удалённость от начала на единицу.
     */
    public boolean checkForOne(Vertex a, Vertex b){
        if(distance(a, b) < 1){
            return true;
        }
        return false;
    }
    //Данные методы были выведены путём перевода формулы Герона для вычисления высоты треугольника
    private double distance(Vertex a, Vertex b){
        if(cosinus_theory(a, b) > 90  || cosinus_theory(b, a) > 90 ){
            if (length(a, zero) > length(b, zero)){
                return length(b, zero);
            }
            else{
                return length(a, zero);
            }
        }
        else {
            double p = (length(a, b) + length(a, zero) + length(b, zero)) / 2;
            return 2 * Math.sqrt(p * (p - length(a, b)) * (p - length(a, zero)) * (p - length(b, zero))) / length(a, b);
        }
    }
    private double length(Vertex a1, Vertex a2){
        //расчёт длины отрезка
        return Math.sqrt(Math.pow(a2.getX()-a1.getX(),2)+Math.pow(a2.getY()-a1.getY(),2)+Math.pow(a2.getZ()-a1.getZ(),2));
    }
    private double cosinus_theory(Vertex a, Vertex b){
        //расчёт угла
        return Math.acos((Math.pow(length(a, zero), 2) + Math.pow(length(a, b), 2) - Math.pow(length(b, zero), 2)) / 2*length(a, b)*length(a, zero));
    }
    /**
     * Получить значение счётчика.
     */
    public static int getCounter(){
        return counter;
    }
}