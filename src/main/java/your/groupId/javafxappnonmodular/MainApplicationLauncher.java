package your.groupId.javafxappnonmodular;

/**
 * This external launcher class is needed for starting JavaFx, because Java does not like it, if your main class extends from another class.
 */
public class MainApplicationLauncher {

    public static void main(String[] args) {
        MainApplication.main(args);
    }

}
