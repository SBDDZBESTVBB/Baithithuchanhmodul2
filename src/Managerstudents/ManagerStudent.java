package Managerstudents;

import Model.Student;
import io.ReadAndWrite;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerStudent {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Student> students = new ArrayList<>();
    public final String REGEX_STRING = "[ny]";


    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    //Thông tin sinh viên
    public Student infProduct() {

        System.out.println("Nhap ma sinh vien: ");
        String code = scanner.nextLine();

        System.out.println("Nhap ten sinh vien: ");
        String name = scanner.nextLine();

        System.out.println("Nhap tuoi sinh vien: ");
        int age = checkLoiNhap();

        System.out.println("Nhap gioi tinh sinh vien: ");
        String gender = scanner.nextLine();

        System.out.println("Nhap dia chi sinh vien: ");
        String address = scanner.nextLine();

        System.out.println("Nhap diem trung binh sinh vien sinh vien: ");
        double GPA;

        while (true) {
            try {
                GPA = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Sai đinh dang! Vui long chon lai");
            }
        }

        Student product = new Student(code, name, age, gender, address, GPA);
        System.out.println(product);
        return product;
    }

    //Hiển thị
    public void show() {
        System.out.println("-----------------------------------------------Danh sach hoc sinh-----------------------------------------------");
        System.out.printf("| %-25s| %-15s| %-15s| %-15s| %-15s| %-15s", "Ma học sinh", "Ten", "Tuoi", "Gioi tinh", "Dia chi", "Diem trung binh");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("| %-25s| %-15s| %-15s| %-15s| %-15s| %-15s", student.getCode(), student.getName(), student.getAge(), student.getGender(), student.getAddress(), student.getGPA());
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }

    }

    //Thêm
    public void add() {
        Student product = infProduct();
        students.add(product);
    }

    //Sửa
    public void update() {
        System.out.println("Nhap ma sinh vien ban muon sua: ");
        String code = scanner.nextLine();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getCode().equals(code)) {
                students.set(i, infProduct());
            }
        }
    }

    //Xóa
    public void delete() {
        System.out.println("Nhap ma sinh vien bạn muon xoa:  ");
        String code = scanner.nextLine();
        System.out.println("Ban co chac se xoa sinh vien (yes:y or no:n): ");
        String choice = validateString(REGEX_STRING);
        if (choice.equals("y")) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getCode().equalsIgnoreCase(code)) {
                    students.remove(i);
                    System.out.println("Xoa thanh cong");
                    break;
                }
            }
        } else {
            System.out.println("Huy bo xoa!");
        }

    }

    //Sắp xếp
    public void sort() {
        int choose = 3;
        System.out.println("1. Sap xep diem trung binh tang dan.");
        System.out.println("2. Sap xep diem trung binh giam dan.");
        System.out.println("3. Thoat");
        System.out.println("Nhap so de lua chon ");
        do {
            if (choose > 3) System.out.println("Vui long nhap lai");
            choose = Integer.parseInt(scanner.nextLine());
        } while (choose > 3);

        switch (choose) {
            case 1 -> ascending();
            case 2 -> decrease();
            case 3 -> System.out.println("oke");
        }
    }

    //Tăng dần
    public void ascending() {
        Student temp;
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getGPA() > students.get(j).getGPA()) {
                    temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
        show();

    }

    //Giảm dần
    public void decrease() {

        Student temp;
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getGPA() < students.get(j).getGPA()) {
                    temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }

        show();

    }


    //Đọc từ file
    public void ReadFromFile() {
        System.out.println("Doc tu file co the mat du lieu hien tai, ban co muon tiep tuc (yes:y or no:n): ");
        String choice = validateString(REGEX_STRING);
        if (choice.equals("y")) {
            ReadAndWrite readAndWrite = new ReadAndWrite();
            students = readAndWrite.read("students.csv");
            System.out.println("Doc file thanh cong");
        }
    }

    //Ghi từ file
    public void WriteToFile() {
        System.out.println("Ghi vao file co the mat du lieu, chon yes(y) or no(n) ");
        String choice = validateString(REGEX_STRING);
        if (choice.equals("y")) {
            ReadAndWrite readAndWrite = new ReadAndWrite();
            readAndWrite.write(students,"students.csv");
            System.out.println("Ghi file thanh cong");
        }
    }


    //Check lỗi
    public int checkLoiNhap() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Sai dinh dang, vui long nhap lai");
            }
        }
        return choice;
    }

    public String validateString(String regex) {
        while (true) {
            String name = scanner.nextLine();
            if (name.matches(regex)) {
                return name;
            }
            System.err.println("Sai dinh dang, vui long nhap lai");
        }
    }
}
