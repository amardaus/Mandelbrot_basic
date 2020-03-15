package complex;

public class DivideByZeroException extends Exception{
    String msg;

    DivideByZeroException(){
        this.msg = "Nie mozna dzielic przez 0!";
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}