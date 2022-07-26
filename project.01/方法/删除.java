package project02.方法;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class 删除 {
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
            String sql = "delete from phone_table where 姓名 = ? ";
            ps = conn.prepareStatement(sql);
            System.out.println("请输入需要删除的人的姓名");
            String name = sc.next();
            //进行替换
            ps.setString(1,name);
            int n = ps.executeUpdate();
            if (n > 0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
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
