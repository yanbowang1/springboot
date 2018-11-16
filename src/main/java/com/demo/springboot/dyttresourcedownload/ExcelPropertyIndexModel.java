package com.demo.springboot.dyttresourcedownload;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Auther: user
 * @Date: 2018/11/16
 * @Description:
 */
@Data
public class ExcelPropertyIndexModel extends BaseRowModel {

    @ExcelProperty(value = "电影名称", index = 0)
    private String name;

    @ExcelProperty(value = "IMDb评分", index = 1)
    private String imdb;

    @ExcelProperty(value = "豆瓣评分", index = 2)
    private String douban;

    @ExcelProperty(value = "下载链接", index = 3)
    private String link;
}
