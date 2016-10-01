import org.json.simple.JSONObject;
import java.io.*;
import java.util.Scanner;

/**
 * Created by pronoymukherjee on 29/09/16.
 */
public class AttendanceManager {
    String fileName="Info.txt";
    String fileName1="Details.txt";
    int totalSubjects;
    public void getAttendanceRecord(JSONObject jsObject){
        name=(String) jsObject.get("Name");
        age=(String)jsObject.get("Age");
        semester=(String)jsObject.get("Semester");
        rollNumber=(String) jsObject.get("Roll");
        String subjects[]=(String [])jsObject.get("Subjects");
        totalSubjects=subjects.length;
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
                double percentage=(Integer.parseInt(attendanceRecord[i])/Integer.parseInt(totalAttendance[i])*100);
                obj3.print(subjects[i] + ": " + attendanceRecord[i]+" "+totalAttendance[i]+" "+percentage);
                obj3.println();
            }
            obj3.close();
        }
        catch(Exception e){System.out.println("ERROR: "+e);}
    }
    String name,age,rollNumber,semester;
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
        JSONObject object= new JSONObject();
        try {
            FileReader obj1 = new FileReader(fileName1);
            Scanner obj2= new Scanner(obj1);
            while(obj2.hasNext()){
                String t=obj2.nextLine();
                char ch;int i;String subject="";
                for(i=0;i<t.length();i++){
                    ch=t.charAt(i);
                    if((int)ch>=48 && (int)ch<=57) break;
                    subject+=ch;
                }
                double percentage=Double.parseDouble(t.substring(t.lastIndexOf(' ')+1));
                object.put(subject,percentage);
            }
            obj2.close();
        }
        catch (Exception e){System.out.println("ERROR: "+e);}
        return object;
    }
    public String getName(){
        return name;
    }
    public String getAge() {
        return age;
    }
    public String getSemester(){
        return semester;
    }
    public String getRollNumber(){
        return rollNumber;
    }
    public String[] getSubjects(){
        String subjects[]=new String[totalSubjects];
        int i=0;
        try{
            FileReader obj1= new FileReader(fileName);
            Scanner obj2= new Scanner(obj1);
            while(obj2.hasNext()){
                String t=obj2.nextLine();
                if(t.equalsIgnoreCase("Subjects: ")){
                    while(obj2.hasNext()){
                        subjects[i++]=obj2.nextLine();
                    }
                    break;
                }
            }
        }
        catch (Exception e){System.out.println("ERROR: "+e);}
        return subjects;
    }
}
