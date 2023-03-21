package pl.polsl.kubala.patryk.controller;

import pl.polsl.kubala.patryk.view.*;
import pl.polsl.kubala.patryk.model.*;

/**
 * Class responsible for exact calling of
 * {@link pl.polsl.kubala.patryk.model.Model} methods
 *
 * @author Patryk Kubala
 * @version 2.1
 */
public class ControllerWindow {


    /**
     * A {@link pl.polsl.kubala.patryk.model.Model} object used to for calling
     * methods
     */
    private Model model;
    /**
     * A {@link pl.polsl.kubala.patryk.model.Window} object used to for calling
     * methods
     */
    private Window window;

    /**
     * {@link Controller} It takes command line arguments and passes it to
     * controller method. {@link pl.polsl.kubala.patryk.model.Window}
     * Initializes the objects present inside of this class.
     * {@link pl.polsl.kubala.patryk.model.Model} It also initializes the
     * objects present inside of this class.
     *
     * @param args initial comand line parameters passed from main function
     */
    ControllerWindow(String[] args) {
        this.model = new Model();
        this.window = new Window();
        this.controller(args);
    }

    /**
     * Function controller operates the whole program, calling methods from
     * {@link pl.polsl.kubala.patryk.model.Model}
     *
     * @param args command line arguments passed from main through the
     * {@link Controller} initializer
     */
    private void controller(String[] args) {
        window.setVisible(true);
        try {
            model.takeFromCommandLine(args);
        } catch (IncorrectInputException e) {
            window.printErrorMsg(e);
            model.setFromCL(false);
        }

        if (model.getFromCL()) {
            window.setVieldsFromCmd(model.getSeed(), model.getText());
            window.setWaiterTrue();
        }
        while (true) {
            
           
                if (!model.getFromCL()) {
                    try {
                        model.setSeed(window.getKeySeed());
                    } catch (NumberFormatException e) {
                        window.printErrorMsg(e);
                    }
                    model.setChoice(window.getChoice());
                }
               
            //System.out.println("window.getWaiter()"+window.getWaiter()+"\n");
            //////////////////////////////////////////////
            if (window.getWaiter()== true) {
                System.out.println("if(window.getWaiter()) TRUE\n");
                window.setWaiterFalse();
                model.setKey();
                model.setchoiceEnum(model.getChoice());
                switch (model.getchoiceEnum()) {
                    // DECODE ------------------------
                    case DECODE -> {
                        if (!model.getFromCL()) {
                            model.setText(window.getText());
                        }

                        try {
                            window.printToTextOutput(model.decodeText());
                            model.setText(model.decodeText());
                        } catch (IncorrectTextException e) {
                            window.cleanFields();
                            window.printErrorMsg(e);
                            model.setFromCL(false);
                            model.setNoError(false);
                        }

                    }
                    // ENCODE ------------------------
                    case ENCODE -> {
                        System.out.println("ENCODE\n");
                        if (!model.getFromCL()) {
                            model.setText(window.getText());
                        }
                        try {
                            window.printToTextOutput(model.encodeText());
                        } catch (IncorrectTextException e) {
                            window.cleanFields();
                            window.printErrorMsg(e);
                            model.setFromCL(false);
                            model.setNoError(false);
                        }
                    }
                }

                model.destroyKey();
                System.out.println("model.setFromCL(false);\n");
                model.setFromCL(false);
                System.out.println("END OF LOOP\n");
            }
        }
    }
}
