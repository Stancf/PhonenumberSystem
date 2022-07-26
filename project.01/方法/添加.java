package project02.方法;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class 添加 {
    public static void main(String[] args) {
        //1.定义需要使用的JDBC对象（connection preparedstatement）
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
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
            String sql = "insert into phone_table values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            System.out.println("请输入添加入的名字");
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
            ps.setString(1,name);
            ps.setString(2,sex);
            ps.setString(3,unit);
            ps.setInt(4,phonenum);
            ps.setString(5,group);
            ps.setString(6,weichat);
            ps.setString(7,remark);

            String sql1 = "update fenlei_phonetable set "+ group + "=" + group + "+1";
            ps1 = conn.prepareStatement(sql1);
            //ps1.setString(1,group);
            //ps1.setString(2,group);
            int n = ps.executeUpdate();
            if (n > 0){
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }
            int m = ps1.executeUpdate();
            if (m > 0){
                System.out.println("添加分组成功");
            }else{
                System.out.println("添加分组失败");
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
