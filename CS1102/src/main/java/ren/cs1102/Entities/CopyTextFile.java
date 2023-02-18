package ren.cs1102.Entities;
import lib.TextIO;
import org.w3c.dom.Text;

public class CopyTextFile {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Two command-line arguments expected.");
            System.exit(1);
        }
        TextIO.readFile(args[0]);
        TextIO.writeFile(args[1]);
        int lineCount = 0;
        while (TextIO.eof() == false) {
            String line = TextIO.getln();
            TextIO.putln(line);
            lineCount++;
        }
        System.out.printf("%d lines copied from %s to %s%n", lineCount, args[0], args[1]);
    }
}
