package org.example.task2;

public interface CustomList<T>{
    int customSize();
    void customClear();

    void customAdd(T value);
    void customAdd(int index, T value);

    T customGet(int index);
    void customSet(int index, T value);

    boolean customRemove(T value);
    boolean customRemoveAt(int index);

}
