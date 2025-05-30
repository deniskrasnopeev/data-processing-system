import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Delegates reading and writing the files to FileOperator class. Performs calculations on the read
 * in data using the functions included in ArrayList<BaseFunction> functions.
 * 
 * @author Denis Krasnopeev
 *
 */
public class ChannelProcessingSystem {

  private String parametersFileName;
  private String inputsFileName;
  private String resultFileName;
  private Queue<String> expRes;

  private NonUpdatableHashMap<String, Double> parameters;
  private NonUpdatableHashMap<String, ArrayList<Double>> inputs;

  /**
   * Constructs a ChannelProcessingSystem instance.
   * 
   * @param parametersFileName filename of a file containing program parameters
   * @param inputsFileName     filename of a file containing channel data input
   * @param resultFileName     filename of a file to which the results of the program should be
   *                           written
   * @param expRes             queue of variable names that should be calculated, retrieved and
   *                           written to the output file
   */
  public ChannelProcessingSystem(String parametersFileName, String inputsFileName,
      String resultFileName, Queue<String> expRes) {
    this.parametersFileName = parametersFileName;
    this.inputsFileName = inputsFileName;
    this.resultFileName = resultFileName;
    this.expRes = expRes;
  }

  private void evaluateFunctions(ArrayList<BaseFunction> functions) {
    int i = 0;
    while (i < functions.size()) {
      try {
        // I made the design decision to make the functions' return type void and the
        // functions themselves mutate the passed in data structures. This allows to avoid
        // redundant merge results operations and provides more flexibility allowing the functions
        // to "return" more than one type of calculation(both channels and metrics can be calculated
        // in the same function).
        functions.get(i).calculate(parameters, inputs);
        functions.remove(i);
        i = 0;
      } catch (NoSuchElementException e) {
        i++;
      } catch (IllegalArgumentException e) {
        System.out.println(
            functions.get(i).getClass().getName() + " attempts to overwrite an existing variable.");
        functions.remove(i);
      }
    }
  }

  /**
   * Loads data, executes the provided functions in a working order and writes the requested results
   * into a file.
   */
  public void run() {
    FileOperation operator = new FileOperation(parametersFileName, inputsFileName);
    parameters = operator.getParameters();
    inputs = operator.getInputs();
    // if more functions need to be added they should extend BaseFunction and be included in this
    // array list
    // this could be improved by using reflections to automatically pick up any instances of the
    // functions that extend BaseFunction and including them into the execution list
    ArrayList<BaseFunction> functions = new ArrayList<>(
        Arrays.asList(new Function1(), new Function2(), new Function3(), new Function4()));

    evaluateFunctions(functions);
    if (!functions.isEmpty()) {
      System.out.println(
          "The program was unable to execute all functions provided due to a lack of required inputs.");
    }
    operator.writeFile(resultFileName, expRes);
  }

}
