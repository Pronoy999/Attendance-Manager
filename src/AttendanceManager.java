import org.json.simple.JSONObject;
import java.io.*;

/**
 * Created by pronoymukherjee on 29/09/16.
 */
public class AttendanceManager {
    String fileName="Info.txt";
    String fileName1="Details.txt";
    public void getAttendanceRecord(JSONObject jsObject){
        String name=(String) jsObject.get("Name");
        String age=(String)jsObject.get("Age");
        String semester=(String)jsObject.get("Semester");
        String rollNumber=(String) jsObject.get("Roll");
        String subjects[]=(String [])jsObject.get("Subjects");
        String totalAttendance[]=(String [])jsObject.get("Total");
        File f= new File(fileName);
        if(!f.exists()){
            fileWrite(name,age,semester,rollNumber,subjects);
        }
        else {
            f.delete();
            fileWrite(name, age, semester, rollNumber,subjects);
        }
        try {
            FileWriter obj1 = new FileWriter(fileName1, false);
            BufferedWriter obj2 = new BufferedWriter(obj1);
            PrintWriter obj3 = new PrintWriter(obj2);
            JSONObject attendance = (JSONObject) jsObject.get("attendance");
            String attendanceRecord[] = new String[subjects.length];
            for (int i = 0; i < subjects.length; i++) {
                attendanceRecord[i] = (String) attendance.get(subjects[i]);
            }
            for (int i = 0; i < subjects.length; i++) {
                double percentage=(attendanceRecord[i]/totalAttendance[i])*100;
                obj3.print(subjects[i] + ": " + attendanceRecord[i]+" "+totalAttendance[i]+" "+percentage);
                obj3.println();
            }
            obj3.close();
        }
        catch(Exception e){System.out.println("ERROR: "+e);}
    }
    public void fileWrite(String name,String age,String semester, String rollNumber,String subjects[]){
        try {
            FileWriter obj1 = new FileWriter(fileName, false);
            BufferedWriter obj2 = new BufferedWriter(obj1);
            PrintWriter obj3 = new PrintWriter(obj2);
            obj3.println("Name: " + name);
            obj3.println("Age: " + age);
            obj3.println("Semester: " + semester);
            obj3.println("Roll Number: " + rollNumber);
            obj3.println("Subjects: ");
            for (int i = 0; i < subjects.length; i++) {
                obj3.print(subjects[i] + ", ");
            }
            obj3.close();
        }
        catch (Exception e){System.out.println("ERROR: "+e);}
    }
    public JSONObject getPercentage(){

    }
}
