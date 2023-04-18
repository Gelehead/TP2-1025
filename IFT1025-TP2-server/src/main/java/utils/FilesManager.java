package utils;

import server.models.Course;
import server.models.RegistrationForm;

import java.util.ArrayList;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

public class FilesManager {

    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {e.printStackTrace();}
        return lines;
    }

    public static ArrayList<Course> getCourseList(String filePath, String session){
        ArrayList<Course> allCourses = new ArrayList<>();
        List<String> list = utils.FilesManager.readFileInList(filePath);

        for (String s : (Iterable<String>) list) {
            String[] parts = s.split("\t");
            Course course = new Course(parts[1], parts[0], parts[2]);
            if (course.getSession().equals(session)) {
                allCourses.add(course);
            }
        }
        return allCourses;
        }

    public static ArrayList<Course> getCourseList(String filePath) {
        ArrayList<Course> allCourses = new ArrayList<>();
        List<String> list = readFileInList(filePath);

        for (String s : (Iterable<String>) list) {
            String[] parts = s.split("\t");
            Course course = new Course(parts[1], parts[0], parts[2]);
            allCourses.add(course);
        }
        return allCourses;
    }

    public static void formToFile(String filePath, RegistrationForm form) throws IOException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.print(formToString(form));

        pw.close();
    }

    public static String formToString(RegistrationForm form){
        return form.getCourse().getSession() + "\t" + form.getCourse().getCode()
                + "\t" + form.getMatricule() + "\t" + form.getPrenom() + "\t"
                + form.getNom() + "\t" + form.getEmail();
    }
}

