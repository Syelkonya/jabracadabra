package su.syelkonya;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {

    GradeBook gradeBook;

    @BeforeEach
    void setUp() {
        gradeBook = new GradeBook();
        gradeBook.addGrade("Иван", 5.0);
        gradeBook.addGrade("Иван", 4.0);
        gradeBook.addGrade("Иван", 3.0);

        gradeBook.addGrade("Мария", 5.0);
        gradeBook.addGrade("Мария", 5.0);
        gradeBook.addGrade("Мария", 4.0);

        gradeBook.addGrade("Петр", 3.0);
        gradeBook.addGrade("Петр", 3.0);
        gradeBook.addGrade("Петр", 4.0);
        gradeBook.addGrade("Петр", 5.0);
    }

    @Test
    void average() {
        assertEquals(4.0, gradeBook.average("Иван"), 0.001);
        assertEquals(4.666, gradeBook.average("Мария"), 0.001);
        assertEquals(3.75, gradeBook.average("Петр"), 0.001);
    }

    @Test
    void bestStudent() {
        assertEquals("Мария", gradeBook.bestStudent());
    }

    @Test
    void addGrade() {
        gradeBook.addGrade("Иван", 5.0);
        assertEquals(4.25, gradeBook.average("Иван"), 0.001);
    }
}