<template>
  <van-row>
    <van-submit-bar
      button-text="确认订单"
      button-type="brown"
      @submit="onSubmit">
      <span slot>
        <div style="padding-left: 8px;">
          需支付：&yen;37.00
        </div>
      </span>
    </van-submit-bar>

    <van-cell-group>
      <van-cell title="选择送餐方式：">
        <van-radio-group v-model="radio">
          <van-radio name="1">配送</van-radio>
          <van-radio name="2">店内就餐</van-radio>
          <div style="clear: both;"></div>
        </van-radio-group>
      </van-cell>
      <van-cell title="上海一店" label="上海市一号路一号一零一室"/>
      <van-collapse v-model="activeNames">
        <van-collapse-item title="" name="1">
          <span slot="title">
            <div style="float: left;">已选菜品</div>
            <div style="float: right;color: #C8A550;">&yen;38&nbsp;</div>
            <div style="clear: both;"></div>
          </span>
          <span slot>
          菜品1&nbsp;15.20
          </span>
        </van-collapse-item>
      </van-collapse>
      <van-cell title="打包费：">
        <span solt>
          &yen;9.00
        </span>
      </van-cell>
      <van-cell title="订单金额：">
        <span solt>
          &yen;47.00
        </span>
      </van-cell>
      <van-cell title="自提时间" is-link @click="onPickTime"/>
      <van-field
        v-model="message"
        label="买家留言："
        type="textarea"
        placeholder="备注您的口味，喜好等"
        rows="2"
        autosize/>
    </van-cell-group>

    <!-- 配送时间 -->
    <van-actionsheet v-model="show">
      <van-picker
        show-toolbar
        title="选择自提时间"
        :columns="columns"
        @confirm="onConfirm"
        @cancel="onCancel"
        @change="onChange"/>
    </van-actionsheet>
    <!-- ./ 配送时间 -->

  </van-row>
</template>

<script>
  const time = ['11:30', '12:00', '12:30', '13:00', '13:30']
  const date = {
    '今天': time,
    '明天': time
  }

  export default {
    data: function () {
      return {
        xiaoji: '小计： 15.20',
        message: '',
        radio: '2',
        activeNames: ['0'],
        show: false,
        columns: [
          {
            values: Object.keys(date),
            className: 'column1'
          },
          {
            values: date['今天'],
            className: 'column2',
            defaultIndex: 2
          }
        ]
      }
    },
    methods: {
      onSubmit() {
        alert('即将前往支付页面')
        // this.$router.push('/')
      },
      onChange(picker, value, index) {
        console.log(picker)
        console.log(value)
        console.log(index)
      },
      onConfirm(value, index) {
        alert(`当前值：${value}, 当前索引：${index}`);
        this.show = false;
      },
      onCancel() {
        console.log('取消');
      },
      onPickTime() {
        this.show = true
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
