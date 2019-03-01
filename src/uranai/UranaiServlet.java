package uranai;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class UranaiServlet
 */
@WebServlet("/uranai/UranaiServlet")
public class UranaiServlet extends HttpServlet {



	//20181107に書き換えました
    public UranaiServlet() {
        super();

    }


  //星座の名前リスト
  	public static String[] sta={
  	"おひつじ座","おうし座","ふたご座","かに座",
  	"しし座","おとめ座","てんびん座","さそり座",
  	"いて座","やぎ座","みずがめ座","うお座","へびつかい座"};

  	//表示する占いメッセージリスト
  	public static final String[] mes={"今日は良い1日になるでしょう！",
  	"今日は見学会でうまくプログラムが動かないかもしれない日です。ちょっとしたうちまちがいや、ファイルの置き場所のチェックを忘れないようにしましょう。","道で出会った猫に声をかけると恋愛運アップ！","うっかりミスに注意。出かける前は持ち物確認！","衝動買いも今日は吉(`･ω･')","映画館行くべからず。観たいものがみれません","いちごのお菓子を食べると吉","MINTIAを食べると吉","都庁前で転倒注意","授業で出てきた単語を３回唱えると良い事ありそう","パスポートを申請しにいくとラッキー","購買でソイジョイがもらえるかも","８月末に公開される２０世紀少年が観たいですよね","お昼ごはんをソイジョイにすると１ヶ月１キロ弱減りそう","授業で使ったプリントをまとめると頭がよくなりそう","今日は衝動買いは控えるべし","お菓子に注意。その一口がデブの素",	"臨時収入の予感ですよ！","道端で可愛い猫と素敵なであいそう",	"幸せになるための２７のドレス観たいよね",	"茶トラの猫を見れたら幸せな気分になれそう","クリーム玄米ブランを食べると元気が出そう",	"教科書を朗読すると頭がよくなりそう","何となく気がゆるんだり、注意力が散漫になってしまい、ミスが多くなる時なので、慎重な言動を心がけ、周囲に対する気配りを忘れないよう心がけてください。また、親しい人や知人が、あなたの将来や今後の進路に関する良い話を持ってきてくれそうです。興味や関心がないからといって無視してしまわず、話くらいはきちんと聞いてみた方が良いでしょう。思がけない有益な情報が得られるはずです。","交際範囲が変化し、人間関係が崩れてしまいそうな日です。一方的に自慢話をしたり、人の悪口を言ってしまい失敗するような暗示も出ています。ちょっとした一言が思わぬ展開になり、あなたの信用を損ねることにもなりかねないので、十分注意してください。新しい出会いの中には、あなたに幸運をもたらしてくれる人も現れそうです。話の輪にはできるだけ積極的に参加し、自分をアピールすることを忘れないようにしましょう。","一つの目標を持つことによって大きな幸運がもたらされる日でしょう。また、周囲の人たちと一致団結できそうな日です。そうした過程で、いつも近くにいる人の今まで気づかなかった長所を発見し、再評価することもあるでしょう。また、友人や家族の誕生日や記念日に、パーティーを企画するなどすれば、より親密なつき合いを続けていけます。逆に、家族と友人との間でトラブルが起こると、辛い思いをすることもありそうですので、注意してください。","あなたの心の不安定さが、対人関係に陰を落としそうです。今、あなたは人間関係でのストレスがたまりやすく、精神状態があまりよくない様子です。そんな状態が、人と接する場面でふと出てきてしまい、敬遠されてしまったり、病人のように応対されたりなど、思った以上に心配されてしまうことがあるかもしれません。周囲に余計な心配をさせないためにも、あなたが一番親しいと思う友人には、悩みや不安を打ち明けておくとよいでしょう。","勘がさえ、面白いアイデアがひらめきそうな日です。筋道を立てて自分のアイデアを発表することができれば、あなたの評価もアップし、周囲からの期待も高まるでしょう。人間関係面でも関心のある人にアプローチするなら積極的に出てOK。ただし、出しゃばった態度は控えるようにしてください。","周囲の協力やバックアップが期待できる日です。ピンチに陥っても必ず誰かが助けてくれるので、思い切って大きなことにチャレンジしてみるのも良いでしょう。やるべきことが山積みになっている人は、友人にヘルプサインを出してみると、快く援助してくれそうです。独力で物事を進めるより、周囲との協力体制を第一に考えてみてください。","今日のあなたは、なんとなく周囲と波長が合わない一日を過ごすことになりそうです。無理をしても、周囲の人にはおそらくお見通しであるばかりか、かえって感じ悪く映るので、なるべく一人で行動した方がいいかもしれません。せっかくの機会ですから、じっくりと考え事でもしてみたらどうでしょう？遊びの誘いも、今日は断ったほうが無難です。 ","今日のあなたは、なぜか悲観的な考えに支配されてしまいそう。周りの人がうわさ話をしていようものなら自分に違いない、と思いつめてしまったり、新しい役割を与えられても、どうせできっこないとやる前から決めつけてしまったり……とにかく後ろ向きになってしまいます。今日は一日そんな調子。次の休みに海に行く計画をたてるなど、気分転換に努めたほうがいいかもしれません。","落ち着いている判断力を持って、客観的に物事を見ることができます。そして学んだ知識を利用して人類に幸福をもたらすことができるでしょう。ただ、人となりは一部生気がなくて、融通性に不足して、頑固にしかもあまりにも慎重に優柔不断なことと、時にはたやすく手に入るチャンスを逃しまうことがあります。気をつけましょう。","何にも屈しない精神と耐久力を持って、高い責任感驚異的な長い間続く力があります。たとえ枝葉末節の体験と知識だとしても、同じく努力して獲得することができます。その反面、今日は人生に直面してしまうこともありますが、焦りすぎないでくださいね。","一日ハッピーな気分でいられそう。自分から誰かの手伝いをすればもっと運気はアップします。恋愛運も良好なので何か出会いがあるかも？でもうかれるのは危険。調子に乗って口が滑ると思わぬ結果を生むことになります。金運はそこそこ。今ならきっとBIGで６億当たりますよ。早速１０万円分買いに行こう！健康運もそこそこ。やりたいスポーツがあれば思い切ってやってみよう！","人生最悪の日。今日は外に出ないほうがいいかも。あなたが外にでた瞬間台風がやってきます。もうほんと存在自体が間違ってるんじゃね？大人しくしとくといいよ。恋愛運は最悪。意中の人に「ちょっと近づかないでくれる？」とか言われそう。金運も最悪で、自己破産必至！健康運なんて・・・外にでた瞬間事故ると思ったほうがいいよ。TV見たら貞子が出てくる。蛇口開けたら血が出てくる。布団に入ったら中に変な怨霊がいる。諦めましょう☆","目に映るもの、耳にするもの全てに、チャレンジしたくなるアクティブな日。ためらわずに、どんどん実行すれば、夢中になれそう。出会い運が絶好調。恋愛対象としてでも、友人としてでも、あなたの期待に応えてくれる相手を紹介されそう。初対面で意気投合。好きな人が困っているところを、あなたがばっちりサポートして、一気に接近できる予感。素の自分を見せられる関係に進展しそう。","好きな人と、趣味の話題で話が盛り上がり、デートの申し出があるかも。普通なら引きそうなくらいディープな話題でも、問題なし。友達との会話の中で、役に立つ情報をゲット。望みを叶える、最短の方法を教えてもらえるかも。デートスポットは口コミで探して。ストレス発散をしたいなら、気の合う仲間を誘って、思い切り騒げるパーティーを。愚痴は言わずに、楽しいことだけ考えるとグッド。","周りの人に尋ねてもわからないことは、インターネットで検索を。パソコンが使いこなせない上司から、感謝されてポイントアップ。中途半端にすることはバツ。一度始めたことは、最後まで責任を持って取り組むこと。やり遂げた分だけ、経験値が上がりそう。自慢話ばかりしていると、いくら仲良しの相手でも、飽きられてしまいそうです。話し上手よりも、聞き上手になることを意識して。","忘れ物に注意、家を出る前にバッグの中身を確認して。翌日の荷物は前日の夜にしっかり整えておくのが吉。週末には普段行けなかった所に思い切っていってみよう。健康運は下り気味、休み明けは特に注意。ちょっとしたことから人間関係がこじれそう。仲直りするのはしばらく時間がかかりそう、あわてないで。","余計な出費が発生しそう、ぐっとこらえて無用な衝動買いは気をつけて。財布に大金を入れておくと誘惑に負けてしまいそう。部屋の大掃除をしてみると、無くした物が見つかる予感。夜間の長電話に注意、隣人とのトラブルが発生するかも。作業をする時にクラシック音楽をかけると普段より効率アップするかも。","交友関係が活発になりそうな一日です。友人や、先輩、また会社関係の人などからもお誘いがあるかもしれません。用事がない限り積極的に参加するようにしましょう。また、その席であなたにプラスになる人を紹介されるかもしれません。きっと後々あなたに大きなメリットを与えてくれる人脈づくりが出来るでしょう。また、ちょっとした旅行に誘われるかもしれません。予定が特になければ、ぜひいい返事をしましょう。","友人との関係がスムーズにいく日です。これまで以上に友情が深まることもあるでしょう。ただし、この日、秘密を打ち明けるようなことは避けたほうがいいようです。約束事は守ってもらえない可能性が高そうです。逆に、相手の相談に乗ってあげたり、力を貸してあげたりということによって、あなたに幸運が訪れることになるでしょう。また、趣味の合う友人と何かのサークルに入ってみるのもいいようです。きっと充実した楽しい時間が過ごせるはずです。","今日は、新しいことを学ぶのに絶好の日かも！？少しでも興味があるものには手を出してみよう！ただし、わからないことをそのままに進めるのはダメ。ちょっとでも躓いたら、知ってる人に聞いて解決してしまおう！きっと、そこから新しい発見の糸口を掴めそう。その学んだことや糸口が将来にきっと役立つよ！頑張ってみてね。","午後からは外に出ない方が得策かも。家でゆっくり体を休めるチャンスだと思って、大人しくしてみては？もし、外に出る必要があるなら何処かの喫茶店に寄って、一息付いてみることが大切だよ。絶対に焦っての行動は禁物！もちろん、今日中にしなくていいことなら、明日に回してしまってね！明日になったらすぐ行動に移そう。","今日はちょっと遠出や知らない場所に行って、新しい刺激を受けるといいかもしれない。今まで、悩んでいたことや考えていたことの答えが出る切欠になるよ。無い人は、新しい思いつきが生まれる切欠が出来るかも！そして、それを何かに残しておくと将来に役立つよ。毎日同じことばかりじゃなくて、些細な刺激も大切なんだよ。","ちょっとした気の緩みがあとあと取り返しのつかない事になるかも。場合によっては、人生を棒に振るかもしれないよ。目標をクリアに。やるべき事に力を入れて、優しさと思いやりを持って早めに話し合おう。そして、長い目で物事を見つめて挨拶をきちんとしましょう。体より心の不調が心配。気持ちを落ち着かせて。でも、金銭面は困らなそう。高い利益は望めないけど。","たくさんの新しい出会いが待ってるかも。恥ずかしがらずに思いっきり、始めからハイテンションで楽しんで行こう。人によっては、癖になるかもしれないけどそれは、良い予兆だよ。でも、体には、気を付けてね。何をするにも健康第一！！遅寝遅起きで運気アップ。金銭的にも問題ないかも。波に乗れば異性に気に入られて、何でも奢ってくれるかも。","家にいる時間を普段より長く持ったり、一人暮らしの人は実家に電話するなどして、家族との絆を深めるのには良い日です。本や映画の話で盛り上がったりして、楽しい雰囲気の中、家族全員の心がやすらぐことができるでしょう。また、日頃の悩みを相談してみると、的確なアドバイスを得ることもできそうです。家族以外の人に対しても持ち前のサービス精神を発揮して、積極的に声をかけてみると良いでしょう。新しい出会いが期待できそうです。","下卑にまみれ夜な夜な札束を数える貴方の顔と、餓えに苦しむ人々の様子が見えます。昨日の貴方は、他人の意見を尊重せずにワガママを押し通したりしませんでしたか？それは例え小さな事だったとしても、相手には大きなショックとして伝わってしまったみたい……。家族や恋人、友達の前の態度を改めて考えて直してみましょう。","貴方は今までに食べた食パンの数を覚えていますか？取るに足らない事だと高を括っているなら、今日は外に出るのは得策とは言えません。過去の小さな積み重ねに足元を掬われてしまいそうです。外に出ると赤信号を無視した回数分、貴方に不幸が訪れます……。ラッキーアイテムをポケットに詰め込んで、今日を乗り切って下さい。","欲しいものより、必要なものをと星が告げています。自分の自由になるお金が確保できなくてもよしとして。欠けているものの補充をするのに、金銭的に苦労をすることはないでしょう。うっとうしい時期ならではの衛生管理に気をつけること。家事の中では清掃を最優先にして、トイレタリーエリアを中心に、通気のよい環境づくりを。","見栄や世間体などから、強引に欲しいものを購入したがる傾向強し。金欠状態で危うしですが、今期はバイタリティ満々。帳尻合わせを成功させることが頼みのつな！免疫力・抵抗力には自信を持ってOK。しかし注意力散漫な面があなどれず、うっかりミスによるケガなどが心配。中途ハンパに体力づくりをするくらいなら、いっそお休みして。 ","うわぁ転んじまった、「いてー」なんて寒いギャグには御用心。今のあなたの地位を追われかねないぞ。林家ぺーのことが好きでも、パー子のほうが好きなのなどといって話をはぐらかしてしまおう。ラッキーワードはフニフニフニフニ。誰かに会ったらさりげなく会話に織りまぜてみよう。樹木希林のことを話題にすれば、結構スムーズに話ができるわ！あいさつがわりに口走ってみるのもいいかも。これで恋愛運もアップ。意中に人には作詞が阿久悠だったってことも教えてあげて！","今日はちょっと自信がないので、あまり占いたくないのですが、一応やってみます。全体的には良さそうですね。恋愛運がピークを迎えます。メスのシェパードに気に入られるでしょう。ただ、この恋は長続きしません。飼い主の猛反対にあいます。まあ、そんなとこですな。","あなたは、一見おとなしそうに見えても、実は人並みはずれた好奇心と冒険心を持っています。何に対しても興味を抱くので、意外な趣味を持つことも多いです。オリジナリティを求められる職業に向いているでしょう。そのちょっと予測不能な性格とはうらはらに、周囲に対する面倒見は意外とよく、義理人情を大切にするお人よし。断れない性格なので物事を頼まれやすい所があります。","あなたは、人と違ったことをしたい、奇抜なタイプです。自然とマイナー嗜好になり、誰も知らない分野のことにカナリ詳しかったりします。内面は誠実なのですが、普段の行動から誤解をされることはしばしば。それでもあなた自身は、誤解されても仕方ないか、と考えているフシもあります。異性に対しては、自分と違った、ごく普通の相手に惹かれます。","気配り、根回し、サポートなどの縁の下の力持ち的な役割に幸運が眠っていそうな今月は、あなたの優しさが最大限に発揮されそう。地味だけど「できる人」を目指して頑張ろう ! 恋は華やかに ! 想いが伝わりやすい時なのでチャンス。バンソウコウを指に巻くとお守りに。","今月は職場や学校での目標や野心が大きくふくらみそう。ついついエネルギッシュになって他人と争い競う傾向に ? でもまずは自分のスキルを磨くことから頑張ってみて。恋は知的かつアクティブな雰囲気。ちょっとフォーマルな雰囲気のお店で大人の時間を楽しもう。","今月は後回しにしていたことにきちんと向き合っていくべき。地道な作業の中に新しいひらめきが隠れていそう ! クリエイティブなことにもパワーを発揮できるので企画案などどんどん提出しよう。恋は楽しむことを一番に♪隠れ家的なお店でまったりしたデートを。 ","あなたが今まで、しっかりと目的を持ち、地道な努力を続けてきたなら、社会的な成功に手が届く日でしょう。隠れていた才能も力をどんどん発揮していくかも。最大限にチャンスを有効利用し目標を遂げていきましょう。遊び心を発揮してみてもいいかも。話題が広がり楽しい一日なるかも。話の輪にはできるだけ積極的に参加し、自分をアピールすることを忘れないようにしましょう。","目標を持つことによって大きな幸運がもたらされる日でしょう。周囲の人たちと一致団結できそうな日です。そうした過程で、いつも近くにいる人の今まで気づかなかった長所を発見し、再評価することもあるでしょう。また、お得な情報が、あなたのすぐそばに横たわっている日。しかし一見するとあまりに当然のことで、聞き逃すことになりがちになります。注意をしていないとチャンスをうまく手中に収められません。","周囲があなたの一生懸命がんばる姿を認め、好印象を持ってくれるでしょう。目上の人からもこの上ない評判を得られるでしょう。特にマスメディア関係にアンテナを張っておくと、ラッキーな情報がひっかかりそうです。持てる力を１２０％出してください。仕事も効率よくこなしていけるでしょう。また、友人との付き合いを緊密にしておくと、欲しかったものや有益な情報が、突然手に入ることもあるでしょう。","心身共にパワー不足といえそう。全身がだるかったり、特に午後に入ると眠気に襲われてしまいそうです。今日は、なるべく無理しないようにして、早めに家に帰ったほうが賢明ですだと思います。周囲とのコミュニケーションも上手くかみ合わない日なので、タイミングが合わないと喧嘩にまで発展する恐れがあります、気をつけてください。","人との出会いが大きな幸運を運んでくれるけど、それは偶然によるものです。あえて行動に出る必要はないので、自然に待っていましょう。また、家族や親戚から嬉しいニュースや紹介もありそうで、人との係わり合いの中に幸運を見出せますよ。家族のコネが大きな成功をもたらしてくれるなんてことがあるかも。","思ってもいなかった役割を任され緊張してしまうかも。今後の信頼関係にもつながっているからミスのないように。もしくは、これまで避けて来た事柄に巻き込まれてしまう暗示も出ているよ。ここで腹を決め、誠実な態度で臨まなければ、今後に悪影響を及ぼすかもしれないね","あまり周囲との仲が上手くいかないような一日です。あなたが何気なく口にした一言が大きな問題になってしまい、人間性までもが問われるようなことにもなりかねないので注意が必要です。特に、出しゃばりや知ったかぶりの言動は、周囲の人に、さらにあなたの悪印象を植えつけてしまうことになりそうですので気をつけましょう。控えめな気持ちで過ごすことが必要な一日になりそうです。","新たな出会いのチャンスの日です。今まで以上に多くの人と巡り合うことができ、新しいタイプの友人と知り合うことができるでしょう。お互いの気持ちを思いやり、信頼関係も築いていけそうな相手との出会いの予感があります。今日の人間関係は好調そのものですので、積極的に行動することを心掛けてみて下さい。また、思わぬ出来事が起こる予感もあります。","自分の将来や人生について、急に深く考えるようになる日です。また、精神的なことに急に興味を覚えるようになったり、理屈では説明できない不思議な体験をしたりするかもしれません。また、大事なことは後まわしにせず、この日に終わらせてしまうのが幸運を招くでしょう。この日の決心に間違いはないようですので、思い切りよく決断するようにして下さい。","頭が良く、とても好奇心が旺盛なあなた。もともといろんなことを「知りたい」という欲求が人一倍強くて、さまざまなことをみずから体験したいと考えるようですね。ちょっと危なさそうなことにも進んで身を投じていくので、若いうちから多くの経験を積みやすく、結果として早熟になりやすいようです。あまり自分の身近にはいない異業種や異文化のひとたちに興味を持つのもその好奇心の顕れで、多くのことを知りたいがために、自然と社交的になり、さまざまな身分の人たちとつきあうことになるでしょう。","親しい人から大きなサポートが得られそうな日です。突然、お金を渡されて戸惑うことがあるかも知れませんが、深い意味はなく、あなたを援助したいという純粋な気持ちからのことなので、心配はいりません。また、そうした人からおすそ分けに預かるようなこともありそうです。人間関係においては、何でも言い合えるオープンな付き合いを大切にしていってください。あなたが積極的に好意を示していけば、お互いの心を和ませる良い関係が築いていけるでしょう。","一つの目標に向かって、周囲のみんなが団結するようなことがある日でしょう。自分にないものを持っている人を見つけて、素晴らしさを再認識することもありそうです。また、家族や友人の誕生日や記念日が近づいていたら、パーティーの計画を立ててみましょう。雰囲気が良くなること間違いなしです。","お得な情報が、あなたのすぐそばに横たわっている日なのですが、一見するとあまりに当然のことで、聞き逃すことになりがちでしょう。よく注意を傾けていないと、チャンスをうまく手中に収められません。あなたの感受性にゆだねられているのです。人間関係では、これまで良い付き合いだったとは言い難い相手と、接触することになるでしょうが、言葉のすれ違いや悪いイメージなどの誤解を晴晴らすべく、全力を傾けるほかありません。","スケジュールや約束事の管理がいい加減になりがちな日です。再度予定を確認して、無理な時間配分ではないか、見落としてしまっている約束はないか、などをチェックしてみてください。あなたが周囲に迷惑をかけているのに、気付かないでいるようなことがあれば、目の前までやってきていた幸運をつかみ損ねてしまうことになるでしょう。行動に余裕を持つことが、幸運を招きます。","何ごとも自分一人だけの力では先に進まない日です。今日のところは、周囲に力を貸してもらいましょう。もしも、あなたが今まで周囲に対して協力することを拒んできたのなら、あなたに協力してくれる人は現れないかもしれません。しかし、それも当然の報いですから反省することです。仕事面では、波乱の暗示があります。所属部課やプロジェクトチームの中で意見が対立し、当然のごとく渦中に引きずり込まれてしまうでしょう。","新しい出会いがありそうな日です。積極的に声をかけていき、交友を広げていくとよいでしょう。「私、人見知りなの」という人はとりあえずニコニコして、私は敵じゃないですよオーラを出せばOKです。突然、知らない人に物を貰って困ることがあるかも知れませんが、深く考えずにありがたく受け取りましょう。それはいずれあなたの宝物となるものです。きっと。","なにかと不安になる日です。朝家を出た後に、鍵をかけたかどうか忘れて気になったりするでしょう。でも以外にしっかりかけているので大丈夫です。そのまま出かけましょう。用事を済ませて帰ってきたら、家の中の物の配置が変わっているかも知れません。しかし鍵もかかっていましたし、気のせいです。問題ありません。ただ、もし窓が開いていることに気づいたとしたら、ガタンと音がした寝室の方を覗いてみるべきです。きっとあなたに転機が訪れるでしょう。","ちょっといいことがある日です。朝食はしっかり取りましょう。朝はご飯がベストですが、調子に乗って納豆を食べようと思ってはいけません。味付け海苔にしておきましょう。海苔の香りがあなたを幸せな気分にします。昼食はセブンイレブンで十分でしょう。くじを引いてうまくいけばロイヤルミルクティーが当たりますよ。学校や職場で嫌なことがあったら、夕食は豪華にカレーにお肉を入れてみましょう。最高の一日だったと思えるはずです。","何となく気がゆるんだり、注意力が散漫になってしまい、ミスが多くなる時なので、慎重な言動を心がけ、周囲に対する気配りを忘れないよう心がけてください。また、親しい人や知人が、あなたの将来や今後の進路に関する良い話を持ってきてくれそうです。興味や関心がないからといって無視してしまわず、話くらいはきちんと聞いてみた方が良いでしょう。思がけない有益な情報が得られるはずです。","思いもよらないくらい簡単に、あなたの望みが実現しそうな日です。しかし、どんなことでも自分の思い通りになってしまうかのように、錯覚してはいけません。たまたまツキがあっただけのことで、世の中そう甘くはないのです。楽することを考えてばかりで、努力することに怠慢にならないよう注意しましょう。人間関係は調子よく発展し、あなたが協力を仰げば、必ず助け舟を出してくれる人が周囲にいてくれるはずです。","今日は恋愛関係を始め、多方面で大きな発展が期待できる時です。好意を寄せている相手にアピールする絶好のチャンスですから、告白やデートの申し込みをしてみましょう。また、複数の異性と出会う機会がありそうです。特に古くから親しくしている異性に注目してみましょう。きっと大きな幸運をもたらしてくれるはずです。この日は、できるだけ多くの人と会話することで、相手の良い面を吸収することができます。","出だしは好調ですが、時間がたつにつれ集中力がダウンしてつまらないミスを連発してしまいそうな一日です。大切なやりとりや作業は早い時間に片づけたほうが賢明でしょう。夜の行動は能率が上がらないばかりか、体力を消耗するばかりなので、早めに切り上げてゆっくり身体を休め、集中力を取り戻すことに努めてください。","両親や日頃お世話になっている年長者にさりげなく感謝の気持ちを示すとラッキーなことがありそうです。ご無沙汰している恩師などには電話や手紙で連絡してみると喜ばれるでしょう。また、すれ違いが多くなっている友人とのコミュニケーションも大切です。直接会えない場合も、電話やメールなどで連絡をとってみてください。","朝から好調な一日で、通学や通勤電車、バスの中で憧れの人と隣り合わせになったり、理想の人と出会うなど嬉しいハプニングがありそうです。絶好のチャンスなので、偶然を装って話しかけてみるとよいでしょう。勘も冴えているので、以前から計画していたことがあれば、実行に移す良い機会と言えそうです。","今日は、人から学ぶこと多い一日。人の振り見て我が振り直すという言葉もあり。人の失敗なども笑ってばかりいないで、自分もそれから得るもの学ぶことは多いはず。特に転寝危険！！","今日は、運気低めで協力、援助得られにくい一日。共に足並み揃えてやらなければならないことは日を改めたほうが良さそう。,出来ればきょうは単独で行動を。黙々と淡々と仕事に励んで問題なし。特に長い待ち合わせ危険！！","今日は、運気乱れて心落ち着かず。小さなことを大げさに騒ぎ立ててしまう傾向が。大山鳴動してネズミ一匹にならないよう気をつけて。問題は一度に解決しようと焦らないこと。特に走るのは危険！！","今日の運勢は絶好調です。なにをやってもたいていのことはうまくいくので、自分の可能性を信じていろんなことに挑戦しましょう。金運は最高です。東中野周辺を散策すると2億円の宝くじが拾えるかもしれません。あるいは何かいいことをすると人から謝礼をもらえるかもしれません。でもあんまり調子にのって金を使っていると、全財産を失いかねないので注意しましょう。金の招き猫を持ち歩いていると、金運がさらにアップします。恋愛運も最高です。気になる人がいるなら勇気を出して行動すれば、よりいい関係になれるかもしれないです。明るい服を着ていれば、誰からも好感を得ることができるでしょう。逆に暗い感じでいると、あまりよく思われなくなりがちなので気をつけましょう。携帯に面白いストラップをつけていると、いい出会いがあるかもしれません。しかし、あせって行動するとよくない展開になることもあります、慎重かつ迅速に行動しましょう。勉強運も最高です。どんな問題でもすらすら解けていくでしょう、今日一日はかなり頭が冴えていい判断ができそうです。多少の問題があってもよく考えれば解決に導くことができるでしょう。ただし、頭を使いすぎると反動で明日以降馬鹿になる可能性があるので、気をつけましょう。健康運も絶好調です。健全な生活を送っていれば、心身ともに健康でいられるでしょう。また現在何か不調があれば、今日あっさり治ってしまうかもしれません。毎日健康に気をつけるのもいいかもしれません。","今日はひらめきが冴える日です。悩みや不安があっても自分の勘を信じて行動すれば、それなりにいいことがあるでしょう。金運は最悪です。一人で夜出歩くと誰かに財布の中身を全部盗られます。恋愛運は普通です。普通にしていればたぶんなんかあります、新鮮なナスを持っているとフラグが立ちます。食べながら歩きましょう。勉強運は最高です。ですが、モンハンのやりすぎには注意しましょう！！健康運も最高です。今日は何をやっても病気にかかりません、明日以降は知りません。","今日のあなたはとても不運かも、あまり外出はしないで自宅に引きこもるのがベストかも、もし外に出たいなら帽子とマフラーを忘れずに、帽子で不運を払いのけてマフラーで幸運を引き寄せよう、帽子とマフラーの色を合わせるともっと運気がUP！時期が冬じゃないなら、マフラーも変だから運気が少し下がるけどもマフラーの代わりにスカーフでも大丈夫！帽子はなるべく軽いやつを、これであなたも大丈夫！","今日のあなたはとても危険な一日になってしまうかも、周りには十分注意しよう！特に電車関係で！もしも行く予定があるのならお腹のとこジャンプを仕込もう、それか防護服でも大丈夫！常に自分の周りに注意をはらって行動しよう、それが運気上昇の秘訣かも。人混みに入る時は自分以外の人を連れていければ問題が起こりにくいかも。","今日のあなたはとてもいいことが起こるからとてもいい日になりそう、今日はみんなに明るく振舞って自分だけじゃなく周りのみんなも明るくすればあなたも周りのみんなも運気が上昇！金色の腕時計をすればもっと運気上昇！普段苦手な人とも仲良くすれば関係も向上し、今後も仲良くやっていけるかも！金色の腕時計はあまり人に見せ付けないように！"};

  	//表示するラッキーアイテムのリスト
  	public static final String[] lui = {"メモ帳",
  	"ニンテンドーDS",	"黄色い傘","白い百合の花","青い携帯ストラップ","本や教科書類","緑色のもの","紙とペン","くしゃくしゃのピン札","携帯電話のアンテナ","のど飴","腕時計","蛍光ペン","米","おでこが広い人","天然パーマの人","パン","お菓子","絵葉書","怪人X","くびもげ太","そんなものでどうにかなるほど甘くない","PSP","過去の栄光","未来への希望","今を生き抜く心意気","2000円札","ミネラルウォーター","バームクーヘン","LANケーブル","携帯電話","カメラ","時計","4000円のユンケル","トーテムポールのキーホルダー","シャーペン","マウス","マウスパッド","ボールペン","カフェラテ","紫色のTシャツ","携帯電話","青いメガネケース","半ズボン","ネクタイ","しおり","金属バット","もやし","スケジュール帳","ストラップがついてない携帯電話","太ったネコ","扇風機","アンティークグッズ","ティーカップ","アイスティー","アイスコーヒー","コーラ","ミント系のガム","グミ","のど飴","銀のアクセサリーです。ネックレスやリングを身につけていると悪運から守ってくれるでしょう。","ムジュラの仮面","週刊少年誌","金色物","マフラーと帽子"
  	};

  	//占いの初期画面から送られる「POST」という命令を受け取ったときの処理
  	public void doPost(HttpServletRequest request,		HttpServletResponse response)
  		throws ServletException, IOException {

		//ブラウザから届くデータの文字コードの設定
		request.setCharacterEncoding("Windows-31J");

		//ブラウザに送り返すデータのタイプや文字コードの設定
		response.setContentType("text/html; charset=Windows-31J");


		//出力するための機能の呼び出し。
		PrintWriter out = response.getWriter();

		//送られてきた星座の値を受け取る
		String seiza = request.getParameter("seiza");

		//星座の値を処理のために数値にする
		int s = Integer.parseInt(seiza);

		//今日の日付を出す処理。Dateという日付に関する機能を利用
		Date date1 = new Date();

		//日付のデータを計算で使うために数字にする。
		SimpleDateFormat today = new SimpleDateFormat("yyMMdd");
		int i = Integer.parseInt(today.format(date1));

		//メッセージ:番号と、ラッキーアイテム番号を出す処理。
		int u = (i+s)%mes.length;
		int lu =   (i+s+15)%lui.length;

		//メッセージリストからメッセージを取り出す。
		String message = mes[u];

		//ラッキーアイテムリストからラッキーアイテムを取り出す。
		String li = lui[lu];

		//星座リストから星座を取り出す。
		String star = sta[s];


		out.print("<html><body><center>");
		out.print("<img src=\"img/" + s + ".jpg\">");
		out.print(" <table bgcolor=\"lightblue\" cellspacing=\"5\" cellpadding=\"5\" width=\"500\" height=\"250\">");
		out.print("<tr><td style=\"border:3px solid white;\"> <center>");
		out.print("<font color=\"white\">");
		//取り出した星座名を使った表示
		out.print("<h1>" + star + "の今日の運勢</h1>");
		out.print("*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜<br><br>");
		//取り出したメッセージとラッキーアイテムを表示
		out.print("<font color=\"blue\"><b>" + message + "</b></font><br><font color=\"blue\">あなたのラッキーアイテム："+li+"<br></font>");
		out.print("<font color=\"white\">*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜*:.。..。.:*・゜");
		out.print("</center></td></tr></table>");
		out.print("</center></body></html>");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}



}
