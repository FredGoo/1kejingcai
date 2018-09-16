<template>
  <van-row class="fd-container">
    <!-- header -->
    <van-row>
      <van-col span="8">
        <div
          style="background-color: #fff;padding: 4px;border-radius: 5px;margin-left: 5px;box-shadow: 0 1px #888888;">
          <div
            style="border-radius: 5px;line-height: 25px;background: linear-gradient(#F0FFFF, #87CEEB);border:1px solid #87CEEB;font-weight: bold;">
            &yen;&nbsp;{{(totalPrice / 100).toFixed(2)}}
          </div>
        </div>
      </van-col>
      <van-col span="8" offset="8">
        <van-button type="default" size="small" class="button-brown" @click="onSubmitOrder">下&nbsp;单</van-button>
      </van-col>
    </van-row>
    <!-- ./ header -->

    <!-- body -->
    <van-row class="fd-body">

      <!-- 购物车 -->
      <van-col span="8" class="fd-product-picked">
        <div class="fd-product-col">
          <div class="fd-product-picked-title" style="margin-bottom: 16px;">
            <span>已选菜品</span>
          </div>
          <div>
            <div v-for="(cartProduct, index) in cart"
                 :key="index"
                 style="border:1px solid #bbb;border-radius: 3px;margin-bottom: 8px;background-color: #F0FFFF;">
              <div style="position:absolute;">
                <div class="van-badge__info"
                     style="min-width: 28px;line-height: 28px;border-radius: 14px;font-size: 16px;top: -12px;right: -14px;background-color: #bbb;"
                     @click="onProductRemoveClick(index)">
                  X
                </div>
              </div>
              <div style="border-bottom:1px solid #bbb;font-size: 14px;padding:6px 0;">
                {{cartProduct.cTitle}}
              </div>
              <div style="font-size: 13px;padding:6px 0;">
                &yen;{{(cartProduct.nAmount / 100).toFixed(2)}}&nbsp;<span style="font-size: 12px;">*&nbsp;{{cartProduct.pcs}}</span>
              </div>
            </div>
          </div>
        </div>
      </van-col>
      <!-- ./ 购物车 -->

      <van-col span="16" class="fd-product-list">
        <div class="fd-product-col">

          <!-- 分类名称提示 -->
          <van-row class="fd-product-list-title" style="height: 48px;">
            <van-col span="4">
              <van-icon name="arrow-left" class="fd-product-list-arrow" @click="turnLeft"/>
            </van-col>
            <van-col span="16">
              <div>
                {{currentCategory.cTitle}}
              </div>
              <div style="display: flex;justify-content: center;margin-top: 8px;">
                <i class="van-swipe__indicator"
                   v-bind:class="{'van-swipe__indicator--active': index == currentCategory.index}"
                   v-for="(category, index) in categories"
                   :key="index"/>
              </div>
            </van-col>
            <van-col span="4">
              <van-icon name="arrow" class="fd-product-list-arrow" @click="turnRight"/>
            </van-col>
          </van-row>
          <!-- ./ 分类名称提示 -->

          <van-row class="fd-product-list-body">
            <van-swipe :autoplay="0" style="height: 100%;" @change="onSwipeChange" ref="mySwipe">

              <!-- 分类 -->
              <van-swipe-item v-for="(category, index) in categories"
                              :key="index"
                              :show-indicators="false"
                              style="overflow: scroll;">

                <!-- 分类的产品 -->
                <div class="fd-product-list-body-page">
                  <div v-for="(product, index) in products"
                       :key="index"
                       @click="onProductClick(product)"
                       style="width: 50%;float: left;margin:6px 0;">
                    <div style="height: 100%;border: 1px solid #bbb;border-radius: 6px;margin:0 6px;">
                      <div>{{product.cTitle}}</div>
                      <div>
                        <img
                          :src="product.tImageUrl"
                          style="width: 100%;"/>
                        <div>&yen;{{(product.nAmount / 100).toFixed(2)}}</div>
                      </div>
                    </div>
                  </div>
                  <div style="clear: both;height: 48px;"></div>
                </div>
                <!-- ./ 分类的产品 -->

              </van-swipe-item>
              <!-- 分类 -->

            </van-swipe>
          </van-row>

        </div>
      </van-col>
    </van-row>
    <!-- ./ body -->
  </van-row>
</template>

<script>
  import {Toast} from 'vant';
  import LocalStorage from '../store/localStorage'
  import User from '../api/user'

  export default {
    name: 'Index',
    data: function () {
      return {
        totalPrice: 0,
        currentCategory: {
          index: 0,
          nId: 1,
          cTitle: '',
          nAmount: 0,
          tImageUrl: ''
        },
        // 类别
        categories: [],
        // 产品
        products: [],
        // 购物车
        cart: []
      }
    },
    mounted: function () {
      User.checkLogin()
      this.init()
    },
    methods: {
      // 初始化
      init() {
        this.$axios
          .get('/category/listAll')
          .then(response => {
            if (response.data.status) {
              this.categories = response.data.result

              // 初始化类别
              this.currentCategory = this.categories[0]
              this.currentCategory.index = 0

              // 获取当前类下的产品
              this.getProductByCategory(this.currentCategory.nId)

              // 获取localStorage
              let store = LocalStorage.fetch()
              if (store.totalPrice && store.orderItemList) {
                this.totalPrice = store.totalPrice
                this.cart = store.orderItemList
              }
            }
          })
      },
      // 往左划
      turnLeft() {
        let toIndex = this.currentCategory.index - 1
        if (toIndex < 0) {
          toIndex = this.categories.length - 1
        }

        this.$refs.mySwipe.swipeTo(toIndex)
      },
      // 往右划
      turnRight() {
        let toIndex = this.currentCategory.index + 1
        if (toIndex > this.categories.length - 1) {
          toIndex = 0
        }

        this.$refs.mySwipe.swipeTo(toIndex)
      },
      // 监听swipe的change事件
      onSwipeChange(index) {
        this.currentCategory = this.categories[index]
        this.currentCategory.index = index

        this.getProductByCategory(this.currentCategory.nId)
      },
      // 根据类别获取产品
      getProductByCategory(categoryId) {
        this.$axios
          .get('/product/getProductByCategoryId', {
            params: {
              categoryId: categoryId
            }
          })
          .then(response => {
            if (response.data.status) {
              this.products = response.data.result
            }
          })
      },
      // 添加产品
      onProductClick(product) {
        let productAlreadyIn = false

        for (let cartProductIndex in this.cart) {
          let cartProduct = this.cart[cartProductIndex]
          if (cartProduct.nId == product.nId) {
            product.pcs = parseInt(this.cart[cartProductIndex].pcs) + 1
            this.cart.splice(cartProductIndex, 1, product)

            productAlreadyIn = true
          }
        }

        if (!productAlreadyIn) {
          product.pcs = 1
          this.cart.push(product)
        }

        this.calcTotalAmount()
      },
      // 删除产品
      onProductRemoveClick(index) {
        let product = this.cart[index]
        let toPcs = product.pcs - 1

        if (toPcs < 1) {
          this.cart.splice(index, 1)
        } else {
          product.pcs = toPcs
          this.cart.splice(index, 1, product)
        }

        this.calcTotalAmount()
      },
      // 计算金额
      calcTotalAmount() {
        let totalPrice = 0

        for (let cartIndex in this.cart) {
          totalPrice += this.cart[cartIndex].nAmount * this.cart[cartIndex].pcs
        }

        this.totalPrice = totalPrice

        this.updateCart()
      },
      // 更新购物车
      updateCart() {
        LocalStorage.save({
          orderItemList: this.cart,
          totalPrice: this.totalPrice
        })
      },
      // 下单
      onSubmitOrder() {
        if (this.cart.length < 1) {
          Toast('请选择菜品');
          return
        }

        location.href = '/?1=1#/orderInfo'
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
