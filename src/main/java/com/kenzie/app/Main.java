package com.kenzie.app;

import CustomHTTPClient.CustomHttpClient;
import com.kenzie.app.QuestionDTO.KenzieDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String GET_QUESTIONS_URL = "https://jservice.kenzie.academy/api/clues";

        try {
            playGame(GET_QUESTIONS_URL);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void playGame(String URL) throws JsonProcessingException {
        //scanner for reading user input
        Scanner scanner = new Scanner(System.in);

        //keep track of player points
        int totalScore = 0;

        //counter to stop at 10 questions
        int numQuestions = 0;

        //get request method from class
        CustomHttpClient.sendGET(URL);
        String response = CustomHttpClient.sendGET(URL);
        ObjectMapper objectMapper = new ObjectMapper();

        //instance of DTO
        KenzieDTO kenzieDTO = objectMapper.readValue(response, KenzieDTO.class);

        //loop until 10 questions
        for (int i = 0; i < 10; i++) {
            // generate random number between 1 and 100
            int max = 100;
            int min = 1;
            int range = max - min + 1;
            int rand = (int) (Math.random() * range) + min;

            //print out category title + question + ID
            System.out.println("Category: " + kenzieDTO.getClues().get(rand).getCategory().getTitle());
            System.out.println("Question: " + kenzieDTO.getClues().get(rand).getQuestion());
            System.out.println("ID: " + kenzieDTO.getClues().get(rand).getId());

            //prompt for user input then read input
            System.out.println("Please enter correct answer for a point...");
            String userAnswer = scanner.nextLine();

            //try again if user enters nothing or empty space
            if (userAnswer.equals("") || userAnswer.equals(" ")) {
                System.out.println("Please try entering your answer again: ");
                scanner.nextLine();
            }
            if (userAnswer.equalsIgnoreCase(kenzieDTO.getClues().get(rand).getAnswer())) {
                //if correct answer, add a point to the users score and print message and totalScore
                totalScore++;
                System.out.println("Congratulations! You've answered correctly and scored a point!");
                System.out.println("Your Total Score is " + totalScore);
            } else {
                //if not correct answer, print incorrect message and + totalScore + correct answer
                System.out.println("Sorry that answer is incorrect, your score is still " + totalScore + ".");
                System.out.println("The correct answer is " + kenzieDTO.getClues().get(rand).getAnswer());
            }
            //add one question to the numQuestions counter and print a new line
            numQuestions++;
            System.out.print("\n");

            //stop game after numQuestions displayed = 10 + final score + "Game over"
            if (numQuestions == 10) {
                System.out.println("Your final score is " + totalScore + " !");
                System.out.println("Game over");
                break;
            }
        }
    }
}




