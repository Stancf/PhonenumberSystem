package project02;

import JDBC的使用.entity.phonetable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class text01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("+++++通讯录管理系统+++++");
        System.out.println("1.输出通讯录");
        System.out.println("2.查询联系人");
        System.out.println("3.添加联系人");
        System.out.println("4.修改联系人信息");
        System.out.println("5.删除联系人");
        System.out.println("6.退出系统");
        System.out.println("+++++++++++++++++++++");
        System.out.println("请输入你的选择：");
        int choice = sc.nextInt();
        switch (choice){
            case 1://输出
                output();
                break;
            case 2://查询
                System.out.println("+++++通讯录管理系统+++++");
                System.out.println("++++++++精准查询+++++++");
                System.out.println("1.联系人姓名查询");
                System.out.println("2.联系人电话号查询");
                System.out.println();
                int find = sc.nextInt();
                switch (find){
                    case 1://根据姓名查询
                        find_name();
                        break;
                    case 2://根据手机号查询
                        find_num();
                        break;
                }
                break;
            case 3://添加
                add();
                break;
            case 4://修改
                revise();
                break;
            case 5://删除
                delete();
                break;
            case 6:
                System.out.println("系统已退出，欢迎下次使用。");
                break;
            default:
                System.out.println("输入错误");
                break;


        }

    }

    public static void output(){
        //查询通讯录方法
        //1. 创建三个接口（连接 处理 结果集）
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
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
            String sql = "select * from phone_table";
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
    public static void find_name(){
        //根据姓名查询联系人
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
    public static void find_num(){
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
            System.out.println("请输入查询联系人的手机号:");
            String num = sc.next();
            String sql = "select * from phone_table where 手机号 ='" + num + "'";
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
        //根据手机号查询联系人
    }
    public static void add(){
        //添加联系人
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
    public static void revise(){
        //修改联系人信息
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
    public static void delete(){
        //删除联系人
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
