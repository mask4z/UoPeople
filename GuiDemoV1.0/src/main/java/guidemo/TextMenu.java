package guidemo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

;

/**
 * A menu full of commands that affect the text shown in a DrawPanel.
 */
public class TextMenu extends JMenu {

    private final DrawPanel panel;    // the panel whose text is controlled by this menu

    private JCheckBoxMenuItem bold;   // controls whether the text is bold or not.
    private JCheckBoxMenuItem italic; // controls whether the text is italic or not.

    private JRadioButtonMenuItem left; // controls whether the text is justified left, center, or right.

    /**
     * Constructor creates all the menu commands and adds them to the menu.
     *
     * @param owner the panel whose text will be controlled by this menu.
     */
    public TextMenu(DrawPanel owner) {
        super("Text");
        this.panel = owner;
        final JMenuItem change = new JMenuItem("Change Text...");
        change.addActionListener((ActionEvent evt) -> {
            String currentText = panel.getTextItem().getText();
            String newText = GetTextDialog.showDialog(panel, currentText);
            if (newText != null && newText.trim().length() > 0) {
                panel.getTextItem().setText(newText);
                panel.repaint();
            }
        });
        final JMenuItem size = new JMenuItem("Set Size...");
        size.addActionListener((ActionEvent evt) -> {
            int currentSize = panel.getTextItem().getFontSize();
            String s = JOptionPane.showInputDialog(panel, "What font size do you want to use?", currentSize);
            if (s != null && s.trim().length() > 0) {
                try {
                    int newSize = Integer.parseInt(s.trim()); // can throw NumberFormatException
                    panel.getTextItem().setFontSize(newSize); // can throw IllegalArgumentException
                    panel.repaint();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, s + " is not a legal text size.\n"
                            + "Please enter a positive integer.");
                }
            }
        });
        final JMenuItem lineHeight = new JMenuItem("Set the line height");

        lineHeight.addActionListener((ActionEvent e) -> {
            double currentLineHeight = panel.getTextItem().getLineHeightMultiplier();
            String input = JOptionPane.showInputDialog(panel, "What line height would you like to use?", currentLineHeight);
            if (input != null && input.trim().length() > 0) {
                try {
                    double newLineHeight = Double.parseDouble(input.trim()); // can throw NumberFormatException
                    panel.getTextItem().setLineHeightMultiplier(newLineHeight);// can throw IllegalArgumentException
                    panel.repaint();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(panel, input + " is not a legal line height. \n"
                            + "Please enter a positive double value.");
                }
            }
        });
        final JMenuItem color = new JMenuItem("Set Color...");
        color.addActionListener((ActionEvent evt) -> {
            Color currentColor = panel.getTextItem().getColor();
            Color newColor = JColorChooser.showDialog(panel, "Select Text Color", currentColor);
            if (newColor != null) {
                panel.getTextItem().setColor(newColor);
                panel.repaint();
            }
        });
        italic = new JCheckBoxMenuItem("Italic");
        italic.addActionListener((ActionEvent evt) -> {
            panel.getTextItem().setItalic(italic.isSelected());
            panel.repaint();
        });
        bold = new JCheckBoxMenuItem("Bold");
        bold.addActionListener((ActionEvent evt) -> {
            panel.getTextItem().setBold(bold.isSelected());
            panel.repaint();
        });
        add(change);
        addSeparator();
        add(size);
        add(lineHeight);
        add(color);
        add(italic);
        add(bold);
        add(createJustifyMenu());
        addSeparator();
        add(makeFontNameSubmenu());
    }

    /**
     * Reset the state of the menu to reflect the default settings for text in a
     * DrawPanel. (Sets the italic and bold checkboxes to unselected.) This
     * method is called by the main program when the user selects the "New"
     * command, to make sure that the menu state reflects the contents of the
     * panel.
     */
    public void setDefaults() {
        italic.setSelected(false);
        bold.setSelected(false);
        left.setSelected(true);

    }

    /**
     * Create a menu containing a list of all available fonts. (It turns out
     * this can be very messy, at least on Linux, but it does show the use what
     * is available and lets the user try everything!)
     */
    private JMenu makeFontNameSubmenu() {
        ActionListener setFontAction = (ActionEvent evt) -> {
            panel.getTextItem().setFontName(evt.getActionCommand());
            panel.repaint();
        };
        JMenu menu = new JMenu("Font Name");
        String[] basic = {"Serif", "SansSerif", "Monospace"};
        for (String f : basic) {
            JMenuItem m = new JMenuItem(f + " Default");
            m.setActionCommand(f);
            m.addActionListener(setFontAction);
            m.setFont(new Font(f, Font.PLAIN, 12));
            menu.add(m);
        }
        menu.addSeparator();
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        if (fonts.length <= 20) {
            for (String f : fonts) {
                JMenuItem m = new JMenuItem(f);
                m.addActionListener(setFontAction);
                m.setFont(new Font(f, Font.PLAIN, 12));
                menu.add(m);
            }
        } else { //Too many items for one menu; divide them into several sub-sub-menus.
            char ch1 = 'A';
            char ch2 = 'A';
            JMenu m = new JMenu();
            int i = 0;
            while (i < fonts.length) {
                while (i < fonts.length && (Character.toUpperCase(fonts[i].charAt(0)) <= ch2 || ch2 == 'Z')) {
                    JMenuItem item = new JMenuItem(fonts[i]);
                    item.addActionListener(setFontAction);
                    item.setFont(new Font(fonts[i], Font.PLAIN, 12));
                    m.add(item);
                    i++;
                }
                if (i == fonts.length || (m.getMenuComponentCount() >= 12 && i < fonts.length - 4)) {
                    if (ch1 == ch2) {
                        m.setText("" + ch1);
                    } else {
                        m.setText(ch1 + " to " + ch2);
                    }
                    menu.add(m);
                    if (i < fonts.length) {
                        m = new JMenu();
                    }
                    ch2++;
                    ch1 = ch2;
                } else {
                    ch2++;
                }
            }
        }
        return menu;
    }

    public JMenu createJustifyMenu() {
        JMenu justifyMenu = new JMenu("Justify");
        ActionListener justifyLeft = (ActionEvent e) -> {
            panel.getTextItem().setJustify(TextItem.LEFT);
            panel.repaint();
        };

        ActionListener justifyCenter = (ActionEvent e) -> {
            panel.getTextItem().setJustify(TextItem.CENTER);
            panel.repaint();
        };

        ActionListener justifyRight = (ActionEvent e) -> {
            panel.getTextItem().setJustify(TextItem.RIGHT);
            panel.repaint();
        };

        ButtonGroup bgroup = new ButtonGroup();

        // LEFT
        left = new JRadioButtonMenuItem("Left");
        left.addActionListener(justifyLeft);
        bgroup.add(left);

        // CENTER
        JRadioButtonMenuItem center = new JRadioButtonMenuItem("Center");
        center.addActionListener(justifyCenter);
        bgroup.add(center);

        // RIGHT
        JRadioButtonMenuItem right = new JRadioButtonMenuItem("Right");
        right.addActionListener(justifyRight);
        bgroup.add(right);

        justifyMenu.add(left);
        justifyMenu.add(center);
        justifyMenu.add(right);

        return justifyMenu;
    }
}
