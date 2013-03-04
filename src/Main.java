import model.Model;
import controller.Controller;


public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        Controller myController = new Controller(model);
    }
}
