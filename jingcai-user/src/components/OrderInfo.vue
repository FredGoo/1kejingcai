<template>
  <van-row>
    <van-submit-bar
      button-text="支付"
      button-type="brown"
      @submit="onSubmit">
      <span slot>
        <div style="padding-left: 8px;">
          需支付：&yen;{{((this.orderItemTotalPrice + this.serviceFee) / 100).toFixed(2)}}

        </div>
      </span>
    </van-submit-bar>

    <van-cell-group>
      <van-cell title="选择送餐方式：">
        <van-radio-group v-model="deliveryType" @change="onDeliveryChange">
          <van-radio name="1">店内就餐</van-radio>
          <van-radio name="0">配送</van-radio>
          <div style="clear: both;"></div>
        </van-radio-group>
      </van-cell>

      <!-- 配送信息 -->
      <van-cell :title="storeName" :label="storeAddress" v-show="deliveryShow.toGo"/>
      <van-row v-show="deliveryShow.atSite">
        <van-field
          v-model="userContact.name"
          label="收货人："
          type="text"/>
        <van-field
          v-model="userContact.mobile"
          label="电话："
          type="text"/>
        <van-cell title="详细地址" :label="customerFixedAddress">
          <van-field
            v-model="userContact.address"
            type="text">
          </van-field>
        </van-cell>
      </van-row>
      <!-- ./ 配送信息 -->

      <van-collapse v-model="activeNames">
        <van-collapse-item title="" name="1">
          <span slot="title">
            <div style="float: left;">已选菜品</div>
            <div style="float: right;color: #C8A550;">&yen;{{(orderItemTotalPrice / 100).toFixed(2)}}&nbsp;</div>
            <div style="clear: both;"></div>
          </span>
          <div slot v-for="(orderItem, index) in orderItemList" :key="index">
            {{orderItem.cTitle}}&nbsp;&yen;{{(orderItem.nAmount / 100).toFixed(2)}}&nbsp;*{{orderItem.pcs}}
          </div>
        </van-collapse-item>
      </van-collapse>
      <van-cell title="打包费：">
        <span solt>
          &yen;{{(this.serviceFee / 100).toFixed(2)}}
        </span>
      </van-cell>
      <van-cell title="订单金额：">
        <span solt>
          &yen;{{((this.orderItemTotalPrice + this.serviceFee) / 100).toFixed(2)}}
        </span>
      </van-cell>
      <van-cell title="自提时间" is-link @click="onPickTime">
        <span slot>{{pickTime}}</span>
      </van-cell>
      <van-field
        v-model="tRemark"
        label="买家留言："
        type="textarea"
        placeholder="备注您的口味，喜好等"
        rows="2"
        autosize/>
    </van-cell-group>

    <!-- 配送时间 -->
    <van-actionsheet v-model="pickTimeShow">
      <van-picker
        show-toolbar
        title="选择自提时间"
        :columns="columns"
        @change="onChange"
        @confirm="onConfirm"/>
    </van-actionsheet>
    <!-- ./ 配送时间 -->

  </van-row>
</template>

<script>
  import {Toast} from 'vant';
  import LocalStorage from '../store/localStorage'
  import User from '../api/user'

  export default {
    data: function () {
      return {
        // 配置
        config: {},

        // 门店信息
        storeName: '',
        storeAddress: '',

        // 订单产品信息
        orderItemList: LocalStorage.fetch().orderItemList || [],
        orderItemTotalPrice: LocalStorage.fetch().totalPrice || 0,

        // 配送信息
        deliveryType: '1',
        deliveryShow: {
          toGo: true,
          atSite: false
        },

        // 收货人信息
        customerFixedAddress: '',
        userContact: {
          name: '',
          mobile: '',
          address: ''
        },

        // 服务费用
        serviceFee: 0,

        // 取货时间选择
        activeNames: ['0'],
        pickTimeShow: false,
        // 选择时间
        pickUpTime: {},
        pickTime: '',
        columns: [],

        // 订单备注
        tRemark: '',

        // 支付信息
        unifiedOrder: {}
      }
    },
    mounted: function () {
      User.checkLogin().then(response => {
        this.userContact = {
          name: response.cName,
          mobile: response.cMobile,
          address: response.cAddress
        }
      })
      this.init()
    },
    methods: {
      // 初始化函数
      init() {
        this.$axios
          .get('/config/getConfigByCategories', {
            params: {
              categories: 'fee.deliver,store.info'
            }
          })
          .then(response => {
            if (response.data.status) {
              let configList = response.data.result
              for (let configItem of configList) {
                if (!this.config[configItem.cCategory]) {
                  this.config[configItem.cCategory] = {}
                }

                this.config[configItem.cCategory][configItem.cKey] = configItem.cVal
              }

              // 服务费用
              this.serviceFee = parseInt(this.config['fee.deliver'].AT_SITE)
              // 店名
              this.storeName = this.config['store.info'].STORE_NAME
              // 店址
              this.storeAddress = this.config['store.info'].STORE_ADDRESS
              // 客户固定地址
              this.customerFixedAddress = this.config['store.info'].CUST_ADDRESS
            }
          })
      },
      // 提交订单
      onSubmit() {
        // 表单校验
        if (0 == this.deliveryType) {
          if (!this.userContact.name) {
            Toast('请输入姓名')
            return
          }
          if (!this.userContact.mobile) {
            Toast('请输入电话')
            return
          }
          if (!this.userContact.address) {
            Toast('请输入详细地址')
            return
          }
        }
        if (!this.pickTime) {
          Toast('请选择自提时间')
          return
        }

        // 用户信息
        let user = {
          cName: this.userContact.name,
          cMobile: this.userContact.mobile,
          cAddress: this.userContact.address
        }

        // 订单数据
        let order = {
          nType: this.deliveryType,
          nProductsAmount: this.orderItemTotalPrice,
          nServiceAmount: this.serviceFee,
          nTotalAmount: this.serviceFee + this.orderItemTotalPrice,
          cDeliver: this.pickTime,
          tRemark: this.tRemark
        }
        let dateDeliver = this.pickTime.split(' ')
        const now = new Date()
        const monthStr = '0' + (now.getMonth() + 1)
        if (dateDeliver[0] == '今天') {
          dateDeliver = now.getFullYear() + '-' + monthStr.substr(monthStr.size - 2, monthStr) + '-' + now.getDate() + ' ' + dateDeliver[1] + ':00'
        } else {
          dateDeliver = now.getFullYear() + '-' + monthStr.substr(monthStr.size - 2, monthStr) + '-' + (now.getDate() + 1) + ' ' + dateDeliver[1] + ':00'
        }
        order.dDeliver = dateDeliver

        // 订单产品数据
        let orderItemList = []
        for (let productItem of this.orderItemList) {
          let orderItem = {
            nProductId: productItem.nId,
            cProductTitle: productItem.cTitle,
            nProductAmount: productItem.nAmount,
            nProductPcs: productItem.pcs
          }
          orderItemList.push(orderItem)
        }

        // 创建订单
        this.$axios
          .post('/order/create', {
            user: user,
            order: order,
            orderItemList: orderItemList
          })
          .then(response => {
            // 更新购物车
            LocalStorage.save({
              orderItemList: [],
              totalPrice: 0
            })

            this.$axios
              .post('/wechatPay/unifiedOrder', {
                "orderNo": response.data.result,
                "ip": returnCitySN.cip
              })
              .then(response1 => {
                // 微信支付
                this.onBridgeReady(response1.data.result)
              })
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
            if (res.err_msg != "get_brand_wcpay_request:ok") {
              alert('支付失败')
            }
            location.href = '/?1=1#/userOrders'
          });
      },
      // 配送方式选择
      onDeliveryChange(type) {
        if (1 == type) {
          this.deliveryShow.toGo = true
          this.deliveryShow.atSite = false
        } else {
          this.deliveryShow.toGo = false
          this.deliveryShow.atSite = true
        }
      },
      // 时间选择
      onPickTime() {
        const now = new Date()
        const startHour = 11
        const endHour = 20

        let date = []

        this.pickUpTime['明天'] = []
        for (let i = startHour; i <= endHour; i++) {
          this.pickUpTime['明天'].push(i + ':00')
        }
        // 判断营业时间
        if (now.getHours() > endHour) {
          date = ['明天']
        } else {
          date = ['今天', '明天']
          this.pickUpTime['今天'] = []
          for (let i = (now.getHours() + 1); i <= endHour; i++) {
            this.pickUpTime['今天'].push(i + ':00')
          }
        }

        this.columns = [{
          values: date,
          className: 'column1'
        },
          {
            values: this.pickUpTime[date[0]],
            className: 'column2',
            defaultIndex: 2
          }]

        this.pickTimeShow = true
      },
      onConfirm(value, index) {
        this.pickTime = value[0] + " " + value[1]
        this.pickTimeShow = false;
      },
      onChange(picker, values) {
        console.log(values)
        console.log(this.pickUpTime)
        picker.setColumnValues(1, this.pickUpTime[values[0]]);
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
