package interfaces;


import java.util.List;


public interface DatabaseDao<T> {
    void save (T entity);
    void update(T entity);
    void delete(int id);
     T findByYd(int id);
     T findByString(String string);
    <T> List<T> getList();
}
