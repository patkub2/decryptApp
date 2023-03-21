package pl.polsl.kubala.patryk.model;
/**
 * An exception is thrown if the command line input does not contain all the required fields
 * 
 * @author Patryk Kubala
 * @version 1.1
 */
public class IncorrectInputException 
        extends Exception
        {
 
    /**
     * Exception constructor with error message
     * 
     * @param errorMessage Message shown when the exception is caught
     */
    public IncorrectInputException(String errorMessage) 
            {
            super(errorMessage);
            }
}
