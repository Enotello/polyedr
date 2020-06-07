import java.util.*;
import java.io.*;

/**
 * @author Е.А. Роганов
 * Класс Polyedr, реализующий полиэдр.
 */

public class Polyedr{
    /**
     * Массив вершин полиэдра.
     */
    private Vertex[] vertexes;

    /**
     * Массив ребер полиэдра.
     */
    private Edge[] edges;

    /**
     * Массив граней полиэдра.
     */
    private Facet[] facets;

    /**
     * Конструктор класса.
     * @param file Файл, содержащий описание полиэдра.
     * @exception Exception Исключительная ситуация, возникающая при
     * ошибках чтения или преобразования данных.
     */
    public Polyedr(String file) throws Exception{
        RandomAccessFile f = new RandomAccessFile(file, "r");
        StringTokenizer st = new StringTokenizer(f.readLine());

        vertexes = new Vertex[Integer.parseInt(st.nextToken())];
        facets = new Facet[Integer.parseInt(st.nextToken())];
        edges = new Edge[Integer.parseInt(st.nextToken())];

        for(int i = 0; i < vertexes.length; i++){
            st = new StringTokenizer(f.readLine());

            double x = Double.valueOf(st.nextToken()).doubleValue();
            double y = Double.valueOf(st.nextToken()).doubleValue();
            double z = Double.valueOf(st.nextToken()).doubleValue();

            vertexes[i]= new Vertex(x, y, z);
        }

        int k = 0;
        for(int i = 0; i < facets.length; i++){
            st = new StringTokenizer(f.readLine());
            int size = Integer.parseInt(st.nextToken());

            Vertex[] facet= new Vertex[size];
            facet[0] = vertexes[Integer.parseInt(st.nextToken())- 1];

            for(int j = 1; j < size; j += 1){
                facet[j] = vertexes[Integer.parseInt(st.nextToken()) - 1];
                edges[k++] = new Edge(facet[j], facet[j-1]);
            }

            edges[k++] = new Edge(facet[size-1], facet[0]);
            facets[i] = new Facet(facet);
        }
    }

    /**
     * Получить количество вершин.
     * @return Количесвто вершин полиэдра.
     */
    public final int getVertexesQuantity(){
        return vertexes.length;
    }

    /**
     * Получить вершину.
     * @param i Номер вершины.
     * @return Вершина полиэдра.
     */
    public final Vertex getVertex(int i){
        return vertexes[i];
    }

    /**
     * Получить количество ребер.
     * @return Количество ребер полиэдра.
     */
    public final int getEdgesQuantity(){
        return edges.length;
    }

    /**
     * Получить ребро.
     * @param i  Номер ребра.
     * @return Ребро полиэдра.
     */
    public final Edge getEdge(int i){
        return edges[i];
    }

    /**
     * Получить количество граней.
     * @return Количесивр граней полиэдра.
     */
    public final int getFacetsQuantity(){
        return facets.length;
    }

    /**
     * Получить грань.
     * @param i  Номер грани.
     * @return Грань полиэдра.
     */
    public final Facet getFacet(int i){
        return facets[i];
    }
}
