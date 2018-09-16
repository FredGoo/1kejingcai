<template>
  <el-row>
    <el-form :model="form" style="margin-top: 18px;" label-width="80px">
      <el-form-item label="门店地址" :label-width="formLabelWidth">
        <el-input v-model="form['store.info']['STORE_ADDRESS']" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="外卖费用" :label-width="formLabelWidth">
        <el-input v-model="atSiteFix" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="堂吃费用" :label-width="formLabelWidth">
        <el-input v-model="toGoFix" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="用户地址" :label-width="formLabelWidth">
        <el-input v-model="form['store.info']['CUST_ADDRESS']" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</template>

<script>
  import {getConfigByCategories, updateConfig} from '@/api/config'

  export default {
    data() {
      return {
        formLabelWidth: '100px',

        form: {
          'fee.deliver': {
            'AT_SITE': 0,
            'TO_GO': 0
          },
          'store.info': {
            'STORE_ADDRESS': '',
            'CUST_ADDRESS': ''
          }
        }
      }
    },
    created() {
      this.init()
    },
    computed: {
      atSiteFix: {
        get: function () {
          return (this.form['fee.deliver']['TO_GO'] / 100).toFixed(2)
        },
        set: function (val) {
          this.form['fee.deliver']['TO_GO'] = (val * 100).toFixed(0)
        }
      },
      toGoFix: {
        get: function () {
          return (this.form['fee.deliver']['AT_SITE'] / 100).toFixed(2)
        },
        set: function (val) {
          this.form['fee.deliver']['AT_SITE'] = (val * 100).toFixed(0)
        }
      }
    },
    methods: {
      init() {
        getConfigByCategories('fee.deliver,store.info').then(response => {
          if (response.status) {
            for (const item of response.result) {
              this.form[item.cCategory][item.cKey] = item.cVal
            }
          }
        })
      }
      ,
      onSubmit() {
        let data = [
          {cCategory: 'fee.deliver', cKey: 'TO_GO', cVal: this.form['fee.deliver']['TO_GO']},
          {cCategory: 'fee.deliver', cKey: 'AT_SITE', cVal: this.form['fee.deliver']['AT_SITE']},
          {cCategory: 'store.info', cKey: 'STORE_ADDRESS', cVal: this.form['store.info']['STORE_ADDRESS']},
          {cCategory: 'store.info', cKey: 'CUST_ADDRESS', cVal: this.form['store.info']['CUST_ADDRESS']}
        ]

        updateConfig(data).then(response => {
          if (response.status) {
            this.$message({
              type: 'info',
              message: '保存成功'
            })
          }
        })
      }
    }
  }
</script>
