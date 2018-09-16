<template>
  <van-row>
    <van-cell-group>
      <van-cell icon="records" style="font-size: 16px">
        我的订单
      </van-cell>
    </van-cell-group>

    <van-cell-group>
      <van-cell v-for="item of list" :key="item.order.nId">
        <template slot>
          <div>订单编号：{{ item.order.cOrderNo }}</div>
          <div>取货方式：{{ item.order.nType | typeFilter }}</div>
          <div>订单状态：{{ item.order.nStatus | statusFilter }}</div>
          <div>配送时间：{{ item.order.cDeliver }}</div>
          <div>包含商品：</div>
          <div>
            <div v-for="orderItem of item.orderItemList" :key="orderItem.nId" style="padding-left: 20px;">
              {{ orderItem.cProductTitle }}&nbsp;*&nbsp;{{ orderItem.nProductPcs }}
            </div>
          </div>
          <div>
            <div style="float: left;padding: 8px 0">
              总计金额：&yen;{{ (item.order.nTotalAmount / 100).toFixed(2) }}
            </div>
            <div style="float: right;">
              <van-button v-if="item.order.nStatus == 0" type="danger" size="small" @click="pay(item.order.cOrderNo)">
                去支付
              </van-button>
            </div>
            <div style="clear: both;"></div>
          </div>
        </template>
      </van-cell>
    </van-cell-group>

  </van-row>
</template>

<script>
  import {Toast} from 'vant';
  import LocalStorage from '../store/localStorage'
  import User from '../api/user'

  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          '0': '未支付',
          '1': '未支付',
          '2': '已支付',
          '3': '已完成'
        }
        return statusMap[status]
      },
      typeFilter(status) {
        const statusMap = {
          '0': '配送',
          '1': '自取'
        }
        return statusMap[status]
      }
    },
    data: function () {
      return {
        activeNames: [1],

        list: []
      }
    },
    mounted: function () {
      User.checkLogin('userOrders')
      this.init()
    },
    methods: {
      // 初始化函数
      init() {
        this.$axios
          .get('/order/userOrder')
          .then(response => {
            if (response.data.status) {
              this.list = response.data.result
            }
          })
      },
      // 支付订单
      pay(orderNo) {
        this.$axios
          .post('/wechatPay/unifiedOrder', {
            "orderNo": orderNo,
            "ip": returnCitySN.cip
          })
          .then(response1 => {
            // 微信支付
            this.onBridgeReady(response1.data.result)
          })
      },
      onBridgeReady(data) {
        WeixinJSBridge.invoke(
          'getBrandWCPayRequest', {
            "appId": data.appId,     //公众号名称，由商户传入
            "timeStamp": data.timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr": data.nonceStr, //随机串
            "package": data.package,
            "signType": "MD5",         //微信签名方式：
            "paySign": data.paySign //微信签名
          },
          function (res) {
            if (res.err_msg == "get_brand_wcpay_request:ok") {
              location.reload()
            } else {
              alert('支付失败')
            }
          });
      }
    }
  }
</script>

<style>
  .van-button--brown {
    background-color: #C8A550;
    color: #fff;
  }

  .van-cell__title {
    text-align: left;
  }

  .van-collapse-item__content {
    background-color: #F5F5F5;
    padding: 6px 16px;
    font-size: 12px;
    text-align: left;
  }

  .van-radio {
    float: left;
    margin-left: 10%;
  }

  .van-radio .van-icon-checked {
    color: #C8A550;
  }
</style>
