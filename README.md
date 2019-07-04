# SwaggerAPIManagement-HUAWEI

*API management system based on Swagger2* 
`USTC工程实践项目`
<br/>
<br/>

## 1. 注意事项
为了美观，可以在本地的hosts文件中添加一条url到域名的映射关系。
~~~
CAS登录页面：localhost:9100/cas/login
APIMS页面：localhost:8889/index
SwaggerUI原生页面：localhost:8889/swagger-ui.html
~~~
该项目的CAS Server是在本机配置的Tomcat-CAS中，建议修改配置如下：
~~~xml
<!--1.修改端口号-->
<!--(1) tomcat-config-service.xml中修改为9100-->
<!--(2) cas-WEB-INF-cas.properties中修改为9100-->

<!--2.去除https认证，避开SSL证书-->
<!--(1) cas -> WEB-INF -> deployerConfigContext.xml中修改-->
<bean id="proxyAuthenticationHandler" class="org.jasig.cas.authentication.handler.support.HttpBasedServiceCredentialsAuthenticationHandler" p:httpClient-ref="httpClient" p:requireSecure="false"/>

<!--(2) cas -> WEB-INF -> spring-configuration -> ticketGrantingTicketCookieGenerator.xml中修改-->
<!--(3) cas -> WEB-INF -> spring-configuration -> warnCookieGenerator.xml中修改-->
<bean id="" class="" p:cookieSecure="false" p:cookieMaxAge="3600" p:cookieName="CASTGC" p:cookiePath="/cas" />
<!-- cookie有效时间为3600s -->

<!--3.允许退出登录后重定向到其他页面-->
<bean id="logoutAction" class="org.jasig.cas.web.flow.LogoutAction" p:servicesManager-ref="servicesManager" p:followServiceRedirects="${cas.logout.followServiceRedirects:true}"/>
~~~
<br/>
<br/>

## 2. 导入项目到本地
建议直接下载zip包，在Intellij中直接open pom.xml as project。     
[参考教程](https://blog.csdn.net/hry2015/article/details/77984399)
<br/>
<br/>

## 3. 在本地的项目目录下建立git管理
预备工作，依次输入：（**以下操作均要在项目文件夹下完成，其中${} 部分为需要你修改代入的地方**）
~~~
$ git config --global user.name "${你的名称}"    #设置你的信息
$ git config --global user.email ${你的邮箱}
$ git init                                      #对这个项目初始化git管理
$ git checkout -b ${你的分支名}                  #在本地创建属于你的分支
$ git remote add origin https://github.com/MatthewHuangs/SwaggerAPIManagement-HUAWEI.git    #设置远程仓库，名叫origin
$ git fetch origin ${GitHub上你的分支名}         #将GitHub上你的分支fetch到本地
$ git branch --set-upstream-to=origin/${GitHub上你的分支名} ${你的分支名}    #将GitHub你的分支与你本地的分支匹配，确保一定要成功才能做代码提交操作，不然会篡改GitHub上的master分支
~~~
<br/>

代码上传操作
~~~
$ git add .                       #将你的代码提交都缓存区
$ git commit -m "${描述信息}"      #提交你的代码到本地仓库
$ git push origin ${你的分支名}    #第一次上传代码时，在最后加上-f，强行上传避免merge你的分支这一麻烦操作
~~~
<br/>
<br/>

[comment]: 注释部分
 
[项目的github地址](https://github.com/MatthewHuangs/SwaggerAPIManagement-HUAWEI)
