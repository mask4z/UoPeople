package Unit7.DiscussionExample;

public class MovieList {

    public static void main(String[] args) {

        MovieView theView = new MovieView();
        MovieModel theModel = new MovieModel();
        MovieController theController = new MovieController(theView, theModel);

        theView.setVisible(true);
    }
}
