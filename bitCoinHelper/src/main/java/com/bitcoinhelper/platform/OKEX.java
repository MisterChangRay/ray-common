package com.bitcoinhelper.platform;

import com.bitcoinhelper.init.Init;
import com.bitcoinhelper.platform.enums.Coin;
import com.bitcoinhelper.platform.vo.Ticker;
import com.fasterxml.jackson.databind.JsonNode;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.ray.common.core.util.DateUtils;
import com.ray.common.core.util.JSONUtil;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 4/29/2018.
 *
 * 实现版本V3
 *
 * 炒币死的早;屯币是王道
 */

@Component
public class OKEX extends BaseService {
    private String socketUrl = "wss://real.okex.com:10442/ws/v3"; //socket地址
    private String resetUrl = "https://www.okex.com/"; //地址
    private static Map<Coin, Ticker> tickerMap = new HashMap<>(1000);
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    public Boolean init() {
        openSocketSpot(Coin.values());
        return super.init();
    }

    @Override
    public Ticker getTickerSpot(String symbol)  {
        return tickerMap.get(Coin.getCoin(symbol));
    }


    private static WebSocket ws = null;
    private void openSocketSpot( Coin[] coinses) {
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        TimeZone tz2 = TimeZone.getTimeZone("UTC");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ws = Init.webSocketFactory.createSocket(socketUrl);
                    ws.setPingInterval(3 * 1000);
                    ws.addListener(new WebSocketAdapter() {
                        Logger logger = LoggerFactory.getLogger(OKEX.class);

                        @Override
                        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                            super.onConnected(websocket, headers);
                            logger.info("--------------------------- OKEX otc websocket connect success [{}] ---------------------------", DateUtils.now(null));
                            StringBuilder cmd = new StringBuilder("{\"op\": \"subscribe\", \"args\": [");
                            for (Coin coin : coinses) {
                                String type = coin.toString().replace("_", "-");
                                cmd.append(MessageFormat.format("\"spot/ticker:{0}\",", type));  //ticker订阅
                            }
                            cmd.delete(cmd.length() -1, cmd.length());
                            cmd.append("]}");
                            ws.sendText(cmd.toString());

                        }

                        @Override
                        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
                            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
                            logger.info("--------------------------- OKEX otc websocket connect error,retry connect[{}] ---------------------------", DateUtils.now(null));
                            //连接断开后清空所有数据
                            tickerMap.clear();
                            openSocketSpot( coinses);
                        }

                        @Override
                        public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
                            String text = new String(uncompress(binary));
                            JsonNode res = JSONUtil.buildJsonNode(text);
                            if (res.isObject()) {
                                if (null != res.get("table")) {
                                    String key = res.get("table").asText();
                                    if (key.contains("ticker")) {
                                        //解析ticker数据
                                        res = res.get("data").get(0);
                                        Ticker ticker = new Ticker();
                                        ticker.setBuy(res.get("best_bid").asText());
                                        ticker.setSell(res.get("best_ask").asText());
                                        ticker.setHigh(res.get("high_24h").asText());
                                        ticker.setLow(res.get("low_24h").asText());
                                        ticker.setLast(res.get("last").asText());
                                        ticker.setUpdateTime(DateUtils.utcToDate(res.get("timestamp").asText()).getTime());
                                        ticker.setVol(res.get("base_volume_24h").asText());
                                        ticker.setInfo(res);
                                        tickerMap.put(Coin.getCoin(res.get("instrument_id").asText()), ticker);
                                    }
                                }
                            }
                        }


                    });
                    ws.connect();
                } catch (Exception e) {
                    logger.warn("--------------------------- okex otc websocket connect error , retry connect [{}]; info{} ---------------------------", DateUtils.now(null), e.toString());
                    openSocketSpot( coinses);
                }
            }
        });
    }

    /**
     * 解压socket信息
     * @param bytes
     * @return
     */
    private static String uncompress(byte[] bytes) {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream();
             final ByteArrayInputStream in = new ByteArrayInputStream(bytes);
             final Deflate64CompressorInputStream zin = new Deflate64CompressorInputStream(in)) {
            final byte[] buffer = new byte[1024];
            int offset;
            while (-1 != (offset = zin.read(buffer))) {
                out.write(buffer, 0, offset);
            }
            return out.toString();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
