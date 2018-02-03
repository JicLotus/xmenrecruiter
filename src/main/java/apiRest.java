package main.java;

import static spark.Spark.*;

public class apiRest {
    public static void main(String[] args) {
       get("/rest", (req, res) -> "Hello Rest");
    }
}
