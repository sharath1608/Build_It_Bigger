package com.example;

import java.util.Random;

public class JokeGenerator {

    final int jokeSelectionSize = 15;

    public String fetchRandomjoke(){

        Random rand = new Random();
        int select = rand.nextInt(jokeSelectionSize);
        String returnString = "";

        switch (select){
            case 0: returnString = "A recent study has found that women who carry a little extra weight live longer than the men who mention it.";
                break;
            case 1: returnString = "Today a man knocked on my door and asked for a small donation towards the local swimming pool. I gave him a glass of water.";
                break;
            case 2: returnString = "If i had a dollar for every girl that found me unattractive, they would eventually find me attractive.";
                break;
            case 3: returnString = "I want to die peacefully in my sleep, like my grandfather.. Not screaming and yelling like the passengers in his car.";
                break;
            case 4: returnString = "I changed my password to \"incorrect\". So whenever I forget what it is the computer will say \"Your password is incorrect\".";
                break;
            case 5: returnString = "I find it ironic that the colors red, white, and blue stand for freedom until they are flashing behind you.";
                break;
            case 6: returnString = "Isn't it great to live in the 21st century? Where deleting history has become more important than making it.";
                break;
            case 7: returnString = "That awkward moment when you leave a store without buying anything and all you can think is \"act natural, you're innocent\".";
                break;
            case 8: returnString = "Life is all about perspective. The sinking of the Titanic was a miracle to the lobsters in the ship's kitchen.";
                break;
            case 9: returnString = "Hospitality: making your guests feel like they're at home, even if you wish they were.";
                break;
            case 10: returnString = "When an employment application asks who is to be notified in case of emergency, I always write, \"A very good doctor\".";
                break;
            case 11: returnString = "I think my neighbor is stalking me as she's been googling my name on her computer. I saw it through my telescope last night.";
                break;
            case 12: returnString = "You know you're ugly when it comes to a group picture and they hand you the camera.";
                break;
            case 13: returnString = "Strong people don't put others down. They lift them up and slam them on the ground for maximum damage.";
                break;
            case 14: returnString = "Money talks ...but all mine ever says is good-bye.";
                break;
            case 15:
                returnString = "This is just a random funny joke. Laugh now so others think this is funny";
        }
       return returnString;
    }
}
