package com.yaoqian.mini_alipay.tools;
import java.util.Map;
import com.yaoqian.mini_alipay.entity.ResultEntity;
public class ResultTools {
    /**
     * 错误码记录：
     * 0--------成功
     * 1001-----请求传参错误
     * 1002-----没有对应内容
     * 1003-----此用户已存在
     * 1004-----上传文件为空
     * 1005-----没有登录信息
     * 404------异常抛出错误
     *
     * @param status--返回码
     * @param message---404服务器内部异常时提示消息(返回码不是404时传空即可)
     * @param map------数据源
     * @return ResultEntity---返回类
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
            case 1005:
                model.setMessage("没有登陆信息");
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

    public static ResultEntity error(int status, String message) {
        return result(status, message,null);
    }

    public static ResultEntity error(int status) {
        return result(status, "",null);
    }

    public static ResultEntity error(String message) {
        return result(404, message,null);
    }

    public static ResultEntity success(String message,Map<String, Object> map) {
        return result(0, message,map);
    }

    public static ResultEntity success(String message) {
        return result(0,message,null);
    }

    public static ResultEntity success(Map<String, Object> map) {
        return result(0, "",map);
    }

    public static ResultEntity success() {
        return result(0, "",null);
    }
}
