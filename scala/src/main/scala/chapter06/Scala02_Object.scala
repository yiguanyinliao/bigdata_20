package chapter06

object Scala02_Object {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象编程 -- 包
    //  Scala的基本面向对象的语法和Java是相同的

    // 包
    // 1.声明包(起名)
    //  域名反写 + 项目名称 + 模块名称 + 程序类型的名称
    //  com.atguigu.test.user.bean
    //  com.atguigu.test.user.util
    //  c.a.t.u.util.DateUtil

    // 2.包的作用
    //  2.1 分类管理（不是必须的）
    //    如果访问不是当前包中的类，也不是java.lang,必定要引入指定包中的类
    //    com.atguigu.bean.User => BeanUser
    //    com.atguigu.bean.Emp => BeanEmp
    //  2.2 区分类（不是必须的）
    //    Java.sql.Date => SqlDate
    //    Java.util.Date => UtilDate
    //  2.3 访问权限（从来不用）
    //    Java 默认的访问权限就是包权限
    //  2.4 编译类，类的源码程序所在的物理路径应该和包名保持一致（可以改善）
    // Java的语法中，package包语法的作用没有那么强大
    // Scala语言对package的语法进行了补充，让package的语法变的更加强大



  }

}

