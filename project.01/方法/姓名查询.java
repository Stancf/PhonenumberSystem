package project02.方法;

import JDBC的使用.entity.phonetable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 姓名查询 {
    public static void main(String[] args) {
        //1. 创建三个接口（连接 处理 结果集）
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(System.in);
        //2. 注册驱动
        // 在try之外创建list的泛型类型
        List<phonetable> phonetables = new ArrayList<>();
        //键盘输入
        //int a = sc.nextInt();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //3. 创建连接对象
            String url = "jdbc:mysql://localhost:3306/mydb";
            String uname = "root";
            String upwd = "133754";
            connection = DriverManager.getConnection(url, uname, upwd);

            //4. 创建执行对象
            statement = connection.createStatement();
            //5. 执行sql语句
            //键盘输入条件信息
            System.out.println("请输入查询联系人的姓名:");
            String name1 = sc.next();
            String sql = "select * from phone_table where 姓名 ='" + name1 + "'";
            resultSet = statement.executeQuery(sql);
            //遍历结果集
            while(resultSet.next()){
                String name = resultSet.getString("姓名");
                String sex = resultSet.getString("性别");
                String unit = resultSet.getString("单位");
                int phonenum = resultSet.getInt("手机号");
                String group = resultSet.getString("分组");
                String weichat = resultSet.getString("微信");
                String remark = resultSet.getString("备注");
                //System.out.println(user + "|" + id + "|" + age + "|" + name + "|" + phone);
                //创建实体对象（new）
                phonetable emplyee = new phonetable(name,sex,unit,phonenum,group,weichat,remark);
                //在循环中只要创建了一个实体对象
                //将这个实体对象存入相应的泛型集合中即可
                phonetables.add(emplyee);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if(resultSet!=null){
                    resultSet.close();
                }
                if (statement!=null){
                    statement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //在try之后使用集合中保存的数据
        System.out.println(phonetables);
    }
}
