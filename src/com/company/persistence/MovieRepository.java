package com.company.persistence;

import com.company.connection.DatabaseConnection;
import com.company.domain.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRepository {

    private static MovieRepository instance;

    private static final String INSERT_ANIMATION = "INSERT INTO movies (type, id, duration, movieName, nrTickets, recommendedAge, soldTickets, requiresSupervizor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ANIMATION = "SELECT * FROM movies WHERE id = ?";
    private static final String UPDATE_ANIMATION = "UPDATE movies SET soldTickets = ? WHERE id = ?";

    private static final String INSERT_COMEDY = "INSERT INTO movies (type, id, duration, movieName, nrTickets, recommendedAge, soldTickets) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_COMEDY = "SELECT * FROM movies WHERE id = ?";
    private static final String UPDATE_COMEDY = "UPDATE movies SET soldTickets = ? WHERE id = ?";

    private static final String INSERT_DRAMA = "INSERT INTO movies (type, id, duration, movieName, nrTickets, recommendedAge, soldTickets) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_DRAMA = "SELECT * FROM movies WHERE id = ?";
    private static final String UPDATE_DRAMA = "UPDATE movies SET soldTickets = ? WHERE id = ?";

    private static final String INSERT_HORROR = "INSERT INTO movies (type, id, duration, movieName, nrTickets, recommendedAge, soldTickets) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_HORROR = "SELECT * FROM movies WHERE id = ?";
    private static final String UPDATE_HORROR = "UPDATE movies SET soldTickets = ? WHERE id = ?";


    private static final String INSERT_ROMANTIC = "INSERT INTO movies (type, id, duration, movieName, nrTickets, recommendedAge, soldTickets) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ROMANTIC = "SELECT * FROM movies WHERE id = ?";
    private static final String UPDATE_ROMANTIC = "UPDATE movies SET soldTickets = ? WHERE id = ?";

    private static final String INSERT_THRILLER = "INSERT INTO movies (type, id, duration, movieName, nrTickets, recommendedAge, soldTickets) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_THRILLER = "SELECT * FROM movies WHERE id = ?";
    private static final String UPDATE_THRILLER = "UPDATE movies SET soldTickets = ? WHERE id = ?";

    private static final String DELETE_MOVIE = "DELETE FROM movies WHERE id=?";

    private MovieRepository() {
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }

        return instance;
    }
    public void deleteMovie(Integer id) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_MOVIE)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Movie was deleted successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete movie: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to delete movie: movie was not found!");
    }

    public void saveAnimation(Animation animation) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_ANIMATION)) {
            statement.setString(1, animation.getType());
            statement.setInt(2, animation.getId());
            statement.setInt(3, animation.getDuration());
            statement.setString(4, animation.getMovieName());
            statement.setInt(5, animation.getNrTickets());
            statement.setInt(6, animation.getRecommendedAge());
            statement.setInt(7, animation.getSoldTickets());
            statement.setBoolean(8, animation.getRequiresSupervizor());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new animation was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new animation: " + e.getMessage());
            new Animation();
        }
    }

    public Animation findAnimation(Integer id) {
        Animation animation = new Animation();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_ANIMATION)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find animation: Animation was not found!");
                    return animation;
                }

                System.out.println("Animation was found!");
                animation.setId(result.getInt("id"));
                animation.setDuration(result.getInt("duration"));
                animation.setMovieName(result.getString("movieName"));
                animation.setNrTickets(result.getInt("nrTickets"));
                animation.setRecommendedAge(result.getInt("recommendedAge"));
                animation.setSoldTickets(result.getInt("soldTickets"));
                animation.setRequiresSupervizor(result.getBoolean("requiresSupervizor"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find animation: " + e.getMessage());
        }
        return animation;
    }

    public void updateAnimation(Animation animation) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_ANIMATION)) {
            statement.setInt(1, animation.getNrTickets());
            statement.setInt(2, animation.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Animation was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update animation: " + e.getMessage());
            new Animation();
            return;
        }

        System.out.println("Something went wrong when trying to update animation: Animation was not found!");
        new Animation();
    }


    public void saveComedie(Comedie comedie) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_COMEDY)) {
            statement.setString(1, comedie.getType());
            statement.setInt(2, comedie.getId());
            statement.setInt(3, comedie.getDuration());
            statement.setString(4, comedie.getMovieName());
            statement.setInt(5, comedie.getNrTickets());
            statement.setInt(6, comedie.getRecommendedAge());
            statement.setInt(7, comedie.getSoldTickets());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new comedy was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new comedy: " + e.getMessage());
            new Comedie();
        }
    }

    public Comedie findComedie(Integer id) {
        Comedie comedie = new Comedie();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_COMEDY)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find comedy: Comedy was not found!");
                    return comedie;
                }

                System.out.println("Comedie was found!");
                comedie.setId(result.getInt("id"));
                comedie.setDuration(result.getInt("duration"));
                comedie.setMovieName(result.getString("movieName"));
                comedie.setNrTickets(result.getInt("nrTickets"));
                comedie.setRecommendedAge(result.getInt("recommendedAge"));
                comedie.setSoldTickets(result.getInt("soldTickets"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find comedy: " + e.getMessage());
        }
        return comedie;
    }

    public void updateComedie(Comedie comedie) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_COMEDY)) {
            statement.setInt(1, comedie.getSoldTickets());
            statement.setInt(2, comedie.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Comedie was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update comedie: " + e.getMessage());
            new Comedie();
            return;
        }

        System.out.println("Something went wrong when trying to update comedie: Comedie was not found!");
        new Comedie();
    }


    public void saveDrama(Drama drama) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_DRAMA)) {
            statement.setString(1, drama.getType());
            statement.setInt(2, drama.getId());
            statement.setInt(3, drama.getDuration());
            statement.setString(4, drama.getMovieName());
            statement.setInt(5, drama.getNrTickets());
            statement.setInt(6, drama.getRecommendedAge());
            statement.setInt(7, drama.getSoldTickets());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new drama was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new drama: " + e.getMessage());
            new Drama();
        }
    }

    public Drama findDrama(Integer id) {
        Drama drama = new Drama();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_DRAMA)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find drama: Drama was not found!");
                    return drama;
                }

                System.out.println("Drama was found!");
                drama.setId(result.getInt("id"));
                drama.setDuration(result.getInt("duration"));
                drama.setMovieName(result.getString("movieName"));
                drama.setNrTickets(result.getInt("nrTickets"));
                drama.setRecommendedAge(result.getInt("recommendedAge"));
                drama.setSoldTickets(result.getInt("soldTickets"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find drama: " + e.getMessage());
        }
        return drama;
    }

    public void updateDrama(Drama drama) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_DRAMA)) {
            statement.setInt(1, drama.getSoldTickets());
            statement.setInt(2, drama.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Drama was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update drama: " + e.getMessage());
            new Drama();
            return;
        }

        System.out.println("Something went wrong when trying to update drama: Drama was not found!");
        new Drama();
    }

    public void saveHorror(Horror horror) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_HORROR)) {
            statement.setString(1, horror.getType());
            statement.setInt(2, horror.getId());
            statement.setInt(3, horror.getDuration());
            statement.setString(4, horror.getMovieName());
            statement.setInt(5, horror.getNrTickets());
            statement.setInt(6, horror.getRecommendedAge());
            statement.setInt(7, horror.getSoldTickets());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new horror was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new horror: " + e.getMessage());
            new Horror();
        }
    }

    public Horror findHorror(Integer id) {
        Horror horror = new Horror();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_HORROR)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find horror: Horror was not found!");
                    return horror;
                }

                System.out.println("Horror was found!");
                horror.setId(result.getInt("id"));
                horror.setDuration(result.getInt("duration"));
                horror.setMovieName(result.getString("movieName"));
                horror.setNrTickets(result.getInt("nrTickets"));
                horror.setRecommendedAge(result.getInt("recommendedAge"));
                horror.setSoldTickets(result.getInt("soldTickets"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find horror: " + e.getMessage());
        }
        return horror;
    }

    public void updateHorror(Horror horror) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_HORROR)) {
            statement.setInt(1, horror.getSoldTickets());
            statement.setInt(2, horror.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Horror was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update horror: " + e.getMessage());
            new Horror();
            return;
        }

        System.out.println("Something went wrong when trying to update horror: Horror was not found!");
        new Horror();
    }


    public void saveRomantic(Romantic romantic) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_ROMANTIC)) {
            statement.setString(1, romantic.getType());
            statement.setInt(2, romantic.getId());
            statement.setInt(3, romantic.getDuration());
            statement.setString(4, romantic.getMovieName());
            statement.setInt(5, romantic.getNrTickets());
            statement.setInt(6, romantic.getRecommendedAge());
            statement.setInt(7, romantic.getSoldTickets());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new romantic was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new romantic: " + e.getMessage());
            new Romantic();
        }
    }

    public Romantic findRomantic(Integer id) {
        Romantic romantic = new Romantic();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_ROMANTIC)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find romantic: Romantic was not found!");
                    return romantic;
                }

                System.out.println("Romantic was found!");
                romantic.setId(result.getInt("id"));
                romantic.setDuration(result.getInt("duration"));
                romantic.setMovieName(result.getString("movieName"));
                romantic.setNrTickets(result.getInt("nrTickets"));
                romantic.setRecommendedAge(result.getInt("recommendedAge"));
                romantic.setSoldTickets(result.getInt("soldTickets"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find romantic: " + e.getMessage());
        }
        return romantic;
    }

    public void updateRomantic(Romantic romantic) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_ROMANTIC)) {
            statement.setInt(1, romantic.getSoldTickets());
            statement.setInt(2, romantic.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Romantic was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update romantic: " + e.getMessage());
            new Romantic();
            return;
        }

        System.out.println("Something went wrong when trying to update romantic: Romantic was not found!");
        new Romantic();
    }


    public void saveThriller(Thriller thriller) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_THRILLER)) {
            statement.setString(1, thriller.getType());
            statement.setInt(2, thriller.getId());
            statement.setInt(3, thriller.getDuration());
            statement.setString(4, thriller.getMovieName());
            statement.setInt(5, thriller.getNrTickets());
            statement.setInt(6, thriller.getRecommendedAge());
            statement.setInt(7, thriller.getSoldTickets());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new thriller was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new thriller: " + e.getMessage());
            new Thriller();
        }
    }

    public Thriller findThriller(Integer id) {
        Thriller thriller = new Thriller();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_THRILLER)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find thriller: Thriller was not found!");
                    return thriller;
                }

                System.out.println("Thriller was found!");
                thriller.setId(result.getInt("id"));
                thriller.setDuration(result.getInt("duration"));
                thriller.setMovieName(result.getString("movieName"));
                thriller.setNrTickets(result.getInt("nrTickets"));
                thriller.setRecommendedAge(result.getInt("recommendedAge"));
                thriller.setSoldTickets(result.getInt("soldTickets"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find thriller: " + e.getMessage());
        }
        return thriller;
    }

    public void updateThriller(Thriller thriller) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_THRILLER)) {
            statement.setInt(1, thriller.getSoldTickets());
            statement.setInt(2, thriller.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Thriller was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update thriller: " + e.getMessage());
            new Thriller();
            return;
        }

        System.out.println("Something went wrong when trying to update thriller: Thriller was not found!");
        new Thriller();
    }
}
