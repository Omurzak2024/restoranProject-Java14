package peaksoft.restoranprojectjava14.exceptions;

public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(String message){
        super(message);
    }

    public NoSuchElementException(Class<?> clazz, Long id) {
        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id));
    }

    public NoSuchElementException(){

    }
}
