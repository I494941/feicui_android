package com.feicuiedu.android.expressage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 2);
        System.out.println("1+1");
    }

    @Test
    public void testGetCompanys() {


        List<Map<String, String>> result = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("shentong", "申通");
        result.add(map);

        map = new HashMap<>();
        map.put("ems", "EMS");
        result.add(map);


        map = new HashMap<>();
        map.put("shunfeng", "顺丰");
        result.add(map);

        map = new HashMap<>();
        map.put("yuantong", "圆通");
        result.add(map);

        map = new HashMap<>();
        map.put("zhongtong", "中通");
        result.add(map);

        map = new HashMap<>();
        map.put("yunda", "韵达");
        result.add(map);

        map = new HashMap<>();
        map.put("tiantian", "天天");
        result.add(map);

        map = new HashMap<>();
        map.put("huitongkuaidi", "汇通");
        result.add(map);

        map = new HashMap<>();
        map.put("quanfengkuaidi", "全峰");
        result.add(map);

        map = new HashMap<>();
        map.put("debangwuliu", "德邦");
        result.add(map);

        map = new HashMap<>();
        map.put("zhaijisong", "宅急送");
        result.add(map);

        System.out.println(result.toString());
    }


}