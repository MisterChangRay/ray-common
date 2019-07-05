package com.bitcoinhelper.platform.enums;

public enum Coin {
    //我推荐的币种
    BTC_USDT, BCH_USDT, ETH_USDT, BSV_USDT, XMR_USDT, DCR_USDT, ZEN_USDT, ATOM_USDT, OMG_USDT, ETC_USDT, LTC_USDT, EOS_USDT, DASH_USDT, IOTA_USDT, ZEC_USDT, BTM_USDT, QTUM_USDT, NEO_USDT, TRX_USDT,
    EOS_ETH, LTC_ETH, ETC_ETH, DASH_ETH, IOTA_ETH, BCH_ETH, ZEC_ETH, BTM_ETH, QTUM_ETH, NEO_ETH, TRX_ETH, XMR_ETH, DCR_ETH,
    BCH_BTC, ETH_BTC, LTC_BTC, ETC_BTC, EOS_BTC, DASH_BTC, IOTA_BTC, ZEC_BTC, BTM_BTC, QTUM_BTC, NEO_BTC, TRX_BTC,


    //第三方网友推荐的
    DGD_USDT,
    MKR_ETH, DGD_ETH,
    ;
    public static Coin getCoin(String name) {
        name = name.replaceAll("[-_/\\\\]", "");
        for (Coin coinSpot : Coin.values()) {
            if (coinSpot.toString().equalsIgnoreCase(name) ||
                    coinSpot.toString().replace("_", "").equalsIgnoreCase(name)) {
                return coinSpot;
            }
        }
        throw new RuntimeException("交易对名称错误，只能为[) + ");
    }

    public static String[] getCoins() {
        String[] types = new String[Coin.values().length];
        int i = 0;
        for (Coin coinOtc : Coin.values()) {
            types[i] = coinOtc.toString();
            i++;
        }
        return types;
    }

}
