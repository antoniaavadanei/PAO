package com.company.service;


import com.company.connection.DatabaseConnection;
import com.company.domain.*;
import com.company.persistence.MovieRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MovieService {

    private static MovieService instance;
    private static final String COUNT_MOVIES = "SELECT * FROM `movies`";
    private static final String SELECT_MOVIES_NAME = "SELECT * FROM `movies`";
    private static final String TICKETS_AVAILABLE= "SELECT * FROM `movies` WHERE id=?";

    private static final String COUNT_ANIMATIONS = "SELECT * FROM `movies` WHERE type='ANIMATION'";
    private static final String SELECT_ANIMATIONS_NAME = "SELECT * FROM `movies` WHERE type='ANIMATION'";

    private static final String COUNT_COMEDIES = "SELECT * FROM `movies` WHERE type='COMEDY'";
    private static final String SELECT_COMEDIES_NAME = "SELECT * FROM `movies` WHERE type='COMEDY'";

    private static final String COUNT_DRAMAS = "SELECT * FROM `movies` WHERE type='DRAMA'";
    private static final String SELECT_DRAMAS_NAME = "SELECT * FROM `movies` WHERE type='DRAMA'";

    private static final String COUNT_HORROR = "SELECT * FROM `movies` WHERE type='HORROR'";
    private static final String SELECT_HORROR_NAME = "SELECT * FROM `movies` WHERE type='HORROR'";

    private static final String COUNT_ROMANTIC = "SELECT * FROM `movies` WHERE type='ROMANTIC'";
    private static final String SELECT_ROMANTIC_NAME = "SELECT * FROM `movies` WHERE type='ROMANTIC'";

    private static final String COUNT_THRILLER = "SELECT * FROM `movies` WHERE type='ANIMATION'";
    private static final String SELECT_THRILLER_NAME = "SELECT * FROM `movies` WHERE type='ANIMATION'";

    private final MovieRepository movieRepository = MovieRepository.getInstance();

    private MovieService() {
    }

    public static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }

        return instance;
    }
    public int maxIdMovie() {
        int max = -1;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_MOVIES)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    if (max < result.getInt("id"))
                        max = result.getInt("id");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return max;
    }
    public int nrMovies() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_MOVIES)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }
    public void printMovies() {
        if (nrMovies() != 0) {
            System.out.println("\n    Movies registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_MOVIES_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Type: " + result.getString("type")+ ", Id: " + result.getString("id")+ ", MovieName " + result.getString("movieName") + ", Duration:" + result.getString("duration")+ ", NrTickets: " + result.getString("nrTickets")+ ", RecommendedAge " + result.getString("recommendedAge"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered movie.");
        }
    }

    public void printAvailableTickets(Integer id) {
        if (nrMovies() != 0) {
            System.out.println("\n    Tickets Available for movie with id: " + id);
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(TICKETS_AVAILABLE)) {
                statement.setInt(1, id);

                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getInt("nrTickets") - result.getInt("soldTickets")+ " tickets available.");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered movie.");
        }
    }


    public void saveAnimation() {
        Animation animation = new Animation().readAnimation();
        animation.setId(maxIdMovie() + 1);
        movieRepository.saveAnimation(animation);
    }

    public Animation findAnimation(Integer id) {
        return movieRepository.findAnimation(id);
    }

    public void updateAnimation(Animation animation) {
        animation.setSoldTickets(animation.getSoldTickets()+1);
        movieRepository.updateAnimation(animation);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteMovie(id);
    }

    public void printAnimations() {
        if (nrAnimations() != 0) {
            System.out.println("\n    Animation registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_ANIMATIONS_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Id: " + result.getString("id")+ ", MovieName " + result.getString("movieName") + ", Duration:" + result.getString("duration")+ ", NrTickets: " + result.getString("nrTickets")+ ", RecommendedAge " + result.getString("recommendedAge"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered animations.");
        }
    }

    public int nrAnimations() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_ANIMATIONS)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public void saveComedy() {
        Comedie comedies = new Comedie().readComedie();
        comedies.setId(maxIdMovie() + 1);
        movieRepository.saveComedie(comedies);
    }
    public Comedie findComedie(Integer id) {
        return movieRepository.findComedie(id);
    }

    public void updateComedie(Comedie comedie) {
        comedie.setSoldTickets(comedie.getSoldTickets()+1);
        movieRepository.updateComedie(comedie);
    }


    public void printComedies() {
        if (nrComedies() != 0) {
            System.out.println("\n    Comedie registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_COMEDIES_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Id: " + result.getString("id")+ ", MovieName " + result.getString("movieName") + ", Duration:" + result.getString("duration")+ ", NrTickets: " + result.getString("nrTickets")+ ", RecommendedAge " + result.getString("recommendedAge"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered comedies.");
        }
    }

    public int nrComedies() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_COMEDIES)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public void saveDrama() {
        Drama drama = new Drama().readDrama();

        drama.setId(maxIdMovie() + 1);
        movieRepository.saveDrama(drama);
    }

    public Drama findDrama(Integer id) {
        return movieRepository.findDrama(id);
    }

    public void updateDrama(Drama drama) {
        drama.setSoldTickets(drama.getSoldTickets()+1);
        movieRepository.updateDrama(drama);
    }


    public void printDramas() {
        if (nrDramas() != 0) {
            System.out.println("\n    Drama registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_DRAMAS_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Id: " + result.getString("id")+ ", MovieName " + result.getString("movieName") + ", Duration:" + result.getString("duration")+ ", NrTickets: " + result.getString("nrTickets")+ ", RecommendedAge " + result.getString("recommendedAge"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered dramas.");
        }
    }

    public int nrDramas() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_DRAMAS)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }
    public void saveHorror() {
        Horror horror = new Horror().readHorror();
        horror.setId(maxIdMovie() + 1);
        movieRepository.saveHorror(horror);
    }

    public Horror findHorror(Integer id) {
        return movieRepository.findHorror(id);
    }

    public void updateHorror(Horror horror) {
        horror.setSoldTickets(horror.getSoldTickets()+1);
        movieRepository.updateHorror(horror);
    }

    public void printHorror() {
        if (nrHorrors() != 0) {
            System.out.println("\n    Horror registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_HORROR_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Id: " + result.getString("id")+ ", MovieName " + result.getString("movieName") + ", Duration:" + result.getString("duration")+ ", NrTickets: " + result.getString("nrTickets")+ ", RecommendedAge " + result.getString("recommendedAge"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered horrors.");
        }
    }

    public int nrHorrors() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_HORROR)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }
    public void saveRomantic() {
        Romantic romantic = new Romantic().readRomantic();
        romantic.setId(maxIdMovie() + 1);
        movieRepository.saveRomantic(romantic);
    }

    public Romantic findRomantic(Integer id) {
        return movieRepository.findRomantic(id);
    }

    public void updateRomantic(Romantic romantic) {
        romantic.setSoldTickets(romantic.getSoldTickets()+1);
        movieRepository.updateRomantic(romantic);
    }

    public void printRomantics() {
        if (nrRomantics() != 0) {
            System.out.println("\n    Romantic registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_ROMANTIC_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Id: " + result.getString("id")+ ", MovieName " + result.getString("movieName") + ", Duration:" + result.getString("duration")+ ", NrTickets: " + result.getString("nrTickets")+ ", RecommendedAge " + result.getString("recommendedAge"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered romantics.");
        }
    }

    public int nrRomantics() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_ROMANTIC)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }
    public void saveThriller() {
        Thriller thriller = new Thriller().readThriller();
        thriller.setId(maxIdMovie() + 1);
        movieRepository.saveThriller(thriller);
    }

    public Thriller findThriller(Integer id) {
        return movieRepository.findThriller(id);
    }

    public void updateThriller(Thriller thriller) {
        thriller.setSoldTickets(thriller.getSoldTickets()+1);
        movieRepository.updateThriller(thriller);
    }

    public void printThrillers() {
        if (nrThrillers() != 0) {
            System.out.println("\n    Thriller registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_THRILLER_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Id: " + result.getString("id")+ ", MovieName " + result.getString("movieName") + ", Duration:" + result.getString("duration")+ ", NrTickets: " + result.getString("nrTickets")+ ", RecommendedAge " + result.getString("recommendedAge"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered thrillers.");
        }
    }

    public int nrThrillers() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_THRILLER)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

}