public class NegativeLifespanException extends Exception{
    public NegativeLifespanException (Person person){
        super(person.negativeLifespanExceptionMessege());
    }
}