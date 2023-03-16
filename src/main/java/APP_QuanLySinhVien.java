import dao.StudentDAO;
import model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class APP_QuanLySinhVien {
    private static StudentDAO studentDAO = new StudentDAO();
    public static void mainMenu() {
        System.out.println("//----STUDENTS MANAGEMENT--------------------------//");
        System.out.println("1. Danh sách sinh viên theo bảng");
        System.out.println("2. Nhập 1 sinh viên mới");
        System.out.println("3. Xóa một sinh viên theo mã");
        System.out.println("4. Cập nhật thông tin sinh viên");
        System.out.println("5. Tìm 1 sinh viên theo họ hoặc tên gần đúng");
        System.out.println("6. Sắp xếp sinh viên theo GPA tăng");
        System.out.println("7. In ra tất cả các sinh viên HN có GPA >2.5");
        System.out.println("8. Sắp xếp sinh viên theo họ tên và bảng chữ cái");
    }
    private static void option1(Scanner in) {
        List<Student> studentList = studentDAO.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s", "Mã sinh viên", "Họ Tên", "Giới tính", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < studentList.size(); i++) {
            Student st = studentList.get(i);
            System.out.printf("%-20s %-20s %-20d %-20s\n", st.getId(), st.getFull_name(), st.getGender(),st.getAddress());
        };
    }
    private static void option2(Scanner in) {
        Student nst = new Student();
        System.out.print("Nhập thông tin sinh viên mới: ");
        System.out.println();

        System.out.printf("id= ");
        nst.setId(in.next());

        System.out.printf("full_name= ");
        nst.setFull_name(in.next());

        System.out.printf("gender= ");
        nst.setGender(Integer.parseInt(in.next()));

        System.out.printf("birthdate= ");
        nst.setBirhdate(in.next());

        System.out.print("address= ");
        nst.setAddress(in.next());

        System.out.print("phone= ");
        nst.setPhone(in.next());

        System.out.print("email= ");
        nst.setEmail(in.next());

        System.out.print("GPA= ");
        nst.setGPA(Integer.parseInt(in.next()));

        System.out.println();
        studentDAO.insert(nst);
    }
    private static void option3(Scanner in){
        System.out.print("Nhập id sinh viên cần xóa : ");
        String u_id = in.next();
        if (studentDAO.getByID(u_id) == null) {
            System.out.println("không tồn tại id sinh viên này");
        }else {
            studentDAO.delete(u_id);
        }
    }
    private static void option4(Scanner in){
        System.out.print("Nhập id sinh viên cần cập nhật : ");
        String u_id = in.next();
        if (studentDAO.getByID(u_id) == null) {
            System.out.println("không tồn tại id sinh viên này");
        }else {
            Student nst = new Student();
            System.out.print("Nhập thông tin sinh viên cần cập nhật: ");
            System.out.println();

            System.out.printf("id= ");
            nst.setId(in.next());

            System.out.printf("full_name= ");
            nst.setFull_name(in.next());

            System.out.printf("gender= ");
            nst.setGender(Integer.parseInt(in.next()));

            System.out.printf("birthdate= ");
            nst.setBirhdate(in.next());

            System.out.print("address= ");
            nst.setAddress(in.next());

            System.out.print("phone= ");
            nst.setPhone(in.next());

            System.out.print("email= ");
            nst.setEmail(in.next());

            System.out.print("GPA= ");
            nst.setGPA(Integer.parseInt(in.next()));

            System.out.println();

            studentDAO.update(nst,u_id);
        }
    }
    private static void option5(Scanner in){
        System.out.println("Nhập họ tên cần tìm= ");
        String names = in.next();
        if (studentDAO.getAll() != null) {
            studentDAO.getAll().stream().sorted(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return (o1.getFull_name().compareTo(o2.getFull_name()));
                }
            });
        }

    }
    private static void option6(Scanner in){
        System.out.println("Sắp xếp theo GPA tăng dần");
        studentDAO.getAll().stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getGPA() > o2.getGPA()) {
                    return 1;
                } else if (o1.getGPA() < o2.getGPA()) {
                    return -1;
                }
                return 0;
            }
        }).forEach(student -> System.out.println(student));
    }
    private static void option7(Scanner in){
        System.out.println("Danh sách sinh viên nữ ở Hà Nội có GPA >2.5");
        studentDAO.getAll().stream().filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                if (student.getGender() == 1) {
                    return true;
                }
                return false;
            }
        }).filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getAddress().equalsIgnoreCase("Hà Nội");
            }
        }).filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getGPA() > 2.5;
            }
        }).forEach(student -> System.out.println(student));
    }
    private static void option8(Scanner in){
        System.out.println("Sắp xếp sinh viên theo họ tên tăng dần (abc): ");
       studentDAO.getAll().stream().sorted(new Comparator<Student>() {
           @Override
           public int compare(Student o1, Student o2) {
               return o1.getFull_name().compareTo(o2.getFull_name());
           }
       }).forEach(student -> System.out.println(student));
    }
    public static void main(String[] args) {
        //dien du lieu test
        studentDAO.insert(new Student("BKC222001","Sang",0,"09/11/1997","Hà Nội","0344113","yasuo@lmht",3));
        studentDAO.insert(new Student("BKC222022","Minh",1,"09/11/1997","isrel","0344113","yasuo@lmht",2));
        studentDAO.insert(new Student("BKC222032","Kien",0,"09/11/1997","Hà Nội","0344113","yasuo@lmht",3));
        studentDAO.insert(new Student("BKC222010","Tuan",1,"09/11/1997","isrel","0344113","yasuo@lmht",3));
        studentDAO.insert(new Student("BKC222011","Huy",0,"09/11/1997","Hà Nội","0344113","yasuo@lmht",3));
        studentDAO.insert(new Student("BKC222015","Sang",1,"09/11/1997","Hà Nội","0344113","yasuo@lmht",3));
        studentDAO.insert(new Student("BKC222050","Van",0,"09/11/1997","Hà Nội","0344113","yasuo@lmht",3));
        studentDAO.insert(new Student("BKC222049","Yasuo",1,"09/11/1997","isrel","0344113","yasuo@lmht",2));
        studentDAO.insert(new Student("BKC222033","akali",0,"09/11/1997","Hà Nội","0344113","yasuo@lmht",3));
        Scanner in = new Scanner(System.in);
        int option = -1;
        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            //option = Integer.parseInt(in.nextLine());
            try {
                option = Integer.parseInt(in.next());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng");
                continue;
            }
            //---------out option---------------------------------
            if (option < 1 || option > 8) {
                System.out.println("Vui lòng nhập lại option !");
                continue;
            }

            //SWITCH OPTION------------------------------------------------------
            switch (option) {
                case 1:
                    option1(in);
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    option5(in);
                    break;
                case 6:
                    option6(in);
                    break;
                case 7:
                    option7(in);
                    break;
                case 8:
                    option8(in);
                    break;
            }

            ///---------------------------------------------------------------------
        } while (option!=0);
        in.close();



    }
}
