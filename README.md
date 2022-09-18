# spring-cognito-sample

SpringBootのCognito認証認可サンプルプログラム

[IntelliJでSpringBootのWebアプリケーション開発](https://qiita.com/ykdevs/items/cb660473759937e0ee45)

## 環境変数の設定

|環境変数|説明|デフォルト|
|---|---|---|
|SPRING_PROFILES_ACTIVE|SpringBootの環境名||
|AWS_DEFAULT_REGION| リージョン名 | ap-northeast-1 |
|USER_POOL_ID|ユーザープールID||
|APP_CLIENT_ID|ユーザープールのアプリクライアントID| |
|MYDOMAIN|ユーザーぷるのアプリドメイン名| |
|ID_POOL_ID|IDプールID||


## ビルド

```shell
./gradlew build
```

## 実行

```shell
./gradlew bootRun
```