package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MovieWritingService {
    private static MovieWritingService movie_instance = null;
    private MovieWritingService() {
    }

    public static MovieWritingService getInstance() {
        if (movie_instance == null)
            movie_instance = new MovieWritingService();

        return movie_instance;
    }
    public void writeAnimationsToFile(Service S) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("animation.txt"))) {
            for (Film film : S.filmList.get(1)) {
                bufferedWriter.write(film.getMovieName() + "," + film.duration+","+film.nrTickets+","+film.recommendedAge+"");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
            return;
        }
        System.out.println("Successfully wrote " + S.filmList.get(1).size() + " animations!");
        System.out.println();
    }
    public void writeComediesToFile(Service S) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("comedie.txt"))) {
            for (Film film : S.filmList.get(0)) {
                bufferedWriter.write(film.getMovieName() + "," + film.duration+","+film.nrTickets+","+film.recommendedAge+"");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
            return;
        }
        System.out.println("Successfully wrote " + S.filmList.get(0).size() + " comedies!");
        System.out.println();
    }
    public void writeDramaToFile(Service S) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("drama.txt"))) {
            for (Film film : S.filmList.get(2)) {
                bufferedWriter.write(film.getMovieName() + "," + film.duration+","+film.nrTickets+","+film.recommendedAge+"");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
            return;
        }
        System.out.println("Successfully wrote " + S.filmList.get(2).size() + " dramas!");
        System.out.println();
    }
    public void writeHorrorToFile(Service S) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("horror.txt"))) {
            for (Film film : S.filmList.get(3)) {
                bufferedWriter.write(film.getMovieName() + "," + film.duration+","+film.nrTickets+","+film.recommendedAge+"");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
            return;
        }
        System.out.println("Successfully wrote " + S.filmList.get(3).size() + " horror movies!");
        System.out.println();
    }
}
