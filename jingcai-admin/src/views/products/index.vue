<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button type="success" class="filter-item" @click="showProduct">添加</el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="#" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="名称">
        <template slot-scope="scope">
          {{ scope.row.cTitle }}
        </template>
      </el-table-column>
      <el-table-column label="价格" align="center">
        <template slot-scope="scope">
          <span>&yen;{{(scope.row.nAmount / 100).toFixed(2) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="照片" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.tImageUrl" style="height: 40px;">
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="showProduct(scope.$index)">修改</el-button>
          <el-button type="danger" @click="deleteProduct(scope.row.nId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="产品详情" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="form.cTitle" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="价格" :label-width="formLabelWidth">
          <el-input v-model="form.nAmount" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="图片地址" :label-width="formLabelWidth">
          <el-input v-model="form.tImageUrl" auto-complete="off"></el-input>
          <div><a href="/admin/oss/" target="_blank">上传图片</a></div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateProduct">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {getList, update, deleteItem} from '@/api/product'

  export default {
    data() {
      return {
        list: null,
        listLoading: true,

        dialogFormVisible: false,
        formLabelWidth: '100px',
        form: {
          nId: null,
          cTitle: '',
          nAmount: 0,
          tImageUrl: ''
        }
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getList().then(response => {
          this.list = response.result
          this.listLoading = false
        })
      },
      showProduct(index) {
        if (this.list[index]) {
          this.form.nId = this.list[index].nId
          this.form.cTitle = this.list[index].cTitle
          this.form.nAmount = (this.list[index].nAmount / 100).toFixed(2)
          this.form.tImageUrl = this.list[index].tImageUrl
        } else {
          this.form = {
            nId: null,
            cTitle: '',
            nAmount: 0.00,
            tImageUrl: ''
          }
        }

        this.dialogFormVisible = true
      },
      updateProduct() {
        let data = this.form
        data.nAmount = data.nAmount * 100

        update(data).then(response => {
          this.fetchData()
          this.dialogFormVisible = false
        })
      },
      deleteProduct(id) {
        this.$confirm('此操作将永久删除该产品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteItem(id).then(response => {
            this.fetchData()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
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
