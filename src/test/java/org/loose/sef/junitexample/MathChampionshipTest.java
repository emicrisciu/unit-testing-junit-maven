package org.loose.sef.junitexample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MathChampionshipTest {
    private MathChampionship mathChampionship;

    @BeforeEach
    void setUp(){
        mathChampionship = new MathChampionship();
        mathChampionship.setMaxAchievableScore(100);
        /*mathChampionship.setStudentScores(Arrays.asList(
                new StudentScore("John Doe", 97),
                new StudentScore("Jane Doe", 92)
        ));*/
    }

    @Test
    @DisplayName("Test if a student with a score higher than 95% of the max achievable score gets a Gold medal")
    void testGetGoldPrize() {
        readScoresFromCsv("student_scores.csv");
        Prize prize = mathChampionship.getPrize("John Doe");
        assertEquals(Prize.GOLD, prize);
    }

    @Test
    @DisplayName("Test if a student with a score higher than 90% of the max achievable score gets a Silver medal")
    void testGetSilverPrize() {
        readScoresFromCsv("student_scores.csv");
        Prize prize = mathChampionship.getPrize("Jane Doe");
        assertEquals(Prize.SILVER, prize);
    }

    @Test
    @DisplayName("Test if searching for a student that doesn't exist throws a StudentNotFoundException")
    void testNoSuchStudent()
    {
        readScoresFromCsv("student_scores.csv");
        assertThrows(StudentNotFoundException.class, () -> mathChampionship.getPrize("Mr Nobody"));
    }

    private void readScoresFromCsv(String fileName) {
        List<StudentScore> studentScores = new ArrayList<>();
        Path pathToFile;
        try {
            pathToFile = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read file", e);
        }

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                StudentScore student = new StudentScore(attributes[0], Integer.parseInt(attributes[1]));
                studentScores.add(student);
                line = br.readLine();
            }

            mathChampionship.setStudentScores(studentScores);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Test
    @DisplayName("Template for reading student scores from a CSV file")
    @Disabled("This Test is just a template, do not add it to your test suite")
    void testPrizeFromCsv() {
        // Arrange
        readScoresFromCsv("student_scores.csv");

        // Act
        Prize prize = mathChampionship.getPrize("Jane Doe");

        // Assert
        assertEquals(Prize.GOLD, prize);
    }

}