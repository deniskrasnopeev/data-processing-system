import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class Main {

  private static String paramFileName = "testing-input/parameters.txt";
  private static String channelsFileName = "testing-input/channels.txt";
  private static String resultFileName = "results.txt";
  private static Queue<String> expResults = new LinkedList<>(Arrays.asList("b"));

  private static void collectExpResults(int arrayIndex, String[] args) {
    expResults = new LinkedList<>();
    for (int j = arrayIndex + 1; j < args.length; j++) {
      if (args[j].startsWith("-")) {
        return;
      }
      expResults.add(args[j]);
    }
  }

  private static void parseArgs(String[] args) {
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-params":
          paramFileName = args[i + 1];
          break;
        case "-channels":
          channelsFileName = args[i + 1];
          break;
        case "-resname":
          resultFileName = args[i + 1];
          break;
        case "-res":
          collectExpResults(i, args);
          break;
        default:
          break;
      }
    }
  }

  public static void main(String[] args) {

    parseArgs(args);

    ChannelProcessingSystem process =
        new ChannelProcessingSystem(paramFileName, channelsFileName, resultFileName, expResults);
    process.run();

  }

}
