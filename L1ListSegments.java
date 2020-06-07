/**
 * @author Е.А. Роганов
 * Класс L1ListSegments, реализующий односвязный список отрезков.
 */

public class L1ListSegments{
    /**
     * Массив отрезков.
     */
    private Segment[] array;

    /**
     * Массив ссылок.
     */
    private int[] next;

    /**
     * Нил списка.
     */
    private int nilList;

    /**
     * Нил свободного места.
     */
    private int nilFree;

    /**
     * Элемент до указателя.
     */
    private int before;

    /**
     * Элемент за указателем.
     */
    private int after;

    /**
     * Связать два элемента.
     * @param first Первый элемент.
     * @param second Второй элемент.
     */
    private void link(int first, int second){
        next[first] = second;
    }

    /**
     * Захватить место.
     * @return Индекс занимаемого места.
     */
    private int mallocIndex(){
        int index = next[nilFree];
        link(nilFree, next[index]);
        return index;
    }

    /**
     * Освободить место.
     * @param index Индекс освобождаемого места.
     */
    private void freeIndex(int index){
        link(index, next[nilFree]);
        link(nilFree, index);
    }

    /**
     * Конструктор класса.
     * @param size Максимальный размер списка.
     */
    public L1ListSegments(int size){
        array = new Segment[size];
        next = new int[size+ 2];

        nilList = size;
        nilFree = size+ 1;

        link(nilList, nilList);
        link(nilFree, 0);

        for(int i = 0; i < size - 1; i++)
            link(i, i + 1);

        link(size - 1, nilFree);

        before = after = nilList;
    }

    /**
     * Пуст ли список?
     * @return Пуст ли список?
     */
    public final boolean empty(){
        return next[nilList] == nilList;
    }

    /**
     * Сделать список пустым.
     */
    public final void clear(){
        try{
            toFront();
            while(true)
                erase();
        }catch(Exception e){
            ;
        }
    }

    /**
     * Передвинуть указатель в начало списка.
     */
    public final void toFront(){
        before = nilList;
        after = next[nilList];
    }

    /**
     * Указатель в конце списка?
     * @return Указатель в конце списка?
     */
    public final boolean end(){
        return after == nilList;
    }

    /**
     * Передвинуть указатель вперед.
     * @exception Exception Исключительная ситуация, возникающая при
     * попытке передвинуть вперед указатель, находящийся в конце списка.
     */
    public final void forward() throws Exception{
        if(after == nilList) throw new Exception();

        before = after;after = next[after];
    }

    /**
     * Получить элемент за указателем.
     * @return Элемент за указателем.
     * @exception Exception Исключительная ситуация, возникающая при
     * попытке получить элемент за указателем, находящимся в конце списка.
     */
    public final Segment after() throws Exception{
        return array[after];
    }

    /**
     * Добавить элекмент за указателем.
     * @param val Включаемый отрезок (сегмент).
     * @exception Exception Исключетельная ситуация, возникающая при
     * попытке добавить элемент в заполненный до конца список.
     */
    public final void insert(Segment val) throws Exception{
        int index = mallocIndex();

        link(before, index);
        link(index, after);
        after = index;

        array[index] = val;
    }

    /**
     * Удалить элемент за указателем.
     * @return Удаляемый элемент.
     * @exception Exception Исключительная ситуация, возникающая при
     * попытке удалить элемент из пустого списка.
     */
    public final Segment erase() throws Exception{
        Segment val = array[after];

        int index = after;
        after = next[index];
        link(before, after);

        freeIndex(index);

        return val;
    }
}