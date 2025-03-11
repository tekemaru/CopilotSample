## GitHubCopilotデモアプリケーション
このアプリケーションは、GItHubCopilotを検証するためのサンプルアプリケーションです。

## 始め方
このセクションでは、アプリケーションを動作させるためのセットアップ手順について説明します。

### 前提条件(筆者環境では以下の内容で構築しています)
- Java23
- DB:PostgreSQL(Dockerコンテナでの起動を推奨)
- 開発環境:IntelliJまたはVisualStudioCode

Visual Studio Codeで実行する場合は以下の拡張機能も必要
- ExtensionPack for Java
- Maven for Java
- Spring Boot Tool
- Extension Pack for Java Auto Config(推奨)
    - Javaのバージョン切り替えを良しなにやってくれて便利

### プロジェクトのクローン
以下のコマンドで任意のパスにリポジトリをクローンして下さい。
```bash
git clone https://github.com/tekemaru/CopilotSample.git
```

プロジェクト構造
```text
demo/
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── demo/
    │   │               ├── DemoApplication.java
    │   │               ├── api/
    │   │               │   └── EmployeeController.java
    │   │               ├── entity/
    │   │               │   └── Employee.java
    │   │               ├── repository/
    │   │               │   └── EmployeeRepository.java
    │   │               └── service/
    │   │                   ├── EmployeeService.java
    │   │                   └── EmployeeServiceImpl.java
    │   └── resources/
    │       ├── static/
    │       │   └── employee.html
    │       └── templates
    ├──test/
    │    └── DemoApplicationTest.java
    └── pom.xml
```

### DBの作成
posgreSQLの環境が整っていない方は以下のコマンドで起動する
```bash
$ docker run --name postgres -e POSTGRES_PASSWORD=password -d postgres
```

任意：A5m2などのSQL開発ツールがあればそちらから挿入するのが楽だが、ない場合は紺んて内にはいって直接SQLをたたく

起動後、以下のコマンドでコンテナの中に入る
```
docker exec -it postgres /bin/bash
```

postgreに接続
```
 psql -h localhost -U postgres -d postgre
```

DBが起動したら以下のDDLを実行する
```sql
-- DROP TABLE public.employee;

CREATE TABLE public.employee (
	employee_id serial4 NOT NULL,
	employee_name varchar(50) NULL,
	"password" varchar(255) NULL,
	salary int4 NULL,
	department varchar(50) NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);
```

テスト用のデータは以下のSQLで挿入する
```
INSERT INTO public.employee (employee_name, "password", salary, department)
VALUES 
    ('山田 太郎', 'password123', 500000, '営業部'),
    ('佐藤 次郎', 'password456', 600000, '経理部'),
    ('鈴木 花子', 'password789', 550000, '技術部'),
    ('田中 三郎', 'password101', 650000, '人事部'),
    ('高橋 美咲', 'password202', 700000, 'マーケティング部');
```

### アプリケーションの実行
開発ツールからDemoApplication.javaを実行する。
ターミナルの出力は以下の感じ
`Tomcat started on port 8080 (http) with context path '/'`といった、Tomcatが起動しているログが出ていることを確認
```
  .   ____          _            __ _ _   
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \  
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \ 
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / / 
 =========|_|==============|___/=/_/_/_/  

 :: Spring Boot ::                (v3.4.2)

2025-02-26T14:26:19.986+09:00  INFO 24832 --- [demo] [           main] com.example.demo.DemoApplication         : Starting DemoApplication using Java 23.0.2 with PID 24832 (C:\Dev\Bukai\test\demo\target\classes started by t2021012 in C:\Dev\Bukai\test\demo)
2025-02-26T14:26:19.989+09:00  INFO 24832 --- [demo] [           main] com.example.demo.DemoApplication         : No active profile set, falling back to 1 default profile: "default"
2025-02-26T14:26:20.647+09:00  INFO 24832 --- [demo] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping 
Spring Data JPA repositories in DEFAULT mode.
2025-02-26T14:26:20.738+09:00  INFO 24832 --- [demo] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 75 ms. Found 1 JPA repository interface.
2025-02-26T14:26:21.648+09:00  INFO 24832 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2025-02-26T14:26:21.668+09:00  INFO 24832 --- [demo] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2025-02-26T14:26:21.669+09:00  INFO 24832 --- [demo] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.34]
2025-02-26T14:26:21.775+09:00  INFO 24832 --- [demo] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2025-02-26T14:26:21.777+09:00  INFO 24832 --- [demo] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1685 ms
2025-02-26T14:26:21.981+09:00  INFO 24832 --- [demo] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-02-26T14:26:22.233+09:00  INFO 24832 --- [demo] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@bb25753
2025-02-26T14:26:22.236+09:00  INFO 24832 --- [demo] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-02-26T14:26:22.316+09:00  INFO 24832 --- [demo] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-02-26T14:26:22.414+09:00  INFO 24832 --- [demo] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.6.5.Final
2025-02-26T14:26:22.467+09:00  INFO 24832 --- [demo] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-02-26T14:26:22.875+09:00  INFO 24832 --- [demo] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-02-26T14:26:22.993+09:00  INFO 24832 --- [demo] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 17.3
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
2025-02-26T14:26:23.782+09:00  INFO 24832 --- [demo] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No 
JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-02-26T14:26:23.786+09:00  INFO 24832 --- [demo] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-02-26T14:26:24.835+09:00  WARN 24832 --- [demo] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-02-26T14:26:24.903+09:00  INFO 24832 --- [demo] [           main] o.s.v.b.OptionalValidatorFactoryBean     : Failed to set 
up a Bean Validation provider: jakarta.validation.NoProviderFoundException: Unable to create a Configuration, because no Jakarta Bean Validation provider could be found. Add a provider like Hibernate Validator (RI) to your classpath.
2025-02-26T14:26:25.160+09:00  WARN 24832 --- [demo] [           main] ion$DefaultTemplateResolverConfiguration : Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
2025-02-26T14:26:25.374+09:00  INFO 24832 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2025-02-26T14:26:25.384+09:00  INFO 24832 --- [demo] [           main] com.example.demo.DemoApplication         : Started DemoApplication in 6.451 seconds (process running for 6.889)
```

### アプリケーションの動作確認
- 以下のURLでSwaggerにアクセス出来ることを確認
`http://localhost:8080/swagger-ui/index.html`
- 以下のURLで社員マスタ画面が立ち上がることを確認
`http://localhost:8080/employee.html`
- 検索ボタンを押下すると、テーブルが表示されればOK

