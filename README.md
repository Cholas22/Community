## Milano Community

## 资料
[elastic](https://elasticsearch.cn)
<br>
[Spring](https://spring.io/guides)
<br>
[Spring web](https://spring.io/guides/gs/serving-web-content)
<br>
github --> deploy key
<br>
[Github Oauth文档](https://developer.github.com/apps/building-oauth-apps/)
<br>
[Spring](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

## 工具
[Git](https://git-scm.com/download)
<br>
[OkHttp](https://square.github.io/okhttp/)
<br>
[Fastjson](https://mvnrepository.com/artifact/com.alibaba/fastjson/1.2.58)
<br>
[Flyway](https://flywaydb.org/getstarted/firststeps/maven#creating-the-first-migration)
## 脚本
```sql
    create table USER
    (
    	ID INTEGER INT auto_increment primary key,
    	ACCOUNT_ID VARCHAR(100),
    	NAME VARCHAR(50),
    	TOKEN VARCHAR(36),
    	GMT_CREATE BIGINT,
    	GMT_MODIFIED BIGINT
    );
```
```bash
    mvn flyway:migrate
```
