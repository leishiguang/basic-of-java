package demo.nio.buffer;

import demo.common.FileSizeUtils;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 应用 buffer 进行读写文件的操作，模拟 10GB 数据
 * <p>
 * 参考：如何在5秒内写入10G的文本数据（https://blog.csdn.net/yiifaa/article/details/78128363）
 *
 * @author leishiguang
 * @since v1.0
 */
class BufferReadWriteTest {

    /**
     * 指定文件路径
     */
    private final static String FILE_PATH = "D:/temp/temp.txt";
    /**
     * 这儿调节文件大小，10000000 约为 13GB;
     */
    private final static int LIMITS = 100000;
    private final static String START_WORD = "startWord\n";
    private final static String END_WORD = "endWord";
    private final static String WORD = "1.宝黛：可叹停机德，堪怜咏絮才。玉带林中挂，金簪雪里埋。\n" +
            "2.元春：二十年来辨是非，榴花开处照宫闱。三春争及初春景，虎兕相逢大梦归。\n" +
            "3.探春：才自精明志自高，生于末世运偏消。清明涕送江边望，千里东风一梦遥。\n" +
            "4.湘云：富贵又何为，襁褓之间父母违。展眼吊斜晖，湘江水逝楚云飞。\n" +
            "5.妙玉：欲洁何曾洁，云空未必空。可怜金玉质，终陷淖泥中。\n" +
            "6.迎春：子系中山狼，得志便猖狂。金闺花柳质，一载赴黄粱。\n" +
            "7.王熙凤：凡鸟偏从末世来，都知爱慕此生才。一从二令三人木，哭向金陵事更哀。\n" +
            "8.巧姐：势败休云贵，家亡莫论亲。偶因济刘氏，巧得遇恩人。\n" +
            "9.李纨：桃李春风结子完，到头谁似一盆兰。如冰水好空相妒，枉与他人作笑谈。\n" +
            "10.可卿：情天情海幻情身，情既相逢必主淫。漫言不肖皆荣出，造衅开端实在宁。\n" +
            "11.晴雯：霁月难逢，彩云易散。心比天高，身为下贱。风流灵巧招人怨。寿夭多因毁谤生，多情公子空牵念。\n" +
            "12.袭人：枉自温柔和顺，空云似桂如兰，堪羡优伶有福，谁知公子无缘。\n" +
            "13.香菱：根并荷花一茎香，平生遭际实堪伤。自从两地生孤木，致使香魂返故乡。\n" +
            "14.惜春：勘破三春景不长，缁衣顿改昔年妆。可怜绣户侯门女，独卧青灯古佛旁。\n";

    @Test
    void goWriteAndRead() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("文件未删除");
            }
        }
        if (file.createNewFile()) {
            long startTime = System.currentTimeMillis();
            System.out.println("开始写入，当前时间：" + startTime + "ms");
            write(file);
            long writeEndTime = System.currentTimeMillis();
            System.out.println("写入完成，当前时间：" + writeEndTime + "ms");
            System.out.println("开始读取，当前时间：" + startTime + "ms");
            read(file);
            long readEndTime = System.currentTimeMillis();
            System.out.println("读取完成，当前时间：" + writeEndTime + "ms");
            System.out.println("文件大小：" + FileSizeUtils.getFileLength(file));
            System.out.println("写入耗时：" + (writeEndTime - startTime) + "ms，读取耗时:" + (readEndTime - writeEndTime) + "ms");
        } else {
            System.out.println("创建文件失败");
        }

    }


    /**
     * BufferedWriter 的方式写入数据
     */
    private void write(File file) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(START_WORD);
            for (int i = 0; i < LIMITS; i++) {
                bufferedWriter.write(WORD);
            }
            bufferedWriter.write(END_WORD);
            //等待写入完毕
            bufferedWriter.close();
        }
    }

    /**
     * BufferedReader 的方式按行读入数据
     */
    private void read(File file) throws IOException {
        String firstLine;
        String lastLine;
        String thisLine = "";
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            firstLine = bufferedReader.readLine();
            System.out.println("第一行是:" + firstLine);
            while (true) {
                lastLine = bufferedReader.readLine();
                if (lastLine == null) {
                    System.out.println("最后一行是:" + thisLine);
                    break;
                }
                thisLine = lastLine;
            }
        }
    }
}
