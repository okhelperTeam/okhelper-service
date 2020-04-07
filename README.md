# okhelper-service
#### OK帮云进销存 是一个云进销存平台，项目整体采用 Vue + Java RESTful 前后端分离架构开发，后端核心框架为 SpringBoot + MyBatis + ApacheShiro

##### 设计文档：https://github.com/okhelperTeam/supplies/blob/master/%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3.docx
##### 云端接口文档：https://coderzc.github.io/okhelperapi/doc.html
##### 系统架构图：
![系统架构图](https://github.com/okhelperTeam/supplies/blob/master/picture/OK%E5%B8%AE%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1.png)

### 注意：
* 请先将配置文件 /src/main/resources/application.eg.properties 重命名为 application.properties 并完善该文件后再运行（起不来多半是缺少配置文件或配置文件配置错误）
* okhelper.sql 是数据库建表语句，已经包含了权限、角色的基础数据，可直接使用
* 本地swagger接口文档地址：http:localhost:8080/doc.html
* 此项目是纯后端项目，前端请转至：https://github.com/okhelperTeam/okhelper-web
