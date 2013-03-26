import controller.MainController;
import controller.ModelController;
import model.Model;


/**
 * @author Eric Wu
 * @author Natalia Carvalho
 */
public class Main {

    /**
     * main method
     * @param args default
     */
    public static void main(String[] args) {
        Model model = new Model();
        ModelController modelController = new ModelController(model);
        @SuppressWarnings("unused")
        MainController myController = new MainController(modelController);
    }
}
