package com.daqsoft.util;

import com.daqsoft.bean.Doc;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocUtil {

    public static void main(String[] args) {
        List<Doc> docInfo = getDocInfo();
        for (Doc doc: docInfo){
            System.out.println(doc);
        }
    }

    public static List<Doc> getDocInfo() {
        ArrayList<Doc> docList = new ArrayList<Doc>();
        File file = new File("C:\\Users\\daqsoft\\Desktop\\doc.txt");
        try {
            List<String> lines = FileUtils.readLines(file);
            for(String line: lines){
                Doc doc = new Doc();
                String[] splits = line.split("\t");
                int parseInt = Integer.parseInt(splits[0].trim());
                doc.setId(parseInt);
                doc.setTitle(splits[1].trim());
                doc.setAuthor(splits[2].trim());
                doc.setDescribe(splits[3].trim());
                doc.setContent(splits[3].trim());

                docList.add(doc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return docList;
    }
}
