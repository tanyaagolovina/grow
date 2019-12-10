package task6;

public class Formatter {

    private static final String PLUS_SYMBOL = "+";
    private static final String PIPE_SYMBOL = "|";
    private static final String MINUS_SYMBOL = "-";
    private static final String UNDERSCORE_SYMBOL = " _ ";
    private static final String LINE_BREAK = "\n";

    public static void main(String[] args) {
        System.out.println(formatOutputOfKeyValuePair("enable", "true"));
        System.out.println(formatOutputOfKeyValuePair("name", "Bob"));
    }

    private static String formatOutputOfKeyValuePair(String key, String value) {
        String keyValueConcatenation = key + UNDERSCORE_SYMBOL + value;
        String lineOfMinuses = printSymbol(MINUS_SYMBOL, keyValueConcatenation.length());
        return PLUS_SYMBOL +  lineOfMinuses + PLUS_SYMBOL + LINE_BREAK
                + PIPE_SYMBOL + keyValueConcatenation + PIPE_SYMBOL + LINE_BREAK +
                PLUS_SYMBOL + lineOfMinuses + PLUS_SYMBOL + LINE_BREAK;
    }

    private static String printSymbol(String symbol, int symbolRepetitions) {
        String symbolLine = "";
        for (int i = 0; i < symbolRepetitions; i++)
            symbolLine += symbol;
        return symbolLine;
    }
}
