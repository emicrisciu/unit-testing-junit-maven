package org.loose.sef.junitexample;

import java.util.List;

public class MathChampionship {
    private List<StudentScore> studentScores;
    private int maxAchievableScore;

    public List<StudentScore> getStudentScores() {
        return studentScores;
    }

    public int getMaxAchievableScore() {
        return maxAchievableScore;
    }

    public void setStudentScores(List<StudentScore> studentScores) {
        this.studentScores = studentScores;
    }

    public void setMaxAchievableScore(int maxAchievableScore) {
        this.maxAchievableScore = maxAchievableScore;
    }

    // constructor, getters and setters -> generated by IDE (Alt + Insert)

    public Prize getPrize(String studentName) {
        StudentScore targetStudent = null;

        for (StudentScore student : studentScores) {
            if (student.getStudentName().equals(studentName)) {
                targetStudent = student;
                break;
            }
        }

        if (targetStudent == null) {
            throw new StudentNotFoundException(studentName);
        }

        // find the prize
        if (targetStudent.getScore() > 95) {
            return Prize.GOLD;
        } else if (targetStudent.getScore() > 90) {
            return Prize.SILVER;
        } else if (targetStudent.getScore() > 85) {
            return Prize.BRONZE;
        } else if (targetStudent.getScore() > 80) {
            return Prize.MENTION;
        } else {
            return null;
        }
    }
}