package com.yaoqian.mini_alipay.tools;
import java.util.Map;
import com.yaoqian.mini_alipay.entity.ResultEntity;
public class ResultTools {
    /****
     * 错误码记录：
     * 0--------成功
     * 1001-----请求传参错误
     * 1002-----没有对应内容
     * 1003-----此用户已存在
     * 1004-----上传文件为空
     * 404------异常抛出错误
     *
     */

    /**
     * @param status--返回码
     * @param message---404服务器内部异常时提示消息(返回码不是404时传空即可)
     * @param map------数据源
     * @return
     */
    public static ResultEntity result(int status, String message, Map<String, Object> map) {
        ResultEntity model = new ResultEntity();
        model.setStatus(status);
        switch (status) {
            case 0:
                model.setMessage("成功");
                if (map != null) {
                    model.setData(map);
                }
                break;
            case 1001:
                model.setMessage("请求传参错误 ");
                break;
            case 1002:
                model.setMessage("没有对应内容 ");
                break;
            case 1003:
                model.setMessage("此用户已存在");
                break;
            case 1004:
                model.setMessage("上传文件为空");
                break;
            case 404:
                model.setMessage(message);
                break;
            default:
                model.setMessage("未知错误");
                break;
        }
        return model;
    }
}
