
package modelo;

import java.util.List;
public interface CRUD<T> {
    public T getByID(T obj);
    public List<T> getAll();
    public boolean deleteByID(T obj);
    public boolean updateByID(T obj);
    public boolean insert(T obj);
    public boolean insert(List<T> obj);
}
