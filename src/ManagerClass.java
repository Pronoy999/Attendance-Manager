import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by pronoymukherjee on 06/10/16.
 */
public class ManagerClass {
    Scanner ob= new Scanner(System.in);
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    String name,age,subjects[],roll,semester,attendance[];
    JSONObject jsonObject;
    public ManagerClass(){
        name="";
        age="";
        roll="";
    }
    public void Input()throws IOException{
        System.out.println("Enter the Name");
        name=ob.nextLine();
        System.out.println("Enter the age: ");
        age=ob.nextLine();
        System.out.println("Enter the Roll Number: ");
        roll=ob.nextLine();
        System.out.println("Enter the Semester: ");
        semester=ob.nextLine();
        System.out.println("Enter the Total Number of Subjects: ");
        int total=ob.nextInt();
        subjects= new String[total];
        attendance= new String[total];
        System.out.println("Enter the Subjects: ");
        for(int i=0;i<total;i++){
            subjects[i]=br.readLine();
        }
        System.out.println("Enter the Attendance Record for Each Subjects:");
        for(int i=0;i<total;i++){
            System.out.println("Enter the attendance for Subject: "+subjects[i]+": ");
            attendance[i]=br.readLine();
        }
        jsonObject= new JSONObject();
        jsonObject.put("Name",name);
        jsonObject.put("Age",age);
        jsonObject.put("Roll",roll);
        jsonObject.put("Semester",semester);
        JSONObject attendanceObject= new JSONObject();
        for(int i=0;i<total;i++)
            attendanceObject.put(subjects[i],attendance[i]);
        jsonObject.put("Attendance",attendanceObject);
        System.out.println("Enter the Total Attendance for each Subjects:");
        String totalAttendance[]=new String[total];
        for(int i=0;i<total;i++){
            System.out.println("Enter the Total Attendance for Subject: "+subjects[i]+": ");
            totalAttendance[i]= ob.nextLine();
        }
        jsonObject.put("Total",totalAttendance);
    }
    public void Display(){
        AttendanceManager obj=new AttendanceManager(jsonObject);
        obj.getAttendanceRecord();
        System.out.println("The Name of the Student: "+obj.getName());
        System.out.println("The Age of the Student: "+obj.getAge());
        System.out.println("The Roll Number of the Student: "+obj.getRollNumber());
        System.out.println("The Semester: "+obj.getSemester());
        String subjects[]=obj.getSubjects();
        System.out.println("SUBJECTS: ");
        for(String i:subjects)System.out.print(i+" ");
    }
    public static void main(String args[])throws IOException{
        ManagerClass obj= new ManagerClass();
        obj.Input();
        obj.Display();
    }
}
