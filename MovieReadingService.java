package com.company;

import java.io.*;

public class MovieReadingService {
    private static MovieReadingService movie_instance = null;
    private MovieReadingService() {
    }

    public static MovieReadingService getInstance() {
        if (movie_instance == null)
            movie_instance = new MovieReadingService();

        return movie_instance;
    }

    public void readAnimationsFromFile(Service S) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\anton\\IdeaProjects\\ProiectPAO\\animation.txt"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Animation animation = new Animation(dataFields[0],Integer.parseInt(dataFields[1]), Integer.parseInt(dataFields[2]),Integer.parseInt(dataFields[3]));
                S.registerFilm(animation,1);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        if(S.filmList.containsKey(1)) {
            System.out.println("Successfully read " + S.filmList.get(1).size() + " animations!");
        }
        else{
            System.out.println("animations.txt is empty.");
        }
    }
    public void readComediesFromFile(Service S) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\anton\\IdeaProjects\\ProiectPAO\\comedie.txt"))){
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Comedie comedie = new Comedie(dataFields[0],Integer.parseInt(dataFields[1]), Integer.parseInt(dataFields[2]),Integer.parseInt(dataFields[3]));
                S.registerFilm(comedie,0);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        catch (NullPointerException e){
            System.out.println("File is empty."+ e.getMessage());
            return;
        }
        if(S.filmList.containsKey(0)) {
            System.out.println("Successfully read " + S.filmList.get(0).size() + " comedies!");
        }
        else{
            System.out.println("comedies.txt is empty.");
        }
    }
    public void readDramasFromFile(Service S) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\anton\\IdeaProjects\\ProiectPAO\\drama.txt"))) {
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Drama drama = new Drama(dataFields[0],Integer.parseInt(dataFields[1]), Integer.parseInt(dataFields[2]),Integer.parseInt(dataFields[3]));
                S.registerFilm(drama,2);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        if(S.filmList.containsKey(2)) {
            System.out.println("Successfully read " + S.filmList.get(2).size() + " dramas!");
        }
        else{
            System.out.println("drama.txt is empty.");
        }
    }
    public void readHorrorFromFile(Service S) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\anton\\IdeaProjects\\ProiectPAO\\horror.txt"))) {
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Horror horror = new Horror(dataFields[0],Integer.parseInt(dataFields[1]), Integer.parseInt(dataFields[2]),Integer.parseInt(dataFields[3]));
                S.registerFilm(horror,3);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        if(S.filmList.containsKey(3)) {
            System.out.println("Successfully read " + S.filmList.get(3).size() + " horror movies!");
        }
        else{
            System.out.println("horror.txt is empty.");
        }
    }



}
