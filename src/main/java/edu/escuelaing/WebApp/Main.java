package edu.escuelaing.WebApp;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class Main {
  public static void main(String[] args) {
    port(getPort());
    staticFiles.location("/public");
    get("/form", (req, res) -> formPage(req, res));
    get("/results", (req, res) -> resultPage(req, res));
  }

  private static String formPage(Request req, Response res) {
    String pageContent = "<!DOCTYPE html>" +
                         "<html>" +
                         "<body>" +
                         "<h2>Form</h2>" +
                         "<form action=\"/results\">" +
                         "  First name:<br>" +
                         "  <input type=\"text\" name=\"firstname\" value=\"Mickey\">" +
                         "  <br>" +
                         "  Last name:<br>" +
                         "  <input type=\"text\" name=\"lastname\" value=\"Mouse\">" + "  <br><br>" +
                         "  <input type=\"submit\" value=\"Submit\">" +
                         "</form>" +
                         "<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/results\".</p>" +
                         "</body>" +
                         "</html>";
    return pageContent;
  }

  private static String resultPage(Request req, Response res) {
    return req.queryParams("firstname") + " " + req.queryParams("lastname");
  }

  private static int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 4567;
  }
}