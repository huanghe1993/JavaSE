package com.huanghe;

import com.huanghe.comparator.ImplComparator;
import com.huanghe.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author River
 * @date 2020/5/24 19:23
 * @description
 */
public class App {

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student(10, "Mike"));
        studentList.add(new Student(18, "Angela"));
        studentList.add(new Student(16, "Lucy"));

        //new ImplComparator<Student>().sortByAge(studentList);


    }
}
