package com.goit.jdbc.config;

import java.util.List;

public abstract class DataAccessObject<T extends DataTransferObject> {
    public abstract void create(T object);
    public abstract void update(T object);
    public abstract T getById(int id);
    public abstract List<T> getAll();
    public abstract void remove(int id);
}
