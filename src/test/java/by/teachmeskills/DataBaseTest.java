package by.teachmeskills;

import by.teachmeskills.dto.Student;
import by.teachmeskills.util.DBClient;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class DataBaseTest {

    DBClient dbClient;

    @BeforeMethod
    public void setUp() {
        dbClient = new DBClient();
        dbClient.connect();
    }

    @Test
    public void selectAllStudents() throws SQLException {
        dbClient.executeUpdateWithPreparedStatement("INSERT INTO student (name, groupNumber) VALUES (?,?)");

        ResultSet resultSet = dbClient.selectFrom("student");
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new Student().setId(resultSet.getInt("id"))
                                      .setName(resultSet.getString("name"))
                                      .setGroupNumber(resultSet.getInt("groupNumber")));

        }
        assertThat(students).hasSize(5);
    }

    @AfterMethod
    public void tearDown() {
        dbClient.close();
    }
}
