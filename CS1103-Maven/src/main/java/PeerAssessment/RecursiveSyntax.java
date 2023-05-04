public class RecursiveSyntax {

  static final String[] conjunction = { "and", "or", "but", "because" };

  static final String[] proper_noun = {
    "Paris",
    "Coca-Cola",
    "Beyoncé",
    "Statue of Liberty",
  };

  static final String[] common_noun = {
    "man",
    "woman",
    "fish",
    "elephant",
    "unicorn",
  };

  static final String[] determiner = { "a", "other", "every", "some" };

  static final String[] adjective = { "big", "tiny", "pretty", "bald" };

  static final String[] intransitive_verb = {
    "runs",
    "jumps",
    "talks",
    "sleeps",
  };

  static final String[] transitive_verb = {
    "loves",
    "hates",
    "sees",
    "knows",
    "looks for",
    "finds",
  };

  public static void main(String[] args) {
    while (true) {
      sentence();
      System.out.println(".\n\n");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {}
    }
  }

  /*
   * This function generates sentence as the syntax rule
   * <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
   */
  static void sentence() {
    simpleSentence();
    if (Math.random() > 0.75) {
      System.out.print(" " + randomItem(conjunction));
      sentence();
    }
  }

  /*
   * This function generates simple sentence as the syntax rule
   * <simple_sentence> ::= <noun_phrase> <verb_phrase>
   */
  static void simpleSentence() {
    randomNounPhrase();
    randomVerbPhrase();
  }

  /*
   * This function generates random noun as the following syntax rule
   * <noun_phrase> ::= <proper_noun> | <determiner> [ <adjective> ] <common_noun> [ who <verb_phrase> ]
   */
  static void randomNounPhrase() {
    if (Math.random() > 0.75) {
      System.out.print(" " + randomItem(proper_noun));
    } else {
      System.out.print(" " + randomItem(determiner));
      if (Math.random() > 0.5) {
        System.out.print(" " + randomItem(adjective));
      }
      System.out.print(" " + randomItem(common_noun));
      if (Math.random() > 0.5) {
        System.out.print(" who");
        randomVerbPhrase();
      }
    }
  }

  /*
   * This function generates random verb as the following syntax rule
   * <verb_phrase> ::= <intransitive_verb> | <transitive_verb> <noun_phrase> | is <adjective> | believes that <simple_sentence>
   */
  static void randomVerbPhrase() {
    if (Math.random() > 0.75) {
      System.out.print(" " + randomItem(intransitive_verb));
    } else if (Math.random() > 0.50) {
      System.out.print(" " + randomItem(transitive_verb));
      randomNounPhrase();
    } else if (Math.random() > 0.25) {
      System.out.print(" is " + randomItem(adjective));
    } else {
      System.out.print(" believes that");
      simpleSentence();
    }
  }

  /*
   * This function takes the word arrays and return a random word from the array
   * @param the word array
   * @return random element from the word array
   */
  static String randomItem(String[] wordsArray) {
    return wordsArray[(int) (Math.random() * wordsArray.length)];
  }
}
