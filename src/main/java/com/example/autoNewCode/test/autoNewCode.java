package com.example.autoNewCode.test;

import com.example.autoNewCode.pojo.DataBase;
import com.example.autoNewCode.pojo.Settings;
import com.example.autoNewCode.pojo.Table;
import com.example.autoNewCode.utils.*;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class autoNewCode {

    @Test
    public void test() throws IOException, TemplateException {
        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = this.getClass().getClassLoader().getResource("templates").getPath();
        System.out.println(templates);
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("test.ftl");
        //4，构造数据模型
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "测试人员");
        map.put("password", 1234);

        List<String> list = new ArrayList<>();
        list.add("第一个");
        list.add("第二个");
        map.put("list", list);

        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));
    }

    @Test
    public void test01() throws IOException, TemplateException {
        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = this.getClass().getClassLoader().getResource("templates").getPath();
        System.out.println(templates);
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("${table.name}.java");
        //4，构造数据模型
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pPackage", "com");

        map.put("tablename", "User");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        map.put("tablecolumnList", list);

        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));
    }

    /**
     * 根据数据库生成pojo实体类的
     * @throws Exception
     */
    @Test
    public void testPojo() throws Exception {
        String username = "wc";
        String password = "1140";
        String ip = "112.124.9.174";
        String db = "protect";
        DataBase dataBase = new DataBase("MYSQL",ip,"3306",db);
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        List<Table> tables = DataBaseUtils.getDbInfo(dataBase, "protect");
        /*for (Table table : tables) {
            //对每个table进行代码生成
            System.out.println(table.toString());

        }*/
        Table t = tables.get(0);

        /* 转成Map */
        Map<String, Object> map = new HashMap<>();
        //自定义配置
        map.putAll(PropertiesUtils.customMap);
        //元数据
        map.put("table", t);
        //settings
        String packagename = "com.ftx.demo";
        String projectEngName = "qaq";
        map.put("project", projectEngName);
        map.put("pPackage", packagename);
        map.put("path1", "com");
        map.put("path2", "ftx");
        map.put("path3", "demo");
        //类名
        map.put("className", t.getName2());
        System.out.println(map);

        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = this.getClass().getClassLoader().getResource("templates").getPath();
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("${table.name}.java");
        //4，构造数据模型
        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));

    }

    /**
     * 根据数据库生成dao实体类的
     * @throws Exception
     */
    @Test
    public void testDao() throws Exception {
        String username = "wc";
        String password = "1140";
        String ip = "112.124.9.174";
        String db = "protect";
        DataBase dataBase = new DataBase("MYSQL",ip,"3306",db);
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        List<Table> tables = DataBaseUtils.getDbInfo(dataBase, "protect");
        /*for (Table table : tables) {
            //对每个table进行代码生成
            System.out.println(table.toString());

        }*/
        Table t = tables.get(0);

        /* 转成Map */
        Settings settings = new Settings();
        Map<String, Object> map = ConvertUtils.setConditionMap(settings);
        //自定义配置
        map.putAll(PropertiesUtils.customMap);
        //元数据
        map.put("table", t);
        //settings
        String packagename = "com.ftx.demo";
        String projectEngName = "qaq";
        map.put("project", projectEngName);
        map.put("pPackage", packagename);
        map.put("path1", "com");
        map.put("path2", "ftx");
        map.put("path3", "demo");
        //类名
        map.put("className", t.getName2());
        System.out.println(map);

        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = this.getClass().getClassLoader().getResource("templates").getPath();
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("${table.name}Mapper.java");
        //4，构造数据模型
        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));

    }


    /**
     * 根据数据库生成service实体类的
     * @throws Exception
     */
    @Test
    public void testService() throws Exception {
        String username = "wc";
        String password = "1140";
        String ip = "112.124.9.174";
        String db = "protect";
        DataBase dataBase = new DataBase("MYSQL",ip,"3306",db);
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        List<Table> tables = DataBaseUtils.getDbInfo(dataBase, "protect");
        /*for (Table table : tables) {
            //对每个table进行代码生成
            System.out.println(table.toString());

        }*/
        Table t = tables.get(0);

        /* 转成Map */
        Settings settings = new Settings();
        Map<String, Object> map = ConvertUtils.setConditionMap(settings);
        //自定义配置
        map.putAll(PropertiesUtils.customMap);
        //元数据
        map.put("table", t);
        //settings
        String packagename = "com.ftx.demo";
        String projectEngName = "qaq";
        map.put("project", projectEngName);
        map.put("pPackage", packagename);
        map.put("path1", "com");
        map.put("path2", "ftx");
        map.put("path3", "demo");
        //类名
        map.put("className", t.getName2());
        System.out.println(map);

        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = this.getClass().getClassLoader().getResource("templates").getPath();
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("${table.name}Service.java");
        //4，构造数据模型
        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));

    }

    /**
     * 根据数据库生成serviceImpl实体类的
     * @throws Exception
     */
    @Test
    public void testServiceImpl() throws Exception {
        String username = "wc";
        String password = "1140";
        String ip = "112.124.9.174";
        String db = "protect";
        DataBase dataBase = new DataBase("MYSQL",ip,"3306",db);
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        List<Table> tables = DataBaseUtils.getDbInfo(dataBase, "protect");
        /*for (Table table : tables) {
            //对每个table进行代码生成
            System.out.println(table.toString());

        }*/
        Table t = tables.get(0);

        /* 转成Map */
        Settings settings = new Settings();
        Map<String, Object> map = ConvertUtils.setConditionMap(settings);
        //自定义配置
        map.putAll(PropertiesUtils.customMap);
        //元数据
        map.put("table", t);
        //settings
        String packagename = "com.ftx.demo";
        String projectEngName = "qaq";
        map.put("project", projectEngName);
        map.put("pPackage", packagename);
        map.put("path1", "com");
        map.put("path2", "ftx");
        map.put("path3", "demo");
        //类名
        map.put("className", t.getName2());
        System.out.println(map);

        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = this.getClass().getClassLoader().getResource("templates").getPath();
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("${table.name}ServiceImpl.java");
        //4，构造数据模型
        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));

    }

    /**
     * 根据数据库生成controller实体类的
     * @throws Exception
     */
    @Test
    public void testController() throws Exception {
        String username = "wc";
        String password = "1140";
        String ip = "112.124.9.174";
        String db = "protect";
        DataBase dataBase = new DataBase("MYSQL",ip,"3306",db);
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        List<Table> tables = DataBaseUtils.getDbInfo(dataBase, "protect");
        /*for (Table table : tables) {
            //对每个table进行代码生成
            System.out.println(table.toString());

        }*/
        Table t = tables.get(0);

        /* 转成Map */
        Settings settings = new Settings();
        Map<String, Object> map = ConvertUtils.setConditionMap(settings);

        //自定义配置
        map.putAll(PropertiesUtils.customMap);
        //元数据
        map.put("table", t);
        //settings
        String packagename = "com.ftx.demo";
        String projectEngName = "qaq";
        map.put("project", projectEngName);
        map.put("pPackage", packagename);
        map.put("path1", "com");
        map.put("path2", "ftx");
        map.put("path3", "demo");
        //类名
        map.put("className", t.getName2());


        System.out.println(map);

        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration();
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = this.getClass().getClassLoader().getResource("templates").getPath();
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("${table.name}Controller.java");
        //4，构造数据模型
        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
        //template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));

    }

    @Test
    public void test4() throws Exception {
        String packagename = "com.ftx.demo";
        String projectEngName = "qaq";
        String ip = "112.124.9.174";
        String port = "3306";
        //数据库
        String db = "protect";
        String username = "wc";
        String password = "1140";
        String dbKind = "MYSQL";
        String fileUrl = "D:\\code\\springb_protect\\src\\main\\resources";
        Settings settings = new Settings();
        //包名(com.ftx.demo)
        settings.setPPackage(packagename);
        //split(".")无法分割字符串，必须加上\\
        String[] split = packagename.split("\\.");
        //com
        settings.setPath1(split[0]);
        //ftx
        settings.setPath2(split[1]);
        //demo
        settings.setPath3(split[2]);
        //项目名（没啥用）
        settings.setProject(projectEngName);
        //默认只支持mysql数据库吧，oracle暂时先不处理，先写死为mysql
        DataBase dbs = new DataBase("MYSQL", ip, port,db);
        dbs.setUserName(username);
        dbs.setPassWord(password);

        GeneratorFacade generatorFacade = new GeneratorFacade(dbKind, fileUrl, settings, dbs);
        System.out.println(generatorFacade.toString());
        boolean b = generatorFacade.generatorByDataBase(db);
        if( b ){
            System.out.println("代码已生成");
        }else{
            System.out.println("代码生成失败");
        }

    }

}
