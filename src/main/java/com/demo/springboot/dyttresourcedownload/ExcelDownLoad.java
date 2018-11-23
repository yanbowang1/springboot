package com.demo.springboot.dyttresourcedownload;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: user
 * @Date: 2018/11/16
 * @Description: 结果保存在Excel
 */
public class ExcelDownLoad {

    public static void writeWithHead(String orgin, List<List<String>> lists) throws Exception {
        try (
                OutputStream out = new FileOutputStream("e:\\dytt.xlsx");
                ) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0, ExcelPropertyIndexModel.class);
            sheet1.setSheetName(orgin);
            List<ExcelPropertyIndexModel> data = new ArrayList<>();
            for(List<String> list : lists){
                if(list.size() == 2){
                    System.out.println("hahha");
                }
                ExcelPropertyIndexModel item = new ExcelPropertyIndexModel();
                item.setName(list.get(0));
                item.setImdb(list.get(1));
                item.setDouban(list.get(2));
                item.setLink(list.get(3));
                data.add(item);
            }
            if(new File("e\\withHead.xlsx").exists()){

            }else{
                writer.write(data, sheet1);
            }
            writer.finish();
        }
    }

    public static void main(String[] args) throws Exception {
        //writeWithHead("test", null);
        int i = 100;
        for(int j = 0; j < i; j++){
            if(j%5 == 0){
                System.out.println(j);
            }

        }
    }
}
