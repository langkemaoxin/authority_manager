package com.imooc.project;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");

        gc.setFileOverride(true);

        gc.setAuthor("rexchen");

        //生成文件后是否打开文件夹
        gc.setOpen(false);

        //设置使用   java.util.date的时间
        gc.setDateType(DateType.ONLY_DATE);

        //如果这里不经过设置，则service包名的名字为IxxxService,
        //即头部带有 字母Id
        gc.setServiceName("%sService");

        //实体属性 Swagger2 注解，不需要手动添加注解，自动就添加了
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        /**
         * com.mysql.cj.jdbc.Driver是mysql-connector-java 6 中的特性，
         * 相比mysql-connector-java 5 多了一个时区：serverTimezone，
         * 把数据源配置的驱动改一下就好了
         *
         */

        dsc.setUrl("jdbc:mysql://localhost:3306/project?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();

        //设置一个父路径
        pc.setParent("com.imooc");

        //如果是有多个项目的情况下，可以设置多个目录
        pc.setModuleName(scanner("模块名"));

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        //如果这段代码打开了，每次生成时，都重新创建
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            return true;
        });


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);

        //不能设置为json
        strategy.setRestControllerStyle(false);

        if(scanner("是否继承公共字段").equalsIgnoreCase("y")){
            // 公共父类,我们在模型中，抽取出了公共父类，所以我们在这里定义好公共父类
            strategy.setSuperEntityClass("com.imooc.project.entity.BaseEntity");
            strategy.setSuperEntityColumns("create_time,modified_time,create_account_id,modified_account_id,deleted");
        }


        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));

        //是否是驼峰的
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");


        //设置逻辑删除字段
        strategy.setLogicDeleteFieldName("deleted");

        //设置自动填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill modifiedTime = new TableFill("modified_time", FieldFill.UPDATE);
        TableFill createAccountId = new TableFill("create_account_id", FieldFill.INSERT);
        TableFill modifiedAccountId = new TableFill("modified_account_id", FieldFill.UPDATE);

        tableFills.add(createTime);
        tableFills.add(modifiedTime);
        tableFills.add(createAccountId);
        tableFills.add(modifiedAccountId);
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}