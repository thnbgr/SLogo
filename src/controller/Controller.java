package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import command.CommandPerformer;
import command.CommandPreParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parser.EncodeTree;
import parser.EncodeParser;
import parser.SyntaxCheck;
import parser.node.Node;
import model.Model;
import view.DisplayView;
import view.IView;
import view.InputView;


public class Controller implements Observer {

    private static final Dimension myDisplayViewSize = new Dimension(500, 500);
    private static final Dimension myInputViewSize = new Dimension(500, 600);
    private DisplayView myDisplayView;
    private IView myInputView;
    private List<IView> myViewList;
    private Model myModel;
    private CommandPreParser myCommandPreParser;
    private CommandPerformer myCommandPerformer;

    public static final String TITLE = "Output Display";

    public Controller (Model model) {
        myModel = model;
        myDisplayView = new DisplayView(myDisplayViewSize);
        myCommandPreParser = new CommandPreParser(myDisplayView);
        myCommandPreParser.addObserver(this);

        myCommandPerformer = new CommandPerformer(myDisplayView);

        createOutputJFrame();

        myInputView =
                new InputView("Command Inputs", "English", myCommandPreParser, myInputViewSize);

    }

    private void createOutputJFrame () {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components
        frame.getContentPane().add(myDisplayView, BorderLayout.CENTER);
        // display them
        frame.pack();
        frame.setVisible(true);
        myDisplayView.addTurtle();
        myDisplayView.start();
    }

    public void addView (IView view) {
        myViewList.add(view);
    }

    public void update (Observable o, Object a) {
        CommandPreParser myPreParser = (CommandPreParser) a;
        String myCommand = myPreParser.getParsedString();

        // This is where we send it to the model parser
        // We assume for now that is has been parsed

        myCommandPerformer.sendAction(myCommand);
        // myDisplayView.step();
    }

}
