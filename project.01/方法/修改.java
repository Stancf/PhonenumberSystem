package project02.方法;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class 修改 {
    public static void main(String[] args) {
        //1.定义需要使用的JDBC对象（connection preparedstatement）
        Connection conn = null;
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);

        try {
            //2.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //3.创建链接
            String url = "jdbc:mysql://localhost:3306/mydb";
            String username = "root";
            String password = "133754";
            conn = DriverManager.getConnection(url,username,password);
            //4.创建插入语句
            String sql = "update phone_table set 性别 = ?," + "单位 = ?," + "手机号 = ?," + "分组 = ?," + "微信 = ?," + "备注 = ?" + "where 姓名 = ?";
            ps = conn.prepareStatement(sql);
            System.out.println("请输入需要修改信息的名字");
            String name = sc.next();
            System.out.println("请输入性别");
            String sex = sc.next();
            System.out.println("请输入单位");
            String unit = sc.next();
            System.out.println("请输入手机号");
            int phonenum = sc.nextInt();
            System.out.println("请输入分组");
            String group = sc.next();
            System.out.println("请输入微信");
            String weichat = sc.next();
            System.out.println("请输入备注");
            String remark = sc.next();
            //进行替换
            ps.setString(1,sex);
            ps.setString(2,unit);
            ps.setInt(3,phonenum);
            ps.setString(4,group);
            ps.setString(5,weichat);
            ps.setString(6,remark);
            ps.setString(7,name);
            int n = ps.executeUpdate();
            if (n > 0){
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            //释放资源
            try {
                if(ps !=null){
                    ps.close();
                }
                if(conn !=null){
                    conn.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
