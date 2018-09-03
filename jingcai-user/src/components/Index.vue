<template>
  <van-row class="fd-container">
    <!-- header -->
    <van-row>
      <van-col span="8">
        <div
          style="background-color: #fff;padding: 4px;border-radius: 5px;margin-left: 5px;box-shadow: 0 1px #888888;">
          <div
            style="border-radius: 5px;line-height: 25px;background: linear-gradient(#F0FFFF, #87CEEB);border:1px solid #87CEEB;font-weight: bold;">
            &yen;&nbsp;{{totalPrice}}
          </div>
        </div>
      </van-col>
      <van-col span="8" offset="8">
        <router-link to="/checkout">
          <van-button type="default" size="small" class="button-brown">下&nbsp;单</van-button>
        </router-link>
      </van-col>
    </van-row>
    <!-- ./ header -->

    <!-- body -->
    <van-row class="fd-body">
      <van-col span="8" class="fd-product-picked">
        <div class="fd-product-col">
          <div class="fd-product-picked-title" style="margin-bottom: 16px;">
            <span>已选菜品</span>
          </div>
          <div>
            <div v-for="(product, index) in cart"
                 :key="index"
                 style="border:1px solid #bbb;border-radius: 3px;margin-bottom: 8px;background-color: #F0FFFF;">
              <div style="position:absolute;">
                <div class="van-badge__info"
                     style="min-width: 28px;line-height: 28px;border-radius: 14px;font-size: 16px;top: -12px;right: -14px;background-color: #bbb;">
                  X
                </div>
              </div>
              <div style="border-bottom:1px solid #bbb;font-size: 14px;padding:6px 0;">
                {{product}}
              </div>
              <div style="font-size: 13px;padding:6px 0;">
                &yen;2.85&nbsp;<span style="font-size: 12px;">*&nbsp;1</span>
              </div>
            </div>
          </div>
        </div>
      </van-col>

      <van-col span="16" class="fd-product-list">
        <div class="fd-product-col">

          <van-row class="fd-product-list-title" style="height: 48px;">
            <van-col span="4">
              <van-icon name="arrow-left" class="fd-product-list-arrow"/>
            </van-col>
            <van-col span="16">
              <div>
                {{currentCategory}}
              </div>
              <div style="display: flex;justify-content: center;margin-top: 8px;">
                <i class="van-swipe__indicator van-swipe__indicator--active"/>
                <i class="van-swipe__indicator"/>
              </div>
            </van-col>
            <van-col span="4">
              <van-icon name="arrow" class="fd-product-list-arrow"/>
            </van-col>
          </van-row>

          <van-row class="fd-product-list-body">
            <van-swipe :autoplay="0" style="height: 100%;">
              <van-swipe-item v-for="(category, index) in categorys"
                              :key="index"
                              :show-indicators="false"
                              style="overflow: scroll;">
                <div class="fd-product-list-body-page">
                  <div v-for="(product, index) in products"
                       :key="index"
                       style="width: 50%;float: left;margin:6px 0;">
                    <div style="height: 100%;border: 1px solid #bbb;border-radius: 6px;margin:0 6px;">
                      <div>{{product}}</div>
                      <div>
                        <img
                          src="https://img.yzcdn.cn/public_files/2017/09/05/c0dab461920687911536621b345a0bc9.jpg"
                          style="width: 100%;"/>
                      </div>
                    </div>
                  </div>
                  <div style="clear: both;height: 48px;"></div>
                </div>
              </van-swipe-item>
            </van-swipe>
          </van-row>

        </div>
      </van-col>
    </van-row>
    <!-- ./ body -->
  </van-row>
</template>

<script>
  export default {
    name: 'Index',
    data: function () {
      return {
        totalPrice: '0.00',
        currentCategory: '低脂先锋',
        categorys: [
          './assets/1.png',
          './assets/2.png'
        ],
        products: [
          '菜品1',
          '菜品2',
          '菜品3',
          '菜品4',
          '菜品5',
          '菜品6',
          '菜品7',
          '菜品8',
          '菜品9',
          '菜品10',
          '菜品11',
          '菜品12'
        ],
        cart: [
          '菜品1',
          '菜品2'
        ]
      }
    },
    mounted: function () {
    },
    methods: {
      // 获取类别
      getCategories() {
        this.$axios
          .get('/')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .fd-container {
    margin: 16px 8px;
  }

  .fd-body {
    margin-top: 16px;
    height: 550px;
  }

  .fd-product-col {
    height: 100%;
    margin: 0 5px;
    padding: 12px 6px;
    border-radius: 6px;
    overflow: hidden;
  }

  .fd-product-picked {
    height: 100%;
  }

  .fd-product-picked > div {
    background-color: #FFEBCD;
    border: 1px solid #E9967A;
  }

  .fd-product-picked > div > .fd-product-picked-title > span {
    border-bottom: 2px solid #E9967A;
    font-weight: bold;
  }

  .fd-product-list {
    height: 100%;
  }

  .fd-product-list > div {
    background-color: #F0FFFF;
    border: 1px solid #87CEEB;
  }

  .fd-product-list > div > .fd-product-list-title {
    font-weight: bold;
  }

  .fd-product-list-arrow {
    font-size: 24px;
    color: #bbb;
    line-height: 36px;
  }

  .fd-product-list > div > .fd-product-list-body {
    height: 100%;
  }

  .button-brown {
    padding: 0 18px;
    font-size: 14px;
    color: #ffffff;
    background-color: #C8A550;
  }
</style>
