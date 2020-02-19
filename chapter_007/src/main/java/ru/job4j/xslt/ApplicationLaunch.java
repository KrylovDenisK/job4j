package ru.job4j.xslt;

import ru.job4j.xslt.sax.OperationXML;

/**
 * Launch Data Manipulation Application
 */
public class ApplicationLaunch {
    public static void main(String...arqs) {
        Config config = new Config();
        config.init();
        StoreXML storeXML = new StoreXML("./chapter_007/1.xml");
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.generate(20);
            storeXML.save(storeSQL.load());
            ConvertXSQT.convert("./chapter_007/1.xml", "./chapter_007/2.xml", "./chapter_007/scheme.xsl");
            OperationXML operationXML = new OperationXML();
            operationXML.parse("./chapter_007/2.xml");
            System.out.println(operationXML.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
