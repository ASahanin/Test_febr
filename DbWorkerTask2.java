import java.sql.*;

public class DbWorkerTask2 {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "And13181315!";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addNews(String title, String text) {
        String SQL = "insert into news (title, text, published_at) values(?, ?, current_date)";
        try(Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, text);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showNews() {
        String SQL = "SELECT * FROM news";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next())
            {
                System.out.println("Id: " + rs.getInt(1) + " /" + " TITLE: '" + rs.getString(2)
                        + "' TEXT: '" + rs.getString(3) + "'");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void deleteNews(int id) {
        String SQL = "delete from news where news.id = ?;";
        try(Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void replaceNews(int id, String title, String text) {
        String SQL = "update news set title = ?, text = ? where news.id = ?;";
        try(Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
