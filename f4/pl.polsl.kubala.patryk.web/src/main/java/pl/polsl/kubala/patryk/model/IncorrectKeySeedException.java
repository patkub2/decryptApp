package pl.polsl.kubala.patryk.model;
/**
 * The exception that is thrown if the key seed does not meet the criteria for this operation
 *
 * @author Patryk Kubala
 * @version 1.1
 */
public class IncorrectKeySeedException
        extends Exception
{

    /**
     * Exception constructor with error message
     *
     * @param errorMessage Message shown when the exception is caught
     */
    public IncorrectKeySeedException(String errorMessage)
    {
        super(errorMessage);
    }
}
