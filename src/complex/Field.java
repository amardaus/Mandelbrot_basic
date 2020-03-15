package complex;

public interface Field<T>{
    T add(T t);
    T sub(T t);
    T mul(T t);
    T div(T t) throws DivideByZeroException;
    String toString();
}