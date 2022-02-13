# 書籍「後悔しないためのSpring Boot入門書：Spring 解体新書（第2版）」学習用

### Spring Boot 学習用リポジトリ

## 2章
* IDEにはSTS（Spring Tools Suite）を利用。
* Eclipseも利用可能だったけど、ターゲットがSpringだったので、こちらを採用。
* 試してみたが、意外と後入れ必要なプラグインがあって、Eclipse＋STSプラグインの方が良かったかも？
* 日本語化
  インストーラー（Electronアプリ）がうまく起動せず、手動インストール。
  SpringToolSuite4.iniの内容も修正。
* プロパティエディタ(Limy)
  LimyサイトよりEclipse用プラグインをダウンロード。解凍して、features, pluginsフォルダをSTSフォルダに上書きし、
  STSを再起動
* Thymeleaf plugin
   新規ソフトウェアのインストールで、アップデートサイトを指定。STSでも標準導入されておらず。
   有効にするも、なぜか機能せず。

## 3章
* デフォルトサンプルprojectのパッケージが書籍内容と微妙に異なる。com.example.demoになるので、それで読み替える。
* それ以外は問題なく動作確認。h2データベース利用も簡単に実現できた。

## 6章
* ValidationのOrderの使い方が難しい。部品横断で順序付けではなく、個々の部品に順序をつけたい場合はどうする？

## 8章
* MyBatisはSQL記載できるのが良い。今までごりごりSQL記載していた勢からすると、馴染みやすいとORマッパーだ思う。
* トランザクションの使いどころがサンプルから分からず。
* 分離レベルの説明があるのが良い。

## 11章
* サンプルコードのままでは、循環依存が発生したため、一部コードを修正。  
  \[循環依存内容]  
  UserDetailServiceImple  
  ↓  
  UserServiceImple  
  ↓  
  SecurityConfig  
    
  \[対応]  
  PasswordEncoderをJavaConfigクラスに移動。

## 12章

