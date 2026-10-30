
package org.example.task2;

import java.util.Objects;

public class CustomArrayList<T> implements CustomList<T> {
    private Object[] mass = new Object[10];
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("CustomArrayList - ");
        for (int i = 0; i < size; i++) {
            str.append(mass[i]);

            if (i < size - 1) {
                str.append(", ");
            }
        }

        return str.toString();
    }

    @Override
    public void customAdd(T value) {
        if (size == mass.length) {
            massIncrease();
        }
        mass[size] = value;
        size++;
    }

    @Override
    public void customAdd(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == mass.length) {
            massIncrease();
        }
        for (int i = size; i > index; i--) {
            mass[i] = mass[i - 1];
        }

        mass[index] = value;
        size++;
    }

    @Override
    public int customSize() {
        return size;
    }

    @Override
    public void customClear() {
        mass = new Object[10];
        size = 0;
    }

    @Override
    public boolean customRemoveAt(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            mass[i] = mass[i + 1];
        }
        size--;
        mass[size] = null;

        return true;
    }

    @Override
    public boolean customRemove(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(mass[i], value)) {
                return customRemoveAt(i);
            }
        }

        return false;
    }

    @Override
    public T customGet(int index) {
        checkIndex(index);
        return (T) mass[index];
    }

    @Override
    public void customSet(int index, T value) {
        checkIndex(index);
        mass[index] = value;
    }

    private void massIncrease() {
        Object[] newMass = new Object[mass.length * 2];
        for (int i = 0; i < mass.length; i++) {
            newMass[i] = mass[i];
        }

        mass = newMass;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
