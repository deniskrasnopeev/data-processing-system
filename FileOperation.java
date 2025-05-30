import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handles reading operations using parameter and input files, also provides functionality to write
 * selected variables and their values to an output file
 * 
 * @author Denis Krasnopeev
 *
 */
public class FileOperation {

  private NonUpdatableHashMap<String, Double> parameters;
  private NonUpdatableHashMap<String, ArrayList<Double>> inputs;


  /**
   * Returns the parameters HashMap
   * 
   * @return HashMap of parameter variable names to their values
   */
  public NonUpdatableHashMap<String, Double> getParameters() {
    return parameters;
  }

  /**
   * Returns the inputs HashMap
   * 
   * @return HashMap of input variable names to their list of values
   */
  public NonUpdatableHashMap<String, ArrayList<Double>> getInputs() {
    return inputs;
  }

  /**
   * Constructs a FileOperation instance and loads data from files given.
   * 
   * @param parametersFileName the filename of the file containing parameters
   * @param inputsFileName     the filename of the file containing inputs
   */
  public FileOperation(String parametersFileName, String inputsFileName) {

    this.parameters = new NonUpdatableHashMap<>();
    this.inputs = new NonUpdatableHashMap<>();
    readFiles(parametersFileName, inputsFileName);
  }

  private void readFiles(String parametersFileName, String inputsFileName) {
    readFile(new ParamsAdder(parameters), parametersFileName);
    readFile(new InputsAdder(inputs), inputsFileName);
  }

  private void readLine(Scanner scanner, Adder adder) {
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] data = line.split(",");
      adder.add(data);
    }
  }


  private void readFile(Adder adder, String filename) {
    try (Scanner scanner = new Scanner(new File(filename))) {
      readLine(scanner, adder);
    } catch (IOException e) {
      System.out.println("File " + filename + " doesn't exist.");
    }
  }

  private void createFile(String name) {
    try {
      new File(name).createNewFile();
    } catch (IOException e) {
      System.out.println("File cannot be created.");
    }
  }

  private void writeData(BufferedWriter resultsWriter, Queue<String> results) throws IOException {
    while (!results.isEmpty()) {
      String var = results.poll();
      resultsWriter.write(var);
      if (parameters.containsKey(var)) {
        resultsWriter.write(", " + parameters.get(var));
      } else if (inputs.containsKey(var)) {
        // join the arraylist of values into a comma-separated string and write that string out to
        // the file
        resultsWriter.write(", "
            + inputs.get(var).stream().map(Object::toString).collect(Collectors.joining(", ")));
      } else {
        resultsWriter.write(", Cannot get the value of this variable. Check provided functions.");
      }
      resultsWriter.newLine();
    }
  }

  /**
   * Writes variable names and corresponding value/values into a file with the provided name.
   * 
   * @param resultFileName name of the output file
   * @param results        queue of variable names to retrieve and write to the file
   */
  public void writeFile(String resultFileName, Queue<String> results) {
    String filename = resultFileName;
    createFile(filename);
    try (FileWriter writer = new FileWriter(filename);
        BufferedWriter resultsWriter = new BufferedWriter(writer);) {
      writeData(resultsWriter, results);
    } catch (IOException e) {
      System.out.println("Error writing the file.");
    }
  }



}
