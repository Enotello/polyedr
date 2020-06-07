/**
 * @author Е.А. Роганов
 * Класс Facet, реализующий грань полиэдра.
 */
public class Facet{
    /**
     * Массив вершин полиэдра, принадлежащих грани.
     */
    private Vertex[] vertexes;

    /**
     * Центр грани.
     */
    private R3Vector center;

    /**
     * Вектор нормали к грани.
     */
    private R3Vector normal;

    /**
     * Конструктор.
     * @param vertexes Вершины полиэдра, образующие грань.
     */
    public Facet(Vertex[] vertexes){
        this.vertexes = vertexes;

        normal = R3Vector.vectMul(R3Vector.minus(vertexes[1], vertexes[0]), R3Vector.minus(vertexes[2], vertexes[0]));
        center = new R3Vector(0.,0.,0.);

        for(int i = 0; i < vertexes.length; i++){
            center.plus(vertexes[i]);
        }

        center.mul(1./(double)vertexes.length);
    }

    /**
     * Получить количество вершин.
     * @return Количество вершин грани.
     */
    public final int getVertexesQuantity(){
        return vertexes.length;
    }

    /**
     * Получить вершину.
     * @param i Номер вершины.
     * @return Вершина грани.
     */
    public final Vertex getVertex(int i){
        return vertexes[i];
    }

    /**
     * Получить центр грани.
     * @return Центр грани.
     */
    public final R3Vector getCenter(){
        return center;
    }

    /**
     * Получить нормаль к грани.
     * @return Нормаль грани.
     */
    public final R3Vector getNormal(){
        return normal;
    }

    /**
     * Является ли грань "вертикальной"?
     * @param pr Вектор проектирования.
     * @return Параллельна ли грань векторну проектирования?
     */
    public final boolean vertical(R3Vector pr){
        return R3Vector.scalMul(normal, pr)== 0.;
    }
}