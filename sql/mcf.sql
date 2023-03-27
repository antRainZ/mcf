/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 8.0.31 : Database - mcf
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mcf` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mcf`;

/*Table structure for table `apply` */

DROP TABLE IF EXISTS `apply`;

CREATE TABLE `apply` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息主键',
  `send_id` bigint DEFAULT NULL COMMENT '发送者id',
  `send_name` varchar(100) DEFAULT '' COMMENT '发送者昵称',
  `accept_id` bigint DEFAULT NULL COMMENT '接受者id',
  `accept_name` varchar(100) DEFAULT '' COMMENT '发送者昵称',
  `content` varchar(500) DEFAULT '' COMMENT '申请内容',
  `reply` varchar(500) DEFAULT '' COMMENT '申请回复',
  `status` char(1) DEFAULT 'N' COMMENT '状态 N 未接受 默认， Y 允许；R 拒绝',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='好友申请表';

/*Data for the table `apply` */

insert  into `apply`(`id`,`send_id`,`send_name`,`accept_id`,`accept_name`,`content`,`reply`,`status`,`create_time`,`update_time`) values 

(1,1,'antrain',2,'test1','测试','','Y','2021-04-17 22:08:52','2021-04-17 22:08:52');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章主键',
  `title` varchar(100) DEFAULT '' COMMENT '主题标题',
  `content` text COMMENT '内容',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `user_id` bigint DEFAULT NULL COMMENT '创办用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '创办用户昵称',
  `refine` char(1) DEFAULT 'N' COMMENT '是否加精',
  `status` char(1) DEFAULT 'Y' COMMENT '是否有效： Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `labels` varchar(1000) DEFAULT NULL COMMENT '文章的标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='文章表';

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`content`,`remark`,`user_id`,`nickname`,`refine`,`status`,`create_time`,`update_time`,`labels`) values 

(1,'大一新生入学指南','<h2>证件资料类</h2><p>1. 录取通知书</p><p>2. 身份证</p><p>3. 学籍档案（非常重要！要从高中那里拿过来，但是切记不要打开封条，带来学校直接交上）</p><p>4. 团员证以及转团组织关系的资料（入团志愿书、介绍信）</p><p>5. 党员档案和转党组织关系的相关资料（预备党员和党员需要准备）</p><p>6. 一寸、两寸照片至少各一版</p><p>7. 高考准考证</p><p>8. 户口迁移证（要把户口迁到学校的才需要带）</p><p>9. 现金若干（1000元左右，不要太多也不要太少，学校里用校园卡和微信、支付宝就足够了，其他钱可以放银行卡里面）</p><p>10. 银行卡至少一张（可以用学校发的那张银行卡）</p><p>11. 户口本户主页、本人页复印件和身份证复印件若干（以备不时之需）</p><p>12. 贫困证明（这个在申请助学金之类的会用到，需要的同学要带上）</p><p>13. 生源地贷款回执单（借着生源地贷款的同学别忘记带哦）</p><p>14. 交通卡（办一张，以后出行会很方便）</p><h2>生活用品类</h2><p>1. 衣物,衣服、袜子、内衣裤、皮带……</p><p>2. 鞋子，凉鞋、运动鞋、布鞋、旅游鞋，还有拖鞋要记得带哦。</p><p>3. 箱包旅行箱两个（建议一大一小，大的用来放东西，小的可以出门随身带），双肩背包一个，随身小包看情况携带，塑料袋若干。</p><p>4. 驱蚊用品</p><p><span style=\"color: rgb(18, 18, 18);\">5. 洗漱用品</span></p><p><span style=\"color: rgb(18, 18, 18);\">6. 餐具用品</span></p><p><span style=\"color: rgb(18, 18, 18);\">7. 床上用品</span></p><p><span style=\"color: rgb(18, 18, 18);\">8. 洗护用品</span></p><h2>医药用品类</h2><p>1. 创可贴（必备）</p><p>2. 体温计</p><p>3. 外伤用药：云南白药气雾剂，碘酒，医用棉签，纱布，绷带等</p><p>4. 内服药：退烧药，清热解毒药，消炎药，健胃药，治腹泻药，抗过敏药等。其他类型的药品请大家根据自身需要携带。</p><h2>学习用品类</h2><p>1. 签字笔（多备几只）</p><p>2. 涂卡笔（英语考试会用）</p><p>3. 自动铅笔</p><p>4. 橡皮擦</p><p>5. 荧光笔</p><p>6. 小订书机（钉子记得买）</p><p>7. 笔记本（记笔记是个好习惯哦）</p><p>8. 便利贴</p><p>9. 胶水，固体胶</p><p>10. 长尾夹</p>','',1,'antrain','Y','Y','2021-04-12 16:54:24','2021-05-07 16:26:54','3,4'),

(2,'大一防骗指南','<h2>一、代管行李，支开新生</h2><p><span style=\"color: rgb(18, 18, 18);\">新生人生地不熟，骗子就在车站、学校门口冒充接待人员，“热情”地帮忙看管皮箱等物品，再调虎离山，借机偷走行李。当陌生人对你过分热情时应保持警惕，物品不要轻易离身，以免上当受骗。</span></p><h2>二、转交学费，一去不回</h2><p><span style=\"color: rgb(18, 18, 18);\">骗子以学生或家长身份混上校车后，再假冒学校迎新人员的身份与学生、家长搭讪，取得信任，以代交学费、前往银行取钱为由乘机诈骗。新生在报到时，不可将钱物交给陌生人，报名交费务必亲自办理。</span></p><h2>三、冒充室友，盗走钱物</h2><p>刚入学时，新生相互不认识，骗子正是利用这一点，装扮成新生或老乡和学生套近乎，骗取信任，并乘机盗走学生的财物。对陌生人，应想办法确认其身份，离开宿舍时应将贵重物品随身带走，或者锁上存放起来。</p><h2>四、寝室推销，竟是假货</h2><p><span style=\"color: rgb(18, 18, 18);\">上门推销是新生经常遇到的骗局。骗子所推销物品多与新生的学习生活息息相关，上当者往往以外地初来乍到者居多。对陌生推销者的花言巧语不要轻信，买东西时最好到学校内外的正规超市购买。</span></p><h2>五、招聘兼职，骗取押金</h2><p>学生想利用课余做兼职，应聘时一定要查看对方资质，应聘过程中遇到索要身份证、交押金保证金者，立即转身走人。按照国家规定，用人单位不应收取任何费用，凡有收费都视作违法。</p><h2>六、寝室推销报纸、电话卡</h2><p>刚开学，会有很多学长学姐来寝室推销英语报纸、各种杂志，基本你交钱了，说每月一期，很多是然后就没有然后了，其实，说实话，这东西基本没有人看，如果你的确真想学，可以寝室几个人合起来订一份就够了。同样，会有各种人上门推销电话卡、充值卡的，这个时候就要小心了，很多时候没有售后保证，或者本来就是骗，办卡到学校正规的营业厅办理，当然开学营业厅也会在学校摆帐篷设点销售，这个也没有问题，总之上门的个人就得小心了。</p><h2>七、诈骗电话，骗取个人隐私及财物</h2><p>通常在非正规经销商处购买电话卡后，会接到一些诈骗电话或垃圾短信。有的接到的电话内容类似资助新同学学费、生活费等理由，骗取银行卡密码。还有的打电话自称是教育部或校方以返还学费等为名，让学生提供卡号、密码。</p><p>对自己的隐私要严格保密，遇到此类事件要先向有关机构电话求证，遇到欺诈行为要及时向公安机关报警。</p><h2>八、冒充老师，骗取钱财</h2><p><span style=\"color: rgb(18, 18, 18);\">报道搞定可以休息一下了，寝室门开了：\"你们是今年的新生吧，我是某某老师，这是某某卡请把费用交一下，就几百块\"既然老师来了，赶快交钱吧，付了钱拿了卡，老师是谁，没人认识。</span></p><p><span style=\"color: rgb(18, 18, 18);\">骗子往往就利用这个当口，实施骗局，提高警惕是必须。要记住，在正式上课之前，你只认一个人，就是辅导员，而且是你的辅导员！</span></p><h2>九、冒充学校，骗取钱财</h2><p>警惕专升本报名就可以预约专升本，等于天上掉馅饼，一些专科学生报道后，有些自称的校方的人会前来告知，什么什么条件可以专升本，但必须提前进入啥高价培训班等等，是真是假大家留神，可能就是个骗局。</p><h2>十、盗用证件，乱开卡</h2><p>管好自己的证件身份证等要妥善保管，不能借给他人使用。不能委托他人代为复印，身份证复印件在应用中要注明此件的用途。防止他人借用你的身份证以及复印件办理各种银行卡、电话卡、借记卡等相关有价凭证，给你造成重大经济损失。更不要给你送个小礼物你就把自己的身份信息随便提供给一些人！</p><h2>十一、冒充老师，向家长骗钱</h2><p>很多家长在孩子入学之后会接到学校“老师”打来的电话，说孩子出车祸之类的，医院需要钱等等理由，着急的家长会给孩子打电话，发现孩子电话无法接通，或关机，家长就着急了，最后一点警惕心理也没了，然后就被骗了。所以，一定要将导员的电话第一时间告诉父母！</p>','',1,'antrain','N','Y','2021-05-06 22:21:16','2021-05-07 16:27:40','3,4'),

(3,'大学新生入学指南','<h2><strong>一、入学准备篇</strong></h2><p>开学前一定要仔细阅读学校邮寄过来的录取通知书等文件。上面有很重要的信息，有许多需要你提前准备的东西还有学校的具体地址。</p><p><strong>1、提前准备好各种证件</strong>：录取通知书、身份证原件以及正反面复印件、户口本复印件、银行卡、一寸照片和两寸照片、学生档案。照片一寸和两寸的黑白和彩色一样准备一板，多了总比少了好，用不完可以保存下来以后用，悄悄告诉大家到时候大学校园内以及周围拍照片的很贵，比你在家要贵一倍。身份证和户口本复印件一样多准备两份，现在用不到以后也会用到，有备无患。</p><p><strong>2、提前买好来回的车票</strong>：提前买好去的车票和家人回来的车票，不提前买票到时可能买不到。我记得我当时是和我爸坐客车去的，晚上从长春出发第二天早上到的哈尔滨。一定要让家人陪你去，不是说你矫情，因为到时候你自己忙不过来，有的学校交费都是要排大长队的。我记得我入学的时候交费的队伍排了好长，我爸去给我排的队，然后我去这时候你就会感谢我提醒你让爸妈陪你去。办理其他的，这样两边同时进行会快一些。这时候你一定会感谢我提醒你让爸妈陪你去。去之前打听好学校的具体位置，从车站怎么过去。</p><p><strong>3、多加一些新生沟通群</strong>：多加一些学校新生群，去qq上搜，就能搜到好多关于你们学校的新生群，在群里多活跃，群主都是学长或学姐，想要了解什么可以直接问他们，要记得说谢谢哦。加群目的就是为了了解更多信息和认识一些其他新生。多个朋友多条路，因为群里可能各个专业的新人都有，你现在不和他们认识以后就没机会了，平常也接触不到。多在群里活跃对你以后竞选学生会和班委有好处。谁也不会选个没有能力，不擅长交流的同学当班委吧。还可以去学校的贴吧逛逛，总会给你意外的惊喜。</p><p><strong>4、一定要提前订酒店</strong>：一定要提前订酒店，大多数新生都是前一天到达学校，然后再附近找个酒店或宾馆住下。所以人会很多，不提前订酒店或宾馆，当天是订不到的，一定一定要提前订，不要怕贵，因为那个时间段学校附近的酒店都会涨价。我当时就是因为没有经验，和我爸去了之后匆忙找的住的地方，问了好几个地方都没有房间，最后找了一家小破旅馆住了一晚。还有摸清附近吃饭的地方，要不然到时候会饿肚子找吃饭的地方。</p><p><strong>5、尽量不坐黑车</strong>：我想每个城市或多或少都有一些黑出租，尤其开学前两天，车站出租车整体涨价，甚至坐地起价，欺负你是外来的，我也被坑过.....我们没必要坐这些黑出租，除非你是半夜到的实在没办法了，那就多花点钱被他们坑一回吧，哈哈。每个学校都会在车站设立新生接待点，你到了之后可以去接待点咨询学长学姐们怎么去学校，他们会告诉你坐多少路公交啊或者在哪打车过去等方便等。</p><p><strong>6、可以提前几天来然后陪父母逛逛</strong>：在家做好你将要去的这个城市的旅游攻略，然后提前几天和你的父母过去，带他们到处看看、逛逛，毕竟他们以后不一定会来了，所以趁着这次机会可以好好先玩玩。以后你将在这个城市独自一人度过四年的学习生活，多熟悉熟悉还是有好处的。</p><p><strong>7、不必带太多东西</strong>：我看到有的同学带好多好多行李，恨不得把家都搬过来。开学不是搬家，真的没必要，我记得我同学把洗脸盆都带去了，哈哈。有些东西完全可以在学校附近超市一件一件置办。或者提前一两天在淘宝选好下单，等你过去快递也到了，美哉美哉。至于大件物品被子、枕头床单被罩这些东西有的学校会发，即使不发你也可以提前在网上买好发过去，不嫌累从家拿或者寄过去也可以。还有搞清楚学校允不允许使用大功率的电器，不允许使用就不要带了。我上大学那会，连个吹风机学校都不让用。</p><p><strong>8、校园卡不必着急办理</strong>：不用着急办理校园卡，那些主动推荐你办卡的，多半你办了后期会发现被坑了。校园卡种类很多，套餐都不同，不必着急买，等过去开学这几天慢慢看慢慢挑。</p><p><strong>9、推销的一定不要信</strong>：新生入学前后几天一定会遇到各种各样的推销，还有去宿舍推销的，每个新生在他们眼里都是待宰的羔羊，比如健身啊、拍艺术照、学习机构啊等等。不要信，不要听他们瞎白话，都是在吹牛皮。都是来坑新生的，不信你可以打听打听学长或学姐们。</p><p><strong>10、建议十一或双十一再买电脑</strong>：不建议开学或开学前买电脑，可以等到十一或者双十一再买，那时候会便宜很多。直接建议你买游戏本，无论你是学弟还是学妹。根据我的经验来看你这个笔记本多半时光是用来打游戏的，不要问我为什么，因为我们寝室八个人，大学四年除了毕设其余都是用它来打游戏，哈哈。买电脑之前多查查资料多咨询咨询专业人士。我当时是开学前在本地的百脑汇买的，还是我姐夫这个非专业人士带我去买的，当时还多付了一个U盘的钱。开学后没过多久我就觉得被坑了，不仅价格贵，电脑还垃圾。打lol的时候每次我都是最后进去的。我是一个被电脑耽误的王者选手，哈哈。所以我强烈强烈不建议大家在实体店买，十个有十个坑，并且维权很难。</p><p><br></p><p><br></p><h2><strong>二、宿舍篇</strong></h2><p><strong>1、选好宿舍：</strong>入学当天学校会给你安排好宿舍，但是床位是自己选的，有的学校是先到先选，有的是抽签。我记得我们学校就是抽签的形式，我抽到了一个下铺，很满意。选择床位也很重要，一般这个床位陪你四年，所以尽量选择一个好的床位</p><p><strong>2、保持卫生干净：</strong>大家都在一个干净的空间成长、学习、生活不好吗？最好排个卫生值班表，每人轮流来，这样就会好一点。</p><p><strong>3、团结友好：</strong>你们是要在一起过四年的，所以一定要团结友好，互相帮助，不要斤斤计较。你和谁闹翻了都不好，毕竟低头不见抬头见，多尴尬。我大学八人寝，我们每天都过得快快乐乐，一起开黑，一起过生日，一起互吹，很怀念。到了现在我们都会经常联系，有的已经结婚了，结婚的我们都会互相通知，都会到位。</p><p><strong>4、一定要有自知之明：</strong>别人在学习你再放音乐、玩游戏开外放，你觉得合适吗？到了入睡的时间点，不要在大声喧哗，也不要在煲电话粥了，追剧要带耳机；总之最好不要影响其他人。我记得我们寝室老二，每次他要睡觉的时候都会对大家说，小点声，哈哈，现在想起来还想笑呢。</p><p><strong>5、不要斤斤计较：</strong>你用他一次洗发水、他用你一把洗衣粉，这都没什么，谁也不差那点东西，可能他刚好没有了，还没来得及买。我们寝室互相用的最多的就是水卡了，哈哈。我记得我用过他们水卡好多次呢，当然他们也用过我的。</p><p><strong>6、可以收寝费：</strong>如果有可能可以每人交一部分寝费，可以用来买纸和充水卡，这是我们寝室寝费拿来干的最多的事，男生寝室很废纸，你懂得，哈哈。</p><p><strong>7、关于借钱：</strong>能不借钱最好不要去借。如果借了一定按时还。最烦那种借钱之后不但不感激你，还跟你生气。</p><p><br></p><p><br></p><h2><strong>三、军训篇</strong></h2><p><strong>1、准备好姨妈巾鞋垫：</strong>是的军训之前一定要准备好姨妈巾鞋垫，到时候垫在鞋里，穿军训鞋站时间长了很累脚，垫上之后很舒服。我记得我那个时候还没有姨妈巾鞋垫呢，都是用的姨妈巾，看来我老了，哈哈。</p><p><strong>2、不要迟到：</strong>迟到耽误的是大家的时间，而且迟到可能会挨罚，挨罚很痛苦，我就被罚蹲着过，好累。</p><p><strong>3、多沟通、会来事：</strong>休息时多和自己身边的同学聊天，要学会来事，帮同学买水等等。</p><p><strong>4、教官不可怕：</strong>教官除了军训时严肃，平常很好相处。我记得我们当时的教官晚上放学之后还偷偷买AD钙喝，哈哈。过一段时间学校还会派代表去看这些教官，一般都是和他们相处的好的同学去。<strong>军训时追求女生和男生是个不错的时机哦。  </strong></p><p><br></p><h2><br></h2><h2><strong>四、学习篇</strong></h2><p><strong>学习很重要、很重要、很重要。大学就是让你来学习的，不是让你玩的。</strong></p><p><strong>1、不要旷课：</strong>你可以不听，但是每节课都要去。给老师留个好印象。尤其是最后几节课，很重要，一般老师会在最后几节课划重点，画的很可能就是考的。</p><p><strong>2、不要挂科：</strong>挂科有多重要我就不用多说了，这直接影响到你能不能顺利毕业。考试不会的不要空着，都要写满，这个事情从小学到大学，老师都会一直提醒你。</p><p><strong>3、四六级越早考越好：</strong>趁着高中的底子还在，平常多背背单词，上课认真听讲，趁早把四六级过了，要不然越来越难。四级我考了三次都没过，后来果断放弃了。工作时都指着百度翻译活着呢，千万别学我。过了四六级可以为你以后找工作加分。</p><p><strong>4、课上好好听讲：</strong>不要等到期末老师画一堆题，然后你一顿背。期末那几天时间是补不过来的，因为你平时缺的太多了。</p><p><strong>5、坚持阅读习惯：</strong>每天可以花一小时时间认真的去读一些课外书。阅读就是积累，不断打破以往的认知，看清事物的本质。</p><p><br></p><p><br></p><h2><strong>五、生活篇</strong></h2><p><strong>1、多健身：</strong>身体是革命的本钱。坚强的体魄可以让你远离病毒，可以让你多活十几年。可以让你更轻松的抱起你女朋友走一里地。反正健身就是好！好！好！</p><p><strong>2、暑假一定要去考驾照：</strong>高考之后的暑假和大学寒暑假是你考驾驶证最好的时机，这时候不考以后就没时间了，所以一定要考一本驾照。学生组团报名还便宜。我到现在都没考，后悔的都哭了。</p><p><strong>3、多跟家人通电话：</strong>沟通是人与人之间的桥梁。不要听那些说好朋友不需要经常联系，全是放屁，你试试一年不联系你朋友，你看看你们第一次见面会不会略微尴尬。不要因为没钱了才想起给家人打电话，还有比钱更重要的。平时多跟家人聊天沟通交流，不至于要钱的时候尴尬，多聊聊天，你会发现他们可爱的一面。</p><p><strong>4、多吃水果，多补充维生素：</strong>一周拿出来几十块钱买水果，你会发现身体会越来越好。</p><p><strong>5、不要和别人攀比：</strong>要记住你们来学校的目的是学习，不是攀比。现在的你们没有资格互相攀比，因为你们花的钱都是父母给的。和他人攀比的时候多想想自己的父母。平常没什么重要的东西要买，不要乱花钱。养成存钱的好习惯，将来的你会感谢自己。</p><p><strong>6、培养一个兴趣爱好：</strong>没事少玩游戏，多培养一个兴趣爱好，比如唱歌、跳舞、吉他、滑板、篮球等等，你会发现有一项兴趣爱好交朋友很容易。也能丰富大学生活。</p><p><strong>7、健身房的卡不要办：</strong>健身房的卡不要办，尤其是新开业的健身房。办完卡之后很多健身房都跑路了，还有就是你办完不一定去，那不就浪费了。完全可以自己在寝室练或者在操场上跑步。</p><p><strong>8、保持干净：</strong>不需要你打扮的多帅多漂亮，但一定要干干净净，给人留下一个好印象。</p><p><br></p><p><br></p><h2><strong>六、恋爱篇</strong></h2><p><strong>1、请专一，不要滥情：</strong>无论男生还是女生希望大家不要抱着玩一玩的态度去谈恋爱，宁缺毋滥，浪费时间浪费精力，不仅伤害自己还会伤害到别人。</p><p><strong>2、学会负责：</strong>对自己和对方负责；不要轻易把第一次给对方，容易得到的往往不会珍惜。如果爱，请做好防护措施。</p><p><strong>3、要敢于沟通：</strong>冷暴力解决不了问题，甚至会让事情变得更糟糕，身为男人，要主动一些。</p><p><strong>4、准备一些小惊喜：</strong>特别的节日要过的有仪式感，准备一下小惊喜会加深你们之间的感情。</p><p><strong>5、有目标就要放手去追：</strong>帅不是硬通货，有时候真诚也会打动一个人。如果你爱她，就厚着脸皮去追吧。</p><p><strong>6、谈恋爱要趁早：</strong>前一个月大家都是刚离开家乡和父母，都会感到孤独，身边也没有熟悉的朋友，所以这个时候是最好的出手机会。</p><p><br></p><p><strong>最后</strong></p><p><strong>成长的过程就是破茧为蝶，挣扎着褪掉所有的青涩和丑陋，在阳光下抖动轻盈美丽的翅膀，闪闪的，微微的，幸福的颤抖!</strong></p><p><strong>生活不止酸甜苦辣，还有远方和诗，你们的未来无限可能，努力吧，少年们。祝你们学业有成、爱情有成。</strong></p>','',1,'antrain','N','Y','2021-05-06 22:25:10','2021-05-07 16:27:46','3,4'),

(4,'大一新生常见的心理问题','<p>	大学新生入学之初，就面临着对新环境的适应，许多学生第一次离开家庭，离开中学时熟悉的老师和同学，来到大学的陌生环境。面对新的环境、新的学习生活方式、新的人际关系网，一年级学生很难适应。同时，新生入学后，自我观念也面临着新的冲击。在中学里，大学生往往是各个学校的优秀学生，受父母宠爱、老师重视和同学钦佩；进入大学以后，班级中汇集了来自于不同学校的优秀生，许多学生觉得自己往日的优势不复存在，就会产生失落感。进入大学后，有的学生对自己所报的学校或专业不满意；有的学生未能适应大学教学和学习的方法；有的是独生子女，进入大学后日常生活不能自理，心理脆弱；有的学生觉得眼下的大学生活与自己理想中的“象牙塔”相去甚远……</p><p><br></p><p>	据心理学家研究统计显示，大约有90％的大学新生入校后，在不同程度上产生上述心理上的不适应感。适应能力较强的同学，一般在入学1个月左右能基本稳定情绪，调整好自己，把主要精力投到正常的大学学习和生活中。一部分适应能力较弱的同学，一般也能在3个月左右适应新的环境。而另外一部分同学即使在6个月后却仍然不能适应。新生的这些不适应严重影响了大学生的心身健康，甚至可能导致自我发展障碍，使大学生正常的学习生活产生危机。而此时能否采取有效的应对方式，正确处理、对待新环境中的各种问题，对大学新生尤为重要。</p><p><br></p><p>	大学生的应对方式主要分为“积极”与“消极”两种维度。“积极”的应对是当个体受到应激源的冲击时，能有目的、有意识地做出认知和行为的努力，改变自己与环境之间的关系，控制应激性情境，使应激事件得到符合实际的、妥善的处理，从而减轻焦虑，维护和增进心理健康。“消极”的应对是使个体对学习、生活信心不足，陷入紧张、抑郁、恐惧等负面情绪体验中，有损于心理健康。</p><p><br></p><p>	很明显，大一新生应该采取“积极”的维度去应对新的生活环境。下面主要根据新生常见的心理问题，从认知角度逐一阐述该如何去调整心态，达到尽快适应新的环境。</p><p><br></p><p><br></p><h2>	第一、关于学校、专业的选择问题。</h2><p>	新生中可能会有很大一部分人对现在所来到的学校很失望，认为这不是他们理想中的大学校园，心理上出现失落感和自卑感。其实，理想和现实必定有着差距，这个道理要是单独提出来说，也许每个人都能理解。只是大一新生往往在中学时代就对大学校园有着很高的期望值，再加上社会和家庭的影响，他们过于美化了大学校园，憧憬的有点脱离实际。正所谓：期望越大，失望就越大。难免对陡然呈现在眼前的校园“挑三捡四”了。这也是情理之中，可以理解。但古诗有云：“横看成岭侧成峰，远近高低各不同”，对于目前的学校亦然，同学们要学会用辨证的观点来看待问题，从不同的角度和视野来分析问题。就比如说铜陵职业技术学院吧，在这里图书馆有大量的图书资料可供你学习，也有著作等身的优秀教师资源。每一位同学都完全可以去充分利用，以目前的学校为学习平台，在校期间可以参加本科自考，将来也可以参加专升本考试，以后还想进一步深造学习的话，也可以参加硕士研究生招生考试等。而现在，既然你已经来到了这个学校，木已成舟，还是建议同学们“既来之，则安之”，要开始行动起来，积极着手自己的大学生活。鸡窝里也可能飞出金凤凰，只要你觉得你是块金子，只要你有“打破牙和血吞”的精神，你这块金子到哪都会发光的。</p><p><br></p><p><br></p><h2>	第二、关于学习态度的问题。</h2><p>	现在的人类已进入了21世纪，现代的高等教育模式已实现了由精英式教育向大众化教育的转变，所以每一位同学都应该首先端正自己的学习态度。“60分万岁”、“上了大学就等于不学”等错误观念越来越被人们所摒弃，这些观念本身也不是对待科学与真理的正确态度，未来的世界属于那些真正掌握知识、掌握技能的人。无真才实学者，终将被社会遗弃，被竞争所淘汰！所以，作为大学新生，在你一跨入大学校门后，就应该明白：成功只属于那些有准备的头脑。同学们现在开始就应该始终如一地脚踏实地，勤奋学习，以免“老大徒伤悲”，最后一头白发两手空空。</p><p><br></p><p><br></p><h2>	第三、关于校园恋情问题。</h2><p>	大学生伴随生理和心理的日趋成熟，自我意识的发展，异性交往相对增多，由此产生朦胧的恋情，也实属正常现象。但我个人认为：爱情不是没有，关键看你是否具备了可以迎接她的能力。因为学习仍然是大学阶段的主题，自古云“书中自有颜如玉”，我们也相信“载得梧桐树，必有凤凰来”。大学生是个特殊社会群体，今后的生活还会动荡，毕业分配和就业还是个未知数，即使获得了真爱，毕业后也有可能天各一方。所以，作为大学生首先要把自己的学业放在重中之重，这一点希望广大新生能够明白。其实，纵观大学生们通常反映出的恋爱心理特征，主要有：性爱的好奇心理、急于求成的占有心理、依赖心理、补偿心理、游戏人生心理等。许多大学生在恋爱问题上感到有很多说不明白的心灵困境或叫心理困惑。其原因有三：一是因为在大学生心目中，爱情的理想与现实的差距让人感受到一种无以名状的失落。二是由于恋爱能否成功的因素是多方面的，如年龄、外貌、品行、性格、文化、职业、兴趣、爱好、经济状况、民族、宗教信仰、政治态度等等，或许只有某方面的相互欣赏和认可就走到了一块，或许也仅仅因为某一点小小的看不惯就分手了。三是由大学生恋爱的心理特征所引发并形成的恋爱低龄化、公开化、高速度进展和恋爱的多元化所致。总之，如果在感情的纠葛中出现了问题，首先要做到冷静和理智，切忌冲动、感情用事。</p><p><br></p><p><br></p><h2>	第四、关于同学间的交往问题。</h2><p>	针对同学友情，我们应该本着“捧着一颗心来，不带半根草”去的原则，以诚待人。每个人在处理很多事情时都会或多或少带有自己的主观体验，形成自己的风格。很多人在兴趣、性格和爱好上也不尽相同，甚至也会大相径庭，这也属可以理解的。但为了同学间能求大同存小异，友好相处，每个人都应该尽力做到相互体谅、相互尊重。只要你理解和尊重了别人，别人也一定会去理解、尊重你，这和“投之以李，报之以桃”是一个道理。相信只要你对别人敞开心胸，坦诚相见，那么你一定就会拥有更多真正的知己朋友。</p><p><br></p><p><br></p><h2>	第五、关于家庭经济困难的问题。</h2><p>	“无浪水亦东流去，无风鹤亦上青天”，家庭的贫与富，不是制约你们安然飞翔的根本原因。说白了，无论穷爸爸，还是富爸爸，那完全是上一辈人的事情，这并不能代表你们这一辈人的高低贵贱。有富爸爸的同学，不应有养尊处优的骄气；是穷爸爸的同学，更不能感到低人一等。现在大家同在一个学校、同在一个班级，每位同学都是自由平等的，每一位同学都是自己命运的主宰者。或许现在尚有富与贫，但最终结果你们会如何？是否会“三十年河东，三十年河西”，或“柳暗花明又一村”呢？这还都不得而知，这还得看你付出了多少努力。如果一些家庭经济困难的同学从现在就开始自卑自弃，岂不冤哉。更何况，在现有的知识经济时代和市场经济体制下，知识越来越成为财富的象征，而且是一笔丰硕的财富，书中也有黄金屋。</p><p><br></p><p>	<strong>广大的新生同学们，请你们记住：相信你自己，就等于成功了一半。而在你面对任何挑选时，你将永属于上等！</strong></p>','',1,'antrain','N','N','2021-05-06 22:31:16','2021-05-07 16:27:51','3,4');

/*Table structure for table `article_comment` */

DROP TABLE IF EXISTS `article_comment`;

CREATE TABLE `article_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章评论主键',
  `article_id` bigint DEFAULT NULL COMMENT '对应文章主键',
  `article_title` varchar(100) DEFAULT '' COMMENT '对应文章标题',
  `user_id` bigint DEFAULT NULL COMMENT '评论用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '评论用户昵称',
  `content` text COMMENT '内容',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `status` char(1) DEFAULT 'Y' COMMENT '是否有效： Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='文章评论表';

/*Data for the table `article_comment` */

insert  into `article_comment`(`id`,`article_id`,`article_title`,`user_id`,`nickname`,`content`,`remark`,`status`,`create_time`,`update_time`) values 

(1,2,'大一防骗指南',1,'antrain','测试','','N','2021-05-07 20:37:16','2021-05-07 21:05:05');

/*Table structure for table `gen_table` */

DROP TABLE IF EXISTS `gen_table`;

CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COMMENT='代码生成业务表';

/*Data for the table `gen_table` */

/*Table structure for table `gen_table_column` */

DROP TABLE IF EXISTS `gen_table_column`;

CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb3 COMMENT='代码生成业务表字段';


/*Table structure for table `integral` */

DROP TABLE IF EXISTS `integral`;

CREATE TABLE `integral` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint DEFAULT NULL COMMENT '评论用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '评论用户昵称',
  `add_value` int DEFAULT '1' COMMENT '增加多少积分',
  `per_value` int DEFAULT '0' COMMENT '增强前积分',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `status` char(1) DEFAULT 'Y' COMMENT '是否有效： Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='用户积分记录表';

/*Data for the table `integral` */

insert  into `integral`(`id`,`user_id`,`nickname`,`add_value`,`per_value`,`remark`,`status`,`create_time`,`update_time`) values 

(2,1,'antrain',1,0,'测试','Y','2021-04-19 13:13:48','2021-04-19 13:13:48'),

(3,1,'antrain',1,1,'signIn','Y','2021-04-19 15:52:42','2021-04-19 15:52:42'),

(4,1,'antrain',1,2,'signIn','Y','2021-04-28 17:15:16','2021-04-28 17:15:16');

/*Table structure for table `invitation` */

DROP TABLE IF EXISTS `invitation`;

CREATE TABLE `invitation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `topic_id` bigint DEFAULT NULL COMMENT '主题主键',
  `topic_title` varchar(100) DEFAULT '' COMMENT '主题标题',
  `title` varchar(100) DEFAULT '' COMMENT '标题',
  `user_id` bigint DEFAULT NULL COMMENT '创办用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '创办用户昵称',
  `content` text COMMENT '内容',
  `refine` char(1) DEFAULT 'N' COMMENT '是否加精',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `status` char(1) DEFAULT 'Y' COMMENT '是否有效： Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COMMENT='帖子表';

/*Data for the table `invitation` */

insert  into `invitation`(`id`,`topic_id`,`topic_title`,`title`,`user_id`,`nickname`,`content`,`refine`,`remark`,`status`,`create_time`,`update_time`) values 

(3,1,'校园适应','平衡好学业和实践的关系',1,'antrain','<p>大学生活是非常丰富多彩的。大学里有许多的社团、协会和社会实践的机会，这些组织一般会举行多种活动以丰富校园文化生活。在大学期间，大一新生可以适当的参加一些实践活动，从而提高自己沟通、组织等各方面的能力，还可以学到许多书本上没有的知识。但是，在处理学业和社会活动二者之间的关系时，一定要懂得取舍。比如，一些学生会为了急着参加社会实践，积累工作经验而耽误学业，很有可能会得不偿失。</p><p>提高自控能力，自觉学习</p><p>大学的课程相比中学没有那么密集，老师的管理也不会那么严格，所以一定要有良好的自控能力，按时上下课，这样才能在大学中学习到知识。</p><p><br></p>','Y','无','Y','2021-04-12 14:13:17','2021-05-11 14:40:13'),

(4,1,'校园适应','大学新生如何从自身方面解决问题',1,'antrain','<p><span style=\"color: rgb(18, 18, 18);\">对于新的大学，我们要努力去融入其中，我们无法改变环境只能改变心中的所想，虽有不如意的地方，但既来之则安之，让自己的大学时光不虚度不荒芜，有意识的培养自己的管理能力，打理自己的日常生活，吃、穿、住、行都需要自己合理规划，要培养自己良好的理财观念。</span></p>','N','无','Y','2021-04-12 16:34:55','2021-05-11 14:48:14'),

(5,1,'校园适应','大一新生：想拿奖学金，除了努力学习，还要注意这4点',2,'test1','<p><span style=\"color: rgb(18, 18, 18);\">俗话说，不想拿奖学金的学生不是好学生，要知道大学奖学金除了金额不菲以外，它还是你写在简历上的荣誉。</span></p><ol><li><span style=\"color: rgb(18, 18, 18);\">各学科成绩优异在各大高校都是获得奖学金的一个重要前提，也是一个重要的筛选条件，如果你的成绩没有达到一定的绩点，就没有机会参与奖学金评选。</span></li><li>多参加校内外的活动、比赛</li><li>竞选班干部、学生会干部</li><li>多考、多拿有用证书</li></ol>','N','无','Y','2021-04-12 16:34:55','2021-05-11 14:53:15'),

(6,1,'校园适应','军训，我可以不参加吗？后果很严重',2,'test1','<p>首先，军训是有法可依的活动。《兵役法》规定“普通高等学校的学生在就学期间，必须接受基本军事训练。”</p><p>其次，军训是必须的学分。大学军训一般都是必修学分制，也就是说，如果你没有必要合理的理由，是不可能不参加军训的。而且如果不参加军训，没有任何其他办法抵消过去。</p><p>再次，军训是大学生成长的需要。军训可以增强大学生国防意识与集体主义观念，培养团结互助的作风，增强集体凝聚力与战斗力。军训，还能提高生活自理能力，培养思想上的自立和独立，还能帮助我们养成严格自律的良好习惯。</p><p>一般情况下，所有的大学新生，必须参加军训。如果不能参加军训，一方面是个人成长的损失，另一方面还可能影响到你大学能不能顺利毕业。</p><p><br></p>','N','无','Y','2021-04-12 16:34:55','2021-05-11 14:50:19'),

(7,1,'校园适应','假如回到大一刚入学的自己，有什么想对自己说的',3,'test2','<p>大家可以在讨论一下，假如如果有一次机会让你重新回到刚入大学的时候，<strong>你会对自己说什么</strong></p>','Y','无','Y','2021-04-12 16:34:55','2021-05-12 10:50:37'),

(8,1,'校园适应','给计算机专业新生的五点建议',3,'test2','<h2>1.尽早确定目标</h2><h2>2.学好基础课程</h2><h2>3.精通一门语言</h2><h2>4.善用搜索引擎</h2><h2>5.动手实践</h2><p><br></p><p><br></p>','N','无','Y','2021-04-12 16:34:55','2021-05-11 14:57:26'),

(9,1,'校园适应','开学了，大一新生如何调整心理情绪',1,'antrain','<p><span style=\"color: rgb(34, 34, 34);\">开学了，大一新生面对一个陌生的环境，可以先跟家里人或者好朋友保持联系，减轻自己的忧虑和恐惧。进入大学宿舍后，首先要认识自己的室友，慢慢的跟室友熟悉起来，积极调整自己的心理情绪，之后由室友扩展到大一同班同学，一步一步，</span><strong style=\"color: rgb(34, 34, 34);\">小编希望你能很好的融入属于你的大一生活，感受大学生活的美好。</strong></p><p><span style=\"color: rgb(34, 34, 34);\">开学了，大学对大一新生要求严格，这样就容易给大一新生造成了一定的心理压力，以至于大一新生多会产生一定的心理情绪。其实，</span><strong style=\"color: rgb(34, 34, 34);\">大一新生如果感到过度恐慌，可以与自己的老师沟通一下，最为大一新生倾诉是最好的心理情绪调整方法。</strong></p>','N','无','Y','2021-04-12 16:34:55','2021-05-11 15:02:19'),

(15,1,'校园适应','test',1,'antrain','test','N','','Y','2021-05-12 19:48:50','2021-05-12 19:48:50');

/*Table structure for table `invitation_comment` */

DROP TABLE IF EXISTS `invitation_comment`;

CREATE TABLE `invitation_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '帖子评论主键',
  `invitation_id` bigint DEFAULT NULL COMMENT '对应帖子主键',
  `invitation_title` varchar(100) DEFAULT '' COMMENT '对应帖子标题',
  `user_id` bigint DEFAULT NULL COMMENT '评论用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '评论用户昵称',
  `content` text COMMENT '内容',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `status` char(1) DEFAULT 'Y' COMMENT '是否有效： Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='帖子评论表';

/*Data for the table `invitation_comment` */

insert  into `invitation_comment`(`id`,`invitation_id`,`invitation_title`,`user_id`,`nickname`,`content`,`remark`,`status`,`create_time`,`update_time`) values 

(1,3,'测试',1,'antrain','测试','','Y','2021-04-18 22:36:15','2021-04-18 22:36:15');

/*Table structure for table `lable` */

DROP TABLE IF EXISTS `lable`;

CREATE TABLE `lable` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) DEFAULT '' COMMENT '主题标题',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `user_id` bigint DEFAULT NULL COMMENT '创办用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '创办用户昵称',
  `status` char(1) DEFAULT 'Y' COMMENT '是否有效： Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='标签表';

/*Data for the table `lable` */

insert  into `lable`(`id`,`title`,`remark`,`user_id`,`nickname`,`status`,`create_time`,`update_time`) values 

(1,'算法','算法',1,'antrain','Y','2021-04-12 16:45:58','2021-05-07 13:38:25'),

(2,'vue','vue',1,'antrain','Y','2021-04-17 10:50:28','2021-04-17 10:50:28'),

(3,'入学指南','入学指南',1,'antrain','Y','2021-05-07 14:28:24','2021-05-07 14:28:24'),

(4,'通知','通知',1,'antrain','Y','2021-05-07 14:36:56','2021-05-07 14:36:56');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息主键',
  `send_id` bigint DEFAULT NULL COMMENT '发送者id',
  `send_name` varchar(100) DEFAULT '' COMMENT '发送者昵称',
  `accept_id` bigint DEFAULT NULL COMMENT '接受者id',
  `accept_name` varchar(100) DEFAULT '' COMMENT '发送者昵称',
  `content` text COMMENT '内容',
  `type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT 'C' COMMENT '类别：C普通 默认; R 已读',
  `status` char(1) DEFAULT 'Y' COMMENT '是否有效： Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='消息表';

/*Data for the table `message` */

insert  into `message`(`id`,`send_id`,`send_name`,`accept_id`,`accept_name`,`content`,`type`,`status`,`create_time`,`update_time`) values 

(1,1,'antrain',2,'test1','test','R','Y','2021-04-26 20:27:24','2021-04-26 20:38:29'),

(2,2,'test1',1,'antrain','hello','R','Y','2021-04-26 20:39:56','2021-04-26 20:40:53'),

(3,1,'antrain',2,'test1','干啥呢','R','Y','2021-04-26 20:43:11','2021-04-26 20:43:19'),

(4,2,'test1',1,'antrain','正在敲代码呢','R','Y','2021-04-26 20:45:04','2021-04-26 20:45:07'),

(5,1,'antrain',2,'test1','啥项目呢','R','Y','2021-04-26 20:45:55','2021-04-26 20:45:58'),

(6,1,'antrain',2,'test1','兄弟在？','C','Y','2021-04-26 21:50:35','2021-04-26 21:50:35');

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`sched_name`,`trigger_name`,`trigger_group`,`cron_expression`,`time_zone_id`) values 

('RuoyiScheduler','TASK_CLASS_NAME1','DEFAULT','0/10 * * * * ?','GMT+08:00'),

('RuoyiScheduler','TASK_CLASS_NAME2','DEFAULT','0/15 * * * * ?','GMT+08:00'),

('RuoyiScheduler','TASK_CLASS_NAME3','DEFAULT','0/20 * * * * ?','GMT+08:00');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint NOT NULL,
  `sched_time` bigint NOT NULL,
  `priority` int NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`sched_name`,`job_name`,`job_group`,`description`,`job_class_name`,`is_durable`,`is_nonconcurrent`,`is_update_data`,`requests_recovery`,`job_data`) values 

('RuoyiScheduler','TASK_CLASS_NAME1','DEFAULT',NULL,'org.antrain.quartz.util.QuartzDisallowConcurrentExecution','0','1','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0 org.antrain.quartz.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0)org.antrain.common.core.domain.BaseEntity\0\0\0\0\0\0\0\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0x��xpt\0\0pppt\01t\00/10 * * * * ?t\0ryTask.ryNoParamst\0DEFAULTsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0系统默认（无参）t\03t\01x\0'),

('RuoyiScheduler','TASK_CLASS_NAME2','DEFAULT',NULL,'org.antrain.quartz.util.QuartzDisallowConcurrentExecution','0','1','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0 org.antrain.quartz.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0)org.antrain.common.core.domain.BaseEntity\0\0\0\0\0\0\0\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0x��xpt\0\0pppt\01t\00/15 * * * * ?t\0ryTask.ryParams(\'ry\')t\0DEFAULTsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0系统默认（有参）t\03t\01x\0'),

('RuoyiScheduler','TASK_CLASS_NAME3','DEFAULT',NULL,'org.antrain.quartz.util.QuartzDisallowConcurrentExecution','0','1','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0 org.antrain.quartz.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0)org.antrain.common.core.domain.BaseEntity\0\0\0\0\0\0\0\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0x��xpt\0\0pppt\01t\00/20 * * * * ?t\08ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)t\0DEFAULTsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0系统默认（多参）t\03t\01x\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`sched_name`,`lock_name`) values 

('RuoyiScheduler','STATE_ACCESS'),

('RuoyiScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint NOT NULL,
  `checkin_interval` bigint NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`sched_name`,`instance_name`,`last_checkin_time`,`checkin_interval`) values 

('RuoyiScheduler','LAPTOP-QOH6NISR1618979660339',1618979815260,15000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint NOT NULL,
  `repeat_interval` bigint NOT NULL,
  `times_triggered` bigint NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int DEFAULT NULL,
  `int_prop_2` int DEFAULT NULL,
  `long_prop_1` bigint DEFAULT NULL,
  `long_prop_2` bigint DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint DEFAULT NULL,
  `prev_fire_time` bigint DEFAULT NULL,
  `priority` int DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint NOT NULL,
  `end_time` bigint DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`sched_name`,`trigger_name`,`trigger_group`,`job_name`,`job_group`,`description`,`next_fire_time`,`prev_fire_time`,`priority`,`trigger_state`,`trigger_type`,`start_time`,`end_time`,`calendar_name`,`misfire_instr`,`job_data`) values 

('RuoyiScheduler','TASK_CLASS_NAME1','DEFAULT','TASK_CLASS_NAME1','DEFAULT',NULL,1618979660000,-1,5,'PAUSED','CRON',1618979660000,0,NULL,2,''),

('RuoyiScheduler','TASK_CLASS_NAME2','DEFAULT','TASK_CLASS_NAME2','DEFAULT',NULL,1618979670000,-1,5,'PAUSED','CRON',1618979660000,0,NULL,2,''),

('RuoyiScheduler','TASK_CLASS_NAME3','DEFAULT','TASK_CLASS_NAME3','DEFAULT',NULL,1618979660000,-1,5,'PAUSED','CRON',1618979660000,0,NULL,2,'');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`config_id`,`config_name`,`config_key`,`config_value`,`config_type`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2021-04-07 14:40:58','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),

(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2021-04-07 14:40:58','',NULL,'初始化密码 123456'),

(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2021-04-07 14:40:58','',NULL,'深色主题theme-dark，浅色主题theme-light');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`parent_id`,`ancestors`,`dept_name`,`order_num`,`leader`,`phone`,`email`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values 

(100,0,'0','课后邂逅校园交友平台',0,'万孝国','15733518352','2464210826@qq.com','0','0','admin','2021-04-07 14:40:57','admin','2021-04-07 14:56:02'),

(101,100,'0,100','开发部门',1,'万孝国','15733518352','2464210826@qq.com','0','0','admin','2021-04-07 14:40:57','admin','2021-04-07 14:40:57'),

(102,100,'0,100','测试部门',2,'万孝国','15733518352','2464210826@qq.com','0','0','admin','2021-04-07 14:40:57','admin','2021-04-07 14:40:57');

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,1,'男','0','sys_user_sex','','','Y','0','admin','2021-04-07 14:40:58','',NULL,'性别男'),

(2,2,'女','1','sys_user_sex','','','N','0','admin','2021-04-07 14:40:58','',NULL,'性别女'),

(3,3,'未知','2','sys_user_sex','','','N','0','admin','2021-04-07 14:40:58','',NULL,'性别未知'),

(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2021-04-07 14:40:58','',NULL,'显示菜单'),

(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'隐藏菜单'),

(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2021-04-07 14:40:58','',NULL,'正常状态'),

(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'停用状态'),

(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2021-04-07 14:40:58','',NULL,'正常状态'),

(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'停用状态'),

(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2021-04-07 14:40:58','',NULL,'默认分组'),

(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2021-04-07 14:40:58','',NULL,'系统分组'),

(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2021-04-07 14:40:58','',NULL,'系统默认是'),

(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'系统默认否'),

(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2021-04-07 14:40:58','',NULL,'通知'),

(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2021-04-07 14:40:58','',NULL,'公告'),

(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2021-04-07 14:40:58','',NULL,'正常状态'),

(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'关闭状态'),

(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2021-04-07 14:40:58','',NULL,'新增操作'),

(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2021-04-07 14:40:58','',NULL,'修改操作'),

(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'删除操作'),

(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2021-04-07 14:40:58','',NULL,'授权操作'),

(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2021-04-07 14:40:58','',NULL,'导出操作'),

(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2021-04-07 14:40:58','',NULL,'导入操作'),

(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'强退操作'),

(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2021-04-07 14:40:58','',NULL,'生成操作'),

(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'清空操作'),

(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2021-04-07 14:40:58','',NULL,'正常状态'),

(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2021-04-07 14:40:58','',NULL,'停用状态');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb3 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`dict_id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,'用户性别','sys_user_sex','0','admin','2021-04-07 14:40:58','',NULL,'用户性别列表'),

(2,'菜单状态','sys_show_hide','0','admin','2021-04-07 14:40:58','',NULL,'菜单状态列表'),

(3,'系统开关','sys_normal_disable','0','admin','2021-04-07 14:40:58','',NULL,'系统开关列表'),

(4,'任务状态','sys_job_status','0','admin','2021-04-07 14:40:58','',NULL,'任务状态列表'),

(5,'任务分组','sys_job_group','0','admin','2021-04-07 14:40:58','',NULL,'任务分组列表'),

(6,'系统是否','sys_yes_no','0','admin','2021-04-07 14:40:58','',NULL,'系统是否列表'),

(7,'通知类型','sys_notice_type','0','admin','2021-04-07 14:40:58','',NULL,'通知类型列表'),

(8,'通知状态','sys_notice_status','0','admin','2021-04-07 14:40:58','',NULL,'通知状态列表'),

(9,'操作类型','sys_oper_type','0','admin','2021-04-07 14:40:58','',NULL,'操作类型列表'),

(10,'系统状态','sys_common_status','0','admin','2021-04-07 14:40:58','',NULL,'登录状态列表'),

(100,'是否有效','table_status','0','admin','2021-04-11 22:45:21','',NULL,'表的一条记录是否有效');

/*Table structure for table `sys_job` */

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3 COMMENT='定时任务调度表';

/*Data for the table `sys_job` */

insert  into `sys_job`(`job_id`,`job_name`,`job_group`,`invoke_target`,`cron_expression`,`misfire_policy`,`concurrent`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2021-04-07 14:40:58','',NULL,''),

(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2021-04-07 14:40:58','',NULL,''),

(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2021-04-07 14:40:58','',NULL,'');

/*Table structure for table `sys_job_log` */

DROP TABLE IF EXISTS `sys_job_log`;

CREATE TABLE `sys_job_log` (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='定时任务调度日志表';

/*Data for the table `sys_job_log` */

/*Table structure for table `sys_logininfor` */

DROP TABLE IF EXISTS `sys_logininfor`;

CREATE TABLE `sys_logininfor` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统访问记录';

/*Data for the table `sys_logininfor` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2064 DEFAULT CHARSET=utf8mb3 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,'系统管理',0,1,'system',NULL,1,0,'M','0','0','','system','admin','2021-04-07 14:40:57','',NULL,'系统管理目录'),

(100,'用户管理',1,1,'user','system/user/index',1,0,'C','0','0','system:user:list','user','admin','2021-04-07 14:40:57','',NULL,'用户管理菜单'),

(101,'角色管理',1,2,'role','system/role/index',1,0,'C','0','0','system:role:list','peoples','admin','2021-04-07 14:40:57','',NULL,'角色管理菜单'),

(102,'权限管理',1,3,'menu','system/menu/index',1,0,'C','0','0','system:menu:list','tree-table','admin','2021-04-07 14:40:57','admin','2021-05-11 11:24:02','菜单管理菜单'),

(103,'部门管理',1,4,'dept','system/dept/index',1,0,'C','0','0','system:dept:list','tree','admin','2021-04-07 14:40:57','',NULL,'部门管理菜单'),

(104,'岗位管理',1,5,'post','system/post/index',1,0,'C','0','0','system:post:list','post','admin','2021-04-07 14:40:57','',NULL,'岗位管理菜单'),

(107,'通知公告',1,8,'notice','system/notice/index',1,0,'C','0','0','system:notice:list','message','admin','2021-04-07 14:40:57','',NULL,'通知公告菜单'),

(108,'日志管理',0,9,'log','',1,0,'M','0','0','','log','admin','2021-04-07 14:40:57','admin','2021-04-11 14:08:20','日志管理菜单'),

(500,'操作日志',108,1,'operlog','monitor/operlog/index',1,0,'C','0','0','monitor:operlog:list','form','admin','2021-04-07 14:40:57','',NULL,'操作日志菜单'),

(501,'登录日志',108,2,'logininfor','monitor/logininfor/index',1,0,'C','0','0','monitor:logininfor:list','logininfor','admin','2021-04-07 14:40:57','',NULL,'登录日志菜单'),

(1001,'用户查询',100,1,'','',1,0,'F','0','0','system:user:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1002,'用户新增',100,2,'','',1,0,'F','0','0','system:user:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1003,'用户修改',100,3,'','',1,0,'F','0','0','system:user:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1004,'用户删除',100,4,'','',1,0,'F','0','0','system:user:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1005,'用户导出',100,5,'','',1,0,'F','0','0','system:user:export','#','admin','2021-04-07 14:40:57','',NULL,''),

(1006,'用户导入',100,6,'','',1,0,'F','0','0','system:user:import','#','admin','2021-04-07 14:40:57','',NULL,''),

(1007,'重置密码',100,7,'','',1,0,'F','0','0','system:user:resetPwd','#','admin','2021-04-07 14:40:57','',NULL,''),

(1008,'角色查询',101,1,'','',1,0,'F','0','0','system:role:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1009,'角色新增',101,2,'','',1,0,'F','0','0','system:role:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1010,'角色修改',101,3,'','',1,0,'F','0','0','system:role:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1011,'角色删除',101,4,'','',1,0,'F','0','0','system:role:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1012,'角色导出',101,5,'','',1,0,'F','0','0','system:role:export','#','admin','2021-04-07 14:40:57','',NULL,''),

(1013,'菜单查询',102,1,'','',1,0,'F','0','0','system:menu:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1014,'菜单新增',102,2,'','',1,0,'F','0','0','system:menu:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1015,'菜单修改',102,3,'','',1,0,'F','0','0','system:menu:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1016,'菜单删除',102,4,'','',1,0,'F','0','0','system:menu:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1017,'部门查询',103,1,'','',1,0,'F','0','0','system:dept:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1018,'部门新增',103,2,'','',1,0,'F','0','0','system:dept:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1019,'部门修改',103,3,'','',1,0,'F','0','0','system:dept:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1020,'部门删除',103,4,'','',1,0,'F','0','0','system:dept:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1021,'岗位查询',104,1,'','',1,0,'F','0','0','system:post:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1022,'岗位新增',104,2,'','',1,0,'F','0','0','system:post:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1023,'岗位修改',104,3,'','',1,0,'F','0','0','system:post:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1024,'岗位删除',104,4,'','',1,0,'F','0','0','system:post:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1025,'岗位导出',104,5,'','',1,0,'F','0','0','system:post:export','#','admin','2021-04-07 14:40:57','',NULL,''),

(1026,'字典查询',105,1,'#','',1,0,'F','0','0','system:dict:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1027,'字典新增',105,2,'#','',1,0,'F','0','0','system:dict:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1028,'字典修改',105,3,'#','',1,0,'F','0','0','system:dict:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1029,'字典删除',105,4,'#','',1,0,'F','0','0','system:dict:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1030,'字典导出',105,5,'#','',1,0,'F','0','0','system:dict:export','#','admin','2021-04-07 14:40:57','',NULL,''),

(1031,'参数查询',106,1,'#','',1,0,'F','0','0','system:config:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1032,'参数新增',106,2,'#','',1,0,'F','0','0','system:config:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1033,'参数修改',106,3,'#','',1,0,'F','0','0','system:config:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1034,'参数删除',106,4,'#','',1,0,'F','0','0','system:config:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1035,'参数导出',106,5,'#','',1,0,'F','0','0','system:config:export','#','admin','2021-04-07 14:40:57','',NULL,''),

(1036,'公告查询',107,1,'#','',1,0,'F','0','0','system:notice:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1037,'公告新增',107,2,'#','',1,0,'F','0','0','system:notice:add','#','admin','2021-04-07 14:40:57','',NULL,''),

(1038,'公告修改',107,3,'#','',1,0,'F','0','0','system:notice:edit','#','admin','2021-04-07 14:40:57','',NULL,''),

(1039,'公告删除',107,4,'#','',1,0,'F','0','0','system:notice:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1040,'操作查询',500,1,'#','',1,0,'F','0','0','monitor:operlog:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1041,'操作删除',500,2,'#','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1042,'日志导出',500,4,'#','',1,0,'F','0','0','monitor:operlog:export','#','admin','2021-04-07 14:40:57','',NULL,''),

(1043,'登录查询',501,1,'#','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2021-04-07 14:40:57','',NULL,''),

(1044,'登录删除',501,2,'#','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2021-04-07 14:40:57','',NULL,''),

(1045,'日志导出',501,3,'#','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2021-04-07 14:40:57','',NULL,''),

(2000,'用户管理',0,5,'user',NULL,1,0,'M','0','0',NULL,'user','admin','2021-04-11 14:14:14','',NULL,''),

(2001,'社区版块',0,6,'community',NULL,1,0,'M','0','0',NULL,'build','admin','2021-04-11 14:17:54','',NULL,''),

(2002,'文章版块',0,7,'article',NULL,1,0,'M','0','0',NULL,'education','admin','2021-04-11 22:47:22','',NULL,''),

(2009,'文章管理',2002,1,'article','article/article/index',1,0,'C','0','0','article:article:list','#','admin','2021-04-11 23:22:10','',NULL,'文章菜单'),

(2010,'文章查询',2009,1,'#','',1,0,'F','0','0','article:article:query','#','admin','2021-04-11 23:22:10','',NULL,''),

(2011,'文章新增',2009,2,'#','',1,0,'F','0','0','article:article:add','#','admin','2021-04-11 23:22:10','',NULL,''),

(2012,'文章修改',2009,3,'#','',1,0,'F','0','0','article:article:edit','#','admin','2021-04-11 23:22:10','',NULL,''),

(2013,'文章删除',2009,4,'#','',1,0,'F','0','0','article:article:remove','#','admin','2021-04-11 23:22:10','',NULL,''),

(2014,'文章导出',2009,5,'#','',1,0,'F','0','0','article:article:export','#','admin','2021-04-11 23:22:10','',NULL,''),

(2015,'文章评论',2002,1,'comment','article/comment/index',1,0,'C','0','0','article:comment:list','#','admin','2021-04-11 23:22:31','',NULL,'文章评论菜单'),

(2016,'文章评论查询',2015,1,'#','',1,0,'F','0','0','article:comment:query','#','admin','2021-04-11 23:22:31','',NULL,''),

(2017,'文章评论新增',2015,2,'#','',1,0,'F','0','0','article:comment:add','#','admin','2021-04-11 23:22:31','',NULL,''),

(2018,'文章评论修改',2015,3,'#','',1,0,'F','0','0','article:comment:edit','#','admin','2021-04-11 23:22:31','',NULL,''),

(2019,'文章评论删除',2015,4,'#','',1,0,'F','0','0','article:comment:remove','#','admin','2021-04-11 23:22:31','',NULL,''),

(2020,'文章评论导出',2015,5,'#','',1,0,'F','0','0','article:comment:export','#','admin','2021-04-11 23:22:31','',NULL,''),

(2021,'帖子管理',2001,2,'invitation','community/invitation/index',1,0,'C','0','0','community:invitation:list','#','admin','2021-04-11 23:23:16','',NULL,'帖子菜单'),

(2022,'帖子查询',2021,1,'#','',1,0,'F','0','0','community:invitation:query','#','admin','2021-04-11 23:23:16','',NULL,''),

(2023,'帖子新增',2021,2,'#','',1,0,'F','0','0','community:invitation:add','#','admin','2021-04-11 23:23:16','',NULL,''),

(2024,'帖子修改',2021,3,'#','',1,0,'F','0','0','community:invitation:edit','#','admin','2021-04-11 23:23:16','',NULL,''),

(2025,'帖子删除',2021,4,'#','',1,0,'F','0','0','community:invitation:remove','#','admin','2021-04-11 23:23:16','',NULL,''),

(2026,'帖子导出',2021,5,'#','',1,0,'F','0','0','community:invitation:export','#','admin','2021-04-11 23:23:16','',NULL,''),

(2033,'标签管理',2002,1,'lable','article/lable/index',1,0,'C','0','0','article:lable:list','#','admin','2021-04-11 23:24:14','',NULL,'标签菜单'),

(2034,'标签查询',2033,1,'#','',1,0,'F','0','0','article:lable:query','#','admin','2021-04-11 23:24:14','',NULL,''),

(2035,'标签新增',2033,2,'#','',1,0,'F','0','0','article:lable:add','#','admin','2021-04-11 23:24:14','',NULL,''),

(2036,'标签修改',2033,3,'#','',1,0,'F','0','0','article:lable:edit','#','admin','2021-04-11 23:24:14','',NULL,''),

(2037,'标签删除',2033,4,'#','',1,0,'F','0','0','article:lable:remove','#','admin','2021-04-11 23:24:14','',NULL,''),

(2038,'标签导出',2033,5,'#','',1,0,'F','0','0','article:lable:export','#','admin','2021-04-11 23:24:14','',NULL,''),

(2045,'主题管理',2001,1,'topic','community/topic/index',1,0,'C','0','0','community:topic:list','#','admin','2021-04-11 23:24:45','',NULL,'主题菜单'),

(2046,'主题查询',2045,1,'#','',1,0,'F','0','0','community:topic:query','#','admin','2021-04-11 23:24:45','',NULL,''),

(2047,'主题新增',2045,2,'#','',1,0,'F','0','0','community:topic:add','#','admin','2021-04-11 23:24:45','',NULL,''),

(2048,'主题修改',2045,3,'#','',1,0,'F','0','0','community:topic:edit','#','admin','2021-04-11 23:24:45','',NULL,''),

(2049,'主题删除',2045,4,'#','',1,0,'F','0','0','community:topic:remove','#','admin','2021-04-11 23:24:45','',NULL,''),

(2050,'主题导出',2045,5,'#','',1,0,'F','0','0','community:topic:export','#','admin','2021-04-11 23:24:45','',NULL,''),

(2051,'用户管理',2000,1,'user','user/user/index',1,0,'C','0','0','user:user:list','#','admin','2021-04-11 23:25:02','',NULL,'用户菜单'),

(2052,'用户查询',2051,1,'#','',1,0,'F','0','0','user:user:query','#','admin','2021-04-11 23:25:02','',NULL,''),

(2053,'用户新增',2051,2,'#','',1,0,'F','0','0','user:user:add','#','admin','2021-04-11 23:25:02','',NULL,''),

(2054,'用户修改',2051,3,'#','',1,0,'F','0','0','user:user:edit','#','admin','2021-04-11 23:25:02','',NULL,''),

(2055,'用户删除',2051,4,'#','',1,0,'F','0','0','user:user:remove','#','admin','2021-04-11 23:25:02','',NULL,''),

(2056,'用户导出',2051,5,'#','',1,0,'F','0','0','user:user:export','#','admin','2021-04-11 23:25:02','',NULL,''),

(2057,'用户积分记录',2000,1,'integral','user/integral/index',1,0,'C','0','0','user:integral:list','#','admin','2021-04-12 09:06:49','',NULL,'用户积分记录菜单'),

(2058,'用户积分记录查询',2057,1,'#','',1,0,'F','0','0','user:integral:query','#','admin','2021-04-12 09:06:49','',NULL,''),

(2059,'用户积分记录新增',2057,2,'#','',1,0,'F','0','0','user:integral:add','#','admin','2021-04-12 09:06:49','',NULL,''),

(2060,'用户积分记录修改',2057,3,'#','',1,0,'F','0','0','user:integral:edit','#','admin','2021-04-12 09:06:49','',NULL,''),

(2061,'用户积分记录删除',2057,4,'#','',1,0,'F','0','0','user:integral:remove','#','admin','2021-04-12 09:06:49','',NULL,''),

(2062,'用户积分记录导出',2057,5,'#','',1,0,'F','0','0','user:integral:export','#','admin','2021-04-12 09:06:49','',NULL,''),

(2063,'评论管理',2001,3,'invitationCommnet','community/comment/index',1,0,'C','0','0','community:comment:list','#','admin','2021-04-11 23:23:16','',NULL,'帖子评论菜单');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`notice_id`,`notice_title`,`notice_type`,`notice_content`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(10,'test','1','<p>test</p>','0','admin','2021-04-18 20:51:23','',NULL,NULL);

/*Table structure for table `sys_oper_log` */

DROP TABLE IF EXISTS `sys_oper_log`;

CREATE TABLE `sys_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='操作日志记录';

/*Data for the table `sys_oper_log` */

/*Table structure for table `sys_post` */

DROP TABLE IF EXISTS `sys_post`;

CREATE TABLE `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='岗位信息表';

/*Data for the table `sys_post` */

insert  into `sys_post`(`post_id`,`post_code`,`post_name`,`post_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,'coder','程序员',1,'0','admin','2021-04-07 14:40:57','',NULL,''),

(2,'se','项目经理',2,'0','admin','2021-04-07 14:40:57','',NULL,''),

(3,'hr','人力资源',3,'0','admin','2021-04-07 14:40:57','',NULL,''),

(4,'user','普通员工',4,'0','admin','2021-04-07 14:40:57','',NULL,'');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`data_scope`,`menu_check_strictly`,`dept_check_strictly`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2021-04-07 14:40:57','',NULL,'超级管理员'),

(2,'普通角色','common',2,'5',1,1,'0','0','admin','2021-04-07 14:40:57','','2021-05-11 10:28:42','普通角色');

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和部门关联表';

/*Data for the table `sys_role_dept` */

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values 

(2,1),

(2,2),

(2,3),

(2,4),

(2,100),

(2,101),

(2,102),

(2,103),

(2,104),

(2,105),

(2,106),

(2,107),

(2,108),

(2,109),

(2,110),

(2,111),

(2,112),

(2,113),

(2,114),

(2,115),

(2,116),

(2,500),

(2,501),

(2,1000),

(2,1001),

(2,1002),

(2,1003),

(2,1004),

(2,1005),

(2,1006),

(2,1007),

(2,1008),

(2,1009),

(2,1010),

(2,1011),

(2,1012),

(2,1013),

(2,1014),

(2,1015),

(2,1016),

(2,1017),

(2,1018),

(2,1019),

(2,1020),

(2,1021),

(2,1022),

(2,1023),

(2,1024),

(2,1025),

(2,1026),

(2,1027),

(2,1028),

(2,1029),

(2,1030),

(2,1031),

(2,1032),

(2,1033),

(2,1034),

(2,1035),

(2,1036),

(2,1037),

(2,1038),

(2,1039),

(2,1040),

(2,1041),

(2,1042),

(2,1043),

(2,1044),

(2,1045),

(2,1046),

(2,1047),

(2,1048),

(2,1049),

(2,1050),

(2,1051),

(2,1052),

(2,1053),

(2,1054),

(2,1055),

(2,1056),

(2,1057),

(2,1058),

(2,1059),

(2,1060);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`dept_id`,`user_name`,`nick_name`,`user_type`,`email`,`phonenumber`,`sex`,`avatar`,`password`,`status`,`del_flag`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 

(1,102,'admin','万孝国','00','2464210826@qq.com','15733518352','1','/profile/avatar/2021/04/07/06a05c92-a718-4f00-8c25-afb0c1765b06.jpeg','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2021-04-07 14:40:57','admin','2021-04-07 14:40:57','',NULL,'管理员'),

(2,101,'test','antrain','00','test@qq.com','15666666666','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2021-04-07 14:40:57','admin','2021-04-07 14:40:57','admin','2021-05-11 10:29:01','测试员');

/*Table structure for table `sys_user_post` */

DROP TABLE IF EXISTS `sys_user_post`;

CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户与岗位关联表';

/*Data for the table `sys_user_post` */

insert  into `sys_user_post`(`user_id`,`post_id`) values 

(1,1),

(2,2);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 

(1,1);

/*Table structure for table `topic` */

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主题主键',
  `title` varchar(100) DEFAULT '' COMMENT '标题',
  `user_id` bigint DEFAULT NULL COMMENT '创办用户id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '创办用户昵称',
  `cover` varchar(200) DEFAULT '' COMMENT '封面',
  `description` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
  `file_url` varchar(200) DEFAULT '' COMMENT '申请文件',
  `reason` varchar(500) DEFAULT '' COMMENT '理由',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `refine` char(1) DEFAULT 'N' COMMENT '是否加精，会显示在菜单栏里',
  `status` char(1) DEFAULT 'A' COMMENT '是否有效：A 申请中  Y有效 默认， N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COMMENT='主题表';

/*Data for the table `topic` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息主键',
  `username` varchar(100) DEFAULT '' COMMENT '用户名',
  `nickname` varchar(100) DEFAULT '' COMMENT '用户昵称',
  `name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `password` varchar(200) DEFAULT '' COMMENT '密码',
  `sex` char(10) DEFAULT '未知' COMMENT '性别',
  `telephone` char(15) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `school` varchar(100) DEFAULT NULL COMMENT '学校',
  `dept` varchar(100) DEFAULT NULL COMMENT '院系',
  `major` varchar(100) DEFAULT NULL COMMENT '专业',
  `address` varchar(100) DEFAULT NULL COMMENT '家庭地址',
  `contact` varchar(100) DEFAULT NULL COMMENT '现居住地',
  `introduce` varchar(1000) DEFAULT NULL COMMENT '介绍',
  `interest` varchar(1000) DEFAULT NULL COMMENT '兴趣爱好',
  `integral` int DEFAULT '0' COMMENT '用户积分',
  `status` char(1) DEFAULT 'Y' COMMENT '状态 Y 有效 N 无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

/*Data for the table `user` */

