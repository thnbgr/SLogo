import controller.Controller;
import model.Model;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        @SuppressWarnings("unused")
        Controller myController = new Controller(model);
    }
}
