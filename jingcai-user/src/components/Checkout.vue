<template>
  <van-row>
    <van-submit-bar
      :price="3050"
      button-text="去结算"
      button-type="brown"
      @submit="onSubmit">
      <van-goods-action-mini-btn icon="cart" info="2"/>
      <span slot="tip" style="color: #333;">选购多份套餐可享受折扣，多买多省</span>
    </van-submit-bar>

    <van-panel>
      <div>
        <van-row style="padding: 8px;">
          <van-col span="22" style="text-align: left;padding-left: 8px;">
            菜品1
          </van-col>
          <van-col span="2">
            <van-icon name="arrow"/>
          </van-col>
        </van-row>
      </div>
      <div slot="header" style="border-bottom: 1px solid #e5e5e5;padding: 8px 14px;">
        <van-row>
          <van-col span="12" style="text-align: left;font-size: 18px;font-weight: bold;">
            自选订单
          </van-col>
          <van-col span="12" style="text-align: right;font-size: 14px;">
            <!--小计：<span style="color: #C8A550;">15.20</span>-->
          </van-col>
        </van-row>
      </div>
      <div slot="footer">
        <van-row>
          <!--<van-col span="11">-->
            <!--<van-button size="large" type="brown">再来一单</van-button>-->
          <!--</van-col>-->
          <van-col span="11" offset="13">
            <van-button size="large">删除订单</van-button>
          </van-col>
        </van-row>
      </div>
    </van-panel>
  </van-row>
</template>

<script>
  export default {
    data: function () {
      return {
        xiaoji: '小计： 15.20'
      }
    },
    methods: {
      onSubmit() {
        this.$router.push('/orderInfo')
      }
    }
  }
</script>

<style>
  .van-button--brown {
    background-color: #C8A550;
    color: #fff;
  }

  .van-submit-bar__price-integer {
    color: #C8A550;
  }

  .van-submit-bar__price-decimal {
    color: #C8A550;
  }
</style>
