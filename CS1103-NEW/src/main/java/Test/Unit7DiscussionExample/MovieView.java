package Test.Unit7DiscussionExample;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MovieView extends JFrame {

    private JButton getMoviesButton = new JButton("Get list of movies");

    public MovieView () {

        JPanel moviePanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);

        moviePanel.add(getMoviesButton);

        this.add(moviePanel);
    }

    void getMoviesListener(ActionListener listener) {

        getMoviesButton.addActionListener(listener);
    }
}
