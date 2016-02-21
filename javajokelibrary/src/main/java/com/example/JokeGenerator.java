package com.example;

import java.io.File;

public class JokeGenerator {

    public String fetchRandomjoke(){
        File file = new File("jokes.txt");
        if (file.exists()){
            return "File exists";
        }
       return "This is not a random joke from java";
    }
}
