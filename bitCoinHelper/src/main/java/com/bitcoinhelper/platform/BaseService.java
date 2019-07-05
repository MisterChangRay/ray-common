package com.bitcoinhelper.platform;



import com.bitcoinhelper.platform.enums.Coin;
import com.bitcoinhelper.platform.vo.*;
import com.ray.common.core.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 基础服务定义
 * <p>
 * 这里的数据获取方式至少实现一种;
 * websocket实现后请在/common/init/init.class 中启动
 * ajax实现后请在/quartz/GlobalQuartz.claa 中启动
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  2018/4/20.
 */
public abstract class BaseService {
    Logger logger = LoggerFactory.getLogger(BaseService.class);
    public static int maxCacheSize = 100;


    /**
     * 此方法将会再程序启动完成后自动调用
     */
    public Boolean init() {
        return null;
    }






    /**
     * 获取最新得ticker出局,期货
     *
     * @param symbol       交易对
     * @param contract_type 合约类型
     * @return
     */
    public Ticker getTickerOtc(Coin symbol, String contract_type) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    /**
     * 获取最新得深度数据S,期货
     *
     * @param symbol       交易对
     * @param contract_type 合约类型
     * @return
     */
    public DepthVo getDepthOtc(Coin symbol, String contract_type) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    /**
     * 获取K线数据,期货
     *
     * @param cycle:        1min, 3min, 5min, 15min, 30min, 1hour, 2hour, 4hour, 6hour, 12hour, day, 3day, week
     * @param contract_type 合约类型
     * @return
     */
    public List<Kline> getRecordsOtc(Coin symbol, String contract_type, String cycle, Long size) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    /**
     * 获取最新得ticker数据，现货
     *
     * @param symbol 交易对
     * @return
     */
    public Ticker getTickerSpot(String symbol) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    /**
     * 获取最新得深度数据，现货
     *
     * @param symbol 交易对
     * @return
     */
    public DepthVo getDepthSpot(String symbol) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    /**
     * 获取K线数据， 现货
     *
     * @param cycle: 1min, 5min, 15min, 30min, 1hour, 2hour, 4hour, 6hour, 12hour, day, 3day, week
     * @return
     */
    public List<Kline> getRecordsSpot(Coin symbol, String cycle, Long size) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    /**
     * 下现货单
     *
     * @param symbol 交易对
     * @param price  单价, 当price 为 null 时，请下市价单
     * @param amount 数量
     * @return 订单ID
     */
    public BaseResponse<String> buy(Coin symbol, BigDecimal price, BigDecimal amount, String aKey, String sKey, Map<String, String> args) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    public BaseResponse<String> sell(Coin symbol, BigDecimal price, BigDecimal amount, String aKey, String sKey, Map<String, String> args) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    public BaseResponse<String> cancelOrder(String orderId, Coin symbol, String aKey, String sKey, Map<String, String> args) throws Exception {
        throw new RuntimeException("not yet implemented");
    }



    /**
     * 下期货单
     *
     * @param symbol        交易币种
     * @param contract_type 合约类型: this_week:当周 next_week:下周 quarter:季度
     * @param direct        下单方向， duo/kong/pingduo/pingkong
     * @param lever_rate    杠杆倍数
     * @param price         下单价格  当price 为 null 时，请下市价单
     * @param amount        下单数量
     * @param aKey          apikey
     * @param sKey          sercrkey
     * @return
     */
    public BaseResponse<String> futureOrder(Coin symbol, String contract_type, String direct, Integer lever_rate, String price, String amount, String aKey, String sKey, Map<String, String> args) throws Exception {
        throw new RuntimeException("not yet implemented");
    }


    /**
     * 取消期货订单
     *
     * @param orderId
     * @param symbol
     * @param contractType
     * @param aKey
     * @param sKey
     * @return
     */
    public BaseResponse<String> cancelOrderFuture(String orderId, Coin symbol, String contractType, String aKey, String sKey, Map<String, String> args) throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    ;


    /**
     * 获取订单详情，现货
     *
     * @param symbol
     * @return
     */
    public Order getOrderSpot(String orderId, Coin symbol, String aKey, String sKey) throws Exception {
        throw new RuntimeException("not yet implemented");
    }




    /**
     * 获取订单详情，期货
     *
     * @param symbol
     * @return
     */
    public Order getOrderOtc(String orderId, Coin symbol, String contractType, String aKey, String sKey) throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    ;

    /**
     * 获取交易对得所有未完成订单，现货
     *
     * @param symbol
     * @return
     */
    public List<Order> getOrdersSpot(Coin symbol, String aKey, String sKey) throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    ;

    /**
     * 获取交易对得所有未完成订单，期货
     *
     * @param symbol
     * @return
     */
    public List<Order> getOrdersOtc(Coin symbol, String contractType, String aKey, String sKey) throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    ;

    /**
     * 获取账户余额情况
     *
     * @return
     */
    public List<AccountBalance> getAccount(String aKey, String sKey) throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    ;


    /**
     * 自定义请求;用于处理未实现得各种请求
     *
     * @return
     */
    public String customRequest(String method, String url, Map<String, String> params, String aKey, String sKey) throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    ;

    /**
     * 获取当前持仓情况;期货交易
     *
     * @return
     */
    public List<Position> getPositionOtc(Coin symbol, String contractType, String aKey, String sKey) throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    /**
     * 资产转换,用于总共转换成统一得资产
     *
     * @param symbol 用于转换得币种
     * @param amount 改币得数量
     * @param to     转为为什么价格, 只能为 btc 或者 usdt
     * @return
     */
    public Double convertAsset(String symbol, Double amount, String to) throws Exception {
        return 0d;
    }

}
