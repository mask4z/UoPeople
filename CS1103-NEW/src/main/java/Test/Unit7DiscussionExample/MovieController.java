package Test.Unit7DiscussionExample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieController {

    private MovieModel model;
    private MovieView theView;

    public MovieController (MovieView theView, MovieModel theModel) {

        this.model = theModel;
        this.theView = theView;

        this.theView.getMoviesListener(new MovieListener());

    }

    class MovieListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            model.getMovies();
        }
    }
}
