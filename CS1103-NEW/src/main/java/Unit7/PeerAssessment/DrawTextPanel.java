package Unit7.PeerAssessment;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.RenderingHints;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.PrintWriter;

import java.util.ArrayList;

import java.util.Scanner;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;

import javax.swing.JColorChooser;

import javax.swing.JLabel;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.KeyStroke;

/**

 * Added Features:

 * 1. right click to remove item

 * 2. remove many times

 * 3. left click puts text with random background features

 * 4. save and open command supports new features

 */

public class DrawTextPanel extends JPanel {

    private ArrayList<DrawTextItem> mStrings;

    private Color currentTextColor = Color.BLACK;

    private Canvas canvas;

    private JTextField input;

    private SimpleFileChooser fileChooser;

    private JMenuBar menuBar;

    private MenuHandler menuHandler;

    private JMenuItem undoMenuItem;


    private class Canvas extends JPanel {

        Canvas() {

            setPreferredSize( new Dimension(800,600) );

            setBackground(Color.WHITE);

            setFont( new Font( "Serif", Font.BOLD, 20 ));

        }

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,

                    RenderingHints.VALUE_ANTIALIAS_ON);

            if (mStrings != null)

                for (DrawTextItem s: mStrings)

                    s.draw(g);

        }

    }

    private class MenuHandler implements ActionListener {

        public void actionPerformed(ActionEvent evt) {

            doMenuCommand( evt.getActionCommand());

        }

    }


    public DrawTextPanel() {

        fileChooser = new SimpleFileChooser();

        undoMenuItem = new JMenuItem("Remove");

        undoMenuItem.setEnabled(false);

        menuHandler = new MenuHandler();

        setLayout(new BorderLayout(3,3));

        setBackground(Color.BLACK);

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        canvas = new Canvas();

        add(canvas, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        bottom.add(new JLabel("Text: "));

        input = new JTextField("Hello", 20);

        bottom.add(input);

        add(bottom, BorderLayout.SOUTH);

        canvas.addMouseListener( new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                doMousePress( e );

            }

        }

    }


    public void doMousePress( MouseEvent e ) {

        if (e.isMetaDown()) { // right click remove item

            removeItem();

            return;

        }

        String text = input.getText().trim();

        if (text.length() == 0) {

            input.setText("Hello");

            text = "Hello";

        }

        DrawTextItem s = new DrawTextItem( text, e.getX(), e.getY() );

        s.setTextColor(currentTextColor);

        (black).


        int randomChoice = (int)(Math.random()*3);

        int fontStyle;

        switch (randomChoice) {

            case 0: fontStyle = Font.ITALIC; break;

            case 1: fontStyle = Font.BOLD; break;

            default: fontStyle = Font.ITALIC + Font.BOLD;

        }

        s.setFont( new Font( "Serif", fontStyle, (int)(Math.random()*3+5) ));



        s.setMagnification((int)(Math.random()*3+8));

        // border

        if (Math.random() > 0.3)

            s.setBorder(true);



        // rotation

        s.setRotationAngle(Math.random()*360);



        //transparency

        s.setTextTransparency(Math.random()*0.25);



        //background color

        if (Math.random() > 0.5)

            s.setBackground(new Color((float)Math.random(), (float)Math.random(),

                    (float)Math.random()));



        // background transparency

        s.setBackgroundTransparency(Math.random()*0.50+0.10);



        if (mStrings == null)

            mStrings = new ArrayList<DrawTextItem>();

        mStrings.add(s); // Set this string as the ONLY string to be drawn on the canvas!

        undoMenuItem.setEnabled(true);

        canvas.repaint();

    }

    // menu bar

    public JMenuBar getMenuBar() {

        if (menuBar == null) {

            menuBar = new JMenuBar();

            String commandKey; // for making keyboard accelerators for menu commands

            if (System.getProperty("mrj.version") == null)

                commandKey = "control ";

            else

                commandKey = "control mac ";

            JMenu fileMenu = new JMenu("File");

            menuBar.add(fileMenu);

            JMenuItem saveItem = new JMenuItem("Save");

            saveItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "N"));

            saveItem.addActionListener(menuHandler);

            fileMenu.add(saveItem);

            JMenuItem openItem = new JMenuItem("Open");

            openItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "O"));

            openItem.addActionListener(menuHandler);

            fileMenu.add(openItem);

            fileMenu.addSeparator();

            JMenuItem saveImageItem = new JMenuItem("Save");

            saveImageItem.addActionListener(menuHandler);

            fileMenu.add(saveImageItem);

            JMenu editMenu = new JMenu("Edit");

            menuBar.add(editMenu);

            undoMenuItem.addActionListener(menuHandler);

            undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "Z"));

            editMenu.add(undoMenuItem);

            editMenu.addSeparator();

            JMenuItem clearItem = new JMenuItem("Clear");

            clearItem.addActionListener(menuHandler);

            editMenu.add(clearItem);

            JMenu optionsMenu = new JMenu("Options");

            menuBar.add(optionsMenu);

            JMenuItem colorItem = new JMenuItem("Color");

            colorItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "T"));

            colorItem.addActionListener(menuHandler);

            optionsMenu.add(colorItem);

            JMenuItem bgColorItem = new JMenuItem("Background Color");

            bgColorItem.addActionListener(menuHandler);

            optionsMenu.add(bgColorItem);

        }

        return menuBar;

    }

    private void doMenuCommand(String command) {

        if (command.equals("Save")) {

            saveFile();

        }

        else if (command.equals("Open")) {

            openFile();

            canvas.repaint();

        }

        else if (command.equals("Clear")) {

            mStrings = null;

            undoMenuItem.setEnabled(false);

            canvas.repaint();

        }

        else if (command.equals("Remove"))

            removeItem();

        else if (command.equals("Color")) {

            Color c = JColorChooser.showDialog(this, "Color", currentTextColor);

            if (c != null)

                currentTextColor = c;

        }

        else if (command.equals("Background Color")) {

            Color c = JColorChooser.showDialog(this, "Background Color",

                    canvas.getBackground());

            if (c != null) {

                canvas.setBackground(c);

                canvas.repaint();

            }

        }

        else if (command.equals("Save Image")) {

            File imageFile = fileChooser.getOutputFile(this, "File Name",

                    "textimage.png");

            if (imageFile == null)

                return;

            try {


                BufferedImage image = new BufferedImage(canvas.getWidth(),canvas.getHeight(),

                        BufferedImage.TYPE_INT_RGB);

                Graphics g = image.getGraphics();

                g.setFont(canvas.getFont());

                canvas.paintComponent(g);

                boolean ok = ImageIO.write(image, "PNG", imageFile);

                if (ok == false)

                    throw new Exception("Error");

            }

            catch (Exception e) {

                JOptionPane.showMessageDialog(this,

                        "Error\n" + e);

            }

        }

    }


    private void removeItem() {

        if (mStrings.size() > 0)

            mStrings.remove(mStrings.size()-1); // remove the most recently added string

        if (mStrings.size() == 0)



            undoMenuItem.setEnabled(false);

        canvas.repaint();

    }

// Save

    private void saveFile() {

        File saveAs = fileChooser.getOutputFile(this, "Save As", "Text saver.txt");

        try {

            PrintWriter out = new PrintWriter(saveAs);

            out.println("New file");

            out.println(canvas.getBackground().getRed());

            out.println(canvas.getBackground().getGreen());

            out.println(canvas.getBackground().getBlue());

            if (mStrings != null)

                for (DrawTextItem s: mStrings) {

                    out.println("theString:");

                    out.println(s.getString());

                    out.println(s.getX());

                    out.println(s.getY());

                    out.println(s.getFont().getName());

                    out.println(s.getFont().getStyle());

                    out.println(s.getFont().getSize());

                    out.println(s.getTextColor().getRed());

                    out.println(s.getTextColor().getGreen());

                    out.println(s.getTextColor().getBlue());

                    out.println(s.getTextTransparency());

                    if (s.getBackground() == null) {

                        out.println("-1");

                        out.println("-1");

                        out.println("-1");

                    }

                    else {

                        out.println(s.getBackground().getRed());

                        out.println(s.getBackground().getGreen());

                        out.println(s.getBackground().getBlue());

                    }

                    out.println(s.getBackgroundTransparency());

                    out.println(s.getBorder());

                    out.println(s.getMagnification());

                    out.println(s.getRotationAngle());

                }

            out.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(this, "Cannot write \"" + saveAs + "\".");

            System.out.println("Error" + e);

        }

    }



    private void openFile() {

        File openFile = fileChooser.getInputFile(this, "Open");

        try {

            Scanner in = new Scanner(openFile);

            if (!in.nextLine().equals("New")) {

                JOptionPane.showMessageDialog(this, "Error"" + openFile + "\".");

                return;

            }

            Color savedBg = new Color(in.nextInt(), in.nextInt(), in.nextInt());

            ArrayList<DrawTextItem> newStrings = new ArrayList<DrawTextItem>();

            DrawTextItem newItem;

            in.nextLine();

            while (in.hasNext() && in.nextLine().equals("theString:")) {

                newItem = new DrawTextItem(in.nextLine(),

                        in.nextInt(), in.nextInt());

                in.nextLine(); //skip to the next line before read a new line

                newItem.setFont(new Font(in.nextLine(), in.nextInt(), in.nextInt()));

                newItem.setTextColor(new Color(in.nextInt(), in.nextInt(), in.nextInt()));

                newItem.setTextTransparency(in.nextDouble());

                int r = in.nextInt();

                int g = in.nextInt();

                int b = in.nextInt();

                if (r == -1)

                    newItem.setBackground(null);

                else

                    newItem.setBackground(new Color(r, g, b));

                newItem.setBackgroundTransparency(in.nextDouble());

                newItem.setBorder(in.nextBoolean());

                newItem.setMagnification(in.nextDouble());

                newItem.setRotationAngle(in.nextDouble());

                in.nextLine();

                newStrings.add(newItem);

            }



            canvas.setBackground(savedBg);

            mStrings = newStrings;

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(this, "Error"" + openFile + "\".");

            System.out.println("Error " + e);

        }



    }

}