<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input placeholder="姓名" style="width: 200px;" class="filter-item" v-model="listQuery.name"/>
      <el-input placeholder="手机号" style="width: 200px;" class="filter-item" v-model="listQuery.mobile"/>
      <el-select placeholder="类型" class="filter-item" v-model="listQuery.type" :clearable="true">
        <el-option label="配送" :value="0"/>
        <el-option label="自取" :value="1"/>
      </el-select>
      <el-select placeholder="状态" class="filter-item" v-model="listQuery.status" :clearable="true">
        <el-option label="已保存" :value="0"/>
        <el-option label="未支付" :value="1"/>
        <el-option label="已支付" :value="2"/>
        <el-option label="已配送" :value="3"/>
      </el-select>
      <el-button type="primary" class="filter-item" @click="fetchData">搜索</el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="加载中"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="#" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="订单号">
        <template slot-scope="scope">
          {{ scope.row.order.cOrderNo }}
        </template>
      </el-table-column>
      <el-table-column label="类型">
        <template slot-scope="scope">
          {{ scope.row.order.nType | typeFilter }}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          {{ scope.row.order.nStatus | statusFilter }}
          <el-button v-if="scope.row.order.nStatus == 2" type="success"
                     @click="handleOrderFinished(scope.row.order.nId)">完成
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="总金额">
        <template slot-scope="scope">
          &yen;{{ (scope.row.order.nTotalAmount /100).toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="联系方式">
        <template slot-scope="scope">
          <div>{{ scope.row.order.cCustName }}&nbsp;{{ scope.row.order.cMobile }}</div>
          <div>{{ scope.row.order.tCustAddress }}</div>
          <div>{{ scope.row.order.dDeliver }}</div>
        </template>
      </el-table-column>
      <el-table-column label="菜品">
        <template slot-scope="scope">
          <div v-for="item of scope.row.orderItemList">
            {{ item.cProductTitle }}&nbsp;{{ (item.nProductAmount /100).toFixed(2) }}&nbsp;*&nbsp;{{ item.nProductPcs }}
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="listQuery.page"
        :page-size="listQuery.row"
        layout="total, prev, pager, next, jumper"
        :total="listTotal">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {getList, getListCount, setOrderFinished} from '@/api/order'

  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          '0': '已保存',
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
    data() {
      return {
        list: null,
        listLoading: true,
        listQuery: {
          page: 1,
          row: 20,
          name: null,
          mobile: null,
          type: null,
          status: null
        },
        listTotal: 0
      }
    },
    created() {
      this.fetchData()

      const timeId = setInterval(() => {
        this.fetchData()
      }, 30000)

    },
    methods: {
      // 获取订单数据
      fetchData() {
        this.listLoading = true
        getListCount(this.listQuery).then(response => {
          this.listTotal = response.result

          getList(this.listQuery).then(response => {
            this.list = response.result
            this.listLoading = false
          })
        })
      },
      // 换页
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchData()
      },
      // 完成订单
      handleOrderFinished(id) {
        setOrderFinished(id).then(response => {
          this.fetchData()
        })
      }
    }
  }
</script>

<style scoped>
  .filter-container {
    padding-bottom: 10px;
  }

  .filter-container .filter-item {
    display: inline-block;
    margin-bottom: 10px;
    vertical-align: middle;
  }
</style>
