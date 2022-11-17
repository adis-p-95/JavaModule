import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Scanner;

public class SolutionHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().toString();
        if("GET".equals(httpExchange.getRequestMethod())) {
            handleGetRequest(httpExchange, path);
        } else if("POST".equals(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange, path);
        } else if ("PUT".equals((httpExchange.getRequestMethod()))) {
            handlePutRequest(httpExchange);
        }
    }

    private void handleGetRequest(HttpExchange httpExchange, String path) throws IOException {
        int responseCode = HttpServletResponse.SC_OK;
        StringBuilder responseMessage = new StringBuilder();

        try {
            File file;
            if ("/key".equals(path))
                file = new File(Configuration.KEYS_PATH);
            else if ("/string".equals(path))
                file = new File(Configuration.TEXT_PATH);
            else
                throw new SolutionException("Unsupported path.");

            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                responseMessage.append(input.nextLine());
            }

        } catch (FileNotFoundException exception) {
            System.out.println("File related error: " + exception.getMessage());//scanner cant find related file
            responseCode = HttpServletResponse.SC_NOT_FOUND;
        } catch (SolutionException exception) {
            System.out.println("Path not found " + exception.getMessage());//url path is not valid
            responseCode = HttpServletResponse.SC_NOT_FOUND;
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(responseCode, responseMessage.toString().length());
        outputStream.write(responseMessage.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private void handlePostRequest(HttpExchange httpExchange, String path) throws IOException {
        InputStream inputStream = httpExchange.getRequestBody();
        String body = new String(inputStream.readAllBytes());
        inputStream.close();

        int responseCode = HttpServletResponse.SC_CREATED;

        try {

            FileWriter outputText;
            if ("/key".equals(path))
                outputText = new FileWriter(Configuration.KEYS_PATH, false);
            else if ("/string".equals(path))
                outputText = new FileWriter(Configuration.TEXT_PATH, false);
            else
                throw new SolutionException("Unsupported path");

            outputText.append(body);
            outputText.close();

        } catch (FileNotFoundException exception) {
            System.out.println("File related error: " + exception.getMessage());//scanner cant find related file
            responseCode = HttpServletResponse.SC_NOT_FOUND;
        } catch (SolutionException exception) {
            System.out.println("Path not found " + exception.getMessage());//url path is not valid
            responseCode = HttpServletResponse.SC_NOT_FOUND;
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(responseCode, 0);
        outputStream.close();
    }

    private void handlePutRequest(HttpExchange httpExchange) throws IOException {
        int responseCode = HttpServletResponse.SC_OK;
        StringBuilder responseOutput = new StringBuilder();

        try {
            Solution solution = new Solution();

            File keys_file = new File(Configuration.KEYS_PATH);
            Scanner keysText = new Scanner(keys_file);
            String key = keysText.nextLine();

            File text_file = new File(Configuration.TEXT_PATH);
            Scanner inputText = new Scanner(text_file);

            int total_counter = 0;
            while (inputText.hasNextLine()) {
                String dataText = inputText.nextLine();
                try {
                    int repeat_counter = solution.solution(dataText, key.toCharArray());
                    total_counter = total_counter + repeat_counter;
                    responseOutput.append(repeat_counter + " matches in line " + dataText + ".\r\n");
                } catch (SolutionException exception) {
                    responseOutput.append("Get error " + exception.getMessage() + " when process line " + dataText + ".\r\n");
                }
            }
            responseOutput.append("Total matches: " + total_counter);

            FileWriter outputText = new FileWriter(Configuration.OUTPUT_PATH, false);
            outputText.write(responseOutput.toString());
            outputText.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File related error: " + exception.getMessage());//scanner cant find related file
            responseCode = HttpServletResponse.SC_NOT_FOUND;
        }
        catch (IllegalStateException exception) {
            System.out.println("Scanner related error:" + exception.getMessage()); //next.Line
            responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        catch (IOException exception) {
            System.out.println("Unable to read/write to file. Error: " + exception.getMessage()); //append
            responseCode = HttpServletResponse.SC_SERVICE_UNAVAILABLE;
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(responseCode, responseOutput.toString().length());
        outputStream.write(responseOutput.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
