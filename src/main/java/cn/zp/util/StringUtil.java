package cn.zp.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    /**
     * 判断是否是空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否不是空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化模糊查询
     *
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 过滤掉集合里的空格
     *
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String l : list) {
            if (isNotEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }

    /**
     * 去除html标签
     *
     * @param content
     * @return
     */
    public static String stripHtml(String content) {
        // <p>标签替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br></br>标签替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其他的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        // 去掉空格
        content = content.replaceAll(" ", "");

        return content;
    }

}
