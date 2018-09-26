package com.migu.online.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;  
  
  
  
/**  
 * 利用开源组件POI3.17动态导出EXCEL文档  
 *   
 * @version v1.0  
 * @param <T>  
 *            应用泛型，代表任意一个符合javabean风格的类  
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()  
 *            byte[]表jpg格式的图片数据  
 */  
public class ExportExcel{  
    //文件分隔符"\"（在 UNIX 系统中是“/”）  
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");  
  
    /**  
     * @param jsonStr  
     *          json字符串  
     * @param sheaders  
     *          表格表头  
     * @param fileName  
     *          文件名XXX.xls  
     * @param title  
     *          表格标题名  
     * @param exportMappers 
     * 			字段值映射
     */  
    public void export(String jsonStr,String headerDesc,String sheaders,String fileName,String title,List<ExportMapper> exportMappers, HttpServletResponse response,HttpServletRequest request) {  
        try {  
            // 将json字符串转换为json对象  
            JSONArray jsonArray = new JSONArray(jsonStr);         
            String[] headers= sheaders.substring(0,sheaders.length()-1).split(",");  
            String[] headerDescs= headerDesc.substring(0,headerDesc.length()-1).split(",");  
            int iSize = jsonArray.length();  
            List<List> list = new ArrayList<List>();  
            for (int i = 0; i < iSize; i++) {  
                List<String> line = new ArrayList<String>();  
                JSONObject jsonObject =  jsonArray.getJSONObject(i);  
                Iterator iterator = jsonObject.keys();  
               
                int j=0;  
                while (iterator.hasNext()) {  
                    iterator.next();  
                    if (j<headers.length) {
                    	 String value = "";  
                    	if (!jsonObject.isNull(headers[j])) {
                    		 value = jsonObject.getString(headers[j]);  
                        	 //转换字段值映射
                        	 if (exportMappers.size() > 0) {
                        		 outer:for (ExportMapper exportMapper:exportMappers) {
                        			 if (exportMapper.getName().equals(headers[j])) {
                        				 Map<String,String> map = exportMapper.getMap();
                        				 Iterator<Entry<String, String>> ite = map.entrySet().iterator();
                        				 while(ite.hasNext()) {
                        					 Entry<String, String> entry = ite.next();
                        					 if (entry.getKey().equals(value)) {
                        						 value = entry.getValue();
                        						 break outer;
                        					 }
                        				 }
                        			 }
                        			 exportMapper.getName();
                        		 }
                        	 }
                        	
                    	}
                    	   //表格内容  
                        line.add(value);  
                         j++;  
                    }
                   
                }  
                list.add(line);  
            }  
              
              
            String docsPath = request.getSession().getServletContext().getRealPath("exportExcel");  
      
            //文件路径  
            String filePath = docsPath + FILE_SEPARATOR + fileName;  
              
            OutputStream out = new FileOutputStream(filePath);  
            exportExcel(title,headerDescs, list, out);  
            out.close();  
           // JOptionPane.showMessageDialog(null, "导出成功!");  
              
            //下载  
            download(filePath, response);  
              
        } catch (JSONException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (FileNotFoundException e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }  
    }  
    /**  
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上  
     *   
     * @param title  
     *            表格标题名  
     * @param headers  
     *            表格属性列名数组  
     * @param list  
     *            需要显示的数据集合  
     * @param out  
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中  
     */  
  
    private void exportExcel(String title, String[] headerDescs,List<List> list, OutputStream out) {  
    	  
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 15);  
        // 生成一个样式  
        CellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index); 
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
        style.setBorderBottom(BorderStyle.THIN);  
        style.setBorderLeft(BorderStyle.THIN);  
        style.setBorderRight(BorderStyle.THIN);  
        style.setBorderTop(BorderStyle.THIN);  
        style.setAlignment(HorizontalAlignment.CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
       // font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBold(true);; 
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
        style.setBorderBottom(BorderStyle.THIN);  
        style.setBorderLeft(BorderStyle.THIN);  
        style.setBorderRight(BorderStyle.THIN);  
        style.setBorderTop(BorderStyle.THIN);  
        style.setAlignment(HorizontalAlignment.CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
      //  font2.set  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
        // 声明一个画图的顶级管理器  
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        // 定义注释的大小和位置,详见文档  
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,  
                0, 0, 0, (short) 4, 2, (short) 6, 5));  
        // 设置注释内容  
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));  
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.  
        comment.setAuthor("leno");  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (short i = 0; i < headerDescs.length; i++) {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headerDescs[i]);  
            cell.setCellValue(text);  
        }  
        // 遍历集合数据，产生数据行  
        for(int i=0;i<list.size();i++){  
            List<String> line = list.get(i);  
            row = sheet.createRow(i+1);  
            for(int j=0;j<line.size();j++){  
            	HSSFCell cell = row.createCell((short) j);  
                //HSSFCell cell = row.createCell(j);  
                cell.setCellStyle(style2);  
                String value = line.get(j);  
                try {  
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;  
                    // 其它数据类型都当作字符串简单处理  
                    textValue = value.toString();  
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null) {  
                        HSSFRichTextString richString = new HSSFRichTextString(textValue);  
                        HSSFFont font3 = workbook.createFont();  
                        font3.setColor(HSSFColor.BLUE.index);  
                        richString.applyFont(font3);  
                        cell.setCellValue(richString);  
                    }  
                } catch (SecurityException e) {  
                    e.printStackTrace();  
                } finally {  
                    // 清理资源  
                }  
            }  
              
        }  
          
        try {  
            workbook.write(out);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
      
    private void download(String path, HttpServletResponse response) {  
        try {  
            // path是指欲下载的文件的路径。  
            File file = new File(path);  
            // 取得文件名。  
            String filename = file.getName();  
            // 以流的形式下载文件。  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // 清空response  
            response.reset();  
            // 设置response的Header  
            response.addHeader("Content-Disposition", "attachment;filename="+ new String(filename.getBytes()));  
            response.addHeader("Content-Length", "" + file.length());  
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/vnd.ms-excel;charset=gb2312");  
            toClient.write(buffer);  
            toClient.flush();  
            toClient.close();  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
      
      
      
      
}  