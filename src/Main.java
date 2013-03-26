import controller.ModelController;
import controller.MainController;
import model.Model;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        ModelController modelController = new ModelController(model);
        @SuppressWarnings("unused")
        MainController myController = new MainController(modelController);
    }
}
