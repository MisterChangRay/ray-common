package com.bitcoinhelper.notify;

import com.bitcoinhelper.platform.OKEX;
import com.bitcoinhelper.platform.enums.Coin;
import com.bitcoinhelper.platform.vo.Ticker;
import com.bitcoinhelper.util.EmailBuilder;
import com.ray.common.core.util.JSONUtil;
import com.ray.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.bitcoinhelper.controller.RoleConfigController.ROLE_CONFIG_KEY;

/**
 * 文件名：Notiry.java
 * 修改人：Zhang.Rui
 * 修改时间：2019/7/4
 * 描述：
 */
@Component
public class NotifyService {
    @Autowired
    private OKEX okex;
    private Map<Coin, LinkedList<Ticker>> historyTickers = new HashMap<>(300);
    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    RedisService redisService;

    //每60S扫一次行情
    @Scheduled(cron ="0/60 * * * * ?")
    public void scan10() {
        refreshTickerData();

        List<RoleConfig> roleConfigList = readAllConfig();
        if(null != roleConfigList && 0!= roleConfigList.size()) {
            executeRole(roleConfigList);
        }
    }

    private void refreshTickerData() {
        for(Coin coin : Coin.values()) {
            LinkedList linkedList = historyTickers.get(coin);
            if(null == historyTickers.get(coin)) {
                linkedList = new LinkedList();
            } else if(1500 < historyTickers.get(coin).size()) {
                linkedList.removeLast();
            }
            linkedList.addFirst(okex.getTickerSpot(coin.toString()));

            historyTickers.put(coin, linkedList);
        }
    }

    private void executeRole(List<RoleConfig> roleConfigList) {
        List<RoleConfig> needNotify = new ArrayList<>(100);
        for(RoleConfig roleConfig : roleConfigList) {
            LinkedList<Ticker> tickers = historyTickers.get(Coin.getCoin(roleConfig.coin));
            Ticker now = tickers.getFirst();
            Ticker last = tickers.get(Integer.parseInt(roleConfig.cycle));
            if(Math.abs(Double.valueOf(now.getLast()) - Double.valueOf(last.getLast())) > Double.valueOf(roleConfig.target)) {
                needNotify.add(roleConfig);
            }
        }
        notifyToUser(needNotify);
    }

    private List<RoleConfig> readAllConfig() {
       Object config = redisService.get(ROLE_CONFIG_KEY);
       List<RoleConfig> res = JSONUtil.json2list(String.valueOf(config), RoleConfig.class);
       return res;
    }


    private void notifyToUser(List<RoleConfig> roleConfig) {
        Map<String, List<RoleConfig>> groupByEmail = new HashMap<>();

        for(RoleConfig tmp : roleConfig) {
            if(null == groupByEmail.get(tmp.email)) {
                groupByEmail.put(tmp.email, new ArrayList<>());
            }
            groupByEmail.get(tmp.email).add(tmp);
        }

        Map<String, String> emailData = new HashMap<>();
        for(String key : groupByEmail.keySet()) {
            StringBuilder stringBuilder = new StringBuilder("请注意,你所关注的以下币种发送剧烈波动:\r\n");
            for(RoleConfig tmp : groupByEmail.get(key)) {
                stringBuilder.append(String.format("[%s] 在 [%s] 分钟内波动 [%s]\r\n", tmp.coin, tmp.cycle, tmp.target));
            }
            emailData.put(key, stringBuilder.toString());
        }
        email(emailData);
    }

    private void email( Map<String, String> param) {
        for(String emailAddr : param.keySet()) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    EmailBuilder.build().sendSimpleEmail("jioulongzi@qq.com", emailAddr, "行情变化通知", param.get(emailAddr));
                }
            });
        }
    }

}


