# randomname

単語をいくつかつなげてユーザ名、ハンドルネームなど名前を作るアプリ。


# URL
https://randamu-name.herokuapp.com  

ログインなしでも特定機能は使用できますが、  
アカウント名:user  
パスワード:user  
で登録なしでログインできます。

# なぜ作ったの？
プレイヤー名とかアカウント名とかをつける際に、「他人と被らない」「印象に残る」「面白い」などの条件が欲しい。  
一単語やニックネームとかだと難しいので単語をいくつかつなげることで対処してた。  
そういったサービスがないかと思い探してみたところ、ランダムな二単語をくっつけたり単語をいくつか出してくれるサービスはあった。  
しかし接続が二単語と少なかったり完全ランダムのため、使いたいイメージやテーマの単語群が出ないなど自分にとっては使いづらさがあった。  

そんなわけで  
・単語数を自分で制御出来る。  
・文字数も制限できる。  
・ある程度テーマをもった単語群でシャッフルができる。  
などを目指して作成することに。  


# 使い方

## 基本機能
トップページでいくつかの使いたいパックの「使用」ボタンを押します。  
シャッフル結果を押すと選んだパック内の単語をランダムにつなげたものが出てきます。  

## ページ説明
### トップページ
・パックと単語一覧が表示されます。  
・検索ボックスで欲しいワードがタイトルか単語リストに含まれているパックを検索できます。  
### シャッフル結果
・選んだパックから単語を適当に組み合わせて複雑な単語を作ります。  
### シャッフル詳細
・文字数、単語数の制限を設定できます。  
・使用するパックの確認や除去、パックリストのリセットなどができます。  
### ユーザ情報
・未ログインの場合、ユーザ登録やログイン画面に進みます。  
・ログイン中の場合、お気に入りパックや作製したパックの一覧を表示できます。  
### パック作成
・ログイン中の場合のみの表示です。  
・新しい単語やパックを登録できます。  

## パックについて
・「使用」を押すとシャッフルリストに追加されます。  
・「使用キャンセル」を押すとシャッフルリストから除去されます。  
・「お気に入り」を押すとお気に入りリストに入ります。  
・「お気に入り解除」を押すとお気に入りリストから除去されます。  
・「制作者」の名前を押すとその製作者が作った他のパックを見ることができます。  



# 使用技術
言語　java 16  
フレームワーク　SpringBoot 2.5.4  
セキュリティ　springsecurity  
O/Rマッパー　mybatis (mybatis-spring-boot-starter2.1.0)  
DBMS 開発,本番環境:postgreSQL 13.6  
html  
テンプレートエンジン　thymeleaf  
css  
cssフレームワーク　Bulma  
本番環境　Heroku  

# DB設計
  
![random drawio](https://user-images.githubusercontent.com/80331805/161418640-309836dd-51bc-46d7-b1e8-ae04962c76cc.svg)
  

# 課題

## 課題点（技術として）
テストを書いてない。気になることがあるたびにprint()で確認していた。  
ソフトウェアアーキテクチャについて、DDDを目指したが理解が曖昧なためディレクトリ構成に不安がある。  
presentation層のrequestとresponseが混ざってる。  


## 課題点（サービスとして）
パック使用選択後にリストの頭に戻ったりと不便な部分が残っている。  
何番目にどのパックを置くか決めれたほうがよさそう。単語として成立する品詞の順番を守れるようにしたい。  
１パック５単語じゃなくてもよかった。  
~~結果を複数表示したほうが決めやすいかもしれない。~~

など



# その他

## 作ってみて
フレームワークやライブラリのおかげで書かなければいけないものを省略しているため、  
実際に書くことになったらできないものがあるのではないかと不安になる。(DB接続でのtry-catchやMyBatisでのSqlSessionFactoryなど)

## 技術選定など

色々な物を作れるようになりたいので、使用されている範囲が広いJavaを選んだ。  

herokuを使っているのはクレジットカード登録なしでデプロイできるから。  

一度MySQLでDBを作っていたが、herokuのクレジットカード登録しないと本番環境で使えないのでpostgreSQLで書き直した。  

フロントエンドは重視していないため、cssは軽量フレームワークBulmaを用いて労力をなるべくかけないようにした。
