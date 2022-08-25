package by.teachmeskills;

import by.teachmeskills.dto.Student;
import by.teachmeskills.util.HibernateClient;
import by.teachmeskills.util.JDBCClient;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DataBaseTest {

    @Test
    public void demoTest() {
        Student student = HibernateClient.findById(1);

        List<Student> students = JDBCClient.executeQuery("SELECT * FROM student", (rs) -> {
            List<Student> studentList = new ArrayList<>();
            try {
                while (rs.next()) {
                    studentList.add(new Student().setId(rs.getInt("id"))
                                                 .setName(rs.getString("name"))
                                                 .setGroupNumber(rs.getInt("groupNumber")));
                }
                return studentList;
            } catch (SQLException e) {
                log.error(e.getMessage());
                return new ArrayList<>();
            }
        });
    }
}
