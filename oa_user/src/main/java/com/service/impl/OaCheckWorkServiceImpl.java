package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.OaCheckWork;
import com.entity.OaHome;
import com.httputil.HttpApiUtils;
import com.mapper.OaCheckWorkMapper;
import com.mapper.OaHomeMapper;
import com.service.OaCheckWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class OaCheckWorkServiceImpl extends ServiceImpl<OaCheckWorkMapper, OaCheckWork> implements OaCheckWorkService {
    @Autowired
    private OaHomeMapper oaHomeMapper;
    @Autowired
    private OaCheckWorkMapper oaCheckWorkMapper;


    /**
     * 打卡业务处理
     *
     * @param latitude  纬度
     * @param longitude 经度
     */
    @Transactional
    public JSONObject daka(String longitude, String latitude) throws ParseException {

        LambdaQueryWrapper<OaHome> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OaHome::getId, 1);
        OaHome byOaHome = oaHomeMapper.selectOne(wrapper);

        //定义返回参数 message
        JSONObject message = new JSONObject();
        if (byOaHome != null) {
          String mcUrl="https://apis.map.qq.com/ws/place/v1/search?";
            //腾讯地图api 距离计算接口
            String url = "http://apis.map.qq.com/ws/distance/v1/matrix/?mode=driving";
            //起点坐标 from
            String from = latitude + "," + longitude;
            //终点坐标 to
            String to = byOaHome.getLatitude() + "," + byOaHome.getLongitude();
            //腾讯地图api秘钥
            String key = "2FPBZ-NBBKW-ZVSRS-OO7QS-RSCZS-EMB4V";
            //qingjiu
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("from", from);
            jsonObject.put("to", to);
            jsonObject.put("key", key);

            JSONObject post = HttpApiUtils.get(url, jsonObject);
            System.out.println(post);

            if (post.get("status").equals(0)) {
                //当前位置到打卡位置数据转换处理
                JSONObject result = (JSONObject) post.get("result");
                JSONArray rows = (JSONArray) result.get("rows");
                JSONObject elementsindex = (JSONObject) rows.get(0);
                JSONArray elements = (JSONArray) elementsindex.get("elements");
                JSONObject distanceindex = (JSONObject) elements.get(0);
                Long distance = Long.valueOf(distanceindex.get("distance") + "");
                Integer maximum = byOaHome.getMaximum();
                //判断当前位置是否是打卡范围
                if (distance < maximum) {
                     JSONObject mcjson=new JSONObject();
                                mcjson.put("boundary","nearby("+from+",50)");
                                mcjson.put("key",key);
                                mcjson.put("page_size",2);


                    //获取地理位置名称
                    JSONObject mcget = HttpApiUtils.get(mcUrl, mcjson);
                    JSONArray data = (JSONArray)mcget.get("data");
                    JSONObject dizhijson=(JSONObject)data.get(0);

                     String title= dizhijson.get("title")+"";
                    LambdaQueryWrapper<OaCheckWork> check = new LambdaQueryWrapper<>();
                    check.eq(OaCheckWork::getUserId, 1);

                    OaCheckWork oaCheckWork = new OaCheckWork();//  oaCheckWork oaCheckWorkMapper.selectOne(check);
                      Date date = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String format = df.format(date);
                    Integer i=format.compareTo("09:30:59");

                    //判断早上打卡是还是下午打卡

                        oaCheckWork.setClockInTime(date);
                        oaCheckWork.setClockInPosition(title);
                        oaCheckWork.setClockOutTime(date);
                        oaCheckWork.setClockOutPosition(title);

                    if (oaCheckWork != null) {
                       // oaCheckWorkMapper.insert(oaCheckWork);
                    } else {


                    }


                    message.put("status", 1);
                    message.put("distance", distance);
                    message.put("message", "距离小于公司设置参数,打卡成功！");

                } else {
                    message.put("status", 0);
                    message.put("message", "不在指定返回内！");

                }
            } else {
                message.put("status", 0);
                message.put("message", post.get("message"));
            }
        } else {

            message.put("status", 0);
            message.put("message", "联系管理员定义终点坐标！");

        }
        return message;

    }


}

