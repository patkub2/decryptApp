package pl.polsl.kubala.patryk.model;
/**
 * The exception that is thrown if the text to be encoded or decoded does not meet the criteria for this operation
 * 
 * @author Patryk Kubala
 * @version 1.1
 */
public class IncorrectTextException 
    extends Exception
        {
    
    /**
     * Exception constructor with error message
     * 
     * @param errorMessage Message shown when the exception is caught
     */
    public IncorrectTextException(String errorMessage) 
            {
            super(errorMessage);
            }
}
