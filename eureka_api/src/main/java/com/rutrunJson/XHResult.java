package com.rutrunJson;



import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用结果返回类型
 * @ClassName XHResult
 * @Date: 2018/11/09
 * @version: 1.0.0
 */
@Data
public class XHResult<T> implements Serializable {
    /**
     * 通用成功标志 -- 0
     */
    public static final int SUCCESS_CODE = 0;
    /**
     * 通用成功描述 -- 请求成功
     */
    public static final String SUCCESS_MSG = "请求成功";

    /**
     * 通用失败标志 -- 1
     */
    public static final int FAIL_CODE = 1;
    /**
     * 通用失败描述 -- 请求失败
     */
    public static final String FAIL_MSG = "请求失败";

    /**
     * 通用失败提示类型 -- 弹窗
     */
    public static final Integer FAIL_TYPE = 2;

    private Integer code; //结果结果代码
    private Integer type; //子结果类型
    private String msg;   //中文提示
    private T response;  //实体数据

    private XHResult() {
    }

    private XHResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.type = 0;
    }

    private XHResult(Integer code, String msg, T response, Integer type) {
        this.code = code;
        this.msg = msg;
        this.response = response;
        this.type = type;
    }

    private XHResult(Integer code, String msg, T response) {
        this.code = code;
        this.msg = msg;
        this.response = response;
        this.type = 0;
    }

    ///////成功结果
    public static <T> XHResult<T> success() {
        return new XHResult<T>(SUCCESS_CODE, SUCCESS_MSG);
    }

    /**
     * 自定义成功结果
     *
     * @param response
     * @param <T>
     * @return XHResult对象
     */
    public static <T> XHResult<T> success(T response) {
        return new XHResult<>(SUCCESS_CODE, SUCCESS_MSG, response);
    }

    /**
     * 创建一个修改Msg提示的返回
     *
     * @param msg 提示
     * @return XHResult对象
     */
    public static XHResult<String> successMsg(String msg) {
        return new XHResult<String>(SUCCESS_CODE, msg);
    }

    /**
     * 创建一个Map类型的返回
     *
     * @param key
     * @param value
     * @return XHResult对象
     */
    public static <E> XHResult<Map<String, E>> successMap(String key, E value) {
        Map<String, E> map = new HashMap<>();
        map.put(key, value);
        return success(map);
    }

    /**
     * 创建一个XHPageResult分页列表类型的返回
     *
     * @param total 总数量
     * @param rows  当前页数据
     * @param <T>   vo泛型
     * @return XHResult对象
     */
    public static <T> XHResult<XHPageResult<T>> successPage(Long total, List<T> rows) {
        XHPageResult<T> pageResult = new XHPageResult<>(total, rows);
        return success(pageResult);
    }

    /**
     * 自定义成功结果和子类型
     *
     * @param response
     * @param type
     * @return XHResult对象
     */
    public static <T> XHResult<T> success(T response, Integer type) {
        return new XHResult<>(SUCCESS_CODE, SUCCESS_MSG, response, type);
    }

    /**
     * 自定义成功提示和结果数据
     *
     * @param msg
     * @param response
     * @param <T>
     * @return XHResult对象
     */
    public static <T> XHResult<T> success(String msg, T response) {
        return new XHResult<>(SUCCESS_CODE, msg, response);
    }

    /**
     * 自定义失败提示
     *
     * @param msg
     * @return XHResult对象
     */
    public static <T> XHResult<T> fail(String msg) {
        return new XHResult<>(FAIL_CODE, msg, null);
    }

    ///////失败结果
    public static <T> XHResult<T> fail() {
        return new XHResult<>(FAIL_CODE, FAIL_MSG);
    }

    /**
     * 自定义失败子类型
     *
     * @param type
     * @return XHResult对象
     */
    public static <T> XHResult<T> fail(Integer type) {
        return XHResult.fail(FAIL_MSG, type);
    }

    /**
     * 自定义失败提示和失败子类型
     *
     * @param msg
     * @param type
     * @param <T>
     * @return XHResult对象
     */
    public static <T> XHResult<T> fail(String msg, Integer type) {
        return new XHResult<>(FAIL_CODE, msg, null, type);
    }

    /**
     * 确保类型是map时使用
     *
     * @param key
     * @param value
     * @return XHResult对象
     */
    public XHResult<T> put(String key, Object value) {
        if (this.response instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) this.response;
            map.put(key, value);
        }
        return this;
    }

    public XHResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    //    @JsonIgnore
    public boolean isSuccess() {
        return code.equals(0);
    }

    public XHResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public XHResult<T> setResponse(T response) {
        this.response = response;
        return this;
    }
}
