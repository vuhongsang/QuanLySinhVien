package dao;

import connection.MyConnection;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAll() {
        final String sql = "SELECT * FROM `students`";

        List<Student> studentList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getString("id"));
                st.setFull_name(rs.getString("full_name"));
                st.setGender(rs.getLong("gender"));
                st.setBirhdate(rs.getString("birhdate"));
                st.setAddress(rs.getString("address"));
                st.setPhone(rs.getString("phone"));
                st.setEmail(rs.getString("email"));
                st.setGPA(rs.getLong("GPA"));
                studentList.add(st);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }
    public Student getByID(String id) {
        final String sql = String.format("SELECT * FROM `students` WHERE  `id` = '%s'",id);
        Student st = null;

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                st = new Student();
                st.setId(rs.getString("id"));
                st.setFull_name(rs.getString("full_name"));
                st.setGender(rs.getLong("gender"));
                st.setBirhdate(rs.getString("birhdate"));
                st.setAddress(rs.getString("address"));
                st.setPhone(rs.getString("phone"));
                st.setEmail(rs.getString("email"));
                st.setGPA(rs.getLong("GPA"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }
    public void update(Student st, String id) {
        Student tmp = getByID(id);
        if (tmp == null) {
            throw new RuntimeException("Sinh viên không tồn tại!");
        }

        final String sql = String.format("UPDATE `students` SET `id`='%s',`full_name`='%s',`gender`='%d',`birhdate`='%s',`address`='%s',`phone`='%s',`email`='%s',`GPA`='%d' WHERE `id` = '%s'",
                st.getId(),st.getFull_name(),st.getGender(),st.getBirhdate(),st.getAddress(),st.getPhone(),st.getEmail(),st.getGPA(),id
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            } else {
                System.out.println("Cập nhật thành công");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void delete(String id) {
        Student student = getByID(id);
        if (student == null) {
            throw new RuntimeException("Sinh viên không tồn tại!");
        }

        final String sql = String.format("DELETE FROM `students` WHERE  `id` = '%s'", id);
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xoá thất bại");
            } else {
                System.out.println("Xóa thành công");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insert(Student st) {
        final String sql = String.format("INSERT  INTO `students` VALUES ( '%s','%s','%d','%s','%s','%s','%s','%d' ) ",
                st.getId(),st.getFull_name(),st.getGender(),st.getBirhdate(),st.getAddress(),st.getPhone(),st.getEmail(),st.getGPA()
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Thêm thất bại");
            } else {
                System.out.println("Thêm thành công");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
